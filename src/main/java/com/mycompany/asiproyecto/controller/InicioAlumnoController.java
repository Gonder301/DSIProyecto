package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.view.InicioAlumno;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.view.Index;
import com.mycompany.asiproyecto.service.InicioAlumnoService;
import java.awt.CardLayout;
import java.util.stream.Collectors;
import java.util.List;

public class InicioAlumnoController {
    
    public void filtrarOfertas(InicioAlumno vista) {
        String areaSeleccionada = (String) vista.areaOferaComboBox.getSelectedItem();
        String modalidadSeleccionada = (String) vista.modalidadOfertaComboBox.getSelectedItem();
        String distritoSeleccionado = (String) vista.distritoOfertaComboBox.getSelectedItem();

        List<Oferta> ofertasFiltradas = vista.todasLasOfertas.stream()
                .filter(o -> areaSeleccionada.equals("Todos")
                        || o.getArea().equalsIgnoreCase(areaSeleccionada))
                .filter(o -> modalidadSeleccionada.equals("Todos")
                        || o.getModalidad().equalsIgnoreCase(modalidadSeleccionada))
                .filter(o -> distritoSeleccionado.equals("Todos")
                        || o.getDistrito().equalsIgnoreCase(distritoSeleccionado))
                .collect(Collectors.toList());

        InicioAlumnoService.actualizarPanelOfertas(ofertasFiltradas, vista);
    }
    
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
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "ofertasDePracticasCard":
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misPostulacionesCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_YELLOW);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misContratosCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_YELLOW);
                vista.botonMisInformes.setBackground(Colores.BUTTON_BLUE);
                break;
            case "misInformesCard":
                vista.botonMisPostulaciones.setBackground(Colores.BUTTON_BLUE);
                vista.botonOfertasPracticas.setBackground(Colores.BUTTON_BLUE);
                vista.botonMiInfo.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisContratos.setBackground(Colores.BUTTON_BLUE);
                vista.botonMisInformes.setBackground(Colores.BUTTON_YELLOW);
                break;
        }
    }
}
