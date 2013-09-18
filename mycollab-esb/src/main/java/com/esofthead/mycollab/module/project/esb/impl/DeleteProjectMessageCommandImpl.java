package com.esofthead.mycollab.module.project.esb.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.esb.DeleteProjectMessageCommand;

@Component
public class DeleteProjectMessageCommandImpl implements DeleteProjectMessageCommand {

	@Override
	public void messageRemoved(String username, int accountId, int projectId,
			int bugId) {
		// TODO Auto-generated method stub
		
	}

}
