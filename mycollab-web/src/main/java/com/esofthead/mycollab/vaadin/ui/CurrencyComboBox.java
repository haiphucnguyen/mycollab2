package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.service.CurrencyService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
public class CurrencyComboBox extends ComboBox {

	public CurrencyComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);
	}

	@Override
	public void attach() {
		super.attach();
		this.removeAllItems();
		CurrencyService currencyService = ApplicationContextUtil
				.getSpringBean(CurrencyService.class);
		List<Currency> currencyList = currencyService.getCurrencies();
		for (Currency currency : currencyList) {
			this.addItem(currency.getId());
			this.setItemCaption(currency.getId(), currency.getShortname() + " ("
					+ currency.getSymbol() + ")");
		}
	}
}
