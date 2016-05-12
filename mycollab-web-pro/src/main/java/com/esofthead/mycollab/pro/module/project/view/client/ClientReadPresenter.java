package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.module.project.events.ClientEvent;
import com.esofthead.mycollab.reporting.FormReportLayout;
import com.esofthead.mycollab.reporting.PrintButton;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;

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
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppContext.getSiteName()),
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                        AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                        new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                                    accountService.removeWithSession(data, AppContext.getUsername(), AppContext.getAccountId());
                                    EventBusFactory.getInstance().post(new ClientEvent.GotoList(this, null));
                                }
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
                btn.doPrint(data, new FormReportLayout(CrmTypeConstants.ACCOUNT, Account.Field.accountname.name(),
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
                criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.GREATER));
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
                criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.LESSTHAN));
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
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (AppContext.canRead(RolePermissionCollections.CRM_ACCOUNT)) {
            AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
            SimpleAccount account = accountService.findById((Integer) data.getParams(), AppContext.getAccountId());
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
