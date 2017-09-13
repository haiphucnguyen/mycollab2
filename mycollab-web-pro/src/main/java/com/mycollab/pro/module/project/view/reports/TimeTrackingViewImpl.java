package com.mycollab.pro.module.project.view.reports;

import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.*;
import com.mycollab.db.query.ConstantValueInjector;
import com.mycollab.db.query.DateParam;
import com.mycollab.db.query.LazyValueInjector;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.ProjectTypeConstants;
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
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.PageActionChain;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.ValueComboBox;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.*;
import java.util.Calendar;

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
    private PopupDateFieldExt fromDateField, toDateField;
    private ComboBox groupField, orderField;
    private ItemTimeLoggingSearchCriteria searchCriteria;

    private Label totalHoursLoggingLabel;

    private ItemTimeLoggingService itemTimeLoggingService;

    private MVerticalLayout timeTrackingWrapper;

    public TimeTrackingViewImpl() {
        this.setMargin(new MarginInfo(false, false, true, false));
    }

    private void initListSelectStyle(ListSelect listSelect) {
        listSelect.setWidth("300px");
        listSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
        listSelect.setNullSelectionAllowed(false);
        listSelect.setMultiSelect(true);
        listSelect.setRows(4);
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
            return Arrays.asList(TimeTableFieldDef.summary(),
                    TimeTableFieldDef.logForDate(), TimeTableFieldDef.logUser(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime());
        } else if (groupBy.equals(UserUIContext.getMessage(DayI18nEnum.OPT_DATE))) {
            return Arrays.asList(TimeTableFieldDef.summary(),
                    TimeTableFieldDef.logUser(), TimeTableFieldDef.project(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime());
        } else if (groupBy.equals(UserUIContext.getMessage(UserI18nEnum.SINGLE))) {
            return Arrays.asList(TimeTableFieldDef.summary(),
                    TimeTableFieldDef.logForDate(), TimeTableFieldDef.project(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable(), TimeTableFieldDef.overtime());
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
            }))).withIcon(FontAwesome.PRINT).withStyleName(WebThemes.BUTTON_OPTION)
                    .withDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_EXPORT));

            headerWrapper.with(titleLbl, printBtn).expand(titleLbl).alignAll(Alignment.MIDDLE_LEFT);

            this.addComponent(headerWrapper);

            CssLayout contentWrapper = new CssLayout();
            contentWrapper.setWidth("100%");

            MHorizontalLayout controlsPanel = new MHorizontalLayout().withFullWidth().withStyleName(WebThemes.BOX);
            contentWrapper.addComponent(controlsPanel);

            GridLayout selectionLayout = new GridLayout(9, 2);
            selectionLayout.setSpacing(true);
            selectionLayout.setMargin(true);
            selectionLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
            controlsPanel.addComponent(selectionLayout);

            selectionLayout.addComponent(new ELabel(UserUIContext.getMessage(DayI18nEnum.OPT_FROM)).withStyleName(WebThemes
                    .META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px"), 0, 0);

            fromDateField = new PopupDateFieldExt();
            fromDateField.setResolution(Resolution.DAY);
            selectionLayout.addComponent(fromDateField, 1, 0);

            selectionLayout.addComponent(new ELabel(UserUIContext.getMessage(DayI18nEnum.OPT_TO)).withStyleName(WebThemes
                    .META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px"), 2, 0);

            toDateField = new PopupDateFieldExt();
            toDateField.setResolution(Resolution.DAY);
            selectionLayout.addComponent(toDateField, 3, 0);

            selectionLayout.addComponent(new ELabel(UserUIContext.getMessage(GenericI18Enum.OPT_GROUP)).withStyleName
                    (WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px"), 0, 1);

            groupField = new ValueComboBox(false, UserUIContext.getMessage(ProjectI18nEnum.SINGLE), UserUIContext
                    .getMessage(DayI18nEnum.OPT_DATE), UserUIContext.getMessage(UserI18nEnum.SINGLE));
            groupField.addValueChangeListener(valueChangeEvent -> searchTimeReporting());
            selectionLayout.addComponent(groupField, 1, 1);

            selectionLayout.addComponent(new ELabel(UserUIContext.getMessage(GenericI18Enum.ACTION_SORT)).withStyleName(WebThemes
                    .META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px"), 2, 1);

            orderField = new ItemOrderComboBox();
            orderField.addValueChangeListener(valueChangeEvent -> searchTimeReporting());
            selectionLayout.addComponent(orderField, 3, 1);

            selectionLayout.addComponent(new ELabel(UserUIContext.getMessage(ProjectI18nEnum.SINGLE))
                    .withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px"), 4, 0);

            projectField = new UserInvolvedProjectsListSelect();
            initListSelectStyle(projectField);
            selectionLayout.addComponent(projectField, 5, 0, 5, 1);

            selectionLayout.addComponent(new ELabel(UserUIContext.getMessage(UserI18nEnum.SINGLE))
                    .withStyleName(WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("60px"), 6, 0);

            userField = new UserInvolvedProjectsMemberListSelect(getProjectIds());
            initListSelectStyle(userField);
            selectionLayout.addComponent(userField, 7, 0, 7, 1);

            MButton queryBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SUBMIT), clickEvent -> searchTimeReporting())
                    .withStyleName(WebThemes.BUTTON_ACTION);

            selectionLayout.addComponent(queryBtn, 8, 0);

            totalHoursLoggingLabel = ELabel.h2("Total Hours Logging: 0 Hrs");
            MHorizontalLayout loggingPanel = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withFullWidth();
            loggingPanel.with(totalHoursLoggingLabel).expand(totalHoursLoggingLabel);
            contentWrapper.addComponent(loggingPanel);

            timeTrackingWrapper = new MVerticalLayout().withFullWidth().withMargin(new MarginInfo(true, false, true, false));
            contentWrapper.addComponent(this.timeTrackingWrapper);

            Calendar date = new GregorianCalendar();
            date.set(java.util.Calendar.DAY_OF_MONTH, 1);
            fromDateField.setValue(date.getTime());
            date.add(java.util.Calendar.DAY_OF_MONTH, date.getActualMaximum(java.util.Calendar.DAY_OF_MONTH));
            toDateField.setValue(date.getTime());
            this.with(contentWrapper).withAlign(contentWrapper, Alignment.TOP_CENTER);
            searchTimeReporting();
        } else {
            MVerticalLayout contentWrapper = new MVerticalLayout();
            contentWrapper.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

            Label infoLbl = new Label(UserUIContext.getMessage(TimeTrackingI18nEnum.ERROR_NOT_INVOLVED_ANY_PROJECT));
            infoLbl.setWidthUndefined();
            contentWrapper.with(infoLbl);
            this.with(contentWrapper).withAlign(contentWrapper, Alignment.TOP_CENTER);
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

        final Date fromDate = fromDateField.getValue();
        final Date toDate = toDateField.getValue();
        searchCriteria.addExtraField(DateParam.Companion.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                ConstantValueInjector.valueOf(Date.class, new Date[]{fromDate, toDate})));

        Collection<String> selectedUsers = (Collection<String>) userField.getValue();
        if (CollectionUtils.isNotEmpty(selectedUsers)) {
            searchCriteria.setLogUsers(new SetSearchField(selectedUsers));
        } else {
            searchCriteria.setLogUsers(new SetSearchField(userField.getUsernames()));
        }

        Collection<Integer> selectedProjects = (Collection<Integer>) projectField.getValue();
        if (CollectionUtils.isNotEmpty(selectedProjects)) {
            searchCriteria.setProjectIds(new SetSearchField<>(selectedProjects));
        } else {
            searchCriteria.setProjectIds(new SetSearchField<>(getProjectIds()));
        }

        searchCriteria.setIsBillable(new BooleanSearchField(true));
        Double billableHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setIsBillable(new BooleanSearchField(false));
        Double nonBillableHours = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        searchCriteria.setIsBillable(null);
        Double totalHour = itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        if (totalHour > 0) {
            totalHoursLoggingLabel.setValue(UserUIContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
                    fromDate, toDate, totalHour, billableHour, nonBillableHours));
        } else {
            totalHoursLoggingLabel.setValue(UserUIContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE, fromDate, toDate));
        }

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
                    List<SimpleItemTimeLogging> itemTimeLoggings = (List<SimpleItemTimeLogging>)itemTimeLoggingService.findPageableListByCriteria(new
                            BasicSearchRequest<>(searchCriteria, page + 1, 20));
                    for (SimpleItemTimeLogging item : itemTimeLoggings) {
                        timeDisplayComp.insertItem(item);
                    }
                }
                timeDisplayComp.flush();
            }
        });
    }

    private IPagedBeanTable.TableClickListener tableClickListener = new IPagedBeanTable.TableClickListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void itemClick(final IPagedBeanTable.TableClickEvent event) {
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

    private class UserInvolvedProjectsListSelect extends ListSelect {
        private static final long serialVersionUID = 1L;

        UserInvolvedProjectsListSelect() {
            for (SimpleProject project : projects) {
                this.addItem(project.getId());
                this.setItemCaption(project.getId(), project.getName());
            }
        }

    }

    private static class UserInvolvedProjectsMemberListSelect extends ListSelect {
        private static final long serialVersionUID = 1L;

        private List<SimpleUser> users;

        UserInvolvedProjectsMemberListSelect(List<Integer> projectIds) {
            users = AppContextUtil.getSpringBean(ProjectMemberService.class).getActiveUsersInProjects(projectIds, AppUI.getAccountId());

            for (SimpleUser user : users) {
                this.addItem(user.getUsername());
                this.setItemCaption(user.getUsername(), user.getDisplayName());
            }
        }

        List<String> getUsernames() {
            List<String> keys = new ArrayList<>();
            for (SimpleUser user : users) {
                keys.add(user.getUsername());
            }
            return keys;
        }
    }
}
