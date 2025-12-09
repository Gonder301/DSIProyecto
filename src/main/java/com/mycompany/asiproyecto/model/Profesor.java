package com.mycompany.asiproyecto.model;

public class Profesor {
    private int idProfesor;
    private String nombresProfesor;
    private String apellidosProfesor;
    private String dni;
    private String codigoCurso;
    private String nombreCurso;
    private String carrera;
    private String correoInstitucional;
    
    public Profesor() {
    }
    
    public Profesor(int idProfesor, String nombresProfesor, String apellidosProfesor, 
                    String dni, String codigoCurso, String nombreCurso, 
                    String carrera, String correoInstitucional) {
        this.idProfesor = idProfesor;
        this.nombresProfesor = nombresProfesor;
        this.apellidosProfesor = apellidosProfesor;
        this.dni = dni;
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.carrera = carrera;
        this.correoInstitucional = correoInstitucional;
    }
    
    // --- Getters y Setters ---

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombresProfesor() {
        return nombresProfesor;
    }

    public void setNombresProfesor(String nombresProfesor) {
        this.nombresProfesor = nombresProfesor;
    }

    public String getApellidosProfesor() {
        return apellidosProfesor;
    }

    public void setApellidosProfesor(String apellidosProfesor) {
        this.apellidosProfesor = apellidosProfesor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }
    
    // Se obtiene el nombre completo cuando se hace un cast a String a la clase.
    @Override
    public String toString() {
        return (this.apellidosProfesor + ", " + this.nombresProfesor); 
    }
}
