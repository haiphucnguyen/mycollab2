package com.esofthead.mycollab.module.project.view.risk;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

public class RiskEditFormFieldFactory extends DefaultEditFormFieldFactory{
	private static final long serialVersionUID = 1L;
	private Risk risk;
	public RiskEditFormFieldFactory(Risk risk){
		this.risk = risk;
	}
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
			if (this.risk.getRaisedbyuser() == null) {
				this.risk.setRaisedbyuser(AppContext
						.getUsername());
			}
			return new ProjectMemberComboBox();
		} else if (propertyId.equals("assigntouser")) {
			return new ProjectMemberComboBox();
		} else if (propertyId.equals("response")) {
			return new RichTextArea();
		} else if (propertyId.equals("consequence")) {
			if (this.risk.getConsequence() == null) {
				this.risk.setConsequence("Marginal");
			}
			final ValueComboBox box = new ValueComboBox(false,
					"Catastrophic", "Critical", "Marginal",
					"Negligible");
			return box;
		} else if (propertyId.equals("probalitity")) {
			if (this.risk.getProbalitity() == null) {
				this.risk.setProbalitity("Possible");
			}
			final ValueComboBox box = new ValueComboBox(false,
					"Certain", "Likely", "Possible", "Unlikely", "Rare");
			return box;
		} else if (propertyId.equals("status")) {
			if (this.risk.getStatus() == null) {
				risk.setStatus("Open");
			}
			final ValueComboBox box = new ValueComboBox(false, "Open",
					"Closed");
			return box;
		} else if (propertyId.equals("level")) {
			final RatingStars ratingField = new RatingStars();
			ratingField.setMaxValue(5);
			ratingField.setImmediate(true);
			ratingField.setDescription("Risk level");
			ratingField.setValueCaption(RiskAddViewImpl.getValueCaptions()
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
							.setValueCaption(RiskAddViewImpl.getValueCaptions()
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
