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
package com.esofthead.mycollab.web;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import org.springframework.web.client.RestTemplate;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class AdWindow extends Window {
    public AdWindow() {
        super("Buy MyCollab Pro edition");
        this.setWidth("900px");
        this.setModal(true);
        this.setResizable(false);
        RestTemplate restTemplate = new RestTemplate();
        try {
            String result = restTemplate.getForObject("http://127.0.0.1:7070/api/storeweb", String.class);
            Label webPage = new Label(result, ContentMode.HTML);
            this.setContent(new CssLayout(webPage));
        } catch (Exception e) {
            Div informDiv = new Div().appendText("Can not load the store page. You can check the online edition at ")
                    .appendChild(new A("https://www.mycollab.com/pricing/download/", "_blank").appendText("here"));
            Label webPage = new Label(informDiv.write(), ContentMode.HTML);
            this.setContent(new CssLayout(webPage));
        }
    }
}
