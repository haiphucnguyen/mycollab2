package com.esofthead.mycollab.module.ecm.esb;

import com.esofthead.mycollab.module.ecm.domain.Content;

public interface SaveContentCommand {
	void saveContent(Content content, String createdUser, Integer sAccountId);
}
