package com.esofthead.mycollab.vaadin.events;

public interface PreviewFormHandlers<T> {

    void gotoNext(T data);

    void gotoPrevious(T data);
    
    void onAssign(T data);

    void onEdit(T data);

    void onDelete(T data);

    void onClone(T data);

    void onCancel();
}
