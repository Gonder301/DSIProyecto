package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.view.LoginJDialog;
import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.Placeholder;
import com.mycompany.asiproyecto.service.LoginService;
import com.mycompany.asiproyecto.model.*;
import com.mycompany.asiproyecto.view.InicioAlumno;
import java.util.Arrays;
import javax.swing.JFrame;

public class LoginController {
    public void procesarValidacion(LoginJDialog vista) {
        //Verifica estado de los campos (correo y contra)
        int estado = 0;
        String correo = vista.getCorreoTextFieldText();
        char[] contrasena = vista.getContraPasswordFieldArray();
        
        boolean correoInvalido = (correo == null || correo.trim().isEmpty() || correo.equals(Placeholder.correo));
        boolean contraInvalida = (contrasena == null || contrasena.length == 0 || 
                Arrays.equals(contrasena, Placeholder.contra.toCharArray()));
        
        if (correoInvalido && contraInvalida) {
            estado = 3;
        } else if (correoInvalido) {
            estado = 1;
        } else if (contraInvalida) {
            estado = 2;
        }
        
        //Modifica la vista de acuerdo al estado
        //0 <- todo bien
        //1 <- Correo vacío o con el placeholder
        //2 <- Contraseña vacía o con el placeholder
        //3 <- Correo y Contraseña ...
        switch (estado) {
            case 0:
                //Se crea una instancia de LoginService para comunicarse con la base de datos.
                LoginService ls = new LoginService();
                String tipoUsuario = vista.getTipoUsuario();
                if (tipoUsuario.equals("Alumno")) {
                    Alumno a = ls.consultarAlumnoDB(correo, new String(contrasena));
                    if (a != null) {
                        InicioAlumno inicioAlumno = new InicioAlumno();
                        inicioAlumno.setVisible(true);
                        vista.dispose();
                    }
                } else if (tipoUsuario.equals("Profesor")) {
                    Profesor p = ls.consultarProfesorDB(correo, new String(contrasena));
                } else if (tipoUsuario.equals("Empresa")) {
                    EmpleadoEmpresa e = ls.consultarEmpleadoEmpresaDB(correo, new String(contrasena));
                }
                break;
            case 1:
                vista.cambiarCorreoBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarMsgError("Por favor ingrese un correo válido.");
                break;
            case 2:
                vista.cambiarContraBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarMsgError("Por favor ingrese su contraseña.");
                break;
            case 3:
                vista.cambiarCorreoBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarContraBorder(Colores.textFieldBorderErr, 2);
                vista.cambiarMsgError("Debe ingresar correo y contraseña.");
                break;
        }
    }
}
