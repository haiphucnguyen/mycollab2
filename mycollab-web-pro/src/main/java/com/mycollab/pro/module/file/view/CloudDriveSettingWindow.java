package com.mycollab.pro.module.file.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.ecm.StorageNames;
import com.mycollab.module.ecm.domain.ExternalDrive;
import com.mycollab.module.ecm.domain.ExternalFolder;
import com.mycollab.module.ecm.service.ExternalDriveService;
import com.mycollab.module.ecm.service.ExternalResourceService;
import com.mycollab.module.file.events.FileEvent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class CloudDriveSettingWindow extends MWindow {
    private static final long serialVersionUID = 1L;

    private final MVerticalLayout bodyLayout;

    private ExternalResourceService externalResourceService;
    private ExternalDriveService externalDriveService;

    public CloudDriveSettingWindow() {
        super("Cloud drives setting");
        this.withWidth("800px").withModal(true).withResizable(false);
        MVerticalLayout mainLayout = new MVerticalLayout().withFullWidth();
        externalDriveService = AppContextUtil.getSpringBean(ExternalDriveService.class);
        externalResourceService = AppContextUtil.getSpringBean(ExternalResourceService.class);

        Button connectAccountBtn = new Button("Connect account", clickEvent -> {
            OauthWindowFactory oauthWindowFactory = ViewManager.getCacheComponent(OauthWindowFactory.class);
            Window dropboxWindow = oauthWindowFactory.newDropBoxAuthWindow();
            UI.getCurrent().addWindow(dropboxWindow);
        });
        connectAccountBtn.addStyleName(WebUIConstants.BUTTON_ACTION);
        mainLayout.addComponent(connectAccountBtn);

        bodyLayout = new MVerticalLayout().withSpacing(false).withMargin(false).withFullWidth();

        List<ExternalDrive> externalDrives = externalDriveService.getExternalDrivesOfUser(UserUIContext.getUsername());

        bodyLayout.addComponent(ELabel.hr());
        for (ExternalDrive drive : externalDrives) {
            OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(drive);
            bodyLayout.with(layout, ELabel.hr()).withAlign(layout, Alignment.MIDDLE_LEFT);
        }

        mainLayout.addComponent(bodyLayout);
        Button closeBtn = new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLOSE), clickEvent -> close());
        closeBtn.setStyleName(WebUIConstants.BUTTON_OPTION);
        mainLayout.with(closeBtn).withAlign(closeBtn, Alignment.MIDDLE_RIGHT);

        this.setContent(mainLayout);
    }

    private class OneDriveConnectionBodyLayout extends VerticalLayout {
        private static final long serialVersionUID = 1L;
        private boolean isEdit = false;
        private Label foldernameLbl;

        OneDriveConnectionBodyLayout(final ExternalDrive drive) {
            final MVerticalLayout externalDriveEditLayout = new MVerticalLayout();

            final MHorizontalLayout titleLayout = new MHorizontalLayout().withFullWidth();
            titleLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            externalDriveEditLayout.addComponent(titleLayout);

            CssLayout iconWapper = new CssLayout();
            iconWapper.setWidth("60px");

            if (drive.getStoragename().equals(StorageNames.DROPBOX)) {
                ELabel driveIcon = ELabel.fontIcon(FontAwesome.DROPBOX);
                driveIcon.addStyleName("icon-38px");
                iconWapper.addComponent(driveIcon);
            }

            titleLayout.with(iconWapper);

            if (drive.getStoragename().equals(StorageNames.DROPBOX)) {
                Label lbl = new Label("Dropbox");
                lbl.addStyleName(ValoTheme.LABEL_H3);
                lbl.setWidth("100px");
                titleLayout.addComponent(lbl);

                // ----construct title --------------
                foldernameLbl = new Label(drive.getFoldername());
                foldernameLbl.addStyleName(ValoTheme.LABEL_H3);
                titleLayout.with(foldernameLbl).expand(foldernameLbl);

                final PopupButton popupBtn = new PopupButton();
                popupBtn.setStyleName(WebUIConstants.BUTTON_ACTION);

                final OptionPopupContent popupOptionActionLayout = new OptionPopupContent();

                Button editBtn = new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT), clickEvent -> {
                    popupBtn.setPopupVisible(false);
                    if (!isEdit) {
                        isEdit = true;
                        externalDriveEditLayout.addStyleName("driveEditting");
                        titleLayout.removeComponent(popupBtn);
                        HorizontalLayout layout = editActionHorizontalLayout(drive);
                        titleLayout.replaceComponent(foldernameLbl, layout);
                        titleLayout.setComponentAlignment(layout, Alignment.MIDDLE_LEFT);
                        titleLayout.setExpandRatio(layout, 1.0f);
                        titleLayout.addComponent(popupBtn);
                        titleLayout.setComponentAlignment(popupBtn, Alignment.MIDDLE_RIGHT);
                    }
                });
                popupOptionActionLayout.addOption(editBtn);

                Button deleteBtn = new Button(UserUIContext.getMessage(GenericI18Enum.BUTTON_DELETE), clickEvent -> {
                    try {
                        ConfirmDialogExt.show(UI.getCurrent(), UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE,
                                MyCollabUI.getSiteName()),
                                UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                                UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                                UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                                confirmDialog -> {
                                    if (confirmDialog.isConfirmed()) {
                                        externalDriveService.removeWithSession(drive, UserUIContext.getUsername(), MyCollabUI.getAccountId());
                                        bodyLayout.removeComponent(OneDriveConnectionBodyLayout.this);
                                        EventBusFactory.getInstance().post(new FileEvent.ExternalDriveDeleteEvent(this, drive));
                                    }
                                });
                    } catch (Exception e) {
                        throw new MyCollabException(e);
                    }
                });

                popupOptionActionLayout.addDangerOption(deleteBtn);
                popupBtn.setContent(popupOptionActionLayout);
                titleLayout.with(popupBtn).withAlign(popupBtn, Alignment.MIDDLE_RIGHT);

            }
            this.addComponent(externalDriveEditLayout);
        }

        private HorizontalLayout editActionHorizontalLayout(final ExternalDrive drive) {
            final MHorizontalLayout layout = new MHorizontalLayout();

            Label folderTitleLbl = new Label("Folder title");
            layout.with(folderTitleLbl).withAlign(folderTitleLbl, Alignment.MIDDLE_LEFT);

            final TextField folderNameTextField = new TextField();
            folderNameTextField.setImmediate(true);
            folderNameTextField.setValue(drive.getFoldername());
            layout.addComponent(folderNameTextField);

            MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
                String folderName = folderNameTextField.getValue().trim();
                try {
                    if (folderName.length() > 0) {
                        FileUtils.assertValidFolderName(folderName);
                        drive.setFoldername(folderName);
                        externalDriveService.updateWithSession(drive, UserUIContext.getUsername());

                        foldernameLbl = new Label(folderName);
                        foldernameLbl.addStyleName(ValoTheme.LABEL_H3);
                        ExternalFolder res = (ExternalFolder) externalResourceService.getCurrentResourceByPath(drive, "/");
                        // TODO: reload external drives
                    }
                } catch (Exception e) {
                    throw new MyCollabException(e);
                }
            }).withIcon(FontAwesome.SAVE).withStyleName(WebUIConstants.BUTTON_ACTION);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                    .withStyleName(WebUIConstants.BUTTON_OPTION);
            layout.with(saveBtn, cancelBtn);
            return layout;
        }
    }
}
