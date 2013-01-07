package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
public class ProjectTypeComboBox extends ValueComboBox {

    public ProjectTypeComboBox() {
        this.loadData(ProjectDataTypeFactory.getProjectTypeList());
    }
}
