package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugEvent;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class BugListViewImpl extends AbstractView implements BugListView {

	private static final long serialVersionUID = 1L;
	private final BugSearchPanel problemSearchPanel;
	private BugTableDisplay tableItem;
	private final VerticalLayout problemListLayout;
	private Label titleLbl;
	private Button exportBtn;

	public BugListViewImpl() {
		this.setSpacing(true);
		this.setMargin(false, true, true, true);

		titleLbl = new Label();
		titleLbl.setStyleName("h2");
		this.addComponent(titleLbl);

		problemSearchPanel = new BugSearchPanel();
		this.addComponent(problemSearchPanel);

		problemListLayout = new VerticalLayout();
		problemListLayout.setSpacing(true);
		this.addComponent(problemListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {

		if (ScreenSize.hasSupport1024Pixels()) {
			tableItem = new BugTableDisplay(
					new String[] { "id", "bugkey", "summary",
							"assignuserFullName", "severity", "resolution" },
					new String[] { "", "#", "Summary", "Assigned User",
							"Severity", "Resolution" });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			tableItem = new BugTableDisplay(new String[] { "id", "bugkey",
					"summary", "assignuserFullName", "severity", "resolution",
					"duedate" }, new String[] { "", "#", "Summary",
					"Assigned User", "Severity", "Resolution", "Due Date" });
		}

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleBug bug = (SimpleBug) event.getData();
						if ("summary".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(BugListViewImpl.this,
											bug.getId()));
						}
					}
				});

		problemListLayout.addComponent(constructTableActionControls());
		problemListLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<BugSearchCriteria> getSearchHandlers() {
		return problemSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");

		Label lbEmpty = new Label("");
		layout.addComponent(lbEmpty);
		layout.setExpandRatio(lbEmpty, 1.0f);

		exportBtn = new Button("Export");
		exportBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		exportBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS));
		layout.addComponent(exportBtn);
		return layout;
	}
	
	@Override
	public Button getExportBtn() {
		return exportBtn;
	}

	@Override
	public HasSelectableItemHandlers<SimpleBug> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<BugSearchCriteria, SimpleBug> getPagedBeanTable() {
		return tableItem;
	}

	@Override
	public void setTitle(String title) {
		titleLbl.setValue(title);
	}
}
