package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.StyleCalendarFieldExp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class AddTimeEntryWindow extends Window implements
		AssignmentSelectableComp {
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
		this.setResizable(false);
		this.parentView = view;
		this.setCaption(AppContext
				.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));

		selectedDate = new GregorianCalendar().getTime();

		MVerticalLayout content = new MVerticalLayout().withSpacing(true).withMargin(true);

		GridLayout grid = new GridLayout(3, 2);
		grid.setMargin(new MarginInfo(false, false, true, false));
		grid.setSpacing(true);
		content.addComponent(grid);

		grid.addComponent(
				new Label(AppContext.getMessage(TimeTrackingI18nEnum.FORM_WHO)),
				0, 0);
		grid.addComponent(
				new Label(AppContext.getMessage(TimeTrackingI18nEnum.FORM_WEEK)),
				1, 0);
		HorizontalLayout isBillable = new HorizontalLayout();
		isBillable.setSpacing(true);
		isBillable.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
		Label billableTitle = new Label(
				AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE));
		isBillable.addComponent(billableTitle);

		projectMemberSelectionBox = new ProjectMemberSelectionBox(false);
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

		Label descriptionLbl = new Label(
				AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION));
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

		Button cancelBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						AddTimeEntryWindow.this.close();
					}
				});
		cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		controlsLayout.addComponent(cancelBtn);

		Button saveBtn = new Button(
				AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						saveTimeLoggingItems();
						AddTimeEntryWindow.this.close();
					}
				});
		saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controlsLayout.addComponent(saveBtn);

		footer.addComponent(controlsLayout);
		footer.setSizeFull();
		footer.setComponentAlignment(controlsLayout, Alignment.TOP_RIGHT);
		content.addComponent(footer);
		this.setContent(content);
		this.center();
	}

	@Override
	public void updateLinkTask(ProjectGenericTask task) {
		this.selectionTask = task;
		if (this.selectionTask != null) {
			final String taskName = this.selectionTask.getName();
			taskLayout.removeAllComponents();

			Button detachTaskBtn = new Button(
					AppContext
							.getMessage(TimeTrackingI18nEnum.BUTTON_DETACH_TASK),
					new Button.ClickListener() {

						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							createLinkTaskButton();
							updateLinkTask(null);
						}
					});
			detachTaskBtn.setStyleName(UIConstants.THEME_RED_LINK);
			taskLayout.addComponent(detachTaskBtn);

			Label attachTaskBtn = new Label(
					StringUtils.trim(taskName, 60, true));

			attachTaskBtn.addStyleName("task-attached");
			attachTaskBtn.setWidth("500px");

			attachTaskBtn
					.setDescription(new ProjectGenericTaskTooltipGenerator(
							this.selectionTask.getType(), this.selectionTask
									.getTypeId()).getContent());
			taskLayout.addComponent(attachTaskBtn);
			this.selectionTask.getTypeId();
		}

	}

	private void createLinkTaskButton() {
		taskLayout.removeAllComponents();
		Button attachTaskBtn = new Button(
				AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LINK_TASK),
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

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			AppContext.getUserDayMonthFormat());

	private void updateTimeTableHeader() {
		Date monday = DateTimeUtils.getBounceDateofWeek(selectedDate)[0];
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(monday);

		timeInputTable.setColumnHeader("Monday", AppContext.getMessage(
				TimeTrackingI18nEnum.MONDAY_FIELD,
				simpleDateFormat.format(calendar.getTime())));

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Tuesday", AppContext.getMessage(
				TimeTrackingI18nEnum.TUESDAY_FIELD,
				simpleDateFormat.format(calendar.getTime())));

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Wednesday", AppContext.getMessage(
				TimeTrackingI18nEnum.WEDNESDAY_FIELD,
				simpleDateFormat.format(calendar.getTime())));

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Thursday", AppContext.getMessage(
				TimeTrackingI18nEnum.THURSDAY_FIELD,
				simpleDateFormat.format(calendar.getTime())));

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Friday", AppContext.getMessage(
				TimeTrackingI18nEnum.FRIDAY_FIELD,
				simpleDateFormat.format(calendar.getTime())));

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Saturday", AppContext.getMessage(
				TimeTrackingI18nEnum.SATURDAY_FIELD,
				simpleDateFormat.format(calendar.getTime())));

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeInputTable.setColumnHeader("Sunday", AppContext.getMessage(
				TimeTrackingI18nEnum.SUNDAY_FIELD,
				simpleDateFormat.format(calendar.getTime())));

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

		List<ItemTimeLogging> timeLoggings = new ArrayList<>();

		ItemTimeLogging timeLogging = buildItemTimeLogging("Monday", calendar,
				user);
		if (timeLogging != null) {
			timeLoggings.add(buildItemTimeLogging("Monday", calendar, user));
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Tuesday", calendar, user);
		if (timeLogging != null) {
			timeLoggings.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Wednesday", calendar, user);
		if (timeLogging != null) {
			timeLoggings.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Thursday", calendar, user);
		if (timeLogging != null) {
			timeLoggings.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Friday", calendar, user);
		if (timeLogging != null) {
			timeLoggings.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Saturday", calendar, user);
		if (timeLogging != null) {
			timeLoggings.add(timeLogging);
		}

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		timeLogging = buildItemTimeLogging("Sunday", calendar, user);
		if (timeLogging != null) {
			timeLoggings.add(timeLogging);
		}

		ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil
				.getSpringBean(ItemTimeLoggingService.class);
		itemTimeLoggingService.batchSaveTimeLogging(timeLoggings,
				AppContext.getAccountId());
		parentView.refresh();
	}

	private ItemTimeLogging buildItemTimeLogging(String headerId,
			Calendar calendar, SimpleProjectMember logForMember) {
		Item timeEntries = timeInputTable.getItem("timeEntry");
		Property<?> itemProperty = timeEntries.getItemProperty(headerId);
		Double timeVal = (Double) itemProperty.getValue();
		if (timeVal == null || timeVal == 0) {
			return null;
		} else {
			ItemTimeLogging timeLogging = new ItemTimeLogging();
			timeLogging.setIsbillable(isBillableCheckBox.getValue());
			timeLogging.setLoguser(logForMember.getUsername());
			timeLogging.setCreateduser(AppContext.getUsername());
			timeLogging.setLogforday(DateTimeUtils.trimHMSOfDate(calendar
					.getTime()));
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
