package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class MembersWeeklyHoursViewImpl extends AbstractPageView implements MembersWeeklyHoursView {
    @Override
    public void display() {
        addComponent(new Label("Hours"));
    }
}
