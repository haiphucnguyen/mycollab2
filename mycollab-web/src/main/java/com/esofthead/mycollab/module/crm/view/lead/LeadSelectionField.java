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
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
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
public class LeadSelectionField extends CustomField<Lead> implements
		FieldSelection {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout layout;

	private SimpleLead lead;

	private TextField leadName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public void setLead(SimpleLead lead) {
		this.lead = lead;
		leadName.setValue(lead.getLeadName());
	}

	@Override
	public void fireValueChange(Object data) {
		this.lead = (SimpleLead) data;
		leadName.setValue(lead.getLeadName());
	}

	@Override
	protected Component initContent() {
		layout = new HorizontalLayout();
		layout.setSpacing(true);

		leadName = new TextField();
		layout.addComponent(leadName);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
		browseBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				LeadSelectionWindow leadWindow = new LeadSelectionWindow(
						LeadSelectionField.this);
				UI.getCurrent().addWindow(leadWindow);

			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addClickListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				leadName.setValue("");
				LeadSelectionField.this.lead = null;
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		return layout;
	}

	@Override
	public Class<? extends Lead> getType() {
		return Lead.class;
	}

}
