package com.mycollab.module.crm.view.contact;

import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.vaadin.web.ui.IListView;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface ContactListView extends IListView<ContactSearchCriteria, SimpleContact> {
}
