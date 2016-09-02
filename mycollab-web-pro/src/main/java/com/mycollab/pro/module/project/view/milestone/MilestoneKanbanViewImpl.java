package com.mycollab.pro.module.project.view.milestone;

import com.mycollab.module.project.view.milestone.IMilestoneKanbanView;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@ViewComponent
public class MilestoneKanbanViewImpl extends AbstractLazyPageView implements IMilestoneKanbanView {
    @Override
    protected void displayView() {
        with(new Label("AAA"));
    }
}
