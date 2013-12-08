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
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
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

@SuppressWarnings("serial")
public class CampaignSelectionField extends CustomField<CampaignWithBLOBs>
		implements FieldSelection<CampaignWithBLOBs> {

	private CampaignWithBLOBs campaign;

	private TextField campaignName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public void setCampaign(CampaignWithBLOBs campaign) {
		this.campaign = campaign;
		campaignName.setValue(campaign.getCampaignname());
	}

	@Override
	protected Component initContent() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		campaignName = new TextField();
		layout.addComponent(campaignName);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		browseBtn.addClickListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				CampaignSelectionWindow campaignWindow = new CampaignSelectionWindow(
						CampaignSelectionField.this);
				UI.getCurrent().addWindow(campaignWindow);
				campaignWindow.show();

			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addClickListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				campaignName.setValue("");
				campaign = null;
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		return layout;
	}

	@Override
	public Class<? extends CampaignWithBLOBs> getType() {
		return CampaignWithBLOBs.class;
	}

	@Override
	public void fireValueChange(CampaignWithBLOBs data) {
		this.campaign = data;
	}

}
