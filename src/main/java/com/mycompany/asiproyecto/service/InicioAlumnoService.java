package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.dao.OfertaDAO;
import com.mycompany.asiproyecto.dao.PostulacionDAO;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.model.Postulacion;
import com.mycompany.asiproyecto.view.InicioAlumno;
import com.mycompany.asiproyecto.view.OfertaPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InicioAlumnoService {

    public static void cargarTodasLasOfertas(InicioAlumno vista) {
        OfertaDAO ofertaDAO = new OfertaDAO();
        vista.todasLasOfertas = ofertaDAO.obtenerTodasLasOfertas();
        actualizarPanelOfertas(vista.todasLasOfertas, vista);
    }

    public static void actualizarPanelOfertas(List<Oferta> ofertasParaMostrar, InicioAlumno vista) {
        if (vista.panelMisOfertas == null)
            return; // Safety check
        vista.panelMisOfertas.removeAll();
        vista.panelMisOfertas.setLayout(new java.awt.BorderLayout());

        JPanel container = new JPanel(new java.awt.GridLayout(0, 2, 10, 10)); // 2 columns
        container.setBackground(new java.awt.Color(240, 240, 240));

        PostulacionDAO postulacionDAO = new PostulacionDAO();

        // Create a set of IDs for offers the student has already applied to, for O(1)
        // lookup
        // This avoids calling the DB for every single offer in the loop below.
        java.util.Set<Integer> ofertasPostuladasIds = new java.util.HashSet<>();
        if (vista.todasLasPostulaciones != null) {
            for (Postulacion p : vista.todasLasPostulaciones) {
                ofertasPostuladasIds.add(p.getIdOferta());
            }
        }

        if (ofertasParaMostrar != null) {
            for (Oferta o : ofertasParaMostrar) {
                // isEditable = false para alumno
                OfertaPanel op = new OfertaPanel(o, false);
                op.setPostularVisible(true);

                // Check against the cache set instead of DB
                boolean yaPostulo = ofertasPostuladasIds.contains(o.getIdOferta());

                if (yaPostulo) {
                    op.setPostularEnabled(false);
                } else {
                    op.addPostularListener(e -> {
                        // Custom Dialog Logic
                        JDialog dialog = new JDialog(vista, "Confirmar Postulación", true);
                        dialog.setSize(400, 250);
                        dialog.setLocationRelativeTo(vista);
                        dialog.setLayout(new BorderLayout());

                        JPanel contentPanel = new JPanel();
                        contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel,
                                javax.swing.BoxLayout.Y_AXIS));
                        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

                        JLabel infoLabel = new JLabel(
                                "<html>¿Estás seguro de postular a esta oferta?<br>Debes adjuntar tu CV en formato PDF.</html>");
                        infoLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

                        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        JButton btnSelectFile = new JButton("Seleccionar PDF");
                        JLabel lblFileName = new JLabel("Ningún archivo seleccionado");
                        filePanel.add(btnSelectFile);
                        filePanel.add(lblFileName);

                        contentPanel.add(infoLabel);
                        contentPanel.add(javax.swing.Box.createVerticalStrut(20));
                        contentPanel.add(filePanel);

                        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                        JButton btnCancel = new JButton("Cancelar");
                        JButton btnConfirm = new JButton("SI");
                        btnConfirm.setEnabled(false); // Disabled until file selected

                        buttonPanel.add(btnCancel);
                        buttonPanel.add(btnConfirm);

                        dialog.add(contentPanel, BorderLayout.CENTER);
                        dialog.add(buttonPanel, BorderLayout.SOUTH);

                        // File selection logic
                        final File[] selectedFile = { null };
                        btnSelectFile.addActionListener(evt -> {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setFileFilter(new FileNameExtensionFilter(
                                    "PDF Documents", "pdf"));
                            int result = fileChooser.showOpenDialog(dialog);
                            if (result == JFileChooser.APPROVE_OPTION) {
                                File f = fileChooser.getSelectedFile();
                                if (f.getName().toLowerCase().endsWith(".pdf")) {
                                    selectedFile[0] = f;
                                    lblFileName.setText(f.getName());
                                    btnConfirm.setEnabled(true);
                                } else {
                                    javax.swing.JOptionPane.showMessageDialog(
                                            dialog,
                                            "Por favor selecciona un archivo PDF válido.");
                                }
                            }
                        });

                        btnCancel.addActionListener(evt -> dialog.dispose());

                        btnConfirm.addActionListener(evt -> {
                            if (selectedFile[0] != null) {
                                try {
                                    btnConfirm.setEnabled(false);
                                    btnSelectFile.setEnabled(false);
                                    btnCancel.setEnabled(false);
                                    lblFileName.setText("Subiendo archivo...");

                                    // Upload to Drive
                                    String remoteName = "Postulacion_"
                                            + vista.alumno.getIdAlumno() + "_"
                                            + o.getIdOferta() + ".pdf";
                                    String link = GoogleDriveService.uploadFile(
                                            selectedFile[0], remoteName,
                                            "application/pdf");

                                    // Register in DB
                                    Postulacion p = new Postulacion();
                                    p.setIdAlumno(vista.alumno.getIdAlumno());
                                    p.setIdOferta(o.getIdOferta());
                                    p.setFechaPostulacion(LocalDate.now());
                                    p.setRequisitoLink(link);
                                    p.setEstado("Pendiente");

                                    if (postulacionDAO.registrarPostulacion(p)) {
                                        dialog.dispose();
                                        javax.swing.JOptionPane
                                                .showMessageDialog(vista,
                                                        "Te has postulado correctamente. Archivo subido.");
                                        op.setPostularEnabled(false);
                                        // Update Cache
                                        // RELOAD APPLICATIONS (which updates redundant tracking)
                                        cargarMisPostulaciones(vista);
                                        // RE-RENDER offers to reflect the new "Applied" status locally without full DB
                                        // reload
                                        // However, since we just added a new postulacion, we should update the cache
                                        // map.
                                        // Easiest is to just reload offers view or update local cache.
                                        // Correct flow:
                                        // 1. cargarMisPostulaciones updates 'vista.todasLasPostulaciones'
                                        // 2. We can then re-call actualizarPanelOfertas with current offers to refresh
                                        // buttons
                                        actualizarPanelOfertas(ofertasParaMostrar, vista);
                                    } else {
                                        throw new Exception(
                                                "Error al registrar en base de datos.");
                                    }

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    javax.swing.JOptionPane.showMessageDialog(
                                            dialog,
                                            "Error al procesar la postulación: "
                                                    + ex.getMessage());
                                    btnConfirm.setEnabled(true);
                                    btnSelectFile.setEnabled(true);
                                    btnCancel.setEnabled(true);
                                    lblFileName.setText(selectedFile[0].getName());
                                }
                            }
                        });

                        dialog.setVisible(true);
                    });
                }
                container.add(op);
            }
        }

        JScrollPane scroll = new JScrollPane(container);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setPreferredSize(new java.awt.Dimension(0, 0));
        vista.panelMisOfertas.add(scroll, java.awt.BorderLayout.CENTER);
        if (vista.jLabel24 != null) {
            vista.jLabel24.setText("OFERTAS ENCONTRADAS: "
                    + (ofertasParaMostrar != null ? ofertasParaMostrar.size() : 0));
        }
        vista.panelMisOfertas.revalidate();
        vista.panelMisOfertas.repaint();
    }

    public static void llenarMiInfo(InicioAlumno vista) {
        // Académica
        vista.labelNombreCompleto.setText(vista.alumno.getNombresAlumno() + ", " + vista.alumno.getApellidosAlumno());
        vista.labelCodigo.setText(vista.alumno.getCodigo());
        vista.labelCurso.setText(vista.alumno.getCurso());
        vista.labelEP.setText(vista.alumno.getCarrera());
        vista.labelProfesorAsignado.setText(vista.alumno.getDocenteACargo());
        // Personal
        vista.labelNombres.setText(vista.alumno.getNombresAlumno());
        vista.labelApellidos.setText(vista.alumno.getApellidosAlumno());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vista.labelFechaNac.setText(vista.alumno.getFechaNacimiento().format(dateFormatter));
        vista.labelDni.setText(vista.alumno.getDni());
        vista.labelGenero.setText(vista.alumno.getGenero());
        vista.labelCorreo.setText(vista.alumno.getCorreoElectronico());
    }

    public static void cargarMisPostulaciones(InicioAlumno vista) {
        PostulacionDAO postulacionDAO = new PostulacionDAO();
        vista.todasLasPostulaciones = postulacionDAO.obtenerPostulacionesPorAlumno(vista.alumno.getIdAlumno());
        vista.actualizarPanelPostulaciones();
        vista.cargarMisContratos();
    }

    // Helper method called from constructor to initial load
    public static void agregarPanelPostulaciones(InicioAlumno vista) {
        cargarMisPostulaciones(vista);
    }
}
