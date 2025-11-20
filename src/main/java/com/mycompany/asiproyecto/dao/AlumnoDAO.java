package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Alumno;
import com.mycompany.asiproyecto.service.PasswordService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
    public Alumno obtenerAlumno(String correoElectronico, char[] contrasena) {
        Alumno a = null;
        
        String sql = "SELECT * FROM Alumno WHERE correoElectronico = ? AND contrasena = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String hashContrasena = PasswordService.hash(contrasena);
            pstmt.setString(1, correoElectronico);
            pstmt.setString(2, hashContrasena);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    a = new Alumno(); 
                    a.setIdAlumno(rs.getInt("idAlumno"));
                    a.setNombresAlumno(rs.getString("nombresAlumno"));
                    a.setApellidosAlumno(rs.getString("apellidosAlumno"));
                    a.setDni(rs.getString("dni"));
                    a.setGenero(rs.getString("genero"));
                    
                    java.sql.Date fechaSql = rs.getDate("fechaNacimiento");
                    if (fechaSql != null) {
                        a.setFechaNacimiento(fechaSql.toLocalDate());
                    }
                    
                    a.setCodigo(rs.getString("codigo"));
                    a.setCarrera(rs.getString("carrera"));
                    a.setCurso(rs.getString("curso"));
                    a.setDocenteACargo(rs.getString("docenteACargo"));
                    a.setCorreoElectronico(rs.getString("correoElectronico"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al iniciar sesión / obtener alumno: " + e.getMessage());
            e.printStackTrace();
        }
        
        return a;
    }
    
    public boolean registrarAlumno(Alumno a, char[] contrasena) {
        boolean registrado = false;
    
        String sql = "INSERT INTO Alumno (nombresAlumno, apellidosAlumno, dni, genero, " +
                    "fechaNacimiento, codigo, carrera, curso, docenteACargo, " +
                    "correoElectronico, contrasena) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, a.getNombresAlumno());
            pstmt.setString(2, a.getApellidosAlumno());
            pstmt.setString(3, a.getDni());
            pstmt.setString(4, a.getGenero());

            if (a.getFechaNacimiento() != null) {
                pstmt.setDate(5, java.sql.Date.valueOf(a.getFechaNacimiento()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            String hashContrasena = PasswordService.hash(contrasena);
            
            pstmt.setString(6, a.getCodigo());
            pstmt.setString(7, a.getCarrera());
            pstmt.setString(8, a.getCurso());
            pstmt.setString(9, a.getDocenteACargo());
            pstmt.setString(10, a.getCorreoElectronico());
            pstmt.setString(11, hashContrasena);

            int filasAfectadas = pstmt.executeUpdate();
        
            if (filasAfectadas > 0) {
                registrado = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al registrar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    return registrado;
    }
}
