package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public class PopupBeanField extends PopupView {
    private String caption;
    private Field field;
    private Object bean;
    private String bindProperty;
    private BeanFieldGroup fieldGroup;

    public PopupBeanField(final String smallContent) {
        super(new PopupContent(smallContent));

        this.addPopupVisibilityListener(new PopupVisibilityListener() {
            @Override
            public void popupVisibilityChange(PopupVisibilityEvent event) {
                if (event.isPopupVisible()) {
                    BeanItem item = new BeanItem(bean);
                    fieldGroup = new BeanFieldGroup(bean.getClass());
                    fieldGroup.setBuffered(true);
                    fieldGroup.setItemDataSource(item);

                    Content content = PopupBeanField.this.getContent();
                    MVerticalLayout layout = (MVerticalLayout) content.getPopupComponent();
                    layout.removeAllComponents();
                    layout.with(new Label(caption, ContentMode.HTML));
                    layout.with(field);
                    fieldGroup.bind(field, bindProperty);
                } else {
                    try {
                        fieldGroup.commit();
                        PopupContent content = (PopupContent) PopupBeanField.this.getContent();
                    } catch (FieldGroup.CommitException e) {
                        throw new MyCollabException(e);
                    }
                }
            }
        });
        this.setStyleName("block-popupedit");
        this.setDescription("Click to edit");
    }

    public PopupBeanField withField(Field field) {
        this.field = field;
        return this;
    }

    public PopupBeanField withBean(Object bean) {
        this.bean = bean;
        return this;
    }

    public PopupBeanField withBindProperty(String bindProperty) {
        this.bindProperty = bindProperty;
        return this;
    }

    public PopupBeanField withCaption(String caption) {
        this.caption = caption;
        return this;
    }

    private static class PopupContent implements Content {
        private String valueAsHtml;
        private MVerticalLayout content;

        public PopupContent(String valueAsHtml) {
            this.valueAsHtml = valueAsHtml;
            content = new MVerticalLayout();
        }

        public void setMinimizedValueAsHTML(String valueAsHtml) {
            this.valueAsHtml = valueAsHtml;
        }

        @Override
        public String getMinimizedValueAsHTML() {
            return valueAsHtml;
        }

        @Override
        public Component getPopupComponent() {
            return content;
        }
    }
}
