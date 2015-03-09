package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 5.0.2
 */
public interface GoogleAnalyticsService {
    void registerUI(UI ui);

    void trackPageView(String pageId);
}