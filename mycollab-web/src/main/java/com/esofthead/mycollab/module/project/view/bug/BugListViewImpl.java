package com.esofthead.mycollab.module.project.view.bug;

import java.util.Arrays;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
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
	private Button exportBtn;

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
		customizeViewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		buttonControls.addComponent(customizeViewBtn);

		this.exportBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.TABLE_EXPORT_BUTTON));
		this.exportBtn.setIcon(MyCollabResource
				.newResource("icons/16/export_excel.png"));
		this.exportBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		this.exportBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		buttonControls.addComponent(this.exportBtn);
		return layoutWrapper;
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
		if (this.bugSearchPanel != null) {
			this.bugSearchPanel.setBugTitle(title);
		}
	}
}
