/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class TaskTableDisplay extends PagedBeanTable2<ProjectTaskService, TaskSearchCriteria, SimpleTask> {

    public TaskTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(ProjectTaskService.class),
                SimpleTask.class, visibleColumns, columnHeaders);

        

        this.addGeneratedColumn("taskname", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleTask task = TaskTableDisplay.this.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(task.getTaskname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                fireTableEvent(new IPagedBeanTable.TableClickEvent(TaskTableDisplay.this, task, "taskname"));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });
        
        this.addGeneratedColumn("startdate", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleTask task = TaskTableDisplay.this.getBeanByIndex(itemId);
                return new Label(AppContext.formatDate(task.getStartdate()));

            }
        });
        
        this.addGeneratedColumn("deadline", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleTask task = TaskTableDisplay.this.getBeanByIndex(itemId);
                return new Label(AppContext.formatDate(task.getDeadline()));

            }
        });



        this.setColumnExpandRatio("taskname", 1);
        this.setColumnWidth("assignUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);
        this.setColumnWidth("startdate", UIConstants.TABLE_DATE_WIDTH);
        this.setColumnWidth("deadline", UIConstants.TABLE_DATE_WIDTH);

        this.setWidth("100%");
    }
    
}
