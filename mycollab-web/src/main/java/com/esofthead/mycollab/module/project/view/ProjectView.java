package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.Component;

public interface ProjectView extends View {
	void displayProject(SimpleProject project);
	
	Component gotoSubView(String name);
}
