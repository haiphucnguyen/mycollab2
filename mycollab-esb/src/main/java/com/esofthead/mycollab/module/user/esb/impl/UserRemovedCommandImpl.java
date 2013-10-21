package com.esofthead.mycollab.module.user.esb.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.ProjectMemberExample;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.esb.UserRemovedCommand;
import com.esofthead.mycollab.module.user.service.UserService;

@Component
public class UserRemovedCommandImpl implements UserRemovedCommand {

	private static Logger log = LoggerFactory
			.getLogger(UserRemovedCommandImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ProjectMemberMapper projectMemberMapper;

	@Autowired
	private ProjectMemberService projectMemberService;

	@Override
	public void userRemoved(String username, Integer accountid) {
		log.debug("Remove user {} with account id {}", username, accountid);

		log.debug(
				"Update status of project member to status 'Delete' if project member associates with deleted user {}",
				username);
		ProjectMemberExample ex = new ProjectMemberExample();
		ex.createCriteria()
				.andStatusIn(
						Arrays.asList(
								RegisterStatusConstants.ACTIVE,
								RegisterStatusConstants.SENT_VERIFICATION_EMAIL,
								RegisterStatusConstants.VERIFICATING))
				.andSaccountidEqualTo(accountid);
		ProjectMember projectMember = new ProjectMember();
		projectMember.setStatus(RegisterStatusConstants.DELETE);
		projectMemberMapper.updateByExampleSelective(projectMember, ex);

		// Remove cache of project member
		// clean cache of related items
		String userPrefixKey = String.format("%s-%d",
				ProjectMemberService.class.getName(), accountid);
		LocalCacheManager.removeCacheItems(accountid.toString(), userPrefixKey);
	}

}
