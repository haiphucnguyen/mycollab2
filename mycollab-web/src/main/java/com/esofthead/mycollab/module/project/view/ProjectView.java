package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.Component;

public interface ProjectView extends View {

    void constructProjectHeaderPanel(SimpleProject project, PageActionChain pageActionChain);

    void gotoUsersAndGroup(ScreenData<?> data);

    void gotoMilestoneView(ScreenData<?> data);
    
    void gotoStandupReportView(ScreenData<?> data);

    void gotoRiskView(ScreenData<?> data);

    void gotoBugView(ScreenData<?> data);

    void gotoTaskList(ScreenData<?> data);

    Component gotoSubView(String name);
}
