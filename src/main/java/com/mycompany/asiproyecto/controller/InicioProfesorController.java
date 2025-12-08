package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.view.InicioProfesor;
import java.awt.CardLayout;

public class InicioProfesorController {
public void cambiarCard(String cardName, InicioProfesor vista) {
        CardLayout cl = (CardLayout) vista.cardHolderPanel.getLayout();
        cl.show(vista.cardHolderPanel, cardName);
        switch(cardName) {
            case "miInformacionCard":
                vista.botonMiInformacion.setBackground(Colores.BUTTON_YELLOW);
                vista.botonRevisarContrato.setBackground(Colores.BUTTON_BLUE);
                vista.botonEvaluarInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "revisarContratoCard":
                vista.botonMiInformacion.setBackground(Colores.BUTTON_BLUE);
                vista.botonRevisarContrato.setBackground(Colores.BUTTON_YELLOW);
                vista.botonEvaluarInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "evaluarInformesCard":
                vista.botonMiInformacion.setBackground(Colores.BUTTON_BLUE);
                vista.botonRevisarContrato.setBackground(Colores.BUTTON_BLUE);
                vista.botonEvaluarInformes.setBackground(Colores.BUTTON_YELLOW);
                break;
        }
    }
}
