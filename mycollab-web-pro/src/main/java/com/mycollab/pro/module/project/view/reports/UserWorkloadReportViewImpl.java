package com.mycollab.pro.module.project.view.reports;

import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class UserWorkloadReportViewImpl extends AbstractPageView implements UserWorkloadReportView {
    @Override
    public void display() {
        removeAllComponents();

    }
}
