package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.criteria.ClientSearchCriteria;
import com.mycollab.common.i18n.ClientI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.db.query.Param;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.event.ClientEvent;
import com.mycollab.module.project.ui.components.ComponentUtils;
import com.mycollab.module.user.ui.components.ActiveUserListSelect;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.HeaderWithIcon;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
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
public class ClientSearchPanel extends DefaultGenericSearchPanel<ClientSearchCriteria> {

    private static Param[] paramFields = new Param[]{
            ClientSearchCriteria.p_name, ClientSearchCriteria.p_anyPhone, ClientSearchCriteria.p_website,
            ClientSearchCriteria.p_numemployees, ClientSearchCriteria.p_assignee,
            ClientSearchCriteria.p_anyCity, ClientSearchCriteria.p_createdtime,
            ClientSearchCriteria.p_lastupdatedtime
    };

    @Override
    protected HeaderWithIcon buildSearchTitle() {
        return ComponentUtils.headerH2(ProjectTypeConstants.CLIENT, UserUIContext.getMessage(ClientI18nEnum.LIST));
    }

    @Override
    protected Component buildExtraControls() {
        if (UserUIContext.canWrite(RolePermissionCollections.CLIENT)) {
            return new MButton(UserUIContext.getMessage(ClientI18nEnum.NEW),
                    clickEvent -> EventBusFactory.getInstance().post(new ClientEvent.GotoAdd(this, null)))
                    .withIcon(VaadinIcons.PLUS).withStyleName(WebThemes.BUTTON_ACTION);
        } else return null;
    }

    @Override
    protected BasicSearchLayout<ClientSearchCriteria> createBasicSearchLayout() {
        return new AccountBasicSearchLayout();
    }

    @Override
    protected SearchLayout<ClientSearchCriteria> createAdvancedSearchLayout() {
        return new AccountAdvancedSearchLayout();
    }

    private class AccountAdvancedSearchLayout extends DynamicQueryParamLayout<ClientSearchCriteria> {

        private AccountAdvancedSearchLayout() {
            super(ClientSearchPanel.this, ProjectTypeConstants.CLIENT);
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Class<ClientSearchCriteria> getType() {
            return ClientSearchCriteria.class;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("account-assignuser".equals(fieldId)) {
                return new ActiveUserListSelect();
            }
            return null;
        }
    }

    private class AccountBasicSearchLayout extends BasicSearchLayout<ClientSearchCriteria> {
        private TextField nameField;
        private CheckBox myItemCheckbox;

        private AccountBasicSearchLayout() {
            super(ClientSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            nameField = new MTextField().withPlaceholder(UserUIContext.getMessage(GenericI18Enum.ACTION_QUERY_BY_TEXT))
                    .withWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);

            myItemCheckbox = new CheckBox(UserUIContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
            myItemCheckbox.addStyleName(ValoTheme.CHECKBOX_SMALL);

            MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withIcon(FontAwesome.SEARCH).withStyleName(WebThemes.BUTTON_ACTION)
                    .withClickShortcut(ShortcutAction.KeyCode.ENTER);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MButton advancedSearchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    clickEvent -> moveToAdvancedSearchLayout()).withStyleName(WebThemes.BUTTON_LINK);

            return new MHorizontalLayout(nameField, myItemCheckbox, searchBtn, cancelBtn, advancedSearchBtn)
                    .withMargin(true).alignAll(Alignment.MIDDLE_LEFT);
        }

        @Override
        protected ClientSearchCriteria fillUpSearchCriteria() {
            ClientSearchCriteria searchCriteria = new ClientSearchCriteria();
            searchCriteria.setSaccountid(NumberSearchField.equal(AppUI.getAccountId()));
            searchCriteria.setName(StringSearchField.and(nameField.getValue().trim()));
            if (myItemCheckbox.getValue()) {
                searchCriteria.setAssignUser(StringSearchField.and(UserUIContext.getUsername()));
            } else {
                searchCriteria.setAssignUsers(null);
            }

            return searchCriteria;
        }
    }
}
