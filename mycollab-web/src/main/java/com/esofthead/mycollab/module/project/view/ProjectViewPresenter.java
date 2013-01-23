package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class ProjectViewPresenter extends AbstractPresenter<ProjectView> {

    private static final long serialVersionUID = 1L;

    public ProjectViewPresenter() {
        super(ProjectView.class);
    }

    @Override
    public void onGo(ComponentContainer container, ScreenData<?> data) {
        ProjectContainer prjContainer = (ProjectContainer) container;
        prjContainer.removeAllComponents();
        prjContainer.addComponent((Component) view);
        prjContainer.setComponentAlignment((Component) view,
                Alignment.TOP_CENTER);
        
        if (data.getParams() instanceof Integer) {
            ProjectService projectService = (ProjectService)AppContext.getSpringBean(ProjectService.class);
            SimpleProject project = (SimpleProject)projectService.findProjectById((Integer)data.getParams());
            
            if (project == null) {
                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
            } else {
                AppContext.putVariable(ProjectContants.PROJECT_NAME, project);
                view.displayProject(project, null);
                AppContext.addFragment("project/" + project.getName());
            }
            
        }
    }
}
