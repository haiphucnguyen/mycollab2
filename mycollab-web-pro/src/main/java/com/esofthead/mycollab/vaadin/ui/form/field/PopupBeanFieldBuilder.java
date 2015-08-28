package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.LazyPopupView;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.label.ContentMode;
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
    protected String description = "Click to edit";
    protected Field field;
    protected B bean;
    protected String bindProperty;
    protected BeanFieldGroup fieldGroup;
    protected ICrudService crudService;

    public PopupBeanFieldBuilder withValue(Object value) {
        this.value = value;
        return this;
    }

    public PopupBeanFieldBuilder withDescription(String description) {
        this.description = description;
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

    protected String generateSmallAsHtmlAfterUpdate() {
        return generateSmallContentAsHtml();
    }

    protected String generateDescription() {
        return description;
    }

    public PopupView build() {
        final PopupView view = new BeanPopupView(generateSmallContentAsHtml());
        view.setDescription(description);
        return view;
    }

    private class BeanPopupView extends LazyPopupView {
        BeanPopupView(String valueAsHtml) {
            super(valueAsHtml);
        }

        @Override
        protected void doHide() {
            try {
                if (fieldGroup.isModified()) {
                    fieldGroup.commit();
                    crudService.updateWithSession(bean, AppContext.getUsername());
                    setMinimizedValueAsHTML(generateSmallAsHtmlAfterUpdate());
                    BeanPopupView.this.setDescription(generateDescription());
                }
            } catch (FieldGroup.CommitException e) {
                throw new MyCollabException(e);
            }
        }

        @Override
        protected void doShow() {
            BeanItem item = new BeanItem(bean);
            fieldGroup = new BeanFieldGroup(bean.getClass());
            fieldGroup.setBuffered(true);
            fieldGroup.setItemDataSource(item);

            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            Label headerLbl = new Label(caption, ContentMode.HTML);
            headerLbl.addStyleName("h2");
            layout.with(headerLbl);
            layout.with(field);
            fieldGroup.bind(field, bindProperty);
        }
    }
}
