package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class CreateSectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private CrmCustomView customView;

	public CreateSectionWindow(CrmCustomView customView) {
		super("Create Section");
		center();
		this.setWidth("600px");

		this.customView = customView;

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 2);
		TextField sectionName = new TextField();
		layoutHelper.addComponent(sectionName, "Name", 0, 0);

		SectionLayoutComboBox sectionLayoutComboBox = new SectionLayoutComboBox();
		layoutHelper.addComponent(sectionLayoutComboBox, "Column Layout", 0, 1);

		this.addComponent(layoutHelper.getLayout());

		HorizontalLayout controlLayout = new HorizontalLayout();
		Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		controlLayout.addComponent(saveBtn);

		Button cancelBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						CreateSectionWindow.this.close();

					}
				});
		controlLayout.addComponent(cancelBtn);

		this.addComponent(controlLayout);
	}

	private static class SectionLayoutComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public SectionLayoutComboBox() {
			super(false, "One Column", "Two Columns");
		}
	}

}
