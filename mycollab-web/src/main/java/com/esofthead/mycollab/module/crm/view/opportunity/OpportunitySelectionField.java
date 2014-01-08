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
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.vaadin.resource.ui.FieldSelection;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class OpportunitySelectionField extends CustomField implements
		FieldSelection {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout layout;

	private SimpleOpportunity opportunity;

	private TextField opportunityName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public void setOpportunity(SimpleOpportunity opportunity) {
		this.opportunity = opportunity;
		opportunityName.setValue(opportunity.getOpportunityname());
	}

	@Override
	public void fireValueChange(Object data) {
		this.opportunity = (SimpleOpportunity) data;
		opportunityName.setValue(opportunity.getOpportunityname());
	}

	@Override
	protected Component initContent() {
		layout = new HorizontalLayout();
		layout.setSpacing(true);

		opportunityName = new TextField();
		layout.addComponent(opportunityName);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
		browseBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				OpportunitySelectionWindow opportunityWindow = new OpportunitySelectionWindow(
						OpportunitySelectionField.this);
				UI.getCurrent().addWindow(opportunityWindow);
				opportunityWindow.show();

			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				opportunityName.setValue("");
				opportunity = null;
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		return layout;
	}

	@Override
	public Class getType() {
		return Opportunity.class;
	}

}
