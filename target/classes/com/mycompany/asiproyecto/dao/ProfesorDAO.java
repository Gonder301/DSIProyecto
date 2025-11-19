package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {

    public Profesor obtenerProfesor(String correoInstitucional, String contrasena) {
        Profesor prof = null;

        // Se asume que la tabla se llama Profesor y tiene columna contrasena
        String sql = "SELECT * FROM Profesor WHERE correoInstitucional = ? AND contrasena = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoInstitucional);
            pstmt.setString(2, contrasena);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prof = new Profesor();
                    // Mapeo exacto según los atributos de tu clase Profesor.java
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
    
    public boolean registrarProfesor(Profesor p, String contrasena) {
        boolean registrado = false;

        // Se asume que la tabla tiene columna 'contrasena' al igual que Alumno
        String sql = "INSERT INTO Profesor (nombresProfesor, apellidosProfesor, dni, " +
                     "codigoCurso, nombreCurso, carrera, correoInstitucional, contrasena) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 1. Mapeo de datos desde el objeto Profesor
            pstmt.setString(1, p.getNombresProfesor());
            pstmt.setString(2, p.getApellidosProfesor());
            pstmt.setString(3, p.getDni());
            pstmt.setString(4, p.getCodigoCurso());
            pstmt.setString(5, p.getNombreCurso());
            pstmt.setString(6, p.getCarrera());
            pstmt.setString(7, p.getCorreoInstitucional());

            // 2. Inserción de la contraseña (parámetro extra)
            pstmt.setString(8, contrasena);

            // 3. Ejecutar la inserción
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