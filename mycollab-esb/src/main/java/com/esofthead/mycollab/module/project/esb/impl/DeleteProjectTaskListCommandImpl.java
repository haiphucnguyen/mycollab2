package com.esofthead.mycollab.module.project.esb.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.esb.DeleteProjectTaskListCommand;

@Component
public class DeleteProjectTaskListCommandImpl implements
		DeleteProjectTaskListCommand {

//	private static 
	
	@Override
	public void taskListRemoved(String username, int accountId, int projectId,
			int taskListId) {
		// TODO Auto-generated method stub
		
	}

}
