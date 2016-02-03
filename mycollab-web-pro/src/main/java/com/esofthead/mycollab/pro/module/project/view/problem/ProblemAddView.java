package com.esofthead.mycollab.pro.module.project.view.problem;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ProblemAddView extends IFormAddView<Problem> {
    HasEditFormHandlers<Problem> getEditFormHandlers();
}
