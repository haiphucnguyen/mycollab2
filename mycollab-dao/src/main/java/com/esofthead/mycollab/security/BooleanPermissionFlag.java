/**
 * This file is part of mycollab-dao.
 *
 * mycollab-dao is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-dao is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-dao.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.security;

import com.esofthead.mycollab.core.MyCollabException;

/**
 * Boolean permission flag
 * 
 */
public class BooleanPermissionFlag implements PermissionFlag {
	public static final int TRUE = 128;

	public static final int FALSE = 129;

	/**
	 * Check whether <code>flag</code> is true permission
	 * 
	 * @param flag
	 * @return
	 */
	public static boolean beTrue(int flag) {
		return (flag == TRUE);
	}

	/**
	 * Check whether <code>flag</code> is false permission
	 * 
	 * @param flag
	 * @return
	 */
	public static boolean beFalse(int flag) {
		return (flag == FALSE);
	}

	public static String toString(int flag) {
		if (flag == TRUE) {
			return "Yes";
		} else if (flag == FALSE) {
			return "No";
		} else {
			throw new MyCollabException("Flag could be true or false");
		}
	}
}
