package com.mycollab.module.crm.view.opportunity;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.core.SecureAccessException;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.Opportunity;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.event.OpportunityEvent;
import com.mycollab.module.crm.i18n.OpportunityI18nEnum;
import com.mycollab.module.crm.service.OpportunityService;
import com.mycollab.module.crm.view.CrmGenericPresenter;
import com.mycollab.module.crm.view.CrmModule;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class OpportunityAddPresenter extends CrmGenericPresenter<OpportunityAddView> {
    private static final long serialVersionUID = 1L;

    public OpportunityAddPresenter() {
        super(OpportunityAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<SimpleOpportunity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleOpportunity item) {
                int opportunityId = saveOpportunity(item);
                EventBusFactory.getInstance().post(new OpportunityEvent.GotoRead(this, opportunityId));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new OpportunityEvent.GotoList(this, null));
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
        CrmModule.navigateItem(CrmTypeConstants.INSTANCE.getOPPORTUNITY());
        if (UserUIContext.canWrite(RolePermissionCollections.INSTANCE.getCRM_OPPORTUNITY())) {
            SimpleOpportunity opportunity = null;
            if (data.getParams() instanceof SimpleOpportunity) {
                opportunity = (SimpleOpportunity) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                OpportunityService accountService = AppContextUtil.getSpringBean(OpportunityService.class);
                opportunity = accountService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }
            if (opportunity == null) {
                throw new ResourceNotFoundException();
            }
            super.onGo(container, data);
            view.editItem(opportunity);

            if (opportunity.getId() == null) {
                AppUI.addFragment("crm/opportunity/add", UserUIContext.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
                        UserUIContext.getMessage(OpportunityI18nEnum.SINGLE)));
            } else {
                AppUI.addFragment("crm/opportunity/edit/" + UrlEncodeDecoder.encode(opportunity.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
                                UserUIContext.getMessage(OpportunityI18nEnum.SINGLE), opportunity.getOpportunityname()));
            }
        } else {
            throw new SecureAccessException();
        }
    }

    private int saveOpportunity(Opportunity opportunity) {
        OpportunityService opportunityService = AppContextUtil.getSpringBean(OpportunityService.class);
        opportunity.setSaccountid(AppUI.getAccountId());
        if (opportunity.getId() == null) {
            opportunityService.saveWithSession(opportunity, UserUIContext.getUsername());
        } else {
            opportunityService.updateWithSession(opportunity, UserUIContext.getUsername());
        }
        return opportunity.getId();
    }
}
