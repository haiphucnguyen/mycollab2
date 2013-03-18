package com.esofthead.mycollab.module.crm.view.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.module.file.ExportStreamResource;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class EventListPresenter extends AbstractPresenter<EventListView>
		implements ListPresenter<EventSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private static final String[] EXPORT_VISIBLE_COLUMNS = new String[] {
			"status", "eventType", "subject", "startDate", "endDate" };
	private static final String[] EXPORT_DISPLAY_NAMES = new String[] {
			"Status", "Type", "Subject", "Start Date", "End Date" };

	private EventService eventService;

	private EventSearchCriteria searchCriteria;

	private boolean isSelectAll = false;

	public EventListPresenter() {
		super(EventListView.class);
		eventService = AppContext.getSpringBean(EventService.class);

		view.getPagedBeanTable().addPagableHandler(new PagableHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void move(int newPageNumber) {
				pageChange();
			}

			@Override
			public void displayItemChange(int numOfItems) {
				pageChange();
			}

			private void pageChange() {
				if (isSelectAll) {
					selectAllItemsInCurrentPage();
				}

				checkWhetherEnableTableActionControl();
			}
		});

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<EventSearchCriteria>() {

					@Override
					public void onSearch(EventSearchCriteria criteria) {
						doSearch(criteria);
					}
				});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {

					@Override
					public void onSelectCurrentPage() {
						isSelectAll = false;
						selectAllItemsInCurrentPage();

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						Collection<SimpleEvent> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleEvent item : currentDataList) {
							item.setSelected(false);
							CheckBox checkBox = (CheckBox) item.getExtraData();
							checkBox.setValue(false);
						}

						checkWhetherEnableTableActionControl();

					}

					@Override
					public void onSelectAll() {
						isSelectAll = true;
						selectAllItemsInCurrentPage();

						checkWhetherEnableTableActionControl();

					}
				});

		view.getPopupActionHandlers().addPopupActionHandler(
				new PopupActionHandler() {

					@Override
					public void onSelect(String id, String caption) {
						if ("delete".equals(id)) {
							ConfirmDialog.show(view.getWindow(),
									"Please Confirm:",
									"Are you sure to delete selected items: ",
									"Yes", "No", new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
												deleteSelectedItems();
											}
										}
									});
						} else if ("mail".equals(id)) {
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
						} else if ("export".equals(id)) {
							Resource res = null;

							if (isSelectAll) {
								res = new StreamResource(
										new ExportStreamResource.AllItems<EventSearchCriteria>(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES,
												AppContext
														.getSpringBean(EventService.class),
												searchCriteria), "export.csv",
										view.getApplication());
							} else {
								List tableData = view.getPagedBeanTable()
										.getCurrentDataList();
								res = new StreamResource(
										new ExportStreamResource.ListData(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES, tableData),
										"export.csv", view.getApplication());
							}

							view.getWidget().getWindow().open(res, "_blank");
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<SimpleEvent>() {

					@Override
					public void onSelect(SimpleEvent item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<SimpleEvent> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (SimpleEvent item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleEvent> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (SimpleEvent item : currentDataList) {
			if (item.isSelected()) {
				countItems++;
			}
		}
		if (countItems > 0) {
			view.enableActionControls(countItems);
		} else {
			view.disableActionControls();
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_MEETING)
				|| AppContext.canRead(RolePermissionCollections.CRM_TASK)
				|| AppContext.canRead(RolePermissionCollections.CRM_CALL)) {
			doSearch((EventSearchCriteria) data.getParams());
			AppContext.addFragment("crm/activity/todo", "Activity To Do");
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	public void doSearch(EventSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	private static final String CALL = "Call";
	private static final String MEETING = "Meeting";
	private static final String TASK = "Task";

	private void deleteSelectedItems() {
		Collection<SimpleEvent> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		List<Integer> keyListCall = new ArrayList<Integer>();
		List<Integer> keyListMeeting = new ArrayList<Integer>();
		List<Integer> keyListTask = new ArrayList<Integer>();
		if (!isSelectAll) {
			for (SimpleEvent item : currentDataList) {
				if (item.isSelected()) {
					if (item.getEventType().equals(CALL)) {
						keyListCall.add(item.getId());
					} else if (item.getEventType().equals(MEETING)) {
						keyListMeeting.add(item.getId());
					} else if (item.getEventType().equals(TASK)) {
						keyListTask.add(item.getId());
					}
				}
			}
		} else {
			for (SimpleEvent item : currentDataList) {
				if (item.getEventType().equals(CALL)) {
					keyListCall.add(item.getId());
				} else if (item.getEventType().equals(MEETING)) {
					keyListMeeting.add(item.getId());
				} else if (item.getEventType().equals(TASK)) {
					keyListTask.add(item.getId());
				}
			}
		}

		if (keyListCall.size() > 0) {
			CallService callService = AppContext
					.getSpringBean(CallService.class);
			callService
					.removeWithSession(keyListCall, AppContext.getUsername());
		}

		if (keyListMeeting.size() > 0) {
			MeetingService meetingService = AppContext
					.getSpringBean(MeetingService.class);
			meetingService.removeWithSession(keyListMeeting,
					AppContext.getUsername());
		}

		if (keyListTask.size() > 0) {
			TaskService taskService = AppContext
					.getSpringBean(TaskService.class);
			taskService
					.removeWithSession(keyListTask, AppContext.getUsername());
		}
		doSearch(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

}
