package com.mycollab.module.crm.view.activity;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.crm.CrmLinkGenerator;
import com.mycollab.module.crm.domain.CallWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCall;
import com.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.mycollab.module.crm.event.ActivityEvent;
import com.mycollab.module.crm.i18n.CallI18nEnum;
import com.mycollab.module.crm.service.CallService;
import com.mycollab.module.crm.view.CrmGenericPresenter;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CallReadPresenter extends CrmGenericPresenter<CallReadView> {
    private static final long serialVersionUID = 1L;

    public CallReadPresenter() {
        super(CallReadView.class);
    }

    @Override
    protected void postInitView() {
        view.getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleCall>() {
            @Override
            public void onEdit(SimpleCall data) {
                EventBusFactory.getInstance().post(new ActivityEvent.CallEdit(this, data));
            }

            @Override
            public void onAdd(SimpleCall data) {
                EventBusFactory.getInstance().post(new ActivityEvent.CallAdd(this, null));
            }

            @Override
            public void onDelete(final SimpleCall data) {
                ConfirmDialogExt.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppUI.getSiteName()),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                CallService callService = AppContextUtil.getSpringBean(CallService.class);
                                callService.removeWithSession(data, UserUIContext.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new ActivityEvent.GotoTodoList(this, null));
                            }
                        });
            }

            @Override
            public void onClone(SimpleCall data) {
                CallWithBLOBs cloneData = (CallWithBLOBs) data.copy();
                cloneData.setId(null);
                EventBusFactory.getInstance().post(new ActivityEvent.CallEdit(this, cloneData));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new ActivityEvent.GotoTodoList(this, null));
            }

            @Override
            public void gotoNext(SimpleCall data) {
                CallService callService = AppContextUtil.getSpringBean(CallService.class);
                CallSearchCriteria criteria = new CallSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.GREATER));
                Integer nextId = callService.getNextItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new ActivityEvent.CallRead(this, nextId));
                } else {
                    NotificationUtil.showGotoLastRecordNotification();
                }

            }

            @Override
            public void gotoPrevious(SimpleCall data) {
                CallService callService = AppContextUtil.getSpringBean(CallService.class);
                CallSearchCriteria criteria = new CallSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.LESS_THAN));
                Integer nextId = callService.getPreviousItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new ActivityEvent.CallRead(this, nextId));
                } else {
                    NotificationUtil.showGotoFirstRecordNotification();
                }
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canRead(RolePermissionCollections.CRM_CALL)) {
            SimpleCall call;
            if (data.getParams() instanceof Integer) {
                CallService callService = AppContextUtil.getSpringBean(CallService.class);
                call = callService.findById((Integer) data.getParams(), AppUI.getAccountId());
                if (call == null) {
                    NotificationUtil.showRecordNotExistNotification();
                    return;
                }
            } else {
                throw new MyCollabException("Invalid data: " + data);
            }

            super.onGo(container, data);

            view.previewItem(call);
            AppUI.addFragment(CrmLinkGenerator.generateCallPreviewLink(call.getId()), UserUIContext.
                    getMessage(GenericI18Enum.BROWSER_PREVIEW_ITEM_TITLE, UserUIContext.getMessage(CallI18nEnum.SINGLE),
                            call.getSubject()));
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}
