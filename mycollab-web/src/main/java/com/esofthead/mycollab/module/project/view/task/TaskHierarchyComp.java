package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.vaadin.data.Item;
import com.vaadin.ui.TreeTable;

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
public class TaskHierarchyComp extends TreeTable {
    public TaskHierarchyComp() {
        super();
        this.addContainerProperty("Name", String.class, "");
    }

    public void addTaskList(SimpleTaskList taskList) {
        Item item = this.addItem(new Object[]{taskList.getName()});
        System.out.println(item);
    }
}
