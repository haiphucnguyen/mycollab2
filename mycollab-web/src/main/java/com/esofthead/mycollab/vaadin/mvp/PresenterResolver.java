package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.web.AppContext;
import java.util.Map;
import java.util.WeakHashMap;

public class PresenterResolver {

    private static final String PRESENTER_VAL = "presenterMap";

    @SuppressWarnings("unchecked")
    public static <P extends Presenter> P getPresenter(Class<P> presenterClass) {
        Map<Class<?>, Object> presenterMap = (Map<Class<?>, Object>)AppContext.getVariable(PRESENTER_VAL);
        if (presenterMap == null) {
            presenterMap = new WeakHashMap<Class<?>, Object>();
            AppContext.putVariable(PRESENTER_VAL, presenterMap);
        }
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
        Map<Class<?>, Object> presenterMap = (Map<Class<?>, Object>)AppContext.getVariable(PRESENTER_VAL);
        if (presenterMap != null) {
           presenterMap.clear(); 
        }
    }
}
