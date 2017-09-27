package com.mycollab.mobile.module.crm.view.cases;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.event.CaseEvent;
import com.mycollab.mobile.module.crm.view.AbstractCrmPresenter;
import com.mycollab.mobile.ui.ConfirmDialog;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.mycollab.module.crm.service.CaseService;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class CaseReadPresenter extends AbstractCrmPresenter<CaseReadView> {
    private static final long serialVersionUID = 4762457350559016677L;

    public CaseReadPresenter() {
        super(CaseReadView.class);
    }

    @Override
    protected void postInitView() {
        getView().getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleCase>() {
            @Override
            public void onEdit(SimpleCase data) {
                EventBusFactory.getInstance().post(new CaseEvent.GotoEdit(this, data));
            }

            @Override
            public void onAdd(SimpleCase data) {
                EventBusFactory.getInstance().post(new CaseEvent.GotoAdd(this, null));
            }

            @Override
            public void onDelete(final SimpleCase data) {
                ConfirmDialog.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        dialog -> {
                            if (dialog.isConfirmed()) {
                                CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                                caseService.removeWithSession(data,
                                        UserUIContext.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new CaseEvent.GotoList(this, null));
                            }
                        });
            }

            @Override
            public void onClone(SimpleCase data) {
                SimpleCase cloneData = (SimpleCase) data.copy();
                cloneData.setId(null);
                EventBusFactory.getInstance().post(new CaseEvent.GotoEdit(this, cloneData));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new CaseEvent.GotoList(this, null));
            }

            @Override
            public void gotoNext(SimpleCase data) {
                CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                CaseSearchCriteria criteria = new CaseSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.GREATER));
                Integer nextId = caseService.getNextItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new CaseEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoLastRecordNotification();
                }

            }

            @Override
            public void gotoPrevious(SimpleCase data) {
                CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                CaseSearchCriteria criteria = new CaseSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.LESS_THAN));
                Integer nextId = caseService.getPreviousItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new CaseEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoFirstRecordNotification();
                }
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canRead(RolePermissionCollections.CRM_CASE)) {

            if (data.getParams() instanceof Integer) {
                CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                SimpleCase cases = caseService.findById((Integer) data.getParams(), AppUI.getAccountId());
                if (cases != null) {
                    getView().previewItem(cases);
                    super.onGo(container, data);
                } else {
                    NotificationUtil.showRecordNotExistNotification();
                }
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

}
