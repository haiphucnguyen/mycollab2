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

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class CustomFieldComponent extends CssLayout {

	private static final long serialVersionUID = 1L;

	private AbstractDynaField field;
	private Label fieldNameLbl;
	private String fieldName;
	private Panel fieldEditPanel;
	private VerticalLayout panelContentLayout = new VerticalLayout();
	private PopupButton editFieldBtn;

	public CustomFieldComponent(AbstractDynaField field) {
		this.setStyleName(UIConstants.CUSTOM_FIELD_COMPONENT);
		this.field = field;
		if (field == null) {
			fieldName = "&nbsp;";
			this.addStyleName("emptyField");
		} else {
			fieldName = field.getDisplayName();
		}
		fieldNameLbl = new Label(fieldName);
		if (isEmptyField()) {
			fieldNameLbl.setContentMode(ContentMode.HTML);
			this.addComponent(fieldNameLbl);
		} else {

			HorizontalLayout fieldWrapper = new HorizontalLayout();
			fieldWrapper.setWidth("100%");
			fieldWrapper.addComponent(fieldNameLbl);
			fieldWrapper.setExpandRatio(fieldNameLbl, 1.0f);

			editFieldBtn = new PopupButton();
			editFieldBtn.setStyleName(UIConstants.POPUP_WITHOUT_INDICATOR);
			editFieldBtn.addStyleName("editFieldBtn");
			editFieldBtn.addStyleName("link");
			editFieldBtn.setIcon(MyCollabResource
					.newResource("icons/16/edit.png"));

			fieldEditPanel = new Panel();
			panelContentLayout = new VerticalLayout();
			panelContentLayout.setSpacing(true);
			panelContentLayout.setMargin(true);
			panelContentLayout.setWidth("100%");
			fieldEditPanel.setContent(panelContentLayout);
			fieldEditPanel.setStyleName(Reindeer.PANEL_LIGHT);
			fieldEditPanel.setWidth("300px");

			createDefaultEditOption();

			editFieldBtn.setContent(fieldEditPanel);
			fieldWrapper.addComponent(editFieldBtn);
			this.addComponent(fieldWrapper);
		}
	}

	protected void createDefaultEditOption() {
		final CheckBox isRequired = new CheckBox("Is Required Field",
				field.isRequired());
		panelContentLayout.addComponent(isRequired);

		Button saveBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent arg0) {
				editFieldBtn.setPopupVisible(false);
				CustomFieldComponent.this.setRequired((Boolean) isRequired
						.getValue());
			}
		});
		saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		saveBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
		panelContentLayout.addComponent(saveBtn);
		((VerticalLayout) fieldEditPanel.getContent()).setComponentAlignment(
				saveBtn, Alignment.MIDDLE_CENTER);
	}

	protected void addFieldEditOption(Component comp) {
		panelContentLayout.addComponent(comp);
	}

	public String getFieldName() {
		return fieldName;
	}

	public boolean isEmptyField() {
		return (field == null);
	}

	public AbstractDynaField getField() {
		return field;
	}

	public void setMandatory(boolean isMandatory) {
		if (isMandatory) {
			fieldNameLbl.setContentMode(ContentMode.HTML);
			fieldNameLbl.setValue("<img src='"
					+ MyCollabResource.newResourceLink("icons/16/lock.png")
					+ "'>&nbsp;" + fieldName);

		} else if (fieldNameLbl.getContentMode() == ContentMode.HTML) {
			fieldNameLbl.setContentMode(ContentMode.TEXT);
			fieldNameLbl.setValue(fieldName);
		}
	}

	public void setRequired(boolean isRequired) {
		if (isRequired) {
			fieldNameLbl.addStyleName("isRequiredField");
		} else {
			fieldNameLbl.removeStyleName("isRequiredField");
		}
		field.setRequired(isRequired);
	}
}
