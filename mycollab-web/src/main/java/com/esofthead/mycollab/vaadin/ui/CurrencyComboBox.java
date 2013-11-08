/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
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

		CurrencyService currencyService = ApplicationContextUtil
				.getSpringBean(CurrencyService.class);
		List<Currency> currencyList = currencyService.getCurrencies();
		for (Currency currency : currencyList) {
			this.addItem(currency.getId());
			this.setItemCaption(currency.getId(), currency.getShortname()
					+ " (" + currency.getSymbol() + ")");
		}
	}
}
