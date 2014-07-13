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
package com.esofthead.mycollab.mobile.module.crm.view.opportunity;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.mobile.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.mobile.ui.MobileNavigationButton;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.i18n.OpportunityI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.ui.Component;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */

@ViewComponent
public class OpportunityListViewImpl extends
		AbstractListViewComp<OpportunitySearchCriteria, SimpleOpportunity>
		implements OpportunityListView {
	private static final long serialVersionUID = 8959720143847140837L;

	public OpportunityListViewImpl() {
		super();

		setCaption(AppContext.getMessage(OpportunityI18nEnum.VIEW_LIST_TITLE));
	}

	@Override
	protected AbstractPagedBeanList<OpportunitySearchCriteria, SimpleOpportunity> createBeanTable() {
		OpportunityListDisplay opportunityListDisplay = new OpportunityListDisplay();
		return opportunityListDisplay;
	}

	@Override
	protected Component createRightComponent() {
		MobileNavigationButton addOpportunity = new MobileNavigationButton();
		addOpportunity.setTargetViewCaption(AppContext
				.getMessage(OpportunityI18nEnum.VIEW_NEW_TITLE));
		addOpportunity
				.addClickListener(new NavigationButton.NavigationButtonClickListener() {
					private static final long serialVersionUID = 7172838996944732255L;

					@Override
					public void buttonClick(
							NavigationButton.NavigationButtonClickEvent event) {
						EventBusFactory.getInstance().post(
								new OpportunityEvent.GotoAdd(this, null));
					}
				});
		addOpportunity.setStyleName("add-btn");
		return addOpportunity;
	}

}
