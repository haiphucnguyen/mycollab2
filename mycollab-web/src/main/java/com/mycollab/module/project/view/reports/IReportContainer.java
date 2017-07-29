package com.mycollab.module.project.view.reports;

import com.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public interface IReportContainer extends PageView {
    void addView(PageView view);

    void showDashboard();
}
