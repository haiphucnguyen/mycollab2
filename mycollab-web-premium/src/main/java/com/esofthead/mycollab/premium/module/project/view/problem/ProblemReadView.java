package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public interface ProblemReadView extends IPreviewView<SimpleProblem> {
	HasPreviewFormHandlers<SimpleProblem> getPreviewFormHandlers();

}
