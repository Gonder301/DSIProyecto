package com.mycompany.asiproyecto.view;

import com.mycompany.asiproyecto.model.Oferta;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class OfertaPanel extends JPanel {
    private Oferta oferta;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public OfertaPanel(Oferta oferta) {
        this.oferta = oferta;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(350, 200));

        // Panel principal con márgenes
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // Nombre de la empresa (más destacado)
        JLabel lblEmpresa = new JLabel(oferta.getNombreEmpresa());
        lblEmpresa.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblEmpresa.setForeground(new Color(0, 100, 200));
        lblEmpresa.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Puesto de práctica
        JLabel lblPuesto = new JLabel(oferta.getPuestoPractica());
        lblPuesto.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPuesto.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Área
        JLabel lblArea = new JLabel("Área: " + oferta.getArea());
        lblArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Distrito
        JLabel lblDistrito = new JLabel("Distrito: " + oferta.getDistrito());
        lblDistrito.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblDistrito.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Modalidad
        JLabel lblModalidad = new JLabel("Modalidad: " + oferta.getModalidad());
        lblModalidad.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblModalidad.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Fecha límite
        JLabel lblFechaLimite = new JLabel();
        if (oferta.getFechaLimiteRecepcion() != null) {
            lblFechaLimite.setText("Fecha límite: " + oferta.getFechaLimiteRecepcion().format(dateFormatter));
        } else {
            lblFechaLimite.setText("Fecha límite: No especificada");
        }
        lblFechaLimite.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblFechaLimite.setForeground(Color.RED);
        lblFechaLimite.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Botón para más detalles
        JButton btnVerMas = new JButton("Ver más detalles");
        btnVerMas.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnVerMas.addActionListener(e -> mostrarDetalles());

        // Espaciadores
        Component verticalStrut = Box.createVerticalStrut(5);
        Component verticalStrut2 = Box.createVerticalStrut(10);

        // Agregar componentes al panel
        contentPanel.add(lblEmpresa);
        contentPanel.add(verticalStrut);
        contentPanel.add(lblPuesto);
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(lblArea);
        contentPanel.add(Box.createVerticalStrut(3));
        contentPanel.add(lblDistrito);
        contentPanel.add(Box.createVerticalStrut(3));
        contentPanel.add(lblModalidad);
        contentPanel.add(verticalStrut2);
        contentPanel.add(lblFechaLimite);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(btnVerMas);

        add(contentPanel, BorderLayout.CENTER);
    }
    
    private void mostrarDetalles() {
        // Crear un diálogo para mostrar todos los detalles
        JDialog detallesDialog = new JDialog();
        detallesDialog.setTitle("Detalles de la Oferta - " + oferta.getNombreEmpresa());
        detallesDialog.setModal(true);
        detallesDialog.setSize(500, 400);
        detallesDialog.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));

        // Construir el texto con todos los detalles
        StringBuilder detalles = new StringBuilder();
        detalles.append("EMPRESA: ").append(oferta.getNombreEmpresa()).append("\n\n");
        detalles.append("PUESTO: ").append(oferta.getPuestoPractica()).append("\n\n");
        detalles.append("DESCRIPCIÓN:\n").append(oferta.getDescriptionPerfil()).append("\n\n");
        detalles.append("REQUISITOS:\n").append(oferta.getRequisitos()).append("\n\n");
        detalles.append("HABILIDADES:\n").append(oferta.getHabilidadesCompetencias()).append("\n\n");
        detalles.append("ÁREA: ").append(oferta.getArea()).append("\n");
        detalles.append("DISTRITO: ").append(oferta.getDistrito()).append("\n");
        detalles.append("MODALIDAD: ").append(oferta.getModalidad()).append("\n\n");
        
        if (oferta.getFechaInicio() != null) {
            detalles.append("FECHA INICIO: ").append(oferta.getFechaInicio().format(dateFormatter)).append("\n");
        }
        if (oferta.getFechaFin() != null) {
            detalles.append("FECHA FIN: ").append(oferta.getFechaFin().format(dateFormatter)).append("\n");
        }
        if (oferta.getFechaLimiteRecepcion() != null) {
            detalles.append("FECHA LÍMITE: ").append(oferta.getFechaLimiteRecepcion().format(dateFormatter)).append("\n");
        }
        
        detalles.append("\nBENEFICIOS:\n").append(oferta.getBeneficios()).append("\n\n");
        detalles.append("CONSULTAS:\n").append(oferta.getConsultas());

        textArea.setText(detalles.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        detallesDialog.add(scrollPane);

        detallesDialog.setVisible(true);
    }
}