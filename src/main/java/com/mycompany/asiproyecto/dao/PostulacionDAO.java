package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Oferta;
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
                "WHERE o.empleadoid = ? AND p.estado = 'Pendiente'";

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

    public boolean registrarPostulacion(Postulacion p) {
        boolean registrado = false;
        String sql = "INSERT INTO Postulacion (idalumno, idoferta, fechapostulacion, requisitolink, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, p.getIdAlumno());
            pstmt.setInt(2, p.getIdOferta());
            pstmt.setDate(3, java.sql.Date.valueOf(p.getFechaPostulacion()));
            pstmt.setString(4, p.getRequisitoLink());
            pstmt.setString(5, p.getEstado());
            int rows = pstmt.executeUpdate();
            if (rows > 0)
                registrado = true;
        } catch (SQLException e) {
            System.err.println("Error al registrar postulacion: " + e.getMessage());
            e.printStackTrace();
        }
        return registrado;
    }

    public boolean existePostulacion(int idAlumno, int idOferta) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM Postulacion WHERE idalumno = ? AND idoferta = ?";
        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAlumno);
            pstmt.setInt(2, idOferta);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar postulacion: " + e.getMessage());
            e.printStackTrace();
        }
        return existe;
    }

    public List<Postulacion> obtenerPostulacionesPorAlumno(int idAlumno) {
        List<Postulacion> lista = new ArrayList<>();
        // Join with Oferta to get details for the panel
        String sql = "SELECT p.idpostulacion, p.idalumno, p.idoferta, o.empleadoid, " +
                "p.fechapostulacion, p.requisitolink, p.estado, " +
                "o.nombreempresa, o.puestopractica, o.area, o.distrito, o.modalidad, " +
                "o.descripcionperfil, o.requisitos, o.habilidadescompetencias, " +
                "o.fechainicio, o.fechafin, o.beneficios, o.consultas " +
                "FROM Postulacion p " +
                "INNER JOIN Oferta o ON p.idoferta = o.idoferta " +
                "WHERE p.idalumno = ?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAlumno);

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

                    // Populate Oferta object
                    Oferta o = new Oferta();
                    o.setIdOferta(rs.getInt("idoferta"));
                    o.setNombreEmpresa(rs.getString("nombreempresa"));
                    o.setPuestoPractica(rs.getString("puestopractica"));
                    o.setArea(rs.getString("area"));
                    o.setDistrito(rs.getString("distrito"));
                    o.setModalidad(rs.getString("modalidad"));
                    o.setDescripcionPerfil(rs.getString("descripcionperfil"));
                    o.setRequisitos(rs.getString("requisitos"));
                    o.setHabilidadesCompetencias(rs.getString("habilidadescompetencias"));
                    o.setFechaInicio(rs.getObject("fechainicio", LocalDate.class));
                    o.setFechaFin(rs.getObject("fechafin", LocalDate.class));
                    o.setBeneficios(rs.getString("beneficios"));
                    o.setConsultas(rs.getString("consultas"));
                    o.setEmpleadoID(rs.getInt("empleadoid"));

                    p.setOferta(o);

                    // Also set flattened fields (optional, but keep for compatibility if needed
                    // elsewhere)
                    p.setPuestoPractica(rs.getString("puestopractica"));

                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener postulaciones por alumno: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminarPostulacion(int idAlumno, int idOferta) {
        boolean eliminado = false;
        String sql = "DELETE FROM Postulacion WHERE idalumno = ? AND idoferta = ?";
        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAlumno);
            pstmt.setInt(2, idOferta);
            if (pstmt.executeUpdate() > 0) {
                eliminado = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar postulacion: " + e.getMessage());
            e.printStackTrace();
        }
        return eliminado;
    }

    public boolean actualizarEstado(int idPostulacion, String nuevoEstado) {
        boolean actualizado = false;
        String sql = "UPDATE Postulacion SET estado = ? WHERE idpostulacion = ?";
        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevoEstado);
            pstmt.setInt(2, idPostulacion);
            if (pstmt.executeUpdate() > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado postulación: " + e.getMessage());
            e.printStackTrace();
        }
        return actualizado;
    }
}
