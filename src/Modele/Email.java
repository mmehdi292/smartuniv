package Modele;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	final String username = "smartuniv2";
	final String password = "MqvqCxDmduN8JJv";
	final String frommail = "smartuniv2@gmail.com";
	String subject;
	String textmessage;

	public void initMessageTypeToken(String pageContext,String Token) {
		subject="Récupération de mot de passe";
		textmessage= pageContext+"/recupererPass?token="+Token;

	}
	public void sendCode(String Token,String email,String pageContext) {
		
		Properties Properties = new Properties();
		Properties.put("mail.smtp.auth", "true");
		Properties.put("mail.smtp.starttls.enable", "true");
		Properties.put("mail.smtp.host", "smtp.gmail.com");
		Properties.put("mail.smtp.port", "587");
		initMessageTypeToken(pageContext,Token);
		Session session = Session.getDefaultInstance(Properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(frommail));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));	
			message.setSubject(subject);
			message.setContent(textmessage,"text/html");
			Transport.send(message);
			
			
			
		}
		catch(MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	
	
}
	

