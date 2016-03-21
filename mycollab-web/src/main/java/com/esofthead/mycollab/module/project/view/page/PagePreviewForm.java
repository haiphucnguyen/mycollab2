package com.esofthead.mycollab.module.project.view.page;

import com.esofthead.mycollab.common.i18n.WikiI18nEnum;
import com.esofthead.mycollab.module.page.domain.Page;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.field.I18nFormViewField;
import com.esofthead.mycollab.vaadin.web.ui.field.RichTextViewField;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class PagePreviewForm extends AdvancedPreviewBeanForm<Page> {
    @Override
    public void setBean(Page bean) {
        this.setFormLayoutFactory(new PageReadFormLayout());
        this.setBeanFormFieldFactory(new PageReadFormFieldFactory(this));
        super.setBean(bean);
    }

    private static class PageReadFormLayout implements IFormLayoutFactory {
        private static final long serialVersionUID = 1L;

        private MVerticalLayout layout;

        @Override
        public ComponentContainer getLayout() {
            layout = new MVerticalLayout().withStyleName("border-bottom").withWidth("100%");
            return layout;
        }

        @Override
        public void attachField(java.lang.Object propertyId, Field<?> field) {
            if (propertyId.equals("content")) {
                layout.addComponent(field);
            }
        }
    }

    private static class PageReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<Page> {
        private static final long serialVersionUID = 1L;

        public PageReadFormFieldFactory(GenericBeanForm<Page> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(java.lang.Object propertyId) {
            if (propertyId.equals("status")) {
                return new I18nFormViewField(attachForm.getBean().getStatus(), WikiI18nEnum.class);
            } else if (propertyId.equals("content")) {
                return new RichTextViewField(attachForm.getBean().getContent());
            }
            return null;
        }
    }
}
