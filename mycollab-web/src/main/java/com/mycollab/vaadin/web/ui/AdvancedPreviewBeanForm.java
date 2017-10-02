package com.mycollab.vaadin.web.ui;

import com.mycollab.vaadin.event.HasPreviewFormHandlers;
import com.mycollab.vaadin.event.PreviewFormHandler;
import com.mycollab.vaadin.ui.GenericBeanForm;

import java.util.HashSet;
import java.util.Set;

/**
 * @param <B>
 * @author MyCollab Ltd
 * @since 1.0
 */
public class AdvancedPreviewBeanForm<B> extends GenericBeanForm<B> implements HasPreviewFormHandlers<B> {
    private static final long serialVersionUID = 1L;

    private Set<PreviewFormHandler<B>> handlers;

    @Override
    public void addFormHandler(PreviewFormHandler<B> handler) {
        if (handlers == null) {
            handlers = new HashSet<>();
        }

        handlers.add(handler);
    }

    public void fireAssignForm(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onAssign(bean);
            }
        }
    }

    public void fireEditForm(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onEdit(bean);
            }
        }
    }

    public void fireAddForm(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onAdd(bean);
            }
        }
    }

    public void fireCancelForm(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onCancel();
            }
        }
    }

    public void fireDeleteForm(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onDelete(bean);
            }
        }
    }

    public void firePrintForm(Object source, B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onPrint(source, bean);
            }
        }
    }

    public void fireCloneForm(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onClone(bean);
            }
        }
    }

    public void fireGotoNextItem(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.gotoNext(bean);
            }
        }
    }

    public void fireGotoPrevious(B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.gotoPrevious(bean);
            }
        }
    }

    public void fireExtraAction(String action, B bean) {
        if (handlers != null) {
            for (PreviewFormHandler<B> handler : handlers) {
                handler.onExtraAction(action, bean);
            }
        }
    }
}
