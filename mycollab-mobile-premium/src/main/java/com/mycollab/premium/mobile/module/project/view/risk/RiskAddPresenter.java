package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class RiskAddPresenter extends AbstractProjectPresenter<RiskAddView> {
    private static final long serialVersionUID = -1243069642966773053L;

    public RiskAddPresenter() {
        super(RiskAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleRisk>() {
            private static final long serialVersionUID = 9034340428921755073L;

            @Override
            public void onSave(SimpleRisk bean) {
                saveRisk(bean);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {
            SimpleRisk risk = (SimpleRisk) data.getParams();
            view.editItem(risk);
            super.onGo(navigator, data);
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveRisk(SimpleRisk risk) {
        RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);

        risk.setSaccountid(MyCollabUI.getAccountId());
        risk.setProjectid(CurrentProjectVariables.getProjectId());

        if (risk.getId() == null) {
            risk.setCreateduser(UserUIContext.getUsername());
            int riskId = riskService.saveWithSession(risk, UserUIContext.getUsername());
            ProjectFormAttachmentUploadField uploadField = view.getAttachUploadField();
            uploadField.saveContentsToRepo(CurrentProjectVariables.getProjectId(), ProjectTypeConstants.RISK, riskId);
        } else {
            riskService.updateWithSession(risk, UserUIContext.getUsername());
            ProjectFormAttachmentUploadField uploadField = view.getAttachUploadField();
            uploadField.saveContentsToRepo();
        }
    }
}
