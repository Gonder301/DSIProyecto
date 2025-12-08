package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.view.InicioProfesor;

public class InicioProfesorService {
    public static void llenarMiInfo(InicioProfesor vista) {
        vista.labelCarrera.setText(vista.profesor.getCarrera());
        vista.labelCodigoCurso.setText(vista.profesor.getCodigoCurso());
        vista.labelCorreo.setText(vista.profesor.getCorreoInstitucional());
        vista.labelCurso.setText(vista.profesor.getNombreCurso());
        vista.labelDni.setText(vista.profesor.getDni());
        vista.labelDocente.setText(vista.profesor.getNombresProfesor() + ", " + vista.profesor.getApellidosProfesor());
    }
}
