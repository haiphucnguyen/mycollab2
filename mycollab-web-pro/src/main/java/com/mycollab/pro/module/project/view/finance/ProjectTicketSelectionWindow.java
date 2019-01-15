package com.mycollab.pro.module.project.view.finance;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.module.project.ui.components.GenericTaskTableFieldDef;
import com.mycollab.module.project.ui.components.TicketTableDisplay;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.icons.VaadinIcons;
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
class ProjectTicketSelectionWindow extends MWindow {
    private static final long serialVersionUID = 1L;

    private TicketTableDisplay ticketTableDisplay;
    private TextField nameField;
    private ProjectTicketSearchCriteria searchCriteria;

    ProjectTicketSelectionWindow(final AssignmentSelectableComp timeEntryWindow) {
        super(UserUIContext.getMessage(TicketI18nEnum.OPT_SELECT_TICKET));
        this.withCenter().withResizable(false).withModal(true).withWidth("800px");
        MVerticalLayout content = new MVerticalLayout();
        ticketTableDisplay = new TicketTableDisplay(Arrays.asList(GenericTaskTableFieldDef.name, GenericTaskTableFieldDef.assignUser));
        ticketTableDisplay.setDisplayNumItems(10);
        ticketTableDisplay.addTableListener(event -> {
            final ProjectTicket task = (ProjectTicket) event.getData();
            if ("name".equals(event.getFieldName())) {
                timeEntryWindow.updateTicketLink(task);
                close();
            }
        });

        searchCriteria = new ProjectTicketSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        ticketTableDisplay.setSearchCriteria(searchCriteria);

        content.with(constructTopPanel(), ticketTableDisplay);
        this.setContent(content);
    }

    private ComponentContainer constructTopPanel() {
        Label nameLbl = new Label(UserUIContext.getMessage(GenericI18Enum.FORM_NAME) + ": ");

        nameField = new TextField();
        nameField.setWidth(WebUIConstants.DEFAULT_CONTROL_WIDTH);

        final MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                .withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.SEARCH);

        final MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                .withStyleName(WebThemes.BUTTON_OPTION);

        return new MHorizontalLayout(nameLbl, nameField, searchBtn, cancelBtn).withMargin(true);
    }

    private void callSearchAction() {
        searchCriteria.setName(StringSearchField.and(nameField.getValue().trim()));
        ticketTableDisplay.setSearchCriteria(searchCriteria);
    }
}