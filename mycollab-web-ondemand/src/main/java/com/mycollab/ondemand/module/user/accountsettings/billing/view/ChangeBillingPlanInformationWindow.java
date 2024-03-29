package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.form.view.LayoutType;
import com.mycollab.module.user.accountsettings.localization.BillingI18nEnum;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper;
import com.mycollab.ondemand.module.billing.domain.BillingSubscription;
import com.mycollab.ondemand.module.billing.esb.UpdateSubscriptionEvent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.*;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.data.HasValue;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class ChangeBillingPlanInformationWindow extends MWindow {

    ChangeBillingPlanInformationWindow(BillingSubscription billingSubscription) {
        super(UserUIContext.getMessage(BillingI18nEnum.OPT_CHANGE_CONTACT_INFO));
        EditForm editForm = new EditForm();
        editForm.setBean(BeanUtility.deepClone(billingSubscription));
        this.withModal(true).withResizable(false).withWidth("600px").withContent(editForm);
    }

    private class EditForm extends AdvancedEditBeanForm<BillingSubscription> {
        @Override
        public void setBean(final BillingSubscription item) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setBeanFormFieldFactory(new EditFormFieldFactory(this));
            super.setBean(item);
        }

        class FormLayoutFactory extends AbstractFormLayoutFactory {
            private GridFormLayoutHelper informationLayout;

            @Override
            public AbstractComponent getLayout() {
                VerticalLayout layout = new VerticalLayout();
                informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(LayoutType.ONE_COLUMN);
                layout.addComponent(informationLayout.getLayout());

                MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
                    if (EditForm.this.validateForm()) {
                        BillingSubscriptionMapper mapper = AppContextUtil.getSpringBean(BillingSubscriptionMapper.class);
                        mapper.updateByPrimaryKey(bean);
                        UpdateSubscriptionEvent event = new UpdateSubscriptionEvent(bean);
                        AsyncEventBus eventBus = AppContextUtil.getSpringBean(AsyncEventBus.class);
                        eventBus.post(event);
                        close();
                        UIUtils.reloadPage();
                    }
                }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.CLIPBOARD).withClickShortcut(KeyCode.ENTER);

                MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                        .withStyleName(WebThemes.BUTTON_OPTION);

                MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(true);

                layout.addComponent(buttonControls);
                layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
                return layout;
            }

            @Override
            protected HasValue<?> onAttachField(Object propertyId, HasValue<?> field) {
                if (BillingSubscription.Field.name.equalTo(propertyId)) {
                    informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0);
                } else if (BillingSubscription.Field.email.equalTo(propertyId)) {
                    informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_EMAIL), 0, 1);
                } else if (BillingSubscription.Field.phone.equalTo(propertyId)) {
                    informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_PHONE), 0, 2);
                } else if (BillingSubscription.Field.company.equalTo(propertyId)) {
                    informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_COMPANY), 0, 3);
                }
                return null;
            }
        }

        class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<BillingSubscription> {
            EditFormFieldFactory(GenericBeanForm<BillingSubscription> form) {
                super(form);
            }

            @Override
            protected HasValue<?> onCreateField(Object propertyId) {
                return null;
            }
        }
    }
}
