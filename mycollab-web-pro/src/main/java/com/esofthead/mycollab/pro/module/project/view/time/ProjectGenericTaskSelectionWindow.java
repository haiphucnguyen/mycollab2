package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableDisplay;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableFieldDef;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
class ProjectGenericTaskSelectionWindow extends Window {
    private static final long serialVersionUID = 1L;

    private GenericTaskTableDisplay assignmentTableDisplay;
    private TextField nameField;
    private ProjectGenericTaskSearchCriteria searchCriteria;

    public ProjectGenericTaskSelectionWindow(final AssignmentSelectableComp timeEntryWindow) {
        super("Select Assignments");
        this.center();
        this.setResizable(false);
        this.setModal(true);
        this.setWidth("800px");
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
        Label nameLbl = new Label("Name:");

        this.nameField = new TextField();
        this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);

        final MButton searchBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                .withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.SEARCH);

        final MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                .withStyleName(UIConstants.BUTTON_OPTION);

        return new MHorizontalLayout(nameLbl, nameField, searchBtn, cancelBtn).withMargin(true);
    }

    private void callSearchAction() {
        searchCriteria.setName(StringSearchField.and(this.nameField.getValue().trim()));
        assignmentTableDisplay.setSearchCriteria(searchCriteria);
    }
}
