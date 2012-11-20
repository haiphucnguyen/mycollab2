package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.ui.data.ValueFactory;


public class CountryComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public CountryComboBox() {
		loadData(ValueFactory.getCountryList());
	}
}
