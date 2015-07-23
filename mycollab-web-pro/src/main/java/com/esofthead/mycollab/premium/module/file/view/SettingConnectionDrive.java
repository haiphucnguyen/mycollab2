package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class SettingConnectionDrive extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    private final Button connectAccountBtn;
    private final MVerticalLayout bodyLayout;
    private final MVerticalLayout mainLayout;

    private ExternalDriveService externalDriveService;

    public SettingConnectionDrive() {
        mainLayout = new MVerticalLayout().withWidth("100%");
        externalDriveService = ApplicationContextUtil.getSpringBean(ExternalDriveService.class);

        connectAccountBtn = new Button("Connect account", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                OauthWindowFactory oauthWindowFactory = ApplicationContextUtil.getSpringBean(OauthWindowFactory.class);
                Window dropboxWindow = oauthWindowFactory.newDropBoxAuthWindow();
//                dropboxWindow.addExternalDriveConnectedListener(new AbstractCloudDriveOAuthWindow.ExternalDriveConnectedListener() {
//                    private static final long serialVersionUID = 1L;
//
//                    @Override
//                    public void connectedSuccess(AbstractCloudDriveOAuthWindow.ExternalDriveConnectedEvent event) {
////                        folderNavigator.collapseItem(rootECMFolder);
////                        folderNavigator.expandItem(rootECMFolder);
////                        OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(
////                                (ExternalDrive) event.getData());
////                        bodyLayout.addComponent(layout);
////                        bodyLayout.setComponentAlignment(layout, Alignment.MIDDLE_LEFT);
////                        bodyLayout.addComponent(new Hr());
//
//                    }
//                });
                UI.getCurrent().addWindow(dropboxWindow);
            }
        });
        connectAccountBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
        mainLayout.addComponent(connectAccountBtn);

        bodyLayout = new MVerticalLayout().withSpacing(false).withMargin(false).withWidth("100%");

        mainLayout.addComponent(bodyLayout);
        this.addComponent(mainLayout);

        List<ExternalDrive> externalDrives = externalDriveService.getExternalDrivesOfUser(AppContext.getUsername());

        bodyLayout.addComponent(new Hr());
        for (ExternalDrive drive : externalDrives) {
            OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(drive);
            bodyLayout.with(layout, new Hr()).withAlign(layout, Alignment.MIDDLE_LEFT);
        }
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
                FontIconLabel driveIcon = new FontIconLabel(FontAwesome.DROPBOX);
                driveIcon.addStyleName("icon-38px");
                iconWapper.addComponent(driveIcon);
            }

            titleLayout.with(iconWapper);

            if (drive.getStoragename().equals(StorageNames.DROPBOX)) {
                Label lbl = new Label("Dropbox");
                lbl.addStyleName("h2");
                lbl.setWidth("100px");
                titleLayout.addComponent(lbl);

                // ----construct title --------------
                foldernameLbl = new Label(drive.getFoldername());
                foldernameLbl.addStyleName("h3-dropbox");
                titleLayout.with(foldernameLbl).expand(foldernameLbl);

                final PopupButton popupBtn = new PopupButton();
                popupBtn.setStyleName(UIConstants.THEME_BLANK_LINK);

                final OptionPopupContent popupOptionActionLayout = new OptionPopupContent().withWidth("100px");

                Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        popupBtn.setPopupVisible(false);
                        if (!isEdit) {
                            isEdit = true;
                            externalDriveEditLayout.addStyleName("driveEditting");
                            titleLayout.removeComponent(popupBtn);
                            HorizontalLayout layout = editActionHorizontalLayout(drive, titleLayout, foldernameLbl,
                                    externalDriveEditLayout);
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
//                                                externalDriveService.removeWithSession(drive,
//                                                        AppContext.getUsername(), AppContext.getAccountId());
//                                                int index = bodyLayout.getComponentIndex(OneDriveConnectionBodyLayout.this);
//                                                bodyLayout.removeComponent(bodyLayout.getComponent(index + 1));
//                                                bodyLayout.removeComponent(OneDriveConnectionBodyLayout.this);
//                                                ExternalFolder res = (ExternalFolder) externalDriveService
//                                                        .getCurrentResourceByPath(drive, "/");
//                                                if (res != null) {
//                                                    Container dataSource = folderNavigator.getContainerDataSource();
//                                                    final Object[] dataCollectionArray = dataSource.getItemIds().toArray();
//                                                    for (Object id : dataCollectionArray) {
//                                                        Folder folder = (Folder) id;
//                                                        if (folder.getName().equals(res.getExternalDrive().getFoldername())
//                                                                && folder instanceof ExternalFolder) {
//                                                            dataSource.removeItem(folder);
//                                                        }
//                                                    }
//                                                    folderNavigator.setContainerDataSource(dataSource);
//                                                }
                                            }
                                        }
                                    });
                        } catch (Exception e) {
                            throw new MyCollabException(e);
                        }
                    }
                });
                deleteBtn.addStyleName(UIConstants.THEME_LINK);
                popupOptionActionLayout.addOption(deleteBtn);
                popupBtn.setContent(popupOptionActionLayout);
                titleLayout.addComponent(popupBtn);
                titleLayout.setComponentAlignment(popupBtn, Alignment.MIDDLE_RIGHT);

            }
            this.addComponent(externalDriveEditLayout);
        }

        private HorizontalLayout editActionHorizontalLayout(final ExternalDrive drive, final HorizontalLayout parentLayout,
                                                            final Label lbl, final VerticalLayout externalDriveEditLayout) {
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
//                    String folderName = folderNameTextField.getValue().trim();
//                    try {
//                        if (folderName.length() > 0) {
//                            FileUtils.assertValidFolderName(folderName);
//                            ExternalFolder res = (ExternalFolder) externalDriveService.getCurrentResourceByPath(drive, "/");
//
//                            Container dataSource = folderNavigator.getContainerDataSource();
//                            final Object[] dataCollectionArray = dataSource.getItemIds().toArray();
//                            for (int i = 0; i < dataCollectionArray.length; i++) {
//                                Folder folder = (Folder) folderNavigator.getContainerDataSource().getItemIds().toArray()[i];
//                                if (folder.getName().equals(res.getExternalDrive().getFoldername())
//                                        && folder instanceof ExternalFolder) {
//                                    folderNavigator.collapseItem(rootECMFolder);
//                                    folderNavigator.expandItem(rootECMFolder);
//                                    break;
//                                }
//                            }
//                            drive.setFoldername(folderName);
//                            externalDriveService.updateWithSession(drive, AppContext.getUsername());
//
//                            foldernameLbl = new Label(folderName);
//                            foldernameLbl.addStyleName("h3");
//
//                            turnBackMainLayout(parentLayout, foldernameLbl, layout, externalDriveEditLayout);
//                        }
//                    } catch (Exception e) {
//                        throw new MyCollabException(e);
//                    }
                }
            });
            saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
            saveBtn.setIcon(FontAwesome.SAVE);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    turnBackMainLayout(parentLayout, lbl, layout, externalDriveEditLayout);
                }
            });
            cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
            layout.with(saveBtn, cancelBtn);
            return layout;
        }

        private void turnBackMainLayout(HorizontalLayout parentLayout, Label lbl, HorizontalLayout newComponent,
                                        VerticalLayout externalDriveEditLayout) {
            this.isEdit = false;
            parentLayout.replaceComponent(newComponent, lbl);
            parentLayout.setComponentAlignment(lbl, Alignment.MIDDLE_LEFT);
            parentLayout.setExpandRatio(lbl, 1.0f);
            externalDriveEditLayout.removeStyleName("driveEditting");
        }
    }
}
