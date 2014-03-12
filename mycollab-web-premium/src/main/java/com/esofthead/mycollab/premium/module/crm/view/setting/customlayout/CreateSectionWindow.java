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
package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
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

	public CreateSectionWindow(final ICrmCustomView customView) {
		super("Create Section");
		center();
		this.setWidth("600px");

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(false);
		this.setContent(contentLayout);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 2,
				"100%", "167px", Alignment.MIDDLE_LEFT);
		layoutHelper.getLayout().setMargin(false);
		layoutHelper.getLayout().setWidth("100%");
		layoutHelper.getLayout().addStyleName(UIConstants.COLORED_GRIDLAYOUT);

		final TextField sectionName = new TextField();
		layoutHelper.addComponent(sectionName, "Name", 0, 0);

		final SectionLayoutComboBox sectionLayoutComboBox = new SectionLayoutComboBox();
		layoutHelper.addComponent(sectionLayoutComboBox, "Column Layout", 0, 1);

		contentLayout.addComponent(layoutHelper.getLayout());

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
		saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
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

		contentLayout.addComponent(controlLayout);
	}

	private static class SectionLayoutComboBox extends ComboBox {
		private static final long serialVersionUID = 1L;

		public SectionLayoutComboBox() {
			this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
			this.setNullSelectionAllowed(false);
			this.addItem(LayoutType.ONE_COLUMN);
			this.setItemCaption(LayoutType.ONE_COLUMN, "One Column");

			this.addItem(LayoutType.TWO_COLUMN);
			this.setItemCaption(LayoutType.TWO_COLUMN, "Two Columns");
			this.select(LayoutType.ONE_COLUMN);
		}
	}

}
