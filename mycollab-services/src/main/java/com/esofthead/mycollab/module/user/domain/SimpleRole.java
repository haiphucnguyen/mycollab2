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
package com.esofthead.mycollab.module.user.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.security.PermissionMap;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * 
 * @author haiphucnguyen
 */
public class SimpleRole extends Role {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SimpleRole.class);

	public static final String ADMIN = "Administrator";
	public static final String EMPLOYEE = "Employee";
	public static final String GUEST = "Guest";

	private String permissionVal;
	private PermissionMap permissionMap;

	public String getPermissionVal() {
		return permissionVal;
	}

	public void setPermissionVal(String permissionVal) {
		this.permissionVal = permissionVal;
	}

	public PermissionMap getPermissionMap() {
		if (permissionMap == null) {

			if (permissionVal == null || "".equals(permissionVal)) {
				permissionMap = new PermissionMap();
			} else {
				try {
					XStream xstream = new XStream(new StaxDriver());
					permissionMap = (PermissionMap) xstream
							.fromXML(permissionVal);
				} catch (Exception e) {
					log.error("Error while get permission", e);
				}
			}
		}
		return permissionMap;
	}
}
