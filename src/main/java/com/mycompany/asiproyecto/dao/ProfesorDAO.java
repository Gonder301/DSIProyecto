package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.Profesor;
import com.mycompany.asiproyecto.service.PasswordService;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {

    public boolean cambiarContrasena(int idProfesor, char[] nuevaContrasena) {
        boolean actualizado = false;
        
        String hashContrasena = PasswordService.hash(nuevaContrasena);
        
        String sql = "UPDATE Profesor SET contrasena = ? WHERE idprofesor = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, hashContrasena);
            pstmt.setInt(2, idProfesor);

            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                actualizado = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al cambiar contraseña de Profesor: " + e.getMessage());
            e.printStackTrace();
        }
        
        return actualizado;
    }
    
    public boolean esCorreoRegistrado(String correoInstitucional) {
        boolean existe = false;
        
        // Seleccionamos solo el ID para que la consulta sea rápida
        String sql = "SELECT idprofesor FROM Profesor WHERE correoinstitucional = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoInstitucional);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    existe = true;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar existencia del correo del profesor: " + e.getMessage());
            e.printStackTrace();
        }

        return existe;
    }
    
    public List<Profesor> obtenerTodosLosProfesores() {

        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT idprofesor, nombresprofesor, apellidosprofesor, "
                + "dni, codigocurso, nombrecurso, carrera, correoinstitucional "
                + "FROM Profesor";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Profesor p = new Profesor();

                p.setIdProfesor(rs.getInt("idprofesor"));
                p.setNombresProfesor(rs.getString("nombresprofesor"));
                p.setApellidosProfesor(rs.getString("apellidosprofesor"));
                p.setDni(rs.getString("dni"));
                p.setCodigoCurso(rs.getString("codigocurso"));
                p.setNombreCurso(rs.getString("nombrecurso"));
                p.setCarrera(rs.getString("carrera"));
                p.setCorreoInstitucional(rs.getString("correoinstitucional"));
                
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener profesores: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
    public String obetenerNombreCompleto(int idProfesor) {
        String nombreCompleto = null;
        String sql = "SELECT nombresprofesor, apellidosprofesor "
                + "FROM Profesor WHERE idprofesor = ?";
        
        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idProfesor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                nombreCompleto = rs.getString("apellidosprofesor") + ", " + rs.getString("nombresprofesor");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener nombre completo de profesor por id: " + e.getMessage());
            e.printStackTrace();
        }
        return nombreCompleto;
    }
    
    public Profesor obtenerProfesor(String correoInstitucional, char[] contrasena) {
        Profesor prof = null;

        String sql = "SELECT * FROM Profesor WHERE correoinstitucional = ?";

        try (Connection conn = ConnectionPool.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoInstitucional);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashGuardado = rs.getString("contrasena");
                    
                    if(PasswordService.verify(hashGuardado, contrasena)) {
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
                    else {
                        //MENSAJE: CONTRASEÑA INCORRECTA
                    }
                }
                else {
                    //MENSAJE: CORREO NO REGISTRADO
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
                     "codigoCurso, nombreCurso, carrera, correoinstitucional, contrasena) " +
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