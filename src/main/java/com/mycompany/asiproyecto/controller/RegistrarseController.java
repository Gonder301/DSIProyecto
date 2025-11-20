package com.mycompany.asiproyecto.controller;

import java.util.Arrays;
import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.model.*;
import com.mycompany.asiproyecto.dao.*;
import com.mycompany.asiproyecto.view.RegistrarseJDialog;
import javax.swing.border.LineBorder;

public class RegistrarseController {
    private boolean contraCoincidenA(RegistrarseJDialog vista) {
        return Arrays.equals(vista.contraPasswordFieldA1.getPassword(), vista.contraPasswordFieldA2.getPassword());
    }
    
    private boolean contraCoincidenP(RegistrarseJDialog vista) {
        return Arrays.equals(vista.contraPasswordFieldP1.getPassword(), vista.contraPasswordFieldP2.getPassword());
    }
    
    private boolean contraCoincidenE(RegistrarseJDialog vista) {
        return Arrays.equals(vista.contraPasswordFieldE1.getPassword(), vista.contraPasswordFieldE2.getPassword());
    }
    
    private int camposVaciosFormAlumno(RegistrarseJDialog vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.textFieldBorderErr, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.botonDefault, 1);
        
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
        
        if (vista.docenteCargoTextFieldA.getText().isEmpty()) {
            vista.docenteCargoTextFieldA.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.docenteCargoTextFieldA.setBorder(lineBorderDef);
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
    
    public void procesarRegistroAlumno(RegistrarseJDialog vista) {
        int camposVacios = camposVaciosFormAlumno(vista);
        if (camposVacios > 0) {
            vista.msgRegistrarseA.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        if (!contraCoincidenA(vista)) {
            vista.msgRegistrarseA.setText("Las contraseñas no coinciden.");
            return;
        }
        
        char[] contrasena = vista.contraPasswordFieldA1.getPassword();
        
        Alumno a = vista.obtenerAlumnoDeForm();
        
        //Poner dentro de servicio
        AlumnoDAO adao = new AlumnoDAO();
        if (adao.registrarAlumno(a, contrasena)) {
            vista.msgRegistrarseA.setText("El alumno se registró con éxito.");
        }
    }
    
    private int camposVaciosFormProfesor(RegistrarseJDialog vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.textFieldBorderErr, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.botonDefault, 1);
        
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
    
    private int camposVaciosFormEmpresa(RegistrarseJDialog vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.textFieldBorderErr, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.botonDefault, 1);
        
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
    
    public void procesarRegistroProfesor(RegistrarseJDialog vista) {
        int camposVacios = camposVaciosFormProfesor(vista);
        if (camposVacios > 0) {
            vista.msgRegistrarseP.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        if (!contraCoincidenP(vista)) {
            vista.msgRegistrarseP.setText("Las contraseñas no coinciden.");
            return;
        }
        
        char[] contrasena = vista.contraPasswordFieldP1.getPassword();
        
        Profesor p = vista.obtenerProfesorDeForm();
        
        //Poner dentro de servicio
        ProfesorDAO pdao = new ProfesorDAO();
        if (pdao.registrarProfesor(p, contrasena)) {
            vista.msgRegistrarseP.setText("El profesor se registró con éxito.");
        }
    }
    
    public void procesarRegistroEmpresa(RegistrarseJDialog vista) {
        int camposVacios = camposVaciosFormEmpresa(vista);
        if (camposVacios > 0) {
            vista.msgRegistrarseE.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        if (!contraCoincidenE(vista)) {
            vista.msgRegistrarseE.setText("Las contraseñas no coinciden.");
            return;
        }
        
        char[] contrasena = vista.contraPasswordFieldE1.getPassword();
        
        EmpleadoEmpresa e = vista.obtenerEmpleadoEmpresaDeForm();
        
        //Poner dentro de servicio
        EmpleadoEmpresaDAO edao = new EmpleadoEmpresaDAO();
        if (edao.registrarEmpleadoEmpresa(e, contrasena)) {
            vista.msgRegistrarseE.setText("La empresa se registró con éxito.");
        }
    }
    
}
