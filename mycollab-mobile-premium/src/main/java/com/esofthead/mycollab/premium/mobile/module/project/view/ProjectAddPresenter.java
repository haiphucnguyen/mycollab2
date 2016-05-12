package com.esofthead.mycollab.premium.mobile.module.project.view;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.esofthead.mycollab.mobile.module.project.view.IProjectAddPresenter;
import com.esofthead.mycollab.mobile.module.project.view.ProjectAddView;
import com.esofthead.mycollab.mobile.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.DefaultEditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class ProjectAddPresenter extends AbstractProjectPresenter<ProjectAddView> implements IProjectAddPresenter {
    public ProjectAddPresenter() {
        super(ProjectAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleProject>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleProject project) {
                Integer prjId = saveProject(project);
                EventBusFactory.getInstance().post(new PageActionChain(new ProjectScreenData.Goto(prjId)));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (AppContext.canAccess(RolePermissionCollections.CREATE_NEW_PROJECT)) {
            super.onGo(container, data);
            SimpleProject project = (SimpleProject) data.getParams();
            if (project.getProjectstatus() == null) {
                project.setProjectstatus(OptionI18nEnum.StatusI18nEnum.Open.name());
            }
            view.editItem(project);

            if (project.getId() == null) {
                AppContext.addFragment("project/add", "New Project");
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private Integer saveProject(SimpleProject project) {
        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        project.setSaccountid(AppContext.getAccountId());
        return projectService.saveWithSession(project, AppContext.getUsername());
    }
}
