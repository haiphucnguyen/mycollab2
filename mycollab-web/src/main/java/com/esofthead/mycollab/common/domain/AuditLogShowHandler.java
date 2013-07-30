package com.esofthead.mycollab.common.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.web.AppContext;

public class AuditLogShowHandler {

	protected Map<String, FieldDisplayHandler> fieldsFormat = new HashMap<String, FieldDisplayHandler>();
	public static final String DEFAULT_FIELD = "default";
	public static final String DATE_FIELD = "date";
	public static final String DATETIME_FIELD = "datetime";
	private static Map<String, HistoryFieldFormat> defaultFieldHandlers;

	static {
		defaultFieldHandlers = new HashMap<String, HistoryFieldFormat>();
		defaultFieldHandlers
				.put(DEFAULT_FIELD, new DefaultHistoryFieldFormat());
		defaultFieldHandlers.put(DATE_FIELD, new DateHistoryFieldFormat());
	}

	public void generateFieldDisplayHandler(String fieldname, String displayName) {
		fieldsFormat.put(fieldname, new FieldDisplayHandler(displayName));
	}

	public void generateFieldDisplayHandler(String fieldname,
			String displayName, HistoryFieldFormat format) {
		fieldsFormat.put(fieldname,
				new FieldDisplayHandler(displayName, format));
	}

	public void generateFieldDisplayHandler(String fieldname,
			String displayName, String formatName) {
		fieldsFormat.put(fieldname, new FieldDisplayHandler(displayName,
				defaultFieldHandlers.get(formatName)));
	}

	public String generateChangeSet(SimpleAuditLog auditLog) {
		StringBuffer str = new StringBuffer("<p>");
		List<AuditChangeItem> changeItems = auditLog.getChangeItems();
		if (changeItems != null && changeItems.size() > 0) {
			str.append("<ul>");
			for (int i = 0; i < changeItems.size(); i++) {
				AuditChangeItem item = changeItems.get(i);
				String fieldName = item.getField();
				FieldDisplayHandler fieldDisplayHandler = fieldsFormat
						.get(fieldName);
				if (fieldDisplayHandler != null) {
					str.append("<li>");
					str.append(fieldDisplayHandler.getDisplayName())
							.append(": ")
							.append("<i>")
							.append(fieldDisplayHandler.getFormat()
									.formatField(item.getOldvalue()))
							.append("</i>")
							.append("&nbsp; &rarr; &nbsp; ")
							.append("<i>")
							.append(fieldDisplayHandler.getFormat()
									.formatField(item.getNewvalue()))
							.append("</i>");
					str.append("</li>");
				}

			}
			str.append("</ul>");
		}
		str.append("</p>");
		return str.toString();
	}

	private static class FieldDisplayHandler {

		private String displayName;
		private HistoryFieldFormat format;

		public FieldDisplayHandler(String displayName) {
			this(displayName, new DefaultHistoryFieldFormat());
		}

		public FieldDisplayHandler(String displayName, HistoryFieldFormat format) {
			this.displayName = displayName;
			this.format = format;
		}

		public String getDisplayName() {
			return displayName;
		}

		public HistoryFieldFormat getFormat() {
			return format;
		}
	}

	public static interface HistoryFieldFormat {

		String formatField(String value);
	}

	public static class DefaultHistoryFieldFormat implements HistoryFieldFormat {

		@Override
		public String formatField(String value) {
			if (value != null && !value.trim().equals("")) {
				return (value.length() > 200) ? (value.substring(0, 200) + "...")
						: value;
			} else {
				return "<Null>";
			}
		}
	}

	public static class DateHistoryFieldFormat implements HistoryFieldFormat {

		@Override
		public String formatField(String value) {
			if (value != null && !value.trim().equals("")) {
				String formatW3C = "yyyy-MM-dd'T'HH:mm:ss";
				Date formatDate = DateTimeUtils.getDateByStringWithFormat(
						value, formatW3C);
				return AppContext.formatDate(formatDate);
			} else {
				return "<Null>";
			}

		}
	}
}
