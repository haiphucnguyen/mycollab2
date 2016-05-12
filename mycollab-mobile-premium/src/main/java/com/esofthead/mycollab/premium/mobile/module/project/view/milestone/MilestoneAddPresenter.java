package com.esofthead.mycollab.premium.mobile.module.project.view.milestone;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.esofthead.mycollab.mobile.module.project.view.milestone.IMilestoneAddPresenter;
import com.esofthead.mycollab.mobile.module.project.view.milestone.MilestoneAddView;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.DefaultEditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

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
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
            SimpleMilestone milestone = (SimpleMilestone) data.getParams();
            view.editItem(milestone);
            super.onGo(container, data);

            if (milestone.getId() == null) {
                AppContext.addFragment("project/milestone/add/" + GenericLinkUtils.encodeParam(CurrentProjectVariables.getProjectId()),
                        AppContext.getMessage(MilestoneI18nEnum.NEW));
            } else {
                AppContext.addFragment("project/milestone/edit/" + GenericLinkUtils.encodeParam(CurrentProjectVariables.getProjectId(),
                        milestone.getId()), milestone.getName());
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveMilestone(Milestone milestone) {
        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
        milestone.setProjectid(CurrentProjectVariables.getProjectId());
        milestone.setSaccountid(AppContext.getAccountId());

        if (milestone.getId() == null) {
            milestone.setCreateduser(AppContext.getUsername());
            milestoneService.saveWithSession(milestone, AppContext.getUsername());
        } else {
            milestoneService.updateWithSession(milestone, AppContext.getUsername());
        }

    }

}
