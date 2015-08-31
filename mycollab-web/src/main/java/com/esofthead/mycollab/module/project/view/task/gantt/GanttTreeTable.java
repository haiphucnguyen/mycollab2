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
package com.esofthead.mycollab.module.project.view.task.gantt;

import com.esofthead.mycollab.configuration.Storage;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.google.common.eventbus.Subscribe;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.joda.time.LocalDate;
import org.vaadin.peter.contextmenu.ContextMenu;

import java.util.*;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class GanttTreeTable extends TreeTable {
    private ProjectTaskService projectTaskService;
    private GanttExt gantt;
    private GanttItemContainer beanContainer;

    private boolean ganttIndexIsChanged = false;
    private String sortField = "createdTime";

    private ApplicationEventListener<TaskEvent.UpdateGanttItemDates> updateTaskInfoHandler = new
            ApplicationEventListener<TaskEvent.UpdateGanttItemDates>() {
                @Subscribe
                @Override
                public void handle(TaskEvent.UpdateGanttItemDates event) {
                    GanttItemWrapper item = (GanttItemWrapper) event.getData();
                    updateTaskTree(item);
                }
            };

    public GanttTreeTable(final GanttExt gantt) {
        super();
        projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
        this.gantt = gantt;
        this.setWidth("800px");
        this.setBuffered(false);
        beanContainer = gantt.getBeanContainer();
        this.setContainerDataSource(beanContainer);
        this.setVisibleColumns("ganttIndex", "name", "startDate", "endDate", "duration", "predecessors",
                "actualStartDate", "actualEndDate", "percentageComplete");
        this.setColumnHeader("ganttIndex", "");
        this.setColumnWidth("ganttIndex", 35);
        this.setColumnHeader("name", "Task");
        this.setColumnExpandRatio("name", 1.0f);
        this.setHierarchyColumn("name");
        this.setColumnHeader("startDate", "Start");
        this.setColumnWidth("startDate", 75);
        this.setColumnHeader("endDate", "End");
        this.setColumnWidth("endDate", 75);
        this.setColumnHeader("duration", "Duration");
        this.setColumnWidth("duration", 80);
        this.setColumnHeader("predecessors", "Predecessors");
        this.setColumnWidth("predecessors", 80);
        this.setColumnHeader("actualStartDate", "Actual Start");
        this.setColumnWidth("actualStartDate", 80);
        this.setColumnHeader("actualEndDate", "Actual End");
        this.setColumnWidth("actualEndDate", 80);
        this.setColumnHeader("percentageComplete", "% Complete");
        this.setColumnWidth("percentageComplete", 80);
        this.setColumnCollapsingAllowed(true);
        this.setColumnCollapsed("actualStartDate", true);
        this.setColumnCollapsed("actualEndDate", true);
        this.setSelectable(true);
        this.setSortEnabled(true);
        this.setEditable(true);

        this.addGeneratedColumn("ganttIndex", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                return new Label("" + item.getGanttIndex());
            }
        });

        this.addGeneratedColumn("name", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                SimpleTask task = item.getTask();

                String taskLinkContent;
                String uid = UUID.randomUUID().toString();
                String taskPriority = task.getPriority();
                Img priorityLink = new Img(taskPriority, ProjectResources.getIconResourceLink12ByTaskPriority
                        (taskPriority)).setTitle(taskPriority);

                String linkName = String.format("[#%d] - %s", task.getTaskkey(), task.getTaskname());
                A taskLink = new A().setId("tag" + uid).appendText(linkName).setStyle("display:inline");

                taskLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(uid, ProjectTypeConstants.TASK, task.getId() + ""));
                taskLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));

                String avatarLink = Storage.getAvatarPath(task.getAssignUserAvatarId(), 16);
                Img avatarImg = new Img(task.getAssignUserFullName(), avatarLink).setTitle(task.getAssignUserFullName());

                Div resultDiv = new DivLessFormatter().appendChild(priorityLink, DivLessFormatter.EMPTY_SPACE(),
                        avatarImg, DivLessFormatter.EMPTY_SPACE(), taskLink, DivLessFormatter.EMPTY_SPACE(),
                        TooltipHelper.buildDivTooltipEnable(uid));
                taskLinkContent = resultDiv.write();

                Label taskLbl = new Label(taskLinkContent, ContentMode.HTML);
                if (task.isCompleted()) {
                    taskLbl.addStyleName("completed");
                } else if (task.isOverdue()) {
                    taskLbl.addStyleName("overdue");
                }
                return taskLbl;
            }
        });

        this.addGeneratedColumn("startDate", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                return new Label(AppContext.formatDate(item.getStartDate().toDate()));
            }
        });

        this.addGeneratedColumn("endDate", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                return new Label(AppContext.formatDate(item.getEndDate().toDate()));
            }
        });

        this.addGeneratedColumn("percentageComplete", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                return new Label(item.getPercentageComplete() + " %");
            }
        });

        this.addGeneratedColumn("predecessors", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                List<TaskPredecessor> predecessors = item.getPredecessors();
                if (predecessors != null && predecessors.size() > 0) {
                    StringBuilder builder = new StringBuilder();
                    for (TaskPredecessor predecessor : predecessors) {
                        builder.append(predecessor.getGanttIndex());
                        builder.append(",");
                    }
                    if (builder.charAt(builder.length() - 1) == ',') {
                        builder.deleteCharAt(builder.length() - 1);
                    }
                    return new Label(builder.toString());
                } else {
                    return new Label();
                }
            }
        });

        this.addGeneratedColumn("actualEndDate", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                return new Label(AppContext.formatDate(item.getActualEndDate()));
            }
        });

        this.addGeneratedColumn("actualStartDate", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                return new Label(AppContext.formatDate(item.getActualStartDate()));
            }
        });

        this.addGeneratedColumn("duration", new ColumnGenerator() {
            @Override
            public Object generateCell(Table table, Object itemId, Object columnId) {
                GanttItemWrapper item = (GanttItemWrapper) itemId;
                double dur = item.getDuration();
                return new Label(dur + " d");
            }
        });

        this.addExpandListener(new Tree.ExpandListener() {
            @Override
            public void nodeExpand(Tree.ExpandEvent expandEvent) {
                GanttItemWrapper item = (GanttItemWrapper) expandEvent.getItemId();
                List<GanttItemWrapper> subTasks = item.subTasks(new SearchCriteria.OrderField(sortField, SearchCriteria.ASC));
                insertSubSteps(item, subTasks);
            }
        });

        this.addCollapseListener(new Tree.CollapseListener() {
            @Override
            public void nodeCollapse(Tree.CollapseEvent collapseEvent) {
                GanttItemWrapper item = (GanttItemWrapper) collapseEvent.getItemId();
                List<GanttItemWrapper> subTasks = item.subTasks(new SearchCriteria.OrderField(sortField, SearchCriteria.ASC));
                removeSubSteps(item, subTasks);
            }
        });

        this.addHeaderClickListener(new HeaderClickListener() {
            @Override
            public void headerClick(HeaderClickEvent event) {
                String propertyId = (String) event.getPropertyId();
                sortField = propertyId;
                beanContainer.sort(new String[]{propertyId}, new boolean[]{true});
                List<GanttItemWrapper> items = beanContainer.getItemIds();
                gantt.removeSteps();
                for (GanttItemWrapper task : items) {
                    gantt.addTask(task);
                }
            }
        });

        final GanttContextMenu contextMenu = new GanttContextMenu();
        contextMenu.setAsContextMenuOf(this);
        contextMenu.setOpenAutomatically(false);

        ContextMenu.ContextMenuOpenedListener.TableListener tableListener = new ContextMenu.ContextMenuOpenedListener.TableListener() {

            public void onContextMenuOpenFromRow(ContextMenu.ContextMenuOpenedOnTableRowEvent event) {
                // read clicked row item and property from event and modify menu
                GanttItemWrapper item = (GanttItemWrapper) event.getItemId();
                contextMenu.displayContextMenu(item);
                contextMenu.open(GanttTreeTable.this);
            }

            public void onContextMenuOpenFromHeader(ContextMenu.ContextMenuOpenedOnTableHeaderEvent event) {
                NotificationUtil.showErrorNotification("Open from header");
            }

            public void onContextMenuOpenFromFooter(ContextMenu.ContextMenuOpenedOnTableFooterEvent event) {
                NotificationUtil.showErrorNotification("Open from footer");
            }
        };

        contextMenu.addContextMenuTableListener(tableListener);
        gantt.setVerticalScrollDelegateTarget(this);
    }

    GanttItemContainer getRawContainer() {
        return beanContainer;
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(updateTaskInfoHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(updateTaskInfoHandler);
        super.detach();
    }

    private void updateTaskTree(GanttItemWrapper ganttItemWrapper) {
        this.markAsDirtyRecursive();
    }


    public void addTask(GanttItemWrapper itemWrapper) {
        if (itemWrapper.getGanttIndex() == null) {
            int ganttIndex = beanContainer.size();
            itemWrapper.setGanttIndex(ganttIndex);
            ganttIndexIsChanged = true;
        }

        beanContainer.addBean(itemWrapper);

        if (itemWrapper.hasSubTasks()) {
            this.setChildrenAllowed(itemWrapper, true);
            this.setCollapsed(itemWrapper, false);
        } else {
            this.setChildrenAllowed(itemWrapper, false);
        }

    }

    private void insertSubSteps(final GanttItemWrapper parent, final List<GanttItemWrapper> childs) {
        final int stepIndex = gantt.getStepIndex(parent.getStep());
        int count = 0;
        if (stepIndex != -1) {
            LocalDate startDate = parent.getStartDate();
            LocalDate endDate = parent.getEndDate();

            for (GanttItemWrapper child : childs) {
                this.addItem(child);
                if (child.getGanttIndex() == null) {
                    int ganttIndex = beanContainer.indexOfId(child);
                    child.setGanttIndex(ganttIndex);
                    ganttIndexIsChanged = true;
                }
                this.setParent(child, parent);
                this.setChildrenAllowed(child, child.hasSubTasks());
                gantt.addTask(stepIndex + count + 1, child);
                count++;
                startDate = DateTimeUtils.min(startDate, child.getStartDate());
                endDate = DateTimeUtils.max(endDate, child.getEndDate());
            }
            parent.setStartAndEndDate(startDate, endDate);
            gantt.markStepDirty(parent.getStep());
        }
    }

    void removeSubSteps(final GanttItemWrapper parent, final List<GanttItemWrapper> childs) {
        final int stepIndex = gantt.getStepIndex(parent.getStep());
        if (stepIndex != -1) {
            for (GanttItemWrapper child : childs) {
                this.removeItem(child);
                gantt.removeStep(child.getStep());
            }
        }
    }

    public void updateWholeGanttIndexes() {
        if (ganttIndexIsChanged) {
            List<GanttItemWrapper> items = beanContainer.getItemIds();
            List<Map<String, Integer>> mapIndexes = new ArrayList<>();
            for (GanttItemWrapper item : items) {
                Map<String, Integer> value = new HashMap<>(2);
                value.put("id", item.getId());
                value.put("index", item.getGanttIndex());
                mapIndexes.add(value);
            }
            projectTaskService.massUpdateGanttIndexes(mapIndexes, AppContext.getAccountId());
        }
    }

    private class GanttContextMenu extends ContextMenu {
        private GanttItemWrapper taskWrapper;

        GanttContextMenu() {
        }

        void displayContextMenu(final GanttItemWrapper taskWrapper) {
            this.removeAllItems();
            ContextMenuItem detailMenuItem = this.addItem("Detail", FontAwesome.BARS);
            detailMenuItem.addItemClickListener(new ContextMenuItemClickListener() {
                @Override
                public void contextMenuItemClicked(ContextMenuItemClickEvent event) {
                    EventBusFactory.getInstance().post(new TaskEvent.GotoRead(GanttTreeTable.this,
                            taskWrapper.getTask().getId()));
                }
            });

            ContextMenuItem predecessorMenuItem = this.addItem("Predecessors", FontAwesome.MAP_MARKER);
            predecessorMenuItem.addItemClickListener(new ContextMenuItemClickListener() {
                @Override
                public void contextMenuItemClicked(ContextMenuItemClickEvent contextMenuItemClickEvent) {
                    UI.getCurrent().addWindow(new PredecessorWindow(GanttTreeTable.this, taskWrapper));
                }
            });
        }
    }
}
