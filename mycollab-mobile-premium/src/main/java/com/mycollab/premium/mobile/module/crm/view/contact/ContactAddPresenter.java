package com.mycollab.premium.mobile.module.crm.view.contact;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.mobile.module.crm.event.ContactEvent;
import com.mycollab.mobile.module.crm.view.AbstractCrmPresenter;
import com.mycollab.mobile.module.crm.view.contact.ContactAddView;
import com.mycollab.mobile.module.crm.view.contact.IContactAddPresenter;
import com.mycollab.mobile.shell.event.ShellEvent;
import com.mycollab.module.crm.domain.Contact;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.i18n.ContactI18nEnum;
import com.mycollab.module.crm.service.ContactService;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class ContactAddPresenter extends AbstractCrmPresenter<ContactAddView> implements IContactAddPresenter {
    private static final long serialVersionUID = -2859144864540984138L;

    public ContactAddPresenter() {
        super(ContactAddView.class);
    }

    @Override
    protected void postInitView() {
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleContact>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(final SimpleContact contact) {
                saveContact(contact);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }

            @Override
            public void onSaveAndNew(final SimpleContact contact) {
                saveContact(contact);
                EventBusFactory.getInstance().post(new ContactEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (UserUIContext.canWrite(RolePermissionCollections.CRM_CONTACT)) {
            SimpleContact contact = null;
            if (data.getParams() instanceof SimpleContact) {
                contact = (SimpleContact) data.getParams();
            } else if (data.getParams() instanceof Integer) {
                ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
                contact = contactService.findById((Integer) data.getParams(), AppUI.getAccountId());
            }
            if (contact == null) {
                NotificationUtil.showRecordNotExistNotification();
                return;
            }
            super.onGo(container, data);
            getView().editItem(contact);

            if (contact.getId() == null) {
                AppUI.addFragment("crm/contact/add", UserUIContext.getMessage(GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
                        UserUIContext.getMessage(ContactI18nEnum.SINGLE)));
            } else {
                AppUI.addFragment("crm/contact/edit/" + UrlEncodeDecoder.encode(contact.getId()),
                        UserUIContext.getMessage(GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
                                UserUIContext.getMessage(ContactI18nEnum.SINGLE), contact.getLastname()));
            }
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveContact(Contact contact) {
        ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
        contact.setSaccountid(AppUI.getAccountId());
        if (contact.getId() == null) {
            contactService.saveWithSession(contact, UserUIContext.getUsername());
        } else {
            contactService.updateWithSession(contact, UserUIContext.getUsername());
        }
    }

}
