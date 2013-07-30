package com.esofthead.mycollab.module.project.view.bug;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.file.ExportExcelStreamResource;
import com.esofthead.mycollab.module.file.FieldExportColumn;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class BugListPresenter extends AbstractPresenter<BugListView> implements
		ListPresenter<BugSearchCriteria> {

	private static final long serialVersionUID = 1L;

	private static final FieldExportColumn[] EXPORT_COLUMNS = new FieldExportColumn[] {
			new FieldExportColumn("bugkey", "Key"),
			new FieldExportColumn("summary", "Summary", 40),
			new FieldExportColumn("assignuserFullName", "Assigned User"),
			new FieldExportColumn("severity", "Severity"),
			new FieldExportColumn("priority", "Priority"),
			new FieldExportColumn("status", "Status"),
			new FieldExportColumn("milestoneName", "Milestone"),
			new FieldExportColumn("resolution", "Resolution"),
			new FieldExportColumn("duedate", "Due Date") };

	private boolean isSelectAll = false;

	@SuppressWarnings("serial")
	public BugListPresenter() {
		super(BugListView.class);

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
				new SearchHandler<BugSearchCriteria>() {
					@Override
					public void onSearch(BugSearchCriteria criteria) {
						doSearch(criteria);
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<SimpleBug>() {
					@Override
					public void onSelect(SimpleBug item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
		view.getExportBtn().addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				String title = "Bugs of Project "
						+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
								.getProject().getName() != null) ? CurrentProjectVariables
								.getProject().getName() : "");
				BugSearchCriteria searchBugSearchCriteria = new BugSearchCriteria();
				searchBugSearchCriteria.setProjectId(new NumberSearchField(
						SearchField.AND, CurrentProjectVariables.getProject()
								.getId()));
				Resource res = new StreamResource(
						new ExportExcelStreamResource<BugSearchCriteria>(title,
								EXPORT_COLUMNS, AppContext
										.getSpringBean(BugService.class),
								searchBugSearchCriteria), "bug_list.xls", view
								.getApplication());
				view.getWidget().getWindow().open(res, "_blank");
			}
		});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<SimpleBug> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (SimpleBug item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleBug> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (SimpleBug item : currentDataList) {
			if (item.isSelected()) {
				countItems++;
			}
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.BUGS)) {
			TrackerContainer trackerContainer = (TrackerContainer) container;
			trackerContainer.removeAllComponents();
			trackerContainer.addComponent(view.getWidget());

			BugSearchParameter param = (BugSearchParameter) data.getParams();

			view.setTitle(param.getScreenTitle());
			doSearch(param.getSearchCriteria());

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			breadcrumb.gotoBugList();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	public void doSearch(BugSearchCriteria searchCriteria) {
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}
}
