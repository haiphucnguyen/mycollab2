package com.mycollab.module.project.view;

import com.mycollab.vaadin.mvp.PageView;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface UserDashboardView extends PageView {

    void showDashboard();

    void showProjectList();

    List<Integer> getInvolvedProjectKeys();
}
