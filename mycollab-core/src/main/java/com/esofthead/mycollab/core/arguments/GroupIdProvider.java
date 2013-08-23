package com.esofthead.mycollab.core.arguments;

public abstract class GroupIdProvider {
	private static GroupIdProvider instance;

	abstract public Integer getGroupId();

	public static void registerAccountIdProvider(GroupIdProvider provider) {
		instance = provider;
	}

	public static Integer getAccountId() {
		if (instance != null) {
			return instance.getGroupId();
		} else {
			return 0;
		}
	}
}
