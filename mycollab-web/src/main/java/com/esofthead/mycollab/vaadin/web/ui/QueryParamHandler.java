package com.esofthead.mycollab.vaadin.web.ui;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.json.QueryAnalyzer;
import com.esofthead.mycollab.core.db.query.SearchFieldInfo;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.shell.events.ShellEvent;
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
