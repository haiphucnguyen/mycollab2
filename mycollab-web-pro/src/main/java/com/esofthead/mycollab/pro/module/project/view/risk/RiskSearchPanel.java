package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.db.query.Param;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.ComponentUtils;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
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
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RiskSearchPanel extends DefaultGenericSearchPanel<RiskSearchCriteria> {
    private static final long serialVersionUID = 1L;

    private static Param[] paramFields = new Param[]{
            RiskSearchCriteria.p_assignee, RiskSearchCriteria.p_raisedUser,
            RiskSearchCriteria.p_status, RiskSearchCriteria.p_probalitity,
            RiskSearchCriteria.p_consequence, RiskSearchCriteria.p_duedate,
            RiskSearchCriteria.p_raiseddate, RiskSearchCriteria.p_createdtime,
            RiskSearchCriteria.p_lastupdatedtime};

    @Override
    protected HeaderWithFontAwesome buildSearchTitle() {
        return ComponentUtils.headerH2(ProjectTypeConstants.RISK, AppContext.getMessage(RiskI18nEnum.VIEW_LIST_TITLE));
    }

    @Override
    protected void buildExtraControls() {
        Button createBtn = new Button(AppContext.getMessage(RiskI18nEnum.BUTTON_NEW_RISK), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                EventBusFactory.getInstance().post(new RiskEvent.GotoAdd(this, null));
            }
        });
        createBtn.setStyleName(UIConstants.BUTTON_ACTION);
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));
        this.addHeaderRight(createBtn);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected SearchLayout<RiskSearchCriteria> createBasicSearchLayout() {
        return new RiskBasicSearchLayout();
    }

    @Override
    protected SearchLayout<RiskSearchCriteria> createAdvancedSearchLayout() {
        return new RiskAdvancedSearchLayout();
    }

    @SuppressWarnings("rawtypes")
    private class RiskBasicSearchLayout extends BasicSearchLayout {
        private static final long serialVersionUID = 1L;

        private TextField nameField;
        private CheckBox myItemCheckbox;

        @SuppressWarnings("unchecked")
        public RiskBasicSearchLayout() {
            super(RiskSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            MHorizontalLayout basicSearchBody = new MHorizontalLayout().withMargin(true);

            nameField = ShortcutExtension.installShortcutAction(new TextField(),
                    new ShortcutListener("RiskSearchRequest", ShortcutAction.KeyCode.ENTER, null) {
                        @Override
                        public void handleAction(Object o, Object o1) {
                            callSearchAction();
                        }
                    });
            nameField.setInputPrompt("Query by risk name");
            nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            basicSearchBody.with(nameField).withAlign(nameField, Alignment.MIDDLE_CENTER);

            this.myItemCheckbox = new CheckBox(AppContext.getMessage(GenericI18Enum.SEARCH_MYITEMS_CHECKBOX));
            basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox, Alignment.MIDDLE_CENTER);

            Button searchBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
            searchBtn.setIcon(FontAwesome.SEARCH);
            searchBtn.setStyleName(UIConstants.BUTTON_ACTION);
            searchBtn.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    callSearchAction();
                }
            });
            basicSearchBody.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR));
            cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
            cancelBtn.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    nameField.setValue("");
                }
            });
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);

            Button advancedSearchBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    moveToAdvancedSearchLayout();
                }
            });
            advancedSearchBtn.setStyleName(UIConstants.BUTTON_LINK);
            basicSearchBody.with(advancedSearchBtn).withAlign(advancedSearchBtn, Alignment.MIDDLE_CENTER);

            return basicSearchBody;
        }

        @Override
        protected SearchCriteria fillUpSearchCriteria() {
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

        public RiskAdvancedSearchLayout() {
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
            if ("risk-assignuser".equals(fieldId)) {
                return new ProjectMemberListSelect();
            } else if ("risk-raiseduser".equals(fieldId)) {
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
