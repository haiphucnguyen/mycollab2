package com.mycollab.premium.mobile.module.project.view.bug;

import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.core.SecureAccessException;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.mobile.module.project.event.BugEvent;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.bug.BugAddView;
import com.mycollab.mobile.module.project.view.bug.IBugAddPresenter;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.HasComponents;

import static com.mycollab.common.i18n.OptionI18nEnum.*;

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
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleBug>() {
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
            SimpleBug bug = (SimpleBug) data.getParams();
            getView().editItem(bug);
            super.onGo(container, data);
        } else {
            throw new SecureAccessException();
        }
    }

    private Integer saveBug(SimpleBug bug) {
        BugService bugService = AppContextUtil.getSpringBean(BugService.class);
        bug.setProjectid(CurrentProjectVariables.getProjectId());
        bug.setSaccountid(AppUI.getAccountId());
        ProjectFormAttachmentUploadField uploadField = getView().getAttachUploadField();
        if (bug.getId() == null) {
            bug.setStatus(StatusI18nEnum.Open.name());
            bug.setCreateduser(UserUIContext.getUsername());
            bug.setSaccountid(AppUI.getAccountId());
            int bugId = bugService.saveWithSession(bug, UserUIContext.getUsername());
            uploadField.saveContentsToRepo(CurrentProjectVariables.getProjectId(), ProjectTypeConstants.BUG, bugId);
        } else {
            bugService.updateWithSession(bug, UserUIContext.getUsername());
            uploadField.saveContentsToRepo();
        }
        return bug.getId();
    }
}