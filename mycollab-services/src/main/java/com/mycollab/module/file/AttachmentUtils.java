package com.mycollab.module.file;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.project.ProjectTypeConstants;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class AttachmentUtils {
    private static final String COMMENT_PATH = "common-comment";

    public static String getCrmEntityCommentAttachmentPath(Integer accountId, String type, String typeId, Integer commentId) {
        return String.format("%d/crm/.attachments/%s/%s/%s/%d", accountId, type.toLowerCase(), typeId, COMMENT_PATH, commentId);
    }

    public static String getProjectEntityAttachmentPath(Integer accountId, Integer projectId, String type, String typeId) {
        return String.format("%d/project/%d/.attachments/%s/%s", accountId, projectId, type.toLowerCase(), typeId);
    }

    public static String getProjectEntityCommentAttachmentPath(Integer accountId, Integer projectId, String type, String typeId, Integer commentId) {
        return String.format("%d/project/%d/.attachments/%s/%s/%s/%d",
                accountId, projectId, type.toLowerCase(), typeId, COMMENT_PATH, commentId);
    }

    public static String getCommentAttachmentPath(String type, Integer accountId, Integer extraTypeId, String typeId, int commentId) {
        if (ProjectTypeConstants.BUG.equals(type) || ProjectTypeConstants.MESSAGE.equals(type) ||
                ProjectTypeConstants.MILESTONE.equals(type)
                || ProjectTypeConstants.RISK.equals(type) || ProjectTypeConstants.TASK.equals(type)
                || ProjectTypeConstants.PAGE.equals(type)
                || ProjectTypeConstants.BUG_COMPONENT.equals(type) || ProjectTypeConstants.BUG_VERSION.equals(type)
                || ProjectTypeConstants.INVOICE.equals(type)) {
            return getProjectEntityCommentAttachmentPath(accountId, extraTypeId, type, typeId, commentId);
        } else if (CrmTypeConstants.ACCOUNT.equals(type) || CrmTypeConstants.CONTACT.equals(type) ||
                CrmTypeConstants.CAMPAIGN.equals(type) || CrmTypeConstants.LEAD.equals(type) ||
                CrmTypeConstants.OPPORTUNITY.equals(type) || CrmTypeConstants.CASE.equals(type) ||
                CrmTypeConstants.CALL.equals(type) || CrmTypeConstants.MEETING.equals(type) ||
                CrmTypeConstants.TASK.equals(type)) {
            return getCrmEntityCommentAttachmentPath(accountId, type, typeId, commentId);
        } else {
            throw new MyCollabException("Do not support comment attachment path " + type);
        }
    }
}
