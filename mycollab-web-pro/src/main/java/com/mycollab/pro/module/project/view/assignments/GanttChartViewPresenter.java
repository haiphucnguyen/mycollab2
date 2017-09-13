package com.mycollab.pro.module.project.view.assignments;

import com.google.common.eventbus.Subscribe;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.AssignWithPredecessors;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.domain.TaskPredecessor;
import com.mycollab.module.project.service.GanttAssignmentService;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.view.IGanttChartPresenter;
import com.mycollab.module.project.view.IGanttChartView;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.user.ProjectDashboardContainer;
import com.mycollab.pro.module.project.events.GanttEvent;
import com.mycollab.pro.module.project.view.assignments.gantt.GanttItemWrapper;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class GanttChartViewPresenter extends AbstractPresenter<IGanttChartView> implements IGanttChartPresenter {
    private static final long serialVersionUID = 1L;

    private static Logger LOG = LoggerFactory.getLogger(GanttChartViewPresenter.class);

    private GanttAssignmentService ganttAssignmentService = AppContextUtil.getSpringBean(GanttAssignmentService.class);

    private Set<AssignWithPredecessors> queueSetTasksUpdate;
    private Set<AssignWithPredecessors> queueSetTasksDelete;
    private ApplicationEventListener<GanttEvent.ClearGanttItemsNeedUpdate> massUpdateGanttItemsUpdateHandler = new
            ApplicationEventListener<GanttEvent.ClearGanttItemsNeedUpdate>() {
                @Override
                @Subscribe
                public void handle(GanttEvent.ClearGanttItemsNeedUpdate event) {
                    massUpdateTasksInfoInQueue();
                }
            };

    private ApplicationEventListener<GanttEvent.AddGanttItemUpdateToQueue> addTaskToQueueHandler = new
            ApplicationEventListener<GanttEvent.AddGanttItemUpdateToQueue>() {
                @Override
                @Subscribe
                public void handle(GanttEvent.AddGanttItemUpdateToQueue event) {
                    GanttItemWrapper item = (GanttItemWrapper) event.getData();
                    if (item.getId() == null) {
                        if (item.isTask()) {
                            Task newTask = item.buildNewTask();
                            ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                            taskService.saveWithSession(newTask, UserUIContext.getUsername());
                            item.setId(newTask.getId());
                        } else {
                            LOG.error("Milestone with id is null");
                        }
                        return;
                    }
                    if (!queueSetTasksDelete.contains(item.getTask())) {
                        queueSetTasksUpdate.add(item.getTask());
                    }
                }
            };

    private ApplicationEventListener<GanttEvent.DeleteGanttItemUpdateToQueue> deleteTaskToQueueHandler = new
            ApplicationEventListener<GanttEvent.DeleteGanttItemUpdateToQueue>() {
                @Subscribe
                @Override
                public void handle(GanttEvent.DeleteGanttItemUpdateToQueue event) {
                    GanttItemWrapper item = (GanttItemWrapper) event.getData();
                    if (queueSetTasksUpdate.contains(item.getTask())) {
                        queueSetTasksUpdate.remove(item.getTask());
                    }
                    queueSetTasksDelete.add(item.getTask());
                }
            };

    private ApplicationEventListener<GanttEvent.ModifyPredecessors> predecessorsModifyHandler = new
            ApplicationEventListener<GanttEvent.ModifyPredecessors>() {
                @Override
                @Subscribe
                public void handle(GanttEvent.ModifyPredecessors event) {
                    GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) event.getSource();
                    List<TaskPredecessor> predecessors = (List<TaskPredecessor>) event.getData();
                    ganttItemWrapper.adjustTaskDatesByPredecessors(predecessors);
                    ganttAssignmentService.massUpdatePredecessors(ganttItemWrapper.getId(), predecessors, AppUI.getAccountId());
                    ganttItemWrapper.getTask().setPredecessors(predecessors);
                    ((GanttChartView) view).getTaskTable().refreshRowCache();
                }
            };

    public GanttChartViewPresenter() {
        super(IGanttChartView.class);
    }

    @Override
    protected void viewAttached() {
        queueSetTasksUpdate = new HashSet<>();
        queueSetTasksDelete = new HashSet<>();
        EventBusFactory.getInstance().register(addTaskToQueueHandler);
        EventBusFactory.getInstance().register(massUpdateGanttItemsUpdateHandler);
        EventBusFactory.getInstance().register(predecessorsModifyHandler);
        EventBusFactory.getInstance().register(deleteTaskToQueueHandler);
    }

    @Override
    protected void viewDetached() {
        EventBusFactory.getInstance().unregister(addTaskToQueueHandler);
        EventBusFactory.getInstance().unregister(massUpdateGanttItemsUpdateHandler);
        EventBusFactory.getInstance().unregister(predecessorsModifyHandler);
        EventBusFactory.getInstance().unregister(deleteTaskToQueueHandler);
        massUpdateTasksInfoInQueue();
        massDeleteTasksInQueue();
    }

    private void massUpdateTasksInfoInQueue() {
        if (queueSetTasksUpdate.size() > 0) {
            try {
                ganttAssignmentService.massUpdateGanttItems(new ArrayList<>(queueSetTasksUpdate), AppUI.getAccountId());
            } finally {
                queueSetTasksUpdate.clear();
            }
        }
    }

    private void massDeleteTasksInQueue() {
        if (queueSetTasksDelete.size() > 0) {
            try {
                ganttAssignmentService.massDeleteGanttItems(new ArrayList<>(queueSetTasksDelete), AppUI.getAccountId());
            } finally {
                queueSetTasksDelete.clear();
            }
        }
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.INSTANCE.canReadAssignments()) {
            ProjectDashboardContainer projectDashboardContainer = (ProjectDashboardContainer) container;
            projectDashboardContainer.setContent(view);
            ((GanttChartView) view).lazyLoadView();

            ProjectBreadcrumb breadCrumb = ViewManager.INSTANCE.getCacheComponent(ProjectBreadcrumb.class);
            breadCrumb.gotoGanttView();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }
}

