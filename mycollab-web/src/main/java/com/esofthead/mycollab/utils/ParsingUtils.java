package com.esofthead.mycollab.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class ParsingUtils {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static List<MailRecipientField> parseEmailField(String emailField)
			throws InvalidEmailException {

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcherEmail;
		if (emailField == null || emailField.trim().equals("")) {
			throw new InvalidEmailException("The email is not valid!");
		} else {
			List<MailRecipientField> fields = new ArrayList<MailRecipientField>();
			StringTokenizer emailTokenizer = new StringTokenizer(emailField,
					",");
			while (emailTokenizer.hasMoreElements()) {
				String email = emailTokenizer.nextToken().trim();
				MailRecipientField mailField = null;
				if ((email.indexOf("<") > -1)
						&& (email.indexOf(">") == (email.length() - 1))
						&& (email.indexOf(">") > email.indexOf("<"))) {
					String name = email.substring(0, email.indexOf("<"));
					String mail = email.substring(email.indexOf("<") + 1,
							email.indexOf(">"));
					matcherEmail = pattern.matcher(mail);
					if (matcherEmail.matches() && !name.contains("@")) {
						mailField = new MailRecipientField(mail, name);
					}
				} else {
					matcherEmail = pattern.matcher(email);
					if (matcherEmail.matches()) {
						mailField = new MailRecipientField(email, email);
					}
				}
				if (mailField != null) {
					fields.add(mailField);
				} else {
					throw new InvalidEmailException("The email is not valid!");
				}
			}
			return fields;
		}
	}

	public static MailRecipientField getMailRecipient(String emailField)
			throws InvalidEmailException {

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcherEmail;
		if (emailField == null || emailField.trim().equals("")) {
			throw new InvalidEmailException("The email is not valid!");
		} else {
			MailRecipientField mailField = null;
			if ((emailField.indexOf("<") > -1)
					&& (emailField.indexOf(">") == (emailField.length() - 1))
					&& (emailField.indexOf(">") > emailField.indexOf("<"))) {
				String name = emailField.substring(0, emailField.indexOf("<"));
				String mail = emailField.substring(emailField.indexOf("<") + 1,
						emailField.indexOf(">"));
				matcherEmail = pattern.matcher(mail);
				if (matcherEmail.matches() && !name.contains("@")) {
					mailField = new MailRecipientField(mail, name);
				}
			} else {
				matcherEmail = pattern.matcher(emailField);
				if (matcherEmail.matches()) {
					mailField = new MailRecipientField(emailField);
				}
			}
			if (mailField != null) {
				return mailField;
			} else {
				throw new InvalidEmailException("The email is not valid!");
			}
		}
	}

	public static void main(String[] args) {
		try {
			Configuration configuration = new ConfigurationBuilder()
					.withProperties(SiteConfiguration.getCacheProperties())
					.build();
			RemoteCacheManager instance = new RemoteCacheManager(configuration,
					true);
			RemoteCache<Object, Object> cache = instance.getCache();
			cache.put("a", 1);
			cache.put("a-1", 1);
			System.out.println(cache.keySet().size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class InvalidEmailException extends Exception {

		private static final long serialVersionUID = 1L;

		public InvalidEmailException(String msg) {
			super(msg);
		}
	}
}
