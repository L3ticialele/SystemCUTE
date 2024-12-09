package br.cefetmg.space.view;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void enviarEmail(String destinatario, String assunto, String corpo) {
  
        String host = "smtp.gmail.com";
        String port = "587";  // Porta TLS
        String username = "matheusamaral752@gmail.com"; 
        String password = "";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        }); 

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); 
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); 
            message.setSubject(assunto); 
            message.setText(corpo); 

            Transport.send(message);
            System.out.println("E-mail enviado com sucesso para " + destinatario);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao enviar e-mail.");
        }
    }
}
