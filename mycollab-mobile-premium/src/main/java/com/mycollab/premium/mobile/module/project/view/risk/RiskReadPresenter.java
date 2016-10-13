package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.events.RiskEvent;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.mobile.ui.ConfirmDialog;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class RiskReadPresenter extends AbstractProjectPresenter<RiskReadView> {
    public RiskReadPresenter() {
        super(RiskReadView.class);
    }

    @Override
    protected void postInitView() {
        view.getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleRisk>() {

            @Override
            public void onDelete(final SimpleRisk data) {
                ConfirmDialog.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        dialog -> {
                            if (dialog.isConfirmed()) {
                                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                                riskService.removeWithSession(data, UserUIContext.getUsername(), MyCollabUI.getAccountId());
                                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
                            }
                        });
            }

            @Override
            public void onClone(final SimpleRisk data) {
                final Risk cloneData = (Risk) data.copy();
                cloneData.setId(null);
                EventBusFactory.getInstance().post(new RiskEvent.GotoEdit(this, cloneData));
            }
        });
    }

    @Override
    protected void onGo(final ComponentContainer container, final ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
            if (data.getParams() instanceof Integer) {
                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                SimpleRisk risk = riskService.findById((Integer) data.getParams(), MyCollabUI.getAccountId());

                if (risk != null) {
                    this.view.previewItem(risk);
                    super.onGo(container, data);

                    MyCollabUI.addFragment(ProjectLinkGenerator.generateRiskPreviewLink(risk.getProjectid(),
                            risk.getId()), risk.getName());
                } else {
                    NotificationUtil.showRecordNotExistNotification();
                }
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
