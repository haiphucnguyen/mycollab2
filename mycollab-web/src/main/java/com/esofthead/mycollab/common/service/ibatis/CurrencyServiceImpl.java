package com.esofthead.mycollab.common.service.ibatis;

import java.util.List;

import com.esofthead.mycollab.common.dao.CurrencyMapper;
import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.domain.CurrencyExample;
import com.esofthead.mycollab.common.service.CurrencyService;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;

public class CurrencyServiceImpl extends DefaultCrudService<Integer, Currency>
		implements CurrencyService {

	@Override
	public List<Currency> getCountries() {
		CurrencyExample ex = new CurrencyExample();
		return ((CurrencyMapper) daoObj).selectByExample(ex);
	}

}
