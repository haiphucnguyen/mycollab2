package com.esofthead.mycollab.module.project.esb.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.esb.DeleteProjectComponentCommand;

@Component
public class DeleteProjectComponentCommandImpl implements
		DeleteProjectComponentCommand {

	@Override
	public void componentRemoved(String username, int accountId, int projectId,
			int bugId) {
		// TODO Auto-generated method stub
		
	}

}
