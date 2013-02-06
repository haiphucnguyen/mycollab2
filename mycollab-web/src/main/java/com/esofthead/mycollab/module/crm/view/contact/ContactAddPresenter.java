package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.CampaignContact;
import com.esofthead.mycollab.module.crm.domain.OpportunityContact;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
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

public class ContactAddPresenter extends CrmGenericPresenter<ContactAddView> {

    private static final long serialVersionUID = 1L;

    public ContactAddPresenter() {
        super(ContactAddView.class);
        bind();
    }

    private void bind() {
        view.getEditFormHandlers().addFormHandler(
                new EditFormHandler<SimpleContact>() {
                    @Override
                    public void onSave(final SimpleContact contact) {
                        saveContact(contact);
                        ViewState viewState = HistoryViewManager.back();

                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new ContactEvent.GotoList(this, null));
                        }

                    }

                    @Override
                    public void onCancel() {
                        ViewState viewState = HistoryViewManager.back();
                        if (viewState instanceof NullViewState) {
                            EventBus.getInstance().fireEvent(
                                    new ContactEvent.GotoList(this, null));
                        }
                    }

                    @Override
                    public void onSaveAndNew(final SimpleContact contact) {
                        saveContact(contact);
                        EventBus.getInstance().fireEvent(
                                new ContactEvent.GotoAdd(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        super.onGo(container, data);
        view.editItem((SimpleContact) data.getParams());
    }

    public void saveContact(SimpleContact contact) {
        ContactService contactService = AppContext
                .getSpringBean(ContactService.class);

        contact.setSaccountid(AppContext.getAccountId());
        if (contact.getId() == null) {
            contactService.saveWithSession(contact, AppContext.getUsername());
            
            if (contact.getExtraData() != null && contact.getExtraData() instanceof SimpleCampaign) {
                CampaignContact associateContact = new CampaignContact();
                associateContact.setCampaignid(((SimpleCampaign)contact.getExtraData()).getId());
                associateContact.setContactid(contact.getId());
                associateContact.setCreatedtime(new GregorianCalendar().getTime());
                
                CampaignService campaignService = AppContext.getSpringBean(CampaignService.class);
                campaignService.saveCampaignContactRelationship(Arrays.asList(associateContact));
            } else if (contact.getExtraData() != null && contact.getExtraData() instanceof SimpleOpportunity) {
                OpportunityContact associateContact = new OpportunityContact();
                associateContact.setContactid(contact.getId());
                associateContact.setOpportunityid(((SimpleOpportunity)contact.getExtraData()).getId());
                associateContact.setCreatedtime(new GregorianCalendar().getTime());
                OpportunityService opportunityService = AppContext.getSpringBean(OpportunityService.class);
                opportunityService.saveOpportunityContactRelationship(Arrays.asList(associateContact));
            }
            
        } else {
            contactService.updateWithSession(contact, AppContext.getUsername());
        }



    }
}
