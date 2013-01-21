package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.Component;

public interface UserDashboardView extends View {

    void gotoMyProjectList(ScreenData data);
    
    Component gotoSubView(String name);

    void gotoMyFeeds();
}
