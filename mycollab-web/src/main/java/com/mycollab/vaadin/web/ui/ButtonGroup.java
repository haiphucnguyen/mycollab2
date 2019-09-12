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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ButtonGroup extends Div {
    private static final long serialVersionUID = 1L;

    private Button selectedBtn;

    public ButtonGroup() {
        this.addClassName("toggle-btn-group");
    }

    public ButtonGroup(Button... buttons) {
        this.addClassName("toggle-btn-group");
        addButtons(buttons);
    }

    // TODO
    public void addButton(Button button) {
        add(button);
//        button.addClickListener(clickEvent -> {
//            if (!clickEvent.getButton().equals(selectedBtn)) {
//                selectedBtn = clickEvent.getButton();
//                for (Component component : ButtonGroup.this) {
//                    component.removeStyleName(WebThemes.BTN_ACTIVE);
//                }
//                selectedBtn.addStyleName(WebThemes.BTN_ACTIVE);
//            }
//        });
    }

    public void addButtons(Button... buttons) {
        Arrays.stream(buttons).forEach(this::addButton);
    }

    public ButtonGroup withDefaultButton(Button button) {
//        for (Component component : ButtonGroup.this) {
//            Button currentBtn = (Button) component;
//            if (currentBtn.equals(button)) {
//                selectedBtn = button;
//                selectedBtn.addStyleName(WebThemes.BTN_ACTIVE);
//            } else {
//                currentBtn.removeStyleName(WebThemes.BTN_ACTIVE);
//            }
//        }
        return this;
    }
}
