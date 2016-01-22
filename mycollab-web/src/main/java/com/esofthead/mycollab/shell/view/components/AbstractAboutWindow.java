package com.esofthead.mycollab.shell.view.components;

import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public abstract class AbstractAboutWindow extends Window implements CacheableComponent {
    public AbstractAboutWindow(String caption) {
        super(caption);
    }
}
