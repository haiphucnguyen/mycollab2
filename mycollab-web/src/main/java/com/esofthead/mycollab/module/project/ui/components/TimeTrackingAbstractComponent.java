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
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 * 
 */
public abstract class TimeTrackingAbstractComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public static final String ORDERBY_ASCENDING = "Ascending";
	public static final String ORDERBY_DESCENDING = "Descending";

	protected List<TableViewField> visibleFields;
	protected TableClickListener tableClickListener;
	protected ItemTimeLoggingService itemTimeLoggingService;
	protected Comparator<SimpleItemTimeLogging> comparator;

	public TimeTrackingAbstractComponent(List<TableViewField> fields,
			TableClickListener tableClickListener) {
		super();
		addStyleName(UIConstants.LAYOUT_LOG);

		this.visibleFields = fields;
		this.tableClickListener = tableClickListener;
		this.itemTimeLoggingService = ApplicationContextUtil
				.getSpringBean(ItemTimeLoggingService.class);
	}

	public void queryData(ItemTimeLoggingSearchCriteria searchCriteria,
			final String orderBy) {
		this.removeAllComponents();

		List<SimpleItemTimeLogging> itemTimeLoggingList = getData(
				searchCriteria, orderBy);
		List<SimpleItemTimeLogging> temp = new ArrayList<SimpleItemTimeLogging>();

		if (orderBy.equals(ORDERBY_ASCENDING)) {
			for (int i = 0; i < itemTimeLoggingList.size(); i++) {
				addItem(itemTimeLoggingList.get(i), temp);
			}
		} else if (orderBy.equals(ORDERBY_DESCENDING)) {
			for (int i = itemTimeLoggingList.size() - 1; i >= 0; i--) {
				addItem(itemTimeLoggingList.get(i), temp);
			}
		}
		displayList(temp);
	}

	@SuppressWarnings("unchecked")
	protected List<SimpleItemTimeLogging> getData(
			ItemTimeLoggingSearchCriteria searchCriteria, final String orderBy) {
		List<SimpleItemTimeLogging> list = itemTimeLoggingService
				.findPagableListByCriteria(new SearchRequest<ItemTimeLoggingSearchCriteria>(
						searchCriteria));
		Collections.sort(list, comparator);

		return list;
	}

	protected abstract void addItem(SimpleItemTimeLogging itemTimeLogging,
			List<SimpleItemTimeLogging> list);

	protected abstract void displayList(List<SimpleItemTimeLogging> list);
}
