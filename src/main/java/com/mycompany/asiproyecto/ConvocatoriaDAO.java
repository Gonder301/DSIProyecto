package com.mycompany.asiproyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ConvocatoriaDAO {

    public List<Convocatoria> obtenerTodasLasConvocatorias() {
        
        List<Convocatoria> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, descripcion, imagen_url, fecha_publicacion, admin_id " +
                     "FROM convocatoria ORDER BY fecha_publicacion DESC";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Convocatoria c = new Convocatoria();
                
                c.setId(rs.getInt("id"));
                c.setTitulo(rs.getString("titulo"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setImagenUrl(rs.getString("imagen_url"));
                
                Timestamp ts = rs.getTimestamp("fecha_publicacion");
                if (ts != null) {
                    c.setFechaPublicacion(ts.toLocalDateTime());
                }
                
                c.setAdminId(rs.getInt("admin_id"));

                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener convocatorias: " + e.getMessage());
            e.printStackTrace();
        }
        
        return lista;
    }

    public boolean crearConvocatoria(Convocatoria c) {
        
        String sql = "INSERT INTO convocatoria (titulo, descripcion, imagen_url, admin_id) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, c.getTitulo());
            pstmt.setString(2, c.getDescripcion());
            pstmt.setString(3, c.getImagenUrl());
            pstmt.setInt(4, c.getAdminId());

            int filasAfectadas = pstmt.executeUpdate();
            
            return filasAfectadas > 0; 
            
        } catch (SQLException e) {
            System.err.println("Error al crear convocatoria: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}