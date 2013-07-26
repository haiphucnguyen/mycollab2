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

	public static final String PROJECT_MILESTONE = "project-milestone";

	public static final String PROJECT_RISK = "project-risk";

	public static final String PROJECT_PROBLEM = "project-problem";

	public static final String PROJECT_TASKLIST = "project-tasklist";

	public static final String PROJECT_TASK_TYPE = "project-task";

	public static final String COMMON_COMMENT = "common-comment";

	public static String getAttachmentPath(int accountId, String type,
			int typeId) {
		return String.format("%d/.attachments/%s/%d", accountId, type, typeId);
	}

	@Deprecated
	public static String getCommentPath(int accountId, int commentId) {
		return getAttachmentPath(accountId, COMMON_COMMENT, commentId);
	}

	@Deprecated
	public static String getCrmNotePath(int accountId, int noteId) {
		return getAttachmentPath(accountId, CRM_NOTE_TYPE, noteId);
	}

	public static String getCrmNoteAttachmentPath(int accountId, int noteId) {
		return String.format("%d/crm/.attachments/%s/%d", accountId,
				CRM_NOTE_TYPE, noteId);
	}

	public static String getCrmNoteCommentAttachmentPath(int accountId,
			int noteId, int commentId) {
		return String.format("%d/crm/.attachments/%s/%d/%s/%d", accountId,
				CRM_NOTE_TYPE, noteId, COMMON_COMMENT, commentId);
	}

	public static String getProjectMessageAttachmentPath(int accountId,
			int projectId, int messageId) {
		return String.format("%d/project/%d/.attachments/%s/%d", accountId,
				projectId, PROJECT_MESSAGE, messageId);
	}

	public static String getProjectMessageCommentAttachmentPath(int accountId,
			int projectId, int messageId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, PROJECT_MESSAGE, messageId,
				COMMON_COMMENT, commentId);
	}

	public static String getProjectBugAttachmentPath(int accountId,
			int projectId, int bugId) {
		return String.format("%d/project/%d/.attachments/%s/%d", accountId,
				projectId, PROJECT_BUG_TYPE, bugId);
	}

	public static String getProjectBugCommentAttachmentPath(int accountId,
			int projectId, int bugId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, PROJECT_BUG_TYPE, bugId, COMMON_COMMENT,
				commentId);
	}

	public static String getProjectMilestoneAttachmentPath(int accountId,
			int projectId, int milestoneId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, PROJECT_MILESTONE, milestoneId,
				COMMON_COMMENT, commentId);
	}

	public static String getProjectTaskListAttachmentPath(int accountId,
			int projectId, int tasklistId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, PROJECT_TASKLIST, tasklistId,
				COMMON_COMMENT, commentId);
	}

	public static String getProjectTaskAttachmentPath(int accountId,
			int projectId, int taskId) {
		return String.format("%d/project/%d/.attachments/%s/%d", accountId,
				projectId, PROJECT_TASK_TYPE, taskId);
	}

	public static String getProjectTaskCommentAttachmentPath(int accountId,
			int projectId, int taskId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, PROJECT_TASK_TYPE, taskId,
				COMMON_COMMENT, commentId);
	}

	public static String getProjectRiskAttachmentPath(int accountId,
			int projectId, int riskId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, PROJECT_RISK, riskId, COMMON_COMMENT,
				commentId);
	}

	public static String getProjectProblemAttachmentPath(int accountId,
			int projectId, int problemId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, PROJECT_PROBLEM, problemId,
				COMMON_COMMENT, commentId);
	}

	@Deprecated
	public static String getBugPath(int accountId, int bugId) {
		return getAttachmentPath(accountId, PROJECT_BUG_TYPE, bugId);
	}

	@Deprecated
	public static String getTaskPath(int accountId, int bugId) {
		return getAttachmentPath(accountId, PROJECT_TASK_TYPE, bugId);
	}

	@Deprecated
	public static String getMessagePath(int accountId, int bugId) {
		return getAttachmentPath(accountId, PROJECT_MESSAGE, bugId);
	}
}
