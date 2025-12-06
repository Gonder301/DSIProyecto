package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Oferta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfertaDAO {

    public List<Oferta> obtenerTodasLasOfertas() {

        List<Oferta> lista = new ArrayList<>();
        String sql = "SELECT idoferta, nombreempresa, descripcionperfil, puestopractica, " +
                "requisitos, fechainicio, fechafin, modalidad, habilidadescompetencias, " +
                "area, distrito, beneficios, consultas, empleadoid " +
                "FROM Oferta";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Oferta o = new Oferta();

                o.setIdOferta(rs.getInt("idoferta"));
                o.setNombreEmpresa(rs.getString("nombreempresa"));
                o.setDescriptionPerfil(rs.getString("descripcionperfil"));
                o.setPuestoPractica(rs.getString("puestopractica"));
                o.setRequisitos(rs.getString("requisitos"));
                o.setFechaInicio(rs.getObject("fechainicio", LocalDate.class));
                o.setFechaFin(rs.getObject("fechafin", LocalDate.class));
                o.setModalidad(rs.getString("modalidad"));
                o.setHabilidadesCompetencias(rs.getString("habilidadescompetencias"));
                o.setArea(rs.getString("area"));
                o.setDistrito(rs.getString("distrito"));
                o.setBeneficios(rs.getString("beneficios"));
                o.setConsultas(rs.getString("consultas"));
                o.setEmpleadoID(rs.getInt("empleadoid"));
                lista.add(o);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ofertas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean registrarOferta(Oferta o) {
        boolean registrado = false;

        String sql = "INSERT INTO Oferta (nombreempresa, descripcionperfil"
                + ", puestopractica, requisitos, fechainicio, fechafin, modalidad"
                + ", habilidadescompetencias, area, distrito, beneficios"
                + ", consultas, empleadoid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, o.getNombreEmpresa());
            pstmt.setString(2, o.getDescriptionPerfil());
            pstmt.setString(3, o.getPuestoPractica());
            pstmt.setString(4, o.getRequisitos());
            pstmt.setDate(5, java.sql.Date.valueOf(o.getFechaInicio()));
            pstmt.setDate(6, java.sql.Date.valueOf(o.getFechaFin()));
            pstmt.setString(7, o.getModalidad());
            pstmt.setString(8, o.getHabilidadesCompetencias());
            pstmt.setString(9, o.getArea());
            pstmt.setString(10, o.getDistrito());
            pstmt.setString(11, o.getBeneficios());
            pstmt.setString(12, o.getConsultas());
            pstmt.setInt(13, o.getEmpleadoID());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                registrado = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar profesor: " + e.getMessage());
            e.printStackTrace();
        }
        return registrado;
    }

    public List<Oferta> obtenerOfertasPorEmpleado(int empleadoId) {
        List<Oferta> lista = new ArrayList<>();
        String sql = "SELECT idoferta, nombreempresa, descripcionperfil, puestopractica, " +
                "requisitos, fechainicio, fechafin, modalidad, habilidadescompetencias, " +
                "area, distrito, beneficios, consultas, empleadoid " +
                "FROM Oferta WHERE empleadoid = ?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empleadoId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Oferta o = new Oferta();

                    o.setIdOferta(rs.getInt("idoferta"));
                    o.setNombreEmpresa(rs.getString("nombreempresa"));
                    o.setDescriptionPerfil(rs.getString("descripcionperfil"));
                    o.setPuestoPractica(rs.getString("puestopractica"));
                    o.setRequisitos(rs.getString("requisitos"));
                    o.setFechaInicio(rs.getObject("fechainicio", LocalDate.class));
                    o.setFechaFin(rs.getObject("fechafin", LocalDate.class));
                    o.setModalidad(rs.getString("modalidad"));
                    o.setHabilidadesCompetencias(rs.getString("habilidadescompetencias"));
                    o.setArea(rs.getString("area"));
                    o.setDistrito(rs.getString("distrito"));
                    o.setBeneficios(rs.getString("beneficios"));
                    o.setConsultas(rs.getString("consultas"));
                    o.setEmpleadoID(rs.getInt("empleadoid"));
                    lista.add(o);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ofertas por empleado: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminarOferta(int idOferta) {
        String sql = "DELETE FROM Oferta WHERE idoferta = ?";
        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idOferta);
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar oferta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}