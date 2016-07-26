package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.events.InvoiceEvent;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.service.InvoiceService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceAddWindow extends Window {
    InvoiceAddWindow(final SimpleInvoice invoice) {
        if (invoice.getId() == null) {
            setCaption(AppContext.getMessage(InvoiceI18nEnum.NEW));
        } else {
            setCaption(AppContext.getMessage(InvoiceI18nEnum.EDIT));
        }
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
        VerticalLayout content = new VerticalLayout();
        this.setContent(content);
        final AdvancedEditBeanForm<SimpleInvoice> editBeanForm = new AdvancedEditBeanForm<>();
        content.addComponent(editBeanForm);
        editBeanForm.setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.INVOICE,
                InvoiceDefaultFormLayoutFactory.getForm()));
        final InvoiceEditFormFieldFactory invoiceEditFormFieldFactory = new InvoiceEditFormFieldFactory(editBeanForm);
        editBeanForm.setBeanFormFieldFactory(invoiceEditFormFieldFactory);
        editBeanForm.setBean(invoice);

        MButton saveBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            if (editBeanForm.validateForm()) {
                InvoiceService invoiceService = AppContextUtil.getSpringBean(InvoiceService.class);

                if (invoice.getId() == null) {
                    invoiceService.saveWithSession(invoice, AppContext.getUsername());
                    EventBusFactory.getInstance().post(new InvoiceEvent.NewInvoiceAdded(this, invoice));
                } else {
                    invoiceService.updateWithSession(invoice, AppContext.getUsername());
                    EventBusFactory.getInstance().post(new InvoiceEvent.InvoiceUpdateAdded(this, invoice));
                }
                AttachmentUploadField uploadField = invoiceEditFormFieldFactory.getAttachmentUploadField();
                String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(AppContext.getAccountId(), invoice.getProjectid(),
                        ProjectTypeConstants.INVOICE, "" + invoice.getId());
                uploadField.saveContentsToRepo(attachPath);
                close();
            }
        }).withStyleName(WebUIConstants.BUTTON_ACTION).withIcon(FontAwesome.SAVE);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebUIConstants.BUTTON_OPTION);
        MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, saveBtn).withMargin(new MarginInfo(true, true, true, false));
        content.addComponent(buttonControls);
        content.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
    }
}
