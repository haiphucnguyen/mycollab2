/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Set;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.CampaignContact;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class CampaignContactListComp extends RelatedListComp<SimpleContact, ContactSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private CampaignWithBLOBs campaign;

    public CampaignContactListComp() {
        super("Contacts");
        initUI();
    }

    public void displayContacts(CampaignWithBLOBs campaign) {
        this.campaign = campaign;
        loadContacts();
    }

    private void loadContacts() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setCampaignId(new NumberSearchField(SearchField.AND, campaign
                .getId()));
        this.setSearchCriteria(criteria);
    }

    @SuppressWarnings("serial")
    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) bodyContent;
        contentContainer.setSpacing(true);

        final SplitButton controlsBtn = new SplitButton();
        controlsBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CONTACT));
        controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
        controlsBtn.setCaption("New Contact");
        controlsBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
        controlsBtn.addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(SplitButton.SplitButtonClickEvent event) {
                fireNewRelatedItem("");
            }
        });
        Button selectBtn = new Button("Select from existing contacts", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CampaignContactSelectionWindow contactsWindow = new CampaignContactSelectionWindow(CampaignContactListComp.this);
                ContactSearchCriteria criteria = new ContactSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
                getWindow().addWindow(contactsWindow);
                contactsWindow.setSearchCriteria(criteria);
                controlsBtn.setPopupVisible(false);
            }
        });
        selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
        selectBtn.setStyleName("link");
        controlsBtn.addComponent(selectBtn);
        controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CONTACT));
        contentContainer.addComponent(controlsBtn);

        tableItem = new ContactTableDisplay(
                new String[]{"contactName", "email", "officephone", "accountName", "id"},
                new String[]{"Name", "Email", "Phone", "Account", "Action"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleContact contact = (SimpleContact) event.getData();
                if ("contactName".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new ContactEvent.GotoRead(
                            CampaignContactListComp.this, contact
                            .getId()));
                }
            }
        });


        tableItem.addGeneratedColumn("id", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                final SimpleContact contact = (SimpleContact) tableItem.getBeanByIndex(itemId);
                HorizontalLayout controlLayout = new HorizontalLayout();
                Button editBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new ContactEvent.GotoEdit(
                                CampaignContactListComp.this, contact));
                    }
                });
                editBtn.setStyleName("link");
                editBtn.setIcon(new ThemeResource("icons/16/edit.png"));
                controlLayout.addComponent(editBtn);

                Button deleteBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        ConfirmDialog.show(AppContext.getApplication().getMainWindow(),
                                "Please Confirm:",
                                "Are you sure to delete this relationship? Only the relationship is removed. The record will not be deleted.",
                                "Yes", "No", new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    CampaignService campaignService = AppContext
                                            .getSpringBean(CampaignService.class);
                                    CampaignContact associateContact = new CampaignContact();
                                    associateContact.setContactid(contact.getId());
                                    associateContact.setCampaignid(campaign.getId());
                                    campaignService.removeCampaignContactRelationship(associateContact);
                                    CampaignContactListComp.this.refresh();
                                }
                            }
                        });
                    }
                });
                deleteBtn.setStyleName("link");
                deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
                controlLayout.addComponent(deleteBtn);
                return controlLayout;
            }
        });
        contentContainer.addComponent(tableItem);

    }

    @Override
    public void setSelectedItems(Set selectedItems) {
        fireSelectedRelatedItems(selectedItems);
    }

    @Override
    public void refresh() {
        loadContacts();
    }
}
