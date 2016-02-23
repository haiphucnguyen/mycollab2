package com.esofthead.mycollab.pro.module.project.view.assignments;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.vaadin.web.ui.SavedFilterComboBox;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class CalendarSavedFilterComboBox extends SavedFilterComboBox {
    public CalendarSavedFilterComboBox() {
        super(ProjectTypeConstants.ASSIGNMENTS);
    }
}
