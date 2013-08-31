package com.esofthead.mycollab.module.project.esb;

public interface InviteOutsideProjectMemberListener {
	void inviteUsers(String[] email, int projectId, int projectRoleId,
			String inviteUser, int sAccountId);
}
