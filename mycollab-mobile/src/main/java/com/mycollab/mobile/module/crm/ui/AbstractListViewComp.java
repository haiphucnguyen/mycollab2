/**
 * mycollab-mobile - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © ${project.inceptionYear} MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.mobile.module.crm.ui;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.mobile.ui.AbstractMobileMenuPageView;
import com.mycollab.mobile.ui.AbstractPagedBeanList;
import com.mycollab.mobile.ui.IListView;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public abstract class AbstractListViewComp<S extends SearchCriteria, B> extends AbstractMobileMenuPageView implements IListView<S, B> {
    private static final long serialVersionUID = 3603608419228750094L;

    protected AbstractPagedBeanList<S, B> itemList;

    public AbstractListViewComp() {
        this.itemList = createBeanTable();
        setContent(itemList);

        Component rightComponent = createRightComponent();
        if (rightComponent != null) {
            setRightComponent(rightComponent);
        }
    }

    @Override
    public AbstractPagedBeanList<S, B> getPagedBeanTable() {
        return this.itemList;
    }

    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();

        if (getPagedBeanTable().getSearchRequest() != null)
            getPagedBeanTable().refresh();
    }

    @Override
    protected void buildNavigateMenu() {

    }

    abstract protected AbstractPagedBeanList<S, B> createBeanTable();

    abstract protected Component createRightComponent();
}
