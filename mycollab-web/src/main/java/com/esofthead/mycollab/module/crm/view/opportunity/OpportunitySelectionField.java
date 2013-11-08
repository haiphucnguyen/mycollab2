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

import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.UIHelper;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class OpportunitySelectionField extends FieldWrapper<Opportunity>
		implements FieldSelection {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout layout;

	private SimpleOpportunity opportunity;

	private TextField opportunityName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public OpportunitySelectionField() {
		super(new TextField(""), Opportunity.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);

		opportunityName = new TextField();
		layout.addComponent(opportunityName);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
		browseBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				OpportunitySelectionWindow opportunityWindow = new OpportunitySelectionWindow(
						OpportunitySelectionField.this);
				UIHelper.addWindowToRoot(OpportunitySelectionField.this,
						opportunityWindow);
				opportunityWindow.show();

			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				opportunityName.setValue("");
				OpportunitySelectionField.this.getWrappedField().setValue(null);
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		this.setCompositionRoot(layout);
		this.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				try {
					Integer opportunityId = Integer.parseInt((String) event
							.getProperty().getValue());
					OpportunityService opportunityService = ApplicationContextUtil
							.getSpringBean(OpportunityService.class);
					SimpleOpportunity opportunity = opportunityService
							.findById(opportunityId, AppContext.getAccountId());
					if (opportunity != null) {
						opportunityName.setValue(opportunity
								.getOpportunityname());
					}
				} catch (Exception e) {

				}

			}
		});
	}

	public void setOpportunity(SimpleOpportunity opportunity) {
		this.opportunity = opportunity;
		opportunityName.setValue(opportunity.getOpportunityname());
	}

	@Override
	public void fireValueChange(Object data) {
		this.opportunity = (SimpleOpportunity) data;
		opportunityName.setValue(opportunity.getOpportunityname());
		this.getWrappedField().setValue(opportunity.getId());
	}

}
