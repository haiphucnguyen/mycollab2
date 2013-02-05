package com.esofthead.mycollab.utils;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingUtils {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
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
                if ((email.indexOf("<") > -1) && (email.indexOf(">") == (email.length() - 1)) && (email.indexOf(">") > email.indexOf("<"))) {
                    String name = email.substring(0, email.indexOf("<"));
                    String mail = email.substring(email.indexOf("<") + 1, email.indexOf(">"));
                    matcherEmail = pattern.matcher(mail);
                    if (matcherEmail.matches() && !name.contains("@")) {
                        mailField = new MailRecipientField(mail, name);
                    }
                } else {
                    matcherEmail = pattern.matcher(email);
                    if (matcherEmail.matches()) {
                        mailField = new MailRecipientField(email);
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

    public static class InvalidEmailException extends Exception {

        private static final long serialVersionUID = 1L;

        public InvalidEmailException(String msg) {
            super(msg);
        }
    }
}
