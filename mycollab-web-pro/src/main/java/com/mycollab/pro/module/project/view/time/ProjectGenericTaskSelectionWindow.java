package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.module.project.ui.components.GenericTaskTableDisplay;
import com.mycollab.module.project.ui.components.GenericTaskTableFieldDef;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
class ProjectGenericTaskSelectionWindow extends MWindow {
    private static final long serialVersionUID = 1L;

    private GenericTaskTableDisplay assignmentTableDisplay;
    private TextField nameField;
    private ProjectGenericTaskSearchCriteria searchCriteria;

    public ProjectGenericTaskSelectionWindow(final AssignmentSelectableComp timeEntryWindow) {
        super("Select Assignments");
        this.withCenter().withResizable(false).withModal(true).withWidth("800px");
        MVerticalLayout content = new MVerticalLayout();
        assignmentTableDisplay = new GenericTaskTableDisplay(Arrays.asList(GenericTaskTableFieldDef.name,
                GenericTaskTableFieldDef.assignUser));
        assignmentTableDisplay.setDisplayNumItems(10);
        assignmentTableDisplay.addTableListener(event -> {
            final ProjectGenericTask task = (ProjectGenericTask) event.getData();
            if ("name".equals(event.getFieldName())) {
                timeEntryWindow.updateLinkTask(task);
                close();
            }
        });

        searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        assignmentTableDisplay.setSearchCriteria(searchCriteria);

        content.with(constructTopPanel(), assignmentTableDisplay);
        this.setContent(content);
    }

    private ComponentContainer constructTopPanel() {
        Label nameLbl = new Label(UserUIContext.getMessage(GenericI18Enum.FORM_NAME) + ": ");

        this.nameField = new TextField();
        this.nameField.setWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);

        final MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                .withStyleName(WebUIConstants.BUTTON_ACTION).withIcon(FontAwesome.SEARCH);

        final MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                .withStyleName(WebUIConstants.BUTTON_OPTION);

        return new MHorizontalLayout(nameLbl, nameField, searchBtn, cancelBtn).withMargin(true);
    }

    private void callSearchAction() {
        searchCriteria.setName(StringSearchField.and(this.nameField.getValue().trim()));
        assignmentTableDisplay.setSearchCriteria(searchCriteria);
    }
}