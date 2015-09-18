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
package com.esofthead.mycollab.common.ui.components.notification;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.ui.AbstractNotification;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class TimezoneNotification extends AbstractNotification {

    public TimezoneNotification() {
        super(AbstractNotification.WARNING);
    }

    @Override
    public Component renderContent() {
        MHorizontalLayout wrapper = new MHorizontalLayout();
        wrapper.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        wrapper.addComponent(new Label(FontAwesome.EXCLAMATION.getHtml() + " The correct your timezone will help you get the event right", ContentMode.HTML));
        Button actionBtn = new Button("Action", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EventBusFactory.getInstance().post(new ShellEvent.GotoUserAccountModule(this, new String[]{"preview"}));
            }
        });
        actionBtn.setStyleName(UIConstants.THEME_LINK);
        actionBtn.addStyleName("block");
        wrapper.addComponent(actionBtn);
        return wrapper;
    }
}
