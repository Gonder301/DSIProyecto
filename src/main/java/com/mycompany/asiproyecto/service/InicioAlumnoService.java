package com.mycompany.asiproyecto.service;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.dao.ProfesorDAO;
import com.mycompany.asiproyecto.dao.OfertaDAO;
import com.mycompany.asiproyecto.dao.PostulacionDAO;
import com.mycompany.asiproyecto.dao.ContratoDAO;
import com.mycompany.asiproyecto.model.Oferta;
import com.mycompany.asiproyecto.model.Postulacion;
import com.mycompany.asiproyecto.model.Contrato;
import com.mycompany.asiproyecto.view.InicioAlumno;
import com.mycompany.asiproyecto.view.OfertaPanel;
import com.mycompany.asiproyecto.view.MisContratosJDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
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

    public static void cargarMisContratos(InicioAlumno vista) {
        if (vista.misContratosScrollPane == null)
            return;

        JPanel container = new JPanel(new java.awt.GridLayout(0, 1, 10, 10));
        container.setBackground(new java.awt.Color(240, 240, 240));

        // Usar cache
        if (vista.todasLasPostulaciones != null) {
            for (Postulacion p : vista.todasLasPostulaciones) {
                if ("Aceptado".equalsIgnoreCase(p.getEstado())) {
                    // Create logic wrapper panel
                    JPanel rowPanel = new JPanel(new BorderLayout(10, 10));
                    rowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                    rowPanel.setBackground(java.awt.Color.WHITE);

                    // Oferta Panel
                    OfertaPanel op = new OfertaPanel(p.getOferta(), false);
                    op.setPostularVisible(false);

                    // Action Panel (Adjuntar Button)
                    JPanel actionPanel = new JPanel(new java.awt.GridLayout(1, 1));
                    actionPanel.setPreferredSize(new Dimension(150, 40));
                    actionPanel.setOpaque(false);

                    // Center the button vertically if needed, or just fill
                    // Using a flow layout within the grid cell or just adding directly can work.
                    // Let's make it look nice.

                    JButton btnAdjuntar = new JButton("Adjuntar");
                    btnAdjuntar.setBackground(new java.awt.Color(0, 51, 255));
                    btnAdjuntar.setForeground(java.awt.Color.WHITE);
                    btnAdjuntar.setFont(new java.awt.Font("SansSerif", 1, 14));
                    btnAdjuntar.setFocusPainted(false);
                    btnAdjuntar.addActionListener(e -> {
                        MisContratosJDialog dialog = new MisContratosJDialog(vista, true, p.getIdAlumno(),
                                p.getIdOferta(), vista.labelProfesorAsignado.getText());
                        dialog.setVisible(true);
                    });

                    JPanel buttonWrapper = new JPanel(new java.awt.GridBagLayout());
                    buttonWrapper.setOpaque(false);
                    buttonWrapper.setPreferredSize(new Dimension(150, 0));

                    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
                    gbc.weightx = 1.0;
                    gbc.insets = new java.awt.Insets(0, 5, 0, 5);

                    buttonWrapper.add(btnAdjuntar, gbc);

                    rowPanel.add(op, BorderLayout.CENTER);
                    rowPanel.add(buttonWrapper, BorderLayout.EAST);

                    container.add(rowPanel);
                }
            }
        }

        vista.misContratosScrollPane.setViewportView(container);
        vista.misContratosScrollPane.revalidate();
        vista.misContratosScrollPane.repaint();
    }

    public static void cargarTodasLasOfertas(InicioAlumno vista) {
        OfertaDAO ofertaDAO = new OfertaDAO();
        vista.todasLasOfertas = ofertaDAO.obtenerTodasLasOfertas();
        actualizarPanelOfertas(vista.todasLasOfertas, vista);
    }

    public static void actualizarPanelPostulaciones(InicioAlumno vista) {
        if (vista.misPostulacionesScrollPane == null)
            return;

        JPanel container = new JPanel(new java.awt.GridLayout(0, 1, 10, 10));
        container.setBackground(new java.awt.Color(240, 240, 240));

        PostulacionDAO postulacionDAO = new PostulacionDAO();
        // Usamos la cache en lugar de consultar DAO directamente
        if (vista.todasLasPostulaciones != null) {
            for (Postulacion p : vista.todasLasPostulaciones) {
                // Create logic wrapper panel
                JPanel rowPanel = new JPanel(new BorderLayout(10, 10));
                rowPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                rowPanel.setBackground(java.awt.Color.WHITE);

                // Oferta Panel
                OfertaPanel op = new OfertaPanel(p.getOferta(), false);
                // Override default buttons in OfertaPanel if needed or just use it as display
                op.setPostularVisible(false); // We don't want to apply again

                // Status and Action Panel
                JPanel actionPanel = new JPanel(new java.awt.GridLayout(2, 1, 5, 5));
                actionPanel.setPreferredSize(new Dimension(150, 0));
                actionPanel.setOpaque(false);

                // Status Button (Visual Only)
                JButton btnEstado = new JButton(p.getEstado());
                btnEstado.setFocusPainted(false);
                btnEstado.setFont(new java.awt.Font("SansSerif", 1, 12));
                // Color logic
                if ("Pendiente".equalsIgnoreCase(p.getEstado())) {
                    btnEstado.setBackground(Colores.BUTTON_YELLOW);
                    btnEstado.setForeground(java.awt.Color.BLACK);
                } else if ("Aceptado".equalsIgnoreCase(p.getEstado())) {
                    btnEstado.setBackground(java.awt.Color.GREEN);
                    btnEstado.setForeground(java.awt.Color.BLACK);
                } else if ("Rechazado".equalsIgnoreCase(p.getEstado())) {
                    btnEstado.setBackground(java.awt.Color.RED);
                    btnEstado.setForeground(java.awt.Color.BLACK);
                }

                // Disable button but keep color using UI override
                btnEstado.setEnabled(false);
                btnEstado.setUI(new javax.swing.plaf.metal.MetalButtonUI() {
                    protected java.awt.Color getDisabledTextColor() {
                        return btnEstado.getForeground();
                    }
                });

                // Delete Button
                JButton btnEliminar = new JButton("Eliminar");
                btnEliminar.setBackground(Colores.BACKGROUND_RED);
                btnEliminar.setForeground(java.awt.Color.WHITE);
                btnEliminar.addActionListener(e -> {
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(vista,
                            "¿Estás seguro de eliminar esta postulación?\nSe eliminará tu CV y el registro en el sistema.",
                            "Confirmar Eliminación",
                            javax.swing.JOptionPane.YES_NO_OPTION);

                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        try {
                            // 1. Delete from Drive
                            // Filename convention: Postulacion_{idAlumno}_{idOferta}.pdf
                            String fileName = "Postulacion_" + p.getIdAlumno() + "_" + p.getIdOferta() + ".pdf";
                            GoogleDriveService.deleteFileByName(fileName);

                            // 2. Delete from DB
                            if (postulacionDAO.eliminarPostulacion(p.getIdAlumno(), p.getIdOferta())) {
                                javax.swing.JOptionPane.showMessageDialog(vista,
                                        "Postulación eliminada correctamente.");
                                InicioAlumnoService.cargarMisPostulaciones(vista); // Recargar cache
                                InicioAlumnoService.cargarTodasLasOfertas(vista); // Refresh Main offers too
                            } else {
                                javax.swing.JOptionPane.showMessageDialog(vista,
                                        "Error al eliminar de la base de datos.");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            javax.swing.JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
                        }
                    }
                });

                actionPanel.add(btnEstado);
                actionPanel.add(btnEliminar);

                // Desabilita el botón eliminar si la postulación no está en estado pendiente
                if (!"Pendiente".equalsIgnoreCase(p.getEstado())) {
                    btnEliminar.setBackground(java.awt.Color.GRAY);
                    btnEliminar.setEnabled(false);
                }
                rowPanel.add(op, BorderLayout.CENTER);
                rowPanel.add(actionPanel, BorderLayout.EAST);

                container.add(rowPanel);
            }
        }

        vista.misPostulacionesScrollPane.setViewportView(container);
        vista.misPostulacionesScrollPane.revalidate();
        vista.misPostulacionesScrollPane.repaint();
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
                        btnConfirm.setEnabled(false); // Deshabilitar hasta que el archivo se seleccione

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
        //Alumno debería tener un campo que sea nombreProfesorAdignado
        //que almacene eso mismo, pero tiene idProfesor.
        //Se está dejando la lógica así para no tener que hacer una doble
        //consulta al obtener Alumno(s) de la base de datos ya que
        //se tendría que hacer la misma consulta que se está haciendo
        //abajo para poder llenar el campo nombreProfesorAdignado.
        //El nombre del profesor asignado será pasado por parámetro a
        //MisContratosJDialog, obtenido del label labelProfesorAsignado.
        ProfesorDAO pdao = new ProfesorDAO();
        vista.labelProfesorAsignado.setText(pdao.obetenerNombreCompleto(vista.alumno.getIdProfesorACargo()));
        
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
        actualizarPanelPostulaciones(vista);
        cargarMisContratos(vista);
    }

    public static Contrato buscarContrato (MisContratosJDialog vista) {
        ContratoDAO contratoDAO = new ContratoDAO();
        Contrato contrato = null;
        contrato = contratoDAO.obtenerPorIdAlumnoIdOferta(vista.idAlumno, vista.idOferta);
        return contrato;
    }
    
    public static void actualizarDialogSegunExistenciaContrato (MisContratosJDialog vista) {
        vista.contrato = buscarContrato(vista);
        if (vista.contrato != null) { //El contrato ya se encuentra registrado en la BD.
            vista.datePickerInicio.setDate(vista.contrato.getFechaInicio());
            vista.datePickerInicio.setEnabled(false);
            vista.datePickerFin.setDate(vista.contrato.getFechaFin());
            vista.datePickerFin.setEnabled(false);
            vista.btnSelectFile.setEnabled(false);
            vista.btnEnviar.setEnabled(false);
            vista.btnAnular.setEnabled(true);
            
            vista.labelEstado.setText(vista.contrato.getEstadoContrato());
            switch(vista.labelEstado.getText()) {
                case "Pendiente":
                    vista.labelEstado.setForeground(Colores.BUTTON_YELLOW);
                    break;
                case "Aceptado":
                    vista.labelEstado.setForeground(Colores.GREEN_ACEPTADO);
                    break;
                case "Rechazado":
                    vista.labelEstado.setForeground(Colores.BACKGROUND_RED);
                    break;
            }
        }
        else {//El contrato no está registrado en la BD.
            vista.datePickerInicio.setDate(LocalDate.now());
            vista.datePickerInicio.setEnabled(true);
            vista.datePickerFin.setDate(LocalDate.now());
            vista.datePickerFin.setEnabled(true);
            vista.btnSelectFile.setEnabled(true);
            //se activa cuando se añade el archivo
            vista.btnEnviar.setEnabled(false);
            
            vista.btnAnular.setEnabled(false);
            
            vista.labelEstado.setText("No registrado");
        }
    }
    
    public static void agregarPanelPostulaciones(InicioAlumno vista) {
        cargarMisPostulaciones(vista);
    }
    
    public static void agregarDatePickers (MisContratosJDialog vista) {
        DatePickerSettings dateSettings1 = new DatePickerSettings();
        dateSettings1.setFormatForDatesCommonEra("dd-MM-yyyy");
        dateSettings1.setAllowKeyboardEditing(false);
        vista.datePickerInicio = new DatePicker(dateSettings1);
        
        DatePickerSettings dateSettings2 = new DatePickerSettings();
        dateSettings2.setFormatForDatesCommonEra("dd-MM-yyyy");
        dateSettings2.setAllowKeyboardEditing(false);
        vista.datePickerFin = new DatePicker(dateSettings2);
        
        vista.datePickerInicio.getComponentDateTextField().setEditable(false);
        vista.datePickerInicio.getComponentDateTextField().setFocusable(false);
        vista.datePickerInicio.setDate(LocalDate.now());
        
        vista.datePickerFin.getComponentDateTextField().setEditable(false);
        vista.datePickerFin.getComponentDateTextField().setFocusable(false);
        vista.datePickerFin.setDate(LocalDate.now());
        
        java.awt.Container contentPane = vista.getContentPane();
        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) contentPane.getLayout();
        layout.replace(vista.datePickerPlaceholder1, vista.datePickerInicio);
        layout.replace(vista.datePickerPlaceholder2, vista.datePickerFin);
        vista.revalidate();
        vista.repaint();
    }
}
