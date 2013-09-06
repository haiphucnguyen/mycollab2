/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.PermissionMap;
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
