package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.configuration.EmailConfiguration;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class SmtpConfigurationWindow extends Window {
    private static Logger LOG = LoggerFactory.getLogger(SmtpConfigurationWindow.class);
    private EmailConfiguration emailConfiguration;
    private AdvancedEditBeanForm<EmailConfiguration> editForm;

    public SmtpConfigurationWindow() {
        super("SMTP Setting");
        this.center();
        this.setResizable(false);
        this.setModal(true);
        this.setWidth("600px");

        MVerticalLayout contentLayout = new MVerticalLayout().withMargin(false);
        this.setContent(contentLayout);

        emailConfiguration = new EmailConfiguration();
        editForm = new AdvancedEditBeanForm<>();
        contentLayout.addComponent(editForm);
        setCaption("Set up the new SMTP account");
        editForm.setFormLayoutFactory(new FormLayoutFactory());
        editForm.setBeanFormFieldFactory(new EditFormFieldFactory(editForm));
        editForm.setBean(emailConfiguration);
    }

    private class EditFormFieldFactory extends
            AbstractBeanFieldGroupEditFieldFactory<EmailConfiguration> {
        private static final long serialVersionUID = 1L;

        public EditFormFieldFactory(GenericBeanForm<EmailConfiguration> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (propertyId.equals("isTls")) {
                return new CheckBox();
            }
            return null;
        }
    }

    class FormLayoutFactory implements IFormLayoutFactory {
        private static final long serialVersionUID = 1L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout projectAddLayout = new VerticalLayout();

            this.informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 5);
            projectAddLayout.addComponent(this.informationLayout.getLayout());

            final MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(true).withStyleName("addNewControl");

            final Button closeBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_CLOSE),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final Button.ClickEvent event) {
                            SmtpConfigurationWindow.this.close();
                        }

                    });
            closeBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
            buttonControls.with(closeBtn).withAlign(closeBtn, Alignment.MIDDLE_RIGHT);

            final Button saveBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final Button.ClickEvent event) {
                            if (editForm.validateForm()) {
                                SiteConfiguration.setEmailConfiguration(emailConfiguration);
                                File configFile = ApplicationProperties.getAppConfigFile();
                                if (configFile != null) {
                                    Properties props = ApplicationProperties.getAppProperties();
                                    try {
                                        props.store(new FileOutputStream(configFile), null);
                                        NotificationUtil.showNotification("Set up SMTP account successfully");
                                    } catch (IOException e) {
                                        LOG.error("Can not save email props", e);
                                        throw new UserInvalidInputException("Can not save properties file successfully");
                                    }
                                }
                                SmtpConfigurationWindow.this.close();
                            }
                        }
                    });
            saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            saveBtn.setIcon(FontAwesome.SAVE);
            saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
            buttonControls.with(saveBtn).withAlign(saveBtn, Alignment.MIDDLE_RIGHT);

            projectAddLayout.addComponent(buttonControls);
            projectAddLayout.setComponentAlignment(buttonControls,
                    Alignment.MIDDLE_RIGHT);
            return projectAddLayout;
        }

        @Override
        public void attachField(final Object propertyId, final Field<?> field) {
            if (propertyId.equals("host")) {
                this.informationLayout.addComponent(field, "Host", 0, 0);
            } else if (propertyId.equals("user")) {
                this.informationLayout.addComponent(field, "User Name", 0, 1);
            } else if (propertyId.equals("password")) {
                this.informationLayout.addComponent(field, "Password", 0, 2);
            } else if (propertyId.equals("port")) {
                this.informationLayout.addComponent(field, "Port", 0, 3);
            } else if (propertyId.equals("isTls")) {
                this.informationLayout.addComponent(field, "SSL/TLS", 0, 4);
            }
        }
    }
}
