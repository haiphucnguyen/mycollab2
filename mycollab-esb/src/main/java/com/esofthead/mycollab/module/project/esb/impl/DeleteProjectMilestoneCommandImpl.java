package com.esofthead.mycollab.module.project.esb.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.esb.DeleteProjectMilestoneCommand;

@Component
public class DeleteProjectMilestoneCommandImpl implements
		DeleteProjectMilestoneCommand {

	@Override
	public void milestoneRemoved(String username, int accountId, int projectId,
			int bugId) {
		// TODO Auto-generated method stub

	}

}
