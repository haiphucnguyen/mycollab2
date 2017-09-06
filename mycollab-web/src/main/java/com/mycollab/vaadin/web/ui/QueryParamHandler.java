package com.mycollab.vaadin.web.ui;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.json.QueryAnalyzer;
import com.mycollab.db.query.SearchFieldInfo;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.shell.events.ShellEvent;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.Page;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.2
 */
public class QueryParamHandler {
    public static ApplicationEventListener<ShellEvent.AddQueryParam> queryParamHandler() {
        return new ApplicationEventListener<ShellEvent.AddQueryParam>() {
            @Subscribe
            @Override
            public void handle(ShellEvent.AddQueryParam event) {
                List<SearchFieldInfo> searchFieldInfos = (List<SearchFieldInfo>) event.getData();
                String query = QueryAnalyzer.toQueryParams(searchFieldInfos);
                String fragment = Page.getCurrent().getUriFragment();
                int index = fragment.indexOf("?");
                if (index > 0) {
                    fragment = fragment.substring(0, index);
                }

                if (StringUtils.isNotBlank(query)) {
                    fragment += "?" + UrlEncodeDecoder.encode(query);
                    Page.getCurrent().setUriFragment(fragment, false);
                }
            }
        };
    }
}
