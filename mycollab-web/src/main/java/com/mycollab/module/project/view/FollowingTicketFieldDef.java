package com.mycollab.module.project.view;

import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.FollowerI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.web.ui.WebUIConstants;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class FollowingTicketFieldDef {
    public static final TableViewField summary = new TableViewField(FollowerI18nEnum.FORM_SUMMARY, "name",
            WebUIConstants.TABLE_EX_LABEL_WIDTH);

    public static final TableViewField project = new TableViewField(FollowerI18nEnum.FORM_PROJECT_NAME, "projectName",
            (int)(WebUIConstants.TABLE_EX_LABEL_WIDTH * 1.5));

    public static final TableViewField assignee = new TableViewField(GenericI18Enum.FORM_ASSIGNEE, "assignUser",
            WebUIConstants.TABLE_X_LABEL_WIDTH);

    public static final TableViewField createdDate = new TableViewField(FollowerI18nEnum.OPT_FOLLOWER_CREATE_DATE,
            "monitorDate", WebUIConstants.TABLE_DATE_WIDTH);
}
