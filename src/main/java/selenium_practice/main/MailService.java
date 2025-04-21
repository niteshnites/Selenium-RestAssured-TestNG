package selenium_practice.main;

import javax.mail.*;
import javax.mail.internet.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class MailService {

    public static void main(String[] args) {
        sendMail();
    }

    public static void sendMail() {
        try {
            Properties configProps = new Properties();
            configProps.load(Files.newInputStream(Paths.get("src/main/resources/config.properties")));

            final String username = configProps.getProperty("username");
            final String encodedPassword = configProps.getProperty("password");
            final String password = new String(Base64.getDecoder().decode(encodedPassword), StandardCharsets.UTF_8);
            final String to = configProps.getProperty("to");

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Test Execution Results");

            // Email body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hi Team,\n\nPlease find the attached PDF file for today's execution results.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
