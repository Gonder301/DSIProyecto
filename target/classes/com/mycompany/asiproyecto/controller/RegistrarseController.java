package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.model.Alumno;
import com.mycompany.asiproyecto.dao.AlumnoDAO;
import com.mycompany.asiproyecto.view.RegistrarseJDialog;

public class RegistrarseController {
    public void procesarRegistroAlumno(RegistrarseJDialog vista) {
        //Validar que los campos esten llenos (crear funcion privada en la clase)
        char[] contrasena = vista.getPassword();
        //...
        
        Alumno a = vista.obtenerAlumnoDeForm();
        
        //Poner dentro de servicio
        AlumnoDAO adao = new AlumnoDAO();
        if (adao.registrarAlumno(a, new String(contrasena))) {
            vista.setMsgRegistrarse("El alumno se registró con éxito.");
        }
    }
}
