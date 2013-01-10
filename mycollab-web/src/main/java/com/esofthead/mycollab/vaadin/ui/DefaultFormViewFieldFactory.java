package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.crm.ui.components.AttachmentPanel;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;
import org.vaadin.addon.customfield.CustomField;

public class DefaultFormViewFieldFactory extends DefaultFieldFactory {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    public Field createField(Item item, Object propertyId,
            com.vaadin.ui.Component uiContext) {

        Field field = onCreateField(item, propertyId, uiContext);
        if (field == null) {
            Object bean = ((BeanItem<Object>) item).getBean();

            try {
                String propertyValue = BeanUtils.getProperty(bean,
                        (String) propertyId);
                field = new FormViewField(propertyValue);
            } catch (Exception e) {
                field = new FormViewField("Error");
            }
        }

        return field;
    }

    protected Field onCreateField(Item item, Object propertyId,
            com.vaadin.ui.Component uiContext) {
        return null;
    }

    public static class FormViewField extends CustomField {

        private static final long serialVersionUID = 1L;

        public FormViewField(String value) {
            this(value, Label.CONTENT_DEFAULT);
        }

        public FormViewField(String value, int contentMode) {
            Label l = new Label();
            l.setWidth("100%");
            l.setContentMode(contentMode);
            this.setCompositionRoot(l);
            l.setValue(value);
        }

        @Override
        public Class<?> getType() {
            return String.class;
        }
    }

    public static class FormDateViewField extends CustomField {

        public FormDateViewField(Date date) {
            Label l = new Label();
            l.setWidth("100%");
            l.setValue(AppContext.formatDate(date));
            this.setCompositionRoot(l);
        }

        @Override
        public Class<?> getType() {
            return Object.class;
        }
    }

    public static class FormLinkViewField extends CustomField {

        private static final long serialVersionUID = 1L;

        public FormLinkViewField(String value, Button.ClickListener listener) {
            ButtonLink l = new ButtonLink(value, listener);
            l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            this.setCompositionRoot(l);
        }

        @Override
        public Class<?> getType() {
            return String.class;
        }
    }

    public static class UserLinkViewField extends CustomField {

        public UserLinkViewField(String username, String fullName) {
            UserLink l = new UserLink(username, fullName);
            l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            this.setCompositionRoot(l);
        }

        @Override
        public Class<?> getType() {
            return Object.class;
        }
    }

    public static class FormEmailLinkViewField extends CustomField {

        private static final long serialVersionUID = 1L;

        public FormEmailLinkViewField(String email) {
            EmailLink l = new EmailLink(email);
            l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            this.setCompositionRoot(l);
        }

        @Override
        public Class<?> getType() {
            return String.class;
        }
    }

    public static interface AttachmentUploadField extends Field {

        void saveContentsToRepo(String type, int typeId);
    }

    public static class FormAttachmentUploadField extends CustomField implements AttachmentUploadField {

        @Override
        public Class<?> getType() {
            return Object.class;
        }
        private AttachmentPanel attachmentPanel;

        public FormAttachmentUploadField() {
            attachmentPanel = new AttachmentPanel();
            this.setCompositionRoot(attachmentPanel);
        }

        @Override
        public void saveContentsToRepo(String type, int typeId) {
            attachmentPanel.saveContentsToRepo(type, typeId);
        }
    }
}
