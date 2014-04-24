package com.esofthead.mycollab.schedule.email.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.service.CurrencyService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.hp.gagawa.java.elements.Span;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class CurrencyFieldFormat extends FieldFormat<Integer> {

	private static Logger log = LoggerFactory
			.getLogger(CurrencyFieldFormat.class);
	
	public CurrencyFieldFormat(String displayName) {
		super(displayName);
	}

	@Override
	public String formatField(Integer value, String timeZone) {
		if (value == null)
			return new Span().write();

		try {
			CurrencyService currencyService = ApplicationContextUtil
					.getSpringBean(CurrencyService.class);
			Currency currency = currencyService.getCurrency(value);
			return new Span().appendText(currency.getSymbol()).write();
		} catch (Exception e) {
			log.error("Error while get currency id" + value, e);
			return new Span().write();
		}
	}
}
