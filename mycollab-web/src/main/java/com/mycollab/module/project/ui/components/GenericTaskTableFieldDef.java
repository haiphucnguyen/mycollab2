package com.mycollab.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.web.ui.WebUIConstants;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class GenericTaskTableFieldDef {
    public static final TableViewField name = new TableViewField(GenericI18Enum.FORM_DESCRIPTION, "name",
            WebUIConstants.TABLE_EX_LABEL_WIDTH);

    public static final TableViewField assignUser = new TableViewField(GenericI18Enum.FORM_ASSIGNEE,
            "assignUser", WebUIConstants.TABLE_EX_LABEL_WIDTH);
}
