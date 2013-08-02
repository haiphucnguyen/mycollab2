package com.esofthead.mycollab.common.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.CurrencyMapper;
import com.esofthead.mycollab.common.domain.Currency;
import com.esofthead.mycollab.common.domain.CurrencyExample;
import com.esofthead.mycollab.common.service.CurrencyService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;

@Service
public class CurrencyServiceImpl extends DefaultCrudService<Integer, Currency>
		implements CurrencyService {

	@Autowired
	private CurrencyMapper currencyMapper;
	
	@Override
	public ICrudGenericDAO<Integer, Currency> getCrudMapper() {
		return currencyMapper;
	}

	@Override
	public List<Currency> getCurrencies() {
		CurrencyExample ex = new CurrencyExample();
		return currencyMapper.selectByExample(ex);
	}

}
