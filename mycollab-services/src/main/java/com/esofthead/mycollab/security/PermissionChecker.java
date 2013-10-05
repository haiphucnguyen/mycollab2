package com.esofthead.mycollab.security;

import com.esofthead.mycollab.core.MyCollabException;

public class PermissionChecker {
	public static boolean isBooleanPermission(int flag) {
		return (flag >> 7) == 1;
	}

	public static boolean isAccessPermission(int flag) {
		return (flag >> 3) == 0;
	}

	public static boolean isImplied(int flag, int impliedVal) {
		if (isBooleanPermission(flag)) {
			return flag == impliedVal;
		} else if (isAccessPermission(flag)) {
			if (impliedVal == AccessPermissionFlag.READ_ONLY) {
				return AccessPermissionFlag.canRead(flag);
			} else if (impliedVal == AccessPermissionFlag.READ_WRITE) {
				return AccessPermissionFlag.canWrite(flag);
			} else if (impliedVal == AccessPermissionFlag.ACCESS) {
				return AccessPermissionFlag.canAccess(flag);
			} else {
				return true;
			}
		} else {
			throw new MyCollabException(
					"Do not support permission category except boolean and access permission, the check permission is "
							+ flag);
		}
	}
}
