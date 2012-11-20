package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.service.CurrencyService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
public class CurrencyComboBox extends ComboBox {

	public CurrencyComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
	}
	
	@Override
	public void attach() {
		super.attach();
		this.removeAllItems();
		CurrencyService currencyService = AppContext.getSpringBean(CurrencyService.class);
		List<Currency> currencyList = currencyService.getCurrencies();
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
