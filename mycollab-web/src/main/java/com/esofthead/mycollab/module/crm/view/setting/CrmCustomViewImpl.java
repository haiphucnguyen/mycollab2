package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.view.account.AccountDefaultDynaFormFactory;
import com.esofthead.mycollab.module.crm.view.activity.AssignmentDefaultFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.activity.CallDefaultFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.activity.MeetingDefaultFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.cases.CasesDefaultFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.contact.ContactDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.lead.LeadDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CrmCustomViewImpl extends AbstractView implements CrmCustomView {
	private static final long serialVersionUID = 1L;

	private Label headerLbl;
	private ModuleSelectionComboBox moduleComboBox;
	private CustomLayoutDDComp layoutComp;

	public CrmCustomViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		VerticalLayout headerBox = new VerticalLayout();
		headerBox.setWidth("100%");
		headerBox.setSpacing(true);
		headerBox.addStyleName("CrmCustomViewHeader");

		headerLbl = new Label();
		CssLayout headerTitle = new CssLayout();
		headerTitle.addStyleName("headerTitle");
		headerTitle.setWidth("100%");
		headerTitle.addComponent(headerLbl);
		headerBox.addComponent(headerTitle);

		VerticalLayout headerContent = new VerticalLayout();
		headerContent.setWidth("100%");
		headerContent.setMargin(false, true, true, true);
		Label descLbl = new Label(
				"Customize the page layout by changing the order of the columns and fields, marking fields as mandatory, adding or removing the fields and sections. You can drag and drop the section header to reorder the sections. You need to drag and drop the fields to move them to the List of Removed Fields");
		descLbl.setStyleName("instructionLbl");
		headerContent.addComponent(descLbl);

		HorizontalLayout controlLayout = new HorizontalLayout();
		controlLayout.setSpacing(true);
		controlLayout.setMargin(true, false, false, false);
		moduleComboBox = new ModuleSelectionComboBox();

		Label moduleLbl = new Label("Module: ");
		controlLayout.addComponent(moduleLbl);
		controlLayout.setComponentAlignment(moduleLbl, Alignment.MIDDLE_LEFT);
		controlLayout.addComponent(moduleComboBox);
		controlLayout.setComponentAlignment(moduleComboBox,
				Alignment.MIDDLE_LEFT);

		Button createCustomFieldBtn = new Button("New Custom Field",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		createCustomFieldBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		createCustomFieldBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlLayout.addComponent(createCustomFieldBtn);
		controlLayout.setComponentAlignment(createCustomFieldBtn,
				Alignment.MIDDLE_LEFT);

		Button createSectionBtn = new Button("Create Section",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						CreateSectionWindow createSectionWindow = new CreateSectionWindow(
								CrmCustomViewImpl.this);
						getWindow().addWindow(createSectionWindow);

					}
				});
		createSectionBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		createSectionBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlLayout.addComponent(createSectionBtn);
		controlLayout.setComponentAlignment(createSectionBtn,
				Alignment.MIDDLE_LEFT);
		headerContent.addComponent(controlLayout);
		headerBox.addComponent(headerContent);
		this.addComponent(headerBox);

		layoutComp = new CustomLayoutDDComp();
		this.addComponent(layoutComp);

		HorizontalLayout buttonsLayout = new HorizontalLayout();
		buttonsLayout.setSpacing(true);
		Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		buttonsLayout.addComponent(saveBtn);

		Button cancelBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		cancelBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		buttonsLayout.addComponent(cancelBtn);

		this.addComponent(buttonsLayout);
		this.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void display(String moduleName) {
		headerLbl.setCaption(moduleName + ": Edit Page Layout");
		moduleComboBox.select(moduleName);
		DynaForm form;

		if (CrmTypeConstants.ACCOUNT.equals(moduleName)) {
			form = AccountDefaultDynaFormFactory.getForm();
		} else if (CrmTypeConstants.CONTACT.equals(moduleName)) {
			form = ContactDefaultDynaFormLayoutFactory.getForm();
		} else if (CrmTypeConstants.CAMPAIGN.equals(moduleName)) {
			form = CampaignDefaultDynaFormLayoutFactory.getForm();
		} else if (CrmTypeConstants.LEAD.equals(moduleName)) {
			form = LeadDefaultDynaFormLayoutFactory.getForm();
		} else if (CrmTypeConstants.OPPORTUNITY.equals(moduleName)) {
			form = OpportunityDefaultDynaFormLayoutFactory.getForm();
		} else if (CrmTypeConstants.CASE.equals(moduleName)) {
			form = CasesDefaultFormLayoutFactory.getForm();
		} else if (CrmTypeConstants.CALL.equals(moduleName)) {
			form = CallDefaultFormLayoutFactory.getForm();
		} else if (CrmTypeConstants.MEETING.equals(moduleName)) {
			form = MeetingDefaultFormLayoutFactory.getForm();
		} else if (CrmTypeConstants.TASK.equals(moduleName)) {
			form = AssignmentDefaultFormLayoutFactory.getForm();
		} else {
			throw new MyCollabException(
					"Do not support custom layout of module " + moduleName);
		}

		layoutComp.displayLayoutCustom(form);
	}

	private class ModuleSelectionComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public ModuleSelectionComboBox() {
			super(false, CrmTypeConstants.ACCOUNT, CrmTypeConstants.CONTACT,
					CrmTypeConstants.CAMPAIGN, CrmTypeConstants.LEAD,
					CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE,
					CrmTypeConstants.TASK, CrmTypeConstants.CALL,
					CrmTypeConstants.MEETING);

			this.addListener(new Property.ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					String moduleName = (String) ModuleSelectionComboBox.this
							.getValue();
					display(moduleName);

				}
			});
		}
	}

}
