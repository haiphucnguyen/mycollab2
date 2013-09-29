package com.esofthead.mycollab.module.user.esb;

public interface UserRemovedCommand {
	void userRemoved(String username, int accountid);
}
