package com.mycollab.premium.mobile.module.project.view.milestone;

import com.mycollab.common.GenericLinkUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.milestone.IMilestoneAddPresenter;
import com.mycollab.mobile.module.project.view.milestone.MilestoneAddView;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.service.MilestoneService;
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
public class MilestoneAddPresenter extends AbstractProjectPresenter<MilestoneAddView> implements IMilestoneAddPresenter {
    private static final long serialVersionUID = 1L;

    public MilestoneAddPresenter() {
        super(MilestoneAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleMilestone>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleMilestone milestone) {
                saveMilestone(milestone);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
            SimpleMilestone milestone = (SimpleMilestone) data.getParams();
            view.editItem(milestone);
            super.onGo(container, data);

            if (milestone.getId() == null) {
                AppUI.addFragment("project/milestone/add/" + GenericLinkUtils.INSTANCE.encodeParam(CurrentProjectVariables.getProjectId()),
                        UserUIContext.getMessage(MilestoneI18nEnum.NEW));
            } else {
                AppUI.addFragment("project/milestone/edit/" + GenericLinkUtils.INSTANCE.encodeParam(CurrentProjectVariables.getProjectId(),
                        milestone.getId()), milestone.getName());
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveMilestone(Milestone milestone) {
        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
        milestone.setProjectid(CurrentProjectVariables.getProjectId());
        milestone.setSaccountid(AppUI.getAccountId());

        if (milestone.getId() == null) {
            milestone.setCreateduser(UserUIContext.getUsername());
            milestoneService.saveWithSession(milestone, UserUIContext.getUsername());
        } else {
            milestoneService.updateWithSession(milestone, UserUIContext.getUsername());
        }

    }

}
