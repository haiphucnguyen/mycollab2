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
package com.esofthead.mycollab.premium.module.crm.view.setting;

import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.service.MasterFormService;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
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
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.CreateCustomFieldWindow;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.CreateSectionWindow;
import com.esofthead.mycollab.premium.module.crm.view.setting.customlayout.CustomLayoutDDComp;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CrmCustomViewImpl extends AbstractPageView implements
		ICrmCustomView {

	private static final long serialVersionUID = 1L;

	private Label headerLbl;
	private ModuleSelectionComboBox moduleComboBox;
	private CustomLayoutDDComp layoutComp;
	private String moduleName;

	public CrmCustomViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		VerticalLayout headerBox = new VerticalLayout();
		headerBox.setWidth("100%");
		headerBox.setSpacing(true);
		headerBox.addStyleName("CrmCustomViewHeader");

		headerLbl = new Label();
		HorizontalLayout headerTitle = new HorizontalLayout();
		headerTitle.setWidth("100%");
		headerTitle.addStyleName("headerTitle");
		headerTitle.addComponent(headerLbl);
		headerTitle.setComponentAlignment(headerLbl, Alignment.MIDDLE_LEFT);
		headerTitle.setExpandRatio(headerLbl, 1.0f);
		headerBox.addComponent(headerTitle);

		VerticalLayout headerContent = new VerticalLayout();
		headerContent.setWidth("100%");
		headerContent.setMargin(new MarginInfo(false, true, true, true));
		Label descLbl = new Label(
				"Customize the page layout by changing the order of the columns and fields, marking fields as mandatory, adding or removing the fields and sections. You can drag and drop the originSection header to reorder the sections. You need to drag and drop the fields to move them to the List of Removed Fields");
		descLbl.setStyleName("instructionLbl");
		headerContent.addComponent(descLbl);

		HorizontalLayout controlLayout = new HorizontalLayout();
		controlLayout.addStyleName("control-buttons");
		controlLayout.setSpacing(true);
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
						CreateCustomFieldWindow createCustomFieldWindow = new CreateCustomFieldWindow(
								CrmCustomViewImpl.this);
						UI.getCurrent().addWindow(createCustomFieldWindow);

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
						UI.getCurrent().addWindow(createSectionWindow);

					}
				});
		createSectionBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		createSectionBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlLayout.addComponent(createSectionBtn);
		controlLayout.setComponentAlignment(createSectionBtn,
				Alignment.MIDDLE_LEFT);
		headerTitle.addComponent(controlLayout);
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
						DynaForm rebuildForm = layoutComp.rebuildForm();

						MasterFormService formService = ApplicationContextUtil
								.getSpringBean(MasterFormService.class);
						formService.saveCustomForm(AppContext.getAccountId(),
								moduleName, rebuildForm);
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
						display(CrmCustomViewImpl.this.moduleName);

					}
				});
		cancelBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		buttonsLayout.addComponent(cancelBtn);

		headerContent.addComponent(buttonsLayout);
		headerContent.setComponentAlignment(buttonsLayout,
				Alignment.MIDDLE_CENTER);
	}

	@Override
	public void display(String moduleName) {
		this.moduleName = moduleName;
		headerLbl.setCaption(moduleName + ": Edit Page Layout (Beta)");
		moduleComboBox.select(moduleName);

		layoutComp.displayLayoutCustom(getDynaForm(moduleName));
	}

	private static DynaForm getDynaForm(String moduleName) {
		MasterFormService formService = ApplicationContextUtil
				.getSpringBean(MasterFormService.class);
		DynaForm form = formService.findCustomForm(AppContext.getAccountId(),
				moduleName);

		if (form == null) {
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
		}

		return form;
	}

	@Override
	public void addActiveSection(DynaSection section) {
		layoutComp.addActiveSection(section);
	}

	@Override
	public List<DynaSection> getActiveSections() {
		return layoutComp.getActiveSections();
	}

	private class ModuleSelectionComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public ModuleSelectionComboBox() {
			super(false, CrmTypeConstants.ACCOUNT, CrmTypeConstants.CONTACT,
					CrmTypeConstants.CAMPAIGN, CrmTypeConstants.LEAD,
					CrmTypeConstants.OPPORTUNITY, CrmTypeConstants.CASE,
					CrmTypeConstants.TASK, CrmTypeConstants.CALL,
					CrmTypeConstants.MEETING);

			this.addValueChangeListener(new Property.ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(Property.ValueChangeEvent event) {
					String module = (String) ModuleSelectionComboBox.this
							.getValue();
					display(module);

				}
			});
		}
	}

	@Override
	public String getCandidateTextFieldName() {
		return layoutComp.getCandidateTextFieldName();
	}

	@Override
	public void refreshSectionLayout(DynaSection section) {
		layoutComp.refreshSectionLayout(section);

	}
}
