package com.esofthead.mycollab.module.project.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class SimpleProjectRole extends ProjectRole {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(SimpleProjectRole.class);

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
