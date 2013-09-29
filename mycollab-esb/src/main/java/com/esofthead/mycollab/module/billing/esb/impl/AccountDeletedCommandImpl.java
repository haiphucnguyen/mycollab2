package com.esofthead.mycollab.module.billing.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.billing.esb.AccountDeletedCommand;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class AccountDeletedCommandImpl implements AccountDeletedCommand {

	private static Logger log = LoggerFactory
			.getLogger(AccountDeletedCommandImpl.class);

	@Override
	public void accountDeleted(int accountid) {
		deleteAccountFiles(accountid);
	}

	private void deleteAccountFiles(int accountId) {
		log.debug("Delete account files of account {}", accountId);

		ResourceService resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);

		String rootPath = accountId + "";
		resourceService.removeResource(rootPath, "", accountId);
	}

}
