package com.esofthead.mycollab.module.user;

import com.esofthead.mycollab.core.MyCollabException;

public class BooleanPermissionFlag implements PermissionFlag {
	public static final int TRUE = 128;

	public static final int FALSE = 129;

	public static boolean beTrue(int flag) {
		return (flag == TRUE);
	}

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

	public static void main(String[] args) {
		System.out.println(128 >> 7);
	}
}
