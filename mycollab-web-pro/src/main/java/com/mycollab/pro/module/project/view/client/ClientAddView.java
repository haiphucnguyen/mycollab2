package com.mycollab.pro.module.project.view.client;

import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.vaadin.events.HasEditFormHandlers;
import com.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ClientAddView extends PageView {
    void editItem(SimpleAccount item);

    HasEditFormHandlers<SimpleAccount> getEditFormHandlers();
}
