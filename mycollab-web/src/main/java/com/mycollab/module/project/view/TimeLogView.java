package com.mycollab.module.project.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = TimeLogView.VIEW_NAME)
public class TimeLogView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "dashboard/timelog";
}
