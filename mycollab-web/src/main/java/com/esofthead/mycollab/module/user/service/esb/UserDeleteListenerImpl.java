package com.esofthead.mycollab.module.user.service.esb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.dao.MessageMapper;
import com.esofthead.mycollab.module.project.dao.MilestoneMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMemberMapper;

@Component
public class UserDeleteListenerImpl implements UserDeleteListener {

	private static Logger log = LoggerFactory
			.getLogger(UserDeleteListenerImpl.class);

	@Autowired
	private ProjectMemberMapper projectMemberMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private MilestoneMapper milestoneMapper;
	
	

	@Override
	public void userRemoved(String username, int accountid) {
		log.debug("Remove user {} with account id {}", username, accountid);

	}

}
