package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientAddViewImpl extends AbstractPageView implements ClientAddView {
    public ClientAddViewImpl() {
        this.setMargin(true);
    }

    @Override
    public void editItem(SimpleAccount item) {

    }
}

