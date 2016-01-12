package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class ProjectAddPresenter extends AbstractMobilePresenter<ProjectAddView> {
    public ProjectAddPresenter() {
        super(ProjectAddView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (AppContext.canAccess(RolePermissionCollections.CREATE_NEW_PROJECT)) {
            super.onGo(container, data);
            SimpleProject project = (SimpleProject) data.getParams();
            view.editItem(project);

            if (project.getId() == null) {
                AppContext.addFragment("project/add", "New Project");
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
