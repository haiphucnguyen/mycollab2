package com.mycollab.premium.mobile.module.project.view.bug;

import com.mycollab.common.GenericLinkUtils;
import com.mycollab.core.SecureAccessException;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.bug.BugAddView;
import com.mycollab.mobile.module.project.view.bug.IBugAddPresenter;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.events.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
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
                        AppContext.getMessage(BugI18nEnum.NEW));
            } else {
                AppContext.addFragment(ProjectLinkGenerator.generateBugEditLink(bug.getBugkey(), bug.getProjectShortName()),
                        AppContext.getMessage(BugI18nEnum.DETAIL));
            }
        } else {
            throw new SecureAccessException();
        }
    }

    private void saveBug(SimpleBug bug) {
        BugService bugService = AppContextUtil.getSpringBean(BugService.class);
        bug.setProjectid(CurrentProjectVariables.getProjectId());
        bug.setSaccountid(AppContext.getAccountId());
        ProjectFormAttachmentUploadField uploadField = view.getAttachUploadField();
        if (bug.getId() == null) {
            bug.setStatus(BugStatus.Open.name());
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