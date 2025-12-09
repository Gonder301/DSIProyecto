package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.view.InicioAlumno;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.model.Contrato;
import com.mycompany.asiproyecto.dao.ContratoDAO;
import com.mycompany.asiproyecto.service.GoogleDriveService;
import com.mycompany.asiproyecto.view.Index;
import com.mycompany.asiproyecto.service.InicioAlumnoService;
import com.mycompany.asiproyecto.view.MisContratosJDialog;
import java.awt.CardLayout;
import java.io.File;
import java.util.stream.Collectors;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    
    public void seleccionarFileContrato(MisContratosJDialog vista) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        int result = fileChooser.showOpenDialog(vista);
        if (result == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            if (f.getName().toLowerCase().endsWith(".pdf")) {
                vista.selectedFile[0] = f;
                vista.lblFileName.setText(f.getName());
                vista.btnEnviar.setEnabled(true);
            } else {
                javax.swing.JOptionPane.showMessageDialog(vista, "Por favor selecciona un archivo PDF válido.");
            }
        }
    }
    
    public void botonEnviarPresionado(MisContratosJDialog vista) {
        if (vista.selectedFile[0] != null) {
            try {
                vista.btnEnviar.setEnabled(false);
                vista.btnSelectFile.setEnabled(false);
                vista.lblFileName.setText("Subiendo archivo...");
                
                Contrato c = new Contrato();
                c.setIdAlumno(vista.contrato.getIdAlumno());
                c.setIdOferta(vista.contrato.getIdOferta());
                c.setFechaInicio(vista.datePickerInicio.getDate());
                c.setFechaFin(vista.datePickerFin.getDate());
                c.setEstadoContrato("Pendiente");
                
                // Upload to Drive
                String remoteName = "Contrato_"
                    + c.getIdAlumno() + "_"
                    + c.getIdOferta() + ".pdf";
                String link = GoogleDriveService.uploadFile(
                    vista.selectedFile[0], remoteName,
                    "application/pdf");
                
                c.setDocumentoContrato(link);

                ContratoDAO contratoDAO = new ContratoDAO();
                
                if (contratoDAO.registrar(c)) {
                    javax.swing.JOptionPane.showMessageDialog(vista, "Contrato enviado correctamente.");
                    InicioAlumnoService.actualizarDialogSegunExistenciaContrato(vista);
                } else {
                    throw new Exception("Error al registrar contrato en base de datos.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                vista.btnEnviar.setEnabled(true);
                vista.btnSelectFile.setEnabled(true);
                vista.lblFileName.setText(vista.selectedFile[0].getName());
            }
        }
    }
}
