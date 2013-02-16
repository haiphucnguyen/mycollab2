package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.StandupReport;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface StandupAddView extends IFormAddView<StandupReport> {
	HasEditFormHandlers<StandupReport> getEditFormHandlers();

}
