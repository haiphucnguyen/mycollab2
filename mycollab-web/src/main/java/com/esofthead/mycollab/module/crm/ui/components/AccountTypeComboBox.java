package com.esofthead.mycollab.module.crm.ui.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.esofthead.mycollab.module.crm.data.DataTypeFactory;
import com.esofthead.mycollab.module.crm.domain.AccountType;
import com.esofthead.mycollab.module.crm.service.AccountTypeService;
import com.esofthead.mycollab.web.MyCollabApplication;
import com.vaadin.ui.ComboBox;

@Configurable(preConstruction = true)
public class AccountTypeComboBox extends ComboBox {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountTypeService accountTypeService;

	public AccountTypeComboBox() {
		super("Types");

//		List<AccountType> accountTypes = accountTypeService
//				.getAccountTypesByAccountId(MyCollabApplication.getInstance()
//						.getAccountId());
//		
//		this.setContainerDataSource(DataTypeFactory
//				.getAccountTypes(accountTypes));
//		
//		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
//		this.setItemCaptionPropertyId("name");
		
	}
}
