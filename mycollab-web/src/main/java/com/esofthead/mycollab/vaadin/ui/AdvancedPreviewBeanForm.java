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
package com.esofthead.mycollab.vaadin.ui;

import java.util.HashSet;
import java.util.Set;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;

public class AdvancedPreviewBeanForm<T> extends GenericForm implements
        HasPreviewFormHandlers<T> {

    private static final long serialVersionUID = 1L;
    private Set<PreviewFormHandlers<T>> handlers;

    @Override
    public void addFormHandler(PreviewFormHandlers<T> handler) {
        if (handlers == null) {
            handlers = new HashSet<PreviewFormHandlers<T>>();
        }

        handlers.add(handler);
    }
    
    protected void fireAssignForm(T bean) {
        if (handlers != null) {
            for (PreviewFormHandlers<T> handler : handlers) {
                handler.onAssign(bean);
            }
        }
    }

    protected void fireEditForm(T bean) {
        if (handlers != null) {
            for (PreviewFormHandlers<T> handler : handlers) {
                handler.onEdit(bean);
            }
        }
    }
    
    protected void doPrint() {
        throw new MyCollabException("This method must be override by sub classes");
    }

    protected void showHistory() {
        throw new MyCollabException("This method must be override by sub classes");
    }

    protected void fireCancelForm(T bean) {
        if (handlers != null) {
            for (PreviewFormHandlers<T> handler : handlers) {
                handler.onCancel();
            }
        }
    }

    protected void fireDeleteForm(T bean) {
        if (handlers != null) {
            for (PreviewFormHandlers<T> handler : handlers) {
                handler.onDelete(bean);
            }
        }
    }

    protected void fireCloneForm(T bean) {
        if (handlers != null) {
            for (PreviewFormHandlers<T> handler : handlers) {
                handler.onClone(bean);
            }
        }
    }

    protected void fireGotoNextItem(T bean) {
        if (handlers != null) {
            for (PreviewFormHandlers<T> handler : handlers) {
                handler.gotoNext(bean);
            }
        }
    }

    protected void fireGotoPrevious(T bean) {
        if (handlers != null) {
            for (PreviewFormHandlers<T> handler : handlers) {
                handler.gotoPrevious(bean);
            }
        }
    }
}
