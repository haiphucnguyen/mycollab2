package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import org.vaadin.dialogs.ConfirmDialog;

public class ContactReadPresenter extends CrmGenericPresenter<ContactReadView> {

    private static final long serialVersionUID = 1L;

    public ContactReadPresenter() {
        super(ContactReadView.class);
        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<Contact>() {
                    @Override
                    public void onEdit(Contact data) {
                        EventBus.getInstance().fireEvent(
                                new ContactEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(final Contact data) {

                        ConfirmDialog.show(view.getWindow(),
                                "Please Confirm:",
                                "Are you sure to delete contact '" + data.getFirstname() + " " + data.getLastname() + "' ?",
                                "Yes", "No", new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    ContactService ContactService = AppContext
                                            .getSpringBean(ContactService.class);
                                    ContactService.removeWithSession(data.getId(),
                                            AppContext.getUsername());
                                    EventBus.getInstance().fireEvent(
                                            new ContactEvent.GotoList(this, null));
                                }
                            }
                        });
                    }

                    @Override
                    public void onClone(Contact data) {
                        Contact cloneData = (Contact) data.copy();
                        cloneData.setId(null);
                        EventBus.getInstance().fireEvent(
                                new ContactEvent.GotoEdit(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new ContactEvent.GotoList(this, null));
                    }

                    @Override
                    public void gotoNext(Contact data) {
                        ContactService contactService = AppContext
                                .getSpringBean(ContactService.class);
                        ContactSearchCriteria criteria = new ContactSearchCriteria();
                        criteria.setSaccountid(new NumberSearchField(AppContext
                                .getAccountId()));
                        criteria.setId(new NumberSearchField(data.getId(),
                                NumberSearchField.GREATHER));
                        Integer nextId = contactService
                                .getNextItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance().fireEvent(
                                    new ContactEvent.GotoRead(this, nextId));
                        } else {
                            view.getWindow().showNotification("Information",
                                    "You are already in the last record",
                                    Window.Notification.TYPE_HUMANIZED_MESSAGE);
                        }

                    }

                    @Override
                    public void gotoPrevious(Contact data) {
                        ContactService contactService = AppContext
                                .getSpringBean(ContactService.class);
                        ContactSearchCriteria criteria = new ContactSearchCriteria();
                        criteria.setSaccountid(new NumberSearchField(AppContext
                                .getAccountId()));
                        criteria.setId(new NumberSearchField(data.getId(),
                                NumberSearchField.LESSTHAN));
                        Integer nextId = contactService
                                .getPreviousItemKey(criteria);
                        if (nextId != null) {
                            EventBus.getInstance().fireEvent(
                                    new ContactEvent.GotoRead(this, nextId));
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
                            task.setType(CrmTypeConstants.CONTACT);
                            task.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(
                                    new ActivityEvent.TaskEdit(
                                    ContactReadPresenter.this, task));
                        } else if (itemId.equals("meeting")) {
                            Meeting meeting = new Meeting();
                            meeting.setType(CrmTypeConstants.CONTACT);
                            meeting.setTypeid(view.getItem().getId());
                            EventBus.getInstance()
                                    .fireEvent(
                                    new ActivityEvent.MeetingEdit(
                                    ContactReadPresenter.this,
                                    meeting));
                        } else if (itemId.equals("call")) {
                            Call call = new Call();
                            call.setType(CrmTypeConstants.CONTACT);
                            call.setTypeid(view.getItem().getId());
                            EventBus.getInstance().fireEvent(
                                    new ActivityEvent.CallEdit(
                                    ContactReadPresenter.this, call));
                        }
                    }
                });

        view.getRelatedOpportunityHandlers().addRelatedListHandler(new AbstractRelatedListHandler<SimpleOpportunity>() {
            @Override
            public void createNewRelatedItem(String itemId) {
                Opportunity opportunity = new Opportunity();
                opportunity.setExtraData(view.getItem());
                EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoEdit(this, opportunity));
            }

            @Override
            public void selectAssociateItems(Set<SimpleOpportunity> items) {
                if (items.size() > 0) {
                    SimpleContact contact = view.getItem();
                    List<ContactOpportunity> associateOpportunities = new ArrayList<ContactOpportunity>();
                    for (SimpleOpportunity opportunity : items) {
                        ContactOpportunity assoOpportunity = new ContactOpportunity();
                        assoOpportunity.setOpportunityid(opportunity.getId());
                        assoOpportunity.setContactid(contact.getId());
                        assoOpportunity.setCreatedtime(new GregorianCalendar().getTime());
                        associateOpportunities.add(assoOpportunity);
                    }

                    ContactService contactService = AppContext.getSpringBean(ContactService.class);
                    contactService.saveContactOpportunityRelationship(associateOpportunities);

                    view.getRelatedOpportunityHandlers().refresh();
                }
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
    	if (AppContext.canRead(RolePermissionCollections.CRM_CONTACT)) {
    		 if (data.getParams() instanceof Integer) {
    	            ContactService contactService = AppContext
    	                    .getSpringBean(ContactService.class);
    	            SimpleContact contact = contactService
    	                    .findContactById((Integer) data.getParams());
    	            if (contact != null) {
    	                super.onGo(container, data);
    	                view.previewItem(contact);
    	                
    	                AppContext.addFragment("crm/contact/preview/" + UrlEncodeDecoder.encode(contact.getId()));
    	            } else {
    	                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
    	                return;
    	            }
    	        }
    	} else {
    		MessageConstants.showMessagePermissionAlert();
    	}

    }
}
