package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface BugAddView extends IFormAddView<Bug> {
	HasEditFormHandlers<Bug> getEditFormHandlers();

}
