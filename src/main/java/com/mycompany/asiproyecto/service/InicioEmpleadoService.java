package com.mycompany.asiproyecto.service;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.dao.OfertaDAO;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.view.InicioEmpleado;
import com.mycompany.asiproyecto.view.OfertaPanel;
import java.time.LocalDate;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

public class InicioEmpleadoService {
    
    public static void cargarMisOfertas(InicioEmpleado vista) {
        OfertaDAO ofertaDAO = new OfertaDAO();
        vista.todasLasOfertas = ofertaDAO.obtenerOfertasPorEmpleado(vista.empleadoEmpresa.getIdEmpleado());
        // Mostramos todas inicialmente
        actualizarPanelOfertas(vista.todasLasOfertas, vista);
    }
    
    public static void actualizarPanelOfertas (java.util.List<Oferta> ofertasFiltradas, InicioEmpleado vista) {
        OfertaDAO ofertaDAO = new OfertaDAO();

        vista.panelMisOfertas.removeAll();
        vista.panelMisOfertas.setLayout(new java.awt.BorderLayout());

        JPanel container = new JPanel(new java.awt.GridLayout(0, 2, 10, 10)); // 2 columns
        container.setBackground(new java.awt.Color(240, 240, 240));

        for (Oferta o : ofertasFiltradas) {
            OfertaPanel op = new OfertaPanel(o, true);
            op.addEliminarListener(e -> {
                int confirm = javax.swing.JOptionPane.showConfirmDialog(vista, "¿Estás seguro de eliminar esta oferta?",
                        "Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION);
                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    if (ofertaDAO.eliminarOferta(o.getIdOferta())) {
                        cargarMisOfertas(vista);
                        javax.swing.JOptionPane.showMessageDialog(vista, "Oferta eliminada correctamente");
                    }
                }
            });
            op.addEditarListener(e -> {
                javax.swing.JOptionPane.showMessageDialog(vista, "Funcionalidad de editar próximamente.");
            });
            container.add(op);
        }

        JScrollPane scroll = new JScrollPane(container);
        scroll.setPreferredSize(new java.awt.Dimension(0, 0));
        vista.panelMisOfertas.add(scroll, java.awt.BorderLayout.CENTER);
        vista.jLabel24.setText("OFERTAS ENCONTRADAS: " + ofertasFiltradas.size());
        vista.panelMisOfertas.revalidate();
        vista.panelMisOfertas.repaint();
    }
    
    public static String formatearTextArea(JTextArea ta) {
        //Reemplaza los saltos de linea por comas.
        String taFormateado = ta.getText().replaceAll("[\\r\\n]+", ", ");
        //Si al final se dejó un salto de linea en el texto original.
        if (taFormateado.endsWith(", ")) {
            taFormateado = taFormateado.substring(0, taFormateado.length() - 2);
        }
        return taFormateado;
    }
    
    public static void agregarDatePickers(InicioEmpleado vista) {
        DatePickerSettings dateSettings1 = new DatePickerSettings();
        dateSettings1.setFormatForDatesCommonEra("dd-MM-yyyy");
        DatePickerSettings dateSettings2 = new DatePickerSettings();
        dateSettings2.setFormatForDatesCommonEra("dd-MM-yyyy");
        vista.datePickerInicio = new DatePicker(dateSettings1);
        vista.datePickerFin = new DatePicker(dateSettings2);
        vista.datePickerInicio.setDate(LocalDate.now());
        vista.datePickerFin.setDate(LocalDate.now());
        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) vista.informacionOfertaPanel.getLayout();
        layout.replace(vista.datePickerPlaceholder1, vista.datePickerInicio);
        layout.replace(vista.datePickerPlaceholder2, vista.datePickerFin);
        vista.informacionOfertaPanel.revalidate();
        vista.informacionOfertaPanel.repaint();
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
