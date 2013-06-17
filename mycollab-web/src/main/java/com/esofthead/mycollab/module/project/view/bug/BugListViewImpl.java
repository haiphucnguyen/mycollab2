package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.shell.view.ScreenSize;
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
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class BugListViewImpl extends AbstractView implements BugListView {

	private static final long serialVersionUID = 1L;
	private final BugSearchPanel bugSearchPanel;
	private BugTableDisplay tableItem;
	private final VerticalLayout bugListLayout;
	private final Label titleLbl;
	private Button exportBtn;

	public BugListViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		this.titleLbl = new Label();
		this.titleLbl.setStyleName("h2");
		this.addComponent(this.titleLbl);

		this.bugSearchPanel = new BugSearchPanel();
		this.addComponent(this.bugSearchPanel);

		this.bugListLayout = new VerticalLayout();
		this.bugListLayout.setSpacing(true);
		this.addComponent(this.bugListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {

		if (ScreenSize.hasSupport1024Pixels()) {
			this.tableItem = new BugTableDisplay(
					new String[] { "id", "summary", "assignuserFullName",
							"severity", "resolution" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_SUMMARY_HEADER),
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_ASSIGN_USER_HEADER),
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_SEVERITY_HEADER),
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_RESOLUTION_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			this.tableItem = new BugTableDisplay(
					new String[] { "id", "summary", "assignuserFullName",
							"severity", "resolution", "duedate" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_SUMMARY_HEADER),
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_ASSIGN_USER_HEADER),
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_SEVERITY_HEADER),
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_RESOLUTION_HEADER),
							LocalizationHelper
									.getMessage(BugI18nEnum.TABLE_DUE_DATE_HEADER) });
		}

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
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");

		final Label lbEmpty = new Label("");
		layout.addComponent(lbEmpty);
		layout.setExpandRatio(lbEmpty, 1.0f);

		this.exportBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.TABLE_EXPORT_BUTTON));
		this.exportBtn.setIcon(MyCollabResource
				.newResource("icons/16/export_excel.png"));
		this.exportBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		this.exportBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		layout.addComponent(this.exportBtn);
		return layout;
	}

	@Override
	public Button getExportBtn() {
		return this.exportBtn;
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
		this.titleLbl.setValue(title);
	}
}
