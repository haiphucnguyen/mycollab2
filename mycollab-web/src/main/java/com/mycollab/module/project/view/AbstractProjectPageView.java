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
package com.mycollab.module.project.view;

import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class AbstractProjectPageView extends AbstractVerticalPageView {
    private static final long serialVersionUID = 1L;

    protected ELabel headerText;
    protected MCssLayout contentWrapper;
    protected MHorizontalLayout header;

    public AbstractProjectPageView(String headerText, VaadinIcons icon) {
        this.headerText = ELabel.h2(String.format("%s %s", icon.getHtml(), headerText));
        super.addComponent(constructHeader());

        contentWrapper = new MCssLayout().withStyleName(WebThemes.CONTENT_WRAPPER);
        super.addComponent(contentWrapper);
    }

    private ComponentContainer constructHeader() {
        header = new MHorizontalLayout().with(headerText).withStyleName(WebThemes.HEADER_VIEW).withFullWidth().withMargin(true);
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        return header;
    }

    public void addHeaderRightContent(Component c) {
        header.with(c).withAlign(c, Alignment.MIDDLE_RIGHT);
    }

    @Override
    public void addComponent(Component c) {
        contentWrapper.addComponent(c);
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {
        contentWrapper.replaceComponent(oldComponent, newComponent);
    }

    public ComponentContainer getBody() {
        return contentWrapper;
    }
}
