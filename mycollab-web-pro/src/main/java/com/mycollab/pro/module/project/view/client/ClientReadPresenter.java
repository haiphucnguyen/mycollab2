package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.Account;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.mycollab.module.project.event.ClientEvent;
import com.mycollab.vaadin.reporting.FormReportLayout;
import com.mycollab.vaadin.reporting.PrintButton;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;
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
    protected void postInitView() {
        view.getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleAccount>() {
            @Override
            public void onEdit(SimpleAccount data) {
                EventBusFactory.getInstance().post(new ClientEvent.GotoEdit(this, data));
            }

            @Override
            public void onDelete(final SimpleAccount data) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                                accountService.removeWithSession(data, UserUIContext.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
                            }
                        });
            }

            @Override
            public void onAdd(SimpleAccount data) {
                EventBusFactory.getInstance().post(new ClientEvent.GotoAdd(this, null));
            }

            @Override
            public void onClone(SimpleAccount data) {
                Account cloneData = (Account) data.copy();
                cloneData.setId(null);
                EventBusFactory.getInstance().post(new ClientEvent.GotoEdit(this, cloneData));
            }

            @Override
            public void onPrint(Object source, SimpleAccount data) {
                PrintButton btn = (PrintButton) source;
                btn.doPrint(data, new FormReportLayout(CrmTypeConstants.INSTANCE.getACCOUNT(), Account.Field.accountname.name(),
                        AccountDefaultDynaFormLayoutFactory.getForm()));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
            }

            @Override
            public void gotoNext(SimpleAccount data) {
                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                AccountSearchCriteria criteria = new AccountSearchCriteria();
                criteria.setSaccountid(NumberSearchField.equal(AppUI.getAccountId()));
                criteria.setId(NumberSearchField.greaterThan(data.getId()));
                Integer nextId = accountService.getNextItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new ClientEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoLastRecordNotification();
                }
            }

            @Override
            public void gotoPrevious(SimpleAccount data) {
                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                AccountSearchCriteria criteria = new AccountSearchCriteria();
                criteria.setSaccountid(NumberSearchField.equal(AppUI.getAccountId()));
                criteria.setId(NumberSearchField.lessThan(data.getId()));
                Integer nextId = accountService.getPreviousItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new ClientEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoFirstRecordNotification();
                }
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canRead(RolePermissionCollections.INSTANCE.getCRM_ACCOUNT())) {
            AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
            SimpleAccount account = accountService.findById((Integer) data.getParams(), AppUI.getAccountId());
            if (account != null) {
                ClientContainer clientContainer = (ClientContainer) container;
                clientContainer.removeAllComponents();
                clientContainer.addComponent(view);
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
