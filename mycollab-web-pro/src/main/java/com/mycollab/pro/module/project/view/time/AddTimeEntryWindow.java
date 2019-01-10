package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.TimeTrackingEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.RangeDateField;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.RichTextArea;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
// TODO
public class AddTimeEntryWindow extends MWindow implements AssignmentSelectableComp {
    private static final long serialVersionUID = 1L;

    private LocalDate selectedDate;
    private RangeDateField weekSelectionCalendar;
    private CheckBox isBillableCheckBox;
    private CheckBox isOvertimeCheckBox;
    //    private Table timeInputTable;
    private ProjectMemberSelectionBox projectMemberSelectionBox;
    private RichTextArea descArea;
    private ProjectTicket selectedTicket;
    private MHorizontalLayout ticketLayout;

    private ItemTimeLoggingService itemTimeLoggingService;

    public AddTimeEntryWindow() {
        super(UserUIContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));
        this.withModal(true).withResizable(false).withCenter();

        itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
        selectedDate = LocalDate.now();

        MVerticalLayout content = new MVerticalLayout();
        GridLayout grid = new GridLayout(3, 2);
        grid.setMargin(new MarginInfo(false, false, true, false));
        grid.setSpacing(true);
        content.addComponent(grid);

        grid.addComponent(new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_WHO)).withStyleName(WebThemes.META_COLOR), 0, 0);
        grid.addComponent(new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_WEEK)).withStyleName(WebThemes.META_COLOR), 1, 0);

        projectMemberSelectionBox = new ProjectMemberSelectionBox(false);
        grid.addComponent(projectMemberSelectionBox, 0, 1);

        weekSelectionCalendar = new RangeDateField();
//        weekSelectionCalendar.setWidth("200px");
//
//        weekSelectionCalendar.setValue(selectedDate);
//        weekSelectionCalendar.addValueChangeListener(valueChangeEvent -> {
//            selectedDate = weekSelectionCalendar.getValue();
//            weekSelectionCalendar.setPopupClose();
//            updateTimeTableHeader();
//        });
//        grid.addComponent(weekSelectionCalendar, 1, 1);

        MHorizontalLayout attrContainer = new MHorizontalLayout();
        isBillableCheckBox = new CheckBox();
        isOvertimeCheckBox = new CheckBox();
        attrContainer.with(new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE)).withStyleName
                        (WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("100px"),
                isBillableCheckBox, new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_OVERTIME))
                        .withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("100px"), isOvertimeCheckBox)
                .alignAll(Alignment.MIDDLE_LEFT);
        grid.addComponent(attrContainer, 2, 1);

//        timeInputTable = new Table();
//        timeInputTable.setImmediate(true);
//        timeInputTable.addContainerProperty(UserUIContext.getMessage(DayI18nEnum.OPT_MONDAY), DoubleField.class, 0);
//        timeInputTable.addContainerProperty(UserUIContext.getMessage(DayI18nEnum.OPT_TUESDAY), DoubleField.class, 0);
//        timeInputTable.addContainerProperty(UserUIContext.getMessage(DayI18nEnum.OPT_WEDNESDAY), DoubleField.class, 0);
//        timeInputTable.addContainerProperty(UserUIContext.getMessage(DayI18nEnum.OPT_THURSDAY), DoubleField.class, 0);
//        timeInputTable.addContainerProperty(UserUIContext.getMessage(DayI18nEnum.OPT_FRIDAY), DoubleField.class, 0);
//        timeInputTable.addContainerProperty(UserUIContext.getMessage(DayI18nEnum.OPT_SATURDAY), DoubleField.class, 0);
//        timeInputTable.addContainerProperty(UserUIContext.getMessage(DayI18nEnum.OPT_SUNDAY), DoubleField.class, 0);
//
//        timeInputTable.addItem(new DoubleField[]{new DoubleField(), new DoubleField(), new DoubleField(),
//                new DoubleField(), new DoubleField(), new DoubleField(), new DoubleField()}, "timeEntry");
//        timeInputTable.setEditable(true);
//        timeInputTable.setHeight("80px");
        updateTimeTableHeader();
//        content.addComponent(timeInputTable);

        content.addComponent(new ELabel(UserUIContext.getMessage(GenericI18Enum.FORM_DESCRIPTION)).withStyleName(WebThemes.META_COLOR));

        descArea = new RichTextArea();
        descArea.setWidth("100%");
        content.addComponent(descArea);
        MHorizontalLayout footer = new MHorizontalLayout().withFullWidth();
        ticketLayout = new MHorizontalLayout();
        ticketLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        createTicketLinkButton();
        footer.addComponent(ticketLayout);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);

        MButton saveBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), clickEvent -> {
            saveTimeLoggingItems();
            close();
        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.CLIPBOARD)
                .withClickShortcut(ShortcutAction.KeyCode.ENTER);

        MHorizontalLayout controlsLayout = new MHorizontalLayout(cancelBtn, saveBtn);
        footer.with(controlsLayout).withAlign(controlsLayout, Alignment.TOP_RIGHT);

        content.addComponent(footer);
        this.setContent(content);
    }

    @Override
    public void updateTicketLink(ProjectTicket ticket) {
        selectedTicket = ticket;
        if (selectedTicket != null) {
            ticketLayout.removeAllComponents();

            MButton unlinkTicketBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.ACTION_UNLINK_TICKET), clickEvent -> {
                createTicketLinkButton();
                updateTicketLink(null);
            }).withStyleName(WebThemes.BUTTON_DANGER).withIcon(VaadinIcons.UNLINK);
            ticketLayout.addComponent(unlinkTicketBtn);

            ELabel linkTicketBtn = new ELabel(StringUtils.trim(selectedTicket.getName(), 60, true))
                    .withDescription(new ProjectGenericItemTooltipGenerator(selectedTicket.getType(),
                            selectedTicket.getTypeId()).getContent());
            ticketLayout.addComponent(linkTicketBtn);
        }

    }

    private void createTicketLinkButton() {
        ticketLayout.removeAllComponents();
        MButton attachTicketBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.ACTION_LINK_TICKET), clickEvent -> {
            ProjectTicketSelectionWindow selectionTaskWindow = new ProjectTicketSelectionWindow(AddTimeEntryWindow.this);
            getUI().addWindow(selectionTaskWindow);
        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.LINK);

        ticketLayout.addComponent(attachTicketBtn);
    }

    private void updateTimeTableHeader() {
        LocalDate monday = DateTimeUtils.getBounceDatesOfWeek(selectedDate)[0];

//        timeInputTable.setColumnHeader(UserUIContext.getMessage(DayI18nEnum.OPT_MONDAY), UserUIContext.getMessage(TimeTrackingI18nEnum.MONDAY_FIELD,
//                DateTimeUtils.formatDate(calendar.getTime(), AppUI.getShortDateFormat(), UserUIContext.getUserLocale())));
//
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        timeInputTable.setColumnHeader(UserUIContext.getMessage(DayI18nEnum.OPT_TUESDAY), UserUIContext.getMessage(TimeTrackingI18nEnum.TUESDAY_FIELD,
//                DateTimeUtils.formatDate(calendar.getTime(), AppUI.getShortDateFormat(), UserUIContext.getUserLocale())));
//
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        timeInputTable.setColumnHeader(UserUIContext.getMessage(DayI18nEnum.OPT_WEDNESDAY), UserUIContext.getMessage(TimeTrackingI18nEnum.WEDNESDAY_FIELD,
//                DateTimeUtils.formatDate(calendar.getTime(), AppUI.getShortDateFormat(), UserUIContext.getUserLocale())));
//
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        timeInputTable.setColumnHeader(UserUIContext.getMessage(DayI18nEnum.OPT_THURSDAY), UserUIContext.getMessage(TimeTrackingI18nEnum.THURSDAY_FIELD,
//                DateTimeUtils.formatDate(calendar.getTime(), AppUI.getShortDateFormat(), UserUIContext.getUserLocale())));
//
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        timeInputTable.setColumnHeader(UserUIContext.getMessage(DayI18nEnum.OPT_FRIDAY), UserUIContext.getMessage(TimeTrackingI18nEnum.FRIDAY_FIELD,
//                DateTimeUtils.formatDate(calendar.getTime(), AppUI.getShortDateFormat(), UserUIContext.getUserLocale())));
//
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        timeInputTable.setColumnHeader(UserUIContext.getMessage(DayI18nEnum.OPT_SATURDAY), UserUIContext.getMessage(TimeTrackingI18nEnum.SATURDAY_FIELD,
//                DateTimeUtils.formatDate(calendar.getTime(), AppUI.getShortDateFormat(), UserUIContext.getUserLocale())));
//
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        timeInputTable.setColumnHeader(UserUIContext.getMessage(DayI18nEnum.OPT_SUNDAY), UserUIContext.getMessage(TimeTrackingI18nEnum.SUNDAY_FIELD,
//                DateTimeUtils.formatDate(calendar.getTime(), AppUI.getShortDateFormat(), UserUIContext.getUserLocale())));

    }

    private void saveTimeLoggingItems() {
        SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox.getValue();
        if (user == null) {
            throw new UserInvalidInputException(UserUIContext.getMessage(TimeTrackingI18nEnum.ERROR_MEMBER_NOT_NULL));
        }

        LocalDate monday = DateTimeUtils.getBounceDatesOfWeek(selectedDate)[0];

        List<ItemTimeLogging> timeLoggings = new ArrayList<>();

        ItemTimeLogging timeLogging = buildItemTimeLogging(UserUIContext.getMessage(DayI18nEnum.OPT_MONDAY), monday, user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        timeLogging = buildItemTimeLogging(UserUIContext.getMessage(DayI18nEnum.OPT_TUESDAY), monday.plusDays(1), user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        timeLogging = buildItemTimeLogging(UserUIContext.getMessage(DayI18nEnum.OPT_WEDNESDAY), monday.plusDays(2), user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        timeLogging = buildItemTimeLogging(UserUIContext.getMessage(DayI18nEnum.OPT_THURSDAY), monday.plusDays(3), user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        timeLogging = buildItemTimeLogging(UserUIContext.getMessage(DayI18nEnum.OPT_FRIDAY), monday.plusDays(4), user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        timeLogging = buildItemTimeLogging(UserUIContext.getMessage(DayI18nEnum.OPT_SATURDAY), monday.plusDays(5), user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        timeLogging = buildItemTimeLogging(UserUIContext.getMessage(DayI18nEnum.OPT_SUNDAY), monday.plusDays(6), user);
        if (timeLogging != null) {
            timeLoggings.add(timeLogging);
        }

        itemTimeLoggingService.batchSaveTimeLogging(timeLoggings, AppUI.getAccountId());

        updateProjectTimeLogging();
        EventBusFactory.getInstance().post(new TimeTrackingEvent.TimeLoggingEntryChange(AddTimeEntryWindow.this));
    }

    private void updateProjectTimeLogging() {
        ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.setBillable(new BooleanSearchField(true));
        Double totalBillableHours = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setBillable(new BooleanSearchField(false));
        Double totalNonBillableHours = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
        CurrentProjectVariables.getProject().setTotalBillableHours(totalBillableHours);
        CurrentProjectVariables.getProject().setTotalNonBillableHours(totalNonBillableHours);
    }

    private ItemTimeLogging buildItemTimeLogging(String headerId, LocalDate calendar, SimpleProjectMember logForMember) {
        return null;
//        Item timeEntries = timeInputTable.getItem("timeEntry");
//        Property<?> itemProperty = timeEntries.getItemProperty(headerId);
//        DoubleField timeField = (DoubleField) itemProperty.getValue();
//        Double timeVal = timeField.getValue();
//        if (timeVal == null || timeVal == 0) {
//            return null;
//        } else {
//            ItemTimeLogging timeLogging = new ItemTimeLogging();
//            timeLogging.setIsbillable(isBillableCheckBox.getValue());
//            timeLogging.setIsovertime(isOvertimeCheckBox.getValue());
//            timeLogging.setLoguser(logForMember.getUsername());
//            timeLogging.setCreateduser(UserUIContext.getUsername());
//            timeLogging.setLogforday(calendar.getTime());
//            timeLogging.setLogvalue(timeVal);
//            timeLogging.setNote(descArea.getValue());
//            timeLogging.setProjectid(CurrentProjectVariables.getProjectId());
//            timeLogging.setSaccountid(AppUI.getAccountId());
//            timeLogging.setCreatedtime(new GregorianCalendar().getTime());
//            timeLogging.setLastupdatedtime(new GregorianCalendar().getTime());
//
//            if (selectedTicket != null) {
//                timeLogging.setType(selectedTicket.getType());
//                timeLogging.setTypeid(selectedTicket.getTypeId());
//            }
//            return timeLogging;
//        }
    }
}
