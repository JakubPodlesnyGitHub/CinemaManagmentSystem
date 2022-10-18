package Utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailSender {
    public static void createSENDMimeMessage(String messageMail) throws MessagingException {
        Properties prop = new Properties();
        propSettings(prop);
        Session sessionMail = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("example12345r@gmail.com", "frdjjgomxxxskqqf");
            }
        });
        Message message = new MimeMessage(sessionMail);
        message.setFrom(new InternetAddress("example12345r@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(StaticVariables.loggedUser.getMail(), false));
        message.setSubject("Mail Confirmation");
        String msg = "Please enter this code to activate the account.\r\n" + messageMail;
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/plain; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
    private static void propSettings(Properties prop) {
        prop.setProperty("mail.smtp.host", "smtp.gmail.com");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.debug", "true");
        prop.put("mail.store.protocol", "pop3");
        prop.put("mail.transport.protocol", "smtp");
    }
}
