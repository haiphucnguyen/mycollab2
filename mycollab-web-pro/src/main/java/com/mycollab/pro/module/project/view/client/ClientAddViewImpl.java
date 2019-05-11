package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.Client;
import com.mycollab.common.i18n.ClientI18nEnum;
import com.mycollab.common.i18n.FileI18nEnum;
import com.mycollab.common.service.ClientService;
import com.mycollab.module.file.PathUtils;
import com.mycollab.module.file.service.EntityUploaderService;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.HasEditFormHandlers;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.ui.WrappedFormLayoutFactory;
import com.mycollab.vaadin.web.ui.AddViewLayout;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.ImagePreviewCropWindow;
import com.mycollab.vaadin.web.ui.UploadImageField;
import com.vaadin.icons.VaadinIcons;
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
    private final AdvancedEditBeanForm<Client> editForm;
    private Client client;

    public ClientAddViewImpl() {
        this.setMargin(true);
        editForm = new AdvancedEditBeanForm<>();
        addComponent(editForm);
    }

    @Override
    public void editItem(Client account) {
        this.client = account;
        editForm.setFormLayoutFactory(new FormLayoutFactory());
        editForm.setBeanFormFieldFactory(new ClientEditFormFieldFactory<>(editForm));
        editForm.setBean(account);
    }

    @Override
    public HasEditFormHandlers<Client> getEditFormHandlers() {
        return editForm;
    }

    private class FormLayoutFactory extends WrappedFormLayoutFactory implements ImagePreviewCropWindow.ImageSelectionCommand {

        @Override
        public void process(BufferedImage image) {
            EntityUploaderService entityUploaderService = AppContextUtil.getSpringBean(EntityUploaderService.class);
            String newLogoId = entityUploaderService.upload(image, PathUtils.getEntityLogoPath(AppUI.getAccountId()),
                    client.getAvatarid(), UserUIContext.getUsername(), AppUI.getAccountId(),
                    new Integer[]{16, 32, 48, 64, 100});
            ClientService clientService = AppContextUtil.getSpringBean(ClientService.class);
            client.setAvatarid(newLogoId);
            clientService.updateSelectiveWithSession(client, UserUIContext.getUsername());
            UIUtils.reloadPage();
        }

        private Layout createButtonControls() {
            final HorizontalLayout controlPanel = new HorizontalLayout();
            final ComponentContainer controlButtons;

            if (client.getId() == null) {
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
            if (client.getId() == null) {
                titleLbl = ELabel.h2(VaadinIcons.INSTITUTION.getHtml() + " " + UserUIContext.getMessage(ClientI18nEnum.NEW));
                return new MHorizontalLayout(titleLbl).expand(titleLbl);
            } else {
                titleLbl = ELabel.h2(client.getName());
                UploadImageField uploadImageField = new UploadImageField(this);
                uploadImageField.setButtonCaption(UserUIContext.getMessage(FileI18nEnum.ACTION_CHANGE_LOGO));

                MVerticalLayout logoLayout = new MVerticalLayout(ProjectAssetsUtil.clientLogoComp(client, 100),
                        uploadImageField).withMargin(false).withWidth("-1px").alignAll(Alignment.TOP_CENTER);
                return new MHorizontalLayout(logoLayout, titleLbl).expand(titleLbl);
            }
        }

        @Override
        public AbstractComponent getLayout() {
            wrappedLayoutFactory = new DefaultDynaFormLayout(ProjectTypeConstants.CLIENT, ClientDefaultDynaFormLayoutFactory.getForm());
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