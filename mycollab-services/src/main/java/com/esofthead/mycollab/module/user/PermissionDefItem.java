package com.esofthead.mycollab.module.user;

public class PermissionDefItem {
	private String key;

	private String caption;

	private Class<? extends PermissionFlag> permissionCls;

	public PermissionDefItem(String key, String caption,
			Class<? extends PermissionFlag> permissionCls) {
		this.key = key;
		this.caption = caption;
		this.permissionCls = permissionCls;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Class<? extends PermissionFlag> getPermissionCls() {
		return permissionCls;
	}

	public void setPermissionCls(Class<? extends PermissionFlag> permissionCls) {
		this.permissionCls = permissionCls;
	}
}
