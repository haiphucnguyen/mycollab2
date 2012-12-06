package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public class EventListViewImpl extends AbstractView implements EventListView {
	private static final long serialVersionUID = 1L;

	private final EventSearchPanel eventSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable2<EventService, EventSearchCriteria, SimpleEvent> tableItem;

	private final VerticalLayout eventListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	public EventListViewImpl() {
		this.setSpacing(true);

		eventSearchPanel = new EventSearchPanel();
		this.addComponent(eventSearchPanel);

		eventListLayout = new VerticalLayout();
		eventListLayout.setSpacing(true);
		this.addComponent(eventListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<EventService, EventSearchCriteria, SimpleEvent>(
				AppContext.getSpringBean(EventService.class),
				SimpleEvent.class, new String[] { "selected", "status",
						"eventType", "subject", "startDate", "endDate" },
				new String[] { "", "Status", "Type", "Subject", "Start Date",
						"End Date" });

		tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						SimpleEvent simpleEvent = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(simpleEvent);

					}
				});

				@SuppressWarnings("unchecked")
				SimpleEvent simpleEvent = ((PagedBeanTable2<EventService, EventSearchCriteria, SimpleEvent>) source)
						.getBeanByIndex(itemId);
				simpleEvent.setExtraData(cb);
				return cb;
			}
		});
		
		tableItem.addGeneratedColumn("startDate",
				new ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							Object itemId, Object columnId) {
						@SuppressWarnings("unchecked")
						final SimpleEvent event = ((PagedBeanTable2<EventService, EventSearchCriteria, SimpleEvent>) source)
								.getBeanByIndex(itemId);
						Label l = new Label();
						l.setValue(AppContext.formatDateTime(event
								.getStartDate()));
						return l;
					}
				});
		
		tableItem.addGeneratedColumn("endDate",
				new ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							Object itemId, Object columnId) {
						@SuppressWarnings("unchecked")
						final SimpleEvent event = ((PagedBeanTable2<EventService, EventSearchCriteria, SimpleEvent>) source)
								.getBeanByIndex(itemId);
						Label l = new Label();
						l.setValue(AppContext.formatDateTime(event
								.getEndDate()));
						return l;
					}
				});

		tableItem.addGeneratedColumn("subject", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleEvent simpleEvent = ((PagedBeanTable2<EventService, EventSearchCriteria, SimpleEvent>) source)
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(simpleEvent.getSubject(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								if ("Task".equals(simpleEvent.getEventType())) {
									EventBus.getInstance().fireEvent(
											new ActivityEvent.TaskRead(this,
													simpleEvent.getId()));
								} else if ("Meeting".equals(simpleEvent
										.getEventType())) {
									EventBus.getInstance().fireEvent(
											new ActivityEvent.MeetingRead(this,
													simpleEvent.getId()));
								} else if ("Call".equals(simpleEvent
										.getEventType())) {
									EventBus.getInstance().fireEvent(
											new ActivityEvent.CallRead(this,
													simpleEvent.getId()));
								}
							}
						});
				return b;

			}
		});

		tableItem.setColumnExpandRatio("subject", 1);
		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("status", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("eventType", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("startDate", UIConstants.TABLE_DATE_WIDTH);
		tableItem.setColumnWidth("endDate", UIConstants.TABLE_DATE_WIDTH);

		tableItem.setWidth("100%");

		eventListLayout.addComponent(constructTableActionControls());
		eventListLayout.addComponent(tableItem);
		eventListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public HasSearchHandlers<EventSearchCriteria> getSearchHandlers() {
		return eventSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setEnabled(true);
		selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setEnabled(false);
		selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleEvent> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<EventService, EventSearchCriteria, SimpleEvent> getPagedBeanTable() {
		return tableItem;
	}
}
