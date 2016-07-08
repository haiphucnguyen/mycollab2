package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.joda.time.LocalDate;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import static com.mycollab.vaadin.web.ui.UIConstants.BUTTON_ACTION;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class CalendarSearchPanel extends DefaultGenericSearchPanel<ProjectGenericTaskSearchCriteria> {
    private boolean isCreateAssignment;

    public CalendarSearchPanel(boolean isCreateAssignment) {
        this.isCreateAssignment = isCreateAssignment;
    }

    @Override
    protected SearchLayout<ProjectGenericTaskSearchCriteria> createBasicSearchLayout() {
        return new TaskBasicSearchLayout();
    }

    @Override
    protected SearchLayout<ProjectGenericTaskSearchCriteria> createAdvancedSearchLayout() {
        return null;
    }

    @Override
    protected ComponentContainer buildSearchTitle() {
        return new MHorizontalLayout(ELabel.h2(FontAwesome.HASHTAG.getHtml() + " " + AppContext.getMessage
                (ProjectCommonI18nEnum.OPT_ASSIGNMENT_LIST)).withWidthUndefined());
    }

    @Override
    protected Component buildExtraControls() {
        if (isCreateAssignment) {
            MButton newAssignmentBtn = new MButton(AppContext.getMessage(ProjectCommonI18nEnum.ACTION_NEW_ASSIGNMENT),
                    clickEvent -> UI.getCurrent().addWindow(new AssignmentAddWindow(new LocalDate().toDate(),
                            CurrentProjectVariables.getProjectId()))).withIcon(FontAwesome.PLUS).withStyleName(BUTTON_ACTION);
            return newAssignmentBtn;
        }
        return null;
    }

    private class TaskBasicSearchLayout extends BasicSearchLayout<ProjectGenericTaskSearchCriteria> {
        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

        public TaskBasicSearchLayout() {
            super(CalendarSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            MHorizontalLayout basicSearchBody = new MHorizontalLayout().withMargin(true);

            Label nameLbl = new Label("Name:");
            basicSearchBody.with(nameLbl).withAlign(nameLbl, Alignment.MIDDLE_LEFT);

            nameField = new TextField();
            nameField.setInputPrompt("Query by name");
            nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            basicSearchBody.with(nameField).withAlign(nameField, Alignment.MIDDLE_CENTER);

            myItemCheckbox = new CheckBox(AppContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
            basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox, Alignment.MIDDLE_CENTER);

            Button searchBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction());
            searchBtn.setIcon(FontAwesome.SEARCH);
            searchBtn.setStyleName(BUTTON_ACTION);
            basicSearchBody.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""));
            cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }

        @Override
        protected ProjectGenericTaskSearchCriteria fillUpSearchCriteria() {
            ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            searchCriteria.setName(StringSearchField.and(nameField.getValue().trim()));
            if (myItemCheckbox.getValue()) {
                searchCriteria.setAssignUser(StringSearchField.and(AppContext.getUsername()));
            } else {
                searchCriteria.setAssignUser(null);
            }
            return searchCriteria;
        }

        @Override
        public ComponentContainer constructHeader() {
            return CalendarSearchPanel.this.constructHeader();
        }

    }
}