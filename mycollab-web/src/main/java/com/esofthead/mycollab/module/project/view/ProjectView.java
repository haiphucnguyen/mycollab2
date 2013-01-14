package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.Component;

public interface ProjectView extends View {

    void displayProject(SimpleProject project);

    void gotoDashboard(ScreenData data);

    void gotoUsersAndGroup();

    void gotoMessageView(ScreenData data);

    void gotoMilestoneView(ScreenData data);

    void gotoRiskView(@SuppressWarnings("rawtypes") ScreenData data);

    void gotoProblemView(@SuppressWarnings("rawtypes") ScreenData data);

    void gotoBugView(@SuppressWarnings("rawtypes") ScreenData data);

    void gotoTaskList(ScreenData data);

    Component gotoSubView(String name);
}
