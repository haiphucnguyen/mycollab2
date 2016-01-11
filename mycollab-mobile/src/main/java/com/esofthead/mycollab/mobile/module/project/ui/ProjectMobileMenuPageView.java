package com.esofthead.mycollab.mobile.module.project.ui;

import com.esofthead.mycollab.mobile.ui.AbstractMobileMenuPageView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.vaadin.thomas.slidemenu.SlideMenu;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class ProjectMobileMenuPageView extends AbstractMobileMenuPageView {
    @Override
    protected void buildNavigateMenu() {
// Just a normal Vaadin button
        final Button close = new Button("close menu");
        close.setWidth(null);
        close.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = -1692006683791129470L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                // Programmatic closing of the menu
                getMenu().close();
            }
        });
        getMenu().addComponent(close);

        // Section labels have a bolded style
        Label l = new Label("Sections:");
        l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
        getMenu().addComponent(l);

        // Buttons with styling (slightly smaller with left-aligned text)
        Button b = new Button("Dashboard");
        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(b);

        b.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = -194718083859615332L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                // TODO automate with the nav listener
                getMenu().close();

                // Only this button actually does something in the menu. Here we
                // navigate to a dummy view.
                getNavigationManager().navigateTo(new NavigationView() {
                    private static final long serialVersionUID = 7226460754270812124L;

                    {
                        setContent(new Label("another view"));
                        setCaption("DashBoard");
                    }
                });
            }
        });

        // add more buttons for a more realistic look.
        b = new Button("Inbox");
        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(b);

        b = new Button("Admin");
        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(b);

        l = new Label("Settings:");
        l.addStyleName(SlideMenu.STYLENAME_SECTIONLABEL);
        getMenu().addComponent(l);

        b = new Button("Options");
        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(b);
        b = new Button("Logout");
        b.addStyleName(SlideMenu.STYLENAME_BUTTON);
        getMenu().addComponent(b);
    }
}
