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
package com.esofthead.mycollab.premium.module.project.ui.components;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.collections.CollectionUtils;

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

    static class TimeLoggingBockLayout extends VerticalLayout {
        private static final long serialVersionUID = 1L;

        public TimeLoggingBockLayout(List<TableViewField> visibleFields, TableClickListener tableClickListener,
                                     List<SimpleItemTimeLogging> timeLoggingEntries) {
            TimeTrackingTableDisplay table = new TimeTrackingTableDisplay(visibleFields);
            table.addStyleName(UIConstants.FULL_BORDER_TABLE);
            table.setMargin(new MarginInfo(true, false, false, false));
            table.addTableListener(tableClickListener);
            table.setCurrentDataList(timeLoggingEntries);
            addComponent(table);

            double billable = 0, nonbillable = 0;
            for (SimpleItemTimeLogging item : timeLoggingEntries) {
                billable += item.getIsbillable() ? item.getLogvalue() : 0;
                nonbillable += !item.getIsbillable() ? item.getLogvalue() : 0;
            }

            Label labelTotalHours = new Label(("Total Hours: " + (billable + nonbillable)));
            labelTotalHours.addStyleName(UIConstants.TEXT_LOG_HOURS_TOTAL);
            addComponent(labelTotalHours);

            Label labelBillableHours = new Label(("Billable Hours: " + billable));
            labelBillableHours.setStyleName(UIConstants.TEXT_LOG_HOURS);
            addComponent(labelBillableHours);

            Label labelNonbillableHours = new Label(("Non Billable Hours: " + nonbillable));
            labelNonbillableHours.setStyleName(UIConstants.TEXT_LOG_HOURS);
            addComponent(labelNonbillableHours);
        }
    }
}
