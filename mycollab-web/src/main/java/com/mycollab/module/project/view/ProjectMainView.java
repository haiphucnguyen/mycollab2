package com.mycollab.module.project.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = ProjectMainView.VIEW_NAME)
public class ProjectMainView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "project";

    @Autowired
    private ProjectMainPresenter projectMainPresenter;

    @PostConstruct
    public void init() {
        this.addComponent(new Label("Project"));

        projectMainPresenter.initView(this);
    }
}
