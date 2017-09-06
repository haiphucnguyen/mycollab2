package com.mycollab.vaadin.mvp;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.SingleComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.4.5
 */
public class AbstractSingleContainerPageView extends CustomComponent implements PageView, SingleComponentContainer {

    private CssLayout contentLayout;

    public AbstractSingleContainerPageView() {
        contentLayout = new CssLayout();
        contentLayout.setSizeFull();
        setCompositionRoot(contentLayout);
        setSizeFull();
    }

    @Override
    public Component getContent() {
        return contentLayout.getComponent(0);
    }

    @Override
    public void setContent(Component component) {
        contentLayout.removeAllComponents();
        contentLayout.addComponent(component);
    }

    @Override
    public void addComponentAttachListener(ComponentAttachListener componentAttachListener) {
        contentLayout.addComponentAttachListener(componentAttachListener);
    }

    @Override
    public void removeComponentAttachListener(ComponentAttachListener componentAttachListener) {

    }

    @Override
    public void addComponentDetachListener(ComponentDetachListener componentDetachListener) {

    }

    @Override
    public void removeComponentDetachListener(ComponentDetachListener componentDetachListener) {

    }

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }
}
