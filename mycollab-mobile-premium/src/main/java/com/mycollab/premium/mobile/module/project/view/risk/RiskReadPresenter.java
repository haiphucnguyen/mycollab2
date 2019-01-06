package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.common.ModuleNameConstants;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.mobile.module.project.event.RiskEvent;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.shell.event.ShellEvent;
import com.mycollab.mobile.ui.ConfirmDialog;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.event.UpdateNotificationItemReadStatusEvent;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.spring.AppEventBus;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;
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
        getView().getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleRisk>() {

            @Override
            public void onDelete(final SimpleRisk data) {
                ConfirmDialog.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_YES),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_NO),
                        dialog -> {
                            if (dialog.isConfirmed()) {
                                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                                riskService.removeWithSession(data, UserUIContext.getUsername(), AppUI.getAccountId());
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
    protected void onGo(final HasComponents container, final ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
            if (data.getParams() instanceof Integer) {
                RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                SimpleRisk risk = riskService.findById((Integer) data.getParams(), AppUI.getAccountId());

                if (risk != null) {
                    this.getView().previewItem(risk);
                    super.onGo(container, data);

                    AppEventBus.getInstance().post(new UpdateNotificationItemReadStatusEvent(UserUIContext.getUsername(),
                            ModuleNameConstants.PRJ, ProjectTypeConstants.RISK, risk.getId().toString()));
                } else {
                    NotificationUtil.showRecordNotExistNotification();
                }
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
