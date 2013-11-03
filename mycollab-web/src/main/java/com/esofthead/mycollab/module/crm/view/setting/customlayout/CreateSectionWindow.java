package com.esofthead.mycollab.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.view.setting.CrmCustomView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CreateSectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	public CreateSectionWindow(final CrmCustomView customView) {
		super("Create Section");
		center();
		this.setWidth("600px");
		VerticalLayout windowLayout = (VerticalLayout) this.getContent();
		windowLayout.setMargin(false);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 2,
				"100%", "167px", Alignment.MIDDLE_LEFT);
		layoutHelper.getLayout().setMargin(false);
		layoutHelper.getLayout().setWidth("100%");
		layoutHelper.getLayout().addStyleName(UIConstants.COLORED_GRIDLAYOUT);

		final TextField sectionName = new TextField();
		layoutHelper.addComponent(sectionName, "Name", 0, 0);

		final SectionLayoutComboBox sectionLayoutComboBox = new SectionLayoutComboBox();
		layoutHelper.addComponent(sectionLayoutComboBox, "Column Layout", 0, 1);

		this.addComponent(layoutHelper.getLayout());

		HorizontalLayout controlLayout = new HorizontalLayout();
		controlLayout.setWidth("100%");
		controlLayout.setMargin(true);
		controlLayout.setSpacing(true);

		Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						DynaSection section = new DynaSection();
						section.setDeletedSection(false);
						section.setHeader((String) sectionName.getValue());
						section.setLayoutType((LayoutType) sectionLayoutComboBox
								.getValue());

						customView.addActiveSection(section);
						CreateSectionWindow.this.close();
					}
				});
		saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlLayout.addComponent(saveBtn);
		controlLayout.setComponentAlignment(saveBtn, Alignment.MIDDLE_LEFT);

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
		cancelBtn.addStyleName("link");
		controlLayout.addComponent(cancelBtn);
		controlLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_LEFT);
		controlLayout.setExpandRatio(cancelBtn, 1.0f);

		this.addComponent(controlLayout);
	}

	private static class SectionLayoutComboBox extends ComboBox {
		private static final long serialVersionUID = 1L;

		public SectionLayoutComboBox() {
			this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);
			this.setNullSelectionAllowed(false);
			this.addItem(LayoutType.ONE_COLUMN);
			this.setItemCaption(LayoutType.ONE_COLUMN, "One Column");

			this.addItem(LayoutType.TWO_COLUMN);
			this.setItemCaption(LayoutType.TWO_COLUMN, "Two Columns");
			this.select(LayoutType.ONE_COLUMN);
		}
	}

}
