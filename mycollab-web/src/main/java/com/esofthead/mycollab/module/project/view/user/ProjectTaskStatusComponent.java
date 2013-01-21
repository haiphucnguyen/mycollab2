/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectTaskStatusComponent extends Panel {
    
    private DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> taskList;
    
    public ProjectTaskStatusComponent() {
        super("Openned Tasks");
    }
    
    public void showProjectTasksByStatus() {
        this.removeAllComponents();
        
        taskList = new DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask>(AppContext.getSpringBean(ProjectGenericTaskService.class), ActivityStreamRowDisplayHandler.class, 15);
        this.addComponent(new LazyLoadWrapper(taskList));
        ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setsAccountId(new NumberSearchField(AppContext.getAccountId()));
        searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND, new String[]{ProjectGenericTaskSearchCriteria.OPEN_STATUS}));
        
        taskList.setSearchCriteria(searchCriteria);
    }
    
    public static class ActivityStreamRowDisplayHandler implements DefaultBeanPagedList.RowDisplayHandler<ProjectGenericTask> {
        
        @Override
        public Component generateRow(ProjectGenericTask genericTask, int rowIndex) {
            VerticalLayout layout = new VerticalLayout();
            HorizontalLayout header = new HorizontalLayout();
            header.setSpacing(true);
            
            Button taskLink = new Button(genericTask.getName(), new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    
                }
            });
            taskLink.setIcon(ProjectResources.getIconResource16size(genericTask.getType()));
            taskLink.setStyleName("link");
            header.addComponent(taskLink);
            
            Label projectLbl = new Label(" in project ");
            header.addComponent(projectLbl);
            header.setComponentAlignment(projectLbl, Alignment.MIDDLE_CENTER);
            
            Button projectLink = new Button(genericTask.getProjectName(), new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    
                }
            });
            projectLink.setIcon(ProjectResources.getIconResource16size(ProjectContants.PROJECT));
            projectLink.setStyleName("link");
            header.addComponent(projectLink);
            
            layout.addComponent(header);
            
            return layout;
        }
    }
}
