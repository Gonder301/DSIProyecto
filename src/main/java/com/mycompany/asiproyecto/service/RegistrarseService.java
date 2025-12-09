package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.dao.ProfesorDAO;
import com.mycompany.asiproyecto.model.Alumno;
import com.mycompany.asiproyecto.model.EmpleadoEmpresa;
import com.mycompany.asiproyecto.model.Profesor;
import com.mycompany.asiproyecto.view.RegistrarseJDialog;
import javax.swing.border.LineBorder;

public class RegistrarseService {
    public static void obtenerTodosLosProfesores (RegistrarseJDialog vista) {
        ProfesorDAO profesorDAO = new ProfesorDAO();
        vista.todosLosProfesores = profesorDAO.obtenerTodosLosProfesores();
    }
    
    public static void agregarProfesoresAComboBox (RegistrarseJDialog vista) {
        vista.docenteComboBoxA.removeAllItems();
        for (Profesor p : vista.todosLosProfesores) {
            vista.docenteComboBoxA.addItem(p);
        }
    }
    
    public static Alumno obtenerAlumnoDeForm(RegistrarseJDialog vista) {
        Alumno a = new Alumno();
        a.setNombresAlumno(vista.nombresTextFieldA.getText());
        a.setApellidosAlumno(vista.apellidoTextFieldA.getText());
        a.setDni(vista.dniTextFieldA.getText());
        a.setGenero((String)vista.generoComboBoxA.getSelectedItem());
        a.setFechaNacimiento(vista.datePickerA.getDate());
        a.setCodigo(vista.codigoTextFieldA.getText());
        a.setCarrera((String)vista.carreraComboBoxA.getSelectedItem());
        a.setCurso(vista.cursoTextFieldA.getText());
        a.setIdProfesorACargo(((Profesor) vista.docenteComboBoxA.getSelectedItem()).getIdProfesor());
        a.setCorreoElectronico(vista.correoTextFieldA.getText());
        return a;
    }
    
    public static Profesor obtenerProfesorDeForm(RegistrarseJDialog vista) {
        Profesor p = new Profesor();
        p.setNombresProfesor(vista.nombresTextFieldP.getText());
        p.setApellidosProfesor(vista.apellidoTextFieldP.getText());
        p.setDni(vista.dniTextFieldP.getText());
        p.setCorreoInstitucional(vista.correoTextFieldP.getText());
        p.setCarrera((String)vista.carreraComboBoxP.getSelectedItem());
        p.setCodigoCurso(vista.codigoCursoTextFieldP.getText());
        p.setNombreCurso(vista.cursoTextFieldP.getText());
        return p;
    }
    
    public static EmpleadoEmpresa obtenerEmpleadoEmpresaDeForm(RegistrarseJDialog vista) {
        EmpleadoEmpresa e = new EmpleadoEmpresa();
        e.setNombreCompleto(vista.nombreContactoTextFieldE.getText());
        e.setNombreEmpresa(vista.nombreEmpresaTextFieldE.getText());
        e.setCorreoCorporativo(vista.correoCorporativoTextFieldE.getText());
        e.setTelefono(vista.telefonoTextFieldE.getText());
        e.setRuc(vista.rucTextFieldE.getText());
        return e;
    }

    public static int camposVaciosFormAlumno(RegistrarseJDialog vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.TEXTFIELD_BORDER_DEF, 1);
        
        if (vista.nombresTextFieldA.getText().isEmpty()) {
            vista.nombresTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.nombresTextFieldA.setBorder(lineBorderDef);
        }
        
        if (vista.apellidoTextFieldA.getText().isEmpty()) {
            vista.apellidoTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.apellidoTextFieldA.setBorder(lineBorderDef);
        }
        
        if (vista.dniTextFieldA.getText().isEmpty()) {
            vista.dniTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.dniTextFieldA.setBorder(lineBorderDef);
        }
        
        if (vista.cursoTextFieldA.getText().isEmpty()) {
            vista.cursoTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.cursoTextFieldA.setBorder(lineBorderDef);
        }        
        
        if (vista.codigoTextFieldA.getText().isEmpty()) {
            vista.codigoTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.codigoTextFieldA.setBorder(lineBorderDef);
        }
        
        if (vista.correoTextFieldA.getText().isEmpty()) {
            vista.correoTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.correoTextFieldA.setBorder(lineBorderDef);
        }
                
        if (vista.contraPasswordFieldA1.getPassword().length == 0) {
            vista.contraPasswordFieldA1.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldA1.setBorder(lineBorderDef);
        }
        
        if (vista.contraPasswordFieldA2.getPassword().length == 0) {
            vista.contraPasswordFieldA2.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldA2.setBorder(lineBorderDef);
        }
        
        return camposVacios;
    }
    
    public static int camposVaciosFormProfesor(RegistrarseJDialog vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.TEXTFIELD_BORDER_DEF, 1);
        
        if (vista.nombresTextFieldP.getText().isEmpty()) {
            vista.nombresTextFieldP.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.nombresTextFieldP.setBorder(lineBorderDef);
        }
        
        if (vista.apellidoTextFieldP.getText().isEmpty()) {
            vista.apellidoTextFieldP.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.apellidoTextFieldP.setBorder(lineBorderDef);
        }
        
        if (vista.dniTextFieldP.getText().isEmpty()) {
            vista.dniTextFieldP.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.dniTextFieldP.setBorder(lineBorderDef);
        }
        
        if (vista.correoTextFieldP.getText().isEmpty()) {
            vista.correoTextFieldP.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.correoTextFieldP.setBorder(lineBorderDef);
        }
        
        if (vista.codigoCursoTextFieldP.getText().isEmpty()) {
            vista.codigoCursoTextFieldP.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.codigoCursoTextFieldP.setBorder(lineBorderDef);
        }
        
         if (vista.cursoTextFieldP.getText().isEmpty()) {
            vista.cursoTextFieldP.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.cursoTextFieldP.setBorder(lineBorderDef);
        }
                
        if (vista.contraPasswordFieldP1.getPassword().length == 0) {
            vista.contraPasswordFieldP1.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldP1.setBorder(lineBorderDef);
        }
        
        if (vista.contraPasswordFieldP2.getPassword().length == 0) {
            vista.contraPasswordFieldP2.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldP2.setBorder(lineBorderDef);
        }
        
        return camposVacios;
    }
    
    public static int camposVaciosFormEmpresa(RegistrarseJDialog vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.TEXTFIELD_BORDER_DEF, 1);
        
        if (vista.nombreEmpresaTextFieldE.getText().isEmpty()) {
            vista.nombreEmpresaTextFieldE.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.nombreEmpresaTextFieldE.setBorder(lineBorderDef);
        }
        
        if (vista.rucTextFieldE.getText().isEmpty()) {
            vista.rucTextFieldE.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.rucTextFieldE.setBorder(lineBorderDef);
        }
        
        if (vista.nombreContactoTextFieldE.getText().isEmpty()) {
            vista.nombreContactoTextFieldE.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.nombreContactoTextFieldE.setBorder(lineBorderDef);
        }
        
        if (vista.correoCorporativoTextFieldE.getText().isEmpty()) {
            vista.correoCorporativoTextFieldE.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.correoCorporativoTextFieldE.setBorder(lineBorderDef);
        }
        
        if (vista.telefonoTextFieldE.getText().isEmpty()) {
            vista.telefonoTextFieldE.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.telefonoTextFieldE.setBorder(lineBorderDef);
        }
        
        if (vista.contraPasswordFieldE1.getPassword().length == 0) {
            vista.contraPasswordFieldE1.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldE1.setBorder(lineBorderDef);
        }
        
        if (vista.contraPasswordFieldE2.getPassword().length == 0) {
            vista.contraPasswordFieldE2.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.contraPasswordFieldE2.setBorder(lineBorderDef);
        }
        
        return camposVacios;
    }
}
