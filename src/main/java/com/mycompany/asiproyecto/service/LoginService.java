package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.model.*;
import com.mycompany.asiproyecto.dao.*;

public class LoginService {
    public Alumno consultarAlumnoDB(String correoElectronico, String contrasena) {
        AlumnoDAO adao = new AlumnoDAO();
        Alumno a = adao.obtenerAlumno(correoElectronico, contrasena);
        return a;
    }
    
    public Profesor consultarProfesorDB(String correoElectronico, String contrasena) {
        ProfesorDAO pdao = new ProfesorDAO();
        Profesor p = pdao.obtenerProfesor(correoElectronico, contrasena);
        return p;
    }
    
    public EmpleadoEmpresa consultarEmpleadoEmpresaDB(String correoElectronico, String contrasena) {
        EmpleadoEmpresaDAO edao = new EmpleadoEmpresaDAO();
        EmpleadoEmpresa e = edao.obtenerEmpleadoEmpresa(correoElectronico, contrasena);
        return e;
    }
}
