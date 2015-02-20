package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.vaadin.ui.MyCollabSession;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.db.query.Param;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.ProjectViewHeader;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.DynamicQueryParamLayout;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProblemSearchPanel extends
        DefaultGenericSearchPanel<ProblemSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private static Param[] paramFields = new Param[]{
            ProblemSearchCriteria.p_assignee,
            ProblemSearchCriteria.p_raisedUser,
            ProblemSearchCriteria.p_priority, ProblemSearchCriteria.p_status,
            ProblemSearchCriteria.p_duedate, ProblemSearchCriteria.p_raiseddate};

    private final SimpleProject project;
    protected ProblemSearchCriteria searchCriteria;

    public ProblemSearchPanel() {
        this.project = (SimpleProject) MyCollabSession.getVariable("project");
    }

    @Override
    protected SearchLayout<ProblemSearchCriteria> createBasicSearchLayout() {
        return new ProblemBasicSearchLayout();
    }

    @Override
    protected SearchLayout<ProblemSearchCriteria> createAdvancedSearchLayout() {
        return new ProblemAdvancedSearchLayout();
    }

    private HorizontalLayout constructHeader() {

        final Button createBtn = new Button(
                AppContext.getMessage(ProblemI18nEnum.BUTTON_NEW_PROBLEM),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        EventBusFactory.getInstance().post(
                                new ProblemEvent.GotoAdd(this, null));
                    }
                });
        createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.setEnabled(CurrentProjectVariables
                .canWrite(ProjectRolePermissionCollections.PROBLEMS));

        Label headerText = new ProjectViewHeader(ProjectTypeConstants.PROBLEM, "Problems");
        headerText.setStyleName(UIConstants.HEADER_TEXT);

        return new MHorizontalLayout()
                .withStyleName(UIConstants.HEADER_VIEW).withWidth("100%")
                .withSpacing(true)
                .withMargin(new MarginInfo(true, false, true, false))
                .with(headerText, createBtn)
                .withAlign(headerText, Alignment.MIDDLE_LEFT)
                .withAlign(createBtn, Alignment.MIDDLE_RIGHT)
                .expand(headerText);
    }

    private class ProblemBasicSearchLayout extends
            BasicSearchLayout<ProblemSearchCriteria> {

        public ProblemBasicSearchLayout() {
            super(ProblemSearchPanel.this);
        }

        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

        @Override
        public ComponentContainer constructBody() {
            final MHorizontalLayout basicSearchBody = new MHorizontalLayout()
                    .withSpacing(true).withMargin(true);

            this.nameField = this.createSeachSupportTextField(new TextField(),
                    "NameFieldOfBasicSearch");

            this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            basicSearchBody.with(nameField).withAlign(nameField,
                    Alignment.MIDDLE_CENTER);

            this.myItemCheckbox = new CheckBox(
                    AppContext
                            .getMessage(GenericI18Enum.SEARCH_MYITEMS_CHECKBOX));
            this.myItemCheckbox.setWidth("75px");
            basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox,
                    Alignment.MIDDLE_CENTER);

            final Button searchBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
            searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            searchBtn.setIcon(FontAwesome.SEARCH);

            searchBtn.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    ProblemBasicSearchLayout.this.callSearchAction();
                }
            });
            basicSearchBody.with(searchBtn).withAlign(searchBtn,
                    Alignment.MIDDLE_LEFT);

            final Button cancelBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR));
            cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
            cancelBtn.addStyleName("cancel-button");
            cancelBtn.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    ProblemBasicSearchLayout.this.nameField.setValue("");
                }
            });
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn,
                    Alignment.MIDDLE_CENTER);

            final Button advancedSearchBtn = new Button(
                    AppContext
                            .getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            ProblemSearchPanel.this
                                    .moveToAdvancedSearchLayout();
                        }
                    });
            advancedSearchBtn.setStyleName("link");
            basicSearchBody.with(advancedSearchBtn).withAlign(
                    advancedSearchBtn, Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }

        @Override
        protected ProblemSearchCriteria fillupSearchCriteria() {
            searchCriteria = new ProblemSearchCriteria();
            searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
                    ProblemSearchPanel.this.project.getId()));

            searchCriteria.setProblemname(new StringSearchField(this.nameField.getValue().trim()));

            if (this.myItemCheckbox.getValue()) {
                searchCriteria.setAssignToUser(new StringSearchField(
                        SearchField.AND, AppContext.getUsername()));
            } else {
                searchCriteria.setAssignToUser(null);
            }
            return searchCriteria;
        }

        @Override
        public ComponentContainer constructHeader() {
            return ProblemSearchPanel.this.constructHeader();
        }
    }

    private class ProblemAdvancedSearchLayout extends
            DynamicQueryParamLayout<ProblemSearchCriteria> {
        private static final long serialVersionUID = 1L;

        public ProblemAdvancedSearchLayout() {
            super(ProblemSearchPanel.this, ProjectTypeConstants.PROBLEM);
        }

        @Override
        public ComponentContainer constructHeader() {
            return ProblemSearchPanel.this.constructHeader();
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Class<ProblemSearchCriteria> getType() {
            return ProblemSearchCriteria.class;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("problem-assignuser".equals(fieldId)) {
                return new ProjectMemberListSelect();
            } else if ("problem-raiseduser".equals(fieldId)) {
                return new ProjectMemberListSelect();
            }
            return null;
        }

        @Override
        protected ProblemSearchCriteria fillupSearchCriteria() {
            searchCriteria = super.fillupSearchCriteria();
            searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
                    ProblemSearchPanel.this.project.getId()));
            return searchCriteria;
        }
    }
}
