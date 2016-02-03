package com.esofthead.mycollab.pro.module.file.view;

import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public interface OauthWindowFactory extends CacheableComponent {
    Window newDropBoxAuthWindow();
}
