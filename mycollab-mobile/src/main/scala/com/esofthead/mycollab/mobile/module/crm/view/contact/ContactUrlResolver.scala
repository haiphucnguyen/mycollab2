package com.esofthead.mycollab.mobile.module.crm.view.contact

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.crm.events.{ContactEvent, CrmEvent}
import com.esofthead.mycollab.mobile.module.crm.{CrmModuleScreenData, CrmUrlResolver}
import com.esofthead.mycollab.module.crm.domain.Contact
import com.esofthead.mycollab.module.crm.i18n.CrmCommonI18nEnum
import com.esofthead.mycollab.vaadin.AppContext

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ContactUrlResolver extends CrmUrlResolver {
    this.addSubResolver("list", new ContactListUrlResolver)
    this.addSubResolver("preview", new ContactPreviewUrlResolver)
    this.addSubResolver("add", new ContactAddUrlResolver)
    this.addSubResolver("edit", new ContactEditUrlResolver)

    class ContactListUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new CrmEvent.GotoContainer(this,
                new CrmModuleScreenData.GotoModule(AppContext.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACTS_HEADER))))
        }
    }

    class ContactAddUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            EventBusFactory.getInstance.post(new ContactEvent.GotoAdd(this, new Contact))
        }
    }

    class ContactEditUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val contactId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ContactEvent.GotoEdit(this, contactId))
        }
    }

    class ContactPreviewUrlResolver extends CrmUrlResolver {
        protected override def handlePage(params: String*) {
            val contactId: Int = new UrlTokenizer(params(0)).getInt
            EventBusFactory.getInstance.post(new ContactEvent.GotoRead(this, contactId))
        }
    }

}
