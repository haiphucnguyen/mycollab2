package com.esofthead.mycollab.vaadin.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.ui.data.ValueFactory;

@Scope("prototype")
@Component
public class CountryListSelect extends ValueListSelect {
	private static final long serialVersionUID = 1L;

	public CountryListSelect() {
		loadData(ValueFactory.getCountryList());
	}
}
