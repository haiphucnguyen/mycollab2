package com.mycollab.pro.module.project.view.reports;

import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.*;
import com.mycollab.db.query.ConstantValueInjector;
import com.mycollab.db.query.DateParam;
import com.mycollab.db.query.LazyValueInjector;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.fielddef.TimeTableFieldDef;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.parameters.BugScreenData;
import com.mycollab.module.project.view.parameters.ProjectScreenData;
import com.mycollab.module.project.view.parameters.TaskScreenData;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.pro.module.project.ui.components.*;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.PageActionChain;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.StringValueComboBox;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.table.IPagedTable;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@ViewComponent
public class TimeTrackingViewImpl extends AbstractVerticalPageView implements TimeTrackingView {
    private static final long serialVersionUID = 1L;

    private List<SimpleProject> projects;

    private UserInvolvedProjectsListSelect projectField;
    private UserInvolvedProjectsMemberListSelect userField;
    private DateField fromDateField, toDateField;
    private ComboBox groupField, orderField;
    private ItemTimeLoggingSearchCriteria searchCriteria;

    private Label totalHoursLoggingLabel;

    private ItemTimeLoggingService itemTimeLoggingService;

    private MVerticalLayout timeTrackingWrapper;

    public TimeTrackingViewImpl() {
        this.setMargin(new MarginInfo(false, false, true, false));
    }

    private AbstractTimeTrackingDisplayComp buildTimeTrackingComp() {
        String groupBy = (String) groupField.getValue();

        if (groupBy.equals(UserUIContext.getMessage(ProjectI18nEnum.SINGLE))) {
            return new TimeTrackingProjectOrderComponent(getVisibleFields(), tableClickListener);
        } else if (groupBy.equals(UserUIContext.getMessage(DayI18nEnum.OPT_DATE))) {
            return new TimeTrackingDateOrderComponent(getVisibleFields(), tableClickListener);
        } else if (groupBy.equals(UserUIContext.getMessage(UserI18nEnum.SINGLE))) {
            return new TimeTrackingUserOrderComponent(getVisibleFields(), tableClickListener);
        } else {
            throw new MyCollabException("Do not support view type: " + groupBy);
        }
    }

    private List<TableViewField> getVisibleFields() {
        String groupBy = (String) groupField.getValue();

        if (groupBy.equals(UserUIContext.getMessage(ProjectI18nEnum.SINGLE))) {
            return Arrays.asList(TimeTableFieldDef.summary,
                    TimeTableFieldDef.logForDate, TimeTableFieldDef.logUser,
                    TimeTableFieldDef.logValue, TimeTableFieldDef.billable, TimeTableFieldDef.overtime);
        } else if (groupBy.equals(UserUIContext.getMessage(DayI18nEnum.OPT_DATE))) {
            return Arrays.asList(TimeTableFieldDef.summary,
                    TimeTableFieldDef.logUser, TimeTableFieldDef.project,
                    TimeTableFieldDef.logValue, TimeTableFieldDef.billable, TimeTableFieldDef.overtime);
        } else if (groupBy.equals(UserUIContext.getMessage(UserI18nEnum.SINGLE))) {
            return Arrays.asList(TimeTableFieldDef.summary,
                    TimeTableFieldDef.logForDate, TimeTableFieldDef.project,
                    TimeTableFieldDef.logValue, TimeTableFieldDef.billable, TimeTableFieldDef.overtime);
        } else {
            throw new MyCollabException("Do not support view type: " + groupBy);
        }
    }

    @Override
    public void display() {
        removeAllComponents();
        projects = AppContextUtil.getSpringBean(ProjectService.class).getProjectsUserInvolved(UserUIContext.getUsername(),
                AppUI.getAccountId());
        if (CollectionUtils.isNotEmpty(projects)) {
            itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);

            Label titleLbl = ELabel.h2(ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME).getHtml() + " " +
                    UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_TIMESHEET));

            MHorizontalLayout headerWrapper = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true,
                    false)).withFullWidth();

            MButton printBtn = new MButton("", clickEvent -> UI.getCurrent().addWindow(new
                    TimesheetCustomizeReportOutputWindow(new LazyValueInjector() {
                @Override
                protected Object doEval() {
                    return searchCriteria;
                }
            }))).withIcon(VaadinIcons.PRINT).withStyleName(WebThemes.BUTTON_OPTION)
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_EXPORT));

            ELabel groupLbl = new ELabel(UserUIContext.getMessage(GenericI18Enum.OPT_GROUP)).withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px");
            groupField = new StringValueComboBox(false, UserUIContext.getMessage(ProjectI18nEnum.SINGLE), UserUIContext
                    .getMessage(DayI18nEnum.OPT_DATE), UserUIContext.getMessage(UserI18nEnum.SINGLE));
            groupField.addValueChangeListener(event -> searchTimeReporting());

            ELabel sortLbl = new ELabel(UserUIContext.getMessage(GenericI18Enum.ACTION_SORT)).withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px");
            orderField = new ItemOrderComboBox();
            orderField.addValueChangeListener(event -> searchTimeReporting());

            headerWrapper.with(titleLbl, new MHorizontalLayout(groupLbl, groupField, sortLbl, orderField, printBtn).alignAll(Alignment.MIDDLE_LEFT))
                    .expand(titleLbl).alignAll(Alignment.MIDDLE_LEFT);

            this.addComponent(headerWrapper);

            ResponsiveLayout searchLayout = new ResponsiveLayout();
            ResponsiveRow row = searchLayout.addRow();

            LocalDate now = LocalDate.now();

            ELabel fromLbl = new ELabel(UserUIContext.getMessage(DayI18nEnum.OPT_FROM)).withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px");
            fromDateField = new DateField("", now.withDayOfMonth(1));

            ELabel toLbl = new ELabel(UserUIContext.getMessage(DayI18nEnum.OPT_TO)).withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px");
            toDateField = new DateField("", now.withDayOfMonth(now.lengthOfMonth()));

            row.addColumn().withDisplayRules(12, 12, 6, 4)
                    .withComponent(new MVerticalLayout(new MHorizontalLayout(fromLbl, fromDateField), new MHorizontalLayout(toLbl, toDateField)));

            ELabel projectLbl = new ELabel(UserUIContext.getMessage(ProjectI18nEnum.SINGLE)).withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px");
            projectField = new UserInvolvedProjectsListSelect(projects);
            projectField.setWidth(WebThemes.FORM_CONTROL_WIDTH);
            row.addColumn().withDisplayRules(12, 12, 6, 4).withComponent(new MHorizontalLayout(projectLbl, projectField));

            ELabel userLbl = new ELabel(UserUIContext.getMessage(UserI18nEnum.SINGLE)).withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px");
            userField = new UserInvolvedProjectsMemberListSelect(getProjectIds());
            userField.setWidth(WebThemes.FORM_CONTROL_WIDTH);
            row.addColumn().withDisplayRules(12, 12, 6, 4).withComponent(new MHorizontalLayout(userLbl, userField));

            MButton queryBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> searchTimeReporting())
                    .withStyleName(WebThemes.BUTTON_ACTION);

            MVerticalLayout searchPanel = new MVerticalLayout(searchLayout, queryBtn).withStyleName(WebThemes.BOX).withAlign(queryBtn, Alignment.MIDDLE_CENTER);

            totalHoursLoggingLabel = ELabel.h2("Total Hours Logging: 0 Hrs");

            timeTrackingWrapper = new MVerticalLayout().withFullWidth().withMargin(false);

            this.with(searchPanel, new MCssLayout(totalHoursLoggingLabel).withFullWidth(), timeTrackingWrapper).expand(timeTrackingWrapper);
            searchTimeReporting();
        } else {
            this.with(new MCssLayout(new Label(UserUIContext.getMessage(TimeTrackingI18nEnum.ERROR_NOT_INVOLVED_ANY_PROJECT))).withFullWidth());
        }
    }

    private void searchTimeReporting() {
        searchCriteria = new ItemTimeLoggingSearchCriteria();

        Order order = (Order) orderField.getValue();
        String sortDirection;
        if (Order.ASCENDING == order) {
            sortDirection = SearchCriteria.ASC;
        } else {
            sortDirection = SearchCriteria.DESC;
        }

        if (UserUIContext.getMessage(DayI18nEnum.OPT_DATE).equals(groupField.getValue())) {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("logForDay", sortDirection)));
        } else if (UserUIContext.getMessage(UserI18nEnum.SINGLE).equals(groupField.getValue())) {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("loguser", sortDirection)));
        } else if (UserUIContext.getMessage(ProjectI18nEnum.SINGLE).equals(groupField.getValue())) {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("projectName", sortDirection)));
        }

        final LocalDate fromDate = fromDateField.getValue();
        final LocalDate toDate = toDateField.getValue();
        searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                ConstantValueInjector.valueOf(Date.class, new LocalDate[]{fromDate, toDate})));

        Collection<String> selectedUsers = userField.getSelectedUsers();
        if (CollectionUtils.isNotEmpty(selectedUsers)) {
            searchCriteria.setLogUsers(new SetSearchField<>(selectedUsers));
        } else {
            searchCriteria.setLogUsers(new SetSearchField<>(userField.getAllUsersInInvolvedProjects()));
        }

        Collection<Integer> selectedProjectsKey = projectField.getSelectedProjectsKey();
        if (CollectionUtils.isNotEmpty(selectedProjectsKey)) {
            searchCriteria.setProjectIds(new SetSearchField<>(selectedProjectsKey));
        } else {
            searchCriteria.setProjectIds(new SetSearchField<>(getProjectIds()));
        }

        searchCriteria.setBillable(new BooleanSearchField(true));
        Double billableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setBillable(new BooleanSearchField(false));
        Double nonBillableHours = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setBillable(null);
        Double totalHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        totalHoursLoggingLabel.setValue(UserUIContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
                fromDate, toDate, totalHour, billableHour, nonBillableHours));

        timeTrackingWrapper.removeAllComponents();

        final AbstractTimeTrackingDisplayComp timeDisplayComp = buildTimeTrackingComp();

        timeTrackingWrapper.addComponent(timeDisplayComp);
        AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
                int totalCount = itemTimeLoggingService.getTotalCount(searchCriteria);
                int pages = totalCount / 20;
                for (int page = 0; page < pages + 1; page++) {
                    List<SimpleItemTimeLogging> itemTimeLoggings = (List<SimpleItemTimeLogging>) itemTimeLoggingService.findPageableListByCriteria(new
                            BasicSearchRequest<>(searchCriteria, page + 1, 20));
                    for (SimpleItemTimeLogging item : itemTimeLoggings) {
                        timeDisplayComp.insertItem(item);
                    }
                }
                timeDisplayComp.flush();
            }
        });
    }

    private IPagedTable.TableClickListener tableClickListener = new IPagedTable.TableClickListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void itemClick(final IPagedTable.TableClickEvent event) {
            SimpleItemTimeLogging itemLogging = (SimpleItemTimeLogging) event.getData();
            if ("name".equals(event.getFieldName())) {
                int typeId = itemLogging.getTypeid();
                int projectId = itemLogging.getProjectid();

                if (ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
                    PageActionChain chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                            new BugScreenData.Read(typeId));
                    EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain));
                } else if (ProjectTypeConstants.TASK.equals(itemLogging.getType())) {
                    PageActionChain chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                            new TaskScreenData.Read(typeId));
                    EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain));
                }
            } else if ("projectName".equals(event.getFieldName())) {
                PageActionChain chain = new PageActionChain(new ProjectScreenData.Goto(itemLogging.getProjectid()));
                EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain));
            }
        }
    };

    private List<Integer> getProjectIds() {
        List<Integer> keys = new ArrayList<>();
        for (SimpleProject project : projects) {
            keys.add(project.getId());
        }
        return keys;
    }

    private class UserInvolvedProjectsListSelect extends ListSelect<SimpleProject> {
        private static final long serialVersionUID = 1L;

        UserInvolvedProjectsListSelect(List<SimpleProject> projects) {
            this.setItems(projects);
            this.setItemCaptionGenerator((ItemCaptionGenerator<SimpleProject>) project -> project.getName());
            this.setRows(4);
        }

        Collection<Integer> getSelectedProjectsKey() {
            Set<SimpleProject> selectedItems = getValue();
            return selectedItems.stream().map(Project::getId).collect(Collectors.toList());
        }
    }

    private static class UserInvolvedProjectsMemberListSelect extends ListSelect<SimpleUser> {
        private static final long serialVersionUID = 1L;

        private List<SimpleUser> users;

        UserInvolvedProjectsMemberListSelect(List<Integer> projectIds) {
            users = AppContextUtil.getSpringBean(ProjectMemberService.class).getActiveUsersInProjects(projectIds, AppUI.getAccountId());
            setItems(users);
            setItemCaptionGenerator((ItemCaptionGenerator<SimpleUser>) user -> user.getDisplayName());
            this.setRows(4);
        }

        List<String> getSelectedUsers() {
            return getValue().stream().map(SimpleUser::getUsername).collect(Collectors.toList());
        }

        List<String> getAllUsersInInvolvedProjects() {
            return users.stream().map(SimpleUser::getUsername).collect(Collectors.toList());
        }
    }
}
