package com.mycollab.pro.module.project.view.client;

import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ClientListView extends PageView {
    HasSearchHandlers<AccountSearchCriteria> getSearchHandlers();

    void display(AccountSearchCriteria searchCriteria);
}
