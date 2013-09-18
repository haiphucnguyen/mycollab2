package com.esofthead.mycollab.module.project.esb.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.esb.DeleteProjectTaskCommand;

@Component
public class DeleteProjectTaskCommandImpl implements DeleteProjectTaskCommand {

	@Override
	public void taskRemoved(String username, int accountId, int projectId,
			int taskId) {
		// TODO Auto-generated method stub

	}

}
