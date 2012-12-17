package com.esofthead.mycollab.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.esofthead.mycollab.common.domain.MailRecipientField;

public class ParsingUtils {
	public static List<MailRecipientField> parseEmailField(String emailField)
			throws InvalidEmailException {
		if (emailField == null || emailField.trim().equals("")) {
			return null;
		} else {
			List<MailRecipientField> fields = new ArrayList<MailRecipientField>();
			StringTokenizer emailTokenizer = new StringTokenizer(emailField,
					",");
			while (emailTokenizer.hasMoreElements()) {
				String email = emailTokenizer.nextToken();
				MailRecipientField mailField = new MailRecipientField(email);
				fields.add(mailField);
			}
			return fields;
		}
	}

	public static class InvalidEmailException extends Exception {
		private static final long serialVersionUID = 1L;

		public InvalidEmailException(String msg) {
			super(msg);
		}
	}
}
