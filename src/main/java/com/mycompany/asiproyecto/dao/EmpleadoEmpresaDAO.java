package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.model.EmpleadoEmpresa;
import com.mycompany.asiproyecto.service.PasswordService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoEmpresaDAO {

    public boolean cambiarContrasena(int idEmpleadoEmpresa, char[] nuevaContrasena) {
        boolean actualizado = false;
        
        String hashContrasena = PasswordService.hash(nuevaContrasena);
        
        String sql = "UPDATE EmpleadoEmpresa SET contrasena = ? WHERE idempleado = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, hashContrasena);
            pstmt.setInt(2, idEmpleadoEmpresa);

            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                actualizado = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al cambiar contraseña de EmpleadoEmpresa: " + e.getMessage());
            e.printStackTrace();
        }
        
        return actualizado;
    }
    
    public boolean esCorreoRegistrado(String correoCorporativo) {
        boolean existe = false;
        
        // Consultamos solo el ID para optimizar el rendimiento
        String sql = "SELECT idEmpleado FROM EmpleadoEmpresa WHERE correocorporativo = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoCorporativo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    existe = true;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar existencia del correo corporativo: " + e.getMessage());
            e.printStackTrace();
        }

        return existe;
    }
    
    public EmpleadoEmpresa obtenerEmpleadoEmpresa(String correoCorporativo, char[] contrasena) {
        EmpleadoEmpresa emp = null;
        
        String sql = "SELECT * FROM EmpleadoEmpresa WHERE correoCorporativo = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correoCorporativo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashGuardado = rs.getString("contrasena");
                    
                    if (PasswordService.verify(hashGuardado, contrasena)) {
                        emp = new EmpleadoEmpresa();
                        emp.setIdEmpleado(rs.getInt("idEmpleado"));
                        emp.setNombreCompleto(rs.getString("nombreCompleto"));
                        emp.setNombreEmpresa(rs.getString("nombreEmpresa"));
                        emp.setCorreoCorporativo(rs.getString("correoCorporativo"));
                        emp.setTelefono(rs.getString("telefono"));
                        emp.setRuc(rs.getString("ruc"));
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
            System.err.println("Error al obtener EmpleadoEmpresa: " + e.getMessage());
            e.printStackTrace();
        }
        
        return emp;
    }
    
    public boolean registrarEmpleadoEmpresa(EmpleadoEmpresa empleado, char[] contrasena) {
        boolean registrado = false;
        
        String sql = "INSERT INTO EmpleadoEmpresa (nombreCompleto, nombreEmpresa,"
                + " correoCorporativo, telefono, ruc, contrasena) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = ConnectionPool.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            String hashContrasena = PasswordService.hash(contrasena);
        
            pstmt.setString(1, empleado.getNombreCompleto());
            pstmt.setString(2, empleado.getNombreEmpresa());
            pstmt.setString(3, empleado.getCorreoCorporativo());
            pstmt.setString(4, empleado.getTelefono());
            pstmt.setString(5, empleado.getRuc());
            pstmt.setString(6, hashContrasena);
        
            int filasAfectadas = pstmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                registrado = true;
            }
        
        } catch (SQLException e) {
            System.err.println("Error al registrar EmpleadoEmpresa: " + e.getMessage());
            e.printStackTrace();
        }
        return registrado;
    }
}