package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public class ProjectInfoComponent extends MHorizontalLayout {
    public ProjectInfoComponent(SimpleProject project) {
        this.with(new Label("Gaaa"));
        this.setWidth("100%");
        this.setHeight("100px");
    }
}
