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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.security;

/**
 * 
 * @author haiphucnguyen
 */
public class AccessPermissionFlag implements PermissionFlag {

	public static final int NO_ACCESS = 0;
	public static final int READ_ONLY = 1;
	public static final int READ_WRITE = 2;
	public static final int ACCESS = 4;

	public static boolean canRead(int flag) {
		return ((flag & READ_ONLY) == READ_ONLY)
				|| ((flag & READ_WRITE) == READ_WRITE)
				|| ((flag & ACCESS) == ACCESS);
	}

	public static boolean canWrite(int flag) {
		return ((flag & READ_WRITE) == READ_WRITE)
				|| ((flag & ACCESS) == ACCESS);
	}

	public static boolean canAccess(int flag) {
		return ((flag & ACCESS) == ACCESS);
	}

	public static String toString(int flag) {
		if (flag == NO_ACCESS) {
			return "No Access";
		} else if (flag == READ_ONLY) {
			return "Read Only";
		} else if (flag == READ_WRITE) {
			return "Read & Write";
		} else if (flag == ACCESS) {
			return "Access";
		} else {
			return "";
		}
	}
}
