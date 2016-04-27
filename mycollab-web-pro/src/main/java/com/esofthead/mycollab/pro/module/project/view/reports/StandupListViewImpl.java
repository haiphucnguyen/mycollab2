package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.db.query.DateParam;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsUtil;
import com.esofthead.mycollab.module.project.ui.components.ComponentUtils;
import com.esofthead.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.StyleCalendarExp;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.eventbus.Subscribe;
import com.hp.gagawa.java.elements.A;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.joda.time.LocalDate;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.esofthead.mycollab.utils.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class StandupListViewImpl extends AbstractPageView implements StandupListView {
    private static final long serialVersionUID = 1L;

    private StandupReportSearchCriteria baseCriteria;
    private PopupButton dateChooser;
    private StyleCalendarExp standupCalendar = new StyleCalendarExp();

    private ProjectListComp projectListComp;
    private StandupPerProjectView standupPerProjectView;
    private Integer selectedProjectId = null;
    private Date onDate = new GregorianCalendar().getTime();

    private ApplicationEventListener<StandUpEvent.DisplayStandupInProject> displayStandupHandler = new
            ApplicationEventListener<StandUpEvent.DisplayStandupInProject>() {
                @Override
                @Subscribe
                public void handle(StandUpEvent.DisplayStandupInProject event) {
                    Integer projectId = (Integer) event.getData();
                    standupPerProjectView.displayReports(projectId, onDate);
                }
            };


    public StandupListViewImpl() {
        super();
        this.setMargin(new MarginInfo(true, false, true, false));
        standupCalendar.addStyleName("standup-calendar");
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(displayStandupHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(displayStandupHandler);
        super.detach();
    }

    private void addCalendarEvent() {
        standupCalendar.getStyleCalendar().addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(final ValueChangeEvent event) {
                Date selectedDate = (Date) event.getProperty().getValue();
                displayReport(selectedDate);
                standupCalendar.setLabelTime(AppContext.formatDate(selectedDate));
                dateChooser.setCaption(AppContext.formatDate(selectedDate));
                dateChooser.setPopupVisible(false);

                ReportBreadcrumb breadCrumb = ViewManager.getCacheComponent(ReportBreadcrumb.class);
                breadCrumb.gotoStandupList(selectedDate);
            }
        });

        standupCalendar.getBtnShowNextYear().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                standupCalendar.getStyleCalendar().showNextYear();
                standupCalendar.setLabelTime(AppContext.formatDate(standupCalendar.getStyleCalendar().getShowingDate()));
                getListReport();
            }
        });

        standupCalendar.getBtnShowNextMonth().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                standupCalendar.getStyleCalendar().showNextMonth();
                standupCalendar.setLabelTime(AppContext.formatDate(standupCalendar.getStyleCalendar().getShowingDate()));
                getListReport();
            }
        });

        standupCalendar.getBtnShowPreviousMonth().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                standupCalendar.getStyleCalendar().showPreviousMonth();
                standupCalendar.setLabelTime(AppContext.formatDate(standupCalendar.getStyleCalendar().getShowingDate()));
                getListReport();
            }
        });

        standupCalendar.getBtnShowPreviousYear().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                standupCalendar.getStyleCalendar().showPreviousYear();
                standupCalendar.setLabelTime(AppContext.formatDate(standupCalendar.getStyleCalendar().getShowingDate()));
                getListReport();
            }
        });
    }

    private void getListReport() {
        StandupReportSearchCriteria criteria = BeanUtility.deepClone(baseCriteria);

        criteria.addExtraField(DateParam.inRangeDate(StandupReportSearchCriteria.p_fordays, new VariableInjector() {
            @Override
            public Object eval() {
                LocalDate date = new LocalDate(standupCalendar.getStyleCalendar().getShowingDate());
                LocalDate minDate = date.dayOfMonth().withMinimumValue();
                LocalDate maxDate = date.dayOfMonth().withMaximumValue();
                return new Date[]{minDate.toDate(), maxDate.toDate()};
            }
        }));
        StandupReportService reportService = ApplicationContextUtil.getSpringBean(StandupReportService.class);
        List<GroupItem> reportsCount = reportService.getReportsCount(criteria);

        for (GroupItem groupItem : reportsCount) {
            Date date = DateTimeUtils.convertDateByString(groupItem.getGroupname(),
                    AppContext.getUserDateFormat().getDateFormat());
            standupCalendar.addSelectedDate(date);
        }
    }

    private void displayReport(Date date) {
        baseCriteria.setOnDate(new DateSearchField(date, DateSearchField.EQUAL));
        this.setSearchCriteria(baseCriteria);
    }

    @Override
    public void display(List<Integer> projectKeys, Date date) {
        removeAllComponents();
        constructHeader();
        ELabel listLnl = ELabel.h3("Projects");
        MHorizontalLayout favoriteListHeaderPanel = new MHorizontalLayout(listLnl).expand(listLnl).withMargin(new
                MarginInfo(false, true, false, true)).withStyleName("panel-header").withFullWidth().alignAll(Alignment.MIDDLE_LEFT);
        projectListComp = new ProjectListComp();
        MVerticalLayout projectListPanel = new MVerticalLayout(favoriteListHeaderPanel, projectListComp).withMargin(false).withSpacing(false).withWidth("300px");

        ProjectSearchCriteria projectSearchCriteria = new ProjectSearchCriteria();
        projectSearchCriteria.setProjectKeys(new SetSearchField<>(projectKeys));
        standupPerProjectView = new StandupPerProjectView();
        with(new MHorizontalLayout(projectListPanel, standupPerProjectView).expand(standupPerProjectView));

        int totalCount = projectListComp.setSearchCriteria(projectSearchCriteria);
        if (totalCount > 0) {
            SimpleProject firstProject = projectListComp.getItemAt(0);
            if (firstProject != null) {
                viewStandupReportsForProject(firstProject);
            }
            Component firstRow = projectListComp.getRowAt(0);
            if (firstRow != null) {
                projectListComp.setSelectedRow(firstRow);
            }
        }
    }

    private void viewStandupReportsForProject(SimpleProject project) {
        standupPerProjectView.displayReports(project.getId(), onDate);
    }

    private static class ProjectListComp extends DefaultBeanPagedList<ProjectService, ProjectSearchCriteria, SimpleProject> {
        ProjectListComp() {
            super(ApplicationContextUtil.getSpringBean(ProjectService.class), new ProjectRowHandler(), 10);
            addStyleName(UIConstants.BORDER_LIST);
            setControlStyle("borderlessControl");
        }
    }

    private static class ProjectRowHandler implements AbstractBeanPagedList.RowDisplayHandler<SimpleProject> {
        @Override
        public Component generateRow(final AbstractBeanPagedList host, final SimpleProject project, int rowIndex) {
            ELabel projectLbl = new ELabel(project.getName()).withStyleName(UIConstants.TEXT_ELLIPSIS);
            final MHorizontalLayout layout = new MHorizontalLayout(ProjectAssetsUtil.buildProjectLogo(project, 32),
                    projectLbl).expand(projectLbl).withStyleName(UIConstants.BORDER_LIST_ROW)
                    .withStyleName(UIConstants.CURSOR_POINTER).withFullWidth().alignAll(Alignment.MIDDLE_LEFT);
            layout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                    EventBusFactory.getInstance().post(new StandUpEvent.DisplayStandupInProject(this, project.getId(), null));
                    host.setSelectedRow(layout);
                }
            });
            return layout;
        }
    }

    public void setSearchCriteria(StandupReportSearchCriteria searchCriteria) {
        baseCriteria = searchCriteria;

        if (searchCriteria.getOnDate() != null) {
            dateChooser.setCaption(AppContext.formatDate(searchCriteria.getOnDate().getValue()));
            standupCalendar.getStyleCalendar().setShowingDate(searchCriteria.getOnDate().getValue());
            standupCalendar.setLabelTime(AppContext.formatDate(searchCriteria.getOnDate().getValue()));
//            standupMissingComp.search(searchCriteria.getOnDate().getValue());
        }
    }

    private void constructHeader() {
        MHorizontalLayout header = new MHorizontalLayout().withMargin((new MarginInfo(true, false, true, false))).
                withWidth("100%");
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        MHorizontalLayout headerLeft = new MHorizontalLayout();

        HeaderWithFontAwesome titleLbl = ComponentUtils.headerH2(ProjectTypeConstants.STANDUP,
                AppContext.getMessage(StandupI18nEnum.VIEW_LIST_TITLE));

        dateChooser = new PopupButton(AppContext.getMessage(StandupI18nEnum.CHOOSE_REPORT_DATE));
        dateChooser.setContent(standupCalendar);
        dateChooser.setStyleName(UIConstants.BUTTON_ACTION);

        headerLeft.with(titleLbl, dateChooser);

        header.with(headerLeft).withAlign(headerLeft, Alignment.TOP_LEFT);

        Button addNewReport = new Button(AppContext.getMessage(StandupI18nEnum.BUTTON_ADD_REPORT_LABEL), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                EventBusFactory.getInstance().post(new StandUpEvent.GotoAdd(StandupListViewImpl.class, null));
            }
        });
        addNewReport.setStyleName(UIConstants.BUTTON_ACTION);
        addNewReport.setIcon(FontAwesome.PLUS);

        header.with(addNewReport).withAlign(addNewReport, Alignment.TOP_RIGHT);
        this.addComponent(header);
    }

    private static class StandupPerProjectView extends MHorizontalLayout {
        private BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport> reportInDay;
        private StandupMissingComp standupMissingComp;

        void displayReports(Integer projectId, Date onDate) {
            removeAllComponents();
            reportInDay = new BeanList<>(ApplicationContextUtil.getSpringBean(StandupReportService.class), StandupReportRowDisplay.class);
            standupMissingComp = new StandupMissingComp();
            standupMissingComp.setWidth("300px");
            this.with(reportInDay, standupMissingComp).expand(reportInDay);
            standupMissingComp.search(projectId, onDate);

            StandupReportSearchCriteria baseCriteria = new StandupReportSearchCriteria();
            baseCriteria.setOnDate(new DateSearchField(onDate, DateSearchField.EQUAL));
            baseCriteria.setProjectIds(new SetSearchField<>(projectId));
            reportInDay.setSearchCriteria(baseCriteria);
        }
    }

    private static class StandupReportRowDisplay extends RowDisplayHandler<SimpleStandupReport> {
        private static final long serialVersionUID = 1L;

        @Override
        public Component generateRow(SimpleStandupReport report, int rowIndex) {
            HorizontalLayout rowLayout = new HorizontalLayout();
            rowLayout.setStyleName("standup-block");

            MVerticalLayout userInfo = new MVerticalLayout().withWidth("200px").withFullHeight().withStyleName(UIConstants
                    .HOVER_EFFECT_NOT_BOX);
            userInfo.setDefaultComponentAlignment(Alignment.TOP_CENTER);

            Image userAvatar = UserAvatarControlFactory.createUserAvatarEmbeddedComponent(report.getLogByAvatarId(), 100);
            userAvatar.addStyleName(UIConstants.CIRCLE_BOX);
            userInfo.addComponent(userAvatar);
            Label memberLink = new Label(buildMemberLink(report), ContentMode.HTML);
            userInfo.with(memberLink).expand(memberLink).withAlign(memberLink, Alignment.TOP_CENTER);
            rowLayout.addComponent(userInfo);

            MVerticalLayout reportContent = new MVerticalLayout().withStyleName("report-content", UIConstants.HOVER_EFFECT_NOT_BOX);

            ELabel whatYesterdayLbl = ELabel.h3(AppContext.getMessage(StandupI18nEnum.STANDUP_LASTDAY));
            reportContent.addComponent(whatYesterdayLbl);
            Label whatYesterdayField = new SafeHtmlLabel(report.getWhatlastday());
            whatYesterdayField.setSizeUndefined();
            whatYesterdayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
            reportContent.addComponent(whatYesterdayField);

            ELabel whatTodayLbl = ELabel.h3(AppContext.getMessage(StandupI18nEnum.STANDUP_TODAY));
            reportContent.addComponent(whatTodayLbl);
            Label whatTodayField = new SafeHtmlLabel(report.getWhattoday());
            whatTodayField.setSizeUndefined();
            whatTodayField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
            reportContent.addComponent(whatTodayField);

            ELabel roadblockLbl = ELabel.h3(AppContext.getMessage(StandupI18nEnum.STANDUP_ISSUE));
            reportContent.addComponent(roadblockLbl);
            Label whatProblemField = new SafeHtmlLabel(report.getWhatproblem());
            whatProblemField.setSizeUndefined();
            whatProblemField.addStyleName(UIConstants.STANDUP_ROW_CONTENT);
            reportContent.addComponent(whatProblemField);

            rowLayout.addComponent(reportContent);
            rowLayout.setExpandRatio(reportContent, 1.0f);
            return rowLayout;
        }

        private String buildMemberLink(SimpleStandupReport report) {
            A userLink = new A().setId("tag" + TOOLTIP_ID).setHref(ProjectLinkBuilder.generateProjectMemberFullLink(1, report.getLogby()))
                    .appendText(StringUtils.trim(report.getLogByFullName(), 30, true));
            userLink.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(report.getLogby()));
            userLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());

            return userLink.write();
        }

    }
}
