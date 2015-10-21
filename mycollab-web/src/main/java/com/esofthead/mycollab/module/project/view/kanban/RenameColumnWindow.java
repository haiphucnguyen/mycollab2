package com.esofthead.mycollab.module.project.view.kanban;

import com.esofthead.mycollab.module.project.view.IKanbanView;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class RenameColumnWindow extends Window {
    public RenameColumnWindow(final IKanbanView kanbanView, final String type) {
        super("Rename column");
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
        this.center();
    }
}
