package com.esofthead.mycollab.uicomponents;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.string.Strings;

/**
 * User: Nghi Tran
 * Date: 12/6/13
 * Time: 10:47 AM
 */

public class ChatIFrame extends WebMarkupContainer {
    private String srcUrl;

    public ChatIFrame(String id, String url) {
        super(id);
        srcUrl = url;
    }

    public void setUrl(String url) {
        srcUrl = url;
    }

    public String getUrl() {
        return srcUrl;
    }

    @Override
    protected final void onComponentTag(final ComponentTag tag){
        checkComponentTag(tag, "iframe");

        String url = getUrl();

        tag.put("src", Strings.replaceAll(url, "&", "&amp;"));

        super.onComponentTag(tag);
    }
}
