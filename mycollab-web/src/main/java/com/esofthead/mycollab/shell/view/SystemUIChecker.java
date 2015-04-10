package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.ShellI18nEnum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.UI;
import org.vaadin.dialogs.ConfirmDialog;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class SystemUIChecker {
    /**
     *
     * @return true if the system has the valid smtp account, false if otherwise
     */
    public static boolean hasValidSmtpAccount() {
        if (SiteConfiguration.getDeploymentMode() != DeploymentMode.site) {
            ExtMailService extMailService = ApplicationContextUtil.getSpringBean(ExtMailService.class);
            if (!extMailService.isMailSetupValid()) {
                if (AppContext.isAdmin()) {
                    ConfirmDialogExt.show(
                            UI.getCurrent(),
                            AppContext.getMessage(
                                    ShellI18nEnum.WINDOW_STMP_NOT_SETUP),
                            AppContext
                                    .getMessage(ShellI18nEnum.WINDOW_SMTP_CONFIRM_SETUP_FOR_ADMIN),
                            AppContext
                                    .getMessage(GenericI18Enum.BUTTON_YES),
                            AppContext
                                    .getMessage(GenericI18Enum.BUTTON_NO),
                            new ConfirmDialog.Listener() {
                                private static final long serialVersionUID = 1L;

                                @Override
                                public void onClose(ConfirmDialog dialog) {
                                    if (dialog.isConfirmed()) {
                                        UI.getCurrent().addWindow(new SmtpConfigurationWindow());
                                    }
                                }
                            });

                } else {
                    NotificationUtil.showErrorNotification(AppContext
                            .getMessage(ShellI18nEnum.WINDOW_SMTP_CONFIRM_SETUP_FOR_USER));
                }
                return false;
            } else {
                return true;
            }
        }
        return true;
    }
}
