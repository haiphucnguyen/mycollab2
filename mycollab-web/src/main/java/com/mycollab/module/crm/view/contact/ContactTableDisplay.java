package com.mycollab.module.crm.view.contact;

import com.mycollab.common.TableViewField;
import com.mycollab.module.crm.CrmTooltipGenerator;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.mycollab.module.crm.i18n.OptionI18nEnum.OpportunityLeadSource;
import com.mycollab.module.crm.service.ContactService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.CheckBoxDecor;
import com.mycollab.vaadin.web.ui.LabelLink;
import com.mycollab.vaadin.web.ui.UserLink;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.ui.Label;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ContactTableDisplay extends DefaultPagedBeanTable<ContactService, ContactSearchCriteria, SimpleContact> {
    private static final long serialVersionUID = 1L;

    public ContactTableDisplay(List<TableViewField> displayColumns) {
        this(null, displayColumns);
    }

    public ContactTableDisplay(TableViewField requiredColumn, List<TableViewField> displayColumns) {
        this(null, requiredColumn, displayColumns);

    }

    public ContactTableDisplay(String viewId, TableViewField requiredColumn, List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(ContactService.class), SimpleContact.class, viewId, requiredColumn, displayColumns);

        addGeneratedColumn("selected", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);
            final CheckBoxDecor cb = new CheckBoxDecor("", contact.isSelected());
            cb.addValueChangeListener(valueChangeEvent -> {
                fireSelectItemEvent(contact);
                fireTableEvent(new TableClickEvent(ContactTableDisplay.this, contact, "selected"));
            });
            contact.setExtraData(cb);
            return cb;
        });

        addGeneratedColumn("contactName", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);

            LabelLink b = new LabelLink(contact.getContactName(), CrmLinkBuilder.generateContactPreviewLinkFull(contact.getId()));
            b.setDescription(CrmTooltipGenerator.generateToolTipContact(UserUIContext.getUserLocale(), AppUI.getDateFormat(),
                    contact, AppUI.getSiteUrl(), UserUIContext.getUserTimeZone()));
            return b;
        });

        addGeneratedColumn("createdtime", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);
            return new Label(UserUIContext.formatDateTime(contact.getCreatedtime()));
        });

        addGeneratedColumn("email", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);
            return ELabel.email(contact.getEmail());
        });

        addGeneratedColumn("leadsource", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);
            return ELabel.i18n(contact.getLeadsource(), OpportunityLeadSource.class);
        });

        addGeneratedColumn("accountName", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);
            if (contact.getAccountName() != null) {
                return new LabelLink(contact.getAccountName(), CrmLinkBuilder.generateAccountPreviewLinkFull(contact.getAccountid()));
            } else {
                return new Label();
            }
        });

        addGeneratedColumn("birthday", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);
            return new Label(UserUIContext.formatDate(contact.getBirthday()));
        });

        addGeneratedColumn("assignUserFullName", (source, itemId, columnId) -> {
            final SimpleContact contact = getBeanByIndex(itemId);
            return new UserLink(contact.getAssignuser(), contact.getAssignUserAvatarId(), contact.getAssignUserFullName());
        });

        this.setWidth("100%");
    }
}
