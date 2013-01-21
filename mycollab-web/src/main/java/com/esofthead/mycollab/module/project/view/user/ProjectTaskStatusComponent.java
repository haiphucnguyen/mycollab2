/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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
        super("My Openned Tasks");
    }
    
    public void showProjectTasksByStatus() {
        this.removeAllComponents();
        
        taskList = new DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask>(AppContext.getSpringBean(ProjectGenericTaskService.class), TaskStatusComponent.ActivityStreamRowDisplayHandler.class, 15);
        this.addComponent(new LazyLoadWrapper(taskList));
        ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setsAccountId(new NumberSearchField(AppContext.getAccountId()));
        searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND, new String[]{ProjectGenericTaskSearchCriteria.OPEN_STATUS}));
        searchCriteria.setAssignUser(new StringSearchField(SearchField.AND, AppContext.getUsername()));
        
        SimpleProject project = (SimpleProject)AppContext.getVariable(ProjectContants.PROJECT_NAME);
        searchCriteria.setProjectId(new NumberSearchField(project.getId()));
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
                public void buttonClick(Button.ClickEvent event) {
                    
                }
            });
            taskLink.setIcon(ProjectResources.getIconResource16size(genericTask.getType()));
            taskLink.setStyleName("link");
            header.addComponent(taskLink);
            
            layout.addComponent(header);
            
            return layout;
        }
    }
}
