package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Postulacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostulacionDAO {

    public List<Postulacion> obtenerPostulacionPorEmpleado(int empleadoId) {
        List<Postulacion> lista = new ArrayList<>();
        // Se hace JOIN con Alumno y Oferta para obtener datos de visualización (nombre,
        // puesto)
        String sql = "SELECT p.idpostulacion, p.idalumno, p.idoferta, o.empleadoid, " +
                "p.fechapostulacion, p.requisitolink, p.estado, " +
                "a.nombresalumno, a.apellidosalumno, o.puestopractica " +
                "FROM Postulacion p " +
                "INNER JOIN Alumno a ON p.idalumno = a.idalumno " +
                "INNER JOIN Oferta o ON p.idoferta = o.idoferta " +
                "WHERE o.empleadoid = ?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empleadoId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Postulacion p = new Postulacion();

                    p.setIdPostulacion(rs.getInt("idpostulacion"));
                    p.setIdAlumno(rs.getInt("idalumno"));
                    p.setIdOferta(rs.getInt("idoferta"));
                    p.setIdEmpleado(rs.getInt("empleadoid"));
                    p.setFechaPostulacion(rs.getObject("fechapostulacion", LocalDate.class));
                    p.setRequisitoLink(rs.getString("requisitolink"));
                    p.setEstado(rs.getString("estado"));

                    // Set campos extras
                    String nombres = rs.getString("nombresalumno");
                    String apellidos = rs.getString("apellidosalumno");
                    p.setNombreAlumno(nombres + " " + apellidos);
                    p.setPuestoPractica(rs.getString("puestopractica"));

                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener postulaciones por empleado: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
