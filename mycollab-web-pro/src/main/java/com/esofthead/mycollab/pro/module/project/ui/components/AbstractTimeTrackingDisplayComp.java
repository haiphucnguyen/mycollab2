/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.pro.module.project.ui.components;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.pro.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.collections.CollectionUtils;
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

        public TimeLoggingBockLayout(List<TableViewField> visibleFields, TableClickListener tableClickListener,
                                     List<SimpleItemTimeLogging> timeLoggingEntries) {
            withMargin(new MarginInfo(true, false, true, false));
            TimeTrackingTableDisplay table = new TimeTrackingTableDisplay(visibleFields);
            table.addStyleName(UIConstants.FULL_BORDER_TABLE);
            table.addTableListener(tableClickListener);
            table.setCurrentDataList(timeLoggingEntries);
            addComponent(table);

            double billable = 0, nonbillable = 0;
            for (SimpleItemTimeLogging item : timeLoggingEntries) {
                billable += item.getIsbillable() ? item.getLogvalue() : 0;
                nonbillable += !item.getIsbillable() ? item.getLogvalue() : 0;
            }

            ELabel totalHoursLbl = new ELabel(("Total Hours: " + (billable + nonbillable))).withStyleName(ValoTheme
                    .LABEL_BOLD).withWidthUndefined();
            ELabel totalBillableHoursLbl = new ELabel(("Billable Hours: " + billable)).withWidthUndefined();
            ELabel totalNonBillableHoursLbl = new ELabel(("Non Billable Hours: " + nonbillable)).withWidthUndefined();
            with(totalHoursLbl, totalBillableHoursLbl, totalNonBillableHoursLbl).withAlign(totalHoursLbl, Alignment.TOP_RIGHT)
                    .withAlign(totalBillableHoursLbl, Alignment.TOP_RIGHT).withAlign(totalNonBillableHoursLbl, Alignment.TOP_RIGHT);
        }
    }
}
