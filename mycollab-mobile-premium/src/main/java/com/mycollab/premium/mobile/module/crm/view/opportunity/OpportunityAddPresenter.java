package com.mycollab.premium.mobile.module.crm.view.opportunity;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.event.OpportunityEvent;
import com.mycollab.mobile.module.crm.view.AbstractCrmPresenter;
import com.mycollab.mobile.module.crm.view.opportunity.IOpportunityAddPresenter;
import com.mycollab.mobile.module.crm.view.opportunity.OpportunityAddView;
import com.mycollab.mobile.shell.event.ShellEvent;
import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.service.OpportunityService;
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
public class OpportunityAddPresenter extends AbstractCrmPresenter<OpportunityAddView> implements IOpportunityAddPresenter {
    private static final long serialVersionUID = 5202691686429793555L;

    public OpportunityAddPresenter() {
        super(OpportunityAddView.class);
    }

    @Override
    protected void postInitView() {
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleOpportunity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleOpportunity item) {
                saveOpportunity(item);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleOpportunity item) {
                saveOpportunity(item);
                EventBusFactory.getInstance().post(new OpportunityEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY)) {
            SimpleOpportunity opportunity = null;
            if (data.getParams() instanceof SimpleOpportunity) {
                opportunity = (SimpleOpportunity) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                OpportunityService accountService = AppContextUtil.getSpringBean(OpportunityService.class);
                opportunity = accountService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }
            if (opportunity == null) {
                NotificationUtil.showRecordNotExistNotification();
                return;
            }
            super.onGo(container, data);
            getView().editItem(opportunity);

            if (opportunity.getId() == null) {
                AppUI.addFragment("crm/opportunity/add", UserUIContext.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE, "Opportunity"));
            } else {
                AppUI.addFragment("crm/opportunity/edit/" + UrlEncodeDecoder.encode(opportunity.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, "Opportunity", opportunity.getOpportunityname()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveOpportunity(Opportunity opportunity) {
        OpportunityService opportunityService = AppContextUtil.getSpringBean(OpportunityService.class);

        opportunity.setSaccountid(AppUI.getAccountId());
        if (opportunity.getId() == null) {
            opportunityService.saveWithSession(opportunity, UserUIContext.getUsername());
        } else {
            opportunityService.updateWithSession(opportunity, UserUIContext.getUsername());
        }
    }
}
