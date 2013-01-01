package com.esofthead.mycollab.vaadin.mvp;

import java.util.Map;
import java.util.WeakHashMap;

public class PresenterResolver {

    private static Map<Class<?>, Object> presenterMap = new WeakHashMap<Class<?>, Object>();

    @SuppressWarnings("unchecked")
    public static <P extends Presenter> P getPresenter(Class<P> presenterClass) {
        P value = (P) presenterMap.get(presenterClass);
        if (value == null) {
            try {
                value = (P) presenterClass.newInstance();
                presenterMap.put(presenterClass, value);
                return value;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return value;
        }
    }

    public static void clearResources() {
        presenterMap.clear();
    }
}
