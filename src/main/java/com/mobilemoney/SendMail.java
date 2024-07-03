package com.mobilemoney;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    private String emailAdmin;
    private String password;
    private String host;
    private String port;

    public SendMail() {

        this.emailAdmin = "mahefatb@gmail.com";
        this.password = "mvexqrnoyuymxpdu";
        this.host = "smtp.gmail.com";
        this.port = "587";
    }

    public boolean EnvoyerMail(String toAddress, String subject, String messageBody) {
        boolean f = false;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAdmin, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAdmin));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(messageBody);
            Transport.send(message);

            f = true;

            //System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();

            //System.out.println("Failed to send email");
        }
        return f;
    }

}
