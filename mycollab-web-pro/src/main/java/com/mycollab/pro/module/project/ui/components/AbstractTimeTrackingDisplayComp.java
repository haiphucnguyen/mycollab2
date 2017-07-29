package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.pro.module.project.view.time.TimeTrackingTableDisplay;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public abstract class AbstractTimeTrackingDisplayComp extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    protected List<TableViewField> visibleFields;
    protected TableClickListener tableClickListener;

    private String currentItemSearchCriteria = "";
    private List<SimpleItemTimeLogging> itemEntries;

    public AbstractTimeTrackingDisplayComp(List<TableViewField> fields, TableClickListener tableClickListener) {
        super();

        this.visibleFields = fields;
        this.tableClickListener = tableClickListener;
    }

    public void insertItem(SimpleItemTimeLogging item) {
        String itemCriteria = getGroupCriteria(item);
        if (!itemCriteria.equals(currentItemSearchCriteria)) {
            flush();
            currentItemSearchCriteria = itemCriteria;
            itemEntries = new ArrayList<>();
            itemEntries.add(item);
        } else {
            itemEntries.add(item);
        }
    }

    public void flush() {
        if (CollectionUtils.isNotEmpty(itemEntries)) {
            displayGroupItems(itemEntries);
        }
    }

    abstract String getGroupCriteria(SimpleItemTimeLogging timeEntry);

    protected abstract void displayGroupItems(List<SimpleItemTimeLogging> list);

    static class TimeLoggingBockLayout extends MVerticalLayout {
        private static final long serialVersionUID = 1L;

        public TimeLoggingBockLayout() {
            super();
        }

        public TimeLoggingBockLayout(List<TableViewField> visibleFields, TableClickListener tableClickListener,
                                     List<SimpleItemTimeLogging> timeLoggingEntries) {
            withMargin(new MarginInfo(true, false, true, false));
            TimeTrackingTableDisplay table = new TimeTrackingTableDisplay(visibleFields);
            table.addTableListener(tableClickListener);
            table.setCurrentDataList(timeLoggingEntries);
            addComponent(table);

            double billableHours = 0, nonBillableHours = 0, cost = 0;
            for (SimpleItemTimeLogging item : timeLoggingEntries) {
                billableHours += item.getIsbillable() ? item.getLogvalue() : 0;
                nonBillableHours += !item.getIsbillable() ? item.getLogvalue() : 0;
                if (Boolean.TRUE.equals(item.getIsbillable())) {
                    if (Boolean.TRUE.equals(item.getIsovertime())) {
                        if (item.getLogUserOvertimeRate() != null) {
                            cost += item.getLogvalue() * item.getLogUserOvertimeRate();
                        }
                    } else {
                        if (item.getLogUserRate() != null) {
                            cost += item.getLogvalue() * item.getLogUserRate();
                        }
                    }
                }
            }

            MHorizontalLayout summaryLayout = new MHorizontalLayout().withFullWidth();
            with(summaryLayout);
            ELabel totalHoursLbl = new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_TOTAL_HOURS_VALUE, (billableHours + nonBillableHours)))
                    .withStyleName(UIConstants.META_INFO).withWidthUndefined();
            ELabel totalBillableHoursLbl = new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS_VALUE, billableHours))
                    .withStyleName(UIConstants.META_INFO).withWidthUndefined();
            ELabel totalNonBillableHoursLbl = new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS_VALUE,
                    nonBillableHours)).withStyleName(UIConstants.META_INFO).withWidthUndefined();
            MVerticalLayout hoursSummaryLayout = new MVerticalLayout(totalHoursLbl, totalBillableHoursLbl,
                    totalNonBillableHoursLbl).withMargin(false);
            summaryLayout.with(hoursSummaryLayout).withAlign(hoursSummaryLayout, Alignment.TOP_LEFT);

            MVerticalLayout costSummaryLayout = new MVerticalLayout().withMargin(false).with(ELabel.h3(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_COST))
                            .withStyleName(ValoTheme.LABEL_COLORED).withWidthUndefined(),
                    ELabel.hr(), new ELabel(cost + "").withWidthUndefined()).alignAll(Alignment.TOP_RIGHT).withWidth("250px");
            summaryLayout.with(costSummaryLayout).withAlign(costSummaryLayout, Alignment.TOP_RIGHT);
        }
    }
}
