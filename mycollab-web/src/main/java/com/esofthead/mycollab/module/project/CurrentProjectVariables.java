package com.esofthead.mycollab.module.project;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.web.AppContext;

public class CurrentProjectVariables {
	public static SimpleProject getProject() {
		return (SimpleProject) AppContext
				.getVariable(ProjectContants.CURRENT_PROJECT);
	}

	public static int getProjectId() {
		SimpleProject project = getProject();
		return project.getId();
	}
}
