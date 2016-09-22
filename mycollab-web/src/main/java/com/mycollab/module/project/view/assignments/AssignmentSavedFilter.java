package com.mycollab.module.project.view.assignments;

import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.vaadin.web.ui.SavedFilterComboBox;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class AssignmentSavedFilter extends SavedFilterComboBox {
    public AssignmentSavedFilter() {
        super(ProjectTypeConstants.ASSIGNMENT);
    }

    public void setTotalCountNumber(int countNumber) {
        componentsText.setReadOnly(false);
        componentsText.setValue(selectedQueryName + " (" + countNumber + ")");
        componentsText.setReadOnly(true);
    }
}
