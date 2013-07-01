package com.esofthead.mycollab.module.project.ui.components;

import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.vaadin.ui.Label;

public class RelatedReadItemField extends CustomField {

    private static final long serialVersionUID = 1L;
    private Object bean;

    public RelatedReadItemField(Object bean) {
        this.bean = bean;

        try {
            final Integer typeid = (Integer) PropertyUtils.getProperty(
                    RelatedReadItemField.this.bean, "typeid");
            if (typeid == null) {
                this.setCompositionRoot(new Label(""));
                return;
            }

            final String type = (String) PropertyUtils
                    .getProperty(bean, "type");
            if (type == null || type.equals("")) {
                this.setCompositionRoot(new Label(""));
                return;
            }

            ButtonLink relatedLink = null;

            if ("Task".equals(type)) {
            } else if ("MileStone".equals(type)) {
            } else if ("Bug".equals(type)) {
            }

            if (relatedLink != null) {
                this.setCompositionRoot(relatedLink);
            } else {
                this.setCompositionRoot(new Label(""));
            }

        } catch (Exception e) {
            this.setCompositionRoot(new Label(""));
        }
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}
