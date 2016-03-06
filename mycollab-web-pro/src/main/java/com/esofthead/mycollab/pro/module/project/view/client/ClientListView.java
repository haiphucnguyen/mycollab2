package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ClientListView extends PageView {
    HasSearchHandlers<AccountSearchCriteria> getSearchHandlers();

    void display(AccountSearchCriteria searchCriteria);
}
