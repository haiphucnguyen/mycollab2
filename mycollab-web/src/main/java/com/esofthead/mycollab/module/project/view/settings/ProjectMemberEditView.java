package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface ProjectMemberEditView extends IFormAddView<ProjectMember> {

	HasEditFormHandlers<ProjectMember> getEditFormHandlers();

}
