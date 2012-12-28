package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import java.util.List;

public interface CurrencyService extends ICrudService<Integer, Currency> {
	List<Currency> getCurrencies();
}
