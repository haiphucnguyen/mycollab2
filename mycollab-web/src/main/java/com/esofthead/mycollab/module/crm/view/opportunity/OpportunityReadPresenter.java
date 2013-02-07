package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.OpportunityContact;
import com.esofthead.mycollab.module.crm.domain.OpportunityLead;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import org.vaadin.dialogs.ConfirmDialog;

public class OpportunityReadPresenter extends CrmGenericPresenter<OpportunityReadView> {

    private static final long serialVersionUID = 1L;

    public OpportunityReadPresenter() {
        super(OpportunityReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Opportunity>() {
                    @Override
                    public void onEdit(Opportunity data) {
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(final Opportunity data) {
                        ConfirmDialog.show(
                                view.getWindow(),
                                "Please Confirm:",
                                "Are you sure to delete opportunity '"
                                + data.getOpportunityname() + "' ?",
                                "Yes", "No", new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    OpportunityService OpportunityService = AppContext
                                            .getSpringBean(OpportunityService.class);
                                    OpportunityService.removeWithSession(
                                            data.getId(),
                                            AppContext.getUsername());
                                    EventBus.getInstance()
                                            .fireEvent(
                                            new OpportunityEvent.GotoList(
                                            this, null));
                                }
                            }
                        });
                    }

                    @Override
                    public void onClone(Opportunity data) {
                        Opportunity cloneData = (Opportunity) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoList(this, null));
                    }

                    @Override
                    public void gotoNext(Opportunity data) {
                        OpportunityService opportunityService = AppContext
                                .getSpringBean(OpportunityService.class);
                        OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
                        criteria.setSaccountid(new NumberSearchField(AppContext
                                .getAccountId()));
                        criteria.setId(new NumberSearchField(data.getId(),
                                NumberSearchField.GREATHER));
                        Integer nextId = opportunityService
                                .getNextItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance()
                                    .fireEvent(
                                    new OpportunityEvent.GotoRead(this,
                                    nextId));
                        } else {
                            view.getWindow().showNotification("Information",
                                    "You are already in the last record",
                                    Window.Notification.TYPE_HUMANIZED_MESSAGE);
                        }

                    }

                    @Override
                    public void gotoPrevious(Opportunity data) {
                        OpportunityService opportunityService = AppContext
                                .getSpringBean(OpportunityService.class);
                        OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
                        criteria.setSaccountid(new NumberSearchField(AppContext
                                .getAccountId()));
                        criteria.setId(new NumberSearchField(data.getId(),
                                NumberSearchField.LESSTHAN));
                        Integer nextId = opportunityService
                                .getPreviousItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance()
                                    .fireEvent(
                                    new OpportunityEvent.GotoRead(this,
                                    nextId));
                        } else {
                            view.getWindow().showNotification("Information",
                                    "You are already in the first record",
                                    Window.Notification.TYPE_HUMANIZED_MESSAGE);
                        }
                    }
                });

        view.getRelatedActivityHandlers().addRelatedListHandler(
                new AbstractRelatedListHandler() {
                    @Override
                    public void createNewRelatedItem(String itemId) {
                        if (itemId.equals("task")) {
                            Task task = new Task();
                            task.setType(CrmTypeConstants.OPPORTUNITY);
                            task.setTypeid(view.getItem().getId());
                            EventBus.getInstance()
                                    .fireEvent(
                                    new ActivityEvent.TaskEdit(
                                    OpportunityReadPresenter.this,
                                    task));
                        } else if (itemId.equals("meeting")) {
                            Meeting meeting = new Meeting();
                            meeting.setType(CrmTypeConstants.OPPORTUNITY);
                            meeting.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(
                                    new ActivityEvent.MeetingEdit(
                                    OpportunityReadPresenter.this,
                                    meeting));
                        } else if (itemId.equals("call")) {
                            Call call = new Call();
                            call.setType(CrmTypeConstants.OPPORTUNITY);
                            call.setTypeid(view.getItem().getId());
                            EventBus.getInstance()
                                    .fireEvent(
                                    new ActivityEvent.CallEdit(
                                    OpportunityReadPresenter.this,
                                    call));
                        }
                    }
                });
        
        view.getRelatedContactHandlers().addRelatedListHandler(new AbstractRelatedListHandler<SimpleContact>(){

            @Override
            public void createNewRelatedItem(String itemId) {
                Contact contact = new Contact();
                contact.setExtraData(view.getItem());
                EventBus.getInstance().fireEvent(new ContactEvent.GotoEdit(OpportunityReadPresenter.this, contact));
            }

            @Override
            public void selectAssociateItems(Set<SimpleContact> items) {
                List<OpportunityContact> associateContacts = new ArrayList<OpportunityContact>();
                SimpleOpportunity opportunity = view.getItem();
                for (SimpleContact contact : items) {
                    OpportunityContact associateContact = new OpportunityContact();
                    associateContact.setContactid(contact.getId());
                    associateContact.setOpportunityid(opportunity.getId());
                    associateContact.setCreatedtime(new GregorianCalendar().getTime());
                    associateContacts.add(associateContact);
                }
                
                OpportunityService opportunityService = AppContext.getSpringBean(OpportunityService.class);
                opportunityService.saveOpportunityContactRelationship(associateContacts);
                view.getRelatedContactHandlers().refresh();
            }
        });
        
        view.getRelatedLeadHandlers().addRelatedListHandler(new AbstractRelatedListHandler<SimpleLead>() {
            @Override
            public void createNewRelatedItem(String itemId) {
                Lead lead = new Lead();
                lead.setExtraData(view.getItem());
                EventBus.getInstance().fireEvent(new LeadEvent.GotoEdit(OpportunityReadPresenter.this, lead));
            }
            
            @Override
            public void selectAssociateItems(Set<SimpleLead> items) {
                SimpleOpportunity opportunity = view.getItem();
                List<OpportunityLead> associateLeads = new ArrayList<OpportunityLead>();
                for (SimpleLead lead : items) {
                    OpportunityLead associateLead = new OpportunityLead();
                    associateLead.setLeadid(lead.getId());
                    associateLead.setOpportunityid(opportunity.getId());
                    associateLead.setCreatedtime(new GregorianCalendar().getTime());
                    associateLeads.add(associateLead);
                }
                
                OpportunityService opportunityService = AppContext.getSpringBean(OpportunityService.class);
                opportunityService.saveOpportunityLeadRelationship(associateLeads);
                view.getRelatedLeadHandlers().refresh();
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (data.getParams() instanceof Integer) {
            OpportunityService opportunityService = AppContext
                    .getSpringBean(OpportunityService.class);
            SimpleOpportunity opportunity = opportunityService
                    .findOpportunityById((Integer) data.getParams());
            if (opportunity != null) {
                super.onGo(container, data);
                view.previewItem(opportunity);
                
                AppContext.addFragment("crm/opportunity/preview/" + UrlEncodeDecoder.encode(opportunity.getId()));
            } else {
                AppContext
                        .getApplication()
                        .getMainWindow()
                        .showNotification("Information",
                        "The record is not existed",
                        Window.Notification.TYPE_HUMANIZED_MESSAGE);
                return;
            }
        }
    }
}
