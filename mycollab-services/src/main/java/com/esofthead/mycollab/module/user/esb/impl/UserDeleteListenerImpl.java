package com.esofthead.mycollab.module.user.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.dao.ActivityStreamMapper;
import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.ActivityStreamExample;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.esb.UserDeleteListener;
import com.esofthead.mycollab.module.user.service.UserService;

@Component
public class UserDeleteListenerImpl implements UserDeleteListener {

	private static Logger log = LoggerFactory
			.getLogger(UserDeleteListenerImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityStreamMapper activityStreamMapper;

	@Override
	public void userRemoved(String username, int accountid) {
		log.debug("Remove user {} with account id {}", username, accountid);
		removeUserActivities(username, accountid);

	}

	private void removeUserActivities(String username, int accountid) {
		SimpleUser user = userService.findUserByUserNameInAccount(username,
				accountid);
		ActivityStreamExample ex = new ActivityStreamExample();
		ActivityStream record = new ActivityStream();
		record.setCreateduserdisplayname(user.getDisplayName());
		activityStreamMapper.updateByExampleSelective(record, ex);
	}

}
