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
package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.esofthead.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class MeetingAddViewImpl extends AbstractView implements MeetingAddView {

	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private MeetingWithBLOBs meeting;

	public MeetingAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(MeetingWithBLOBs item) {
		this.meeting = item;
		editForm.setItemDataSource(new BeanItem<MeetingWithBLOBs>(meeting));
	}

	private class EditForm extends AdvancedEditBeanForm<MeetingWithBLOBs> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		private class FormLayoutFactory extends MeetingFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((meeting.getId() == null) ? "Create Event" : meeting
						.getSubject());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<MeetingWithBLOBs>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return null;
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("subject")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Subject must not be null");
					return tf;
				} else if (propertyId.equals("status")) {
					return new MeetingStatusComboBox();
				} else if (propertyId.equals("startdate")) {
					return new DateTimePicker<MeetingWithBLOBs>("startdate",
							meeting);
				} else if (propertyId.equals("enddate")) {
					return new DateTimePicker<MeetingWithBLOBs>("enddate",
							meeting);
				} else if (propertyId.equals("description")) {
					TextArea descArea = new TextArea();
					descArea.setNullRepresentation("");
					return descArea;
				} else if (propertyId.equals("type")) {
					RelatedEditItemField field = new RelatedEditItemField(
							new String[] { CrmTypeConstants.ACCOUNT,
									CrmTypeConstants.CAMPAIGN,
									CrmTypeConstants.CONTACT,
									CrmTypeConstants.LEAD,
									CrmTypeConstants.OPPORTUNITY,
									CrmTypeConstants.CASE }, meeting);
					field.setType(meeting.getType());
					return field;
				} else if (propertyId.equals("isrecurrence")) {
					return new RecurringActivityCustomField(meeting);
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<MeetingWithBLOBs> getEditFormHandlers() {
		return editForm;
	}

	private class RecurringSettingPanel extends CustomField {
		private static final long serialVersionUID = 1L;

		public RecurringSettingPanel() {
			CheckBox isRecurringBox = new CheckBox();
		}

		@Override
		public Class<?> getType() {
			return Object.class;
		}
	}

	private class MeetingStatusComboBox extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public MeetingStatusComboBox() {
			super();
			setCaption(null);
			this.loadData(new String[] { "Planned", "Held", "Not Held" });
		}
	}
}
