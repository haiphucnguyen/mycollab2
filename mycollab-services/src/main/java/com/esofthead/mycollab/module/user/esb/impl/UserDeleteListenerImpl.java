package com.esofthead.mycollab.module.user.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.user.esb.UserDeleteListener;
import com.esofthead.mycollab.module.user.service.UserService;

@Component
public class UserDeleteListenerImpl implements UserDeleteListener {

	private static Logger log = LoggerFactory
			.getLogger(UserDeleteListenerImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public void userRemoved(String username, int accountid) {
		log.debug("Remove user {} with account id {}", username, accountid);
	}

}
