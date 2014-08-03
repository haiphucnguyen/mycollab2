package com.esofthead.mycollab.premium.module.project.view.risk;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.I18nValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.data.Property;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 * @param <B>
 */
class RiskEditFormFieldFactory<B extends Risk> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {

	private static final long serialVersionUID = 1L;

	RiskEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	RiskEditFormFieldFactory(GenericBeanForm<B> form, boolean isValidateForm) {
		super(form, isValidateForm);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		Risk risk = attachForm.getBean();
		if (propertyId.equals("description")) {
			final RichTextArea desc = new RichTextArea();
			desc.setRequired(true);
			desc.setNullRepresentation("");
			desc.setRequiredError("Please enter a Desciption");
			return desc;
		} else if (propertyId.equals("raisedbyuser")) {
			if (risk.getRaisedbyuser() == null) {
				risk.setRaisedbyuser(AppContext.getUsername());
			}
			return new ProjectMemberSelectionField();
		} else if (propertyId.equals("assigntouser")) {
			return new ProjectMemberSelectionField();
		} else if (propertyId.equals("response")) {
			return new RichTextArea();
		} else if (propertyId.equals("consequence")) {
			if (risk.getConsequence() == null) {
				risk.setConsequence("Marginal");
			}
			final ValueComboBox box = new ValueComboBox(false, "Catastrophic",
					"Critical", "Marginal", "Negligible");
			return box;
		} else if (propertyId.equals("probalitity")) {
			if (risk.getProbalitity() == null) {
				risk.setProbalitity("Possible");
			}
			final ValueComboBox box = new ValueComboBox(false, "Certain",
					"Likely", "Possible", "Unlikely", "Rare");
			return box;
		} else if (propertyId.equals("status")) {
			if (risk.getStatus() == null) {
				risk.setStatus(StatusI18nEnum.Open.name());
			}
			final I18nValueComboBox box = new I18nValueComboBox(false,
					StatusI18nEnum.Open, StatusI18nEnum.Closed);
			return box;
		} else if (propertyId.equals("level")) {
			final RatingStars ratingField = new RatingStars();
			ratingField.setMaxValue(5);
			ratingField.setImmediate(true);
			ratingField.setDescription("Risk level");
			ratingField.setValueCaption(RiskAddViewImpl.getValueCaptions()
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
							changedRs.setValueCaption(RiskAddViewImpl
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
