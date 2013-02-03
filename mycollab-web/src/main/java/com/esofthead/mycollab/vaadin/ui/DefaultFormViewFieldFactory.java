package com.esofthead.mycollab.vaadin.ui;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.vaadin.addon.customfield.CustomField;
import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

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

    public static class FormUrlLinkViewField extends CustomField {

        private static final long serialVersionUID = 1L;

        public FormUrlLinkViewField(String url) {
            url = (url == null) ? "" : url;
            Link link = new UrlLink(url);
            link.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            this.setCompositionRoot(link);
        }

        @Override
        public Class<?> getType() {
            return String.class;
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

    public static class FormContainerViewField extends CustomField {

        private final CssLayout layout;

        public FormContainerViewField() {
            layout = new CssLayout();
            layout.setWidth("100%");
            this.setCompositionRoot(layout);
            this.setStyleName(UIConstants.FORM_CONTAINER_VIEW);
        }

        public void addComponentField(Component component) {
            layout.addComponent(component);
        }

        @Override
        public Class<?> getType() {
            return Object.class;
        }
    }
    
    public static class FormContainerHorizontalViewField extends CustomField {

        private final HorizontalLayout layout;

        public FormContainerHorizontalViewField() {
            layout = new HorizontalLayout();
            layout.setWidth("100%");
            layout.setSpacing(true);
            this.setCompositionRoot(layout);
        }

        public void addComponentField(Component component) {
            layout.addComponent(component);
        }

        @Override
        public Class<?> getType() {
            return Object.class;
        }
    }

    public static interface AttachmentUploadField extends Field {

        void saveContentsToRepo(String type, int typeId);
    }

    public static class FormAttachmentDisplayField extends CustomField {

        @Override
        public Class<?> getType() {
            return Object.class;
        }

        public FormAttachmentDisplayField(String type, int typeid) {
            Component comp = AttachmentDisplayComponent
                    .getAttachmentDisplayComponent(type, typeid);
            if (comp == null) {
                this.setCompositionRoot(new Label());
            } else {
                this.setCompositionRoot(comp);
            }
        }
    }

    public static class FormAttachmentUploadField extends CustomField implements
            AttachmentUploadField {

        private final MultiFileUploadExt uploadExt;
        private final AttachmentPanel attachmentPanel;

        @Override
        public Class<?> getType() {
            return Object.class;
        }

        public FormAttachmentUploadField() {
            VerticalLayout layout = new VerticalLayout();
            attachmentPanel = new AttachmentPanel();
            uploadExt = new MultiFileUploadExt(attachmentPanel);
            layout.addComponent(attachmentPanel);
            layout.addComponent(uploadExt);
            this.setCompositionRoot(layout);
        }

        public void getAttachments(String type, int typeid) {
            attachmentPanel.getAttachments(type, typeid);
        }

        @Override
        public void saveContentsToRepo(String type, int typeId) {
            attachmentPanel.saveContentsToRepo(type, typeId);
        }
    }
}
