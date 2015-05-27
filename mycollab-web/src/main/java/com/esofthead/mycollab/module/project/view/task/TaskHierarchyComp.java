/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
class TaskHierarchyComp extends TreeTable {
    TaskHierarchyComp() {
        super();
        this.addContainerProperty("name", String.class, "");
        this.addGeneratedColumn("name", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                return new Label(item.getName());
            }
        });

        this.addExpandListener(new Tree.ExpandListener() {
            @Override
            public void nodeExpand(Tree.ExpandEvent expandEvent) {
                GanttItemWrapper item = (GanttItemWrapper) expandEvent.getItemId();
                List<GanttItemWrapper> subTasks = item.subTasks();
                if (subTasks.size() > 0) {
                    for (GanttItemWrapper subTask: subTasks) {
                        TaskHierarchyComp.this.addItem(subTask);
                        TaskHierarchyComp.this.setParent(subTask, item);
                    }
                }
            }
        });
    }

    void addTaskList(SimpleTaskList taskList) {
        this.addItem(new TaskListGanttItemWrapper(taskList));
    }

    void addTask(SimpleTask task) {
        this.addItem(new TaskGanttItemWrapper(task));
    }

    interface GanttItemWrapper {
        String getName();

        List<GanttItemWrapper> subTasks();
    }

    static class TaskGanttItemWrapper implements GanttItemWrapper {
        private ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        private SimpleTask task;

        TaskGanttItemWrapper(SimpleTask task) {
            this.task = task;
        }

        @Override
        public String getName() {
            return task.getTaskname();
        }

        @Override
        public List<GanttItemWrapper> subTasks() {
            List<SimpleTask> subTasks = projectTaskService.findSubTasks(task.getId(), AppContext.getAccountId());
            List<GanttItemWrapper> subItems = new ArrayList<>();
            for (SimpleTask subTask: subTasks) {
                subItems.add(new TaskGanttItemWrapper(subTask));
            }
            return subItems;
        }
    }

    static class TaskListGanttItemWrapper implements GanttItemWrapper {
        private ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        private SimpleTaskList taskList;

        TaskListGanttItemWrapper(SimpleTaskList taskList) {
            this.taskList = taskList;
        }

        @Override
        public String getName() {
            return taskList.getName();
        }

        @Override
        public List<GanttItemWrapper> subTasks() {
            List<SimpleTask> subTasks = projectTaskService.findSubTasksOfGroup(taskList.getId(), AppContext.getAccountId());
            List<GanttItemWrapper> subItems = new ArrayList<>();
            for (SimpleTask subTask: subTasks) {
                subItems.add(new TaskGanttItemWrapper(subTask));
            }
            return subItems;
        }
    }
}
