package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.service.InicioEmpleadoService;
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
                vista.botonPublicarNuevaOferta.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMisOfertas.setBackground(Colores.BUTTON_BLUE);
                vista.botonHistorial.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misOfertasCard":
                vista.botonPublicarNuevaOferta.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisOfertas.setBackground(Colores.BUTTON_YELLOW);
                vista.botonHistorial.setBackground(Colores.BUTTON_BLUE);
                break;
            case "historialCard":
                vista.botonPublicarNuevaOferta.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisOfertas.setBackground(Colores.BUTTON_BLUE);
                vista.botonHistorial.setBackground(Colores.BUTTON_YELLOW);
                break;
        }
    }
    
    public void procesarPublicarOferta(InicioEmpleado vista) {
        int camposVacios = InicioEmpleadoService.camposVaciosFormOferta(vista);
        if (camposVacios > 0) {
            vista.msgError.setText("Lllenar el(los) "+ camposVacios +" campo(s) vacío(s).");
            return;
        }
        
        Oferta oferta = InicioEmpleadoService.obtenerOfertaDeForm(vista);
        if (InicioEmpleadoService.registroOfertaExitoso(oferta)) {
            vista.msgError.setText("La oferta se publicó con éxito.");
        }
    }
}
