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
package com.esofthead.mycollab.module.project.ui.components;

import java.util.Comparator;
import java.util.List;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 * 
 */
public class TimeTrackingUserOrderComponent
		extends
			TimeTrackingAbstractComponent {

	private static final long serialVersionUID = 1L;

	public TimeTrackingUserOrderComponent(List<TableViewField> fields,
			TableClickListener tableClickListener) {
		super(fields, tableClickListener);
		comparator = new Comparator<SimpleItemTimeLogging>() {
			@Override
			public int compare(SimpleItemTimeLogging item1,
					SimpleItemTimeLogging item2) {
				return item1.getLoguser().compareTo(item2.getLoguser());
			}
		};
	}

	@Override
	protected void addItem(SimpleItemTimeLogging itemTimeLogging,
			List<SimpleItemTimeLogging> list) {
		if (list.size() > 0
				&& !itemTimeLogging.getLoguser().equals(
						list.get(0).getLoguser())) {
			displayList(list);
			list.clear();
		}

		list.add(itemTimeLogging);
	}

	@Override
	protected void displayList(List<SimpleItemTimeLogging> list) {
		if (list.size() > 0) {
			String username = list.get(0).getLoguser();
			String avatar = list.get(0).getLogUserAvatarId();
			String fullname = list.get(0).getLogUserFullName();

			addComponent(new ProjectUserLink(username, avatar, fullname));

			TimeTrackingTableDisplay table = new TimeTrackingTableDisplay(
					visibleFields);
			table.addStyleName(UIConstants.FULL_BORDER_TABLE);
			table.setMargin(new MarginInfo(true, false, false, false));
			table.addTableListener(this.tableClickListener);
			table.setCurrentDataList(list);
			addComponent(table);

			double billable = 0, nonbillable = 0;
			for (SimpleItemTimeLogging item : list) {
				billable += item.getIsbillable() ? item.getLogvalue() : 0;
				nonbillable += !item.getIsbillable() ? item.getLogvalue() : 0;
			}

			Label labelTotalHours = new Label(
					("Total Hours: " + (billable + nonbillable)));
			labelTotalHours.addStyleName(UIConstants.TEXT_LOG_HOURS_TOTAL);
			addComponent(labelTotalHours);

			Label labelBillableHours = new Label(
					("Billable Hours: " + billable));
			labelBillableHours.setStyleName(UIConstants.TEXT_LOG_HOURS);
			addComponent(labelBillableHours);

			Label labelNonbillableHours = new Label(
					("Non Billable Hours: " + nonbillable));
			labelNonbillableHours.setStyleName(UIConstants.TEXT_LOG_HOURS);
			addComponent(labelNonbillableHours);
		}
	}
}
