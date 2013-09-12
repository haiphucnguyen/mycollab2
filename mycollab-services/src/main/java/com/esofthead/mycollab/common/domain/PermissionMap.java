/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.domain;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.module.user.AccessPermissionFlag;

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
