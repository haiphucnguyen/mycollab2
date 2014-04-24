package com.esofthead.mycollab.module.project.view.task;

import java.util.Arrays;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
@ViewComponent
public class TaskSearchViewImpl extends AbstractPageView implements
		TaskSearchView {

	private static final long serialVersionUID = 1L;
	private TaskSearchPanel taskSearchPanel;
	private TaskSearchTableDisplay tableItem;
	private final VerticalLayout taskListLayout;
	private Label headerText;

	public void setSearchInputValue(String value) {
		taskSearchPanel.setTextField(value);
	}

	public TaskSearchViewImpl() {

		this.taskSearchPanel = new TaskSearchPanel();

		this.taskListLayout = new VerticalLayout();
		this.setMargin(true);
		this.generateDisplayTable();

		final HorizontalLayout header = new HorizontalLayout();
		header.setSpacing(true);
		header.setSpacing(true);
		header.setMargin(new MarginInfo(true, false, true, false));
		header.setStyleName(UIConstants.HEADER_VIEW);
		header.setWidth("100%");
		Image titleIcon = new Image(null,
				MyCollabResource.newResource("icons/24/project/task.png"));

		headerText = new Label();
		headerText.setSizeUndefined();
		headerText.setStyleName(UIConstants.HEADER_TEXT);

		Button backtoTaskListBtn = new Button("Back to task dashboard",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance()
								.fireEvent(
										new TaskListEvent.GotoTaskListScreen(
												this, null));

					}
				});
		backtoTaskListBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		backtoTaskListBtn.setIcon(MyCollabResource
				.newResource("icons/16/back.png"));

		UiUtils.addComponent(header, titleIcon, Alignment.TOP_LEFT);
		UiUtils.addComponent(header, headerText, Alignment.MIDDLE_LEFT);
		UiUtils.addComponent(header, backtoTaskListBtn, Alignment.MIDDLE_RIGHT);
		header.setExpandRatio(headerText, 1.0f);

		addComponent(header);
		addComponent(this.taskSearchPanel);

		addComponent(this.taskListLayout);
	}

	private void generateDisplayTable() {

		this.tableItem = new TaskSearchTableDisplay(TaskTableFieldDef.id,
				Arrays.asList(TaskTableFieldDef.taskname,
						TaskTableFieldDef.startdate, TaskTableFieldDef.duedate,
						TaskTableFieldDef.assignee,
						TaskTableFieldDef.percentagecomplete));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleTask task = (SimpleTask) event.getData();
						if ("taskname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new TaskEvent.GotoRead(
											TaskSearchViewImpl.this, task
													.getId()));
						}
					}
				});

		this.taskListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<TaskSearchCriteria> getSearchHandlers() {
		return this.taskSearchPanel;
	}

	@Override
	public IPagedBeanTable<TaskSearchCriteria, SimpleTask> getPagedBeanTable() {
		return this.tableItem;
	}

	@Override
	public void setTitle(String title) {
		headerText.setValue(title);

	}

	@Override
	public void moveToAdvanceSearch() {
		taskSearchPanel.getAdvanceSearch();
	}

	@Override
	public void moveToBasicSearch() {
		taskSearchPanel.getBasicSearch();
	}

}
