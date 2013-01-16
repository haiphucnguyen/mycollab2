package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.contact.ContactTableDisplay;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import java.util.Set;
import org.vaadin.hene.splitbutton.SplitButton;

public class AccountContactListComp extends RelatedListComp<ContactSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private Account account;

    public AccountContactListComp() {
        super("Contacts");
        initUI();
    }

    public void displayContacts(Account account) {
        this.account = account;
        loadContacts();
    }
    
    private void loadContacts() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));
        criteria.setAccountId(new NumberSearchField(SearchField.AND, account
                .getId()));
        this.setSearchCriteria(criteria);
    }
    

    @SuppressWarnings("serial")
    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) content;
        contentContainer.setSpacing(true);

        final SplitButton controlsBtn = new SplitButton();
        controlsBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CONTACT));
        controlsBtn.addStyleName(SplitButton.STYLE_CHAMELEON);
        controlsBtn.setCaption("New Contact");
        controlsBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
        controlsBtn.addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(SplitButton.SplitButtonClickEvent event) {
                fireNewRelatedItem("");
            }
        });
        Button selectBtn = new Button("Select from existing contacts", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                AccountContactSelectionWindow contactsWindow = new AccountContactSelectionWindow(AccountContactListComp.this);
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

        contentContainer.addComponent(controlsBtn);

        tableItem = new ContactTableDisplay(
                new String[]{"contactName", "title", "email", "officephone", "id"},
                new String[]{"Name", "Title", "Email", "Office Phone", "Action"});

        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleContact contact = (SimpleContact) event.getData();
                if ("contactName".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new ContactEvent.GotoRead(
                            AccountContactListComp.this, contact
                            .getId()));
                }
            }
        });


        tableItem.addGeneratedColumn("id", new ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                HorizontalLayout controlLayout = new HorizontalLayout();
                Button editBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
                editBtn.setStyleName("link");
                editBtn.setIcon(new ThemeResource("icons/16/edit.png"));
                controlLayout.addComponent(editBtn);

                Button deleteBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        throw new UnsupportedOperationException("Not supported yet.");
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
        
        loadContacts();
    }
    
    
}
