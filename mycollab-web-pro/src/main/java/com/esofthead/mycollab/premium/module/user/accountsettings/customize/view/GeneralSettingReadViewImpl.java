package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class GeneralSettingReadViewImpl extends AbstractPageView implements GeneralSettingReadView {

    private AdvancedPreviewBeanForm<SimpleBillingAccount> previewForm;
    private MHorizontalLayout header;
    private SimpleBillingAccount account;

    public GeneralSettingReadViewImpl() {
        super();
        this.setMargin(new MarginInfo(false, true, true, true));
        this.addStyleName("userInfoContainer");

        header = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withWidth("100%").withStyleName(UIConstants.HEADER_VIEW);

        addComponent(header);

        previewForm = new PreviewForm();
        addComponent(previewForm);
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<SimpleBillingAccount> {
        private static final long serialVersionUID = 1L;

        @Override
        public void setBean(SimpleBillingAccount bean ) {
//            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setBeanFormFieldFactory(new AbstractBeanFieldGroupViewFieldFactory<SimpleBillingAccount>(
                    PreviewForm.this) {
                @Override
                protected Field<?> onCreateField(Object propertyId) {
                    return null;
                }
            });
        }

        private class FormLayoutFactory implements IFormLayoutFactory {
            @Override
            public ComponentContainer getLayout() {
                return null;
            }

            @Override
            public void attachField(Object propertyId, Field<?> field) {

            }
        }
    }
}
