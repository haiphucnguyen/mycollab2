package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public class ClientReadPresenter extends AbstractPresenter<ClientReadView> {
    private static final Logger LOG = LoggerFactory.getLogger(ClientReadPresenter.class);

    public ClientReadPresenter() {
        super(ClientReadView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
            AccountService accountService = ApplicationContextUtil.getSpringBean(AccountService.class);
            SimpleAccount account = accountService.findById((Integer) data.getParams(), AppContext.getAccountId());
            if (account != null) {
                view.previewItem(account);
            } else {
                LOG.error("Can not find the account " + data.getParams());
                NotificationUtil.showMessagePermissionAlert();
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
