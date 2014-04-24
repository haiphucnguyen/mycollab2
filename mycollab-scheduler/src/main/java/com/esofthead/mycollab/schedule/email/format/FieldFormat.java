package com.esofthead.mycollab.schedule.email.format;

import com.esofthead.mycollab.core.MyCollabException;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 * @param <T>
 */
public abstract class FieldFormat<T> {

	public static enum Type {
		DEFAULT, DATE, DATE_TIME, CURRENCY
	}

	protected String displayName;

	public FieldFormat(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	abstract String formatField(T value, String timeZone);

	public static FieldFormat<?> createFieldFormat(Type fieldType,
			String displayName) {
		if (fieldType == Type.DEFAULT) {
			return new DefaultFieldFormat(displayName);
		} else if (fieldType == Type.DATE) {
			return new DateFieldFormat(displayName);
		} else if (fieldType == Type.DATE_TIME) {
			return new DateTimeFieldFormat(displayName);
		} else if (fieldType == Type.CURRENCY) {
			return new CurrencyFieldFormat(displayName);
		} else {
			throw new MyCollabException("Do not support field type "
					+ fieldType);
		}
	}
}
