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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.arguments.Order;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.google.common.collect.Ordering;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 * 
 */
public abstract class AbstractTimeTrackingDisplayComp extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	protected List<TableViewField> visibleFields;
	protected TableClickListener tableClickListener;
	protected ItemTimeLoggingService itemTimeLoggingService;

	public AbstractTimeTrackingDisplayComp(List<TableViewField> fields,
			TableClickListener tableClickListener) {
		super();
		addStyleName(UIConstants.LAYOUT_LOG);

		this.visibleFields = fields;
		this.tableClickListener = tableClickListener;
		this.itemTimeLoggingService = ApplicationContextUtil
				.getSpringBean(ItemTimeLoggingService.class);
	}

	@SuppressWarnings({ "unchecked" })
	public void queryData(ItemTimeLoggingSearchCriteria searchCriteria,
			Order orderBy) {
		this.removeAllComponents();

		List<SimpleItemTimeLogging> timeLoggingEntries = itemTimeLoggingService
				.findPagableListByCriteria(new SearchRequest<ItemTimeLoggingSearchCriteria>(
						searchCriteria));

		Ordering<SimpleItemTimeLogging> ordering = sortEntries();
		if (orderBy == Order.DESCENDING) {
			Collections.sort(timeLoggingEntries, ordering.reverse());
		} else {
			Collections.sort(timeLoggingEntries, ordering);
		}

		List<SimpleItemTimeLogging> groupLogEntries = new ArrayList<SimpleItemTimeLogging>();
		Object groupCriteria = null;

		for (SimpleItemTimeLogging timeLoggingEntry : timeLoggingEntries) {
			Object itemCriteria = getGroupCriteria(timeLoggingEntry);

			if (itemCriteria.equals(groupCriteria)) {
				groupLogEntries.add(timeLoggingEntry);
			} else {
				displayGroupItems(groupLogEntries);
				groupLogEntries.clear();
				groupCriteria = itemCriteria;
			}
		}

		if (groupLogEntries.size() > 0) {
			displayGroupItems(timeLoggingEntries);
		}
	}

	abstract protected Ordering<SimpleItemTimeLogging> sortEntries();

	abstract Object getGroupCriteria(SimpleItemTimeLogging timeEntry);

	protected abstract void addItem(SimpleItemTimeLogging itemTimeLogging,
			List<SimpleItemTimeLogging> list);

	protected abstract void displayGroupItems(List<SimpleItemTimeLogging> list);

	static class UserComparator implements Comparator<SimpleItemTimeLogging> {

		@Override
		public int compare(SimpleItemTimeLogging o1, SimpleItemTimeLogging o2) {
			return o1.getLoguser().compareTo(o2.getLoguser());
		}
	}

	static class DateComparator implements Comparator<SimpleItemTimeLogging> {

		@Override
		public int compare(SimpleItemTimeLogging o1, SimpleItemTimeLogging o2) {
			return o1.getLogforday().compareTo(o2.getLogforday());
		}

	}
}
