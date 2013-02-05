/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityTableDisplay;
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
import java.util.Set;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.SplitButton;

/**
 *
 * @author haiphucnguyen
 */
public class ContactOpportunityListComp extends RelatedListComp<SimpleOpportunity, OpportunitySearchCriteria> {

    private static final long serialVersionUID = 1L;

    private SimpleContact contact;
    
    public ContactOpportunityListComp() {
        super("Opportunities");
        initUI();
    }
    
     public void displayOpportunities(SimpleContact contact) {
        this.contact = contact;
        loadOpportunities();
    }
     
     private void loadOpportunities() {
        OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setContactId(new NumberSearchField(SearchField.AND, contact
                .getId()));
        this.setSearchCriteria(criteria);
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) bodyContent;
        contentContainer.setSpacing(true);

        final SplitButton controlsBtn = new SplitButton();
        controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
        controlsBtn.setCaption("New Opportunity");
        controlsBtn.setIcon(new ThemeResource("icons/16/addRecordGreen.png"));
        controlsBtn.addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(SplitButton.SplitButtonClickEvent event) {
                fireNewRelatedItem("");
            }
        });
        Button selectBtn = new Button("Select from existing opportunities", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ContactOpportunitySelectionWindow opportunitiesWindow = new ContactOpportunitySelectionWindow(ContactOpportunityListComp.this);
                OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
                getWindow().addWindow(opportunitiesWindow);
                opportunitiesWindow.setSearchCriteria(criteria);
                controlsBtn.setPopupVisible(false);
            }
        });
        selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
        selectBtn.setStyleName("link");
        controlsBtn.addComponent(selectBtn);

        contentContainer.addComponent(controlsBtn);

        tableItem = new OpportunityTableDisplay(new String[]{"opportunityname",
                    "salesstage", "amount", "expectedcloseddate",
                    "assignUserFullName", "id"}, new String[]{"Name",
                    "Sales Stage", "Amount", "Close", "User", "Action"});

        tableItem.addTableListener(new ApplicationEventListener<TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }

            @Override
            public void handle(TableClickEvent event) {
                SimpleOpportunity opportunity = (SimpleOpportunity) event.getData();
                if ("opportunityname".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new OpportunityEvent.GotoRead(this,
                            opportunity.getId()));
                }
            }
        });

        tableItem.addGeneratedColumn("id", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                final SimpleOpportunity opportunity = (SimpleOpportunity) tableItem.getBeanByIndex(itemId);
                HorizontalLayout controlLayout = new HorizontalLayout();
                Button editBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new OpportunityEvent.GotoRead(
                                ContactOpportunityListComp.this, opportunity
                                .getId()));
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
                                    ContactService contactService = AppContext
                                            .getSpringBean(ContactService.class);
                                    ContactOpportunity associateOpportunity = new ContactOpportunity();
                                    associateOpportunity.setContactid(contact.getId());
                                    associateOpportunity.setOpportunityid(opportunity.getId());
                                    contactService.removeContactOpportunityRelationship(associateOpportunity);
                                    ContactOpportunityListComp.this.refresh();
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
        loadOpportunities();
    }
}
