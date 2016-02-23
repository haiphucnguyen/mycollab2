package com.esofthead.mycollab.pro.module.project.view.assignments;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.joda.time.LocalDate;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class CalendarSearchPanel extends DefaultGenericSearchPanel<ProjectGenericTaskSearchCriteria> {
    private ProjectGenericTaskSearchCriteria searchCriteria;
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
        Label taskIcon = new Label(FontAwesome.HASHTAG.getHtml() + " Assignments", ContentMode.HTML);
        taskIcon.addStyleName(ValoTheme.LABEL_H2);
        taskIcon.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        taskIcon.setWidthUndefined();
        return new MHorizontalLayout(taskIcon);
    }

    @Override
    protected void buildExtraControls() {
        if (isCreateAssignment) {
            Button newAssignmentBtn = new Button("New assignment", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    UI.getCurrent().addWindow(new AssignmentAddWindow(new LocalDate().toDate(), CurrentProjectVariables.getProjectId()));
                }
            });
            newAssignmentBtn.setIcon(FontAwesome.PLUS);
            newAssignmentBtn.setStyleName(UIConstants.BUTTON_ACTION);
            this.addHeaderRight(newAssignmentBtn);
        }
    }

    private class TaskBasicSearchLayout extends BasicSearchLayout<ProjectGenericTaskSearchCriteria> {
        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

        public TaskBasicSearchLayout() {
            super(CalendarSearchPanel.this);
        }

        public void setNameField(String value) {
            nameField.setValue(value);
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

            myItemCheckbox = new CheckBox(AppContext.getMessage(GenericI18Enum.SEARCH_MYITEMS_CHECKBOX));
            basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox, Alignment.MIDDLE_CENTER);

            Button searchBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
            searchBtn.setIcon(FontAwesome.SEARCH);
            searchBtn.setStyleName(UIConstants.BUTTON_ACTION);
            searchBtn.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    callSearchAction();
                }
            });
            basicSearchBody.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR));
            cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
            cancelBtn.addClickListener(new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    nameField.setValue("");
                }
            });
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }

        @Override
        protected ProjectGenericTaskSearchCriteria fillUpSearchCriteria() {
            searchCriteria = new ProjectGenericTaskSearchCriteria();
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
