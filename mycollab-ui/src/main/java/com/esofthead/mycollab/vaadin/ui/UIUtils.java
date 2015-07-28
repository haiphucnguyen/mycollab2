package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class UIUtils {
    public static <T> T getRoot(Component container, Class<T> type) {
        HasComponents parent = container.getParent();
        while (parent != null) {
            if (type.isAssignableFrom(parent.getClass())) {
                return (T) parent;
            } else {
                parent = parent.getParent();
            }
        }
        return null;
    }
}
