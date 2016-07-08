package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.events.RiskEvent;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.reporting.FormReportLayout;
import com.mycollab.reporting.PrintButton;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.ComponentContainer;
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
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppContext.getSiteName()),
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                        AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                                riskService.removeWithSession(data, AppContext.getUsername(), AppContext.getAccountId());
                                EventBusFactory.getInstance().post(new RiskEvent.GotoList(this, null));
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
                btn.doPrint(data, new FormReportLayout(ProjectTypeConstants.RISK, Risk.Field.riskname.name(),
                        RiskDefaultFormLayoutFactory.getForm(), Risk.Field.id.name()));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new RiskEvent.GotoList(this, null));
            }

            @Override
            public void gotoNext(SimpleRisk data) {
                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                RiskSearchCriteria criteria = new RiskSearchCriteria();
                criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.GREATER));
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
                criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.LESSTHAN));
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
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
            if (data.getParams() instanceof Integer) {
                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                SimpleRisk risk = riskService.findById((Integer) data.getParams(), AppContext.getAccountId());
                if (risk != null) {
                    RiskContainer riskContainer = (RiskContainer) container;
                    riskContainer.removeAllComponents();
                    riskContainer.addComponent(view);
                    view.previewItem(risk);

                    ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
                    breadCrumb.gotoRiskRead(risk);
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