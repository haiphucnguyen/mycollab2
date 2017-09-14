package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.i18n.FileI18nEnum;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.service.AccountService;
import com.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.mycollab.module.crm.view.account.AccountEditFormFieldFactory;
import com.mycollab.module.file.PathUtils;
import com.mycollab.module.file.service.EntityUploaderService;
import com.mycollab.module.project.i18n.ClientI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.vaadin.web.ui.ImagePreviewCropWindow;
import com.mycollab.vaadin.web.ui.UploadImageField;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.HasEditFormHandlers;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.WrappedFormLayoutFactory;
import com.mycollab.vaadin.web.ui.AddViewLayout;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.awt.image.BufferedImage;

import static com.mycollab.vaadin.web.ui.utils.FormControlsGenerator.generateEditFormControls;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientAddViewImpl extends AbstractVerticalPageView implements ClientAddView {
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
            String newLogoId = entityUploaderService.upload(image, PathUtils.getEntityLogoPath(AppUI.getAccountId()),
                    account.getAvatarid(), UserUIContext.getUsername(), AppUI.getAccountId(),
                    new Integer[]{16, 32, 48, 64, 100});
            AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
            account.setAvatarid(newLogoId);
            accountService.updateSelectiveWithSession(account, UserUIContext.getUsername());
        }

        private Layout createButtonControls() {
            final HorizontalLayout controlPanel = new HorizontalLayout();
            final ComponentContainer controlButtons;

            if (account.getId() == null) {
                controlButtons = generateEditFormControls(editForm, true, false, true);
            } else {
                controlButtons = generateEditFormControls(editForm);
            }
            controlPanel.addComponent(controlButtons);
            controlPanel.setComponentAlignment(controlButtons, Alignment.TOP_RIGHT);
            return controlPanel;
        }

        private MHorizontalLayout buildHeaderTitle() {
            ELabel titleLbl;
            if (account.getId() == null) {
                titleLbl = ELabel.h2(FontAwesome.INSTITUTION.getHtml() + " " + UserUIContext.getMessage(ClientI18nEnum.NEW));
                return new MHorizontalLayout(titleLbl).expand(titleLbl);
            } else {
                titleLbl = ELabel.h2(account.getAccountname());
                UploadImageField uploadImageField = new UploadImageField(this);
                uploadImageField.setButtonCaption(UserUIContext.getMessage(FileI18nEnum.ACTION_CHANGE_LOGO));

                MVerticalLayout logoLayout = new MVerticalLayout(ProjectAssetsUtil.clientLogoComp(account, 100),
                        uploadImageField).withMargin(false).withWidth("-1px").alignAll(Alignment.TOP_CENTER);
                return new MHorizontalLayout(logoLayout, titleLbl).expand(titleLbl);
            }
        }

        @Override
        public AbstractComponent getLayout() {
            wrappedLayoutFactory = new DefaultDynaFormLayout(CrmTypeConstants.ACCOUNT, AccountDefaultDynaFormLayoutFactory.getForm());
            MHorizontalLayout header = new MHorizontalLayout().withFullWidth().withMargin(new MarginInfo(true, false, true, false));
            header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            final AddViewLayout clientAddLayout = new AddViewLayout(header);
            clientAddLayout.addHeaderTitle(buildHeaderTitle());
            clientAddLayout.addHeaderRight(createButtonControls());
            clientAddLayout.addBody(wrappedLayoutFactory.getLayout());
            return clientAddLayout;
        }
    }
}