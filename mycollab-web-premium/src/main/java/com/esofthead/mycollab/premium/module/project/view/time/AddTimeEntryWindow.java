package com.esofthead.mycollab.premium.module.project.view.time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.esofthead.mycollab.common.ui.components.ProjectTooltipGenerator;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
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
import com.esofthead.mycollab.vaadin.ui.StyleCalendarFieldExp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class AddTimeEntryWindow extends Window {
	private static final long serialVersionUID = 1L;

	private Date selectedDate;
	private StyleCalendarFieldExp weekSelectionCalendar;
	private CheckBox isBillableCheckBox;
	private Table timeInputTable;
	private ProjectMemberSelectionBox projectMemberSelectionBox;
	private RichTextArea descArea;
	private TimeTrackingListView parentView;
	private ProjectGenericTask selectionTask;
	private HorizontalLayout taskLayout;

	public AddTimeEntryWindow(TimeTrackingListView view) {
		this.setModal(true);
		this.parentView = view;
		this.setCaption("Log time on this project");

		selectedDate = new GregorianCalendar().getTime();

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		GridLayout grid = new GridLayout(3, 2);
		grid.setMargin(new MarginInfo(false,false,true,false));
		grid.setSpacing(true);
		content.addComponent(grid);

		grid.addComponent(new Label("Who:"), 0, 0);
		grid.addComponent(new Label("Week:"), 1, 0);
		HorizontalLayout isBillable = new HorizontalLayout();
		isBillable.setSpacing(true);
		isBillable.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
		Label billableTitle = new Label("Is Billable:");
		isBillable.addComponent(billableTitle);

		

		projectMemberSelectionBox = new ProjectMemberSelectionBox();
		grid.addComponent(projectMemberSelectionBox, 0, 1);

		weekSelectionCalendar = new StyleCalendarFieldExp();
		weekSelectionCalendar.setWidth("150px");
		weekSelectionCalendar.setValue(selectedDate);
		weekSelectionCalendar.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				selectedDate = weekSelectionCalendar.getValue();
				weekSelectionCalendar.setPopupClose();
				updateTimeTableHeader();
			}
		});
		grid.addComponent(weekSelectionCalendar, 1, 1);

		isBillableCheckBox = new CheckBox();
		isBillable.addComponent(isBillableCheckBox);
		grid.addComponent(isBillable, 2, 1);

		timeInputTable = new Table();
		timeInputTable.setImmediate(true);
		timeInputTable.addContainerProperty("Monday", Double.class, 0);
		timeInputTable.addContainerProperty("Tuesday", Double.class, 0);
		timeInputTable.addContainerProperty("Wednesday", Double.class, 0);
		timeInputTable.addContainerProperty("Thursday", Double.class, 0);
		timeInputTable.addContainerProperty("Friday", Double.class, 0);
		timeInputTable.addContainerProperty("Saturday", Double.class, 0);
		timeInputTable.addContainerProperty("Sunday", Double.class, 0);

		timeInputTable.addItem(new Double[] { 0d, 0d, 0d, 0d, 0d, 0d, 0d },
				"timeEntry");
		timeInputTable.setEditable(true);
		updateTimeTableHeader();
		content.addComponent(timeInputTable);

		Label descriptionLbl = new Label("Description");
		content.addComponent(descriptionLbl);

		descArea = new RichTextArea();
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
				AddTimeEntryWindow.this.close();
			}
		});
		saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controlsLayout.addComponent(saveBtn);
		

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				AddTimeEntryWindow.this.close();
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
		
		final String taskName = selectionTask.getName();
		taskLayout.removeAllComponents();
		Label attachTaskBtn = new Label(StringUtils.trim(taskName, 80,true));
	
		attachTaskBtn.addStyleName("task-attached");
		attachTaskBtn.setWidth("120px");
		
		attachTaskBtn.setDescription(generateTooltip(selectionTask.getType(), selectionTask.getTypeId()));
		taskLayout.addComponent(attachTaskBtn);
		this.selectionTask.getTypeId();
		
		Button detachTaskBtn = new Button("Detach",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						createLinkTaskButton();
					}
				});
		detachTaskBtn.setStyleName(UIConstants.THEME_RED_LINK);
		taskLayout.addComponent(detachTaskBtn);
		
		
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
			html = ProjectTooltipGenerator.generateToolTipTaskList(
					taskList, siteURL, timeZone);
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
			html = ProjectTooltipGenerator.generateToolTipTask(task,
					siteURL, timeZone);
		} else if (ProjectTypeConstants.RISK.equals(type)) {
			RiskService service = ApplicationContextUtil
					.getSpringBean(RiskService.class);
			SimpleRisk risk = service.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipRisk(risk,
					siteURL, timeZone);
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
			SimpleComponent component = service
					.findById(typeid, sAccountId);
			html = ProjectTooltipGenerator.generateToolTipComponent(
					component, siteURL, timeZone);
		} else if (ProjectTypeConstants.STANDUP.equals(type)) {
			StandupReportService service = ApplicationContextUtil
					.getSpringBean(StandupReportService.class);
			SimpleStandupReport standup = service.findStandupReportById(
					typeid, sAccountId);
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
						ProjectGenericTaskSelectionWindow selectionTaskWindow = new ProjectGenericTaskSelectionWindow(
								AddTimeEntryWindow.this);
						AddTimeEntryWindow.this.getUI().addWindow(
								selectionTaskWindow);
					}
				});
		attachTaskBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

		taskLayout.addComponent(attachTaskBtn);
	}

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"MM/dd");

	private void updateTimeTableHeader() {
		Date monday = DateTimeUtils.getBounceDateofWeek(selectedDate)[0];
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(monday);

		timeInputTable.setColumnHeader("Monday",
				"Monday (" + simpleDateFormat.format(calendar.getTime()) + ")");

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Tuesday", "Tuesday ("
				+ simpleDateFormat.format(calendar.getTime()) + ")");

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Wednesday", "Wednesday ("
				+ simpleDateFormat.format(calendar.getTime()) + ")");

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Thursday", "Thursday ("
				+ simpleDateFormat.format(calendar.getTime()) + ")");

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Friday",
				"Friday (" + simpleDateFormat.format(calendar.getTime()) + ")");

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Saturday", "Saturday ("
				+ simpleDateFormat.format(calendar.getTime()) + ")");

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Sunday",
				"Sunday (" + simpleDateFormat.format(calendar.getTime()) + ")");

	}

	private void saveTimeLoggingItems() {
		SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox
				.getValue();
		if (user == null) {
			throw new UserInvalidInputException("You must select a member");
		}

		Date monday = DateTimeUtils.getBounceDateofWeek(selectedDate)[0];
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(monday);

		List<ItemTimeLogging> timeLoggins = new ArrayList<ItemTimeLogging>();

		ItemTimeLogging timeLogging = buildItemTimeLogging("Monday", calendar,
				user);
		if (timeLogging != null) {
			timeLoggins.add(buildItemTimeLogging("Monday", calendar, user));
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Tuesday", calendar, user);
		if (timeLogging != null) {
			timeLoggins.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Wednesday", calendar, user);
		if (timeLogging != null) {
			timeLoggins.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Thursday", calendar, user);
		if (timeLogging != null) {
			timeLoggins.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Friday", calendar, user);
		if (timeLogging != null) {
			timeLoggins.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Saturday", calendar, user);
		if (timeLogging != null) {
			timeLoggins.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Sunday", calendar, user);
		if (timeLogging != null) {
			timeLoggins.add(timeLogging);
		}

		ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil
				.getSpringBean(ItemTimeLoggingService.class);
		itemTimeLoggingService.batchSaveTimeLogging(timeLoggins,
				AppContext.getAccountId());
		parentView.refresh();
	}

	private ItemTimeLogging buildItemTimeLogging(String headerId,
			Calendar calendar, SimpleProjectMember logForMember) {
		Item timeEntries = (Item) timeInputTable.getItem("timeEntry");
		Property<?> itemProperty = timeEntries.getItemProperty(headerId);
		Double timeVal = (Double) itemProperty.getValue();
		if (timeVal == null || timeVal == 0) {
			return null;
		} else {
			ItemTimeLogging timeLogging = new ItemTimeLogging();
			timeLogging.setIsbillable(isBillableCheckBox.getValue());
			timeLogging.setLoguser(logForMember.getUsername());
			timeLogging.setCreateduser(AppContext.getUsername());
			timeLogging.setLogforday(calendar.getTime());
			timeLogging.setLogvalue(timeVal);
			timeLogging.setNote(descArea.getValue());
			timeLogging.setProjectid(CurrentProjectVariables.getProjectId());
			timeLogging.setSaccountid(AppContext.getAccountId());
			timeLogging.setCreatedtime(new GregorianCalendar().getTime());
			timeLogging.setLastupdatedtime(new GregorianCalendar().getTime());
			if (selectionTask != null) {
				timeLogging.setType(selectionTask.getType());
				timeLogging.setTypeid(selectionTask.getTypeId());
			}
			return timeLogging;
		}
	}
}
