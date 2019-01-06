package com.mycollab.premium.mobile.module.project.view.settings;

import com.mycollab.common.GenericLinkUtils;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.settings.IProjectMemberInvitePresenter;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberInviteView;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
// TODO
public class ProjectMemberInvitePresenter extends AbstractProjectPresenter<ProjectMemberInviteView> implements IProjectMemberInvitePresenter {
    private static final long serialVersionUID = 1L;

    public ProjectMemberInvitePresenter() {
        super(ProjectMemberInviteView.class);
    }

    @Override
    protected void postInitView() {
//        getView().addViewListener(new ViewListener<ProjectMemberEvent.InviteProjectMembers>() {
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            public void receiveEvent(ViewEvent<ProjectMemberEvent.InviteProjectMembers> event) {
//                ProjectMemberEvent.InviteProjectMembers inviteMembers = (ProjectMemberEvent.InviteProjectMembers) event.getData();
//                ProjectMemberService projectMemberService = AppContextUtil.getSpringBean(ProjectMemberService.class);
//                List<String> inviteEmails = inviteMembers.getInviteEmails();
//                if (CollectionUtils.isNotEmpty(inviteEmails)) {
//                    projectMemberService.inviteProjectMembers(inviteEmails.toArray(new String[0]), CurrentProjectVariables.getProjectId(),
//                            inviteMembers.getRoleId(), UserUIContext.getUsername(), inviteMembers.getInviteMessage(), AppUI.getAccountId());
//                    EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
//                }
//
//            }
//        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.USERS)) {
            super.onGo(container, data);
            getView().display();
            AppUI.addFragment("project/user/invite/" + GenericLinkUtils.encodeParam(CurrentProjectVariables.getProjectId()),
                    UserUIContext.getMessage(ProjectMemberI18nEnum.NEW));

        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

}
