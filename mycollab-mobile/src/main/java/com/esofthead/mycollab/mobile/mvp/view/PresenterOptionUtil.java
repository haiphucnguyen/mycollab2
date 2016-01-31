package com.esofthead.mycollab.mobile.mvp.view;

import com.esofthead.mycollab.vaadin.mvp.IPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterNotFoundException;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class PresenterOptionUtil {
    public static IPresenter getPresenter(Class presenterClass) {
        try {
            IPresenter presenter = PresenterResolver.getPresenter(presenterClass);
            return presenter;
        } catch (PresenterNotFoundException e) {
            return new NotPresentPresenter();
        }
    }
}
