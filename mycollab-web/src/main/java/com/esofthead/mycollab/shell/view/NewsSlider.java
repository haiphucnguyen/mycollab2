package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.maddon.layouts.MVerticalLayout;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderPanelListener;
import org.vaadin.sliderpanel.client.SliderTabPosition;

import java.net.URL;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
class NewsSlider extends SliderPanel {
    private static Logger LOG = LoggerFactory.getLogger(NewsSlider.class);

    private boolean isDisplay = false;
    private MVerticalLayout content;

    NewsSlider() {
        super(new MVerticalLayout(), SliderMode.RIGHT);
        content = (MVerticalLayout) this.getContent();
        content.withWidth("300px");

        this.addStyleName("right-tool");
        this.setCaption("News");
        this.setTabPosition(SliderTabPosition.MIDDLE);
        this.addListener(new SliderPanelListener() {
            @Override
            public void onToggle(final boolean expand) {
                if (!isDisplay && expand) {
                    isDisplay = true;
                    try {
                        SyndFeedInput input = new SyndFeedInput();
                        SyndFeed feed = input.build(new XmlReader(new URL("https://www.mycollab.com/feed/atom/").openStream()));
                        List<SyndEntry> entries = feed.getEntries();
                        for (SyndEntry entry : entries) {
                            content.with(generateItemBlock(entry));
                        }
                    } catch (Exception e) {
                        LOG.error("Load atom contents failed", e);
                        Label infoLbl = new Label("Can not load news");
                        content.with(infoLbl).withAlign(infoLbl, Alignment.MIDDLE_CENTER);
                    }
                }

                if (expand) {
                    NewsSlider.this.markAsDirty();
                }
            }
        });
    }

    private Label generateItemBlock(SyndEntry entry) {
        A itemLink = new A().setHref(entry.getLink()).setTarget("_blank").appendText(entry.getTitle()).setStyle("font-size:14px; font-weight:bold");

        SyndContent content = entry.getContents().get(0);
        String contentVal = StringUtils.formatRichText(StringUtils.trim(content.getValue(), 200, true));

        Div item = new Div().appendChild(itemLink).appendText(contentVal);
        return new Label(item.write(), ContentMode.HTML);
    }
}
