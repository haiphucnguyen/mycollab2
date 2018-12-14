package com.mycollab.module.project.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = StandupMeetingView.VIEW_NAME)
public class StandupMeetingView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "dashboard/standup";
}
