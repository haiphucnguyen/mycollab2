package com.esofthead.mycollab.module.project.view.risk;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class RiskAddViewImpl extends AbstractView implements RiskAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private Risk risk;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
			5);

	static {
		RiskAddViewImpl.valueCaptions.put(1, "Epic Fail");
		RiskAddViewImpl.valueCaptions.put(2, "Poor");
		RiskAddViewImpl.valueCaptions.put(3, "OK");
		RiskAddViewImpl.valueCaptions.put(4, "Good");
		RiskAddViewImpl.valueCaptions.put(5, "Excellent");
	}

	public RiskAddViewImpl() {
		super();
		this.setMargin(true);
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final Risk risk) {
		this.risk = risk;
		this.editForm.setItemDataSource(new BeanItem<Risk>(risk));
	}

	private class EditForm extends AdvancedEditBeanForm<Risk> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends RiskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Risk");
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Risk>(
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
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("description")) {
					final RichTextArea risk = new RichTextArea();
					risk.setRequired(true);
					risk.setNullRepresentation("");
					risk.setRequiredError("Please enter a Desciption");
					return risk;
				} else if (propertyId.equals("raisedbyuser")) {
					if (RiskAddViewImpl.this.risk.getRaisedbyuser() == null) {
						RiskAddViewImpl.this.risk.setRaisedbyuser(AppContext
								.getUsername());
					}
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("assigntouser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("response")) {
					return new RichTextArea();
				} else if (propertyId.equals("consequence")) {
					if (RiskAddViewImpl.this.risk.getConsequence() == null) {
						RiskAddViewImpl.this.risk.setConsequence("Marginal");
					}
					final ValueComboBox box = new ValueComboBox(false,
							"Catastrophic", "Critical", "Marginal",
							"Negligible");
					return box;
				} else if (propertyId.equals("probalitity")) {
					if (RiskAddViewImpl.this.risk.getProbalitity() == null) {
						RiskAddViewImpl.this.risk.setProbalitity("Possible");
					}
					final ValueComboBox box = new ValueComboBox(false,
							"Certain", "Likely", "Possible", "Unlikely", "Rare");
					return box;
				} else if (propertyId.equals("status")) {
					if (RiskAddViewImpl.this.risk.getStatus() == null) {
						RiskAddViewImpl.this.risk.setStatus("Open");
					}
					final ValueComboBox box = new ValueComboBox(false, "Open",
							"Closed");
					return box;
				} else if (propertyId.equals("level")) {
					final RatingStars ratingField = new RatingStars();
					ratingField.setMaxValue(5);
					ratingField.setImmediate(true);
					ratingField.setDescription("Risk level");
					ratingField.setValueCaption(RiskAddViewImpl.valueCaptions
							.values().toArray(new String[5]));

					ratingField.addListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = -3277119031169194273L;

						@Override
						public void valueChange(
								final Property.ValueChangeEvent event) {
							final Double value = (Double) event.getProperty()
									.getValue();
							final RatingStars changedRs = (RatingStars) event
									.getProperty();

							// reset value captions
							changedRs
									.setValueCaption(RiskAddViewImpl.valueCaptions
											.values().toArray(new String[5]));
							// set "Your Rating" caption
							if (value == null) {
								changedRs.setValue(3);
							} else {
								changedRs.setValueCaption(
										(int) Math.round(value), "Your Rating");
							}

						}
					});
					return ratingField;
				}

				if (propertyId.equals("riskname")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Summary");
					return tf;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Risk> getEditFormHandlers() {
		return this.editForm;
	}
}
