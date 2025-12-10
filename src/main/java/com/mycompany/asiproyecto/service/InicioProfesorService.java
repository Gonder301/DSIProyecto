package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.dao.AlumnoContratoDAO;
import com.mycompany.asiproyecto.dao.ContratoDAO;
import com.mycompany.asiproyecto.model.AlumnoContrato;
import com.mycompany.asiproyecto.view.InicioProfesor;
import com.mycompany.asiproyecto.view.RenderContratoJDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InicioProfesorService {

    public static void cargarContratos(InicioProfesor vista) {
        AlumnoContratoDAO alumnoContratoDAO = new AlumnoContratoDAO();
        vista.todosLosAlumnoContrato = alumnoContratoDAO
                .obtenerAlumnoContratoListaPorIdprofesor(vista.profesor.getIdProfesor());
        actualizarPanelContratos(vista.todosLosAlumnoContrato, vista);
    }

    public static void actualizarPanelContratos(List<AlumnoContrato> contratosParaMostrar, InicioProfesor vista) {
        javax.swing.JPanel container = new javax.swing.JPanel();
        container.setLayout(new javax.swing.BoxLayout(container, javax.swing.BoxLayout.Y_AXIS));
        container.setBackground(new java.awt.Color(240, 240, 240));

        if (!contratosParaMostrar.isEmpty()) {
            for (AlumnoContrato c : contratosParaMostrar) {
                javax.swing.JPanel itemPanel = new javax.swing.JPanel(new java.awt.BorderLayout(10, 10));

                // --- Dimensiones y Estilo del Item ---
                itemPanel.setPreferredSize(new java.awt.Dimension(550, 100)); // Aumenté un poco el ancho preferido para
                                                                              // que quepan los botones
                itemPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 100));
                itemPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
                itemPanel.setBackground(java.awt.Color.WHITE);

                // --- Panel de Información (Izquierda) ---
                // Cambiado a (0, 1) para permitir n filas dinámicas (tienes 3 etiquetas)
                javax.swing.JPanel infoPanel = new javax.swing.JPanel(new java.awt.GridLayout(0, 1));
                infoPanel.setOpaque(false);

                javax.swing.JLabel lblNombre = new javax.swing.JLabel("Alumno: " + c.nombreCompletoAlumno);
                lblNombre.setFont(new java.awt.Font("SansSerif", 1, 14));

                javax.swing.JLabel lblCorreo = new javax.swing.JLabel("Correo: " + c.correoElectronicoAlumno);
                lblCorreo.setFont(new java.awt.Font("SansSerif", 0, 14));

                javax.swing.JLabel lblEstado = new javax.swing.JLabel("Estado: " + c.estadoContrato);
                lblEstado.setFont(new java.awt.Font("SansSerif", 1, 14)); // Puse bold para destacar

                // Lógica de colores del estado actual
                switch (c.estadoContrato) {
                    case "Pendiente":
                        lblEstado.setForeground(java.awt.Color.GRAY); // Asumo gris si no es botón
                        break;
                    case "Aceptado":
                        lblEstado.setForeground(Colores.GREEN_ACEPTADO);
                        break;
                    case "Rechazado":
                        lblEstado.setForeground(Colores.BACKGROUND_RED);
                        break;
                }

                infoPanel.add(lblNombre);
                infoPanel.add(lblCorreo);
                infoPanel.add(lblEstado);

                // --- Panel de Acciones (Derecha) ---
                // Usamos FlowLayout para poner el botón VER al lado de los controles de
                // actualización
                javax.swing.JPanel actionsPanel = new javax.swing.JPanel(
                        new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));
                actionsPanel.setOpaque(false);

                // 1. Botón VER
                javax.swing.JButton btnVer = new javax.swing.JButton("VER");
                btnVer.setBackground(new java.awt.Color(0, 51, 255));
                btnVer.setForeground(java.awt.Color.WHITE);
                btnVer.setFont(new java.awt.Font("SansSerif", 1, 14));
                btnVer.setFocusPainted(false);
                // Hacer el botón VER un poco más alto para centrarlo visualmente respecto al
                // panel de update
                btnVer.setPreferredSize(new java.awt.Dimension(70, 60));

                btnVer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        abrirRenderContratoJDialog(vista, c.idDocumento);
                    }
                });

                // 2. Panel de Actualización (Combo + Botón Actualizar)
                javax.swing.JPanel panelUpdate = new javax.swing.JPanel(new java.awt.GridLayout(2, 1, 0, 5)); // 2
                                                                                                              // filas,
                                                                                                              // gap
                                                                                                              // vertical
                                                                                                              // de 5
                panelUpdate.setOpaque(false);

                // Combo Box
                javax.swing.JComboBox<String> cmbEstadoUpdate = new javax.swing.JComboBox<>();
                cmbEstadoUpdate
                        .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aceptado", "Rechazado" }));
                cmbEstadoUpdate.setFont(new java.awt.Font("SansSerif", 0, 12));

                // Botón Actualizar
                javax.swing.JButton btnActualizar = new javax.swing.JButton("Actualizar");
                btnActualizar.setFont(new java.awt.Font("SansSerif", 1, 11));
                btnActualizar.setBackground(new java.awt.Color(230, 230, 230)); // Gris claro
                btnActualizar.setFocusPainted(false);

                // Listener para el botón actualizar
                btnActualizar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nuevoEstado = (String) cmbEstadoUpdate.getSelectedItem();
                        if (nuevoEstado.equalsIgnoreCase(c.estadoContrato)) {
                            return;
                        }
                        ContratoDAO contratoDAO = new ContratoDAO();
                        contratoDAO.actualizarEstado(c.idContrato, nuevoEstado);
                        cargarContratos(vista);
                    }
                });

                panelUpdate.add(cmbEstadoUpdate);
                panelUpdate.add(btnActualizar);

                // Agregar componentes al panel de acciones
                actionsPanel.add(btnVer);
                actionsPanel.add(panelUpdate);

                // Agregar paneles al item principal
                itemPanel.add(infoPanel, java.awt.BorderLayout.CENTER);
                itemPanel.add(actionsPanel, java.awt.BorderLayout.EAST);

                container.add(itemPanel);

                // Gap entre items
                container.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
            }
        }

        vista.scrollPanelContratos.setViewportView(container);
        vista.scrollPanelContratos.revalidate();
        vista.scrollPanelContratos.repaint();
    }

    public static void abrirRenderContratoJDialog(InicioProfesor vista, String idDocumento) {
        RenderContratoJDialog dialog = new RenderContratoJDialog(idDocumento, vista, true);
        dialog.setLocationRelativeTo(vista);
        dialog.setVisible(true);
    }

    public static void llenarMiInfo(InicioProfesor vista) {
        vista.labelCarrera.setText(vista.profesor.getCarrera());
        vista.labelCodigoCurso.setText(vista.profesor.getCodigoCurso());
        vista.labelCorreo.setText(vista.profesor.getCorreoInstitucional());
        vista.labelCurso.setText(vista.profesor.getNombreCurso());
        vista.labelDni.setText(vista.profesor.getDni());
        vista.labelDocente.setText(vista.profesor.getNombresProfesor() + ", " + vista.profesor.getApellidosProfesor());
    }
}
