package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.common.i18n.DayI18nEnum;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.TimeTrackingEvent;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.DoubleField;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.WeeklyCalendarFieldExp;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.*;
import java.util.Calendar;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class AddTimeEntryWindow extends Window implements AssignmentSelectableComp {
    private static final long serialVersionUID = 1L;

    private Date selectedDate;
    private WeeklyCalendarFieldExp weekSelectionCalendar;
    private CheckBox isBillableCheckBox;
    private CheckBox isOvertimeCheckBox;
    private Table timeInputTable;
    private ProjectMemberSelectionBox projectMemberSelectionBox;
    private RichTextArea descArea;
    private ProjectGenericTask selectionTask;
    private MHorizontalLayout taskLayout;

    private ItemTimeLoggingService itemTimeLoggingService;

    public AddTimeEntryWindow() {
        itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
        this.setModal(true);
        this.setResizable(false);
        this.setCaption(AppContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));

        selectedDate = new GregorianCalendar().getTime();

        MVerticalLayout content = new MVerticalLayout();

        GridLayout grid = new GridLayout(3, 2);
        grid.setMargin(new MarginInfo(false, false, true, false));
        grid.setSpacing(true);
        content.addComponent(grid);

        grid.addComponent(new ELabel(AppContext.getMessage(TimeTrackingI18nEnum.FORM_WHO)).withStyleName(UIConstants.META_COLOR), 0, 0);
        grid.addComponent(new ELabel(AppContext.getMessage(TimeTrackingI18nEnum.FORM_WEEK)).withStyleName(UIConstants.META_COLOR), 1, 0);

        projectMemberSelectionBox = new ProjectMemberSelectionBox(false);
        grid.addComponent(projectMemberSelectionBox, 0, 1);

        weekSelectionCalendar = new WeeklyCalendarFieldExp();
        weekSelectionCalendar.setWidth("200px");

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

        MHorizontalLayout attrContainer = new MHorizontalLayout();
        isBillableCheckBox = new CheckBox();
        isOvertimeCheckBox = new CheckBox();
        attrContainer.with(new ELabel(AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE)).withStyleName
                        (UIConstants.META_COLOR, UIConstants.TEXT_ALIGN_RIGHT).withWidth("100px"),
                isBillableCheckBox, new ELabel(AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_OVERTIME))
                        .withStyleName(UIConstants.META_COLOR, UIConstants.TEXT_ALIGN_RIGHT).withWidth("100px"), isOvertimeCheckBox)
                .alignAll(Alignment.MIDDLE_LEFT);
        grid.addComponent(attrContainer, 2, 1);

        timeInputTable = new Table();
        timeInputTable.setImmediate(true);
        timeInputTable.addContainerProperty(AppContext.getMessage(DayI18nEnum.OPT_MONDAY), DoubleField.class, 0);
        timeInputTable.addContainerProperty(AppContext.getMessage(DayI18nEnum.OPT_TUESDAY), DoubleField.class, 0);
        timeInputTable.addContainerProperty(AppContext.getMessage(DayI18nEnum.OPT_WEDNESDAY), DoubleField.class, 0);
        timeInputTable.addContainerProperty(AppContext.getMessage(DayI18nEnum.OPT_THURSDAY), DoubleField.class, 0);
        timeInputTable.addContainerProperty(AppContext.getMessage(DayI18nEnum.OPT_FRIDAY), DoubleField.class, 0);
        timeInputTable.addContainerProperty(AppContext.getMessage(DayI18nEnum.OPT_SATURDAY), DoubleField.class, 0);
        timeInputTable.addContainerProperty(AppContext.getMessage(DayI18nEnum.OPT_SUNDAY), DoubleField.class, 0);

        timeInputTable.addItem(new DoubleField[]{new DoubleField(), new DoubleField(), new DoubleField(),
                new DoubleField(), new DoubleField(), new DoubleField(), new DoubleField()}, "timeEntry");
        timeInputTable.setEditable(true);
        timeInputTable.setHeight("80px");
        updateTimeTableHeader();
        content.addComponent(timeInputTable);

        content.addComponent(new ELabel(AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION)).withStyleName(UIConstants.META_COLOR));

        descArea = new RichTextArea();
        descArea.setWidth("100%");
        content.addComponent(descArea);
        MHorizontalLayout footer = new MHorizontalLayout().withFullWidth();
        taskLayout = new MHorizontalLayout();
        taskLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        createLinkTaskButton();
        footer.addComponent(taskLayout);

        MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(UIConstants.BUTTON_OPTION);

        MButton saveBtn = new MButton(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), clickEvent -> {
            saveTimeLoggingItems();
            close();
        }).withStyleName(UIConstants.BUTTON_ACTION);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        MHorizontalLayout controlsLayout = new MHorizontalLayout(cancelBtn, saveBtn);
        footer.with(controlsLayout).withAlign(controlsLayout, Alignment.TOP_RIGHT);

        content.addComponent(footer);
        this.setContent(content);
        this.center();
    }

    @Override
    public void updateLinkTask(ProjectGenericTask task) {
        selectionTask = task;
        if (selectionTask != null) {
            String taskName = this.selectionTask.getName();
            taskLayout.removeAllComponents();

            MButton detachTaskBtn = new MButton(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_DETACH_TASK), clickEvent -> {
                createLinkTaskButton();
                updateLinkTask(null);
            }).withStyleName(UIConstants.BUTTON_DANGER);
            taskLayout.addComponent(detachTaskBtn);

            Label attachTaskBtn = new Label(StringUtils.trim(taskName, 60, true));
            attachTaskBtn.addStyleName("task-attached");
            attachTaskBtn.setWidth("500px");

            attachTaskBtn.setDescription(new ProjectGenericTaskTooltipGenerator(this.selectionTask.getType(),
                    this.selectionTask.getTypeId()).getContent());
            taskLayout.addComponent(attachTaskBtn);
            this.selectionTask.getTypeId();
        }

    }

    private void createLinkTaskButton() {
        taskLayout.removeAllComponents();
        MButton attachTaskBtn = new MButton(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LINK_TASK), clickEvent -> {
            ProjectGenericTaskSelectionWindow selectionTaskWindow = new ProjectGenericTaskSelectionWindow(AddTimeEntryWindow.this);
            getUI().addWindow(selectionTaskWindow);
        }).withStyleName(UIConstants.BUTTON_ACTION);

        taskLayout.addComponent(attachTaskBtn);
    }

    private void updateTimeTableHeader() {
        Date monday = DateTimeUtils.getBounceDatesOfWeek(selectedDate)[0];
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(monday);

        timeInputTable.setColumnHeader(AppContext.getMessage(DayI18nEnum.OPT_MONDAY), AppContext.getMessage(TimeTrackingI18nEnum.MONDAY_FIELD,
                DateTimeUtils.formatDate(calendar.getTime(), AppContext.getShortDateFormat())));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeInputTable.setColumnHeader(AppContext.getMessage(DayI18nEnum.OPT_TUESDAY), AppContext.getMessage(TimeTrackingI18nEnum.TUESDAY_FIELD,
                DateTimeUtils.formatDate(calendar.getTime(), AppContext.getShortDateFormat())));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeInputTable.setColumnHeader(AppContext.getMessage(DayI18nEnum.OPT_WEDNESDAY), AppContext.getMessage(TimeTrackingI18nEnum.WEDNESDAY_FIELD,
                DateTimeUtils.formatDate(calendar.getTime(), AppContext.getShortDateFormat())));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeInputTable.setColumnHeader(AppContext.getMessage(DayI18nEnum.OPT_THURSDAY), AppContext.getMessage(TimeTrackingI18nEnum.THURSDAY_FIELD,
                DateTimeUtils.formatDate(calendar.getTime(), AppContext.getShortDateFormat())));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeInputTable.setColumnHeader(AppContext.getMessage(DayI18nEnum.OPT_FRIDAY), AppContext.getMessage(TimeTrackingI18nEnum.FRIDAY_FIELD,
                DateTimeUtils.formatDate(calendar.getTime(), AppContext.getShortDateFormat())));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeInputTable.setColumnHeader(AppContext.getMessage(DayI18nEnum.OPT_SATURDAY), AppContext.getMessage(TimeTrackingI18nEnum.SATURDAY_FIELD,
                DateTimeUtils.formatDate(calendar.getTime(), AppContext.getShortDateFormat())));

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeInputTable.setColumnHeader(AppContext.getMessage(DayI18nEnum.OPT_SUNDAY), AppContext.getMessage(TimeTrackingI18nEnum.SUNDAY_FIELD,
                DateTimeUtils.formatDate(calendar.getTime(), AppContext.getShortDateFormat())));

    }

    private void saveTimeLoggingItems() {
        SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox.getValue();
        if (user == null) {
            throw new UserInvalidInputException("You must select a member");
        }

        Date monday = DateTimeUtils.getBounceDatesOfWeek(selectedDate)[0];
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(monday);

        List<ItemTimeLogging> timeLoggings = new ArrayList<>();

        ItemTimeLogging timeLogging = buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_MONDAY), calendar, user);
        if (timeLogging != null) {
            timeLoggings.add(buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_MONDAY), calendar, user));
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeLogging = buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_TUESDAY), calendar, user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeLogging = buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_WEDNESDAY), calendar, user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeLogging = buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_THURSDAY), calendar, user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeLogging = buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_FRIDAY), calendar, user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeLogging = buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_SATURDAY), calendar, user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        timeLogging = buildItemTimeLogging(AppContext.getMessage(DayI18nEnum.OPT_SUNDAY), calendar, user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        itemTimeLoggingService.batchSaveTimeLogging(timeLoggings, AppContext.getAccountId());

        updateProjectTimeLogging();
        EventBusFactory.getInstance().post(new TimeTrackingEvent.TimeLoggingEntryChange(AddTimeEntryWindow.this));
    }

    private void updateProjectTimeLogging() {
        ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.setIsBillable(new BooleanSearchField(true));
        Double totalBillableHours = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setIsBillable(new BooleanSearchField(false));
        Double totalNonBillableHours = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
        CurrentProjectVariables.getProject().setTotalBillableHours(totalBillableHours);
        CurrentProjectVariables.getProject().setTotalNonBillableHours(totalNonBillableHours);
    }

    private ItemTimeLogging buildItemTimeLogging(String headerId, Calendar calendar, SimpleProjectMember logForMember) {
        Item timeEntries = timeInputTable.getItem("timeEntry");
        Property<?> itemProperty = timeEntries.getItemProperty(headerId);
        DoubleField timeField = (DoubleField) itemProperty.getValue();
        Double timeVal = timeField.getValue();
        if (timeVal == null || timeVal == 0) {
            return null;
        } else {
            ItemTimeLogging timeLogging = new ItemTimeLogging();
            timeLogging.setIsbillable(isBillableCheckBox.getValue());
            timeLogging.setIsovertime(isOvertimeCheckBox.getValue());
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
