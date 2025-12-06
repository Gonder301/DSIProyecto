package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.dao.OfertaDAO;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.view.InicioEmpleado;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class InicioEmpleadoService {
    public static String formatearTextArea(JTextArea ta) {
        //Reemplaza los saltos de linea por comas.
        String taFormateado = ta.getText().replaceAll("[\\r\\n]+", ", ");
        //Si al final se dejó un salto de linea en el texto original.
        if (taFormateado.endsWith(", ")) {
            taFormateado = taFormateado.substring(0, taFormateado.length() - 2);
        }
        return taFormateado;
    }
    
    public static Oferta obtenerOfertaDeForm(InicioEmpleado vista) {
        Oferta oferta = new Oferta();
        oferta.setNombreEmpresa(vista.nombreEmpresaTF.getText());
        oferta.setDescriptionPerfil(formatearTextArea(vista.descripcionPerfilTA));
        oferta.setPuestoPractica((String)vista.puestoComboBox.getSelectedItem());
        oferta.setRequisitos(formatearTextArea(vista.requisitosTA));
        oferta.setFechaInicio(vista.datePickerInicio.getDate());
        oferta.setFechaFin(vista.datePickerFin.getDate());
        oferta.setModalidad((String)vista.modalidadComboBox.getSelectedItem());
        oferta.setHabilidadesCompetencias(formatearTextArea(vista.habilidadesCompetenciasTA));
        oferta.setArea((String)vista.areaComboBox.getSelectedItem());
        oferta.setDistrito(vista.distritoTF.getText());
        oferta.setBeneficios(formatearTextArea(vista.beneficiosTA));
        oferta.setConsultas(formatearTextArea(vista.consultasTA));
        oferta.setEmpleadoID(vista.empleadoEmpresa.getIdEmpleado());
        return oferta;
    }
    
    public static int camposVaciosFormOferta(InicioEmpleado vista) {
        int camposVacios = 0;
        LineBorder lineBorderErr = new LineBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
        LineBorder lineBorderDef = new LineBorder(Colores.TEXTFIELD_BORDER_DEF, 1);
        
        if (vista.nombreEmpresaTF.getText().isEmpty()) {
            vista.nombreEmpresaTF.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.nombreEmpresaTF.setBorder(lineBorderDef);
        }
        
        if (vista.distritoTF.getText().isEmpty()) {
            vista.distritoTF.setBorder(lineBorderErr);
            camposVacios += 1;
        }
        else {
            vista.distritoTF.setBorder(lineBorderDef);
        }
        
        return camposVacios;
    }
    
    public static boolean registroOfertaExitoso(Oferta o) {
        OfertaDAO adao = new OfertaDAO();
        return adao.registrarOferta(o);
    }
}
