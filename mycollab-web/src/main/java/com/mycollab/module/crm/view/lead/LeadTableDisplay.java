package com.mycollab.module.crm.view.lead;

import com.mycollab.common.TableViewField;
import com.mycollab.module.crm.CrmTooltipGenerator;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.mycollab.module.crm.i18n.OptionI18nEnum.AccountIndustry;
import com.mycollab.module.crm.i18n.OptionI18nEnum.LeadStatus;
import com.mycollab.module.crm.i18n.OptionI18nEnum.OpportunityLeadSource;
import com.mycollab.module.crm.service.LeadService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.*;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class LeadTableDisplay extends DefaultPagedBeanTable<LeadService, LeadSearchCriteria, SimpleLead> {

    public LeadTableDisplay(List<TableViewField> displayColumns) {
        this(null, displayColumns);
    }

    public LeadTableDisplay(TableViewField requiredColumn, List<TableViewField> displayColumns) {
        this(null, requiredColumn, displayColumns);

    }

    public LeadTableDisplay(String viewId, TableViewField requiredColumn, List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(LeadService.class),
                SimpleLead.class, viewId, requiredColumn, displayColumns);

        this.addGeneratedColumn("selected", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);
            final CheckBoxDecor cb = new CheckBoxDecor("", lead.isSelected());
            cb.setImmediate(true);
            cb.addValueChangeListener(valueChangeEvent -> {
                fireSelectItemEvent(lead);
                fireTableEvent(new TableClickEvent(LeadTableDisplay.this, lead, "selected"));
            });
            lead.setExtraData(cb);
            return cb;
        });

        this.addGeneratedColumn("leadName", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);

            LabelLink b = new LabelLink(lead.getLeadName(), CrmLinkBuilder.INSTANCE.generateLeadPreviewLinkFull(lead.getId()));
            if ("Dead".equals(lead.getStatus()) || "Converted".equals(lead.getStatus())) {
                b.addStyleName(WebThemes.LINK_COMPLETED);
            }
            b.setDescription(CrmTooltipGenerator.INSTANCE.generateTooltipLead(
                    UserUIContext.getUserLocale(), lead,
                    AppUI.getSiteUrl(), UserUIContext.getUserTimeZone()));
            return b;
        });

        this.addGeneratedColumn("assignUserFullName", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);
            return new UserLink(lead.getAssignuser(), lead.getAssignUserAvatarId(), lead.getAssignUserFullName());
        });

        this.addGeneratedColumn("email", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);
            Link l = new Link();
            l.setResource(new ExternalResource("mailto:" + lead.getEmail()));
            l.setCaption(lead.getEmail());
            return l;
        });

        this.addGeneratedColumn("status", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);
            return ELabel.i18n(lead.getStatus(), LeadStatus.class);
        });

        this.addGeneratedColumn("industry", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);
            return ELabel.i18n(lead.getIndustry(), AccountIndustry.class);
        });

        this.addGeneratedColumn("source", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);
            return ELabel.i18n(lead.getSource(), OpportunityLeadSource.class);
        });

        this.addGeneratedColumn("website", (source, itemId, columnId) -> {
            final SimpleLead lead = getBeanByIndex(itemId);
            if (lead.getWebsite() != null) {
                return new UrlLink(lead.getWebsite());
            } else {
                return new Label("");
            }
        });

        this.setWidth("100%");
    }
}
