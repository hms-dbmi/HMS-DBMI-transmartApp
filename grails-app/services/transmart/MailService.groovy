package transmart

import grails.util.Holders

import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.BodyPart
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.PasswordAuthentication
import javax.mail.SendFailedException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class MailService {

	private static final log = org.apache.commons.logging.LogFactory.getLog(this)

	static Properties props = System.getProperties();

	// SMTP configuration properties
	static String host = Holders.config.edu.harvard.transmart.email.server
	static String user = Holders.config.edu.harvard.transmart.email.user
	static String password = Holders.config.edu.harvard.transmart.email.password
	static String from = Holders.config.edu.harvard.transmart.email.from
	static String port = Holders.config.edu.harvard.transmart.email.port
	static String timeout = Holders.config.edu.harvard.transmart.email.smtp_timeout
	static Boolean isDebug = Holders.config.edu.harvard.transmart.email.isdebug?Holders.config.edu.harvard.transmart.email.isdebug.equalsIgnoreCase('true'):false

	public static void sendMail(params) {

		if (params.to == null) {
			throw new RuntimeException("Could not determine TO field for e-mail being sent.")
		}

		if (params.subject == null) {
			throw new RuntimeException("Could not determine SUBJECT field for e-mail being sent.")
		}

		if (params.body == null) {
			throw new RuntimeException("Could not determine BODY field for e-mail being sent.")
		}

		initializeSMTPEnvironment()

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});
		session.setDebug(isDebug);

		// Create base message
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(params.to));
		message.setSubject(params.subject);

		// Check if we need to send it to others
		if (params.cc != null) {
			message.setRecipients(Message.RecipientType.CC, params.cc);
		}

		// Build the message body and attachment if any
		if (params.attachment != null) {
			// Do a multipart message with an attachment
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(params.body.toString(), 'text/html')

			Multipart multipart = new MimeMultipart()
			multipart.addBodyPart(messageBodyPart)

			messageBodyPart = new MimeBodyPart()
			messageBodyPart.setHeader('Content-type', 'application/pdf')
			messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(params.filepath)))
			messageBodyPart.setFileName(params.filename);
			multipart.addBodyPart(messageBodyPart)
			message.setContent(multipart);
		} else {
			// No attachment, just a simple e-mail with body content.
			message.setContent(params.body.toString(), "text/html");
		}

		// Send the message, use Properties to use host/port/user/password
		try {
			Transport tr = session.getTransport("smtp");
			tr.connect();
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
			log.info "Successfully sent mail '"+params.subject+"' to '"+params.to+"'"
		} catch (SendFailedException sfe) {
			log.error "Failed to send mail '"+params.subject+"' to '"+params.to+"' because "+sfe.getMessage()
		}
	}

	private static initializeSMTPEnvironment() {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.timeout", timeout);
	}
}
