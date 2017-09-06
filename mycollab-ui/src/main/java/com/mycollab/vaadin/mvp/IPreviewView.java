package com.mycollab.vaadin.mvp;

/**
 * @param <T>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface IPreviewView<T> extends PageView {
    void previewItem(T item);

    T getItem();
}
