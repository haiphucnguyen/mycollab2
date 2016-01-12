package com.esofthead.mycollab.vaadin.web.ui;

import com.esofthead.mycollab.common.CountryValueFactory;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class CountryComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public CountryComboBox() {
		loadData(CountryValueFactory.getCountryList());
	}
}
