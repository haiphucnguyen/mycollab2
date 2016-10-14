package com.mycollab.mobile.module.project.view.ticket;

import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.mobile.module.project.ui.SearchInputView;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class TicketSearchInputView extends SearchInputView<ProjectTicketSearchCriteria> {

    @Override
    protected ProjectTicketSearchCriteria buildSearchCriteria() {
        ProjectTicketSearchCriteria criteria = new ProjectTicketSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setTypes(CurrentProjectVariables.getRestrictedTicketTypes());
        return criteria;
    }
}
