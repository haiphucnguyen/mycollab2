package com.mycollab.mobile.module.project.view.ticket;

import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.mobile.module.project.ui.SearchInputView;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class TicketSearchInputView extends SearchInputView<ProjectTicketSearchCriteria> {

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        MVerticalLayout content = new MVerticalLayout();
        MTextField nameField = new MTextField().withFullWidth().withInputPrompt("Query ticket name ...");
        content.with(nameField);
        setContent(content);
    }

    @Override
    protected ProjectTicketSearchCriteria buildSearchCriteria() {
        ProjectTicketSearchCriteria criteria = new ProjectTicketSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setTypes(CurrentProjectVariables.getRestrictedTicketTypes());
        criteria.setAssignUser(StringSearchField.and("haiphucnguyen@gmail.com"));
        return criteria;
    }
}
