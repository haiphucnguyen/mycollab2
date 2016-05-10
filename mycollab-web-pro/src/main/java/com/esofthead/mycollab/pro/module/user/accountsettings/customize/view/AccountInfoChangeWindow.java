package com.esofthead.mycollab.pro.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.module.user.accountsettings.localization.AdminI18nEnum;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.module.user.ui.components.LanguageComboBox;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CurrencyComboBoxField;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.TimeZoneSelectionField;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.google.common.base.MoreObjects;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
class AccountInfoChangeWindow extends Window {
    private SimpleBillingAccount billingAccount;

    private AdvancedEditBeanForm<SimpleBillingAccount> editForm;

    AccountInfoChangeWindow() {
        super("Change account info");
        this.setModal(true);
        this.setResizable(false);
        this.setWidth("600px");

        billingAccount = BeanUtility.deepClone(AppContext.getBillingAccount());
        MVerticalLayout content = new MVerticalLayout().withMargin(false);
        this.setContent(content);
        editForm = new AdvancedEditBeanForm<>();
        editForm.setFormLayoutFactory(new IFormLayoutFactory() {
            private GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 8, "190px");

            @Override
            public ComponentContainer getLayout() {
                return gridFormLayoutHelper.getLayout();
            }

            @Override
            public void attachField(Object propertyId, Field<?> field) {
                if (BillingAccount.Field.sitename.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_SITE_NAME), 0, 0);
                } else if (BillingAccount.Field.subdomain.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_SITE_ADDRESS), 0, 1);
                } else if (BillingAccount.Field.defaulttimezone.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_DEFAULT_TIMEZONE), 0, 2);
                } else if (BillingAccount.Field.defaultcurrencyid.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_DEFAULT_CURRENCY), 0, 3);
                } else if (BillingAccount.Field.defaultyymmddformat.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_DEFAULT_YYMMDD_FORMAT), 0, 4);
                } else if (BillingAccount.Field.defaultmmddformat.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_DEFAULT_MMDD_FORMAT), 0, 5);
                } else if (BillingAccount.Field.defaulthumandateformat.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_DEFAULT_HUMAN_DATE_FORMAT), 0, 6);
                } else if (BillingAccount.Field.defaultlanguagetag.equalTo(propertyId)) {
                    gridFormLayoutHelper.addComponent(field, AppContext.getMessage(AdminI18nEnum.FORM_DEFAULT_LANGUAGE), 0, 7);
                }
            }
        });

        editForm.setBeanFormFieldFactory(new AbstractBeanFieldGroupEditFieldFactory<SimpleBillingAccount>(editForm) {
            @Override
            protected Field<?> onCreateField(Object propertyId) {
                if (BillingAccount.Field.subdomain.equalTo(propertyId)) {
                    return new SubDomainField();
                } else if (BillingAccount.Field.defaulttimezone.equalTo(propertyId)) {
                    TimeZoneSelectionField cboTimezone = new TimeZoneSelectionField(false);
                    if (billingAccount.getDefaulttimezone() != null) {
                        cboTimezone.setTimeZone(TimezoneMapper.getTimezoneExt(billingAccount.getDefaulttimezone()));
                    } else {
                        if (AppContext.getUser().getTimezone() != null) {
                            cboTimezone.setTimeZone(TimezoneMapper.getTimezoneExt(AppContext.getUser().getTimezone()));
                        }
                    }
                    return cboTimezone;
                } else if (BillingAccount.Field.defaultcurrencyid.equalTo(propertyId)) {
                    return new CurrencyComboBoxField();
                } else if (BillingAccount.Field.defaultyymmddformat.equalTo(propertyId)) {
                    return new DateFormatField(MoreObjects.firstNonNull(billingAccount.getDefaultyymmddformat(), "MM/dd/yyyy"));
                } else if (BillingAccount.Field.defaultmmddformat.equalTo(propertyId)) {
                    return new DateFormatField(MoreObjects.firstNonNull(billingAccount.getDefaultmmddformat(), "MM/dd"));
                } else if (BillingAccount.Field.defaulthumandateformat.equalTo(propertyId)) {
                    return new DateFormatField(MoreObjects.firstNonNull(billingAccount.getDefaulthumandateformat(), "E, dd MMM yyyy"));
                } else if (BillingAccount.Field.defaultlanguagetag.equalTo(propertyId)) {
                    return new LanguageComboBox();
                }
                return null;
            }

        });

        editForm.setBean(billingAccount);

        MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(true);
        Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (editForm.validateForm()) {
                    BillingAccountService billingAccountService = ApplicationContextUtil.getSpringBean(BillingAccountService.class);
                    billingAccountService.updateSelectiveWithSession(billingAccount, AppContext.getUsername());
                    AccountInfoChangeWindow.this.close();
                    String siteUrl = SiteConfiguration.getSiteUrl(billingAccount.getSubdomain());
                    String assignExec = String.format("window.location.assign(\'%s\');", siteUrl);
                    Page.getCurrent().getJavaScript().execute(assignExec);
                }
            }
        });
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.setStyleName(UIConstants.BUTTON_ACTION);

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AccountInfoChangeWindow.this.close();
            }
        });
        cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
        buttonControls.with(cancelBtn, saveBtn);

        content.with(editForm, buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
    }

    private static class SubDomainField extends CustomField<String> {
        private TextField subDomainField = new TextField();

        @Override
        protected Component initContent() {
            MHorizontalLayout layout = new MHorizontalLayout().withSpacing(false);
            layout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            Label httpsLabel = new Label("https://");
            Label domainLbl = new Label(".mycollab.com");
            layout.with(httpsLabel, subDomainField, domainLbl);
            return layout;
        }

        @Override
        public void setPropertyDataSource(Property newDataSource) {
            String value = (String) newDataSource.getValue();
            if (value != null) {
                subDomainField.setValue(value);
            }
            super.setPropertyDataSource(newDataSource);
        }

        @Override
        public void commit() throws SourceException, Validator.InvalidValueException {
            setInternalValue(subDomainField.getValue());
            super.commit();
        }

        @Override
        public Class<? extends String> getType() {
            return String.class;
        }
    }

    private static final class DateFormatField extends CustomField<String> {
        String dateFormat;
        TextField dateInput;
        Label dateExample;
        Date now;
        DateFormat dateFormatInstance;

        DateFormatField(String initialFormat) {
            this.dateFormat = initialFormat;
            dateInput = new TextField(null, initialFormat);
            now = new GregorianCalendar().getTime();
            dateExample = new Label();
            dateFormatInstance = DateTimeUtils.getDateFormat(dateFormat);
            dateExample.setValue("(" + dateFormatInstance.format(now) + ")");
            dateExample.setWidthUndefined();
        }

        @Override
        protected Component initContent() {
            return new MHorizontalLayout(dateInput, dateExample);
        }

        @Override
        public Class<? extends String> getType() {
            return String.class;
        }
    }
}
