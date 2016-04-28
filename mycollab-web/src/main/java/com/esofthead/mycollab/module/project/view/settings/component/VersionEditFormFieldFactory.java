package com.esofthead.mycollab.module.project.view.settings.component;

import com.esofthead.mycollab.module.project.i18n.VersionI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DateFieldExt;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
public class VersionEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<Version> {
    private static final long serialVersionUID = 1L;

    public VersionEditFormFieldFactory(GenericBeanForm<Version> form) {
        super(form);
    }

    @Override
    protected Field<?> onCreateField(final Object propertyId) {
        if (Version.Field.versionname.equalTo(propertyId)) {
            final TextField tf = new TextField();
            if (isValidateForm) {
                tf.setNullRepresentation("");
                tf.setRequired(true);
                tf.setRequiredError(AppContext.getMessage(VersionI18nEnum.FORM_VERSION_ERROR_MSG));
            }
            return tf;
        } else if (Version.Field.description.equalTo(propertyId)) {
            return new RichTextArea();
        } else if (Version.Field.duedate.equalTo(propertyId)) {
            final DateFieldExt dateField = new DateFieldExt();
            dateField.setResolution(Resolution.DAY);
            return dateField;
        }

        return null;
    }
}
