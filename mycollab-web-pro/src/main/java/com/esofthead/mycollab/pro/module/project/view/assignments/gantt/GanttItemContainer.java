package com.esofthead.mycollab.pro.module.project.view.assignments.gantt;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.esofthead.mycollab.module.project.service.GanttAssignmentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class GanttItemContainer extends BeanItemContainer<GanttItemWrapper> implements Container.Hierarchical {
    private List<GanttItemWrapper> rootItems = new ArrayList<>();

    public GanttItemContainer() {
        super(GanttItemWrapper.class);
    }

    public GanttItemWrapper getItemByGanttIndex(int rowIndex) {
        List items = getAllItemIds();
        for (Object item : items) {
            GanttItemWrapper itemWrapper = (GanttItemWrapper) item;
            if (rowIndex == itemWrapper.getGanttIndex()) {
                return itemWrapper;
            }
        }
        return null;
    }

    @Override
    public BeanItem<GanttItemWrapper> addItem(Object itemId) {
        GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) itemId;
        if (ganttItemWrapper.getParent() == null) {
            rootItems.add(ganttItemWrapper);
        }
        return super.addItem(ganttItemWrapper);
    }

    @Override
    public boolean removeItem(Object itemId) {
        if (itemId instanceof GanttItemWrapper) {
            GanttItemWrapper removedTask = (GanttItemWrapper) itemId;
            removeAssociatesPredecessorsAndDependents(removedTask);
            if (removedTask.getParent() == null) {
                rootItems.remove(removedTask);
            }
            return super.removeItem(itemId);
        } else {
            throw new MyCollabException("Do not support removing type " + itemId);
        }
    }

    private void removeAssociatesPredecessorsAndDependents(GanttItemWrapper removedTask) {
        List items = getAllItemIds();
        for (Object itemId : items) {
            GanttItemWrapper item = (GanttItemWrapper) itemId;
            List<TaskPredecessor> removedPredecessors = new ArrayList<>();

            List<TaskPredecessor> predecessors = item.getPredecessors();
            if (CollectionUtils.isNotEmpty(predecessors)) {
                Iterator<TaskPredecessor> iterator = predecessors.iterator();
                while (iterator.hasNext()) {
                    TaskPredecessor predecessor = iterator.next();
                    if (predecessor.getDescid().intValue() == removedTask.getId().intValue()) {
                        iterator.remove();
                        removedPredecessors.add(predecessor);
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(removedPredecessors)) {
                GanttAssignmentService ganttAssignmentService = ApplicationContextUtil.getSpringBean
                        (GanttAssignmentService.class);
                ganttAssignmentService.massDeletePredecessors(removedPredecessors, AppContext.getAccountId());
            }

            List<TaskPredecessor> dependents = item.getDependents();
            if (CollectionUtils.isNotEmpty(dependents)) {
                Iterator<TaskPredecessor> iterator = predecessors.iterator();
                while (iterator.hasNext()) {
                    TaskPredecessor dependent = iterator.next();
                    if (dependent.getSourceid().intValue() == removedTask.getId().intValue()) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    public boolean hasCircularRelationship(GanttItemWrapper item1, GanttItemWrapper item2) {
        return item1.isAncestor(item2) || item2.isAncestor(item1);
    }

    @Override
    public Collection<?> getChildren(Object o) {
        GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) o;
        return ganttItemWrapper.subTasks();
    }

    @Override
    public Object getParent(Object o) {
        GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) o;
        return ganttItemWrapper.getParent();
    }

    @Override
    public Collection<?> rootItemIds() {
        return rootItems;
    }

    @Override
    public boolean setParent(Object child, Object parent) throws UnsupportedOperationException {
        return true;
    }

    @Override
    public boolean areChildrenAllowed(Object o) {
        GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) o;
        return ganttItemWrapper.hasSubTasks();
    }

    @Override
    public boolean setChildrenAllowed(Object o, boolean b) throws UnsupportedOperationException {
        return b;
    }

    @Override
    public boolean isRoot(Object o) {
        GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) o;
        return ganttItemWrapper.getParent() == null;
    }

    @Override
    public boolean hasChildren(Object o) {
        GanttItemWrapper ganttItemWrapper = (GanttItemWrapper) o;
        return ganttItemWrapper.hasSubTasks();
    }
}
