package com.esofthead.mycollab.schedule.email.format;

import com.hp.gagawa.java.elements.Span;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class DefaultFieldFormat extends FieldFormat<String> {

	public DefaultFieldFormat(String displayName) {
		super(displayName);
	}

	@Override
	public String formatField(String value, String timeZone) {
		if (value == null)
			return new Span().write();

		return new Span().appendText(value).write();
	}
}
