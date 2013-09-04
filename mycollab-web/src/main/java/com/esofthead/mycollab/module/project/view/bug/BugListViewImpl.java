package com.esofthead.mycollab.module.project.view.bug;

import java.util.Arrays;

import org.vaadin.hene.splitbutton.SplitButtonExt;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.file.resource.ExportItemsStreamResource;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class BugListViewImpl extends AbstractView implements BugListView {

	private static final long serialVersionUID = 1L;
	private final BugSearchPanel bugSearchPanel;
	private BugTableDisplay tableItem;
	private final VerticalLayout bugListLayout;
	private SplitButtonExt exportButtonControl;

	public BugListViewImpl() {
		this.setMargin(false, true, true, true);

		this.bugSearchPanel = new BugSearchPanel();
		this.addComponent(this.bugSearchPanel);

		this.bugListLayout = new VerticalLayout();
		this.addComponent(this.bugListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {

		this.tableItem = new BugTableDisplay(BugListView.VIEW_DEF_ID,
				BugTableFieldDef.action, Arrays.asList(
						BugTableFieldDef.summary, BugTableFieldDef.assignUser,
						BugTableFieldDef.severity, BugTableFieldDef.resolution,
						BugTableFieldDef.duedate));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleBug bug = (SimpleBug) event.getData();
						if ("summary".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(BugListViewImpl.this,
											bug.getId()));
						}
					}
				});

		this.bugListLayout.addComponent(this.constructTableActionControls());
		this.bugListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<BugSearchCriteria> getSearchHandlers() {
		return this.bugSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		final Label lbEmpty = new Label("");
		layout.addComponent(lbEmpty);
		layout.setExpandRatio(lbEmpty, 1.0f);

		HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.setSpacing(true);
		layout.addComponent(buttonControls);

		Button customizeViewBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getWindow().addWindow(
						new BugListCustomizeWindow(BugListView.VIEW_DEF_ID,
								tableItem));

			}
		});
		customizeViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/customize.png"));
		customizeViewBtn.setDescription("Layout Options");
		customizeViewBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
		buttonControls.addComponent(customizeViewBtn);

		Button exportBtn = new Button("Export", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				exportButtonControl.setPopupVisible(false);

			}
		});
		exportButtonControl = new SplitButtonExt(exportBtn);
		exportButtonControl.setStyleName(UIConstants.THEME_GRAY_LINK);
		exportButtonControl.addStyleName(UIConstants.SPLIT_BUTTON);
		exportButtonControl.setIcon(MyCollabResource
				.newResource("icons/16/export.png"));

		VerticalLayout popupButtonsControl = new VerticalLayout();
		popupButtonsControl.setWidth("150px");
		exportButtonControl.addComponent(popupButtonsControl);

		Button exportPdfBtn = new Button("Pdf", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String title = "Bugs of Project "
						+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
								.getProject().getName() != null) ? CurrentProjectVariables
								.getProject().getName() : "");
				BugSearchCriteria searchCriteria = new BugSearchCriteria();
				searchCriteria.setProjectId(new NumberSearchField(
						SearchField.AND, CurrentProjectVariables.getProject()
								.getId()));

				StreamResource res = new StreamResource(
						new ExportItemsStreamResource.AllItems<BugSearchCriteria, SimpleBug>(
								title, tableItem.getDisplayColumns(),
								ReportExportType.PDF, AppContext
										.getSpringBean(BugService.class),
								searchCriteria, SimpleBug.class), "export.pdf",
						BugListViewImpl.this.getApplication());
				BugListViewImpl.this.getWindow().open(res, "_blank");
				exportButtonControl.setPopupVisible(false);

			}
		});
		exportPdfBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/pdf.png"));
		exportPdfBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportPdfBtn);

		Button exportExcelBtn = new Button("Excel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String title = "Bugs of Project "
						+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
								.getProject().getName() != null) ? CurrentProjectVariables
								.getProject().getName() : "");
				BugSearchCriteria searchCriteria = new BugSearchCriteria();
				searchCriteria.setProjectId(new NumberSearchField(
						SearchField.AND, CurrentProjectVariables.getProject()
								.getId()));

				StreamResource res = new StreamResource(
						new ExportItemsStreamResource.AllItems<BugSearchCriteria, SimpleBug>(
								title, tableItem.getDisplayColumns(),
								ReportExportType.EXCEL, AppContext
										.getSpringBean(BugService.class),
								searchCriteria, SimpleBug.class),
						"export.xlsx", BugListViewImpl.this.getApplication());
				BugListViewImpl.this.getWindow().open(res, "_blank");
				exportButtonControl.setPopupVisible(false);

			}
		});
		exportExcelBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/excel.png"));
		exportExcelBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportExcelBtn);

		Button exportCsvBtn = new Button("CSV", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String title = "Bugs of Project "
						+ ((CurrentProjectVariables.getProject() != null && CurrentProjectVariables
								.getProject().getName() != null) ? CurrentProjectVariables
								.getProject().getName() : "");
				BugSearchCriteria searchCriteria = new BugSearchCriteria();
				searchCriteria.setProjectId(new NumberSearchField(
						SearchField.AND, CurrentProjectVariables.getProject()
								.getId()));

				StreamResource res = new StreamResource(
						new ExportItemsStreamResource.AllItems<BugSearchCriteria, SimpleBug>(
								title, tableItem.getDisplayColumns(),
								ReportExportType.CSV,
								AppContext.getSpringBean(BugService.class),
								searchCriteria, SimpleBug.class), "export.csv",
						BugListViewImpl.this.getApplication());
				BugListViewImpl.this.getWindow().open(res, "_blank");
				exportButtonControl.setPopupVisible(false);

			}
		});

		exportCsvBtn.setIcon(MyCollabResource
				.newResource("icons/16/filetypes/csv.png"));
		exportCsvBtn.setStyleName("link");
		popupButtonsControl.addComponent(exportCsvBtn);

		buttonControls.addComponent(exportButtonControl);
		return layoutWrapper;
	}

	@Override
	public HasSelectableItemHandlers<SimpleBug> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public IPagedBeanTable<BugSearchCriteria, SimpleBug> getPagedBeanTable() {
		return this.tableItem;
	}

	@Override
	public void setTitle(final String title) {
		if (this.bugSearchPanel != null) {
			this.bugSearchPanel.setBugTitle(title);
		}
	}
}
