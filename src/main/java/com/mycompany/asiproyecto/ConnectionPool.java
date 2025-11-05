
package com.mycompany.asiproyecto;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author abrah
 */
public class ConnectionPool {
private static HikariConfig config = new HikariConfig();
    
    // 2. El DataSource (fuente de datos) que manejará el pool
    private static HikariDataSource ds;

    // 3. Bloque estático: Se ejecuta UNA SOLA VEZ cuando la clase es cargada
    static {
        // Usamos los datos que me diste
        String host = "localhost";
        String port = "3306";
        String database = "ASIDB";
        String user = "root";
        String password = "ANGU01";
        String url = "jdbc:mariadb://" + host + ":" + port + "/" + database;

        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);

        // --- Configuración del Pool ---
        config.setMaximumPoolSize(10); // Máximo de conexiones en el pool
        config.setMinimumIdle(5);      // Mínimo de conexiones inactivas
        config.setIdleTimeout(300000); // Tiempo que una conexión puede estar inactiva (5 min)
        config.setConnectionTimeout(30000); // Tiempo de espera para obtener una conexión (30 seg)

        // Optimizaciones recomendadas para MariaDB/MySQL
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // 4. Se crea el DataSource con la configuración
        ds = new HikariDataSource(config);
        System.out.println("¡Pool de conexiones HikariCP inicializado!");
    }

    private ConnectionPool() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    public static void closePool() {
        if (ds != null) {
            ds.close();
            System.out.println("Pool de conexiones HikariCP cerrado.");
        }
    }
}
