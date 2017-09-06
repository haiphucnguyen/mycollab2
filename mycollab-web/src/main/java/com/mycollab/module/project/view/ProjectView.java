package com.mycollab.module.project.view;

import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.vaadin.mvp.PageView;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface ProjectView extends PageView {

    void initView(SimpleProject project);

    void updateProjectFeatures();

    Component gotoSubView(String name);

    void setNavigatorVisibility(boolean visibility);

    void displaySearchResult(String value);
}
