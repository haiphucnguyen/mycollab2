package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Date;

import com.esofthead.mycollab.common.ui.components.ProjectTooltipGenerator;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class TimeTrackingReadViewWindow extends Window {
	private static final long serialVersionUID = 1L;

	private Date selectedDate;
	private CheckBox isBillableCheckBox;
	private ProjectMemberSelectionBox projectMemberSelectionBox;
	private RichTextArea descArea;
	private TimeTrackingListView parentView;
	private ProjectGenericTask selectionTask;
	private HorizontalLayout taskLayout;
	private DateField dateField;
	private TextField timeField;
	private SimpleItemTimeLogging item;

	public TimeTrackingReadViewWindow(TimeTrackingListView view,
			SimpleItemTimeLogging item) {
		this.item = item;
		this.setModal(true);
		this.parentView = view;
		this.setCaption("Log time on this project");

		selectedDate = this.item.getLogforday();
		dateField = new DateField("Select date", selectedDate);
		timeField = new TextField(this.item.getLogvalue().toString());

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		GridLayout grid = new GridLayout(3, 2);
		grid.setMargin(new MarginInfo(false, false, true, false));
		grid.setSpacing(true);
		content.addComponent(grid);

		HorizontalLayout isBillable = new HorizontalLayout();
		isBillable.setSpacing(true);
		isBillable.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
		Label billableTitle = new Label("Is Billable:");
		isBillable.addComponent(billableTitle);

		projectMemberSelectionBox = new ProjectMemberSelectionBox();
		projectMemberSelectionBox.setValue(this.item.getLoguser());
		grid.addComponent(projectMemberSelectionBox, 0, 1);

		grid.addComponent(dateField, 1, 1);
		grid.addComponent(timeField, 2, 0);

		isBillableCheckBox = new CheckBox();
		isBillable.addComponent(isBillableCheckBox);
		grid.addComponent(isBillable, 2, 1);

		Label descriptionLbl = new Label("Description");
		content.addComponent(descriptionLbl);

		descArea = new RichTextArea();
		descArea.setValue(this.item.getNote());
		descArea.setWidth("100%");
		content.addComponent(descArea);
		HorizontalLayout footer = new HorizontalLayout();
		taskLayout = new HorizontalLayout();
		taskLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		taskLayout.setSpacing(true);
		createLinkTaskButton();

		footer.addComponent(taskLayout);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setSpacing(true);
		Button saveBtn = new Button("Log time", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				saveTimeLoggingItems();
				TimeTrackingReadViewWindow.this.close();
			}
		});
		saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controlsLayout.addComponent(saveBtn);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				TimeTrackingReadViewWindow.this.close();
			}
		});
		cancelBtn.addStyleName(UIConstants.THEME_BLANK_LINK);
		controlsLayout.addComponent(cancelBtn);

		footer.addComponent(controlsLayout);
		footer.setSizeFull();
		footer.setComponentAlignment(controlsLayout, Alignment.TOP_RIGHT);
		content.addComponent(footer);
		this.setContent(content);
		this.center();
	}

	void setSelectionTask(ProjectGenericTask selectionTask) {
		this.selectionTask = selectionTask;
		if (this.selectionTask != null) {
			final String taskName = this.selectionTask.getName();
			taskLayout.removeAllComponents();

			Button detachTaskBtn = new Button("Detach",
					new Button.ClickListener() {

						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							createLinkTaskButton();
							setSelectionTask(null);
						}
					});
			detachTaskBtn.setStyleName(UIConstants.THEME_RED_LINK);
			taskLayout.addComponent(detachTaskBtn);

			Label attachTaskBtn = new Label(
					StringUtils.trim(taskName, 60, true));

			attachTaskBtn.addStyleName("task-attached");
			attachTaskBtn.setWidth("500px");

			attachTaskBtn.setDescription(generateTooltip(
					this.selectionTask.getType(),
					this.selectionTask.getTypeId()));
			taskLayout.addComponent(attachTaskBtn);
			this.selectionTask.getTypeId();
		}

	}

	private String generateTooltip(String type, int typeid) {

		String html = "";
		int sAccountId = AppContext.getAccountId();
		String timeZone = AppContext.getSession().getTimezone();
		String siteURL = AppContext.getSiteUrl();

		if (ProjectTypeConstants.TASK_LIST.equals(type)) {
			ProjectTaskListService service = ApplicationContextUtil
					.getSpringBean(ProjectTaskListService.class);
			SimpleTaskList taskList = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipTaskList(taskList,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.BUG.equals(type)) {
			BugService service = ApplicationContextUtil
					.getSpringBean(BugService.class);
			SimpleBug bug = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipBug(bug, siteURL,
					timeZone);
		} else if (ProjectTypeConstants.TASK.equals(type)) {
			ProjectTaskService service = ApplicationContextUtil
					.getSpringBean(ProjectTaskService.class);
			SimpleTask task = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipTask(task, siteURL,
					timeZone);
		} else if (ProjectTypeConstants.RISK.equals(type)) {
			RiskService service = ApplicationContextUtil
					.getSpringBean(RiskService.class);
			SimpleRisk risk = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipRisk(risk, siteURL,
					timeZone);
		} else if (ProjectTypeConstants.PROBLEM.equals(type)) {
			ProblemService service = ApplicationContextUtil
					.getSpringBean(ProblemService.class);
			SimpleProblem problem = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipProblem(problem,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.BUG_VERSION.equals(type)) {
			VersionService service = ApplicationContextUtil
					.getSpringBean(VersionService.class);
			SimpleVersion version = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipVersion(version,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.BUG_COMPONENT.equals(type)) {
			ComponentService service = ApplicationContextUtil
					.getSpringBean(ComponentService.class);
			SimpleComponent component = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipComponent(component,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.STANDUP.equals(type)) {
			StandupReportService service = ApplicationContextUtil
					.getSpringBean(StandupReportService.class);
			SimpleStandupReport standup = service.findStandupReportById(typeid,
					sAccountId);
			html = ProjectTooltipGenerator.generateToolTipStandUp(standup,
					siteURL, timeZone);
		}
		return html;
	}

	private void createLinkTaskButton() {
		taskLayout.removeAllComponents();
		Button attachTaskBtn = new Button("Link with task",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						/*
						 * ProjectGenericTaskSelectionWindow selectionTaskWindow
						 * = new ProjectGenericTaskSelectionWindow(
						 * TimeTrackingReadViewWindow.this);
						 * TimeTrackingReadViewWindow.this.getUI().addWindow(
						 * selectionTaskWindow);
						 */
					}
				});
		attachTaskBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

		taskLayout.addComponent(attachTaskBtn);
	}

	private void saveTimeLoggingItems() {
		SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox
				.getValue();
		item.setLoguser(user.getUsername());
		item.setLogforday(dateField.getValue());
		item.setLogvalue(Double.parseDouble(timeField.getValue()));
		item.setIsbillable(isBillableCheckBox.getValue());
		item.setNote(descArea.getValue());
		if (selectionTask != null) {
			item.setType(selectionTask.getType());
			item.setTypeid(selectionTask.getTypeId());
		}

		parentView.refresh();
	}
}
