package com.esofthead.mycollab.module.project.view.settings.component;

import com.esofthead.mycollab.module.project.i18n.ComponentI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class ComponentEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<Component> {
    private static final long serialVersionUID = 1L;

    public ComponentEditFormFieldFactory(GenericBeanForm<Component> form) {
        super(form);
    }

    @Override
    protected Field<?> onCreateField(final Object propertyId) {
        if (Component.Field.componentname.equalTo(propertyId)) {
            final TextField tf = new TextField();
            if (isValidateForm) {
                tf.setNullRepresentation("");
                tf.setRequired(true);
                tf.setRequiredError(AppContext.getMessage(ComponentI18nEnum.FORM_COMPONENT_ERROR));
            }
            return tf;
        } else if (Component.Field.description.equalTo(propertyId)) {
            return new RichTextArea();
        } else if (Component.Field.userlead.equalTo(propertyId)) {
            return new ProjectMemberSelectionField();
        }

        return null;
    }
}
