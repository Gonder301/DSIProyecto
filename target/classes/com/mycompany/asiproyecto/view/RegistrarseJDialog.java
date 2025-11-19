package com.mycompany.asiproyecto.view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import java.awt.*;
import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.model.*;
import com.mycompany.asiproyecto.controller.RegistrarseController;
import java.awt.CardLayout;
import java.time.LocalDate;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;

public class RegistrarseJDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistrarseJDialog.class.getName());
    
    //Controlador
    RegistrarseController rc;
    
    DatePicker datePickerA;
    /**
     * Creates new form registrarseDialog
     */
    public RegistrarseJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        rc = new RegistrarseController();
        
        //Selector de fecha de nacimiento de Alumno
        DatePickerSettings dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("dd-MM-yyyy");
        datePickerA = new DatePicker(dateSettings);
        datePickerA.setDate(LocalDate.of(2000, 1, 1));
        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) estudiantePanel.getLayout();
        layout.replace(datePickerPlaceholderA, datePickerA);
        estudiantePanel.revalidate();
        estudiantePanel.repaint();
    
        restriccionNumeros(dniTextFieldA, 8);
        restriccionLetras(nombresTextFieldA);
        restriccionLetras(apellidoTextFieldA);
        restriccionLetras(docenteCargoTextFieldA);
        restriccionNumeros(codigoTextFieldA, 8);
        
        restriccionLetras(nombresTextFieldP);
        restriccionLetras(apellidoTextFieldP);
        restriccionNumeros(dniTextFieldP, 8);
    }

    private void restriccionLetras(JTextField tf) {
        AbstractDocument adoc = (AbstractDocument) tf.getDocument();
        adoc.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && !text.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")) {
                    return;
                }
                
                super.replace(fb, offset, length, text, attrs);
            }
        });
    }
    
    private void restriccionNumeros(JTextField tf, int maxLenght) {
        AbstractDocument adoc = (AbstractDocument) tf.getDocument();
        adoc.setDocumentFilter(new DocumentFilter() {
            
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && !text.matches("\\d+")) {
                    return;
                }
                int currentLength = fb.getDocument().getLength();
                int overLimit = (currentLength + text.length()) - length - maxLenght;
                
                if (overLimit <= 0) {
                    super.replace(fb, offset, length, text, attrs);
                }
                else {
                    String cutDownText = text.substring(0, text.length() - overLimit);
                    super.replace(fb, offset, length, cutDownText, attrs);
                }
            }
        });
    }

    //Mover a RegistrarseService
    public Alumno obtenerAlumnoDeForm() {
        Alumno a = new Alumno();
        a.setNombresAlumno(nombresTextFieldA.getText());
        a.setApellidosAlumno(apellidoTextFieldA.getText());
        a.setDni(dniTextFieldA.getText());
        a.setGenero((String)generoComboBoxA.getSelectedItem());
        a.setFechaNacimiento(datePickerA.getDate());
        a.setCodigo(codigoTextFieldA.getText());
        a.setCarrera((String)carreraComboBoxA.getSelectedItem());
        a.setCurso(cursoTextFieldA.getText());
        a.setDocenteACargo(docenteCargoTextFieldA.getText());
        a.setCorreoElectronico(correoTextFieldA.getText());
        return a;
    }
    
    public Profesor obtenerProfesorDeForm() {
        Profesor p = new Profesor();
        p.setNombresProfesor(nombresTextFieldP.getText());
        p.setApellidosProfesor(apellidoTextFieldP.getText());
        p.setDni(dniTextFieldP.getText());
        p.setCorreoInstitucional(correoTextFieldP.getText());
        p.setCarrera((String)carreraComboBoxP.getSelectedItem());
        p.setCodigoCurso(codigoCursoTextFieldP.getText());
        p.setNombreCurso(cursoTextFieldP.getText());
        return p;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonEstudiante = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botonEmpresa = new javax.swing.JButton();
        botonProfesor = new javax.swing.JButton();
        cardHolderPanel = new javax.swing.JPanel();
        estudiantePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombresTextFieldA = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellidoTextFieldA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        codigoTextFieldA = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dniTextFieldA = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        correoTextFieldA = new javax.swing.JTextField();
        generoComboBoxA = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        botonEstudianteSiguente = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        carreraComboBoxA = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cursoTextFieldA = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        docenteCargoTextFieldA = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        contraPasswordFieldA1 = new javax.swing.JPasswordField();
        contraPasswordFieldA2 = new javax.swing.JPasswordField();
        msgRegistrarseA = new javax.swing.JLabel();
        datePickerPlaceholderA = new javax.swing.JTextField();
        empresaPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel30 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        profesorPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        nombresTextFieldP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        apellidoTextFieldP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        dniTextFieldP = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        correoTextFieldP = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        codigoCursoTextFieldP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        carreraComboBoxP = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        cursoTextFieldP = new javax.swing.JTextField();
        msgRegistrarseP = new javax.swing.JLabel();
        contraPasswordFieldP2 = new javax.swing.JPasswordField();
        contraPasswordFieldP1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        botonEstudiante.setBackground(new java.awt.Color(255, 204, 0));
        botonEstudiante.setText("Estudiante");
        botonEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstudianteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setText("Crear Cuenta");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Selecciona el tipo de cuenta que deseas crear");

        botonEmpresa.setBackground(new java.awt.Color(126, 126, 126));
        botonEmpresa.setText("Empresa");
        botonEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmpresaActionPerformed(evt);
            }
        });

        botonProfesor.setBackground(new java.awt.Color(126, 126, 126));
        botonProfesor.setText("Profesor");
        botonProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonProfesorActionPerformed(evt);
            }
        });

        cardHolderPanel.setLayout(new java.awt.CardLayout());

        estudiantePanel.setPreferredSize(new java.awt.Dimension(500, 450));

        jLabel3.setText("Nombres");

        jLabel4.setText("Apellidos");

        jLabel5.setText("Código de estudiante");

        jLabel6.setText("DNI");

        jLabel7.setText("Genero");

        jLabel8.setText("Correo institucional");

        generoComboBoxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino", "No especifico" }));

        jLabel9.setText("Contraseña");

        jLabel10.setText("Confirmar contraseña");

        botonEstudianteSiguente.setBackground(new java.awt.Color(0, 51, 255));
        botonEstudianteSiguente.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        botonEstudianteSiguente.setForeground(new java.awt.Color(255, 255, 255));
        botonEstudianteSiguente.setText("Siguente");
        botonEstudianteSiguente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstudianteSiguenteActionPerformed(evt);
            }
        });

        jLabel19.setText("Carrera");

        carreraComboBoxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingeniería de Sistemas", "Ingeniería de Software", "Ciencias de la Computación" }));

        jLabel21.setText("Curso");

        jLabel22.setText("Docente a cargo");

        jLabel31.setText("Fecha de nacimiento");

        msgRegistrarseA.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout estudiantePanelLayout = new javax.swing.GroupLayout(estudiantePanel);
        estudiantePanel.setLayout(estudiantePanelLayout);
        estudiantePanelLayout.setHorizontalGroup(
            estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estudiantePanelLayout.createSequentialGroup()
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(estudiantePanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addGroup(estudiantePanelLayout.createSequentialGroup()
                                .addComponent(codigoTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(correoTextFieldA))
                            .addGroup(estudiantePanelLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(botonEstudianteSiguente, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(contraPasswordFieldA1)
                            .addComponent(contraPasswordFieldA2)
                            .addGroup(estudiantePanelLayout.createSequentialGroup()
                                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, estudiantePanelLayout.createSequentialGroup()
                                        .addComponent(dniTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(datePickerPlaceholderA))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, estudiantePanelLayout.createSequentialGroup()
                                        .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(carreraComboBoxA, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cursoTextFieldA)
                                            .addGroup(estudiantePanelLayout.createSequentialGroup()
                                                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel21)
                                                    .addComponent(jLabel8))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, estudiantePanelLayout.createSequentialGroup()
                                        .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(nombresTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(apellidoTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel31))))
                                .addGap(18, 18, 18)
                                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(generoComboBoxA, 0, 130, Short.MAX_VALUE)
                                    .addComponent(jLabel22)
                                    .addComponent(docenteCargoTextFieldA)))))
                    .addGroup(estudiantePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(msgRegistrarseA)))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        estudiantePanelLayout.setVerticalGroup(
            estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estudiantePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(estudiantePanelLayout.createSequentialGroup()
                        .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombresTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellidoTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(estudiantePanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generoComboBoxA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datePickerPlaceholderA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carreraComboBoxA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cursoTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(docenteCargoTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(estudiantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correoTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contraPasswordFieldA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contraPasswordFieldA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(botonEstudianteSiguente)
                .addGap(30, 30, 30)
                .addComponent(msgRegistrarseA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardHolderPanel.add(estudiantePanel, "estudianteCard");

        empresaPanel.setPreferredSize(new java.awt.Dimension(500, 450));

        jLabel16.setText("Nombre de la empresa");

        jLabel25.setText("RUC");

        jLabel26.setText("Nombre del contacto");

        jLabel27.setText("Correo corporativo");

        jLabel28.setText("Teléfono");

        jLabel29.setText("Contraseña");

        jLabel30.setText("Confirmar contraseña");

        jButton1.setBackground(new java.awt.Color(0, 51, 255));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Siguente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empresaPanelLayout = new javax.swing.GroupLayout(empresaPanel);
        empresaPanel.setLayout(empresaPanelLayout);
        empresaPanelLayout.setHorizontalGroup(
            empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, empresaPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(143, 143, 143))
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField2)
                    .addComponent(jPasswordField1)
                    .addGroup(empresaPanelLayout.createSequentialGroup()
                        .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel27)
                            .addComponent(jTextField13)
                            .addComponent(jTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField18)
                            .addComponent(jTextField21, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addGroup(empresaPanelLayout.createSequentialGroup()
                                .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel25))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(empresaPanelLayout.createSequentialGroup()
                        .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        empresaPanelLayout.setVerticalGroup(
            empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empresaPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(empresaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton1)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        cardHolderPanel.add(empresaPanel, "empresaCard");

        profesorPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        jLabel11.setText("Nombres");

        jLabel12.setText("Apellidos");

        jLabel13.setText("DNI");

        jLabel14.setText("Correo Institucional");

        jLabel15.setText("Código de Curso");

        jLabel17.setText("Contraseña");

        jLabel18.setText("Confirmar Contraseña");

        jButton5.setBackground(new java.awt.Color(0, 51, 255));
        jButton5.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Siguiente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel23.setText("Carrera");

        carreraComboBoxP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingeniería de Sistemas", "Ingeniería de Software", "Ciencias de la Computación" }));

        jLabel24.setText("Curso");

        javax.swing.GroupLayout profesorPanelLayout = new javax.swing.GroupLayout(profesorPanel);
        profesorPanel.setLayout(profesorPanelLayout);
        profesorPanelLayout.setHorizontalGroup(
            profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profesorPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(msgRegistrarseP)
                    .addComponent(jLabel23)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addGroup(profesorPanelLayout.createSequentialGroup()
                        .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(carreraComboBoxP, javax.swing.GroupLayout.Alignment.LEADING, 0, 232, Short.MAX_VALUE)
                                .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(dniTextFieldP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nombresTextFieldP, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel13)))
                            .addGroup(profesorPanelLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel24)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(apellidoTextFieldP)
                            .addComponent(correoTextFieldP, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
                    .addGroup(profesorPanelLayout.createSequentialGroup()
                        .addComponent(codigoCursoTextFieldP, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cursoTextFieldP))
                    .addComponent(contraPasswordFieldP2)
                    .addComponent(contraPasswordFieldP1))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        profesorPanelLayout.setVerticalGroup(
            profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profesorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(profesorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombresTextFieldP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellidoTextFieldP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(profesorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(32, 32, 32)))
                .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniTextFieldP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correoTextFieldP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carreraComboBoxP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(profesorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoCursoTextFieldP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cursoTextFieldP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contraPasswordFieldP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contraPasswordFieldP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(msgRegistrarseP)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        cardHolderPanel.add(profesorPanel, "profesorCard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cardHolderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(botonEstudiante)
                                .addGap(72, 72, 72)
                                .addComponent(botonEmpresa)
                                .addGap(70, 70, 70)
                                .addComponent(botonProfesor))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEstudiante)
                    .addComponent(botonEmpresa)
                    .addComponent(botonProfesor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardHolderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEstudianteActionPerformed
        CardLayout cl = (CardLayout) cardHolderPanel.getLayout();
        cl.show(cardHolderPanel, "estudianteCard");
        botonEstudiante.setBackground(Colores.botonSelect);
        botonProfesor.setBackground(Colores.botonDefault);
        botonEmpresa.setBackground(Colores.botonDefault);
    }//GEN-LAST:event_botonEstudianteActionPerformed

    private void botonProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonProfesorActionPerformed
        CardLayout cl = (CardLayout) cardHolderPanel.getLayout();
        cl.show(cardHolderPanel, "profesorCard");
        botonEstudiante.setBackground(Colores.botonDefault);
        botonProfesor.setBackground(Colores.botonSelect);
        botonEmpresa.setBackground(Colores.botonDefault);
    }//GEN-LAST:event_botonProfesorActionPerformed

    private void botonEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmpresaActionPerformed
        CardLayout cl = (CardLayout) cardHolderPanel.getLayout();
        cl.show(cardHolderPanel, "empresaCard");
        botonEstudiante.setBackground(Colores.botonDefault);
        botonProfesor.setBackground(Colores.botonDefault);
        botonEmpresa.setBackground(Colores.botonSelect);
    }//GEN-LAST:event_botonEmpresaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonEstudianteSiguenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEstudianteSiguenteActionPerformed
        rc.procesarRegistroAlumno(this);
    }//GEN-LAST:event_botonEstudianteSiguenteActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        rc.procesarRegistroProfesor(this);
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegistrarseJDialog dialog = new RegistrarseJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField apellidoTextFieldA;
    public javax.swing.JTextField apellidoTextFieldP;
    private javax.swing.JButton botonEmpresa;
    private javax.swing.JButton botonEstudiante;
    private javax.swing.JButton botonEstudianteSiguente;
    private javax.swing.JButton botonProfesor;
    private javax.swing.JPanel cardHolderPanel;
    private javax.swing.JComboBox<String> carreraComboBoxA;
    private javax.swing.JComboBox<String> carreraComboBoxP;
    public javax.swing.JTextField codigoCursoTextFieldP;
    public javax.swing.JTextField codigoTextFieldA;
    public javax.swing.JPasswordField contraPasswordFieldA1;
    public javax.swing.JPasswordField contraPasswordFieldA2;
    public javax.swing.JPasswordField contraPasswordFieldP1;
    public javax.swing.JPasswordField contraPasswordFieldP2;
    public javax.swing.JTextField correoTextFieldA;
    public javax.swing.JTextField correoTextFieldP;
    public javax.swing.JTextField cursoTextFieldA;
    public javax.swing.JTextField cursoTextFieldP;
    private javax.swing.JTextField datePickerPlaceholderA;
    public javax.swing.JTextField dniTextFieldA;
    public javax.swing.JTextField dniTextFieldP;
    public javax.swing.JTextField docenteCargoTextFieldA;
    private javax.swing.JPanel empresaPanel;
    private javax.swing.JPanel estudiantePanel;
    private javax.swing.JComboBox<String> generoComboBoxA;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    public javax.swing.JLabel msgRegistrarseA;
    public javax.swing.JLabel msgRegistrarseP;
    public javax.swing.JTextField nombresTextFieldA;
    public javax.swing.JTextField nombresTextFieldP;
    private javax.swing.JPanel profesorPanel;
    // End of variables declaration//GEN-END:variables
}
