package com.esofthead.mycollab.common.service.ibatis;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Override
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

	public static void main(String[] args) throws EmailException, IOException {
		HtmlEmail email = new HtmlEmail();
		final Properties props = new Properties();
		props.load(MailServiceImpl.class.getClassLoader().getResourceAsStream(
				"resources.properties"));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", props.get("mail.smtphost"));
		props.put("mail.smtp.port", props.get("mail.port"));

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(props
								.getProperty("mail.username"), props
								.getProperty("mail.password"));
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
