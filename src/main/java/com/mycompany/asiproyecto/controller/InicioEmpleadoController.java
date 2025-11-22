package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.view.Index;
import com.mycompany.asiproyecto.view.InicioEmpleado;
import java.awt.CardLayout;

public class InicioEmpleadoController {
    public void cerrarSesion(InicioEmpleado vista) {
        vista.dispose();
        Index index = new Index();
        index.setLocationRelativeTo(null);
        index.setVisible(true);
    }
    
    public void cambiarCard(String cardName, InicioEmpleado vista) {
        CardLayout cl = (CardLayout) vista.cardHolderPanel.getLayout();
        cl.show(vista.cardHolderPanel, cardName);
        switch(cardName) {
            case "publicarCard":
                vista.botonPublicar.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMisOfertas.setBackground(Colores.BUTTON_BLUE);
                vista.botonHistorial.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misOfertasCard":
                vista.botonPublicar.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisOfertas.setBackground(Colores.BUTTON_YELLOW);
                vista.botonHistorial.setBackground(Colores.BUTTON_BLUE);
                break;
            case "historialCard":
                vista.botonPublicar.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisOfertas.setBackground(Colores.BUTTON_BLUE);
                vista.botonHistorial.setBackground(Colores.BUTTON_YELLOW);
                break;
        }
    }
}
