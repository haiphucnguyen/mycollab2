package com.mycollab.mobile.module.crm.view.contact;

import com.hp.gagawa.java.elements.A;
import com.mycollab.mobile.ui.DefaultPagedBeanList;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.module.crm.service.ContactService;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Component;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class ContactListDisplay extends DefaultPagedBeanList<ContactService, ContactSearchCriteria, SimpleContact> {
    private static final long serialVersionUID = -2234454107835680053L;

    public ContactListDisplay() {
        super(AppContextUtil.getSpringBean(ContactService.class), new ContactRowDisplayHandler());
    }

    private static class ContactRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleContact> {

        @Override
        public Component generateRow(IBeanList<SimpleContact> host, final SimpleContact contact, int rowIndex) {
            MVerticalLayout rowLayout = new MVerticalLayout().withMargin(false).withSpacing(false).withFullWidth();
            A itemLink = new A(CrmLinkBuilder.INSTANCE.generateContactPreviewLinkFull(contact.getId())).appendText(contact.getContactName());
            MCssLayout itemWrap = new MCssLayout(ELabel.html(itemLink.write()));
            rowLayout.addComponent(new MHorizontalLayout(ELabel.fontIcon(CrmAssetsManager.getAsset
                    (CrmTypeConstants.INSTANCE.getCONTACT())), itemWrap).expand(itemWrap).withFullWidth());
            return rowLayout;
        }

    }
}
