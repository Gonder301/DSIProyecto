package com.mycompany.asiproyecto.view;

import com.mycompany.asiproyecto.model.Oferta;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class OfertaPanel extends JPanel {
    private Oferta oferta;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private boolean isEditable;
    private JButton btnVer;
    private JButton btnEditar;
    private JButton btnEliminar;

    public OfertaPanel(Oferta oferta) {
        this(oferta, false);
    }

    public OfertaPanel(Oferta oferta, boolean isEditable) {
        this.oferta = oferta;
        this.isEditable = isEditable;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
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

        // Espaciadores
        Component verticalStrut = Box.createVerticalStrut(5);
        Component verticalStrut2 = Box.createVerticalStrut(10);

        // Agregar componentes al panel de contenido
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
        contentPanel.add(Box.createVerticalStrut(10));

        if (isEditable) {
            JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 0));
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonPanel.setMaximumSize(new Dimension(350, 30));

            btnVer = new JButton("Ver");
            btnEditar = new JButton("Editar");
            btnEliminar = new JButton("Eliminar");

            btnVer.addActionListener(e -> mostrarDetalles());

            buttonPanel.add(btnVer);
            buttonPanel.add(btnEditar);
            buttonPanel.add(btnEliminar); // Listeners will be added by parent

            contentPanel.add(buttonPanel);
        } else {
            // Botón para más detalles (Comportamiento original)
            JButton btnVerMas = new JButton("Ver más detalles");
            btnVerMas.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnVerMas.addActionListener(e -> mostrarDetalles());
            contentPanel.add(btnVerMas);
        }

        add(contentPanel, BorderLayout.CENTER);
    }

    public void addEliminarListener(java.awt.event.ActionListener l) {
        if (btnEliminar != null) {
            btnEliminar.addActionListener(l);
        }
    }

    public void addEditarListener(java.awt.event.ActionListener l) {
        if (btnEditar != null) {
            btnEditar.addActionListener(l);
        }
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

        detalles.append("\nBENEFICIOS:\n").append(oferta.getBeneficios()).append("\n\n");
        detalles.append("CONSULTAS:\n").append(oferta.getConsultas());

        textArea.setText(detalles.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        detallesDialog.add(scrollPane);

        detallesDialog.setVisible(true);
    }
}