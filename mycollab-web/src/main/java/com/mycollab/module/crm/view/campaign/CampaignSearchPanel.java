/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.view.campaign;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.db.query.Param;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.mycollab.module.crm.event.CampaignEvent;
import com.mycollab.module.crm.i18n.CampaignI18nEnum;
import com.mycollab.module.crm.ui.components.ComponentUtils;
import com.mycollab.module.user.ui.components.ActiveUserListSelect;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.HeaderWithIcon;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 1.0
 */
public class CampaignSearchPanel extends DefaultGenericSearchPanel<CampaignSearchCriteria> {
    private static final long serialVersionUID = 1L;

    private boolean canCreateCampaign;

    private static Param[] paramFields = new Param[]{
            CampaignSearchCriteria.p_campaignName,
            CampaignSearchCriteria.p_startDate,
            CampaignSearchCriteria.p_endDate,
            CampaignSearchCriteria.p_createdtime,
            CampaignSearchCriteria.p_lastUpdatedTime,
            CampaignSearchCriteria.p_types, CampaignSearchCriteria.p_statuses,
            CampaignSearchCriteria.p_assignee};

    public CampaignSearchPanel(boolean canCreateCampaign) {
        this.canCreateCampaign = canCreateCampaign;
    }

    @Override
    protected HeaderWithIcon buildSearchTitle() {
        return ComponentUtils.header(CrmTypeConstants.CAMPAIGN, UserUIContext.getMessage(CampaignI18nEnum.LIST));
    }

    @Override
    protected Component buildExtraControls() {
        return (canCreateCampaign) ? new MButton(UserUIContext.getMessage(CampaignI18nEnum.NEW),
                clickEvent -> EventBusFactory.getInstance().post(new CampaignEvent.GotoAdd(this, null)))
                .withIcon(VaadinIcons.PLUS).withStyleName(WebThemes.BUTTON_ACTION)
                .withVisible(UserUIContext.canWrite(RolePermissionCollections.CRM_CAMPAIGN)) : null;
    }

    @Override
    protected BasicSearchLayout<CampaignSearchCriteria> createBasicSearchLayout() {
        return new CampaignBasicSearchLayout();
    }

    @Override
    protected SearchLayout<CampaignSearchCriteria> createAdvancedSearchLayout() {
        return new CampaignAdvancedSearchLayout();
    }

    private class CampaignBasicSearchLayout extends BasicSearchLayout<CampaignSearchCriteria> {
        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

        CampaignBasicSearchLayout() {
            super(CampaignSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            MHorizontalLayout basicSearchBody = new MHorizontalLayout().withMargin(true);

            nameField = new MTextField().withPlaceholder(UserUIContext.getMessage(GenericI18Enum.ACTION_QUERY_BY_TEXT))
                    .withWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);
            basicSearchBody.with(nameField).withAlign(nameField, Alignment.MIDDLE_CENTER);

            this.myItemCheckbox = new CheckBox(UserUIContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
            basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox, Alignment.MIDDLE_CENTER);

            MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withIcon(VaadinIcons.SEARCH).withStyleName(WebThemes.BUTTON_ACTION)
                    .withClickShortcut(ShortcutAction.KeyCode.ENTER);

            basicSearchBody.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(WebThemes.BUTTON_OPTION);
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);

            MButton advancedSearchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    clickEvent -> moveToAdvancedSearchLayout()).withStyleName(WebThemes.BUTTON_LINK);

            basicSearchBody.with(advancedSearchBtn).withAlign(advancedSearchBtn, Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }

        @Override
        protected CampaignSearchCriteria fillUpSearchCriteria() {
            CampaignSearchCriteria searchCriteria = new CampaignSearchCriteria();
            searchCriteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));

            if (StringUtils.isNotBlank(nameField.getValue())) {
                searchCriteria.setCampaignName(StringSearchField.and(nameField.getValue()));
            }

            if (myItemCheckbox.getValue()) {
                searchCriteria.setAssignUsers(new SetSearchField<>(UserUIContext.getUsername()));
            } else {
                searchCriteria.setAssignUsers(null);
            }
            return searchCriteria;
        }
    }

    private class CampaignAdvancedSearchLayout extends DynamicQueryParamLayout<CampaignSearchCriteria> {
        private static final long serialVersionUID = 1L;

        CampaignAdvancedSearchLayout() {
            super(CampaignSearchPanel.this, CrmTypeConstants.CAMPAIGN);
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Class<CampaignSearchCriteria> getType() {
            return CampaignSearchCriteria.class;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("assignuser".equals(fieldId)) {
                return new ActiveUserListSelect();
            }
            return null;
        }
    }
}
