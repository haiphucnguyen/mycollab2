package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.vaadin.event.HasEditFormHandlers;
import com.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ClientAddView extends PageView {
    void editItem(Client item);

    HasEditFormHandlers<Client> getEditFormHandlers();
}
