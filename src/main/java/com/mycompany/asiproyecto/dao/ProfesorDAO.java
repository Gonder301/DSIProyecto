package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Profesor;
import com.mycompany.asiproyecto.service.PasswordService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {

    public Profesor obtenerProfesor(String correoInstitucional, char[] contrasena) {
        Profesor prof = null;

        String sql = "SELECT * FROM Profesor WHERE correoInstitucional = ? AND contrasena = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String hashContrasena = PasswordService.hash(contrasena);
            pstmt.setString(1, correoInstitucional);
            pstmt.setString(2, hashContrasena);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prof = new Profesor();
                    prof.setIdProfesor(rs.getInt("idProfesor"));
                    prof.setNombresProfesor(rs.getString("nombresProfesor"));
                    prof.setApellidosProfesor(rs.getString("apellidosProfesor"));
                    prof.setDni(rs.getString("dni"));
                    prof.setCodigoCurso(rs.getString("codigoCurso"));
                    prof.setNombreCurso(rs.getString("nombreCurso"));
                    prof.setCarrera(rs.getString("carrera"));
                    prof.setCorreoInstitucional(rs.getString("correoInstitucional"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener Profesor: " + e.getMessage());
            e.printStackTrace();
        }

        return prof;
    }
    
    public boolean registrarProfesor(Profesor p, char[] contrasena) {
        boolean registrado = false;

        String sql = "INSERT INTO Profesor (nombresProfesor, apellidosProfesor, dni, " +
                     "codigoCurso, nombreCurso, carrera, correoInstitucional, contrasena) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String hashContrasena = PasswordService.hash(contrasena);
            
            pstmt.setString(1, p.getNombresProfesor());
            pstmt.setString(2, p.getApellidosProfesor());
            pstmt.setString(3, p.getDni());
            pstmt.setString(4, p.getCodigoCurso());
            pstmt.setString(5, p.getNombreCurso());
            pstmt.setString(6, p.getCarrera());
            pstmt.setString(7, p.getCorreoInstitucional());
            pstmt.setString(8, hashContrasena);

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
}