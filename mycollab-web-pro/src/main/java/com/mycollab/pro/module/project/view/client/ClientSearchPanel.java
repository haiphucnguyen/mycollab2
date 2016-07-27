package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.db.query.Param;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.ui.components.ComponentUtils;
import com.mycollab.module.project.events.ClientEvent;
import com.mycollab.module.project.i18n.ClientI18nEnum;
import com.mycollab.module.user.ui.components.ActiveUserListSelect;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.HeaderWithFontAwesome;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.DynamicQueryParamLayout;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
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
        return ComponentUtils.header(CrmTypeConstants.ACCOUNT, AppContext.getMessage(ClientI18nEnum.LIST));
    }

    @Override
    protected Component buildExtraControls() {
        MButton createBtn = new MButton(AppContext.getMessage(ClientI18nEnum.NEW),
                clickEvent -> EventBusFactory.getInstance().post(new ClientEvent.GotoAdd(this, null)))
                .withIcon(FontAwesome.PLUS).withStyleName(WebUIConstants.BUTTON_ACTION);
        createBtn.setVisible(AppContext.canWrite(RolePermissionCollections.CRM_ACCOUNT));
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

        private AccountAdvancedSearchLayout() {
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

        private AccountBasicSearchLayout() {
            super(ClientSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            nameField = new MTextField().withInputPrompt(AppContext.getMessage(GenericI18Enum.ACTION_QUERY_BY_TEXT))
                    .withWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);

            myItemCheckbox = new CheckBox(AppContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
            myItemCheckbox.addStyleName(ValoTheme.CHECKBOX_SMALL);

            MButton searchBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withIcon(FontAwesome.SEARCH).withStyleName(WebUIConstants.BUTTON_ACTION)
                    .withClickShortcut(ShortcutAction.KeyCode.ENTER);

            MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(WebUIConstants.BUTTON_OPTION);

            MButton advancedSearchBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    clickEvent -> moveToAdvancedSearchLayout()).withStyleName(WebUIConstants.BUTTON_LINK);

            return new MHorizontalLayout(nameField, myItemCheckbox, searchBtn, cancelBtn, advancedSearchBtn)
                    .withMargin(true).alignAll(Alignment.MIDDLE_LEFT);
        }

        @Override
        public ComponentContainer constructHeader() {
            return ClientSearchPanel.this.constructHeader();
        }

        @Override
        protected AccountSearchCriteria fillUpSearchCriteria() {
            AccountSearchCriteria searchCriteria = new AccountSearchCriteria();
            searchCriteria.setSaccountid(NumberSearchField.equal(AppContext.getAccountId()));
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
