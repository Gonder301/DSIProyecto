package com.mycompany.asiproyecto.controller;

import com.mycompany.asiproyecto.view.LoginJDialog;
import com.mycompany.asiproyecto.Colores;
import com.mycompany.asiproyecto.Placeholder;
import com.mycompany.asiproyecto.dao.AlumnoDAO;
import com.mycompany.asiproyecto.dao.EmpleadoEmpresaDAO;
import com.mycompany.asiproyecto.dao.PasswordTokenDAO;
import com.mycompany.asiproyecto.dao.ProfesorDAO;
import com.mycompany.asiproyecto.service.LoginService;
import com.mycompany.asiproyecto.model.*;
import com.mycompany.asiproyecto.view.InicioAlumno;
import com.mycompany.asiproyecto.view.InicioEmpleado;
import com.mycompany.asiproyecto.view.InicioProfesor;
import com.mycompany.asiproyecto.view.RegistrarseJDialog;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class LoginController {
    
    public void abrirVentanaRegistrarse(LoginJDialog vista) {
        JFrame framePadre = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(vista);
        vista.dispose();
        RegistrarseJDialog registrarseDialog = new RegistrarseJDialog(framePadre, true);
        registrarseDialog.setLocationRelativeTo(framePadre);
        registrarseDialog.setVisible(true);
    }
    
    public void procesarCambioDePassword(LoginJDialog vista) {
        JDialog dialog = new JDialog(vista, "Recuperación de Contraseña", true);
        dialog.setSize(400, 480);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- VARIABLES COMPARTIDAS (SCOPE) ---
        // Usamos arrays de 1 posición para poder modificar su valor dentro de los
        // Listeners y leerlos en otro botón diferente.
        final int[] idUsuarioRef = {-1};
        final String[] tipoUsuarioRef = {""};

        // --- 1. SECCIÓN CORREO ---
        JLabel lblCorreo = new JLabel("1. Ingrese su correo registrado:");
        JTextField txtCorreo = new JTextField();
        JButton btnEnviarCodigo = new JButton("Enviar código de verificación");

        // --- 2. SECCIÓN CÓDIGO ---
        JLabel lblCodigo = new JLabel("2. Ingrese el código recibido:");
        JTextField txtCodigo = new JTextField();
        txtCodigo.setEnabled(false); 
        JButton btnVerificarCodigo = new JButton("Verificar código");
        btnVerificarCodigo.setEnabled(false); 

        // --- 3. SECCIÓN NUEVA CONTRASEÑA ---
        JLabel lblPass = new JLabel("3. Nueva contraseña:");
        JPasswordField txtPass = new JPasswordField();
        txtPass.setEnabled(false); 

        JLabel lblConfirm = new JLabel("Confirmar contraseña:");
        JPasswordField txtConfirm = new JPasswordField();
        txtConfirm.setEnabled(false);

        JButton btnCambiarPass = new JButton("Cambiar contraseña");
        btnCambiarPass.setEnabled(false);


        // --- LÓGICA DEL BOTÓN ENVIAR CÓDIGO ---
        btnEnviarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText().trim();
                
                if(correo.isEmpty()){
                     JOptionPane.showMessageDialog(dialog, "Por favor ingrese un correo.");
                     return;
                }

                PasswordTokenDAO ptDAO = new PasswordTokenDAO();
                ptDAO.registrarToken(correo); 

                JOptionPane.showMessageDialog(dialog, 
                    "<html>Si el correo existe, se ha enviado un código.<br>" +
                    "Por favor revíselo e ingréselo abajo.</html>");

                txtCorreo.setEditable(false);
                btnEnviarCodigo.setEnabled(false);
                
                txtCodigo.setEnabled(true);
                btnVerificarCodigo.setEnabled(true);
                txtCodigo.requestFocus();
            }
        });

        // --- LÓGICA DEL BOTÓN VERIFICAR CÓDIGO ---
        btnVerificarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoIngresado = txtCodigo.getText().trim();
                
                if (codigoIngresado.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Por favor ingrese el código de verificación.");
                    return;
                }

                PasswordTokenDAO ptDAO = new PasswordTokenDAO();
                
                int idUsuario = -1;
                String tipoUsuarioDetectado = ""; 

                // 1. Validar ALUMNO
                idUsuario = ptDAO.validarToken(codigoIngresado, "ALUMNO");
                if (idUsuario != -1) {
                    tipoUsuarioDetectado = "ALUMNO";
                } 
                // 2. Validar PROFESOR
                else {
                    idUsuario = ptDAO.validarToken(codigoIngresado, "PROFESOR");
                    if (idUsuario != -1) {
                        tipoUsuarioDetectado = "PROFESOR";
                    } 
                    // 3. Validar EMPRESA
                    else {
                        idUsuario = ptDAO.validarToken(codigoIngresado, "EMPRESA");
                        if (idUsuario != -1) {
                            tipoUsuarioDetectado = "EMPRESA";
                        }
                    }
                }

                if (idUsuario != -1) {
                    JOptionPane.showMessageDialog(dialog, "¡Código correcto! Puede cambiar su contraseña.");
                    
                    // --- GUARDAMOS LOS DATOS EN LAS VARIABLES COMPARTIDAS ---
                    idUsuarioRef[0] = idUsuario;
                    tipoUsuarioRef[0] = tipoUsuarioDetectado;

                    // UI Updates
                    txtCodigo.setEditable(false);
                    btnVerificarCodigo.setEnabled(false);

                    txtPass.setEnabled(true);
                    txtConfirm.setEnabled(true);
                    btnCambiarPass.setEnabled(true);
                    txtPass.requestFocus();
                    
                } else {
                    JOptionPane.showMessageDialog(dialog, "Código incorrecto o expirado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- LÓGICA DEL BOTÓN CAMBIAR CONTRASEÑA ---
        btnCambiarPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pass1 = txtPass.getPassword();
                char[] pass2 = txtConfirm.getPassword();
                String sPass1 = new String(pass1);
                String sPass2 = new String(pass2);

                if (sPass1.isEmpty() || sPass2.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Las contraseñas no pueden estar vacías.");
                } else if (!sPass1.equals(sPass2)) {
                    JOptionPane.showMessageDialog(dialog, "Las contraseñas no coinciden.");
                } else {
                    
                    // RECUPERAMOS LOS DATOS GUARDADOS EN EL PASO ANTERIOR
                    int idUsuario = idUsuarioRef[0];
                    String tipoUsuario = tipoUsuarioRef[0];
                    boolean exito = false;

                    // Switch para llamar al DAO correcto según el tipo detectado
                    switch (tipoUsuario) {
                        case "ALUMNO":
                            AlumnoDAO aDAO = new AlumnoDAO();
                            exito = aDAO.cambiarContrasena(idUsuario, pass1);
                            break;
                            
                        case "PROFESOR":
                            ProfesorDAO pDAO = new ProfesorDAO();
                            exito = pDAO.cambiarContrasena(idUsuario, pass1);
                            break;
                            
                        case "EMPRESA":
                            EmpleadoEmpresaDAO eeDAO = new EmpleadoEmpresaDAO();
                            exito = eeDAO.cambiarContrasena(idUsuario, pass1);
                            break;
                    }

                    if (exito) {
                        JOptionPane.showMessageDialog(dialog, "Contraseña actualizada correctamente.");
                        dialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Error al actualizar la contraseña en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Agregamos componentes al panel
        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(btnEnviarCodigo);
        panel.add(new JSeparator());
        
        panel.add(lblCodigo);
        panel.add(txtCodigo);
        panel.add(btnVerificarCodigo);
        panel.add(new JSeparator());

        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(lblConfirm);
        panel.add(txtConfirm);
        panel.add(btnCambiarPass);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(vista);
        dialog.setVisible(true);
    }
    
    public void procesarValidacion(LoginJDialog vista) {
        //Verifica estado de los campos (correo y contra)
        int estado = 0;
        String correo = vista.correoTextField.getText();
        char[] contrasena = vista.contraPasswordField.getPassword();
        
        boolean correoInvalido = (correo == null || correo.trim().isEmpty() || correo.equals(Placeholder.correo));
        boolean contraInvalida = (contrasena == null || contrasena.length == 0 || 
                Arrays.equals(contrasena, Placeholder.contra.toCharArray()));
        
        if (correoInvalido && contraInvalida) {
            estado = 3;
        } else if (correoInvalido) {
            estado = 1;
        } else if (contraInvalida) {
            estado = 2;
        }
        
        //Modifica la vista de acuerdo al estado
        //0 <- todo bien
        //1 <- Correo vacío o con el placeholder
        //2 <- Contraseña vacía o con el placeholder
        //3 <- Correo y Contraseña ...
        switch (estado) {
            case 0:
                String tipoUsuario = (String)vista.tipoUsuarioComboBox.getSelectedItem();
                if (tipoUsuario.equals("Alumno")) {
                    Alumno a = LoginService.consultarAlumnoDB(correo, contrasena);
                    if (a != null) {
                        InicioAlumno inicioAlumno = new InicioAlumno(a);
                        inicioAlumno.setVisible(true);
                        JFrame framePadre = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(vista);
                        framePadre.dispose();
                        vista.dispose();
                    }
                    else {
                        vista.correoTextField.setText("");
                        vista.contraPasswordField.setText("");
                        vista.msgError.setText("Credenciales incorrectas.");
                    }
                } else if (tipoUsuario.equals("Profesor")) {
                    Profesor p = LoginService.consultarProfesorDB(correo, contrasena);
                    if (p != null) {
                        InicioProfesor inicioProfesor = new InicioProfesor(p);
                        inicioProfesor.setVisible(true);
                        JFrame framePadre = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(vista);
                        framePadre.dispose();
                        vista.dispose();
                    }
                    else {
                        vista.correoTextField.setText("");
                        vista.contraPasswordField.setText("");
                        vista.msgError.setText("Credenciales incorrectas.");
                    }
                } else if (tipoUsuario.equals("Empresa")) {
                    EmpleadoEmpresa e = LoginService.consultarEmpleadoEmpresaDB(correo, contrasena);
                    if (e != null) {
                        InicioEmpleado inicioEmpleado = new InicioEmpleado(e);
                        inicioEmpleado.setVisible(true);
                        JFrame framePadre = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(vista);
                        framePadre.dispose();
                        vista.dispose();
                    }
                    else {
                        vista.correoTextField.setText("");
                        vista.contraPasswordField.setText("");
                        vista.msgError.setText("Credenciales incorrectas.");
                    }
                }
                break;
                
            case 1:
                vista.setCorreoBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
                vista.msgError.setText("Por favor ingrese un correo válido.");
                break;
            case 2:
                vista.setContraBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
                vista.msgError.setText("Por favor ingrese su contraseña.");
                break;
            case 3:
                vista.setCorreoBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
                vista.setContraBorder(Colores.TEXTFIELD_BORDER_ERR, 2);
                vista.msgError.setText("Debe ingresar correo y contraseña.");
                break;
        }
    }
}
