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
