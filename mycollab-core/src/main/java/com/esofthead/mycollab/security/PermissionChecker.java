/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
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
