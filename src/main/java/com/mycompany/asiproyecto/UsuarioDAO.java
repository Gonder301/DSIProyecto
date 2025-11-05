
package com.mycompany.asiproyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public Usuario obtenerUsuario(String email) {
        Usuario u = null;
        
        String sql = "SELECT id, nombre, email, contrasena, rol " +
                     "FROM usuario WHERE email = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            // 4. Ejecuta la consulta (ya no se le pasa 'sql' aquí)
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    u = new Usuario(); 
                    
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setNombre(rs.getString("nombre"));
                    u.setContrasena(rs.getString("contrasena"));
                    u.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario por email: " + e.getMessage());
            e.printStackTrace();
        }
        
        return u;
    }
}
