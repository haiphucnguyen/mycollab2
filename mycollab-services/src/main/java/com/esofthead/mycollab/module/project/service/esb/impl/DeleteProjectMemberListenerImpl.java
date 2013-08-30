package com.esofthead.mycollab.module.project.service.esb.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.dao.ActivityStreamMapper;
import com.esofthead.mycollab.common.domain.ActivityStream;
import com.esofthead.mycollab.common.domain.ActivityStreamExample;
import com.esofthead.mycollab.module.project.service.esb.DeleteProjectMemberListener;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;

@Component
public class DeleteProjectMemberListenerImpl implements
		DeleteProjectMemberListener {

	private static Logger log = LoggerFactory
			.getLogger(DeleteProjectMemberListenerImpl.class);

	@Autowired
	private ActivityStreamMapper activityStreamMapper;

	@Autowired
	private UserService userService;

	@Override
	public void projectMemberRemoved(String username, Integer projectMemberId,
			Integer projectId, Integer accountId) {
		log.debug(
				"Remove project member has username {}, project member id {} and project id {}",
				new Object[] { username, projectMemberId, projectId });

		removeProjectMemberActivityStreams(username, projectId, accountId);

	}

	private void removeProjectMemberActivityStreams(String username,
			Integer projectId, int accountId) {
		SimpleUser user = userService.findUserByUserNameInAccount(username,
				accountId);
		ActivityStreamExample ex = new ActivityStreamExample();
		ActivityStream record = new ActivityStream();
		record.setCreateduserdisplayname(user.getDisplayName());
		activityStreamMapper.updateByExampleSelective(record, ex);
	}

}
