package com.esofthead.mycollab.module.project.view.time;

import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@ViewComponent
public class TimeTrackingListViewImpl extends AbstractView implements
		TimeTrackingListView {
	private static final long serialVersionUID = 1L;
	
	private PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> tableItem;
	private ItemTimeLoggingSearchPanel itemTimeLoggingPanel;
	private ItemTimeLoggingSearchCriteria itemTimeLogginSearchCriteria;
	
	private Label lbTimeRange;

	public TimeTrackingListViewImpl() {
		
		this.setSpacing(true);
		this.setMargin(false, true, true, true);
		
		Label titleLbl = new Label("Time tracking");
		titleLbl.setStyleName("h2");
		this.addComponent(titleLbl);
		
		itemTimeLoggingPanel = new ItemTimeLoggingSearchPanel();
		itemTimeLoggingPanel.addSearchHandler(new SearchHandler<ItemTimeLoggingSearchCriteria>() {
			@Override
			public void onSearch(ItemTimeLoggingSearchCriteria criteria) {
				setSearchCriteria(criteria);
			}
		});
		this.addComponent(itemTimeLoggingPanel);
		
		lbTimeRange = new Label("", Label.CONTENT_XHTML);
		this.addComponent(lbTimeRange);
		
		initUI();
	}
	
	private void setTimeRange() {
		RangeDateSearchField rangeField = itemTimeLogginSearchCriteria.getRangeDate();
		
		String fromDate = AppContext.formatDate(rangeField.getFrom());
		String toDate = AppContext.formatDate(rangeField.getTo());
		
		lbTimeRange.setValue("From: <strong>" + fromDate + "</strong> To: <strong>" + toDate + "</strong>");
	}
	
	private void initUI() {
		tableItem = new PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
				AppContext.getSpringBean(ItemTimeLoggingService.class),
				SimpleItemTimeLogging.class,
				new String[] { "logUserFullName", "createdtime",
						"logvalue"}, new String[] { "User",
						"Created Time", "Time" });

		tableItem.addGeneratedColumn("logUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							Table source, final Object itemId,
							Object columnId) {
						final SimpleItemTimeLogging monitorItem = tableItem
								.getBeanByIndex(itemId);

						return new ProjectUserLink(
								monitorItem.getLoguser(), monitorItem
										.getLogUserFullName(), true, true);

					}
				});

		tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleItemTimeLogging monitorItem = tableItem
						.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(AppContext.formatDateTime(monitorItem
						.getCreatedtime()));
				return l;
			}
		});

		tableItem.addGeneratedColumn("logvalue", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleItemTimeLogging itemTimeLogging = tableItem
						.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(itemTimeLogging.getLogvalue());
				return l;
			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("logUserFullName", 1.0f);
		tableItem.setColumnWidth("createdtime",
				UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("logvalue",
				UIConstants.TABLE_S_LABEL_WIDTH);

		this.addComponent(tableItem);
	}

	@Override
	public void setSearchCriteria(ItemTimeLoggingSearchCriteria searchCriteria) {
		this.itemTimeLogginSearchCriteria = searchCriteria;
		tableItem.setSearchCriteria(searchCriteria);
		setTimeRange();
	}

}
