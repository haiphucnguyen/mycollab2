package com.mycollab.premium.mobile.module.project.view;

import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.IProjectAddPresenter;
import com.mycollab.mobile.module.project.view.ProjectAddView;
import com.mycollab.mobile.module.project.view.parameters.ProjectScreenData;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.PageActionChain;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

import static com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;

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
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleProject>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleProject project) {
                Integer prjId = saveProject(project);
                EventBusFactory.getInstance().post(new PageActionChain(new ProjectScreenData.Goto(prjId)));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canAccess(RolePermissionCollections.CREATE_NEW_PROJECT)) {
            super.onGo(container, data);
            SimpleProject project = (SimpleProject) data.getParams();
            if (project.getStatus() == null) {
                project.setStatus(StatusI18nEnum.Open.name());
            }
            getView().editItem(project);

            if (project.getId() == null) {
                AppUI.addFragment("project/add", "New Project");
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private Integer saveProject(SimpleProject project) {
        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        project.setSaccountid(AppUI.getAccountId());
        return projectService.saveWithSession(project, UserUIContext.getUsername());
    }
}
