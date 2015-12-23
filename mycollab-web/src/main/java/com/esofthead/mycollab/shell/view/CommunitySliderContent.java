package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.AsyncInvoker;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
public class CommunitySliderContent extends MVerticalLayout {
    private static final Logger LOG = LoggerFactory.getLogger(CommunitySliderContent.class);

    CommunitySliderContent() {
        try {
            withWidth("500px").withStyleName("community");
            Div blogLink = new Div().appendText(FontAwesome.INSTITUTION.getHtml() + " ").appendChild(new A("https://www" +
                    ".mycollab.com").appendText("Blog").setTarget("_blank"));
            with(new ELabel(blogLink.write(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2));
            SyndFeed feed = getSyndFeedForUrl("https://www.mycollab.com/feed/atom/");
            final List<SyndEntry> entries = feed.getEntries();
            if (CollectionUtils.isNotEmpty(entries)) {
                AsyncInvoker.access(new AsyncInvoker.PageCommand() {
                    @Override
                    public void run() {
                        for (SyndEntry entry : entries) {
                            Div link = new Div().appendText(FontAwesome.INBOX.getHtml() + " ").appendChild(new A(entry
                                    .getLink()).appendText(entry.getTitle()).setTarget("_blank"));
                            CommunitySliderContent.this.with(new ELabel(link.write(), ContentMode.HTML).withStyleName("title"));
                            Div description = new Div().appendText(entry.getDescription().getValue());
                            CommunitySliderContent.this.with(new ELabel(description.write(), ContentMode.HTML).withStyleName("desc"));
                        }
                    }
                });
            }

        } catch (Exception e) {
            with(new Label("Can not load MyCollab's feed"));
        }
    }

    public SyndFeed getSyndFeedForUrl(String url) throws MalformedURLException, IOException, IllegalArgumentException, FeedException {

        SyndFeed feed = null;
        InputStream is = null;

        try {

            URLConnection openConnection = new URL(url).openConnection();
            is = new URL(url).openConnection().getInputStream();
            if ("gzip".equals(openConnection.getContentEncoding())) {
                is = new GZIPInputStream(is);
            }
            InputSource source = new InputSource(is);
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(source);

        } catch (Exception e) {
            LOG.error("Exception occured when building the feed object out of the url", e);
        } finally {
            if (is != null) is.close();
        }

        return feed;
    }
}
