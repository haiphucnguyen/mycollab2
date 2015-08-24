package com.esofthead.mycollab.vaadin.ui.service;

import com.esofthead.mycollab.vaadin.ui.MyCollabSession;
import com.esofthead.mycollab.vaadin.ui.service.GoogleAnalyticsService;
import com.vaadin.ui.UI;
import org.springframework.stereotype.Service;
import org.vaadin.googleanalytics.tracking.GoogleAnalyticsTracker;

/**
 * @author MyCollab Ltd.
 * @since 5.0.2
 */
@Service
public class GoogleAnalyticsServiceImpl implements GoogleAnalyticsService {

    @Override
    public void registerUI(UI ui) {
        GoogleAnalyticsTracker tracker = new GoogleAnalyticsTracker("UA-46116533-1", "mycollab.com");
        tracker.extend(ui);
        MyCollabSession.putVariable("tracker", tracker);
    }

    @Override
    public void trackPageView(String pageId) {
        GoogleAnalyticsTracker tracker = (GoogleAnalyticsTracker) MyCollabSession.getVariable("tracker");
        if (tracker != null) {
            tracker.trackPageview(pageId);
        }
    }
}