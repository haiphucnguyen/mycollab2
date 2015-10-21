package com.esofthead.mycollab.module.project.view.kanban;

import com.esofthead.mycollab.module.project.view.IKanbanView;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class DeleteColumnWindow extends Window {
    public DeleteColumnWindow(final IKanbanView kanbanView, final String type) {
        super("Delete columns");
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
        this.center();


    }
}
