package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.service.CurrencyService;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
public class CurrencyComboBox extends ComboBox {
	@Autowired
	private CurrencyService currencyService;

	public CurrencyComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
	}
	
	@Override
	public void attach() {
		super.attach();
		this.removeAllItems();
		List<Currency> currencyList = currencyService.getCountries();
		BeanContainer<String, Currency> beanItem = new BeanContainer<String, Currency>(
				Currency.class);
		beanItem.setBeanIdProperty("name");

		for (Currency currency : currencyList) {
			beanItem.addBean(currency);
		}

		this.setContainerDataSource(beanItem);
		this.setItemCaptionPropertyId("name");
	}

	@Override
	public void select(Object itemId) {
		System.out.println("Select: " + itemId);
		super.select(itemId);
	}
}
