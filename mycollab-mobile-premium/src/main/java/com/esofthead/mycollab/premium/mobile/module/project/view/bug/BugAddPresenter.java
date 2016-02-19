package com.esofthead.mycollab.premium.mobile.module.project.view.bug;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.esofthead.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.esofthead.mycollab.mobile.module.project.view.bug.BugAddView;
import com.esofthead.mycollab.mobile.module.project.view.bug.IBugAddPresenter;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkGenerator;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugResolution;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.DefaultEditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class BugAddPresenter extends AbstractProjectPresenter<BugAddView> implements IBugAddPresenter {
    private static final long serialVersionUID = 1L;

    public BugAddPresenter() {
        super(BugAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleBug>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleBug bug) {
                saveBug(bug);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS)) {
            super.onGo(container, data);
            SimpleBug bug = (SimpleBug) data.getParams();
            view.editItem(bug);

            if (bug.getId() == null) {
                AppContext.addFragment("project/bug/add/" + GenericLinkUtils.encodeParam(CurrentProjectVariables.getProjectId()),
                        AppContext.getMessage(BugI18nEnum.FORM_NEW_BUG_TITLE));
            } else {
                AppContext.addFragment(ProjectLinkGenerator.generateBugEditLink(bug.getBugkey(), bug.getProjectShortName()),
                        AppContext.getMessage(BugI18nEnum.FORM_EDIT_BUG_TITLE));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveBug(SimpleBug bug) {
        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
        bug.setProjectid(CurrentProjectVariables.getProjectId());
        bug.setSaccountid(AppContext.getAccountId());
        ProjectFormAttachmentUploadField uploadField = view.getAttachUploadField();
        if (bug.getId() == null) {
            bug.setStatus(BugStatus.Open.name());
            bug.setResolution(BugResolution.Newissue.name());
            bug.setLogby(AppContext.getUsername());
            bug.setSaccountid(AppContext.getAccountId());
            int bugId = bugService.saveWithSession(bug, AppContext.getUsername());
            uploadField.saveContentsToRepo(CurrentProjectVariables.getProjectId(), ProjectTypeConstants.BUG, bugId);
        } else {
            bugService.updateWithSession(bug, AppContext.getUsername());
            uploadField.saveContentsToRepo();
        }
    }
}