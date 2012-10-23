package com.esofthead.mycollab.module.file.domain;

public class IdentityPermission {
	public static final int FULL_CONTROL = 0x00000001;

	public static final int MODIFY = 0x00000002;

	public static final int READ_EXECUTE = 0x00000004;

	public static final int LIST_FOLDERS = 0x00000008;

	public static final int READ = 0x00000010;

	public static final int WRITE = 0x00000020;

	private long flag;

	private boolean inherit = true;

	public IdentityPermission() {

	}

	public IdentityPermission(int flag, boolean inherit) {
		this.flag = flag;
		this.inherit = inherit;
	}

	public long getFlag() {
		return flag;
	}

	public void setFlag(long flag) {
		this.flag = flag;
	}

	public boolean isSameIdentity(IdentityPermission obj) {
		return true;
	}

	public boolean isInherit() {
		return inherit;
	}

	public void setInherit(boolean inherit) {
		this.inherit = inherit;
	}

}
