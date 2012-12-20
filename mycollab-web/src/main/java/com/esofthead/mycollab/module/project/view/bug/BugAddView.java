package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface BugAddView extends IFormAddView<SimpleBug> {
	HasEditFormHandlers<SimpleBug> getEditFormHandlers();

}
