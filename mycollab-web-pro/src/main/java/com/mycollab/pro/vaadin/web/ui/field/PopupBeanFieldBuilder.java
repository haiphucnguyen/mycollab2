package com.mycollab.pro.vaadin.web.ui.field;

import com.google.common.base.MoreObjects;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.persistence.service.ICrudService;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.LazyPopupView;
import com.vaadin.data.HasValue;
import com.vaadin.ui.PopupView;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
// TODO
public abstract class PopupBeanFieldBuilder<B> {
    protected Object value;
    protected String caption;
    protected String description;
    protected HasValue field;
    private boolean hasPermission = true;
    protected B bean;
    private String bindProperty;
    //    private BeanFieldGroup fieldGroup;
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
        final PopupView view = new BeanPopupView(generateSmallContentAsHtml());
        view.setDescription(generateDescription());
        return view;
    }

    private class BeanPopupView extends LazyPopupView {
        BeanPopupView(String valueAsHtml) {
            super(valueAsHtml);
        }

        @Override
        protected void doHide() {
//            try {
//                if (fieldGroup.isModified()) {
//                    fieldGroup.commit();
//                    save();
//                    this.fireEvent(new PropertyChangedEvent(bean, bindProperty));
//                    setMinimizedValueAsHTML(generateSmallAsHtmlAfterUpdate());
//                    BeanPopupView.this.setDescription(generateDescription());
//                }
//            } catch (FieldGroup.CommitException e) {
//                throw new MyCollabException(e);
//            }
        }

        @Override
        protected void doShow() {
//            BeanItem item = new BeanItem(bean);
//            fieldGroup = new BeanFieldGroup(bean.getClass());
//
//            MVerticalLayout layout = getWrapContent();
//            layout.removeAllComponents();
//            Label headerLbl = ELabel.h3(caption);
//            layout.with(headerLbl);
//            layout.with(field);
//            if (field instanceof AbstractComponent) {
//                new Restrain((AbstractComponent) field).setMaxWidth("600px");
//            }
//
//            fieldGroup.setBuffered(true);
//            fieldGroup.setItemDataSource(item);
//            fieldGroup.bind(field, bindProperty);
//            boolean checkPermission = isPermission();
//            field.setVisible(checkPermission);
//            if (!checkPermission) {
//                layout.add(new Label(UserUIContext.getMessage(GenericI18Enum.NOTIFICATION_NO_PERMISSION_DO_TASK)));
//            }
        }
    }
}
