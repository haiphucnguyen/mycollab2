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

import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.CampaignService;
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

@SuppressWarnings("serial")
public class CampaignSelectionField extends FieldWrapper<CampaignWithBLOBs>
		implements FieldSelection {

	private HorizontalLayout layout;

	private SimpleCampaign campaign;

	private TextField campaignName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public CampaignSelectionField() {
		super(new TextField(""), CampaignWithBLOBs.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);

		campaignName = new TextField();
		layout.addComponent(campaignName);

		browseBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
		browseBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				CampaignSelectionWindow campaignWindow = new CampaignSelectionWindow(
						CampaignSelectionField.this);
				UIHelper.addWindowToRoot(CampaignSelectionField.this,
						campaignWindow);
				campaignWindow.show();

			}
		});

		clearBtn = new Embedded(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				campaignName.setValue("");
				CampaignSelectionField.this.getWrappedField().setValue(null);
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		this.setCompositionRoot(layout);
		this.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				try {
					Integer campaignId = Integer.parseInt((String) event
							.getProperty().getValue());
					CampaignService campaignService = ApplicationContextUtil
							.getSpringBean(CampaignService.class);
					SimpleCampaign campaign = campaignService.findById(
							campaignId, AppContext.getAccountId());
					if (campaign != null) {
						campaignName.setValue(campaign.getCampaignname());
					}
				} catch (Exception e) {

				}

			}
		});
	}

	public void setCampaign(SimpleCampaign campaign) {
		this.campaign = campaign;
		campaignName.setValue(campaign.getCampaignname());
	}

	@Override
	public void fireValueChange(Object data) {
		this.campaign = (SimpleCampaign) data;
		campaignName.setValue(campaign.getCampaignname());
		this.getWrappedField().setValue(campaign.getId());
	}

}
