package com.mycollab.mobile.module.project.view.ticket;

import com.mycollab.mobile.module.project.ui.AbstractListPageView;
import com.mycollab.mobile.ui.AbstractPagedBeanList;
import com.mycollab.mobile.ui.SearchInputField;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.vaadin.mvp.ViewComponent;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
@ViewComponent
public class TicketListViewImpl extends AbstractListPageView<ProjectTicketSearchCriteria, ProjectTicket> implements TicketListView {
    @Override
    protected AbstractPagedBeanList<ProjectTicketSearchCriteria, ProjectTicket> createBeanList() {
        return null;
    }

    @Override
    protected SearchInputField<ProjectTicketSearchCriteria> createSearchField() {
        return null;
    }
}
