/**
 * This file is part of mycollab-web-community.
 *
 * mycollab-web-community is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-community is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-community.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.community.module.project.view.time;

import com.esofthead.mycollab.module.project.view.time.IFinanceContainer;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.view.NotPresentedView;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class FinanceContainer extends NotPresentedView implements IFinanceContainer {
    private static final long serialVersionUID = 1L;

    @Override
    public void initContent() {

    }

    @Override
    public Component gotoSubView(String name) {
        return null;
    }
}
