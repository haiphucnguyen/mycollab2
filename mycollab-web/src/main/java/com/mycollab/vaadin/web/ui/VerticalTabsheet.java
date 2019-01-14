/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.web.ui;

import com.mycollab.common.i18n.ShellI18nEnum;
import com.mycollab.core.MyCollabException;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class VerticalTabsheet extends CustomComponent {
    private static final long serialVersionUID = 1L;

    private static final String MAX_SIZE = "220px";
    private static final String MIN_SIZE = "65px";

    private static final String TABSHEET_STYLE = "vertical-tabsheet";
    private static final String TAB_STYLE = "tab";
    private static final String TAB_SELECTED_STYLE = "tab-selected";

    private VerticalLayout navigatorContainer;
    private MCssLayout navigatorWrapper;

    private MCssLayout contentWrapper;

    private Map<String, ButtonTab> compMap = new HashMap<>();

    private Component selectedButton = null;
    private ButtonTab selectedComp = null;
    private Button toggleBtn;
    private Boolean retainVisibility = true;

    public VerticalTabsheet() {
        navigatorWrapper = new MCssLayout().withStyleName("navigator-wrap", "content-height");

        navigatorContainer = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(true, false, true, false));
        navigatorWrapper.addComponent(navigatorContainer);

        contentWrapper = new MCssLayout().withStyleName("container-wrap").withFullSize();

        this.setCompositionRoot(new MCssLayout(navigatorWrapper, contentWrapper).withFullWidth());
        this.setStyleName(TABSHEET_STYLE);
    }

    private void hideTabsCaption() {
        navigatorContainer.forEach(container -> ((ButtonTab) container).hideCaption());
    }

    private void showTabsCaption() {
        navigatorContainer.forEach(container -> ((ButtonTab) container).showCaption());
    }

    public void addTab(Component component, String id, String caption) {
        addTab(component, id, 0, caption, null, null);
    }

    public void addTab(Component component, String id, String caption, Resource resource) {
        addTab(component, id, 0, caption, null, resource);
    }

    public void addTab(Component component, String id, int level, String caption, String link, Resource resource) {
        if (!hasTab(id)) {
            final ButtonTab button = new ButtonTab(id, level, caption, component, link);

            button.addClickListener(clickEvent -> {
                if (!clickEvent.isCtrlKey() && !clickEvent.isMetaKey()) {
                    if (selectedButton != button) {
                        clearTabSelection();
                        selectedButton = button;
                        selectedButton.addStyleName(TAB_SELECTED_STYLE);
                        selectedComp = compMap.get(button.getTabId());
                    }
                    fireTabChangeEvent(new SelectedTabChangeEvent(VerticalTabsheet.this, true));
                } else {
                    Page.getCurrent().open(button.link, "_blank", false);
                }
            });

            button.setIcon(resource);
            button.withStyleName(TAB_STYLE, UIConstants.TEXT_ELLIPSIS).withFullWidth();

            if (button.getLevel() > 0) {
                int insertIndex = 0;
                for (int i = 0; i < navigatorContainer.getComponentCount(); i++) {
                    ButtonTab buttonTmp = (ButtonTab) navigatorContainer.getComponent(i);
                    if (buttonTmp.getLevel() > level) {
                        break;
                    } else {
                        insertIndex++;
                    }
                }
                navigatorContainer.addComponent(button, insertIndex);
                navigatorContainer.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
            } else {
                navigatorContainer.addComponent(button);
                navigatorContainer.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
            }

            compMap.put(id, button);
        }
    }

    private boolean hasTab(String viewId) {
        return compMap.containsKey(viewId);
    }

    public void removeTab(String viewId) {
        ButtonTab tabImpl = compMap.get(viewId);
        if (tabImpl != null) {
            navigatorContainer.removeComponent(tabImpl);
            compMap.remove(viewId);
        }
    }

    private ButtonTab getButtonById(String viewId) {
        for (int i = 0; i < navigatorContainer.getComponentCount(); i++) {
            ButtonTab button = (ButtonTab) navigatorContainer.getComponent(i);
            if (viewId.equals(button.getTabId())) {
                return button;
            }
        }

        return null;
    }

    public void setNavigatorVisibility(boolean visibility) {
        if (!visibility) {
            navigatorWrapper.setWidth(MIN_SIZE);
            navigatorContainer.setWidth(MIN_SIZE);
            this.hideTabsCaption();

            navigatorContainer.setComponentAlignment(toggleBtn, Alignment.MIDDLE_CENTER);
            toggleBtn.setIcon(VaadinIcons.ANGLE_DOUBLE_RIGHT);
            toggleBtn.setStyleName(WebThemes.BUTTON_ICON_ONLY + " expand-button");
            toggleBtn.addStyleName("toggle-button");
            toggleBtn.setDescription(UserUIContext.getMessage(ShellI18nEnum.ACTION_EXPAND_MENU));
            toggleBtn.setWidth("65px");
            toggleBtn.setCaption("");
        } else {
            navigatorWrapper.setWidth(MAX_SIZE);
            navigatorContainer.setWidth(MAX_SIZE);
            this.showTabsCaption();

            toggleBtn.setStyleName(WebThemes.BUTTON_ICON_ONLY + " closed-button");
            toggleBtn.addStyleName("toggle-button");
            toggleBtn.setIcon(VaadinIcons.CLOSE_SMALL);
            toggleBtn.setWidth(MAX_SIZE);
            toggleBtn.setDescription(UserUIContext.getMessage(ShellI18nEnum.ACTION_COLLAPSE_MENU));
            navigatorContainer.setComponentAlignment(toggleBtn, Alignment.MIDDLE_CENTER);
        }
    }

    public void addToggleNavigatorControl() {
        if (getButtonById("button") == null) {
            toggleBtn = new ButtonTab("button", 0, "", null, "");
            toggleBtn.setStyleName(WebThemes.BUTTON_ICON_ONLY + " closed-button");
            toggleBtn.addStyleName("toggle-button");
            toggleBtn.addClickListener(clickEvent -> {
                retainVisibility = !retainVisibility;
                setNavigatorVisibility(retainVisibility);
            });
            navigatorContainer.addComponent(toggleBtn, 0);
            navigatorContainer.setComponentAlignment(toggleBtn, Alignment.TOP_RIGHT);
        }

        setNavigatorVisibility(retainVisibility);
    }

    private void fireTabChangeEvent(SelectedTabChangeEvent event) {
        this.fireEvent(event);
    }

    private static final Method SELECTED_TAB_CHANGE_METHOD;

    static {
        try {
            SELECTED_TAB_CHANGE_METHOD = SelectedTabChangeListener.class
                    .getDeclaredMethod("selectedTabChange", SelectedTabChangeEvent.class);
        } catch (NoSuchMethodException e) {
            throw new MyCollabException("Internal error finding methods in TabSheet");
        }
    }

    public void addSelectedTabChangeListener(TabSheet.SelectedTabChangeListener listener) {
        this.addListener(SelectedTabChangeEvent.class, listener, SELECTED_TAB_CHANGE_METHOD);
    }

    public Component selectTab(String viewId) {
        ButtonTab tab = compMap.get(viewId);
        if (tab != null) {
            selectedButton = tab;
            clearTabSelection();
            selectedButton.addStyleName(TAB_SELECTED_STYLE);
            selectedComp = tab;

            // Hack for tab view has both header - content or content only
            if (contentWrapper.getComponentCount() > 0 && "tab-content-header".equals(contentWrapper.getComponent(0).getId())) {
                if (contentWrapper.getComponentCount() > 1) {
                    contentWrapper.removeComponent(contentWrapper.getComponent(contentWrapper.getComponentCount() - 1));
                }
            } else {
                contentWrapper.removeAllComponents();
            }

            Component tabComponent = tab.getComponent();
            contentWrapper.addComponent(tabComponent);
            return tabComponent;
        } else {
            return null;
        }
    }

    public ButtonTab getSelectedTab() {
        return selectedComp;
    }

    @Override
    public void setWidth(float width, Unit unit) {
        super.setWidth(width, unit);

        if (getCompositionRoot() != null)
            getCompositionRoot().setWidth(width, unit);
    }

    public void setNavigatorWidth(String width) {
        navigatorContainer.setWidth(width);
        navigatorContainer.forEach(comp -> comp.setWidth(width));
    }

    public void setNavigatorStyleName(String styleName) {
        navigatorContainer.setStyleName(styleName);
    }

    private void clearTabSelection() {
        navigatorContainer.forEach(component -> {
            if (component.getStyleName().contains(TAB_SELECTED_STYLE)) {
                component.removeStyleName(TAB_SELECTED_STYLE);
            }
        });
    }

    public CssLayout getContentWrapper() {
        return this.contentWrapper;
    }

    public CssLayout getNavigatorWrapper() {
        return this.navigatorWrapper;
    }

    public static class ButtonTab extends MButton {
        private static final long serialVersionUID = 1L;

        private String tabId;
        private int level;
        String link;
        private String caption;
        private Component component;

        ButtonTab(String id, int level, String caption, Component component, String link) {
            super(caption);
            this.tabId = id;
            this.link = link;
            this.level = level;
            this.caption = caption;
            this.component = component;
        }

        void hideCaption() {
            this.setCaption("");
            this.setDescription(String.format("<div class=\"v-label-h3 no-margin\">%s</div>", caption), ContentMode.HTML);
        }

        void showCaption() {
            this.setCaption(caption);
            this.setDescription("");
        }

        public String getTabId() {
            return tabId;
        }

        public int getLevel() {
            return level;
        }

        public Component getComponent() {
            return component;
        }
    }
}
