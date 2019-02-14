package com.mycollab.pro.vaadin.web.ui.field;

import com.google.common.base.MoreObjects;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.persistence.service.ICrudService;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.PropertyChangedEvent;
import com.mycollab.vaadin.web.ui.LazyPopupView;
import com.vaadin.data.Binder;
import com.vaadin.data.Converter;
import com.vaadin.data.HasValue;
import com.vaadin.server.SerializableFunction;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import org.apache.commons.beanutils.PropertyUtils;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public abstract class PopupBeanFieldBuilder<B> {
    protected Object value;
    protected String caption;
    protected String description;
    protected HasValue field;
    private boolean hasPermission = true;
    protected B bean;
    private String bindProperty;
    private Binder binder;
    private ICrudService crudService;

    public PopupBeanFieldBuilder withValue(Object value) {
        this.value = value;
        return this;
    }

    public PopupBeanFieldBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PopupBeanFieldBuilder withField(HasValue<?> field) {
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

    public PopupBeanFieldBuilder withHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
        return this;
    }

    public boolean isPermission() {
        return hasPermission;
    }

    public void save() {
        if (crudService != null) {
            crudService.updateSelectiveWithSession(bean, UserUIContext.getUsername());
        }
    }

    abstract protected String generateSmallContentAsHtml();

    protected String generateSmallAsHtmlAfterUpdate() {
        return generateSmallContentAsHtml();
    }

    protected String generateDescription() {
        return (StringUtils.isBlank(description)) ? MoreObjects.firstNonNull(caption,
                UserUIContext.getMessage(GenericI18Enum.ACTION_CLICK_TO_EDIT)) : description;
    }

    public PopupView build() {
        PopupView view = new BeanPopupView(generateSmallContentAsHtml());
        view.setDescription(generateDescription(), ContentMode.HTML);
        return view;
    }

    private class BeanPopupView extends LazyPopupView {
        BeanPopupView(String valueAsHtml) {
            super(valueAsHtml);
        }

        @Override
        protected void doHide() {
            if (binder.hasChanges()) {
                value = field.getValue();
                try {
                    if (field instanceof Converter) {
                        value = ((Converter) field).convertToModel(value, null).getOrThrow(SerializableFunction.identity());
                    }
                    PropertyUtils.setProperty(bean, bindProperty, value);
                    save();
                    this.fireEvent(new PropertyChangedEvent(bean, bindProperty));
                } catch (Throwable e) {
                    throw new MyCollabException(e);
                }
                setMinimizedValueAsHTML(generateSmallAsHtmlAfterUpdate());
                BeanPopupView.this.setDescription(generateDescription());
            }
        }

        @Override
        protected void doShow() {
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            Label headerLbl = ELabel.h3(caption);
            layout.with(headerLbl, (Component) field);

            binder = new Binder(bean.getClass());
            if (field instanceof Converter) {
                binder.forField(field).withConverter((Converter) field).bind(bindProperty);
            } else {
                binder.bind(field, bindProperty);
            }
            boolean checkPermission = isPermission();
            ((Component) field).setVisible(checkPermission);
            if (field instanceof Converter) {
                Converter converter = (Converter)field;
                Object convertValue = converter.convertToPresentation(value, null);
                field.setValue(convertValue);
            } else {
                field.setValue(value);
            }
            if (!checkPermission) {
                layout.add(new Label(UserUIContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
            }
        }
    }
}
