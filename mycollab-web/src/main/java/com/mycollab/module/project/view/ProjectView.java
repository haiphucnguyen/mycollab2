package com.mycollab.module.project.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = ProjectView.VIEW_NAME)
public class ProjectView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "project";

    @Autowired
    private ProjectPresenter projectPresenter;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println(event.getParameters());
    }
}
