package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.file.events.FileEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class CloudDriveSettingWindow extends Window {
    private static final long serialVersionUID = 1L;

    private final Button connectAccountBtn;
    private final MVerticalLayout bodyLayout;
    private final MVerticalLayout mainLayout;

    private ExternalResourceService externalResourceService;
    private ExternalDriveService externalDriveService;

    public CloudDriveSettingWindow() {
        super("Cloud drives setting");
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
        mainLayout = new MVerticalLayout().withWidth("100%");
        externalDriveService = ApplicationContextUtil.getSpringBean(ExternalDriveService.class);
        externalResourceService = ApplicationContextUtil.getSpringBean(ExternalResourceService.class);

        connectAccountBtn = new Button("Connect account", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                OauthWindowFactory oauthWindowFactory = ViewManager.getCacheComponent(OauthWindowFactory.class);
                Window dropboxWindow = oauthWindowFactory.newDropBoxAuthWindow();
                UI.getCurrent().addWindow(dropboxWindow);
            }
        });
        connectAccountBtn.addStyleName(UIConstants.BUTTON_ACTION);
        mainLayout.addComponent(connectAccountBtn);

        bodyLayout = new MVerticalLayout().withSpacing(false).withMargin(false).withWidth("100%");

        List<ExternalDrive> externalDrives = externalDriveService.getExternalDrivesOfUser(AppContext.getUsername());

        bodyLayout.addComponent(new Hr());
        for (ExternalDrive drive : externalDrives) {
            OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(drive);
            bodyLayout.with(layout, new Hr()).withAlign(layout, Alignment.MIDDLE_LEFT);
        }

        mainLayout.addComponent(bodyLayout);
        Button closeBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CLOSE), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                CloudDriveSettingWindow.this.close();
            }
        });
        closeBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
        mainLayout.with(closeBtn).withAlign(closeBtn, Alignment.MIDDLE_RIGHT);

        this.setContent(mainLayout);
    }

    private class OneDriveConnectionBodyLayout extends VerticalLayout {
        private static final long serialVersionUID = 1L;
        private boolean isEdit = false;
        private Label foldernameLbl;

        public OneDriveConnectionBodyLayout(final ExternalDrive drive) {
            final MVerticalLayout externalDriveEditLayout = new MVerticalLayout();

            final MHorizontalLayout titleLayout = new MHorizontalLayout().withWidth("100%");
            titleLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            externalDriveEditLayout.addComponent(titleLayout);

            CssLayout iconWapper = new CssLayout();
            iconWapper.setWidth("60px");

            if (drive.getStoragename().equals(StorageNames.DROPBOX)) {
                ELabel driveIcon = new ELabel(FontAwesome.DROPBOX);
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
                popupBtn.setStyleName(UIConstants.BUTTON_ACTION);

                final OptionPopupContent popupOptionActionLayout = new OptionPopupContent();

                Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
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
                    }
                });
                editBtn.addStyleName("link");
                popupOptionActionLayout.addOption(editBtn);

                Button deleteBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_DELETE), new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        try {
                            ConfirmDialogExt.show(UI.getCurrent(), AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE,
                                            AppContext.getSiteName()),
                                    AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                                    AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                                    AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                                    new ConfirmDialog.Listener() {
                                        private static final long serialVersionUID = 1L;

                                        @Override
                                        public void onClose(final ConfirmDialog dialog) {
                                            if (dialog.isConfirmed()) {
                                                externalDriveService.removeWithSession(drive,
                                                        AppContext.getUsername(), AppContext.getAccountId());
                                                bodyLayout.removeComponent(OneDriveConnectionBodyLayout.this);
                                                EventBusFactory.getInstance().post(new FileEvent
                                                        .ExternalDriveDeleteEvent(CloudDriveSettingWindow.this, drive));

                                            }
                                        }
                                    });
                        } catch (Exception e) {
                            throw new MyCollabException(e);
                        }
                    }
                });
                deleteBtn.addStyleName(UIConstants.BUTTON_LINK);
                popupOptionActionLayout.addOption(deleteBtn);
                popupBtn.setContent(popupOptionActionLayout);
                titleLayout.addComponent(popupBtn);
                titleLayout.setComponentAlignment(popupBtn, Alignment.MIDDLE_RIGHT);

            }
            this.addComponent(externalDriveEditLayout);
        }

        private HorizontalLayout editActionHorizontalLayout(final ExternalDrive drive) {
            final MHorizontalLayout layout = new MHorizontalLayout();
            layout.addStyleName("resourceItem");

            Label folderTitleLbl = new Label("Folder title");
            layout.with(folderTitleLbl).withAlign(folderTitleLbl, Alignment.MIDDLE_LEFT);

            final TextField folderNameTextField = new TextField();
            folderNameTextField.setImmediate(true);
            folderNameTextField.setValue(drive.getFoldername());
            layout.addComponent(folderNameTextField);

            Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    String folderName = folderNameTextField.getValue().trim();
                    try {
                        if (folderName.length() > 0) {
                            FileUtils.assertValidFolderName(folderName);
                            drive.setFoldername(folderName);
                            externalDriveService.updateWithSession(drive, AppContext.getUsername());

                            foldernameLbl = new Label(folderName);
                            foldernameLbl.addStyleName(ValoTheme.LABEL_H3);
                            ExternalFolder res = (ExternalFolder) externalResourceService.getCurrentResourceByPath(drive, "/");
                            // TODO: reload external drives
                        }
                    } catch (Exception e) {
                        throw new MyCollabException(e);
                    }
                }
            });
            saveBtn.addStyleName(UIConstants.BUTTON_ACTION);
            saveBtn.setIcon(FontAwesome.SAVE);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    CloudDriveSettingWindow.this.close();
                }
            });
            cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
            layout.with(saveBtn, cancelBtn);
            return layout;
        }
    }
}
