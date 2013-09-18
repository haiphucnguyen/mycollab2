package com.esofthead.mycollab.module.project.esb;

public interface InviteOutsideProjectMemberCommand {
	void inviteUsers(String[] email, int projectId, int projectRoleId,
			String inviteUser, int sAccountId);
}
