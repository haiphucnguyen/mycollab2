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

import com.esofthead.mycollab.vaadin.ui.AbstractNotification;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Properties;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class NewUpdateNotification extends AbstractNotification {
    private Properties props;

    public NewUpdateNotification(Properties props) {
        super(AbstractNotification.WARNING);

        this.props = props;
    }

    @Override
    public Component renderContent() {
        MHorizontalLayout wrapper = new MHorizontalLayout();
        wrapper.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Span spanEl = new Span();
        spanEl.appendText("There is the new MyCollab version " + props.getProperty("version") + " . For the " +
                "enhancements and security purpose, the system administrator should upgrade to the latest version");
        wrapper.addComponent(new Label(FontAwesome.EXCLAMATION.getHtml() + " " + spanEl.write(), ContentMode.HTML));
        return wrapper;
    }
}
