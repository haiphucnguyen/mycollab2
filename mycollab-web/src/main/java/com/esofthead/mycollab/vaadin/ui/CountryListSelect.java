package com.esofthead.mycollab.vaadin.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class CountryListSelect extends ValueListSelect {
	private static final long serialVersionUID = 1L;

	public CountryListSelect() {
		loadData(new String[] { "Viet Nam", "United States" });
	}
}
