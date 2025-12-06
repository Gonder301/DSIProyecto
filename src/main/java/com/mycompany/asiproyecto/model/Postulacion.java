package com.mycompany.asiproyecto.model;

import java.time.LocalDate;

public class Postulacion {
    private int idPostulacion;
    private int idAlumno;
    private int idOferta;
    private int idEmpleado;
    private LocalDate fechaPostulacion;
    private String requisitoLink;
    private String estado;

    // Campos para visualización
    private String nombreAlumno;
    private String puestoPractica;

    // Constructor vacío
    public Postulacion() {
    }

    // --- Getters y Setters ---

    public int getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(int idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDate fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public String getRequisitoLink() {
        return requisitoLink;
    }

    public void setRequisitoLink(String requisitoLink) {
        this.requisitoLink = requisitoLink;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getPuestoPractica() {
        return puestoPractica;
    }

    public void setPuestoPractica(String puestoPractica) {
        this.puestoPractica = puestoPractica;
    }
}