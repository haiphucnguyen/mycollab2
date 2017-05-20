/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * This file is part of mycollab-mobile.
 * <p>
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.mobile.module.crm.view.opportunity;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.events.OpportunityEvent;
import com.mycollab.mobile.module.crm.ui.AbstractListPageView;
import com.mycollab.mobile.ui.AbstractPagedBeanList;
import com.mycollab.mobile.ui.SearchInputField;
import com.mycollab.mobile.ui.SearchInputView;
import com.mycollab.mobile.ui.SearchNavigationButton;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.mycollab.module.crm.i18n.OpportunityI18nEnum;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class OpportunityListViewImpl extends AbstractListPageView<OpportunitySearchCriteria, SimpleOpportunity> implements OpportunityListView {
    private static final long serialVersionUID = 8959720143847140837L;

    public OpportunityListViewImpl() {
        setCaption(UserUIContext.getMessage(OpportunityI18nEnum.LIST));
    }

    @Override
    protected AbstractPagedBeanList<OpportunitySearchCriteria, SimpleOpportunity> createBeanList() {
        return new OpportunityListDisplay();
    }

    @Override
    protected SearchInputField<OpportunitySearchCriteria> createSearchField() {
        return null;
    }

    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        MyCollabUI.addFragment("crm/opportunity/list", UserUIContext.getMessage(OpportunityI18nEnum.LIST));
    }

    @Override
    protected Component buildRightComponent() {
        SearchNavigationButton searchBtn = new SearchNavigationButton() {
            @Override
            protected SearchInputView getSearchInputView() {
                return new OpportunitySearchInputView();
            }
        };
        MButton newOpportunityBtn = new MButton("", clickEvent -> EventBusFactory.getInstance().post(new OpportunityEvent.GotoAdd(this, null)))
                .withIcon(FontAwesome.PLUS).withStyleName(UIConstants.CIRCLE_BOX)
                .withVisible(UserUIContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));
        return new MHorizontalLayout(searchBtn, newOpportunityBtn).alignAll(Alignment.TOP_RIGHT);
    }

}
