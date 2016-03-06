package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ClientAddView extends PageView {
    void editItem(SimpleAccount item);

    HasEditFormHandlers<SimpleAccount> getEditFormHandlers();
}
