package com.esofthead.mycollab.module.crm.data;

import java.util.Collection;

import com.esofthead.mycollab.module.crm.domain.AccountType;
import com.vaadin.data.util.BeanContainer;

public class DataTypeFactory {
	public static BeanContainer<Integer, AccountType> getAccountTypes(
			Collection<AccountType> accountTypes) {
		BeanContainer<Integer, AccountType> types = new BeanContainer<Integer, AccountType>(
				AccountType.class);
		types.setBeanIdProperty("id");
		for (AccountType accType : accountTypes) {
			types.addBean(accType);
		}
		return types;
	}
}
