package com.mycompany.asiproyecto.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailService {
    public static boolean enviarCodigoDeVerificacion(String correo, String token) {
        boolean mensajeEnviado = false;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
        String username = "dsi.proyecto.g3@gmail.com";
        String password = "ftpaubbxsklodwwi";
        
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dsi.proyecto.g3@gmail.com"));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(correo)
            );
            message.setSubject("Código de Verificación");
            message.setText("Su código de verificación es : " + token);

            Transport.send(message);
            
            mensajeEnviado = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        return mensajeEnviado;
    }
    
    
}
