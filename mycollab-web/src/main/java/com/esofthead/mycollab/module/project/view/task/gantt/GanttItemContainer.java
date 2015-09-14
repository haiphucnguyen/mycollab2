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

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.vaadin.data.util.BeanItemContainer;
import org.apache.commons.collections.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class GanttItemContainer extends BeanItemContainer<GanttItemWrapper> {

    public GanttItemContainer() {
        super(GanttItemWrapper.class);
    }

    public GanttItemWrapper getItemByGanttIndex(int rowIndex) {
        List<GanttItemWrapper> items = getItemIds();
        for (GanttItemWrapper item : items) {
            if (rowIndex == item.getGanttIndex()) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean removeItem(Object itemId) {
        if (itemId instanceof GanttItemWrapper) {
            GanttItemWrapper removedTask = (GanttItemWrapper) itemId;
            removeAssociatesPredecessorsAndDependents(removedTask);
            return super.removeItem(itemId);
        } else {
            throw new MyCollabException("Do not support removing type " + itemId);
        }
    }

    private void removeAssociatesPredecessorsAndDependents(GanttItemWrapper removedTask) {
        List<GanttItemWrapper> items = getItemIds();
        for (GanttItemWrapper item : items) {
            List<TaskPredecessor> predecessors = item.getPredecessors();
            if (CollectionUtils.isNotEmpty(predecessors)) {
                Iterator<TaskPredecessor> iterator = predecessors.iterator();
                while (iterator.hasNext()) {
                    TaskPredecessor predecessor = iterator.next();
                    if (predecessor.getSourceid() == removedTask.getId()) {
                        iterator.remove();
                    }
                }
            }

            List<TaskPredecessor> dependents = item.getDependents();
            if (CollectionUtils.isNotEmpty(dependents)) {
                for (TaskPredecessor dependent : dependents) {

                }
            }
        }
    }
}
