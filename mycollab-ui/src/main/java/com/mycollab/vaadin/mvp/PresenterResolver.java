package com.mycollab.vaadin.mvp;

import com.mycollab.core.MyCollabException;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.mvp.service.ComponentScannerService;
import com.mycollab.vaadin.ui.MyCollabSession;

import java.util.Map;
import java.util.WeakHashMap;

import static com.mycollab.vaadin.ui.MyCollabSession.PRESENTER_VAL;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public final class PresenterResolver {

    public static <P extends IPresenter> P getPresenter(Class<P> presenterClass) {
        Map<Class<?>, Object> presenterMap = (Map<Class<?>, Object>) MyCollabSession.getCurrentUIVariable(PRESENTER_VAL);
        if (presenterMap == null) {
            presenterMap = new WeakHashMap<>();
            MyCollabSession.putCurrentUIVariable(PRESENTER_VAL, presenterMap);
        }

        P value = (P) presenterMap.get(presenterClass);
        if (value == null) {
            value = initPresenter(presenterClass);
            presenterMap.put(presenterClass, value);
            return value;
        } else {
            LoadPolicy policy = presenterClass.getAnnotation(LoadPolicy.class);
            if (policy != null && policy.scope() == ViewScope.PROTOTYPE) {
                value = initPresenter(presenterClass);
                presenterMap.put(presenterClass, value);
            }
            return value;
        }
    }

    public static <P extends IPresenter> P getPresenterAndInitView(Class<P> presenterClass) {
        P presenter = getPresenter(presenterClass);
        presenter.getView();
        return presenter;
    }

    private static <P extends IPresenter> P initPresenter(Class<P> presenterClass) {
        P value = null;
        try {
            if (!presenterClass.isInterface()) {
                value = presenterClass.newInstance();
            } else {
                ComponentScannerService componentScannerService = AppContextUtil.getSpringBean
                        (ComponentScannerService.class);
                Class presenterClassImpl = componentScannerService.getPresenterImplCls(presenterClass);
                if (presenterClassImpl != null) {
                    value = (P) presenterClassImpl.newInstance();
                }
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        }
        if (value != null) {
            return value;
        } else {
            throw new PresenterNotFoundException("Can not find instance of " + presenterClass);
        }
    }
}
