package com.mycompany.asiproyecto.model;

import java.time.LocalDate;

public class Alumno {
    private int idAlumno;
    private String nombresAlumno;
    private String apellidosAlumno;
    private String dni;
    private String genero;
    private LocalDate fechaNacimiento;
    private String codigo;
    private String carrera;
    private String curso;
    private int id_profesor_a_cargo;
    private String correoElectronico;
    
    public Alumno() {
    }
    
    public Alumno(int idAlumno, String nombresAlumno, String apellidosAlumno, String dni, 
                  String genero, LocalDate fechaNacimiento, String codigo, String carrera, 
                  String curso, int id_profesor_a_cargo, String correoElectronico) {
        this.idAlumno = idAlumno;
        this.nombresAlumno = nombresAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.dni = dni;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.codigo = codigo;
        this.carrera = carrera;
        this.curso = curso;
        this.id_profesor_a_cargo = id_profesor_a_cargo;
        this.correoElectronico = correoElectronico;
    }
    
    // --- Getters y Setters---
    
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombresAlumno() {
        return nombresAlumno;
    }

    public void setNombresAlumno(String nombresAlumno) {
        this.nombresAlumno = nombresAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public void setApellidosAlumno(String apellidosAlumno) {
        this.apellidosAlumno = apellidosAlumno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getIdProfesorACargo() {
        return id_profesor_a_cargo;
    }

    public void setIdProfesorACargo(int id_profesor_a_cargo) {
        this.id_profesor_a_cargo = id_profesor_a_cargo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
