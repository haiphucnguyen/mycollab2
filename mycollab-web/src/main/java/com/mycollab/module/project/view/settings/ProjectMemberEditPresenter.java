package com.mycollab.module.project.view.settings;

import com.mycollab.core.MyCollabException;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.domain.ProjectMember;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.event.ProjectMemberEvent;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectMemberEditPresenter extends AbstractPresenter<ProjectMemberEditView> {
    private static final long serialVersionUID = 1L;

    public ProjectMemberEditPresenter() {
        super(ProjectMemberEditView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<SimpleProjectMember>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleProjectMember projectMember) {
                saveProjectMember(projectMember);
                EventBusFactory.getInstance().post(new ProjectMemberEvent.GotoList(this, null));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new ProjectMemberEvent.GotoList(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleProjectMember projectMember) {

            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getUSERS())) {
            ProjectUserContainer userGroupContainer = (ProjectUserContainer) container;
            userGroupContainer.setContent(view);

            SimpleProjectMember member = (SimpleProjectMember) data.getParams();
            view.editItem(member);

            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            if (member.getId() == null) {
                breadcrumb.gotoUserAdd();
            } else {
                breadcrumb.gotoUserEdit(member);
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }

    }

    public void saveProjectMember(ProjectMember projectMember) {
        ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);

        if (projectMember.getId() == null) {
            throw new MyCollabException("User not exist in projectMember table, something goes wrong in DB");
        } else {
            projectMemberService.updateWithSession(projectMember, UserUIContext.getUsername());
        }
    }

}
