package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReportStatistic;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsUtil;
import com.esofthead.mycollab.module.project.ui.components.ComponentUtils;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.eventbus.Subscribe;
import com.hp.gagawa.java.elements.A;
import com.vaadin.data.Property;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.apache.commons.collections.CollectionUtils;
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

    private PopupDateFieldExt standupCalendar = new PopupDateFieldExt();

    private ProjectListComp projectListComp;
    private StandupPerProjectView standupPerProjectView;
    private List<Integer> projectIds;
    private StandupReportStatistic selectedProject = null;
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
        this.setMargin(new MarginInfo(false, false, true, false));
        standupCalendar.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                onDate = standupCalendar.getValue();
                showReports();
            }
        });
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

    @Override
    public void display(List<Integer> projectIds, Date date) {
        this.projectIds = projectIds;
        this.onDate = date;
        standupCalendar.setValue(date);
    }

    private void showReports() {
        removeAllComponents();
        if (CollectionUtils.isNotEmpty(projectIds)) {
            constructHeader();

            ELabel listLnl = ELabel.h3("Projects (" + projectIds.size() + ")");
            MHorizontalLayout favoriteListHeaderPanel = new MHorizontalLayout(listLnl).expand(listLnl).withMargin(new
                    MarginInfo(false, true, false, true)).withStyleName("panel-header").withFullWidth().alignAll(Alignment.MIDDLE_LEFT);
            projectListComp = new ProjectListComp();
            MVerticalLayout projectListPanel = new MVerticalLayout(favoriteListHeaderPanel, projectListComp).withMargin(false).withSpacing(false).withWidth("300px");

            standupPerProjectView = new StandupPerProjectView();
            with(new MHorizontalLayout(projectListPanel, standupPerProjectView).expand(standupPerProjectView));

            int totalCount = projectListComp.display(projectIds, onDate);
            if (totalCount > 0) {
                StandupReportStatistic firstProject = projectListComp.getItemAt(0);
                if (firstProject != null) {
                    viewStandupReportsForProject(firstProject);
                }
                Component firstRow = projectListComp.getRowAt(0);
                if (firstRow != null) {
                    projectListComp.setSelectedRow(firstRow);
                }
            }
        }
    }

    private void viewStandupReportsForProject(StandupReportStatistic project) {
        this.selectedProject = project;
        standupPerProjectView.displayReports(project.getProjectId(), onDate);
    }

    private class ProjectListComp extends AbstractBeanPagedList<StandupReportStatistic> {
        private StandupReportService standupReportService;
        private List<Integer> projectIds;
        private Date onDate;

        ProjectListComp() {
            super(new ProjectRowHandler(), 10);
            addStyleName(UIConstants.BORDER_LIST);
            setControlStyle("borderlessControl");
            standupReportService = AppContextUtil.getSpringBean(StandupReportService.class);
        }

        @Override
        protected QueryHandler<StandupReportStatistic> buildQueryHandler() {
            return new QueryHandler<StandupReportStatistic>() {
                @Override
                public int queryTotalCount() {
                    return projectIds.size();
                }

                @Override
                public List<StandupReportStatistic> queryCurrentData() {
                    return standupReportService.getProjectReportsStatistic(projectIds, onDate, searchRequest);
                }
            };
        }

        int display(List<Integer> projectIds, Date date) {
            this.projectIds = projectIds;
            this.onDate = date;
            doSearch();
            return projectIds.size();
        }
    }

    private class ProjectRowHandler implements AbstractBeanPagedList.RowDisplayHandler<StandupReportStatistic> {
        @Override
        public Component generateRow(final AbstractBeanPagedList host, final StandupReportStatistic project, int rowIndex) {
            ELabel projectLbl = new ELabel(project.getProjectName()).withStyleName(UIConstants.TEXT_ELLIPSIS);
            final MHorizontalLayout layout = new MHorizontalLayout(ProjectAssetsUtil.buildProjectLogo(project
                    .getProjectKey(), project.getProjectId(), project.getProjectAvatarId(), 32),
                    projectLbl, new ELabel(" (" + project.getTotalWrittenReports() + " / "
                    + project.getTotalReports() + ")").withWidthUndefined()).expand(projectLbl).withStyleName(UIConstants
                    .BORDER_LIST_ROW).withStyleName(UIConstants.CURSOR_POINTER).withFullWidth().alignAll(Alignment.MIDDLE_LEFT);
            layout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                    selectedProject = project;
                    EventBusFactory.getInstance().post(new StandUpEvent.DisplayStandupInProject(this, project.getProjectId()));
                    host.setSelectedRow(layout);
                }
            });
            return layout;
        }
    }

    private void constructHeader() {
        MHorizontalLayout header = new MHorizontalLayout().withMargin((new MarginInfo(true, false, true, false))).withFullWidth();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        MHorizontalLayout headerLeft = new MHorizontalLayout();

        HeaderWithFontAwesome titleLbl = ComponentUtils.headerH2(ProjectTypeConstants.STANDUP,
                AppContext.getMessage(StandupI18nEnum.VIEW_LIST_TITLE));

        headerLeft.with(titleLbl, standupCalendar);
        header.with(headerLeft).withAlign(headerLeft, Alignment.TOP_LEFT);

        Button newReportBtn = new Button(AppContext.getMessage(StandupI18nEnum.BUTTON_ADD_REPORT_LABEL), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                if (selectedProject != null) {
                    UI.getCurrent().addWindow(new StandupAddWindow(selectedProject, onDate));
                } else {
                    NotificationUtil.showErrorNotification("You do not select any project");
                }
            }
        });
        newReportBtn.setStyleName(UIConstants.BUTTON_ACTION);
        newReportBtn.setIcon(FontAwesome.PLUS);

        header.with(newReportBtn).withAlign(newReportBtn, Alignment.TOP_RIGHT);
        this.addComponent(header);
    }

    private static class StandupPerProjectView extends MHorizontalLayout {
        private BeanList<StandupReportService, StandupReportSearchCriteria, SimpleStandupReport> reportInDay;
        private StandupMissingComp standupMissingComp;

        void displayReports(Integer projectId, Date onDate) {
            removeAllComponents();
            reportInDay = new BeanList<>(AppContextUtil.getSpringBean(StandupReportService.class), StandupReportRowDisplay.class);
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

    public static class StandupReportRowDisplay extends RowDisplayHandler<SimpleStandupReport> {
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
