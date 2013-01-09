package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ContactSelectionWindow extends Window {

    private static final long serialVersionUID = 1L;
    private ContactSearchCriteria searchCriteria;
    private PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact> tableItem;
    private FieldSelection fieldSelection;

    public ContactSelectionWindow(FieldSelection fieldSelection) {
        super("Contact Name Lookup");
        this.setWidth("1035px");

        this.fieldSelection = fieldSelection;
    }

    public void show() {
        searchCriteria = new ContactSearchCriteria();
        searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
                AppContext.getAccountId()));

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        createAccountList();

        layout.addComponent(createSearchPanel());
        layout.addComponent(tableItem);
        this.setContent(layout);

        tableItem.setSearchCriteria(searchCriteria);
        center();
    }
    private TextField textValueField;
    private UserComboBox userBox;
    private GridLayout layoutSearchPane;

    private void addTextFieldSearch() {
        textValueField = new TextField();
        layoutSearchPane.addComponent(textValueField, 0, 0);
    }

    private void addUserListSelectField() {
        userBox = new UserComboBox();
        userBox.setImmediate(true);
        layoutSearchPane.addComponent(userBox, 0, 0);
    }

    private void removeComponents() {
        layoutSearchPane.removeComponent(0, 0);
        userBox = null;
        textValueField = null;
    }

    @SuppressWarnings("serial")
    private ComponentContainer createSearchPanel() {
        layoutSearchPane = new GridLayout(3, 3);
        layoutSearchPane.setSpacing(true);

        final ValueComboBox group = new ValueComboBox(false, new String[]{
                    "Name", "Email", "Phone", "Assigned to"});
        group.select("Name");
        group.setImmediate(true);
        group.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                removeComponents();
                String searchType = (String) group.getValue();
                if (searchType.equals("Name")) {
                    addTextFieldSearch();
                } else if (searchType.equals("Email")) {
                    addTextFieldSearch();
                } else if (searchType.equals("Phone")) {
                    addTextFieldSearch();
                } else if (searchType.equals("Assigned to")) {
                    addUserListSelectField();
                }
            }
        });

        layoutSearchPane.addComponent(group, 1, 0);

        addTextFieldSearch();

        Button searchBtn = new Button("Search");
        searchBtn.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                searchCriteria = new ContactSearchCriteria();
                searchCriteria.setSaccountid(new NumberSearchField(
                        SearchField.AND, AppContext.getAccountId()));

                String searchType = (String) group.getValue();
                if (StringUtil.isNotNullOrEmpty(searchType)) {

                    if (textValueField != null) {
                        String strSearch = (String) textValueField.getValue();
                        if (StringUtil.isNotNullOrEmpty(strSearch)) {

                            if (searchType.equals("Name")) {
                                searchCriteria
                                        .setContactName(new StringSearchField(
                                        SearchField.AND, strSearch));
                            } else if (searchType.equals("Email")) {
                                searchCriteria.setAnyEmail(new StringSearchField(
                                        SearchField.AND, strSearch));
                            } else if (searchType.equals("Phone")) {
                                searchCriteria.setAnyPhone(new StringSearchField(
                                        SearchField.AND, strSearch));
                            }
                        }
                    }

                    if (userBox != null) {
                        String user = (String) userBox.getValue();
                        if (StringUtil.isNotNullOrEmpty(user)) {
                            searchCriteria.setAssignUsers(new SetSearchField<String>(SearchField.AND, new String[]{user}));
                        }
                    }
                }

                tableItem.setSearchCriteria(searchCriteria);
            }
        });
        layoutSearchPane.addComponent(searchBtn, 2, 0);
        return layoutSearchPane;
    }

    private void createAccountList() {
        tableItem = new PagedBeanTable2<ContactService, ContactSearchCriteria, SimpleContact>(
                AppContext.getSpringBean(ContactService.class),
                SimpleContact.class, new String[]{"contactName",
                    "officephone", "email", "assignUserFullName"},
                new String[]{"Name", "Phone", "Email", "Assign User"});
        tableItem.setWidth("100%");

        tableItem.setColumnExpandRatio("contactName", 1.0f);
        tableItem
                .setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
        tableItem.setColumnWidth("assignUserFullName",
                UIConstants.TABLE_X_LABEL_WIDTH);

        tableItem.addGeneratedColumn("contactName", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            public com.vaadin.ui.Component generateCell(final Table source,
                    final Object itemId, Object columnId) {
                final SimpleContact contact = tableItem
                        .getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(contact.getContactName(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                fieldSelection.fireValueChange(contact);
                                ContactSelectionWindow.this.getParent()
                                        .removeWindow(
                                        ContactSelectionWindow.this);
                            }
                        });
                b.addStyleName("medium-text");
                return b;
            }
        });

        tableItem.addGeneratedColumn("email", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    Object itemId, Object columnId) {
                SimpleContact contact = tableItem
                        .getBeanByIndex(itemId);
                return new EmailLink(contact.getEmail());

            }
        });
    }
}
