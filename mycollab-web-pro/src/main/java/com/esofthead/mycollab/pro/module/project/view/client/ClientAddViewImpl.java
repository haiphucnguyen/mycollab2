package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.account.AccountEditFormFieldFactory;
import com.esofthead.mycollab.module.file.PathUtils;
import com.esofthead.mycollab.module.file.service.EntityUploaderService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsUtil;
import com.esofthead.mycollab.module.user.ui.components.ImagePreviewCropWindow;
import com.esofthead.mycollab.module.user.ui.components.UploadImageField;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.WrappedFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.web.ui.DynaFormLayout;
import com.esofthead.mycollab.vaadin.web.ui.EditFormControlsGenerator;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.awt.image.BufferedImage;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientAddViewImpl extends AbstractPageView implements ClientAddView {
    private final AdvancedEditBeanForm<SimpleAccount> editForm;
    private SimpleAccount account;

    public ClientAddViewImpl() {
        this.setMargin(true);
        editForm = new AdvancedEditBeanForm<>();
        addComponent(editForm);
    }

    @Override
    public void editItem(SimpleAccount account) {
        this.account = account;
        editForm.setFormLayoutFactory(new FormLayoutFactory());
        editForm.setBeanFormFieldFactory(new AccountEditFormFieldFactory(editForm));
        editForm.setBean(account);
    }

    @Override
    public HasEditFormHandlers<SimpleAccount> getEditFormHandlers() {
        return editForm;
    }

    private class FormLayoutFactory extends WrappedFormLayoutFactory implements ImagePreviewCropWindow.ImageSelectionCommand {

        @Override
        public void process(BufferedImage image) {
            EntityUploaderService entityUploaderService = AppContextUtil.getSpringBean(EntityUploaderService.class);
            String newLogoId = entityUploaderService.upload(image, PathUtils.getEntityLogoPath(AppContext
                            .getAccountId()), account.getAvatarid(), AppContext.getUsername(), AppContext.getAccountId(),
                    new int[]{16, 32, 48, 64, 100});
            AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
            account.setAvatarid(newLogoId);
            accountService.updateSelectiveWithSession(account, AppContext.getUsername());
        }

        private Layout createButtonControls() {
            final HorizontalLayout controlPanel = new HorizontalLayout();
            final ComponentContainer controlButtons;

            if (account.getId() == null) {
                controlButtons = (new EditFormControlsGenerator<>(editForm)).createButtonControls(true, false, true);
            } else {
                controlButtons = (new EditFormControlsGenerator<>(editForm)).createButtonControls();
            }
            controlPanel.addComponent(controlButtons);
            controlPanel.setComponentAlignment(controlButtons, Alignment.TOP_RIGHT);
            return controlPanel;
        }

        private MHorizontalLayout buildHeaderTitle() {
            ELabel titleLbl;
            if (account.getId() == null) {
                titleLbl = ELabel.h2(FontAwesome.INSTITUTION.getHtml() + " New client");
                return new MHorizontalLayout(titleLbl).expand(titleLbl);
            } else {
                titleLbl = ELabel.h2(account.getAccountname());
                UploadImageField uploadImageField = new UploadImageField(this);
                uploadImageField.setButtonCaption("Change logo");

                MVerticalLayout logoLayout = new MVerticalLayout(ProjectAssetsUtil.buildClientLogo(account, 100),
                        uploadImageField).withMargin(false).withWidth("-1px").alignAll(Alignment.TOP_CENTER);
                return new MHorizontalLayout(logoLayout, titleLbl).expand(titleLbl);
            }
        }

        @Override
        public ComponentContainer getLayout() {
            wrappedLayoutFactory = new DynaFormLayout(CrmTypeConstants.ACCOUNT, AccountDefaultDynaFormLayoutFactory.getForm());
            MHorizontalLayout header = new MHorizontalLayout().withWidth("100%").withMargin(new MarginInfo(true, false, true, false));
            header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            final AddViewLayout clientAddLayout = new AddViewLayout(header);
            clientAddLayout.addHeaderTitle(buildHeaderTitle());
            clientAddLayout.addHeaderRight(createButtonControls());
            clientAddLayout.addBody(wrappedLayoutFactory.getLayout());
            return clientAddLayout;
        }
    }
}

