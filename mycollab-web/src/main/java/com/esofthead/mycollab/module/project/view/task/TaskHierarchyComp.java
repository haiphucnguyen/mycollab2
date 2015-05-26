package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
public class TaskHierarchyComp extends TreeTable {
    public TaskHierarchyComp() {
        super();
        this.addContainerProperty("name", String.class, "");
    }

    public void addTaskList(SimpleTaskList taskList) {
        Item item = this.addItem(new Object[]{""});
        System.out.println(item);
    }
}
