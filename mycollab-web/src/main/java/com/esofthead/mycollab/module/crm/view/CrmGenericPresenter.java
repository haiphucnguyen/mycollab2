package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.ui.ComponentContainer;

public class CrmGenericPresenter<V extends View> extends AbstractPresenter<V> {

    private static final long serialVersionUID = 1L;

    public CrmGenericPresenter(Class<V> viewClass) {
        super(viewClass);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        CrmModule crmModule = (CrmModule) container;
        crmModule.addView(view);
    }
}
