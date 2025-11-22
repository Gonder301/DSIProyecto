package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import java.util.Arrays;
import com.mycompany.asiproyecto.model.*;
import com.mycompany.asiproyecto.dao.*;
import com.mycompany.asiproyecto.view.RegistrarseJDialog;
import com.mycompany.asiproyecto.service.RegistrarseService;
import java.awt.CardLayout;

public class RegistrarseController {
    public void cambiarTipoUsuarioCard(String cardName, RegistrarseJDialog vista) {
        CardLayout cl = (CardLayout) vista.cardHolderPanel.getLayout();
        cl.show(vista.cardHolderPanel, cardName);
        switch(cardName) {
            case "estudianteCard":
                vista.botonEstudiante.setBackground(Colores.BUTTON_YELLOW);
                vista.botonProfesor.setBackground(Colores.BUTTON_GRAY);
                vista.botonEmpresa.setBackground(Colores.BUTTON_GRAY);
                break;
            case "profesorCard":
                vista.botonEstudiante.setBackground(Colores.BUTTON_GRAY);
                vista.botonProfesor.setBackground(Colores.BUTTON_YELLOW);
                vista.botonEmpresa.setBackground(Colores.BUTTON_GRAY);
                break;
            case "empresaCard":
                vista.botonEstudiante.setBackground(Colores.BUTTON_GRAY);
                vista.botonProfesor.setBackground(Colores.BUTTON_GRAY);
                vista.botonEmpresa.setBackground(Colores.BUTTON_YELLOW);
                break;
        }
    }
    
    private boolean contraCoincidenAlumno(RegistrarseJDialog vista) {
        return Arrays.equals(vista.contraPasswordFieldA1.getPassword(), vista.contraPasswordFieldA2.getPassword());
    }
    
    private boolean contraCoincidenProfesor(RegistrarseJDialog vista) {
        return Arrays.equals(vista.contraPasswordFieldP1.getPassword(), vista.contraPasswordFieldP2.getPassword());
    }
    
    private boolean contraCoincidenEmpleado(RegistrarseJDialog vista) {
        return Arrays.equals(vista.contraPasswordFieldE1.getPassword(), vista.contraPasswordFieldE2.getPassword());
    }
    
    public void procesarRegistroAlumno(RegistrarseJDialog vista) {
        int camposVacios = RegistrarseService.camposVaciosFormAlumno(vista);
        if (camposVacios > 0) {
            vista.msgRegistrarseA.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        if (!contraCoincidenAlumno(vista)) {
            vista.msgRegistrarseA.setText("Las contraseñas no coinciden.");
            return;
        }
        
        char[] contrasena = vista.contraPasswordFieldA1.getPassword();
        
        Alumno a = RegistrarseService.obtenerAlumnoDeForm(vista);
        
        //Poner dentro de servicio
        AlumnoDAO adao = new AlumnoDAO();
        if (adao.registrarAlumno(a, contrasena)) {
            vista.msgRegistrarseA.setText("El alumno se registró con éxito.");
        }
    }
    
    public void procesarRegistroProfesor(RegistrarseJDialog vista) {
        int camposVacios = RegistrarseService.camposVaciosFormProfesor(vista);
        if (camposVacios > 0) {
            vista.msgRegistrarseP.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        if (!contraCoincidenProfesor(vista)) {
            vista.msgRegistrarseP.setText("Las contraseñas no coinciden.");
            return;
        }
        
        char[] contrasena = vista.contraPasswordFieldP1.getPassword();
        
        Profesor p = RegistrarseService.obtenerProfesorDeForm(vista);
        
        //Poner dentro de servicio
        ProfesorDAO pdao = new ProfesorDAO();
        if (pdao.registrarProfesor(p, contrasena)) {
            vista.msgRegistrarseP.setText("El profesor se registró con éxito.");
        }
    }
    
    public void procesarRegistroEmpresa(RegistrarseJDialog vista) {
        int camposVacios = RegistrarseService.camposVaciosFormEmpresa(vista);
        if (camposVacios > 0) {
            vista.msgRegistrarseE.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        if (!contraCoincidenEmpleado(vista)) {
            vista.msgRegistrarseE.setText("Las contraseñas no coinciden.");
            return;
        }
        
        char[] contrasena = vista.contraPasswordFieldE1.getPassword();
        
        EmpleadoEmpresa e = RegistrarseService.obtenerEmpleadoEmpresaDeForm(vista);
        
        //Poner dentro de servicio
        EmpleadoEmpresaDAO edao = new EmpleadoEmpresaDAO();
        if (edao.registrarEmpleadoEmpresa(e, contrasena)) {
            vista.msgRegistrarseE.setText("La empresa se registró con éxito.");
        }
    }
}
