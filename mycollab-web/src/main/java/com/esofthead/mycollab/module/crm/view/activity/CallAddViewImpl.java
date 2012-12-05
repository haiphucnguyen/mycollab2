package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Collection;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionWindow;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CallAddViewImpl extends AbstractView implements CallAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	private Call call;

	public CallAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Call item) {
		this.call = item;
		editForm.setItemDataSource(new BeanItem<Call>(call));
	}

	private class EditForm extends AdvancedEditBeanForm<Call> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource,
				Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends CallFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Call>(EditForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
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
					UserComboBox userBox = new UserComboBox();
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
					CallRelatedItemField field = new CallRelatedItemField();
					return field;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Call> getEditFormHandlers() {
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

		public CallDurationControl() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			hourField = new TextField();
			hourField.setWidth("30px");
			layout.addComponent(hourField);

			minutesField = new ValueComboBox();
			minutesField.loadData(new String[] { "00", "15", "30", "45" });
			minutesField.setWidth("40px");
			layout.addComponent(minutesField);

			layout.addComponent(new Label("(hours/minutes)"));

			this.setCompositionRoot(layout);
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}
	}

	private class CallStatusTypeField extends CustomField {
		private static final long serialVersionUID = 1L;

		public CallStatusTypeField() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.addComponent(new CallTypeComboBox());
			layout.addComponent(new CallStatusComboBox());

			this.setCompositionRoot(layout);
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}
	}

	private class CallTypeComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public CallTypeComboBox() {
			super();
			setCaption(null);
			this.setWidth("80px");
			this.loadData(new String[] { "Inbound", "Outbound" });
		}
	}

	private class CallStatusComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public CallStatusComboBox() {
			super();
			setCaption(null);
			this.setWidth("100px");
			this.loadData(new String[] { "Planned", "Held", "Not Held" });
		}
	}
	
	class CallRelatedItemField extends CustomField implements FieldSelection {
		private static final long serialVersionUID = 1L;

		private RelatedItemComboBox relatedItemComboBox;

		private TextField itemField;
		private Embedded browseBtn;
		private Embedded clearBtn;

		public CallRelatedItemField() {

			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);

			relatedItemComboBox = new RelatedItemComboBox();
			layout.addComponent(relatedItemComboBox);

			itemField = new TextField();
			itemField.setEnabled(true);
			layout.addComponent(itemField);
			layout.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);

			browseBtn = new Embedded(null, new ThemeResource(
					"icons/16/browseItem.png"));
			browseBtn.addListener(new MouseEvents.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
					String type = (String) relatedItemComboBox.getValue();
					if ("Account".equals(type)) {
						AccountSelectionWindow accountWindow = new AccountSelectionWindow(
								CallRelatedItemField.this);
						getWindow().addWindow(accountWindow);
						accountWindow.show();
					} else if ("Campaign".equals(type)) {

					} else {
						relatedItemComboBox.focus();
					}
				}
			});

			layout.addComponent(browseBtn);
			layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

			clearBtn = new Embedded(null, new ThemeResource(
					"icons/16/clearItem.png"));
			clearBtn.addListener(new MouseEvents.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void click(ClickEvent event) {
					call.setType("");
					call.setTypeid(null);
				}
			});

			layout.addComponent(clearBtn);
			layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

			this.setCompositionRoot(layout);
		}

		@Override
		public Class<?> getType() {
			return (new String[2]).getClass();
		}

		private class RelatedItemComboBox extends ValueComboBox {
			private static final long serialVersionUID = 1L;

			public RelatedItemComboBox() {
				super();
				setCaption(null);
				this.setWidth("100px");
				this.loadData(new String[] { "Account", "Campaign", "Contact",
						"Lead", "Opportunity", "Case" });
			}
		}

		@Override
		public void fireValueChange(Object data) {
			if (data instanceof SimpleAccount) {
				call.setType("Account");
				call.setTypeid(((SimpleAccount) data).getId());
				itemField.setValue(((SimpleAccount) data).getAccountname());
			}
		}

	}

}
