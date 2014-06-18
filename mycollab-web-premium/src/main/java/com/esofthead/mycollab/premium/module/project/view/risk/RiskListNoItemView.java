/**
 * This file is part of mycollab-web-premium.
 *
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1.2
 * 
 */

@ViewComponent
public class RiskListNoItemView extends AbstractPageView {
	private static final long serialVersionUID = -2154602282175183516L;

	public RiskListNoItemView() {
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("risk-noitem");
		layout.setSpacing(true);
		layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		layout.setMargin(true);

		Image image = new Image(null,
				MyCollabResource.newResource("icons/48/project/risk.png"));
		layout.addComponent(image);

		Label title = new Label(
				AppContext.getMessage(RiskI18nEnum.VIEW_NO_ITEM_TITLE));
		title.addStyleName("h2");
		title.setWidth(SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		layout.addComponent(title);

		Label body = new Label(
				AppContext.getMessage(RiskI18nEnum.VIEW_NO_ITEM_HINT));
		body.setWidth(SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		layout.addComponent(body);

		Button createRiskBtn = new Button(
				AppContext.getMessage(RiskI18nEnum.BUTTON_NEW_RISK),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoAdd(this, null));
					}
				});

		HorizontalLayout links = new HorizontalLayout();

		links.addComponent(createRiskBtn);
		createRiskBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		links.setSpacing(true);

		layout.addComponent(links);
		this.addComponent(layout);
		this.setComponentAlignment(layout, Alignment.TOP_CENTER);
	}
}
