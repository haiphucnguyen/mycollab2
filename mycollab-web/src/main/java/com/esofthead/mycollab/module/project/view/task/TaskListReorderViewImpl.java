/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.terminal.gwt.client.ui.dd.VerticalDropLocation;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskListReorderViewImpl extends AbstractView implements TaskListReorderView {

    private BeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList> taskLists;
    private Button saveOrderBtn;
    private Set<SimpleTaskList> changeSet = new HashSet<SimpleTaskList>();

    public TaskListReorderViewImpl() {
        super();
        constructHeader();
    }

    private void constructHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setMargin(true);
        header.setSpacing(true);
        header.setWidth("100%");
        Label headerLbl = new Label("All Tasks");
        header.addComponent(headerLbl);
        header.setExpandRatio(headerLbl, 1.0f);

        saveOrderBtn = new Button("Save", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EventBus.getInstance().fireEvent(new TaskListEvent.SaveReoderTaskList(event, changeSet));
            }
        });
        saveOrderBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(saveOrderBtn);
        header.setComponentAlignment(saveOrderBtn, Alignment.MIDDLE_RIGHT);


        Button newTaskListBtn = new Button("Back to task list", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EventBus.getInstance().fireEvent(new TaskListEvent.GotoTaskListScreen(this, null));
            }
        });
        newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        header.addComponent(newTaskListBtn);
        header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

        this.addComponent(header);

        final DDVerticalLayout ddLayout = new DDVerticalLayout();
        ddLayout.setComponentVerticalDropRatio(0.3f);
        ddLayout.setDragMode(LayoutDragMode.CLONE);
        ddLayout.setDropHandler(new DropHandler() {
            @Override
            public AcceptCriterion getAcceptCriterion() {
                return new Not(VerticalLocationIs.MIDDLE);
            }

            @Override
            public void drop(DragAndDropEvent event) {
                LayoutBoundTransferable transferable = (LayoutBoundTransferable) event
                        .getTransferable();

                DDVerticalLayout.VerticalLayoutTargetDetails details = (DDVerticalLayout.VerticalLayoutTargetDetails) event
                        .getTargetDetails();

                TaskListComponent comp = (TaskListComponent) transferable.getComponent();

                int currentIndex = ddLayout.getComponentIndex(comp);
                int newIndex = details.getOverIndex();

                ddLayout.removeComponent(comp);

                if (currentIndex > newIndex
                        && details.getDropLocation() == VerticalDropLocation.BOTTOM) {
                    newIndex++;
                }

                SimpleTaskList dropTaskList = comp.getTaskList();
                dropTaskList.setGroupindex(newIndex);
                changeSet.add(dropTaskList);
                ddLayout.addComponent(comp, newIndex);

                //change affected task list items
                for (int i = 0; i < ddLayout.getComponentCount(); i++) {
                    TaskListComponent affectedComp = (TaskListComponent)ddLayout.getComponent(i);
                    SimpleTaskList affectedTaskList = affectedComp.getTaskList();
                    affectedTaskList.setGroupindex(i);
                    changeSet.add(affectedTaskList);
                }
            }
        });

        taskLists = new BeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList>(null, AppContext.getSpringBean(ProjectTaskListService.class), TaskListRowDisplayHandler.class, ddLayout);
        this.addComponent(taskLists);
    }

    @Override
    public void displayTaskLists() {
        TaskListSearchCriteria criteria = new TaskListSearchCriteria();
        SimpleProject project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
        criteria.setProjectId(new NumberSearchField(project.getId()));
        taskLists.setSearchCriteria(criteria);
    }

    public static class TaskListRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleTaskList> {

        @Override
        public Component generateRow(SimpleTaskList taskList, int rowIndex) {
            return new TaskListComponent(taskList);
        }
    }

    private static class TaskListComponent extends VerticalLayout {

        private SimpleTaskList taskList;

        public TaskListComponent(SimpleTaskList taskList) {
            this.taskList = taskList;
            this.addComponent(new Label(taskList.getName()));
            this.addComponent(new Label("Last updated on " + DateTimeUtils.getStringDateFromNow(taskList.getLastupdatedtime())));
        }

        public SimpleTaskList getTaskList() {
            return taskList;
        }
    }
}
