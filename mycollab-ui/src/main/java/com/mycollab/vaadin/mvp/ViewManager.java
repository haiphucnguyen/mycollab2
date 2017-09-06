package com.mycollab.vaadin.mvp;

import com.mycollab.core.MyCollabException;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.mvp.service.ComponentScannerService;
import com.mycollab.vaadin.ui.MyCollabSession;

import java.util.HashMap;
import java.util.Map;

import static com.mycollab.vaadin.ui.MyCollabSession.VIEW_MANAGER_VAL;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ViewManager {

    public static <T extends CacheableComponent> T getCacheComponent(final Class<T> viewClass) {
        Map<Class<?>, Object> viewMap = (Map<Class<?>, Object>) MyCollabSession.getCurrentUIVariable(VIEW_MANAGER_VAL);
        if (viewMap == null) {
            viewMap = new HashMap<>();
            MyCollabSession.putCurrentUIVariable(VIEW_MANAGER_VAL, viewMap);
        }

        try {
            LoadPolicy policy = viewClass.getAnnotation(LoadPolicy.class);
            if (policy != null && policy.scope() == ViewScope.PROTOTYPE) {
                return createInstanceFromCls(viewClass);
            }
            T value = (T) viewMap.get(viewClass);
            if (value == null) {
                value = createInstanceFromCls(viewClass);
                viewMap.put(viewClass, value);
                return value;
            } else {
                return value;
            }
        } catch (Exception e) {
            throw new MyCollabException("Can not create view instance of class: " + viewClass, e);
        }
    }

    private static <T> T createInstanceFromCls(Class<T> viewClass) throws IllegalAccessException, InstantiationException {
        ComponentScannerService componentScannerService = AppContextUtil.getSpringBean(ComponentScannerService.class);
        Class<?> implCls = componentScannerService.getViewImplCls(viewClass);
        if (implCls != null) {
            return (T) implCls.newInstance();
        }
        throw new MyCollabException("Can not find the implementation class for view " + viewClass);
    }
}
