package com.mycompany.asiproyecto.model;

import java.time.LocalDate;

public class Contrato {
    private int idContrato;
    private int idAlumno;
    private int idOferta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estadoContrato;
    private String documentoContrato;
    
    public Contrato() {
    }
    
    public Contrato(int idContrato, int idAlumno, int idOferta, LocalDate fechaInicio,
                 LocalDate fechaFin, String estadoContrato, String documentoContrato) {
        this.documentoContrato = documentoContrato;
        this.estadoContrato = estadoContrato;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.idAlumno = idAlumno;
        this.idContrato = idContrato;
        this.idOferta = idOferta;
    }

    // --- Getters y Setters---

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    public String getDocumentoContrato() {
        return documentoContrato;
    }

    public void setDocumentoContrato(String documentoContrato) {
        this.documentoContrato = documentoContrato;
    }
}
