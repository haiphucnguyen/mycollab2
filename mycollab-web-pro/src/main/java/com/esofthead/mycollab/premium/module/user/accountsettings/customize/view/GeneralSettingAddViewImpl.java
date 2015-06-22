package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.premium.module.user.accountsettings.view.events.SettingExtEvent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.form.field.ContainerHorizontalViewField;
import com.esofthead.mycollab.vaadin.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.web.DesktopApplication;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class GeneralSettingAddViewImpl extends AbstractPageView implements GeneralSettingAddView {
    private AdvancedPreviewBeanForm<SimpleBillingAccount> previewForm;

    public GeneralSettingAddViewImpl() {
        this.setMargin(true);
        this.addStyleName("userInfoContainer");
    }

    @Override
    public void displayView() {
        removeAllComponents();

        previewForm = new PreviewForm();
        previewForm.setBean(AppContext.getBillingAccount());
        addComponent(previewForm);
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<SimpleBillingAccount> {
        private static final long serialVersionUID = 1L;

        @Override
        public void setBean(SimpleBillingAccount bean) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setBeanFormFieldFactory(new AbstractBeanFieldGroupEditFieldFactory<SimpleBillingAccount>(
                    PreviewForm.this) {
                @Override
                protected Field<?> onCreateField(Object propertyId) {
                    if (BillingAccount.Field.sitename.equalTo(propertyId)) {
                        ContainerHorizontalViewField layout = new ContainerHorizontalViewField();
                        Label a = new Label("aaa");
                        DesktopApplication.addContextHelp(a, "AAA");
                        layout.addComponentField(a);
                        return layout;
                    }
                    return null;
                }
            });
            super.setBean(bean);
        }

        private class FormLayoutFactory implements IFormLayoutFactory {

            private GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(1, 3);

            @Override
            public ComponentContainer getLayout() {
                MVerticalLayout layout = new MVerticalLayout().withMargin(new MarginInfo(false, true, true, false));

                MHorizontalLayout header = new MHorizontalLayout().withWidth("100%");

                Label headerLbl = new Label("General Settings for your site");
                headerLbl.setStyleName("h1");

                Button updateBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {

                    }
                });
                updateBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

                Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent clickEvent) {
                        EventBusFactory.getInstance().post(new SettingExtEvent.GeneralSettingRead(
                                GeneralSettingAddViewImpl.this, null));
                    }
                });
                cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

                header.with(headerLbl, updateBtn, cancelBtn).withAlign(headerLbl, Alignment.MIDDLE_LEFT).withAlign
                        (updateBtn, Alignment.MIDDLE_RIGHT).withAlign(cancelBtn, Alignment.MIDDLE_RIGHT).expand(headerLbl);

                layout.with(header, gridLayout.getLayout());
                return layout;
            }

            @Override
            public void attachField(Object propertyId, Field<?> field) {
                if (BillingAccount.Field.sitename.equalTo(propertyId)) {
                    gridLayout.addComponent(field, "Site name", 0, 0);
                } else if (BillingAccount.Field.subdomain.equalTo(propertyId)) {
                    gridLayout.addComponent(field, "Site Address", 0, 1);
                } else if (BillingAccount.Field.defaulttimezone.equalTo(propertyId)) {
                    gridLayout.addComponent(field, "Default Timezone", 0, 2);
                }
            }
        }
    }
}
