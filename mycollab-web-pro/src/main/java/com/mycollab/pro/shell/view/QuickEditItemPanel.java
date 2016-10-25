package com.mycollab.pro.shell.view;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.server.FontAwesome;
import org.joda.time.DateTime;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderPanelListener;
import org.vaadin.sliderpanel.client.SliderTabPosition;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.4.4
 */
public class QuickEditItemPanel {
    public static SliderPanel buildSliderPanel() {
        final SliderContent sliderContent = new SliderContent();
        SliderPanel sliderPanel = new SliderPanelBuilder(sliderContent).caption("Community")
                .flowInContent(true).mode(SliderMode.RIGHT).tabPosition(SliderTabPosition.MIDDLE).build();
        sliderPanel.addListener(new SliderPanelListener() {
            @Override
            public void onToggle(boolean b) {
                if (b) {

                }
            }
        });
        return sliderPanel;
    }

    private static class SliderContent extends MVerticalLayout {
        private MVerticalLayout content;
        private DateTime lastTimeAccess;

        SliderContent() {
            withWidth("500px").withStyleName("community");
            Div blogLink = new Div().appendText(FontAwesome.INSTITUTION.getHtml() + " ").appendChild(new A("https://www" +
                    ".mycollab.com").appendText("Blog").setTarget("_blank"));
            with(ELabel.h2(blogLink.write()));
            content = new MVerticalLayout(new ELabel("AA"));
            with(content);
        }
    }
}
