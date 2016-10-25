package com.mycollab.module.project.view.ticket;

import com.google.common.eventbus.Subscribe;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.vaadin.ui.UIUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import org.vaadin.sliderpanel.SliderPanel;
import org.vaadin.sliderpanel.SliderPanelBuilder;
import org.vaadin.sliderpanel.client.SliderMode;
import org.vaadin.sliderpanel.client.SliderTabPosition;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.4.4
 */
class TicketSliderPanel extends SliderPanel {
    TicketSliderPanel() {
        super(new SliderPanelBuilder(new Content())
                .flowInContent(true).mode(SliderMode.RIGHT).tabPosition(SliderTabPosition.END));
    }

    private ApplicationEventListener<TicketEvent.EditTicketInstantly> editHandler = new
            ApplicationEventListener<TicketEvent.EditTicketInstantly>() {
                @Override
                @Subscribe
                public void handle(TicketEvent.EditTicketInstantly event) {
                    TicketSliderPanel.this.scheduleExpand(0);
                }
            };

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(editHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(editHandler);
        super.detach();
    }

    private static class Content extends MVerticalLayout {
        Content() {
            super();
            withWidth("800px");
            MButton closeBtn = new MButton("", clickEvent -> {
                TicketSliderPanel root = UIUtils.getRoot(Content.this, TicketSliderPanel.class);
                root.toogle();
            }).withIcon(FontAwesome.CLOSE);
            with(closeBtn);
        }
    }
}
