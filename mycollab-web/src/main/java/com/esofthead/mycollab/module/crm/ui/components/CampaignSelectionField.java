package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.vaadin.data.Property;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class CampaignSelectionField extends FieldWrapper<Campaign> implements
		FieldSelection {

	private HorizontalLayout layout;

	private TextField campaignName;
	private Embedded browseBtn;
	private Embedded clearBtn;

	@Autowired
	private CampaignService campaignService;

	public CampaignSelectionField() {
		super(new TextField(""), Campaign.class);

		layout = new HorizontalLayout();
		layout.setSpacing(true);

		campaignName = new TextField();
		layout.addComponent(campaignName);

		browseBtn = new Embedded(null, new ThemeResource(
				"icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
		browseBtn.addListener(new MouseEvents.ClickListener() {

			@Override
			public void click(ClickEvent event) {
				CampaignSelectionWindow campaignWindow = new CampaignSelectionWindow(
						CampaignSelectionField.this);
				getWindow().addWindow(campaignWindow);
				campaignWindow.show();

			}
		});

		clearBtn = new Embedded(null, new ThemeResource(
				"icons/16/clearItem.png"));
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
					SimpleCampaign campaign = campaignService
							.findCampaignById(campaignId);
					if (campaign != null) {
						campaignName.setValue(campaign.getCampaignname());
					}
				} catch (Exception e) {

				}

			}
		});
	}

	public void setCampaignName(String name) {
		campaignName.setValue(name);
	}

	@Override
	public void fireValueChange(Object data) {
		Campaign campaign = (Campaign) data;
		campaignName.setValue(campaign.getCampaignname());
		this.getWrappedField().setValue(campaign.getId());
	}

}
