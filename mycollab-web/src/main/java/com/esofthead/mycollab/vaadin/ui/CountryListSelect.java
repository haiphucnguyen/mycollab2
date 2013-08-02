package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.ui.data.ValueFactory;

public class CountryListSelect extends ValueListSelect {
	private static final long serialVersionUID = 1L;

	public CountryListSelect() {
		loadData(ValueFactory.getCountryList());
	}
}
