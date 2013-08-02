package com.esofthead.mycollab.module.user.dao;

import java.util.List;

import com.esofthead.mycollab.module.user.domain.SimpleUserAccountInvitation;

public interface UserAccountInvitationMapperExt {
	List<SimpleUserAccountInvitation> findAccountInvitations(
			String invitationStatus);
}
