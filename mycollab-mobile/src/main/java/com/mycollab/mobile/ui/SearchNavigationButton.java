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
package com.mycollab.mobile.ui;

import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.icons.VaadinIcons;
import org.vaadin.touchkit.ui.NavigationButton;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public abstract class SearchNavigationButton extends NavigationButton {
    public SearchNavigationButton() {
        setIcon(VaadinIcons.SEARCH);
        addStyleName(UIConstants.CIRCLE_BOX);
        this.addClickListener(navigationButtonClickEvent -> getNavigationManager().navigateTo(getSearchInputView()));
    }

    abstract protected SearchInputView getSearchInputView();
}
