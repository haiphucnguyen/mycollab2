package com.esofthead.mycollab.module.project.view.kanban;

import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class KanbanBlock extends MVerticalLayout {
    private String stage;

    public KanbanBlock(String stage) {
        this.stage = stage;
        Label header = new Label(stage);
        this.with(header);
        this.setWidth("300px");
        this.setHeight("100%");
    }
}
