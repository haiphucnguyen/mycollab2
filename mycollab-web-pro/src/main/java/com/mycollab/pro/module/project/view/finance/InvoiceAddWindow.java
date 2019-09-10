package com.mycollab.pro.module.project.view.finance;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.event.InvoiceEvent;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.service.InvoiceService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
class InvoiceAddWindow extends MWindow {
    InvoiceAddWindow(final SimpleInvoice invoice) {
        if (invoice.getId() == null) {
            setCaption(UserUIContext.getMessage(InvoiceI18nEnum.NEW));
        } else {
            setCaption(UserUIContext.getMessage(InvoiceI18nEnum.EDIT));
        }
        VerticalLayout content = new VerticalLayout();
        this.withWidth("800px").withModal(true).withResizable(false).withCenter().withContent(content);
        final AdvancedEditBeanForm<SimpleInvoice> editBeanForm = new AdvancedEditBeanForm<>();
        content.addComponent(editBeanForm);
        editBeanForm.setStyleName(WebThemes.SCROLLABLE_CONTAINER);
        editBeanForm.setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.INVOICE,
                InvoiceDefaultFormLayoutFactory.getForm()));
        final InvoiceEditFormFieldFactory invoiceEditFormFieldFactory = new InvoiceEditFormFieldFactory(editBeanForm);
        editBeanForm.setBeanFormFieldFactory(invoiceEditFormFieldFactory);
        editBeanForm.setBean(invoice);

        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            if (editBeanForm.validateForm()) {
                InvoiceService invoiceService = AppContextUtil.getSpringBean(InvoiceService.class);

                if (invoice.getId() == null) {
                    invoiceService.saveWithSession(invoice, UserUIContext.getUsername());
                    EventBusFactory.getInstance().post(new InvoiceEvent.NewInvoiceAdded(this, invoice));
                } else {
                    invoiceService.updateWithSession(invoice, UserUIContext.getUsername());
                    EventBusFactory.getInstance().post(new InvoiceEvent.InvoiceUpdateAdded(this, invoice));
                }
                AttachmentUploadField uploadField = invoiceEditFormFieldFactory.getAttachmentUploadField();
                String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(AppUI.getAccountId(), invoice.getProjectid(),
                        ProjectTypeConstants.INVOICE, "" + invoice.getId());
                uploadField.saveContentsToRepo(attachPath);
                close();
            }
        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.CLIPBOARD).withClickShortcut(KeyCode.ENTER);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);
        MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(new MarginInfo(true, false, true, false));
        content.addComponent(buttonControls);
        content.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
    }
}
