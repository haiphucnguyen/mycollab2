package com.esofthead.mycollab.module.project.esb.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.project.esb.DeleteProjectVersionCommand;

@Component
public class DeleteProjectVersionCommandImpl implements DeleteProjectVersionCommand{

	@Override
	public void versionRemoved(String username, int accountId, int projectId,
			int bugId) {
		// TODO Auto-generated method stub
		
	}

}
