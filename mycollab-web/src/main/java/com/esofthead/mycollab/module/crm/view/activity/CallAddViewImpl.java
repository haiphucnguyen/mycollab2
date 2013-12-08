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

import com.vaadin.ui.CustomField;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class CallAddViewImpl extends AbstractPageView implements CallAddView {

	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private CallWithBLOBs call;

	public CallAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(CallWithBLOBs item) {
		this.call = item;
		editForm.setItemDataSource(new BeanItem<CallWithBLOBs>(call));
	}

	private class EditForm extends AdvancedEditBeanForm<CallWithBLOBs> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		private class FormLayoutFactory extends CallFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((call.getId() == null) ? "Create Call" : call
						.getSubject());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<CallWithBLOBs>(
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
				} else if (propertyId.equals("assignuser")) {
					ActiveUserComboBox userBox = new ActiveUserComboBox();
					return userBox;
				} else if (propertyId.equals("description")) {
					TextArea descArea = new TextArea();
					descArea.setNullRepresentation("");
					return descArea;
				} else if (propertyId.equals("result")) {
					TextArea resultArea = new TextArea();
					resultArea.setNullRepresentation("");
					return resultArea;
				} else if (propertyId.equals("durationinseconds")) {
					CallDurationControl durationField = new CallDurationControl();
					return durationField;
				} else if (propertyId.equals("purpose")) {
					CallPurposeComboBox purposeField = new CallPurposeComboBox();
					return purposeField;
				} else if (propertyId.equals("status")) {
					CallStatusTypeField field = new CallStatusTypeField();
					return field;
				} else if (propertyId.equals("type")) {
					RelatedEditItemField field = new RelatedEditItemField(
							new String[] { CrmTypeConstants.ACCOUNT,
									CrmTypeConstants.CAMPAIGN,
									CrmTypeConstants.CONTACT,
									CrmTypeConstants.LEAD,
									CrmTypeConstants.OPPORTUNITY,
									CrmTypeConstants.CASE }, call);
					field.setType(call.getType());
					return field;
				} else if (propertyId.equals("startdate")) {
					return new DateTimePicker<CallWithBLOBs>("startdate", call);
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<CallWithBLOBs> getEditFormHandlers() {
		return editForm;
	}

	private class CallPurposeComboBox extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public CallPurposeComboBox() {
			super();
			setCaption(null);
			this.loadData(new String[] { "Prospecting", "Administrative",
					"Negotiation", "Project", "Support" });
		}
	}

	private class CallDurationControl extends CustomField {

		private static final long serialVersionUID = 1L;
		private TextField hourField;
		private ValueComboBox minutesField;

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		private void calculateDurationInSeconds() {
			String hourValue = (String) hourField.getValue();
			String minuteValue = (String) minutesField.getValue();
			int hourVal = 0, minutesVal = 0;
			try {
				hourVal = Integer.parseInt(hourValue);
			} catch (NumberFormatException e) {
				hourField.setValue("");
				hourVal = 0;
			}

			try {
				minutesVal = Integer.parseInt(minuteValue);
			} catch (NumberFormatException e) {
				minutesField.select(null);
				minutesVal = 0;
			}

			if (hourVal != 0 || minutesVal != 0) {
				int seconds = minutesVal * 60 + hourVal * 3600;
				call.setDurationinseconds(seconds);
			}
		}

		@Override
		protected Component initContent() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			hourField = new TextField();
			hourField.setWidth("30px");
			hourField
					.addValueChangeListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							calculateDurationInSeconds();
						}
					});

			layout.addComponent(hourField);

			minutesField = new ValueComboBox();
			minutesField.loadData(new String[] { "0", "15", "30", "45" });
			minutesField.setWidth("40px");
			minutesField
					.addValueChangeListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							calculateDurationInSeconds();

						}
					});

			Integer duration = call.getDurationinseconds();
			if (duration != null && duration != 0) {
				int hours = duration / 3600;
				int minutes = (duration % 3600) / 60;
				hourField.setValue("" + hours);
				minutesField.select("" + minutes);
			}

			layout.addComponent(minutesField);

			layout.addComponent(new Label("(hours/minutes)"));

			return layout;
		}
	}

	private class CallStatusTypeField extends CustomField {

		private static final long serialVersionUID = 1L;

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		protected Component initContent() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);

			CallTypeComboBox typeField = new CallTypeComboBox();
			layout.addComponent(typeField);
			typeField.select(call.getCalltype());

			CallStatusComboBox statusField = new CallStatusComboBox();
			layout.addComponent(statusField);
			statusField.select(call.getStatus());

			return layout;
		}
	}

	private class CallTypeComboBox extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public CallTypeComboBox() {
			super();
			setCaption(null);
			this.setWidth("80px");
			this.loadData(new String[] { "Inbound", "Outbound" });
			this.addValueChangeListener(new Property.ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					call.setCalltype((String) CallTypeComboBox.this.getValue());
				}
			});
		}
	}

	private class CallStatusComboBox extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public CallStatusComboBox() {
			super();
			setCaption(null);
			this.setWidth("100px");
			this.loadData(new String[] { "Planned", "Held", "Not Held" });
			this.addValueChangeListener(new Property.ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					call.setStatus((String) CallStatusComboBox.this.getValue());
				}
			});
		}
	}
}
