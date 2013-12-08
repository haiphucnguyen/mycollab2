/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;

public class DateTimePicker<B> extends CustomField {
	private static final long serialVersionUID = 1L;

	public static final long ONE_MINUTE_IN_MILLIS = 60000;

	private PopupDateField popupDateField;
	private HourPickerComboBox hourPickerComboBox;
	private MinutePickerComboBox minutePickerComboBox;
	private ValueComboBox timeFormatComboBox;

	private String fieldName;
	private B bean;

	public DateTimePicker(final String fieldName, final B bean) {
		this.fieldName = fieldName;
		this.bean = bean;
	}

	private long calculateMiniSecond(Integer hour, Integer minus,
			String timeFormat) {
		long allMinus = 0;
		if (timeFormat.equals("AM")) {
			allMinus = (((hour == 12) ? 0 : hour) * 60 + minus)
					* ONE_MINUTE_IN_MILLIS;
		} else if (timeFormat.equals("PM")) {
			allMinus = (((hour == 12) ? 12 : hour + 12) * 60 + minus)
					* ONE_MINUTE_IN_MILLIS;
		}
		return allMinus;
	}

	@Override
	public Class<Date> getType() {
		return Date.class;
	}

	@Override
	public Date getValue() {
		if (popupDateField.getValue() == null) {
			return null;
		}
		Date baseDate = DateTimeUtils.trimHMSOfDate((Date) popupDateField
				.getValue());
		Integer hour = Integer.parseInt((String) hourPickerComboBox.getValue());
		Integer minus = Integer.parseInt((String) minutePickerComboBox
				.getValue());
		String timeFormat = (String) timeFormatComboBox.getValue();
		long milliseconds = calculateMiniSecond(hour, minus, timeFormat);
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTimeInMillis(baseDate.getTime() + milliseconds);
		return cal.getTime();
	}

	private class HourPickerComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;
		private final String[] HOURDATA = new String[] { "12", "01", "02",
				"03", "04", "05", "06", "07", "08", "09", "10", "11" };

		public HourPickerComboBox() {
			super();
			setCaption(null);
			this.loadData(HOURDATA);
		}
	}

	private class MinutePickerComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;
		private String[] MINUSDATA = new String[] { "00", "15", "30", "45" };

		public MinutePickerComboBox() {
			super();
			setCaption(null);
			this.loadData(MINUSDATA);
		}
	}

	@Override
	protected Component initContent() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		long min = 0, hrs = 0;
		String timeFormat = "AM";
		try {
			Date date = (Date) PropertyUtils.getProperty(bean, fieldName);
			if (fieldName != null && date != null) {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.setTime(date);
				min = cal.get(java.util.Calendar.MINUTE);
				hrs = cal.get(java.util.Calendar.HOUR);
				timeFormat = (cal.get(java.util.Calendar.AM_PM) == 0) ? "AM"
						: "PM";
			}
			popupDateField = new PopupDateField(null, date);
			popupDateField.setResolution(Resolution.DAY);
			popupDateField.setWidth("130px");
			popupDateField.addValueChangeListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					try {
						PropertyUtils.setProperty(bean, fieldName,
								DateTimePicker.this.getValue());
					} catch (Exception e) {
						throw new MyCollabException(e);
					}
				}
			});
			layout.addComponent(popupDateField);

			hourPickerComboBox = new HourPickerComboBox();
			hourPickerComboBox.setValue((hrs < 10) ? "0" + hrs : "" + hrs);
			hourPickerComboBox.setWidth("50px");
			hourPickerComboBox
					.addValueChangeListener(new ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(
								com.vaadin.data.Property.ValueChangeEvent event) {
							try {
								PropertyUtils.setProperty(bean, fieldName,
										DateTimePicker.this.getValue());
							} catch (Exception e) {
								throw new MyCollabException(e);
							}
						}
					});
			layout.addComponent(hourPickerComboBox);
			minutePickerComboBox = new MinutePickerComboBox();
			minutePickerComboBox.setWidth("50px");
			minutePickerComboBox.setValue((min < 10) ? "0" + min : "" + min);
			minutePickerComboBox
					.addValueChangeListener(new ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(
								com.vaadin.data.Property.ValueChangeEvent event) {
							try {
								PropertyUtils.setProperty(bean, fieldName,
										DateTimePicker.this.getValue());
							} catch (Exception e) {
								throw new MyCollabException(e);
							}
						}
					});
			layout.addComponent(minutePickerComboBox);

			timeFormatComboBox = new ValueComboBox();
			timeFormatComboBox.setWidth("50px");
			timeFormatComboBox.setCaption(null);
			timeFormatComboBox.loadData(new String[] { "AM", "PM" });
			timeFormatComboBox.setNullSelectionAllowed(false);
			timeFormatComboBox.setImmediate(true);
			timeFormatComboBox.setValue(timeFormat);
			timeFormatComboBox
					.addValueChangeListener(new ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(
								com.vaadin.data.Property.ValueChangeEvent event) {
							try {
								PropertyUtils.setProperty(bean, fieldName,
										DateTimePicker.this.getValue());
							} catch (Exception e) {
								throw new MyCollabException(e);
							}
						}
					});
			layout.addComponent(timeFormatComboBox);
			return layout;
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}
}