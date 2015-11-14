/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.web.client.RestTemplate;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class NotPresentedView extends AbstractPageView {
    private static final long serialVersionUID = 1L;

    public NotPresentedView() {
        this.withSpacing(true).withWidth("100%");
        this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        final Label titleIcon = new Label(FontAwesome.EXCLAMATION_CIRCLE.getHtml(), ContentMode.HTML);
        titleIcon.setStyleName("warning-icon");
        titleIcon.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        titleIcon.setWidthUndefined();
        this.with(titleIcon);

        Label label = ELabel.h2(AppContext.getMessage(GenericI18Enum.NOTIFICATION_FEATURE_NOT_AVAILABLE_IN_VERSION)).withWidthUndefined();
        this.with(label).withAlign(label, Alignment.MIDDLE_CENTER);

        Button requestFeatureBtn = new Button("Buy the premium edition");
        requestFeatureBtn.setStyleName(UIConstants.BUTTON_ACTION);
        BrowserWindowOpener opener = new BrowserWindowOpener("https://www.mycollab.com/contact/");
        opener.extend(requestFeatureBtn);
        this.addComponent(requestFeatureBtn);

        RestTemplate restTemplate = new RestTemplate();
        try {
            String result = restTemplate.getForObject("http://127.0.0.1:7070/api/storeweb", String.class);
            Label webPage = new Label(result, ContentMode.HTML);
            webPage.setHeight("600px");
            this.with(new MVerticalLayout(webPage).withMargin(false).withAlign(webPage, Alignment.TOP_CENTER));
        } catch (Exception e) {
            Div informDiv = new Div().appendText("Can not load the store page. You can check the online edition at ")
                    .appendChild(new A("https://www.mycollab.com/pricing/download/", "_blank").appendText("here"));
            ELabel webPage = new ELabel(informDiv.write(), ContentMode.HTML).withWidthUndefined();
            this.with(new MVerticalLayout(webPage).withAlign(webPage, Alignment.TOP_CENTER));
        }
    }
}
