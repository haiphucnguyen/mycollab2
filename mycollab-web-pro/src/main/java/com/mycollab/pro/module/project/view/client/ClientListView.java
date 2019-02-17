package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.criteria.ClientSearchCriteria;
import com.mycollab.vaadin.event.HasSearchHandlers;
import com.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ClientListView extends PageView {

    HasSearchHandlers<ClientSearchCriteria> getSearchHandlers();

    void display(ClientSearchCriteria searchCriteria);
}
