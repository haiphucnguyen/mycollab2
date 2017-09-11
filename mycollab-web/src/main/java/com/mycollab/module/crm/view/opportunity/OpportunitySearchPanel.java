package com.mycollab.module.crm.view.opportunity;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.db.query.Param;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.mycollab.module.crm.event.OpportunityEvent;
import com.mycollab.module.crm.i18n.OpportunityI18nEnum;
import com.mycollab.module.crm.ui.components.ComponentUtils;
import com.mycollab.module.crm.view.account.AccountSelectionField;
import com.mycollab.module.crm.view.campaign.CampaignSelectionField;
import com.mycollab.module.user.ui.components.ActiveUserListSelect;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.HeaderWithFontAwesome;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class OpportunitySearchPanel extends DefaultGenericSearchPanel<OpportunitySearchCriteria> {
    private boolean canCreateOpportunity;

    private static Param[] paramFields = new Param[]{
            OpportunitySearchCriteria.Companion.getP_opportunityName(),
            OpportunitySearchCriteria.Companion.getP_account(),
            OpportunitySearchCriteria.Companion.getP_nextStep(),
            OpportunitySearchCriteria.Companion.getP_campaign(),
            OpportunitySearchCriteria.Companion.getP_leadSource(),
            OpportunitySearchCriteria.Companion.getP_saleStage(),
            OpportunitySearchCriteria.Companion.getP_type(),
            OpportunitySearchCriteria.Companion.getP_expectedcloseddate(),
            OpportunitySearchCriteria.Companion.getP_createdtime(),
            OpportunitySearchCriteria.Companion.getP_lastupdatedtime()};

    public OpportunitySearchPanel(boolean canCreateOpportunity) {
        this.canCreateOpportunity = canCreateOpportunity;
    }

    @Override
    protected HeaderWithFontAwesome buildSearchTitle() {
        return ComponentUtils.header(CrmTypeConstants.INSTANCE.getOPPORTUNITY(), UserUIContext.getMessage(OpportunityI18nEnum.LIST));
    }

    @Override
    protected Component buildExtraControls() {
        return (canCreateOpportunity) ? new MButton(UserUIContext.getMessage(OpportunityI18nEnum.NEW),
                clickEvent -> EventBusFactory.getInstance().post(new OpportunityEvent.GotoAdd(OpportunitySearchPanel.this, null)))
                .withIcon(FontAwesome.PLUS).withStyleName(WebThemes.BUTTON_ACTION)
                .withVisible(UserUIContext.canWrite(RolePermissionCollections.INSTANCE.getCRM_OPPORTUNITY())) : null;
    }

    @Override
    protected BasicSearchLayout<OpportunitySearchCriteria> createBasicSearchLayout() {
        return new OpportunityBasicSearchLayout();
    }

    @Override
    protected SearchLayout<OpportunitySearchCriteria> createAdvancedSearchLayout() {
        return new OpportunityAdvancedSearchLayout();
    }

    private class OpportunityBasicSearchLayout extends BasicSearchLayout<OpportunitySearchCriteria> {
        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

        OpportunityBasicSearchLayout() {
            super(OpportunitySearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            MHorizontalLayout layout = new MHorizontalLayout().withMargin(true);

            nameField = new MTextField().withInputPrompt(UserUIContext.getMessage(GenericI18Enum.ACTION_QUERY_BY_TEXT))
                    .withWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);
            layout.with(nameField).withAlign(nameField, Alignment.MIDDLE_CENTER);

            this.myItemCheckbox = new CheckBox(UserUIContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
            layout.with(myItemCheckbox).withAlign(myItemCheckbox, Alignment.MIDDLE_CENTER);

            MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withIcon(FontAwesome.SEARCH).withStyleName(WebThemes.BUTTON_ACTION)
                    .withClickShortcut(ShortcutAction.KeyCode.ENTER);
            layout.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(WebThemes.BUTTON_OPTION);
            layout.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);

            MButton advancedSearchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    clickEvent -> moveToAdvancedSearchLayout())
                    .withStyleName(WebThemes.BUTTON_LINK);

            layout.with(advancedSearchBtn).withAlign(advancedSearchBtn, Alignment.MIDDLE_CENTER);
            return layout;
        }

        @Override
        protected OpportunitySearchCriteria fillUpSearchCriteria() {
            OpportunitySearchCriteria searchCriteria = new OpportunitySearchCriteria();
            searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));

            if (StringUtils.isNotBlank(this.nameField.getValue().trim())) {
                searchCriteria.setOpportunityName(StringSearchField.and(this.nameField.getValue().trim()));
            }

            if (this.myItemCheckbox.getValue()) {
                searchCriteria.setAssignUsers(new SetSearchField<>(UserUIContext.getUsername()));
            } else {
                searchCriteria.setAssignUsers(null);
            }

            return searchCriteria;
        }
    }

    private class OpportunityAdvancedSearchLayout extends DynamicQueryParamLayout<OpportunitySearchCriteria> {

        OpportunityAdvancedSearchLayout() {
            super(OpportunitySearchPanel.this, CrmTypeConstants.INSTANCE.getOPPORTUNITY());
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Class<OpportunitySearchCriteria> getType() {
            return OpportunitySearchCriteria.class;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("assignee".equals(fieldId)) {
                return new ActiveUserListSelect();
            } else if ("account".equals(fieldId)) {
                return new AccountSelectionField();
            } else if ("campaign".equals(fieldId)) {
                return new CampaignSelectionField();
            }
            return null;
        }
    }
}
