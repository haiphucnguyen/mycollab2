package com.esofthead.mycollab.vaadin.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DateComboboxSelectionField extends CustomField {

	private ComboBox cboYear;
	private ComboBox cboMonth;
	private ComboBox cboDate;

	private Map<String, Integer> mapNumberMonth = new HashMap<String, Integer>();

	public DateComboboxSelectionField() {
		initUI();
	}

	private void initUI() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		cboMonth = new ComboBox();
		cboMonth.setNullSelectionAllowed(false);
		addMonthItems();
		cboMonth.select(cboMonth.getItemIds().iterator().next());
		cboMonth.setWidth("100px");
		layout.addComponent(cboMonth);
		layout.setComponentAlignment(cboMonth, Alignment.TOP_CENTER);

		cboDate = new ComboBox();
		cboDate.setNullSelectionAllowed(false);
		addDayItems();
		cboDate.select(cboDate.getItemIds().iterator().next());
		cboDate.setWidth("50px");
		layout.addComponent(cboDate);
		layout.setComponentAlignment(cboDate, Alignment.TOP_CENTER);

		cboYear = new ComboBox();
		cboYear.setNullSelectionAllowed(false);
		addYearItems();
		cboYear.select(cboYear.getItemIds().iterator().next());
		cboYear.setWidth("70px");
		layout.addComponent(cboYear);
		layout.setComponentAlignment(cboYear, Alignment.TOP_CENTER);

		setCompositionRoot(layout);
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
		}
	}

	public Date getDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(cboYear.getValue().toString()), mapNumberMonth.get(cboMonth.getValue().toString()) - 1,
				Integer.parseInt(cboDate.getValue().toString()));
		return calendar.getTime();
	}

	@Override
	public Class<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
