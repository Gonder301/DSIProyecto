
package com.mycompany.asiproyecto;

public class ASIProyecto {

    public static void main(String[] args) {
        
        try (var conn = ConnectionPool.getConnection()) {
            System.out.println("Prueba de conexión al pool exitosa.");
        } catch (Exception e) {
            System.err.println("¡FALLO AL INICIAR EL POOL DE CONEXIONES!");
            e.printStackTrace();
            return;
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        });
        
    }
}
