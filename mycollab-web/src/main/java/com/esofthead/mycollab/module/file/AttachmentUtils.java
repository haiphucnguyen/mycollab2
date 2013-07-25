/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

/**
 * 
 * @author haiphucnguyen
 */
public class AttachmentUtils {

	public static final String CRM_NOTE_TYPE = "crm-note";

	public static final String PROJECT_MESSAGE = "project-message";

	public static final String PROJECT_BUG_TYPE = "project-bug";

	public static final String PROJECT_TASK_TYPE = "project-task";

	public static final String COMMON_COMMENT = "common-comment";

	public static String getAttachmentPath(int accountId, String type,
			int typeId) {
		return String.format("%d/.attachments/%s/%d", accountId, type, typeId);
	}

	public static String getCommentPath(int accountId, int commentId) {
		return getAttachmentPath(accountId, COMMON_COMMENT, commentId);
	}

	public static String getCrmNotePath(int accountId, int noteId) {
		return getAttachmentPath(accountId, CRM_NOTE_TYPE, noteId);
	}

	public static String getBugPath(int accountId, int bugId) {
		return getAttachmentPath(accountId, PROJECT_BUG_TYPE, bugId);
	}

	public static String getMessagePath(int accountId, int bugId) {
		return getAttachmentPath(accountId, PROJECT_MESSAGE, bugId);
	}
}
