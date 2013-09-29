package com.esofthead.mycollab.module.user.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.user.esb.UserRemovedCommand;
import com.esofthead.mycollab.module.user.service.UserService;

@Component
public class UserRemovedCommandImpl implements UserRemovedCommand {

	private static Logger log = LoggerFactory
			.getLogger(UserRemovedCommandImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public void userRemoved(String username, int accountid) {
		log.debug("Remove user {} with account id {}", username, accountid);
	}

}
