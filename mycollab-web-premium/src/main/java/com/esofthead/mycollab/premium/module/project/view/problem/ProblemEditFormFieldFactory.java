package com.esofthead.mycollab.premium.module.project.view.problem;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

public class ProblemEditFormFieldFactory extends DefaultEditFormFieldFactory {
	private static final long serialVersionUID = 1L;
	private Problem problem;

	public ProblemEditFormFieldFactory(Problem problem) {
		this.problem = problem;
	}

	@Override
	protected Field onCreateField(final Item item, final Object propertyId,
			final com.vaadin.ui.Component uiContext) {

		if (propertyId.equals("description")) {
			final RichTextArea risk = new RichTextArea();
			risk.setRequired(true);
			risk.setNullRepresentation("");
			risk.setRequiredError("Please enter a Desciption");
			return risk;
		} else if (propertyId.equals("raisedbyuser")) {
			if (this.problem.getRaisedbyuser() == null) {
				this.problem.setRaisedbyuser(AppContext.getUsername());
			}
			return new ProjectMemberComboBox();
		} else if (propertyId.equals("type")) {
		} else if (propertyId.equals("assigntouser")) {
			return new ProjectMemberComboBox();
		} else if (propertyId.equals("priority")) {
			if (this.problem.getPriority() == null) {
				this.problem.setPriority("Medium");
			}
			final ValueComboBox box = new ValueComboBox(false, "High",
					"Medium", "Low");
			return box;
		} else if (propertyId.equals("status")) {
			if (this.problem.getStatus() == null) {
				this.problem.setStatus("Open");
			}
			final ValueComboBox box = new ValueComboBox(false, "Open", "Closed");
			return box;
		} else if (propertyId.equals("level")) {
			final RatingStars ratingField = new RatingStars();
			ratingField.setMaxValue(5);
			ratingField.setImmediate(true);
			ratingField.setDescription("Problem level");
			ratingField.setValueCaption(ProblemAddViewImpl.getValueCaptions()
					.values().toArray(new String[5]));

			ratingField
					.addValueChangeListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = -3277119031169194273L;

						@Override
						public void valueChange(
								final Property.ValueChangeEvent event) {
							final Double value = (Double) event.getProperty()
									.getValue();
							final RatingStars changedRs = (RatingStars) event
									.getProperty();

							// reset value captions
							changedRs.setValueCaption(ProblemAddViewImpl
									.getValueCaptions().values()
									.toArray(new String[5]));
							// set "Your Rating" caption
							if (value == null) {
								changedRs.setValue(3d);
							} else {
								changedRs.setValueCaption(
										(int) Math.round(value), "Your Rating");
							}

						}
					});
			return ratingField;
		} else if (propertyId.equals("resolution")) {
			return new RichTextArea();
		}

		if (propertyId.equals("issuename")) {
			final TextField tf = new TextField();
			tf.setNullRepresentation("");
			tf.setRequired(true);
			tf.setRequiredError("Please enter a Name");
			return tf;
		}

		return null;
	}
}
