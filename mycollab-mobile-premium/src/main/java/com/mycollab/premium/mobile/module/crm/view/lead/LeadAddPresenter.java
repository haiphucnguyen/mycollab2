package com.mycollab.premium.mobile.module.crm.view.lead;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.events.LeadEvent;
import com.mycollab.mobile.module.crm.view.AbstractCrmPresenter;
import com.mycollab.mobile.module.crm.view.lead.ILeadAddPresenter;
import com.mycollab.mobile.module.crm.view.lead.LeadAddView;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.module.crm.domain.Lead;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.i18n.LeadI18nEnum;
import com.mycollab.module.crm.service.LeadService;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class LeadAddPresenter extends AbstractCrmPresenter<LeadAddView> implements ILeadAddPresenter {
    private static final long serialVersionUID = -873280663492245087L;

    public LeadAddPresenter() {
        super(LeadAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleLead>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleLead lead) {
                saveLead(lead);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleLead lead) {
                saveLead(lead);
                EventBusFactory.getInstance().post(new LeadEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canWrite(RolePermissionCollections.CRM_LEAD)) {
            SimpleLead lead = null;
            if (data.getParams() instanceof SimpleLead) {
                lead = (SimpleLead) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                LeadService leadService = AppContextUtil.getSpringBean(LeadService.class);
                lead = leadService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }
            if (lead == null) {
                NotificationUtil.showRecordNotExistNotification();
                return;
            }
            super.onGo(container, data);
            view.editItem(lead);

            if (lead.getId() == null) {
                AppUI.addFragment("crm/lead/add", UserUIContext.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
                        UserUIContext.getMessage(LeadI18nEnum.SINGLE)));
            } else {
                AppUI.addFragment("crm/lead/edit/" + UrlEncodeDecoder.encode(lead.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
                                UserUIContext.getMessage(LeadI18nEnum.SINGLE), lead.getLastname()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }

    }

    private void saveLead(Lead lead) {
        LeadService leadService = AppContextUtil.getSpringBean(LeadService.class);
        lead.setSaccountid(AppUI.getAccountId());
        if (lead.getId() == null) {
            leadService.saveWithSession(lead, UserUIContext.getUsername());
        } else {
            leadService.updateWithSession(lead, UserUIContext.getUsername());
        }
    }
}
