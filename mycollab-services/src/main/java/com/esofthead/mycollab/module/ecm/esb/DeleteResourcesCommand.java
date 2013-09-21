package com.esofthead.mycollab.module.ecm.esb;

public interface DeleteResourcesCommand {
	void removeResource(String path, String userDelete, Integer sAccountId);
}
