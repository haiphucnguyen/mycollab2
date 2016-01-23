package com.esofthead.mycollab.vaadin;

import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class AbstractLicenseActivationWindow extends Window implements CacheableComponent {
    public AbstractLicenseActivationWindow() {
        super("Activate MyCollab");
    }
}
