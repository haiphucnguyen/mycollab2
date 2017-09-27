package com.mycollab.premium.mobile.module.project.view.settings;

import com.mycollab.common.GenericLinkUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.settings.IProjectMemberEditPresenter;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberEditView;
import com.mycollab.mobile.shell.event.ShellEvent;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class ProjectMemberEditPresenter extends AbstractProjectPresenter<ProjectMemberEditView> implements
        IProjectMemberEditPresenter {
    private static final long serialVersionUID = -209370866970403913L;

    public ProjectMemberEditPresenter() {
        super(ProjectMemberEditView.class);
    }

    @Override
    protected void postInitView() {
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleProjectMember>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleProjectMember projectMember) {
                saveProjectMember(projectMember);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.USERS)) {
            SimpleProjectMember member = (SimpleProjectMember) data.getParams();
            super.onGo(container, data);
            getView().editItem(member);

            AppUI.addFragment("project/user/edit/" + GenericLinkUtils.encodeParam(CurrentProjectVariables.getProjectId(),
                    member.getId()), member.getDisplayName());
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }

    }

    public void saveProjectMember(SimpleProjectMember projectMember) {
        ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
        projectMemberService.updateWithSession(projectMember, UserUIContext.getUsername());

    }

}
