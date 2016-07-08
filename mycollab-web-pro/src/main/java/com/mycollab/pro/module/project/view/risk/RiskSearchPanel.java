package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.db.query.Param;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.events.RiskEvent;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.ui.components.ComponentUtils;
import com.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.HeaderWithFontAwesome;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.DynamicQueryParamLayout;
import com.mycollab.vaadin.web.ui.ShortcutExtension;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class RiskSearchPanel extends DefaultGenericSearchPanel<RiskSearchCriteria> {
    private static final long serialVersionUID = 1L;

    private static Param[] paramFields = new Param[]{
            RiskSearchCriteria.p_assignee, RiskSearchCriteria.p_raisedUser,
            RiskSearchCriteria.p_status, RiskSearchCriteria.p_probalitity,
            RiskSearchCriteria.p_consequence, RiskSearchCriteria.p_duedate,
            RiskSearchCriteria.p_raiseddate, RiskSearchCriteria.p_createdtime,
            RiskSearchCriteria.p_lastupdatedtime};

    @Override
    protected HeaderWithFontAwesome buildSearchTitle() {
        return ComponentUtils.headerH2(ProjectTypeConstants.RISK, AppContext.getMessage(RiskI18nEnum.LIST));
    }

    @Override
    protected Component buildExtraControls() {
        MButton createBtn = new MButton(AppContext.getMessage(RiskI18nEnum.NEW),
                clickEvent -> EventBusFactory.getInstance().post(new RiskEvent.GotoAdd(this, null)))
                .withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.PLUS);
        createBtn.setVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));
        return createBtn;
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
            nameField = ShortcutExtension.installShortcutAction(new TextField(),
                    new ShortcutListener("RiskSearchRequest", ShortcutAction.KeyCode.ENTER, null) {
                        @Override
                        public void handleAction(Object o, Object o1) {
                            callSearchAction();
                        }
                    });
            nameField.setInputPrompt("Query by risk name");
            nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);

            myItemCheckbox = new CheckBox(AppContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));

            MButton searchBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.SEARCH);

            MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(UIConstants.BUTTON_OPTION);

            MButton advancedSearchBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    clickEvent -> moveToAdvancedSearchLayout()).withStyleName(UIConstants.BUTTON_LINK);

            return new MHorizontalLayout(nameField, myItemCheckbox, searchBtn, cancelBtn, advancedSearchBtn)
                    .alignAll(Alignment.MIDDLE_LEFT).withMargin(true);
        }

        @Override
        protected RiskSearchCriteria fillUpSearchCriteria() {
            RiskSearchCriteria searchCriteria = new RiskSearchCriteria();
            searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
            searchCriteria.setRiskname(StringSearchField.and(this.nameField.getValue().trim()));

            if (this.myItemCheckbox.getValue()) {
                searchCriteria.setAssignToUser(StringSearchField.and(AppContext.getUsername()));
            } else {
                searchCriteria.setAssignToUser(null);
            }
            return searchCriteria;
        }

        @Override
        public ComponentContainer constructHeader() {
            return RiskSearchPanel.this.constructHeader();
        }
    }

    private class RiskAdvancedSearchLayout extends DynamicQueryParamLayout<RiskSearchCriteria> {
        private static final long serialVersionUID = 1L;

        RiskAdvancedSearchLayout() {
            super(RiskSearchPanel.this, ProjectTypeConstants.RISK);
        }

        @Override
        public ComponentContainer constructHeader() {
            return RiskSearchPanel.this.constructHeader();
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
                return new ProjectMemberListSelect();
            } else if ("raiseduser".equals(fieldId)) {
                return new ProjectMemberListSelect();
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