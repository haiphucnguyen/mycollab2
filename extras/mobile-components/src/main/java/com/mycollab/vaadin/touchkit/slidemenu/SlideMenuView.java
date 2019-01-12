package com.mycollab.vaadin.touchkit.slidemenu;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.vaadin.touchkit.ui.NavigationManager.NavigationEvent;
import org.vaadin.touchkit.ui.NavigationManager.NavigationListener;
import org.vaadin.touchkit.ui.NavigationView;

/**
 * An enhanced {@link NavigationView} with button to open the {@link SlideMenu}.
 * <p>
 * Button will automatically be hidden on navigation to sub-views (displaying
 * the normal back-link instead).
 *
 * @author thomas
 */
public class SlideMenuView extends NavigationView implements NavigationListener {

    private static final long serialVersionUID = 3952898936850021537L;

    protected SlideMenu menu = new SlideMenu();
    protected Button menuButton;

    public SlideMenuView() {

        menuButton = new Button();
        menuButton.setIcon(VaadinIcons.ALIGN_JUSTIFY);
        menuButton.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 6014031942527721065L;

            @Override
            public void buttonClick(ClickEvent event) {
                menu.open();
            }
        });

        getNavigationBar().setLeftComponent(menuButton);

    }

    @Override
    public void attach() {
        super.attach();
        if (getNavigationManager() != null) {
            getNavigationManager().addNavigationListener(this);
        }
    }

    public SlideMenu getMenu() {
        return menu;
    }

    /**
     * Change the icon for the menu button. Default is {@link VaadinIcons#ALIGN_JUSTIFY}
     *
     * @param icon
     */
    public void setMenuIcon(Resource icon) {
        menuButton.setIcon(icon);
    }

    @Override
    public void navigate(NavigationEvent event) {

        // When navigating, do one of two things:

        if (getNavigationManager().getCurrentComponent().equals(this)) {
            // 1. if navigating back to this view, re-add the menu button to top
            // left corner
            setLeftComponent(menuButton);
        } else {
            // 2. if navigating away, make sure the menu is closed
            menu.close();
        }

    }
}
