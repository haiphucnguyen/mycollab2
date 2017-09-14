package com.mycollab.module.crm.view.cases;

import com.mycollab.common.TableViewField;
import com.mycollab.module.crm.CrmTooltipGenerator;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.mycollab.module.crm.i18n.OptionI18nEnum.*;
import com.mycollab.module.crm.service.CaseService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.*;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class CaseTableDisplay extends DefaultPagedBeanTable<CaseService, CaseSearchCriteria, SimpleCase> {

    public CaseTableDisplay(List<TableViewField> displayColumns) {
        this(null, displayColumns);
    }

    public CaseTableDisplay(TableViewField requiredColumn, List<TableViewField> displayColumns) {
        this(null, requiredColumn, displayColumns);
    }

    public CaseTableDisplay(String viewId, TableViewField requiredColumn, List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(CaseService.class), SimpleCase.class, viewId, requiredColumn, displayColumns);

        this.addGeneratedColumn("selected", (source, itemId, columnId) -> {
            final SimpleCase cases = getBeanByIndex(itemId);
            CheckBoxDecor cb = new CheckBoxDecor("", cases.isSelected());
            cb.setImmediate(true);
            cb.addValueChangeListener(valueChangeEvent -> fireSelectItemEvent(cases));
            cases.setExtraData(cb);
            return cb;
        });

        this.addGeneratedColumn("subject", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            LabelLink b = new LabelLink(cases.getSubject(), CrmLinkBuilder.generateCasePreviewLinkFull(cases.getId()));

            if (cases.isCompleted()) {
                b.addStyleName(WebThemes.LINK_COMPLETED);
            }
            b.setDescription(CrmTooltipGenerator.generateTooltipCases(UserUIContext.getUserLocale(), cases,
                    AppUI.getSiteUrl(), UserUIContext.getUserTimeZone()));
            return b;
        });

        this.addGeneratedColumn("accountName", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return new LabelLink(cases.getAccountName(), CrmLinkBuilder.generateAccountPreviewLinkFull(cases.getAccountid()));
        });

        this.addGeneratedColumn("assignUserFullName", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return new UserLink(cases.getAssignuser(), cases.getAssignUserAvatarId(), cases.getAssignUserFullName());
        });

        this.addGeneratedColumn("createdtime", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return new ELabel(UserUIContext.formatPrettyTime(cases.getCreatedtime())).withDescription(UserUIContext
                    .formatDateTime(cases.getCreatedtime()));
        });

        this.addGeneratedColumn("origin", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return ELabel.i18n(cases.getOrigin(), CaseOrigin.class);
        });

        this.addGeneratedColumn("priority", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return ELabel.i18n(cases.getPriority(), CasePriority.class);
        });

        this.addGeneratedColumn("status", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return ELabel.i18n(cases.getStatus(), CaseStatus.class);
        });

        this.addGeneratedColumn("reason", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return ELabel.i18n(cases.getReason(), CaseReason.class);
        });

        this.addGeneratedColumn("type", (source, itemId, columnId) -> {
            SimpleCase cases = getBeanByIndex(itemId);
            return ELabel.i18n(cases.getType(), CaseType.class);
        });

        this.setWidth("100%");
    }
}
