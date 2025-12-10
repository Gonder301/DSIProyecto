package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.AlumnoContrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlumnoContratoDAO {
    //Se obtiene una lista que contiene información acerca del alumno y su contrato,
    //un alumno puede tener varios contratos. Se escogen solo los alumnos que han
    //sido asignados al profesor con id que se pasa por parametro.
    public List<AlumnoContrato> obtenerAlumnoContratoListaPorIdprofesor(int idProfesor) {
        List<AlumnoContrato> lista = new ArrayList<>();
        String sql = "SELECT A.idalumno, A.nombresalumno, A.apellidosalumno, "
                + "A.correoelectronico, C.idcontrato, C.estadocontrato, "
                + "C.documentocontrato, C.fechainicio, C.fechafin "
                + "FROM Alumno A "
                + "INNER JOIN "
                + "Contrato C ON A.idalumno = C.idalumno "
                + "WHERE A.id_profesor_a_cargo = ?";
        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProfesor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    AlumnoContrato ac = new AlumnoContrato();
                    
                    ac.correoElectronicoAlumno = rs.getString("correoelectronico");
                    ac.documentoContrato = rs.getString("documentocontrato");
                    ac.estadoContrato = rs.getString("estadocontrato");
                    ac.fechaFinContrato = rs.getObject("fechafin", LocalDate.class);
                    ac.fechaInicioContrato = rs.getObject("fechainicio", LocalDate.class);
                    ac.idAlumno = rs.getInt("idalumno");
                    ac.idContrato = rs.getInt("idcontrato");
                    ac.nombreCompletoAlumno = rs.getString("nombresalumno") + ", " + rs.getString("apellidosalumno");
                    lista.add(ac);
                }
            }
        } catch(SQLException e) {
            System.err.println("Error al obtener AlumnoContrato List: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
