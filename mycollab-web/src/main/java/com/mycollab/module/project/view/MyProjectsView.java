package com.mycollab.module.project.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.layouts.MVerticalLayout;

@SpringView(name = MyProjectsView.VIEW_NAME)
public class MyProjectsView extends MVerticalLayout implements View {
    public static final String VIEW_NAME = "dashboard/projects";

    @Autowired
    private MyProjectsPresenter myProjectsPresenter;


}
