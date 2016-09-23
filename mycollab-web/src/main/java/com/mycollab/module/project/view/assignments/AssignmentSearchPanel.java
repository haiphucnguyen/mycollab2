package com.mycollab.module.project.view.assignments;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.QueryI18nEnum;
import com.mycollab.db.arguments.SearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.query.ConstantValueInjector;
import com.mycollab.db.query.Param;
import com.mycollab.db.query.SearchFieldInfo;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectAssignment;
import com.mycollab.module.project.domain.criteria.ProjectAssignmentSearchCriteria;
import com.mycollab.module.project.events.AssignmentEvent;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.milestone.MilestoneListSelect;
import com.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.mycollab.module.project.view.task.components.TaskStatusListSelect;
import com.mycollab.shell.events.ShellEvent;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.DynamicQueryParamLayout;
import com.mycollab.vaadin.web.ui.SavedFilterComboBox;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class AssignmentSearchPanel extends DefaultGenericSearchPanel<ProjectAssignmentSearchCriteria> {
    private static final long serialVersionUID = 1L;
    private ProjectAssignmentSearchCriteria searchCriteria;

    private AssignmentSavedFilter savedFilterComboBox;

    private static Param[] paramFields = new Param[]{
            ProjectAssignmentSearchCriteria.p_name, ProjectAssignmentSearchCriteria.p_priority,
            ProjectAssignmentSearchCriteria.p_milestones, ProjectAssignmentSearchCriteria.p_startDate,
            ProjectAssignmentSearchCriteria.p_endDate, ProjectAssignmentSearchCriteria.p_dueDate,
            ProjectAssignmentSearchCriteria.p_assignee, ProjectAssignmentSearchCriteria.p_createdUser};

    public AssignmentSearchPanel(boolean canSwitchToAdvanceLayout) {
        super(canSwitchToAdvanceLayout);
    }

    public AssignmentSearchPanel() {
        super();
    }

    @Override
    protected ComponentContainer buildSearchTitle() {
        if (canSwitchToAdvanceLayout) {
            savedFilterComboBox = new AssignmentSavedFilter();
            savedFilterComboBox.addQuerySelectListener(new SavedFilterComboBox.QuerySelectListener() {
                @Override
                public void querySelect(SavedFilterComboBox.QuerySelectEvent querySelectEvent) {
                    List<SearchFieldInfo> fieldInfos = querySelectEvent.getSearchFieldInfos();
                    ProjectAssignmentSearchCriteria criteria = SearchFieldInfo.buildSearchCriteria(ProjectAssignmentSearchCriteria.class,
                            fieldInfos);
                    criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    EventBusFactory.getInstance().post(new AssignmentEvent.SearchRequest(AssignmentSearchPanel.this, criteria));
                    EventBusFactory.getInstance().post(new ShellEvent.AddQueryParam(this, fieldInfos));
                }
            });
            ELabel taskIcon = ELabel.h2(ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK).getHtml()).withWidthUndefined();
            return new MHorizontalLayout(taskIcon, savedFilterComboBox).expand(savedFilterComboBox).alignAll(Alignment.MIDDLE_LEFT);
        } else {
            return null;
        }
    }

    @Override
    public void setTotalCountNumber(int countNumber) {
        savedFilterComboBox.setTotalCountNumber(countNumber);
    }

    @Override
    protected SearchLayout<ProjectAssignmentSearchCriteria> createBasicSearchLayout() {
        return new AssignmentBasicSearchLayout();
    }

    @Override
    protected SearchLayout<ProjectAssignmentSearchCriteria> createAdvancedSearchLayout() {
        return new AssignmentAdvancedSearchLayout();
    }

    public void setTextField(String name) {
        if (getCompositionRoot() instanceof AssignmentBasicSearchLayout) {
            ((AssignmentBasicSearchLayout) getCompositionRoot()).setNameField(name);
        }
    }

    public void displaySearchFieldInfos(List<SearchFieldInfo> searchFieldInfos) {
        if (canSwitchToAdvanceLayout) {
            AssignmentAdvancedSearchLayout advancedSearchLayout = (AssignmentAdvancedSearchLayout) moveToAdvancedSearchLayout();
            advancedSearchLayout.displaySearchFieldInfos(searchFieldInfos);
        }
    }

    public void selectQueryInfo(String queryId) {
        savedFilterComboBox.selectQueryInfo(queryId);
    }

    private class AssignmentBasicSearchLayout extends BasicSearchLayout<ProjectAssignmentSearchCriteria> {
        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

        private AssignmentBasicSearchLayout() {
            super(AssignmentSearchPanel.this);
        }

        public void setNameField(String value) {
            nameField.setValue(value);
        }

        @Override
        public ComponentContainer constructBody() {
            MHorizontalLayout basicSearchBody = new MHorizontalLayout().withMargin(true);

            Label nameLbl = new Label(UserUIContext.getMessage(GenericI18Enum.FORM_NAME) + ":");
            basicSearchBody.with(nameLbl).withAlign(nameLbl, Alignment.MIDDLE_LEFT);

            nameField = new MTextField().withInputPrompt(UserUIContext.getMessage(GenericI18Enum.ACTION_QUERY_BY_TEXT))
                    .withWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);
            basicSearchBody.with(nameField).withAlign(nameField, Alignment.MIDDLE_CENTER);

            myItemCheckbox = new CheckBox(UserUIContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
            basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox, Alignment.MIDDLE_CENTER);

            MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withIcon(FontAwesome.SEARCH).withStyleName(WebUIConstants.BUTTON_ACTION)
                    .withClickShortcut(ShortcutAction.KeyCode.ENTER);
            basicSearchBody.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(WebUIConstants.BUTTON_OPTION);
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);

            if (canSwitchToAdvanceLayout) {
                MButton advancedSearchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                        clickEvent -> moveToAdvancedSearchLayout()).withStyleName(WebUIConstants.BUTTON_LINK);
                basicSearchBody.with(advancedSearchBtn).withAlign(advancedSearchBtn, Alignment.MIDDLE_CENTER);
            }
            return basicSearchBody;
        }

        @Override
        protected ProjectAssignmentSearchCriteria fillUpSearchCriteria() {
            List<SearchFieldInfo> searchFieldInfos = new ArrayList<>();
            searchFieldInfos.add(new SearchFieldInfo(SearchField.AND, ProjectAssignmentSearchCriteria.p_name,
                    QueryI18nEnum.StringI18nEnum.CONTAINS.name(),
                    ConstantValueInjector.valueOf(nameField.getValue().trim())));
//            if (myItemCheckbox.getValue()) {
//                searchFieldInfos.add(new SearchFieldInfo(SearchField.AND, TaskSearchCriteria.p_assignee, QueryI18nEnum.CollectionI18nEnum.IN.name(),
//                        ConstantValueInjector.valueOf(Arrays.asList(UserUIContext.getUsername()))));
//            }
            EventBusFactory.getInstance().post(new ShellEvent.AddQueryParam(this, searchFieldInfos));
            searchCriteria = SearchFieldInfo.buildSearchCriteria(ProjectAssignmentSearchCriteria.class, searchFieldInfos);
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            return searchCriteria;
        }

        @Override
        public ComponentContainer constructHeader() {
            return AssignmentSearchPanel.this.constructHeader();
        }
    }

    private class AssignmentAdvancedSearchLayout extends DynamicQueryParamLayout<ProjectAssignmentSearchCriteria> {
        private static final long serialVersionUID = 1L;

        private AssignmentAdvancedSearchLayout() {
            super(AssignmentSearchPanel.this, ProjectTypeConstants.ASSIGNMENT);
        }

        @Override
        public ComponentContainer constructHeader() {
            return AssignmentSearchPanel.this.constructHeader();
        }

        @Override
        protected Class<ProjectAssignmentSearchCriteria> getType() {
            return ProjectAssignmentSearchCriteria.class;
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("assignuser".equals(fieldId) || "createduser".equals(fieldId)) {
                return new ProjectMemberListSelect(false);
            } else if ("milestone".equals(fieldId)) {
                return new MilestoneListSelect();
            }
            return null;
        }

        @Override
        protected ProjectAssignmentSearchCriteria fillUpSearchCriteria() {
            searchCriteria = super.fillUpSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            return searchCriteria;
        }
    }
}
