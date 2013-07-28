package com.esofthead.mycollab.module.user.service.esb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserDeleteListenerImpl implements UserDeleteListener {

	private static Logger log = LoggerFactory
			.getLogger(UserDeleteListenerImpl.class);
	
	

	@Override
	public void userRemoved(String username, int accountid) {
		log.debug("Remove user {} with account id {}", username, accountid);

	}

}
