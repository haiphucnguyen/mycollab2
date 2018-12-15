package com.mycollab.module.project.view;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.Presenter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@SpringComponent
@ViewScope
public class MyProjectsPresenter extends Presenter<MyProjectsView> {

    @Autowired
    private ProjectService projectService;

    @Override
    public void initView(MyProjectsView view) {
        super.initView(view);
    }

    void displayMyProjects(ProjectSearchCriteria searchCriteria) {
        Collection<Integer> prjKeys = projectService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        if (!prjKeys.isEmpty()) {
            searchCriteria.setProjectKeys(new SetSearchField<>(prjKeys));
            List<?> projects = projectService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria, 0, Integer.MAX_VALUE));
            view.getProjectGrid().setItems(projects);
        }
    }
}
