package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.view.Index;
import com.mycompany.asiproyecto.view.InicioProfesor;
import com.mycompany.asiproyecto.model.AlumnoContrato;
import com.mycompany.asiproyecto.service.InicioProfesorService;
import java.awt.CardLayout;
import java.util.List;
import java.util.stream.Collectors;

public class InicioProfesorController {
    
    public void filtrarContratos(InicioProfesor vista) {
        String estadoSeleccionado = (String) vista.estadoContratoComboBox.getSelectedItem();
        
        List<AlumnoContrato> contratosFiltrados = vista.todosLosAlumnoContrato.stream()
                .filter(o -> estadoSeleccionado.equals("Todos")
                        || o.estadoContrato.equalsIgnoreCase(estadoSeleccionado))
                .collect(Collectors.toList());

        InicioProfesorService.actualizarPanelContratos(contratosFiltrados, vista);
    }
    
    public void cerrarSesion(InicioProfesor vista) {
        vista.dispose();
        Index index = new Index();
        index.setLocationRelativeTo(null);
        index.setVisible(true);
    }
    
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
