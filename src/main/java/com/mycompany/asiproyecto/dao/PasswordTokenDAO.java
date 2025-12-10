package com.mycompany.asiproyecto.dao;

import com.mycompany.asiproyecto.db.ConnectionPool;
import com.mycompany.asiproyecto.service.GmailService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class PasswordTokenDAO {
    
    public int validarToken(String token, String tipoUsuario) {
        int idUsuarioEncontrado = -1;
        
        String sql = "SELECT id_usuario FROM password_token WHERE token = ? AND tipo_usuario = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, token);
            pstmt.setString(2, tipoUsuario);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Si encontramos un registro, obtenemos el ID del usuario dueño del token
                    idUsuarioEncontrado = rs.getInt("id_usuario");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al validar token: " + e.getMessage());
            e.printStackTrace();
        }

        return idUsuarioEncontrado;
    }
    
    public void registrarToken(String correo) {
        AlumnoDAO aDAO = new AlumnoDAO();
        boolean esAlumno = aDAO.esCorreoRegistrado(correo);
        ProfesorDAO pDAO = new ProfesorDAO();
        boolean esProfesor = pDAO.esCorreoRegistrado(correo);
        EmpleadoEmpresaDAO eeDAO = new EmpleadoEmpresaDAO();
        boolean esEmpleado = eeDAO.esCorreoRegistrado(correo);
        
        boolean estaRegistrado = esAlumno || esProfesor || esEmpleado;
        if (estaRegistrado) {
            int idUsuario = -1;
            String tipoUsuario = "";
            
            if (esAlumno) {
                tipoUsuario = "ALUMNO";
                idUsuario = obtenerIdPorCorreo("Alumno", "idAlumno", "correoElectronico", correo);
            } else if (esProfesor) {
                tipoUsuario = "PROFESOR";
                idUsuario = obtenerIdPorCorreo("Profesor", "idProfesor", "correoInstitucional", correo);
            } else if (esEmpleado) {
                tipoUsuario = "EMPRESA";
                idUsuario = obtenerIdPorCorreo("EmpleadoEmpresa", "idEmpleado", "correoCorporativo", correo);
            }
            
            Random rnd = new Random();
            int token_int = rnd.nextInt(1000000); 
            String token = String.format("%06d", token_int);
            
            if (idUsuario != -1) {
                String sql = "INSERT INTO password_token (tipo_usuario, id_usuario, token) VALUES (?, ?, ?)";

                try (Connection conn = ConnectionPool.getConnection();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setString(1, tipoUsuario);
                    pstmt.setInt(2, idUsuario);
                    pstmt.setString(3, token);

                    int filas = pstmt.executeUpdate();

                    if (filas > 0) {
                        //Retorna true si se envía y false si no.
                        //No se hace nada con el retorno en este caso.
                        GmailService.enviarCodigoDeVerificacion(correo, token);
                    }

                } catch (SQLException e) {
                    System.err.println("Error al registrar el token en BD: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
    
    //Se puede usar como DAO de Alumno, Profesor y EmpleadoEmpresa
    private int obtenerIdPorCorreo(String tabla, String columnaId, String columnaCorreo, String correo) {
        int id = -1;
        String sql = "SELECT " + columnaId + " FROM " + tabla + " WHERE " + columnaCorreo + " = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, correo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID de " + tabla + ": " + e.getMessage());
        }
        return id;
    }
}
