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
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@SuppressWarnings("serial")
public class CampaignSelectionField extends CustomField<Integer> implements
		FieldSelection<CampaignWithBLOBs> {

	private CampaignWithBLOBs internalValue = new CampaignWithBLOBs();

	private TextField campaignName = new TextField();
	private Image browseBtn;
	private Image clearBtn;

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		Object value = newDataSource.getValue();
		if (value instanceof Integer) {
			CampaignService campaignService = ApplicationContextUtil
					.getSpringBean(CampaignService.class);
			SimpleCampaign campaign = campaignService.findById((Integer) value,
					AppContext.getAccountId());
			if (campaign != null) {
				setInternalCampaign(campaign);
			}

			super.setPropertyDataSource(newDataSource);
		} else {
			super.setPropertyDataSource(newDataSource);
		}
	}

	private void setInternalCampaign(SimpleCampaign campaign) {
		this.internalValue = campaign;
		campaignName.setValue(internalValue.getCampaignname());
	}

	@Override
	protected Component initContent() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		campaignName.setWidth("100%");
		layout.addComponent(campaignName);

		browseBtn = new Image(null,
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

		clearBtn = new Image(null,
				MyCollabResource.newResource("icons/16/clearItem.png"));
		clearBtn.addClickListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				campaignName.setValue("");
				internalValue = null;
			}
		});
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		layout.setExpandRatio(campaignName, 1.0f);
		return layout;
	}

	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	@Override
	public void fireValueChange(CampaignWithBLOBs data) {
		this.internalValue = data;
		if (internalValue != null) {
			campaignName.setValue(internalValue.getCampaignname());
			setInternalValue(data.getId());
		}
	}

}
