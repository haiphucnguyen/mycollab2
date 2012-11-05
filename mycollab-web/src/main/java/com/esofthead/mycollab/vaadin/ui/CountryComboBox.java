package com.esofthead.mycollab.vaadin.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.ui.data.ValueFactory;

@Scope("prototype")
@Component
public class CountryComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public CountryComboBox() {
		loadData(ValueFactory.getCountryList());
	}
}
