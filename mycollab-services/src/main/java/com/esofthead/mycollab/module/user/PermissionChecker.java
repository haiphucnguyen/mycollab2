package com.esofthead.mycollab.module.user;

public class PermissionChecker {
	public static boolean isBooleanPermission(int flag) {
		return (flag >> 7) == 1;
	}

	public static boolean isAccessPermission(int flag) {
		return (flag >> 3) == 0;
	}
}
