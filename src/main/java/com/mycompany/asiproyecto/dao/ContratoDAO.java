package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Contrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {

    public List<Contrato> obtenerTodos() {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT idContrato, idAlumno, idOferta, fechaInicio, fechaFin, estadoContrato, documentoContrato FROM Contrato";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Contrato c = new Contrato();
                c.setIdContrato(rs.getInt("idContrato"));
                c.setIdAlumno(rs.getInt("idAlumno"));
                c.setIdOferta(rs.getInt("idOferta"));
                c.setFechaInicio(rs.getObject("fechaInicio", LocalDate.class));
                c.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
                c.setEstadoContrato(rs.getString("estadoContrato"));
                c.setDocumentoContrato(rs.getString("documentoContrato"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener contratos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public Contrato obtenerPorId(int id) {
        Contrato c = null;
        String sql = "SELECT idContrato, idAlumno, idOferta, fechaInicio, fechaFin, estadoContrato, documentoContrato FROM Contrato WHERE idContrato = ?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    c = new Contrato();
                    c.setIdContrato(rs.getInt("idContrato"));
                    c.setIdAlumno(rs.getInt("idAlumno"));
                    c.setIdOferta(rs.getInt("idOferta"));
                    c.setFechaInicio(rs.getObject("fechaInicio", LocalDate.class));
                    c.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
                    c.setEstadoContrato(rs.getString("estadoContrato"));
                    c.setDocumentoContrato(rs.getString("documentoContrato"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener contrato por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return c;
    }

    public List<Contrato> obtenerPorAlumno(int idAlumno) {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT idContrato, idAlumno, idOferta, fechaInicio, fechaFin, estadoContrato, documentoContrato FROM Contrato WHERE idAlumno = ?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAlumno);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Contrato c = new Contrato();
                    c.setIdContrato(rs.getInt("idContrato"));
                    c.setIdAlumno(rs.getInt("idAlumno"));
                    c.setIdOferta(rs.getInt("idOferta"));
                    c.setFechaInicio(rs.getObject("fechaInicio", LocalDate.class));
                    c.setFechaFin(rs.getObject("fechaFin", LocalDate.class));
                    c.setEstadoContrato(rs.getString("estadoContrato"));
                    c.setDocumentoContrato(rs.getString("documentoContrato"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener contratos por alumno: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean registrar(Contrato c) {
        String sql = "INSERT INTO Contrato (idAlumno, idOferta, fechaInicio, fechaFin, estadoContrato, documentoContrato) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, c.getIdAlumno());
            pstmt.setInt(2, c.getIdOferta());
            pstmt.setObject(3, c.getFechaInicio());
            pstmt.setObject(4, c.getFechaFin());
            pstmt.setString(5, c.getEstadoContrato());
            pstmt.setString(6, c.getDocumentoContrato());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar contrato: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Contrato c) {
        String sql = "UPDATE Contrato SET idAlumno=?, idOferta=?, fechaInicio=?, fechaFin=?, estadoContrato=?, documentoContrato=? WHERE idContrato=?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, c.getIdAlumno());
            pstmt.setInt(2, c.getIdOferta());
            pstmt.setObject(3, c.getFechaInicio());
            pstmt.setObject(4, c.getFechaFin());
            pstmt.setString(5, c.getEstadoContrato());
            pstmt.setString(6, c.getDocumentoContrato());
            pstmt.setInt(7, c.getIdContrato());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar contrato: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Contrato WHERE idContrato = ?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar contrato: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
