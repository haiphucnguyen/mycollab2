package com.mycollab.module.project.view;

import com.mycollab.module.project.service.ProjectService;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.Presenter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class DashboardPresenter extends Presenter<DashboardView> {

    @Autowired
    private ProjectService projectService;

    private List<Integer> projectKeys;

    List<Integer> getProjectKeys() {
        projectKeys = projectService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        return projectKeys;
    }
}
