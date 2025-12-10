package com.mycompany.asiproyecto.model;

import java.time.LocalDate;

public class AlumnoContrato {
    //Este modelo se usa unicamente en InicioProfesor para tener
    //tanto información de Alumno como de los contratos que tenga registrado.
    //Una instancia almacena algunos datos del alumno y casi todos
    //del contrato (se excluye idOferta).
    public int idAlumno;
    public String nombreCompletoAlumno;
    public String correoElectronicoAlumno;
    public int idContrato;
    public String estadoContrato;
    public String documentoContrato;
    public LocalDate fechaInicioContrato;
    public LocalDate fechaFinContrato;
}
