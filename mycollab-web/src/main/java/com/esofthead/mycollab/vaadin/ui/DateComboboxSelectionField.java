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
package com.esofthead.mycollab.vaadin.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DateComboboxSelectionField extends CustomField {

	private ComboBox cboYear;
	private ComboBox cboMonth;
	private ComboBox cboDate;

	private Map<String, Integer> mapNumberMonth = new HashMap<String, Integer>();
	private FieldSelection fieldSelection;

	public DateComboboxSelectionField() {
		this(null);
	}

	public DateComboboxSelectionField(FieldSelection fieldSelection) {
		this.fieldSelection = fieldSelection;
		initUI();
	}

	private void initUI() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		cboMonth = new ComboBox();
		cboMonth.setNullSelectionAllowed(true);
		cboMonth.setImmediate(true);

		addMonthItems();
		cboMonth.select(cboMonth.getItemIds().iterator().next());
		cboMonth.setWidth("117px");
		layout.addComponent(cboMonth);
		layout.setComponentAlignment(cboMonth, Alignment.TOP_CENTER);

		cboDate = new ComboBox();
		cboDate.setNullSelectionAllowed(true);
		cboDate.setImmediate(true);

		addDayItems();
		cboDate.select(cboDate.getItemIds().iterator().next());
		cboDate.setWidth("50px");
		layout.addComponent(cboDate);
		layout.setComponentAlignment(cboDate, Alignment.TOP_CENTER);

		cboYear = new ComboBox();
		cboYear.setNullSelectionAllowed(true);
		cboYear.setImmediate(true);

		addYearItems();
		cboYear.select(cboYear.getItemIds().iterator().next());
		cboYear.setWidth("70px");
		layout.addComponent(cboYear);
		layout.setComponentAlignment(cboYear, Alignment.TOP_CENTER);

		cboMonth.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				fireDateChange();

			}
		});

		cboDate.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				fireDateChange();

			}
		});

		cboYear.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(Property.ValueChangeEvent event) {
				fireDateChange();

			}
		});
		setCompositionRoot(layout);
	}

	private void fireDateChange() {
		if (fieldSelection != null) {
			fieldSelection.fireValueChange(getDate());
		}
	}

	private String formatMonth(String month) {
		SimpleDateFormat monthParse = new SimpleDateFormat("MM");
		SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
		try {
			return monthDisplay.format(monthParse.parse(month));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void addMonthItems() {
		for (int i = 1; i <= 12; i++) {
			cboMonth.addItem(formatMonth(i + ""));
			mapNumberMonth.put(formatMonth(i + ""), i);
		}
	}

	private void addDayItems() {
		for (int i = 1; i <= 31; i++) {
			if (i < 10) {
				cboDate.addItem("0" + i);
			} else {
				cboDate.addItem(i + "");
			}
		}
	}

	private void addYearItems() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		for (int i = (currentYear - 10); i >= 1900; i--) {
			cboYear.addItem(i);
		}
	}

	public void setDate(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			cboDate.select(calendar.get(Calendar.DATE) + "");
			cboMonth.select(formatMonth(calendar.get(Calendar.MONTH) + 1 + ""));
			cboYear.select(calendar.get(Calendar.YEAR));
		} else {
			cboDate.select("");
			cboMonth.select("");
			cboYear.select("");
		}
	}

	public Date getDate() {
		if ((cboDate.getValue() != null) && (cboMonth.getValue() != null)
				&& (cboYear.getValue() != null)) {
			Calendar calendar = Calendar.getInstance();

			calendar.set(Integer.parseInt(cboYear.getValue().toString()),
					mapNumberMonth.get(cboMonth.getValue().toString()) - 1,
					Integer.parseInt(cboDate.getValue().toString()));
			return calendar.getTime();
		} else {
			return null;
		}

	}

	@Override
	public Class<?> getType() {
		return Date.class;
	}
}
