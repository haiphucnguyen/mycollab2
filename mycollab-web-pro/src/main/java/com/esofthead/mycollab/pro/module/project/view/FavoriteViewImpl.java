package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.ProjectActivityComponent;
import com.esofthead.mycollab.module.project.ui.format.BugFieldFormatter;
import com.esofthead.mycollab.module.project.ui.format.MilestoneFieldFormatter;
import com.esofthead.mycollab.module.project.ui.format.RiskFieldFormatter;
import com.esofthead.mycollab.module.project.ui.format.TaskFieldFormatter;
import com.esofthead.mycollab.module.project.view.IFavoriteView;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.bug.BugPreviewForm;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePreviewForm;
import com.esofthead.mycollab.module.project.view.task.TaskPreviewForm;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.pro.module.project.view.risk.RiskPreviewForm;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.esofthead.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.eventbus.Subscribe;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class FavoriteViewImpl extends AbstractPageView implements IFavoriteView {
    private ProjectGenericTaskSearchCriteria searchCriteria;

    private FavoriteListComp favoriteListComp;
    private AssignmentReadView assignmentReadView;

    private ApplicationEventListener<ProjectEvent.SelectFavoriteItem> selectFavoriteHandler = new
            ApplicationEventListener<ProjectEvent.SelectFavoriteItem>() {
                @Override
                @Subscribe
                public void handle(ProjectEvent.SelectFavoriteItem event) {
                    ProjectGenericTask assignment = (ProjectGenericTask) event.getData();
                    viewFavoriteItem(assignment);
                }
            };

    public FavoriteViewImpl() {
        addStyleName("hdr-view");
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(selectFavoriteHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(selectFavoriteHandler);
        setProjectNavigatorVisibility(true);
        super.detach();
    }

    @Override
    public void display() {
        removeAllComponents();
        setProjectNavigatorVisibility(false);
        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true, false))
                .withWidth("100%");
        Label headerLbl = new Label(FontAwesome.STAR.getHtml() + " Favorites", ContentMode.HTML);
        headerLbl.setSizeUndefined();
        headerLbl.setStyleName(ValoTheme.LABEL_H2);
        headerLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.with(headerLbl);

        favoriteListComp = new FavoriteListComp();
        assignmentReadView = new AssignmentReadView();
        MHorizontalLayout contentWrapper = new MHorizontalLayout(favoriteListComp, assignmentReadView).expand(assignmentReadView).withFullWidth();
        searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setMonitorProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        favoriteListComp.setSearchCriteria(searchCriteria);
        with(header, contentWrapper);
    }

    private void viewFavoriteItem(ProjectGenericTask assignment) {
        assignmentReadView.showAssignment(assignment);
    }

    private void setProjectNavigatorVisibility(boolean visibility) {
        ProjectView view = UIUtils.getRoot(this, ProjectView.class);
        if (view != null) {
            view.setNavigatorVisibility(visibility);
        }
    }

    private static class FavoriteListComp extends DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> {
        FavoriteListComp() {
            super(ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class), new AssignmentRowHandler(), 10);
            setWidth("300px");
            addStyleName(UIConstants.BORDER_LIST);
            setControlStyle("borderlessControl");
        }
    }

    private static class AssignmentRowHandler implements AbstractBeanPagedList.RowDisplayHandler<ProjectGenericTask> {
        @Override
        public Component generateRow(AbstractBeanPagedList host, final ProjectGenericTask item, int rowIndex) {
            final MVerticalLayout layout = new MVerticalLayout().withStyleName(UIConstants.BORDER_LIST_ROW)
                    .withStyleName(UIConstants.CURSOR_POINTER);
            ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(item.getType()).getHtml() + " " + item
                    .getName(), ContentMode.HTML).withWidth("100%").withStyleName(UIConstants.TEXT_ELLIPSIS);
            layout.with(headerLbl);
            layout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                    EventBusFactory.getInstance().post(new ProjectEvent.SelectFavoriteItem(this, item));
                }
            });
            return layout;
        }
    }

    private static class AssignmentReadView extends VerticalLayout {
        void showAssignment(ProjectGenericTask assignment) {
            this.setMargin(new MarginInfo(false, false, false, true));
            removeAllComponents();
            ProjectActivityComponent activityComponent;
            if (ProjectTypeConstants.BUG.equals(assignment.getType())) {
                BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
                SimpleBug bug = bugService.findById(assignment.getTypeId(), AppContext.getAccountId());
                if (bug != null) {
                    ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                            + bug.getSummary(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2, ValoTheme.LABEL_NO_MARGIN);
                    MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl).expand(headerLbl).withFullWidth();
                    addComponent(headerLayout);
                    BugPreviewForm form = new BugPreviewForm();
                    form.setBean(bug);
                    addComponent(form);
                    activityComponent = new ProjectActivityComponent(ProjectTypeConstants.BUG, assignment.getProjectId(),
                            BugFieldFormatter.instance());
                    activityComponent.loadActivities("" + bug.getId());
                    addComponent(activityComponent);
                }
            } else if (ProjectTypeConstants.TASK.equals(assignment.getType())) {
                ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                SimpleTask task = taskService.findById(assignment.getTypeId(), AppContext.getAccountId());
                if (task != null) {
                    ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                            + task.getTaskname(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2, ValoTheme
                            .LABEL_NO_MARGIN);
                    MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl).expand(headerLbl).withFullWidth();
                    addComponent(headerLayout);
                    TaskPreviewForm form = new TaskPreviewForm();
                    form.setBean(task);
                    addComponent(form);
                    activityComponent = new ProjectActivityComponent(ProjectTypeConstants.TASK, assignment
                            .getProjectId(), TaskFieldFormatter.instance());
                    activityComponent.loadActivities("" + task.getId());
                    addComponent(activityComponent);
                }
            } else if (ProjectTypeConstants.MILESTONE.equals(assignment.getType())) {
                MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
                SimpleMilestone milestone = milestoneService.findById(assignment.getTypeId(), AppContext.getAccountId());
                if (milestone != null) {
                    ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                            + milestone.getName(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2, ValoTheme
                            .LABEL_NO_MARGIN);
                    MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl).expand(headerLbl).withFullWidth();
                    addComponent(headerLayout);
                    MilestonePreviewForm form = new MilestonePreviewForm();
                    form.setBean(milestone);
                    addComponent(form);
                    activityComponent = new ProjectActivityComponent(ProjectTypeConstants.MILESTONE, assignment.getProjectId(),
                            MilestoneFieldFormatter.instance());
                    activityComponent.loadActivities("" + milestone.getId());
                    addComponent(activityComponent);
                }
            } else if (ProjectTypeConstants.RISK.equals(assignment.getType())) {
                RiskService riskService = ApplicationContextUtil.getSpringBean(RiskService.class);
                SimpleRisk risk = riskService.findById(assignment.getTypeId(), AppContext.getAccountId());
                if (risk != null) {
                    ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                            + risk.getRiskname(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2, ValoTheme
                            .LABEL_NO_MARGIN);
                    MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl).expand(headerLbl).withFullWidth();
                    addComponent(headerLayout);
                    RiskPreviewForm form = new RiskPreviewForm();
                    form.setBean(risk);
                    addComponent(form);
                    activityComponent = new ProjectActivityComponent(ProjectTypeConstants.RISK, assignment
                            .getProjectId(), RiskFieldFormatter.instance());
                    activityComponent.loadActivities("" + risk.getId());
                    addComponent(activityComponent);
                }
            }
        }
    }
}
