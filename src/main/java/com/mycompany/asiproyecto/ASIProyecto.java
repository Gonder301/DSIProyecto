
package com.mycompany.asiproyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author abrah
 */
public class ASIProyecto {

    public static void main(String[] args) {
        
        String host = "localhost"; // o la IP de tu servidor
        String port = "3306"; // Puerto por defecto de MariaDB
        String database = "ASIDB";
        String user = "root";
        String password = "ANGU01";

        // --- 2. Construye la Cadena de Conexión (Connection String) ---
        // Formato: jdbc:mariadb://<host>:<port>/<database>
        String url = "jdbc:mariadb://" + host + ":" + port + "/" + database;

        // --- 3. Conecta y ejecuta una consulta ---
        // Usamos try-with-resources para que la conexión se cierre sola
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            System.out.println("¡Conexión exitosa a MariaDB!");

            // Ejemplo: Ejecutar una consulta simple
            String sqlQuery = "SELECT * FROM USUARIOS WHERE NOMBRE=\"Abraham\";";

            try (ResultSet rs = stmt.executeQuery(sqlQuery)) {
                // Iterar sobre los resultados
                if (rs.next()) {
                    String mensaje = rs.getString("id");
                    System.out.println("Resultado de la consulta: " + mensaje);
                }
            }

        } catch (SQLException e) {
            // Manejo de errores
            System.err.println("Error al conectar o ejecutar la consulta:");
            e.printStackTrace();
        }
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
