package com.esofthead.vaadin.mobilecomponent.demo;

import javax.servlet.annotation.WebServlet;

import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;
import com.esofthead.vaadin.mobilecomponent.MobileNavigationView;
import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mobilecomponent")
@Title("MobileNavigationManager Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "com.esofthead.vaadin.mobilecomponent.WidgetSet")
    public static class Servlet extends TouchKitServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final MobileNavigationManager root = new MobileNavigationManager();

        VerticalLayout mainNav = new VerticalLayout();
        mainNav.addComponent(new Label("This is main navigation"));
        mainNav.setHeight("1000px");
        root.setNavigationMenu(mainNav);
        root.setMaintainBreadcrumb(false);

        // Show it in the middle of the screen
        final MobileNavigationView layout = new MobileNavigationView();
        VerticalLayout content = new VerticalLayout();
        content.setStyleName("demoContentLayout");
        content.setSizeFull();
        content.addComponent(new Label("This is main content"));
        layout.setContent(content);
        layout.setToggleButton(true);
        layout.setSizeFull();

        final MobileNavigationView newView = new MobileNavigationView();
        VerticalLayout content2 = new VerticalLayout();
        content2.setStyleName("demoContentLayout2");
        content2.setSizeFull();
        content2.addComponent(new Label("Another view"));
        newView.setContent(content2);
        newView.setToggleButton(true);
        newView.setSizeFull();
        newView.setRightComponent(new Button("Back", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				root.navigateTo(layout);
			}
		}));

        layout.setRightComponent(new Button("Next", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                root.navigateTo(newView);
            }
        }));

        root.navigateTo(layout);

        setContent(root);

    }

}
