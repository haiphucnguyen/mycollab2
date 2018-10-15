package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.module.project.view.service.TicketComponentFactory;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.joda.time.LocalDate;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import static com.mycollab.vaadin.web.ui.WebThemes.BUTTON_ACTION;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class AssignmentSearchPanel extends DefaultGenericSearchPanel<ProjectTicketSearchCriteria> {
    private boolean isCreateAssignment;

    public AssignmentSearchPanel(boolean isCreateAssignment) {
        this.isCreateAssignment = isCreateAssignment;
    }

    @Override
    protected SearchLayout<ProjectTicketSearchCriteria> createBasicSearchLayout() {
        return new AssignmentBasicSearchLayout();
    }

    @Override
    protected SearchLayout<ProjectTicketSearchCriteria> createAdvancedSearchLayout() {
        return null;
    }

    @Override
    protected ComponentContainer buildSearchTitle() {
        return new MHorizontalLayout(ELabel.h2(FontAwesome.HASHTAG.getHtml() + " " + UserUIContext.getMessage
                (ProjectCommonI18nEnum.OPT_ASSIGNMENT_LIST)).withUndefinedWidth());
    }

    @Override
    protected Component buildExtraControls() {
        if (isCreateAssignment) {
            return new MButton(UserUIContext.getMessage(TicketI18nEnum.NEW),
                    clickEvent -> UI.getCurrent().addWindow(AppContextUtil.getSpringBean(TicketComponentFactory.class).createNewTicketWindow(new
                            LocalDate().toDate(), CurrentProjectVariables.getProjectId(), null, true))).withIcon(VaadinIcons.PLUS).withStyleName(BUTTON_ACTION);
        }
        return null;
    }

    private class AssignmentBasicSearchLayout extends BasicSearchLayout<ProjectTicketSearchCriteria> {
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
                    .withIcon(FontAwesome.SEARCH).withStyleName(WebThemes.BUTTON_ACTION)
                    .withClickShortcut(ShortcutAction.KeyCode.ENTER);
            basicSearchBody.with(searchBtn).withAlign(searchBtn, Alignment.MIDDLE_LEFT);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> nameField.setValue(""))
                    .withStyleName(WebThemes.BUTTON_OPTION);
            basicSearchBody.with(cancelBtn).withAlign(cancelBtn, Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }

        @Override
        protected ProjectTicketSearchCriteria fillUpSearchCriteria() {
            ProjectTicketSearchCriteria searchCriteria = new ProjectTicketSearchCriteria();
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
