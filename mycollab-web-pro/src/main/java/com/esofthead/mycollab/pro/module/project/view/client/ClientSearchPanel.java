package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.db.query.Param;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.ComponentUtils;
import com.esofthead.mycollab.module.project.events.ClientEvent;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserListSelect;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.HeaderWithFontAwesome;
import com.esofthead.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.web.ui.DynamicQueryParamLayout;
import com.esofthead.mycollab.vaadin.web.ui.ShortcutExtension;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class ClientSearchPanel extends DefaultGenericSearchPanel<AccountSearchCriteria> {

    private static Param[] paramFields = new Param[]{
            AccountSearchCriteria.p_accountName, AccountSearchCriteria.p_anyPhone, AccountSearchCriteria.p_website,
            AccountSearchCriteria.p_numemployees, AccountSearchCriteria.p_assignee, AccountSearchCriteria.p_industries,
            AccountSearchCriteria.p_types, AccountSearchCriteria.p_assignee, AccountSearchCriteria.p_billingCountry,
            AccountSearchCriteria.p_shippingCountry, AccountSearchCriteria.p_anyCity, AccountSearchCriteria.p_createdtime,
            AccountSearchCriteria.p_lastupdatedtime};

    @Override
    protected HeaderWithFontAwesome buildSearchTitle() {
        return ComponentUtils.header(CrmTypeConstants.ACCOUNT, "Clients");
    }

    @Override
    protected Component buildExtraControls() {
        Button createBtn = new Button("New Client", new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                EventBusFactory.getInstance().post(new ClientEvent.GotoAdd(this, null));
            }
        });
        createBtn.setStyleName(UIConstants.BUTTON_ACTION);
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT));
        return createBtn;
    }

    @Override
    protected BasicSearchLayout<AccountSearchCriteria> createBasicSearchLayout() {
        return new AccountBasicSearchLayout();
    }

    @Override
    protected SearchLayout<AccountSearchCriteria> createAdvancedSearchLayout() {
        return new AccountAdvancedSearchLayout();
    }

    private class AccountAdvancedSearchLayout extends DynamicQueryParamLayout<AccountSearchCriteria> {

        public AccountAdvancedSearchLayout() {
            super(ClientSearchPanel.this, CrmTypeConstants.ACCOUNT);
        }

        @Override
        public ComponentContainer constructHeader() {
            return ClientSearchPanel.this.constructHeader();
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Class<AccountSearchCriteria> getType() {
            return AccountSearchCriteria.class;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("account-assignuser".equals(fieldId)) {
                return new ActiveUserListSelect();
            }
            return null;
        }
    }

    private class AccountBasicSearchLayout extends BasicSearchLayout<AccountSearchCriteria> {
        private TextField nameField;
        private CheckBox myItemCheckbox;

        public AccountBasicSearchLayout() {
            super(ClientSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            MHorizontalLayout basicSearchBody = new MHorizontalLayout().withMargin(true);

            nameField = ShortcutExtension.installShortcutAction(new TextField(),
                    new ShortcutListener("AccountSearchRequest", ShortcutAction.KeyCode.ENTER, null) {
                        @Override
                        public void handleAction(Object o, Object o1) {
                            callSearchAction();
                        }
                    });
            nameField.setInputPrompt("Query by client name");
            nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);

            basicSearchBody.with(nameField).withAlign(nameField, Alignment.MIDDLE_CENTER);

            myItemCheckbox = new CheckBox(AppContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
            myItemCheckbox.addStyleName(ValoTheme.CHECKBOX_SMALL);

            basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox, Alignment.MIDDLE_CENTER);

            Button searchBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
            searchBtn.setStyleName(UIConstants.BUTTON_ACTION);
            searchBtn.setIcon(FontAwesome.SEARCH);
            searchBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    AccountBasicSearchLayout.this.callSearchAction();
                }
            });

            basicSearchBody.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR));
            cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
            cancelBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    nameField.setValue("");
                }
            });
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);

            Button advancedSearchBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH), new Button.ClickListener() {
                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    ClientSearchPanel.this.moveToAdvancedSearchLayout();
                }
            });
            advancedSearchBtn.setStyleName(UIConstants.BUTTON_LINK);
            basicSearchBody.with(advancedSearchBtn).withAlign(advancedSearchBtn, Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }

        @Override
        public ComponentContainer constructHeader() {
            return ClientSearchPanel.this.constructHeader();
        }

        @Override
        protected AccountSearchCriteria fillUpSearchCriteria() {
            AccountSearchCriteria searchCriteria = new AccountSearchCriteria();
            searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND, AppContext.getAccountId()));
            searchCriteria.setAccountname(StringSearchField.and(nameField.getValue().trim()));
            if (myItemCheckbox.getValue()) {
                searchCriteria.setAssignUser(StringSearchField.and(AppContext.getUsername()));
            } else {
                searchCriteria.setAssignUsers(null);
            }

            return searchCriteria;
        }
    }
}
