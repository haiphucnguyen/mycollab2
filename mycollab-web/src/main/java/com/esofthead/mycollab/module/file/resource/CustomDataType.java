package com.esofthead.mycollab.module.file.resource;

import net.sf.dynamicreports.report.base.datatype.AbstractDataType;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

import org.apache.commons.lang3.Validate;

import com.esofthead.mycollab.common.domain.Currency;

public class CustomDataType {
	private static final CurrencyType currencyType = new CurrencyType();

	@SuppressWarnings("unchecked")
	public static <T extends DRIDataType<?, ?>> T detectType(String dataType)
			throws DRException {
		Validate.notNull(dataType, "dataType must not be null");

		String dataTypeLC = dataType.toLowerCase().trim();
		if (dataTypeLC.equals("currency")
				|| dataType.equals(Currency.class.getName())) {
			return (T) currencyType;
		}
		return null;
	}

	public static class CurrencyType extends
			AbstractDataType<Currency, Currency> {
		private static final long serialVersionUID = 1L;
	}
}
