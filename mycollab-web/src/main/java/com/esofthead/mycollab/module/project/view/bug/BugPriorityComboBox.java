package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

public class BugPriorityComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public BugPriorityComboBox() {
		super();
		setCaption(null);
		loadData(ProjectDataTypeFactory.getBugPriorityList());
	}
}
