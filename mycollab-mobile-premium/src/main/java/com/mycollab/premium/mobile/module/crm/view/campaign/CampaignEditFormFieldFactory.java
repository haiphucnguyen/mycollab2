package com.mycollab.premium.mobile.module.crm.view.campaign;

import com.mycollab.mobile.module.user.ui.components.ActiveUserComboBox;
import com.mycollab.mobile.ui.CurrencyComboBoxField;
import com.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * @param <B>
 * @author MyCollab Ltd.
 * @since 4.1
 */
class CampaignEditFormFieldFactory<B extends CampaignWithBLOBs> extends AbstractBeanFieldGroupEditFieldFactory<B> {
    private static final long serialVersionUID = 1L;

    CampaignEditFormFieldFactory(GenericBeanForm<B> form) {
        super(form);
    }

    @Override
    protected AbstractField<?> onCreateField(Object propertyId) {

        if ("type".equals(propertyId)) {
            CampaignTypeListSelect typeCombo = new CampaignTypeListSelect();
            typeCombo.setWidth("100%");
            return typeCombo;
        } else if ("status".equals(propertyId)) {
            CampaignStatusListSelect statusCombo = new CampaignStatusListSelect();
            statusCombo.setWidth("100%");
            return statusCombo;
        } else if ("campaignname".equals(propertyId)) {
            TextField tf = new TextField();
            if (isValidateForm) {
                tf.setNullRepresentation("");
                tf.setRequired(true);
                tf.setRequiredError("Name must not be null");
            }
            return tf;
        } else if ("description".equals(propertyId)) {
            TextArea descArea = new TextArea();
            descArea.setNullRepresentation("");
            return descArea;
        } else if ("assignuser".equals(propertyId)) {
            ActiveUserComboBox userBox = new ActiveUserComboBox();
            userBox.select(attachForm.getBean().getAssignuser());
            return userBox;
        } else if (propertyId.equals("currencyid")) {
            return new CurrencyComboBoxField();
        }
        return null;
    }
}
