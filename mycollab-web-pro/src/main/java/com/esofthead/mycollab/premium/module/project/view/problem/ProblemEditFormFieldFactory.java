package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.esofthead.mycollab.vaadin.web.ui.ValueComboBox;
import com.vaadin.data.Property;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import org.vaadin.teemu.ratingstars.RatingStars;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 2.0
 */
class ProblemEditFormFieldFactory<B extends Problem> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    ProblemEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
    }

    ProblemEditFormFieldFactory(GenericBeanForm<B> form, boolean isValidateForm) {
        super(form, isValidateForm);
    }

    @Override
    protected Field<?> onCreateField(Object propertyId) {
        Problem problem = attachForm.getBean();
        if (propertyId.equals("description")) {
            final RichTextArea desc = new RichTextArea();
            desc.setRequired(true);
            desc.setNullRepresentation("");
            desc.setRequiredError("Description must be not empty");
            return desc;
        } else if (propertyId.equals("raisedbyuser")) {
            if (problem.getRaisedbyuser() == null) {
                problem.setRaisedbyuser(AppContext.getUsername());
            }
            return new ProjectMemberSelectionField();
        } else if (propertyId.equals("type")) {
        } else if (propertyId.equals("assigntouser")) {
            return new ProjectMemberSelectionField();
        } else if (propertyId.equals("priority")) {
            if (problem.getPriority() == null) {
                problem.setPriority("Medium");
            }
            final ValueComboBox box = new ValueComboBox(false, "High",
                    "Medium", "Low");
            return box;
        } else if (propertyId.equals("status")) {
            if (problem.getStatus() == null) {
                problem.setStatus(StatusI18nEnum.Open.name());
            }
            final I18nValueComboBox box = new I18nValueComboBox(false, StatusI18nEnum.Open, StatusI18nEnum.Closed);
            return box;
        } else if (propertyId.equals("level")) {
            final RatingStars ratingField = new RatingStars();
            ratingField.setMaxValue(5);
            ratingField.setImmediate(true);
            ratingField.setDescription("Problem level");
            ratingField.setValueCaption(ProblemAddViewImpl.getValueCaptions()
                    .values().toArray(new String[5]));

            if (problem.getLevel() != null) {
                ratingField.setValue(problem.getLevel());
            }

            ratingField.addValueChangeListener(new Property.ValueChangeListener() {
                private static final long serialVersionUID = -3277119031169194273L;

                @Override
                public void valueChange(final Property.ValueChangeEvent event) {
                    final Double value = (Double) event.getProperty().getValue();
                    final RatingStars changedRs = (RatingStars) event.getProperty();

                    // reset value captions
                    changedRs.setValueCaption(ProblemAddViewImpl.getValueCaptions().values().toArray(new String[5]));
                    // set "Your Rating" caption
                    if (value == null) {
                        changedRs.setValue(3d);
                    } else {
                        changedRs.setValueCaption((int) Math.round(value), "Your Rating");
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
            tf.setRequiredError("Issue must not be empty");
            return tf;
        }

        return null;
    }
}
