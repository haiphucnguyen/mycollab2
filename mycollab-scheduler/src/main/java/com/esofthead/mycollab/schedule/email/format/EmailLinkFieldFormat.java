package com.esofthead.mycollab.schedule.email.format;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.schedule.email.MailContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Span;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class EmailLinkFieldFormat extends FieldFormat {
	private static Logger log = LoggerFactory
			.getLogger(EmailLinkFieldFormat.class);

	public EmailLinkFieldFormat(String fieldName, String displayName) {
		super(fieldName, displayName);
	}

	@Override
	public String formatField(MailContext<?> context) {
		Object wrappedBean = context.getWrappedBean();
		Object value;
		try {
			value = PropertyUtils.getProperty(wrappedBean, fieldName);
			return formatEmail((String) value);
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			log.error("Error", e);
			return new Span().write();
		}
	}

	@Override
	public String formatField(MailContext<?> context, String value) {
		return formatEmail(value);
	}

	private String formatEmail(String value) {
		if (value == null) {
			return new Span().write();
		} else {
			A link = new A();
			link.setStyle("text-decoration: none; color: rgb(36, 127, 211);");
			link.setHref("mailto:" + value.toString());
			link.appendText(value.toString());
			return new Span().appendChild(link).write();
		}
	}

}
