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
package com.esofthead.mycollab.common.domain;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.BooleanPermissionFlag;

/**
 * 
 * @author haiphucnguyen
 */
public class PermissionMap extends ValuedBean {
	private static final long serialVersionUID = 1L;

	private Map<String, Integer> perMap = new HashMap<String, Integer>();

	public void addPath(String permissionItem, Integer value) {
		perMap.put(permissionItem, value);
	}

	public Integer getPermissionFlag(String permissionItem) {
		Object value = perMap.get(permissionItem);
		if (value == null) {
			return AccessPermissionFlag.NO_ACCESS;
		}

		return (Integer) value;
	}

	public boolean canBeYes(String permissionItem) {
		Object value = perMap.get(permissionItem);
		if (value == null) {
			return false;
		} else {
			return BooleanPermissionFlag.beTrue((Integer) value);
		}
	}

	public boolean canBeFalse(String permissionItem) {
		Object value = perMap.get(permissionItem);
		if (value == null) {
			return false;
		} else {
			return BooleanPermissionFlag.beFalse((Integer) value);
		}
	}

	public boolean canRead(String permissionItem) {
		Object value = perMap.get(permissionItem);
		if (value == null) {
			return false;
		} else {
			return AccessPermissionFlag.canRead((Integer) value);
		}
	}

	public boolean canWrite(String permissionItem) {
		Object value = perMap.get(permissionItem);
		if (value == null) {
			return false;
		} else {
			return AccessPermissionFlag.canWrite((Integer) value);
		}
	}

	public boolean canAccess(String permissionItem) {
		Object value = perMap.get(permissionItem);
		if (value == null) {
			return false;
		} else {
			return AccessPermissionFlag.canAccess((Integer) value);
		}
	}

	public Integer get(String key) {
		return perMap.get(key);
	}
}
