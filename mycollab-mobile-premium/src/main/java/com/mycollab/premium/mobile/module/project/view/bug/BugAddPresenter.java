package com.mycollab.premium.mobile.module.project.view.bug;

import com.mycollab.core.SecureAccessException;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.events.BugEvent;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.bug.BugAddView;
import com.mycollab.mobile.module.project.view.bug.IBugAddPresenter;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.HasComponents;

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
                Integer bugId = saveBug(bug);
                EventBusFactory.getInstance().post(new BugEvent.GotoRead(this, bugId));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS)) {
            super.onGo(container, data);
            SimpleBug bug = (SimpleBug) data.getParams();
            view.editItem(bug);
        } else {
            throw new SecureAccessException();
        }
    }

    private Integer saveBug(SimpleBug bug) {
        BugService bugService = AppContextUtil.getSpringBean(BugService.class);
        bug.setProjectid(CurrentProjectVariables.getProjectId());
        bug.setSaccountid(MyCollabUI.getAccountId());
        ProjectFormAttachmentUploadField uploadField = view.getAttachUploadField();
        if (bug.getId() == null) {
            bug.setStatus(BugStatus.Open.name());
            bug.setCreateduser(UserUIContext.getUsername());
            bug.setSaccountid(MyCollabUI.getAccountId());
            int bugId = bugService.saveWithSession(bug, UserUIContext.getUsername());
            uploadField.saveContentsToRepo(CurrentProjectVariables.getProjectId(), ProjectTypeConstants.BUG, bugId);
        } else {
            bugService.updateWithSession(bug, UserUIContext.getUsername());
            uploadField.saveContentsToRepo();
        }
        return bug.getId();
    }
}