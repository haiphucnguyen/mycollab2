package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.db.query.Param;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.event.RiskEvent;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.ui.components.ComponentUtils;
import com.mycollab.module.project.view.milestone.MilestoneListSelect;
import com.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.HeaderWithFontAwesome;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class RiskSearchPanel extends DefaultGenericSearchPanel<RiskSearchCriteria> {
    private static final long serialVersionUID = 1L;

    private static Param[] paramFields = new Param[]{
            RiskSearchCriteria.p_assignee, RiskSearchCriteria.p_raisedUser,
            RiskSearchCriteria.p_milestones,
            RiskSearchCriteria.p_status, RiskSearchCriteria.p_probalitity,
            RiskSearchCriteria.p_consequence, RiskSearchCriteria.p_duedate,
            RiskSearchCriteria.p_raiseddate, RiskSearchCriteria.p_createdtime,
            RiskSearchCriteria.p_lastupdatedtime};

    @Override
    protected HeaderWithFontAwesome buildSearchTitle() {
        return ComponentUtils.headerH2(ProjectTypeConstants.RISK, UserUIContext.getMessage(RiskI18nEnum.LIST));
    }

    @Override
    protected Component buildExtraControls() {
        return new MButton(UserUIContext.getMessage(RiskI18nEnum.NEW),
                clickEvent -> EventBusFactory.getInstance().post(new RiskEvent.GotoAdd(this, null)))
                .withStyleName(WebThemes.BUTTON_ACTION).withIcon(FontAwesome.PLUS)
                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));
    }

    @Override
    protected SearchLayout<RiskSearchCriteria> createBasicSearchLayout() {
        return new RiskBasicSearchLayout();
    }

    @Override
    protected SearchLayout<RiskSearchCriteria> createAdvancedSearchLayout() {
        return new RiskAdvancedSearchLayout();
    }

    private class RiskBasicSearchLayout extends BasicSearchLayout<RiskSearchCriteria> {
        private static final long serialVersionUID = 1L;

        private TextField nameField;
        private CheckBox myItemCheckbox;

        RiskBasicSearchLayout() {
            super(RiskSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            nameField = new MTextField().withInputPrompt(UserUIContext.getMessage(GenericI18Enum.ACTION_QUERY_BY_TEXT))
                    .withWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);

            myItemCheckbox = new CheckBox(UserUIContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));

            MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withStyleName(WebThemes.BUTTON_ACTION).withIcon(FontAwesome.SEARCH)
                    .withClickShortcut(ShortcutAction.KeyCode.ENTER);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MButton advancedSearchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    clickEvent -> moveToAdvancedSearchLayout()).withStyleName(WebThemes.BUTTON_LINK);

            return new MHorizontalLayout(nameField, myItemCheckbox, searchBtn, cancelBtn, advancedSearchBtn)
                    .alignAll(Alignment.MIDDLE_LEFT).withMargin(true);
        }

        @Override
        protected RiskSearchCriteria fillUpSearchCriteria() {
            RiskSearchCriteria searchCriteria = new RiskSearchCriteria();
            searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
            searchCriteria.setName(StringSearchField.and(this.nameField.getValue().trim()));

            if (this.myItemCheckbox.getValue()) {
                searchCriteria.setAssignUser(StringSearchField.and(UserUIContext.getUsername()));
            } else {
                searchCriteria.setAssignUser(null);
            }
            return searchCriteria;
        }
    }

    private class RiskAdvancedSearchLayout extends DynamicQueryParamLayout<RiskSearchCriteria> {
        private static final long serialVersionUID = 1L;

        RiskAdvancedSearchLayout() {
            super(RiskSearchPanel.this, ProjectTypeConstants.RISK);
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Class<RiskSearchCriteria> getType() {
            return RiskSearchCriteria.class;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("assignuser".equals(fieldId)) {
                return new ProjectMemberListSelect(Arrays.asList(CurrentProjectVariables.getProjectId()));
            } else if ("raiseduser".equals(fieldId)) {
                return new ProjectMemberListSelect(Arrays.asList(CurrentProjectVariables.getProjectId()));
            } else if ("milestones".equals(fieldId)) {
                return new MilestoneListSelect();
            }
            return null;
        }

        @Override
        protected RiskSearchCriteria fillUpSearchCriteria() {
            RiskSearchCriteria searchCriteria = super.fillUpSearchCriteria();
            searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
            return searchCriteria;
        }
    }
}
