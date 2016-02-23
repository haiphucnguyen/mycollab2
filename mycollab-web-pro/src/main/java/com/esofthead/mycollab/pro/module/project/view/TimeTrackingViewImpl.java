package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.*;
import com.esofthead.mycollab.core.db.query.DateParam;
import com.esofthead.mycollab.core.db.query.DateRangeInjecter;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.ITimeTrackingView;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.module.project.view.time.TimeTableFieldDef;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.pro.module.project.ui.components.*;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.AsyncInvoker;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.resources.LazyStreamSource;
import com.esofthead.mycollab.vaadin.ui.PopupDateFieldExt;
import com.esofthead.mycollab.vaadin.web.ui.OptionPopupContent;
import com.esofthead.mycollab.vaadin.web.ui.SplitButton;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable;
import com.vaadin.data.Property;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.*;
import java.util.Calendar;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@ViewComponent
public class TimeTrackingViewImpl extends AbstractPageView implements ITimeTrackingView {
    private static final long serialVersionUID = 1L;

    private static final String GROUPBY_PROJECT = "Project";
    private static final String GROUPBY_USER = "User";
    private static final String GROUPBY_DATE = "Date";

    private List<SimpleProject> projects;

    private UserInvolvedProjectsListSelect projectField;
    private UserInvolvedProjectsMemberListSelect userField;
    private PopupDateFieldExt fromDateField, toDateField;
    private ComboBox groupField, orderField;

    private Label totalHoursLoggingLabel;
    private SplitButton exportButtonControl;

    private ItemTimeLoggingService itemTimeLoggingService;

    private MVerticalLayout timeTrackingWrapper;

    public TimeTrackingViewImpl() {
        this.setMargin(new MarginInfo(false, true, false, true));
    }

    private void initListSelectStyle(ListSelect listSelect) {
        listSelect.setWidth("300px");
        listSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
        listSelect.setNullSelectionAllowed(false);
        listSelect.setMultiSelect(true);
        listSelect.setRows(4);
    }

    private StreamResource constructStreamResource(final ReportExportType exportType) {
        LazyStreamSource streamSource = new LazyStreamSource() {
            private static final long serialVersionUID = 1L;

            @Override
            protected StreamResource.StreamSource buildStreamSource() {
//                return new SimpleGridExportItemsStreamResource.AllItems<>(
//                        "Time Report", new RpParameterBuilder(getVisibleFields()), exportType,
//                        itemTimeLoggingService, searchCriteria,
//                        SimpleItemTimeLogging.class);
                throw new MyCollabException("Not support reporting yet");
            }
        };
        return new StreamResource(streamSource, exportType.getDefaultFileName());
    }

    private AbstractTimeTrackingDisplayComp buildTimeTrackingComp() {
        String groupBy = (String) groupField.getValue();

        if (groupBy.equals(GROUPBY_PROJECT)) {
            return new TimeTrackingProjectOrderComponent(getVisibleFields(), tableClickListener);
        } else if (groupBy.equals(GROUPBY_DATE)) {
            return new TimeTrackingDateOrderComponent(getVisibleFields(), tableClickListener);
        } else if (groupBy.equals(GROUPBY_USER)) {
            return new TimeTrackingUserOrderComponent(getVisibleFields(), tableClickListener);
        } else {
            throw new MyCollabException("Do not support view type: " + groupBy);
        }
    }

    private List<TableViewField> getVisibleFields() {
        String groupBy = (String) groupField.getValue();

        if (groupBy.equals(GROUPBY_PROJECT)) {
            return Arrays.asList(TimeTableFieldDef.summary(),
                    TimeTableFieldDef.logForDate(), TimeTableFieldDef.logUser(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable());
        } else if (groupBy.equals(GROUPBY_DATE)) {
            return Arrays.asList(TimeTableFieldDef.summary(),
                    TimeTableFieldDef.logUser(), TimeTableFieldDef.project(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable());
        } else if (groupBy.equals(GROUPBY_USER)) {
            return Arrays.asList(TimeTableFieldDef.summary(),
                    TimeTableFieldDef.logForDate(), TimeTableFieldDef.project(),
                    TimeTableFieldDef.logValue(), TimeTableFieldDef.billable());
        } else {
            throw new MyCollabException("Do not support view type: " + groupBy);
        }
    }

    @Override
    public void display() {
        removeAllComponents();
        projects = ApplicationContextUtil.getSpringBean(ProjectService.class)
                .getProjectsUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        if (CollectionUtils.isNotEmpty(projects)) {
            itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);

            Label titleLbl = new Label(ProjectAssetsManager.getAsset(ProjectTypeConstants.TIME).getHtml() +
                    " Time Tracking", ContentMode.HTML);
            titleLbl.addStyleName(ValoTheme.LABEL_H2);

            MHorizontalLayout headerWrapper = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false))
                    .withWidth("100%");

            Button exportBtn = new Button("Export", new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    exportButtonControl.setPopupVisible(true);
                }
            });
            exportButtonControl = new SplitButton(exportBtn);
            exportButtonControl.setWidthUndefined();
            exportButtonControl.addStyleName(UIConstants.BUTTON_OPTION);
            exportButtonControl.setIcon(FontAwesome.EXTERNAL_LINK);

            OptionPopupContent popupButtonsControl = new OptionPopupContent();
            exportButtonControl.setContent(popupButtonsControl);

            Button exportPdfBtn = new Button("Pdf");
            FileDownloader pdfDownloader = new FileDownloader(constructStreamResource(ReportExportType.PDF));
            pdfDownloader.extend(exportPdfBtn);
            exportPdfBtn.setIcon(FontAwesome.FILE_PDF_O);
            popupButtonsControl.addOption(exportPdfBtn);

            Button exportExcelBtn = new Button("Excel");
            FileDownloader excelDownloader = new FileDownloader(constructStreamResource(ReportExportType.EXCEL));
            excelDownloader.extend(exportExcelBtn);
            exportExcelBtn.setIcon(FontAwesome.FILE_EXCEL_O);
            popupButtonsControl.addOption(exportExcelBtn);

            headerWrapper.with(titleLbl, exportButtonControl).expand(titleLbl).alignAll(Alignment.MIDDLE_LEFT);

            this.addComponent(headerWrapper);

            CssLayout contentWrapper = new CssLayout();
            contentWrapper.setWidth("100%");

            MHorizontalLayout controlsPanel = new MHorizontalLayout().withWidth("100%");
            contentWrapper.addComponent(controlsPanel);

            VerticalLayout selectionLayoutWrapper = new VerticalLayout();
            selectionLayoutWrapper.setWidth("100%");
            selectionLayoutWrapper.addStyleName("time-tracking-summary-search-panel");
            controlsPanel.addComponent(selectionLayoutWrapper);

            GridLayout selectionLayout = new GridLayout(9, 2);
            selectionLayout.setSpacing(true);
            selectionLayout.setMargin(true);
            selectionLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
            selectionLayoutWrapper.addComponent(selectionLayout);

            Label fromLb = new Label("From:");
            fromLb.setWidthUndefined();
            selectionLayout.addComponent(fromLb, 0, 0);

            this.fromDateField = new PopupDateFieldExt();
            this.fromDateField.setResolution(Resolution.DAY);
            this.fromDateField.setDateFormat(AppContext.getUserDateFormat().getDateFormat());
            this.fromDateField.setWidth("100px");
            selectionLayout.addComponent(this.fromDateField, 1, 0);

            Label toLb = new Label("To:");
            toLb.setWidthUndefined();
            selectionLayout.addComponent(toLb, 2, 0);

            this.toDateField = new PopupDateFieldExt();
            this.toDateField.setResolution(Resolution.DAY);
            this.toDateField.setDateFormat(AppContext.getUserDateFormat().getDateFormat());
            this.toDateField.setWidth("100px");
            selectionLayout.addComponent(this.toDateField, 3, 0);

            Label groupLb = new Label("Group:");
            groupLb.setWidthUndefined();
            selectionLayout.addComponent(groupLb, 0, 1);

            this.groupField = new ValueComboBox(false, GROUPBY_PROJECT, GROUPBY_DATE, GROUPBY_USER);
            this.groupField.setWidth("100px");
            groupField.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    searchTimeReporting();
                }
            });
            selectionLayout.addComponent(this.groupField, 1, 1);

            Label sortLb = new Label("Sort:");
            sortLb.setWidthUndefined();
            selectionLayout.addComponent(sortLb, 2, 1);

            this.orderField = new ItemOrderComboBox();
            orderField.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    searchTimeReporting();
                }
            });
            this.orderField.setWidth("100px");
            selectionLayout.addComponent(this.orderField, 3, 1);

            Label projectLb = new Label("Project:");
            projectLb.setWidthUndefined();
            selectionLayout.addComponent(projectLb, 4, 0);

            this.projectField = new UserInvolvedProjectsListSelect();
            initListSelectStyle(this.projectField);
            selectionLayout.addComponent(this.projectField, 5, 0, 5, 1);

            Label userLb = new Label("User:");
            userLb.setWidthUndefined();
            selectionLayout.addComponent(userLb, 6, 0);

            this.userField = new UserInvolvedProjectsMemberListSelect(getProjectIds());
            initListSelectStyle(this.userField);
            selectionLayout.addComponent(this.userField, 7, 0, 7, 1);

            Button queryBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SUBMIT), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final Button.ClickEvent event) {
                    searchTimeReporting();
                }
            });
            queryBtn.setStyleName(UIConstants.BUTTON_ACTION);

            selectionLayout.addComponent(queryBtn, 8, 0);

            totalHoursLoggingLabel = new Label("Total Hours Logging: 0 Hrs", ContentMode.HTML);
            totalHoursLoggingLabel.addStyleName(ValoTheme.LABEL_H2);
            MHorizontalLayout loggingPanel = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                    .withWidth("100%");
            loggingPanel.with(totalHoursLoggingLabel).expand(totalHoursLoggingLabel);
            contentWrapper.addComponent(loggingPanel);

            timeTrackingWrapper = new MVerticalLayout().withWidth("100%").withMargin(new MarginInfo(true, false, true, false));
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

            Label infoLbl = new Label("You are not involved in any project yet to track time working");
            infoLbl.setWidthUndefined();
            contentWrapper.with(infoLbl);
            this.with(contentWrapper).withAlign(contentWrapper, Alignment.TOP_CENTER);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void searchTimeReporting() {
        final ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();

        Order order = (Order) orderField.getValue();
        String sortDirection;
        if (Order.ASCENDING == order) {
            sortDirection = SearchCriteria.ASC;
        } else {
            sortDirection = SearchCriteria.DESC;
        }

        if (GROUPBY_DATE.equals(groupField.getValue())) {
            searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField("logForDay", sortDirection)));
        } else if (GROUPBY_USER.equals(groupField.getValue())) {
            searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField("loguser", sortDirection)));
        } else if (GROUPBY_PROJECT.equals(groupField.getValue())) {
            searchCriteria.setOrderFields(Arrays.asList(new SearchCriteria.OrderField("projectName", sortDirection)));
        }

        final Date fromDate = fromDateField.getValue();
        final Date toDate = toDateField.getValue();
        searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates, new
                DateRangeInjecter(fromDate, toDate)));

        Collection<String> selectedUsers = (Collection<String>) this.userField.getValue();
        if (CollectionUtils.isNotEmpty(selectedUsers)) {
            searchCriteria.setLogUsers(new SetSearchField(selectedUsers));
        } else {
            searchCriteria.setLogUsers(new SetSearchField(userField.getUsernames()));
        }

        Collection<Integer> selectedProjects = (Collection<Integer>) this.projectField.getValue();
        if (CollectionUtils.isNotEmpty(selectedProjects)) {
            searchCriteria.setProjectIds(new SetSearchField<>(selectedProjects));
        } else {
            searchCriteria.setProjectIds(new SetSearchField<>(getProjectIds()));
        }

        searchCriteria.setIsBillable(new BooleanSearchField(true));
        Double billableHour = this.itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
        if (billableHour == null || billableHour < 0) {
            billableHour = 0d;
        }

        searchCriteria.setIsBillable(new BooleanSearchField(false));
        Double nonBillableHours = this.itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
        if (nonBillableHours == null || nonBillableHours < 0) {
            nonBillableHours = 0d;
        }

        searchCriteria.setIsBillable(null);
        Double totalHour = this.itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);

        if (totalHour == null || totalHour < 0) {
            totalHoursLoggingLabel.setValue("Total hours logging: 0 Hrs");
        } else {
            totalHoursLoggingLabel.setValue(AppContext.getMessage(TimeTrackingI18nEnum.TASK_LIST_RANGE_WITH_TOTAL_HOUR,
                    fromDate, toDate, totalHour, billableHour, nonBillableHours));
        }

        timeTrackingWrapper.removeAllComponents();

        final AbstractTimeTrackingDisplayComp timeDisplayComp = buildTimeTrackingComp();
        timeTrackingWrapper.addComponent(timeDisplayComp);
        AsyncInvoker.access(new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);
                int totalCount = itemTimeLoggingService.getTotalCount(searchCriteria);
                int pages = totalCount / 20;
                for (int page = 0; page < pages + 1; page++) {
                    List<SimpleItemTimeLogging> itemTimeLoggings = itemTimeLoggingService.findPagableListByCriteria(new
                            SearchRequest<>(searchCriteria, page + 1, 20));
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
            if ("summary".equals(event.getFieldName())) {
                int typeId = itemLogging.getTypeid();
                int projectId = itemLogging.getProjectid();

                if (ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
                    PageActionChain chain = new PageActionChain(
                            new ProjectScreenData.Goto(projectId),
                            new BugScreenData.Read(typeId));
                    EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain));
                } else if (ProjectTypeConstants.TASK.equals(itemLogging
                        .getType())) {
                    PageActionChain chain = new PageActionChain(
                            new ProjectScreenData.Goto(projectId),
                            new TaskScreenData.Read(typeId));
                    EventBusFactory.getInstance().post(
                            new ProjectEvent.GotoMyProject(this, chain));
                }
            } else if ("projectName".equals(event.getFieldName())) {
                PageActionChain chain = new PageActionChain(
                        new ProjectScreenData.Goto(itemLogging.getProjectid()));
                EventBusFactory.getInstance().post(
                        new ProjectEvent.GotoMyProject(this, chain));
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

        public UserInvolvedProjectsListSelect() {
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
            users = ApplicationContextUtil.getSpringBean(ProjectMemberService.class).getActiveUsersInProjects(projectIds, AppContext.getAccountId());

            for (SimpleUser user : users) {
                this.addItem(user.getUsername());
                this.setItemCaption(user.getUsername(), user.getDisplayName());
            }
        }

        public List<String> getUsernames() {
            List<String> keys = new ArrayList<>();
            for (SimpleUser user : users) {
                keys.add(user.getUsername());
            }
            return keys;
        }
    }
}
