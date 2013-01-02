package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import java.util.HashSet;
import java.util.Set;

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
}
