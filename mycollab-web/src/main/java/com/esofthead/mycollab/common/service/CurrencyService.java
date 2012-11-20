package com.esofthead.mycollab.common.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.core.persistence.ICrudService;

public interface CurrencyService extends ICrudService<Integer, Currency> {
	List<Currency> getCurrencies();
}
