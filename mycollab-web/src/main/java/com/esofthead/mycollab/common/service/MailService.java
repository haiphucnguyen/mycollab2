package com.esofthead.mycollab.common.service;

import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.MailRecipientField;

@Service
public class MailService {
	public void sendMail(List<MailRecipientField> toFields,
			List<MailRecipientField> ccFields,
			List<MailRecipientField> bccFields, String subject, String content)
			throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("stmp.gmail.com");
		email.addTo("haiphucnguyen@gmail.com", "John Doe");
		email.setFrom("hainguyen@esofthead.com", "Me");
		email.setSubject("Test email with inline image");
		email.setHtmlMsg("<html>aaa</html>");
		email.send();
	}

	public static void main(String[] args) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"haiphucnguyen@gmail.com", "DtmLinh80");
					}
				});
		email.setMailSession(session);

		email.addTo("haiphucnguyen@gmail.com", "John Doe");
		email.setFrom("haiphucnguyen@gmail.com", "Me");
		email.setSubject("Test email with inline image");
		email.setHtmlMsg("<html>aaa</html>");
		email.send();
	}
}
