package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.criteria.ProjectAssignmentSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.BasicSearchLayout;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.SearchLayout;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.joda.time.LocalDate;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import static com.mycollab.vaadin.web.ui.WebUIConstants.BUTTON_ACTION;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class AssignmentSearchPanel extends DefaultGenericSearchPanel<ProjectAssignmentSearchCriteria> {
    private boolean isCreateAssignment;

    public AssignmentSearchPanel(boolean isCreateAssignment) {
        this.isCreateAssignment = isCreateAssignment;
    }

    @Override
    protected SearchLayout<ProjectAssignmentSearchCriteria> createBasicSearchLayout() {
        return new AssignmentBasicSearchLayout();
    }

    @Override
    protected SearchLayout<ProjectAssignmentSearchCriteria> createAdvancedSearchLayout() {
        return null;
    }

    @Override
    protected ComponentContainer buildSearchTitle() {
        return new MHorizontalLayout(ELabel.h2(FontAwesome.HASHTAG.getHtml() + " " + UserUIContext.getMessage
                (ProjectCommonI18nEnum.OPT_ASSIGNMENT_LIST)).withWidthUndefined());
    }

    @Override
    protected Component buildExtraControls() {
        if (isCreateAssignment) {
            MButton newAssignmentBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.ACTION_NEW_ASSIGNMENT),
                    clickEvent -> UI.getCurrent().addWindow(new AssignmentAddWindow(new LocalDate().toDate(),
                            CurrentProjectVariables.getProjectId(), null, true))).withIcon(FontAwesome.PLUS).withStyleName(BUTTON_ACTION);
            return newAssignmentBtn;
        }
        return null;
    }

    private class AssignmentBasicSearchLayout extends BasicSearchLayout<ProjectAssignmentSearchCriteria> {
        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

        private AssignmentBasicSearchLayout() {
            super(AssignmentSearchPanel.this);
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
            return basicSearchBody;
        }

        @Override
        protected ProjectAssignmentSearchCriteria fillUpSearchCriteria() {
            ProjectAssignmentSearchCriteria searchCriteria = new ProjectAssignmentSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            searchCriteria.setName(StringSearchField.and(nameField.getValue().trim()));
            if (myItemCheckbox.getValue()) {
                searchCriteria.setAssignUser(StringSearchField.and(UserUIContext.getUsername()));
            } else {
                searchCriteria.setAssignUser(null);
            }
            return searchCriteria;
        }

    }
}
