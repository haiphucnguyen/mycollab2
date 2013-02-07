package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class OpportunityAddPresenter extends CrmGenericPresenter<OpportunityAddView> {

    private static final long serialVersionUID = 1L;

    public OpportunityAddPresenter() {
        super(OpportunityAddView.class);
        bind();
    }

    private void bind() {
        view.getEditFormHandlers().addFormHandler(
                new EditFormHandler<Opportunity>() {
                    @Override
                    public void onSave(final Opportunity item) {
                        saveOpportunity(item);
                        ViewState viewState = HistoryViewManager.back();
                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new OpportunityEvent.GotoList(this, null));
                        }
                    }

                    @Override
                    public void onCancel() {
                        ViewState viewState = HistoryViewManager.back();
                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new OpportunityEvent.GotoList(this, null));
                        }
                    }

                    @Override
                    public void onSaveAndNew(final Opportunity item) {
                        saveOpportunity(item);
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoAdd(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        Opportunity opportunity = (Opportunity) data.getParams();
        view.editItem(opportunity);
        
        if (opportunity.getId() == null) {
            AppContext.addFragment("crm/opportunity/add");
        } else {
            AppContext.addFragment("crm/opportunity/edit/" + UrlEncodeDecoder.encode(opportunity.getId()));
        }
    }

    public void saveOpportunity(Opportunity opportunity) {
        OpportunityService opportunityService = AppContext
                .getSpringBean(OpportunityService.class);

        opportunity.setSaccountid(AppContext.getAccountId());
        if (opportunity.getId() == null) {
            opportunityService.saveWithSession(opportunity,
                    AppContext.getUsername());
            
            if ((opportunity.getExtraData() != null) && (opportunity.getExtraData() instanceof SimpleContact)) {
                ContactOpportunity associateOpportunity = new ContactOpportunity();
                associateOpportunity.setOpportunityid(opportunity.getId());
                associateOpportunity.setContactid(((SimpleContact)opportunity.getExtraData()).getId());
                associateOpportunity.setCreatedtime(new GregorianCalendar().getTime());
                ContactService contactService = AppContext.getSpringBean(ContactService.class);
                contactService.saveContactOpportunityRelationship(Arrays.asList(associateOpportunity));
            }
        } else {
            opportunityService.updateWithSession(opportunity,
                    AppContext.getUsername());
        }

    }
}
