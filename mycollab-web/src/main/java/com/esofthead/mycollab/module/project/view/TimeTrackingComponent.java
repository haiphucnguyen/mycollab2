package com.esofthead.mycollab.module.project.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.time.TimeTrackingTableDisplay;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class TimeTrackingComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"EEEE, dd MMMM yyyy");

	private List<TableViewField> visibleFields;
	private TableClickListener tableClickListener;
	private ItemTimeLoggingService itemTimeLoggingService;

	public TimeTrackingComponent(ItemTimeLoggingService itemTimeLoggingService,
			List<TableViewField> fields, TableClickListener tableClickListener) {
		super();
		addStyleName(UIConstants.LAYOUT_LOG);

		this.visibleFields = fields;
		this.tableClickListener = tableClickListener;
		this.itemTimeLoggingService = itemTimeLoggingService;
	}

	public void show(ItemTimeLoggingSearchCriteria searchCriteria) {
		removeAllComponents();
		
		@SuppressWarnings("unchecked")
		List<SimpleItemTimeLogging> itemTimeLoggingList = itemTimeLoggingService
				.findPagableListByCriteria(new SearchRequest<ItemTimeLoggingSearchCriteria>(
						searchCriteria));
		Date current = new Date(0);
		double billable = 0, nonbillable = 0;
		List<SimpleItemTimeLogging> list = new ArrayList<SimpleItemTimeLogging>();

		for (SimpleItemTimeLogging itemTimeLogging : itemTimeLoggingList) {
			if (DateTimeUtils.compareByDate(itemTimeLogging.getLogforday(),
					current) > 0) {
				showRecord(current, list, billable, nonbillable);

				current = itemTimeLogging.getLogforday();
				list.clear();
				billable = nonbillable = 0;
			}

			list.add(itemTimeLogging);
			billable += itemTimeLogging.getIsbillable() ? itemTimeLogging
					.getLogvalue() : 0;
			nonbillable += !itemTimeLogging.getIsbillable() ? itemTimeLogging
					.getLogvalue() : 0;
		}
		showRecord(current, list, billable, nonbillable);
	}

	private void showRecord(Date date, List<SimpleItemTimeLogging> list,
			Double billable, Double nonbillable) {
		if (list.size() > 0) {
			Label logForDay = new Label(DATE_FORMAT.format(date));
			logForDay.addStyleName(UIConstants.TEXT_LOG_DATE);
			addComponent(logForDay);

			TimeTrackingTableDisplay table = new TimeTrackingTableDisplay(
					visibleFields);
			table.addStyleName(UIConstants.FULL_BORDER_TABLE);
			table.setMargin(new MarginInfo(true, false, false, false));
			table.addTableListener(this.tableClickListener);
			table.setCurrentDataList(list);
			addComponent(table);

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
