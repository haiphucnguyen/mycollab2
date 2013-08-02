package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

public interface ProblemReadView extends IPreviewView<SimpleProblem> {
	HasPreviewFormHandlers<Problem> getPreviewFormHandlers();

}
