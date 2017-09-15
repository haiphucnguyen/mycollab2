package com.mycollab.mobile.module.crm.view.contact;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.events.ContactEvent;
import com.mycollab.mobile.module.crm.view.AbstractCrmPresenter;
import com.mycollab.mobile.ui.ConfirmDialog;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.module.crm.service.ContactService;
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
 * @since 4.0
 */
public class ContactReadPresenter extends AbstractCrmPresenter<ContactReadView> {
    private static final long serialVersionUID = 1L;

    public ContactReadPresenter() {
        super(ContactReadView.class);
    }

    @Override
    protected void postInitView() {
        getView().getPreviewFormHandlers().addFormHandler(new DefaultPreviewFormHandler<SimpleContact>() {
            @Override
            public void onEdit(SimpleContact data) {
                EventBusFactory.getInstance().post(new ContactEvent.GotoEdit(this, data));
            }

            @Override
            public void onAdd(SimpleContact data) {
                EventBusFactory.getInstance().post(new ContactEvent.GotoAdd(this, null));
            }

            @Override
            public void onDelete(final SimpleContact data) {
                ConfirmDialog.show(UI.getCurrent(),
                        UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                        dialog -> {
                            if (dialog.isConfirmed()) {
                                ContactService ContactService = AppContextUtil.getSpringBean(ContactService.class);
                                ContactService.removeWithSession(data, UserUIContext.getUsername(), AppUI.getAccountId());
                                EventBusFactory.getInstance().post(new ContactEvent.GotoList(this, null));
                            }
                        });
            }

            @Override
            public void onClone(SimpleContact data) {
                SimpleContact cloneData = (SimpleContact) data.copy();
                cloneData.setId(null);
                EventBusFactory.getInstance().post(new ContactEvent.GotoEdit(this, cloneData));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new ContactEvent.GotoList(this, null));
            }

            @Override
            public void gotoNext(SimpleContact data) {
                ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
                ContactSearchCriteria criteria = new ContactSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.GREATER));
                Integer nextId = contactService.getNextItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new ContactEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoLastRecordNotification();
                }
            }

            @Override
            public void gotoPrevious(SimpleContact data) {
                ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
                ContactSearchCriteria criteria = new ContactSearchCriteria();
                criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
                criteria.setId(new NumberSearchField(data.getId(), NumberSearchField.LESS_THAN));
                Integer nextId = contactService.getPreviousItemKey(criteria);
                if (nextId != null) {
                    EventBusFactory.getInstance().post(new ContactEvent.GotoRead(this, nextId));
                } else {
                    NotificationUtil.showGotoFirstRecordNotification();
                }
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canRead(RolePermissionCollections.CRM_CONTACT)) {

            if (data.getParams() instanceof Integer) {
                ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
                SimpleContact contact = contactService.findById((Integer) data.getParams(), AppUI.getAccountId());
                if (contact != null) {
                    getView().previewItem(contact);
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
