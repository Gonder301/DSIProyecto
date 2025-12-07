package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.view.InicioAlumno;
import java.time.format.DateTimeFormatter;

public class InicioAlumnoService {
    public static void llenarMiInfo(InicioAlumno vista) {
        //Académica
        vista.labelNombreCompleto.setText(vista.alumno.getNombresAlumno() + ", " + vista.alumno.getApellidosAlumno());
        vista.labelCodigo.setText(vista.alumno.getCodigo());
        vista.labelCurso.setText(vista.alumno.getCurso());
        vista.labelEP.setText(vista.alumno.getCarrera());
        vista.labelProfesorAsignado.setText(vista.alumno.getDocenteACargo());
        //Personal
        vista.labelNombres.setText(vista.alumno.getNombresAlumno());
        vista.labelApellidos.setText(vista.alumno.getApellidosAlumno());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vista.labelFechaNac.setText(vista.alumno.getFechaNacimiento().format(dateFormatter));
        vista.labelDni.setText(vista.alumno.getDni());
        vista.labelGenero.setText(vista.alumno.getGenero());
        vista.labelCorreo.setText(vista.alumno.getCorreoElectronico());
    }
}
