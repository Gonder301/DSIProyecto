package com.mycompany.asiproyecto.service;

import com.mycompany.asiproyecto.dao.AlumnoContratoDAO;
import com.mycompany.asiproyecto.model.AlumnoContrato;
import com.mycompany.asiproyecto.view.InicioProfesor;
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

        if (contratosParaMostrar != null) {
            for (AlumnoContrato c : contratosParaMostrar) {
                javax.swing.JPanel itemPanel = new javax.swing.JPanel(new java.awt.BorderLayout(10, 10));
                // Dimensiones
                itemPanel.setPreferredSize(new java.awt.Dimension(468, 100));
                itemPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 100));

                // Padding
                itemPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
                itemPanel.setBackground(java.awt.Color.WHITE);

                // Info
                javax.swing.JPanel infoPanel = new javax.swing.JPanel(new java.awt.GridLayout(2, 1));
                infoPanel.setOpaque(false);
                javax.swing.JLabel lblNombre = new javax.swing.JLabel("Alumno: " + c.nombreCompletoAlumno);
                lblNombre.setFont(new java.awt.Font("SansSerif", 1, 14));
                javax.swing.JLabel lblCorreo = new javax.swing.JLabel("Correo: " + c.correoElectronicoAlumno);
                lblCorreo.setFont(new java.awt.Font("SansSerif", 0, 14));

                infoPanel.add(lblNombre);
                infoPanel.add(lblCorreo);

                // Boton
                javax.swing.JButton btn = new javax.swing.JButton();
                btn.setBackground(new java.awt.Color(0, 51, 255));
                btn.setForeground(java.awt.Color.WHITE);
                btn.setFont(new java.awt.Font("SansSerif", 1, 14));
                btn.setFocusPainted(false);

                String estado = c.estadoContrato;
                if (estado == null) {
                    estado = "";
                }

                if ("Pendiente".equalsIgnoreCase(estado)) {
                    btn.setText("VER");
                } else if ("Aceptado".equalsIgnoreCase(estado) || "Rechazado".equalsIgnoreCase(estado)) {
                    btn.setText("EDITAR");
                } else {
                    btn.setText("VER");
                }

                itemPanel.add(infoPanel, java.awt.BorderLayout.CENTER);
                itemPanel.add(btn, java.awt.BorderLayout.EAST);

                container.add(itemPanel);

                // Gap entre items
                container.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
            }
        }

        vista.scrollPanelContratos.setViewportView(container);
        vista.scrollPanelContratos.revalidate();
        vista.scrollPanelContratos.repaint();
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
