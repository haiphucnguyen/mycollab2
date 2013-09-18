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

	public static String getCrmNoteAttachmentPath(int accountId, int noteId) {
		return String.format("%d/crm/.attachments/%s/%d", accountId,
				AttachmentType.CRM_NOTE_TYPE, noteId);
	}

	public static String getCrmNoteCommentAttachmentPath(int accountId,
			int noteId, int commentId) {
		return String.format("%d/crm/.attachments/%s/%d/%s/%d", accountId,
				AttachmentType.CRM_NOTE_TYPE, noteId,
				AttachmentType.COMMON_COMMENT, commentId);
	}

	public static String getProjectMessageAttachmentPath(int accountId,
			int projectId, int messageId) {
		return String.format("%d/project/%d/.attachments/%s/%d", accountId,
				projectId, AttachmentType.PROJECT_MESSAGE, messageId);
	}

	public static String getProjectMessageCommentAttachmentPath(int accountId,
			int projectId, int messageId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, AttachmentType.PROJECT_MESSAGE,
				messageId, AttachmentType.COMMON_COMMENT, commentId);
	}

	public static String getProjectEntityAttachmentPath(int accountId,
			int projectId, AttachmentType type, int typeid) {
		return String.format("%d/project/%d/.attachments/%s/%d", accountId,
				projectId, type.toString(), typeid);
	}

	public static String getProjectBugAttachmentPath(int accountId,
			int projectId, int bugId) {
		return String.format("%d/project/%d/.attachments/%s/%d", accountId,
				projectId, AttachmentType.PROJECT_BUG_TYPE, bugId);
	}

	public static String getProjectBugCommentAttachmentPath(int accountId,
			int projectId, int bugId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, AttachmentType.PROJECT_BUG_TYPE, bugId,
				AttachmentType.COMMON_COMMENT, commentId);
	}

	public static String getProjectMilestoneCommentAttachmentPath(
			int accountId, int projectId, int milestoneId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, AttachmentType.PROJECT_MILESTONE,
				milestoneId, AttachmentType.COMMON_COMMENT, commentId);
	}

	public static String getProjectTaskListCommentAttachmentPath(int accountId,
			int projectId, int tasklistId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, AttachmentType.PROJECT_TASKLIST,
				tasklistId, AttachmentType.COMMON_COMMENT, commentId);
	}

	public static String getProjectTaskAttachmentPath(int accountId,
			int projectId, int taskId) {
		return String.format("%d/project/%d/.attachments/%s/%d", accountId,
				projectId, AttachmentType.PROJECT_TASK_TYPE, taskId);
	}

	public static String getProjectTaskCommentAttachmentPath(int accountId,
			int projectId, int taskId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, AttachmentType.PROJECT_TASK_TYPE, taskId,
				AttachmentType.COMMON_COMMENT, commentId);
	}

	public static String getProjectRiskCommentAttachmentPath(int accountId,
			int projectId, int riskId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, AttachmentType.PROJECT_RISK, riskId,
				AttachmentType.COMMON_COMMENT, commentId);
	}

	public static String getProjectProblemCommentAttachmentPath(int accountId,
			int projectId, int problemId, int commentId) {
		return String.format("%d/project/%d/.attachments/%s/%d/%s/%d",
				accountId, projectId, AttachmentType.PROJECT_PROBLEM,
				problemId, AttachmentType.COMMON_COMMENT, commentId);
	}
}
