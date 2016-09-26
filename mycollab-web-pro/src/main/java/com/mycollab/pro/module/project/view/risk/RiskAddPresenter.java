package com.mycollab.pro.module.project.view.risk;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.event.RiskEvent;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class RiskAddPresenter extends AbstractPresenter<RiskAddView> {
    private static final long serialVersionUID = 1L;

    public RiskAddPresenter() {
        super(RiskAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<SimpleRisk>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleRisk risk) {
                int riskId = saveRisk(risk);
                EventBusFactory.getInstance().post(new RiskEvent.GotoRead(this, riskId));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new RiskEvent.GotoList(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleRisk risk) {
                saveRisk(risk);
                EventBusFactory.getInstance().post(new RiskEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {
            RiskContainer riskContainer = (RiskContainer) container;
            riskContainer.removeAllComponents();
            riskContainer.addComponent(view);
            SimpleRisk risk = (SimpleRisk) data.getParams();
            view.editItem(risk);

            ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            if (risk.getId() == null) {
                breadCrumb.gotoRiskAdd();
            } else {
                breadCrumb.gotoRiskEdit(risk);
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private int saveRisk(Risk risk) {
        RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
        risk.setProjectid(CurrentProjectVariables.getProjectId());
        risk.setSaccountid(MyCollabUI.getAccountId());

        if (risk.getId() == null) {
            riskService.saveWithSession(risk, UserUIContext.getUsername());
        } else {
            riskService.updateWithSession(risk, UserUIContext.getUsername());
        }
        AttachmentUploadField uploadField = view.getAttachUploadField();
        String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(MyCollabUI.getAccountId(), risk.getProjectid(),
                ProjectTypeConstants.RISK, "" + risk.getId());
        uploadField.saveContentsToRepo(attachPath);
        return risk.getId();
    }
}
