package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.IEditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
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
            riskContainer.addComponent(view.getWidget());
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
        RiskService riskService = ApplicationContextUtil.getSpringBean(RiskService.class);
        risk.setProjectid(CurrentProjectVariables.getProjectId());
        risk.setSaccountid(AppContext.getAccountId());

        if (risk.getId() == null) {
            riskService.saveWithSession(risk, AppContext.getUsername());
        } else {
            riskService.updateWithSession(risk, AppContext.getUsername());
        }
        return risk.getId();
    }
}
