package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.vaadin.AppContext;
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
public abstract class PopupBeanFieldBuilder<B> {
    protected Object value;
    protected String caption;
    protected Field field;
    protected B bean;
    protected String bindProperty;
    protected BeanFieldGroup fieldGroup;
    protected ICrudService crudService;

    public PopupBeanFieldBuilder withValue(Object value) {
        this.value = value;
        return this;
    }

    public PopupBeanFieldBuilder withField(Field field) {
        this.field = field;
        return this;
    }

    public PopupBeanFieldBuilder withBean(B bean) {
        this.bean = bean;
        return this;
    }

    public PopupBeanFieldBuilder withBindProperty(String bindProperty) {
        this.bindProperty = bindProperty;
        return this;
    }

    public PopupBeanFieldBuilder withCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public PopupBeanFieldBuilder withService(ICrudService crudService) {
        this.crudService = crudService;
        return this;
    }

    abstract protected String generateSmallContentAsHtml();

    public PopupView build() {
        final PopupView view = new PopupView(new PopupContent(generateSmallContentAsHtml()));
        view.addPopupVisibilityListener(new PopupView.PopupVisibilityListener() {
            @Override
            public void popupVisibilityChange(PopupView.PopupVisibilityEvent event) {
                if (event.isPopupVisible()) {
                    BeanItem item = new BeanItem(bean);
                    fieldGroup = new BeanFieldGroup(bean.getClass());
                    fieldGroup.setBuffered(true);
                    fieldGroup.setItemDataSource(item);

                    PopupView.Content content = view.getContent();
                    MVerticalLayout layout = (MVerticalLayout) content.getPopupComponent();
                    layout.removeAllComponents();
                    Label headerLbl = new Label(caption, ContentMode.HTML);
                    headerLbl.addStyleName("h2");
                    layout.with(headerLbl);
                    layout.with(field);
                    fieldGroup.bind(field, bindProperty);
                } else {
                    try {
                        if (fieldGroup.isModified()) {
                            fieldGroup.commit();
                            PopupContent content = (PopupContent) view.getContent();
                            crudService.updateWithSession(bean, AppContext.getUsername());
                            content.setMinimizedValueAsHTML(generateSmallContentAsHtml());
                        }
                    } catch (FieldGroup.CommitException e) {
                        throw new MyCollabException(e);
                    }
                }
            }
        });
        view.setStyleName("block-popupedit");
        view.setDescription("Click to edit");
        return view;
    }

    private static class PopupContent implements PopupView.Content {
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
