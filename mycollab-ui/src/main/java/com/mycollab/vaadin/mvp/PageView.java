package com.mycollab.vaadin.mvp;

import com.vaadin.ui.HasComponents;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.EventListener;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface PageView extends HasComponents, CacheableComponent {

    <E> void addViewListener(ViewListener<E> listener);

    interface ViewListener<E> extends EventListener, Serializable {
        Method viewInitMethod = ReflectTools.findMethod(ViewListener.class, "receiveEvent", ViewEvent.class);

        void receiveEvent(ViewEvent<E> event);
    }
}
