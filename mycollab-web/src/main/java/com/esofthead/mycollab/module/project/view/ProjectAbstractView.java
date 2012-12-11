package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;

public class ProjectAbstractView extends AbstractView{
	private static final long serialVersionUID = 1L;
	
	protected SimpleProject project;

	public void setProject(SimpleProject project) {
		this.project = project;
	}
}
