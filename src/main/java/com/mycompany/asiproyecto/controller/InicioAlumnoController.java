package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.model.Alumno;
import com.mycompany.asiproyecto.view.InicioAlumno;
import com.mycompany.asiproyecto.view.Index;
import java.awt.CardLayout;

public class InicioAlumnoController {
    public void cerrarSesion(InicioAlumno vista) {
        vista.dispose();
        Index index = new Index();
        index.setLocationRelativeTo(null);
        index.setVisible(true);
    }
    
    public void cambiarCard(String cardName, InicioAlumno vista) {
        CardLayout cl = (CardLayout) vista.cardHolderPanel.getLayout();
        cl.show(vista.cardHolderPanel, cardName);
        switch(cardName) {
            case "miInformacionCard":
                vista.botonMiInfo.setBackground(Colores.BUTTON_YELLOW);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonAdjuntarContrato.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "ofertasDePracticasCard":
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonAdjuntarContrato.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misPostulacionesCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_YELLOW);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonAdjuntarContrato.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "adjuntarContratoCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonAdjuntarContrato.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misInformesCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonAdjuntarContrato.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_YELLOW);
                break;
        }
    }
}
