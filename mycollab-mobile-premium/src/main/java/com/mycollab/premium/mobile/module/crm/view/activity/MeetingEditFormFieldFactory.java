package com.mycollab.premium.mobile.module.crm.view.activity;

import com.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.DummyCustomField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.data.HasValue;
import org.vaadin.touchkit.ui.DatePicker;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
// TODO: revise this class
public class MeetingEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<MeetingWithBLOBs> {
    private static final long serialVersionUID = 1L;

    MeetingEditFormFieldFactory(GenericBeanForm<MeetingWithBLOBs> form) {
        super(form);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        if (propertyId.equals("subject")) {
            TextField tf = new TextField();
            if (isValidateForm) {
//                tf.setNullRepresentation("");
//                tf.setRequired(true);
//                tf.setRequiredError("Subject must not be null");
            }

            return tf;
        } else if (propertyId.equals("status")) {
            return new MeetingStatusListSelect();
        } else if (propertyId.equals("startdate")) {
            return new DatePicker();
        } else if (propertyId.equals("enddate")) {
            return new DatePicker();
        } else if (propertyId.equals("description")) {
            TextArea descArea = new TextArea();
//            descArea.setNullRepresentation("");
            return descArea;
        } else if (propertyId.equals("typeid")) {
            return new RelatedItemSelectionField(attachForm.getBean());
        } else if (propertyId.equals("type")) {
            return new DummyCustomField<String>();
        } else if (propertyId.equals("isrecurrence")) {
            return null;
        }
        return null;
    }

}
