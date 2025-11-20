package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.model.*;
import com.mycompany.asiproyecto.dao.*;

public class LoginService {
    static public Alumno consultarAlumnoDB(String correoElectronico, char[] contrasena) {
        AlumnoDAO adao = new AlumnoDAO();
        Alumno a = adao.obtenerAlumno(correoElectronico, contrasena);
        return a;
    }
    
    static public Profesor consultarProfesorDB(String correoElectronico, char[] contrasena) {
        ProfesorDAO pdao = new ProfesorDAO();
        Profesor p = pdao.obtenerProfesor(correoElectronico, contrasena);
        return p;
    }
    
    static public EmpleadoEmpresa consultarEmpleadoEmpresaDB(String correoCorporativo, char[] contrasena) {
        EmpleadoEmpresaDAO edao = new EmpleadoEmpresaDAO();
        EmpleadoEmpresa e = edao.obtenerEmpleadoEmpresa(correoCorporativo, contrasena);
        return e;
    }
}
