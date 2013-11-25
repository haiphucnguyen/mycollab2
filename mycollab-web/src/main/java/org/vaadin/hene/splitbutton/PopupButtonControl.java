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
package org.vaadin.hene.splitbutton;

import java.util.HashSet;
import java.util.Set;

import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.TablePopupActionHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class PopupButtonControl extends SplitButton implements
        HasPopupActionHandlers {

    private static final long serialVersionUID = 1L;
    private VerticalLayout selectContent;
    private Set<TablePopupActionHandler> handlers;

    public PopupButtonControl(final String id, final Button button) {
        super(button, new SplitButton.PopupButton());

        addStyleName(UIConstants.THEME_GRAY_LINK);

        initPopupButton(id, button.getCaption());
    }

    public PopupButtonControl(final String id, final String defaultName) {
        super();
        setCaption(defaultName);
        addStyleName(UIConstants.THEME_GRAY_LINK);
        initPopupButton(id, defaultName);
    }

    public void addOptionItem(final String id, final String name) {
        this.addOptionItem(id, name, true);
    }

    public void addOptionItem(final String id, final String name,
            final boolean isEnable) {
        final Button optionBtn = new Button(name, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                changeOption(id, name);
                PopupButtonControl.this.setPopupVisible(false);
            }
        });
        optionBtn.addStyleName("link");
        optionBtn.setEnabled(isEnable);
        selectContent.addComponent(optionBtn);
    }

    @Override
    public void addPopupActionHandler(final TablePopupActionHandler handler) {
        if (handlers == null) {
            handlers = new HashSet<TablePopupActionHandler>();
        }
        handlers.add(handler);

    }

    private void changeOption(final String id, final String caption) {
        if (handlers != null) {
            for (final TablePopupActionHandler handler : handlers) {
                handler.onSelect(id, caption);
            }
        }
    }

    private void initPopupButton(final String id, final String defaultName) {
        setData(id);
        addStyleName(UIConstants.THEME_BLUE_LINK);

        addClickListener(new SplitButton.SplitButtonClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void splitButtonClick(
                    final SplitButton.SplitButtonClickEvent event) {
                changeOption(id, defaultName);
                PopupButtonControl.this.setPopupVisible(false);
            }
        });

        selectContent = new VerticalLayout();
        selectContent.setWidth("100px");
        setComponent(selectContent);
    }
}
