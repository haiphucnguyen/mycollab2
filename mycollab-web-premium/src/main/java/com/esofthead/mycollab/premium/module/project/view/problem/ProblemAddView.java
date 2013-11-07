package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

public interface ProblemAddView extends IFormAddView<Problem> {
	HasEditFormHandlers<Problem> getEditFormHandlers();

}
