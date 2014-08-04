package com.esofthead.mycollab.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.service.CurrencyService;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.HistoryFieldFormat;
import com.esofthead.mycollab.vaadin.ui.LabelHTMLDisplayWidget;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.3
 *
 */
public class FieldGroupFomatter {
	private static Logger log = LoggerFactory
			.getLogger(FieldGroupFomatter.class);

	private static Map<String, HistoryFieldFormat> defaultFieldHandlers;

	public static final String DEFAULT_FIELD = "default";
	public static final String DATE_FIELD = "date";
	public static final String DATETIME_FIELD = "datetime";
	public static final String CURRENCY_FIELD = "currency";

	protected Map<String, FieldDisplayHandler> fieldsFormat = new HashMap<String, FieldDisplayHandler>();

	static {
		defaultFieldHandlers = new HashMap<String, HistoryFieldFormat>();
		defaultFieldHandlers
				.put(DEFAULT_FIELD, new DefaultHistoryFieldFormat());
		defaultFieldHandlers.put(DATE_FIELD, new DateHistoryFieldFormat());
		defaultFieldHandlers.put(DATETIME_FIELD,
				new DateTimeHistoryFieldFormat());
		defaultFieldHandlers.put(CURRENCY_FIELD,
				new CurrencyHistoryFieldFormat());
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

	public static class DefaultHistoryFieldFormat implements HistoryFieldFormat {

		@Override
		public Component formatField(String value) {
			LabelHTMLDisplayWidget lbHtml = new LabelHTMLDisplayWidget(value);
			lbHtml.setWidth("90%");
			return lbHtml;
		}
	}

	public static class DateHistoryFieldFormat implements HistoryFieldFormat {

		@Override
		public Component formatField(String value) {
			Date formatDate = DateTimeUtils.convertDateByFormatW3C(value);
			return new Label(AppContext.formatDate(formatDate));
		}
	}

	public static class DateTimeHistoryFieldFormat implements
			HistoryFieldFormat {

		@Override
		public Component formatField(String value) {
			if (value != null && !value.trim().equals("")) {
				Date formatDate = DateTimeUtils.convertDateByFormatW3C(value);
				SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(formatDate);
				int timeFormat = calendar.get(Calendar.AM_PM);
				if (timeFormat == 1) {
					calendar.add(Calendar.HOUR_OF_DAY, -12);
				}
				String dateStr = simpleDateTimeFormat
						.format(calendar.getTime())
						+ ((timeFormat == 0) ? " AM" : " PM");
				return new Label(dateStr);
			} else {
				return new Label();
			}
		}
	}

	public static class CurrencyHistoryFieldFormat implements
			HistoryFieldFormat {

		@Override
		public Component formatField(String value) {
			if (value != null && !"".equals(value)) {
				try {
					Integer currencyid = Integer.parseInt(value);
					CurrencyService currencyService = ApplicationContextUtil
							.getSpringBean(CurrencyService.class);
					Currency currency = currencyService.getCurrency(currencyid);
					return new Label(currency.getSymbol());
				} catch (Exception e) {
					log.error("Error while get currency id" + value, e);
					return new Label("");
				}
			}

			return new Label("");
		}
	}

	public static class I18nHistoryFieldFormat implements HistoryFieldFormat {

		private Class<? extends Enum> enumCls;

		public I18nHistoryFieldFormat(Class<? extends Enum> enumCls) {
			this.enumCls = enumCls;
		}

		@Override
		public Component formatField(String value) {
			try {
				if (value != null && !"".equals(value)) {
					return new Label(AppContext.getMessage(Enum.valueOf(
							enumCls, value)));
				}

				return new Label("");
			} catch (Exception e) {
				return new Label(value);
			}
		}

	}
}
