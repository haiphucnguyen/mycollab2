package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.ModuleNameConstants;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.event.RiskEvent;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.module.project.event.UpdateNotificationItemReadStatusEvent;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.spring.AppEventBus;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.reporting.FormReportLayout;
import com.mycollab.vaadin.reporting.PrintButton;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class RiskReadPresenter extends AbstractPresenter<RiskReadView> {
    private static final long serialVersionUID = 1L;

    public RiskReadPresenter() {
        super(RiskReadView.class);
    }

    @Override
    protected void postInitView() {
        view.getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleRisk>() {
            @Override
            public void onEdit(SimpleRisk data) {
                EventBusFactory.getInstance().post(new RiskEvent.GotoEdit(this, data));
            }

            @Override
            public void onAdd(SimpleRisk data) {
                EventBusFactory.getInstance().post(new RiskEvent.GotoAdd(this, null));
            }

            @Override
            public void onDelete(final SimpleRisk data) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_YES),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                                riskService.removeWithSession(data, UserUIContext.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new TicketEvent.GotoDashboard(this, null));
                            }
                        });
            }

            @Override
            public void onClone(SimpleRisk data) {
                Risk cloneData = (Risk) data.copy();
                cloneData.setId(null);
                EventBusFactory.getInstance().post(new RiskEvent.GotoEdit(this, cloneData));
            }

            @Override
            public void onPrint(Object source, SimpleRisk data) {
                PrintButton btn = (PrintButton) source;
                btn.doPrint(data, new FormReportLayout(ProjectTypeConstants.RISK, Risk.Field.name.name(),
                        RiskDefaultFormLayoutFactory.getForm(), Risk.Field.id.name()));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new TicketEvent.GotoDashboard(this, null));
            }

            @Override
            public void gotoNext(SimpleRisk data) {
                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                RiskSearchCriteria criteria = new RiskSearchCriteria();
                criteria.setProjectId(NumberSearchField.equal(CurrentProjectVariables.getProjectId()));
                criteria.setId(NumberSearchField.greaterThan(data.getId()));
                Integer nextId = riskService.getNextItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new RiskEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoLastRecordNotification();
                }

            }

            @Override
            public void gotoPrevious(SimpleRisk data) {
                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                RiskSearchCriteria criteria = new RiskSearchCriteria();
                criteria.setProjectId(NumberSearchField.equal(CurrentProjectVariables.getProjectId()));
                criteria.setId(NumberSearchField.lessThan(data.getId()));
                Integer nextId = riskService.getPreviousItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new RiskEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoFirstRecordNotification();
                }
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
            if (data.getParams() instanceof Integer) {
                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                SimpleRisk risk = riskService.findById((Integer) data.getParams(), AppUI.getAccountId());
                if (risk != null) {
                    RiskContainer riskContainer = (RiskContainer) container;
                    riskContainer.removeAllComponents();
                    riskContainer.addComponent(view);
                    view.previewItem(risk);

                    ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
                    breadCrumb.gotoRiskRead(risk);

                    AppEventBus.getInstance().post(new UpdateNotificationItemReadStatusEvent(UserUIContext.getUsername(),
                            ModuleNameConstants.PRJ, ProjectTypeConstants.RISK, risk.getId().toString()));
                } else {
                    NotificationUtil.showRecordNotExistNotification();
                }
            } else {
                throw new MyCollabException("Unhandle this case yet");
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
