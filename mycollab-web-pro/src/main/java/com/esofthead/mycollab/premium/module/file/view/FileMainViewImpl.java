package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow;
import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow.ExternalDriveConnectedEvent;
import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow.ExternalDriveConnectedListener;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.FileMainView;
import com.esofthead.mycollab.module.file.view.components.ResourcesDisplayComponent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.premium.module.file.view.FolderNavigatorMenu.SelectFolderEvent;
import com.esofthead.mycollab.premium.module.file.view.FolderNavigatorMenu.SelectedFolderListener;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.data.Container;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MMarginInfo;
import org.vaadin.maddon.layouts.MVerticalLayout;
import org.vaadin.peter.buttongroup.ButtonGroup;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class FileMainViewImpl extends AbstractPageView implements FileMainView {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(FileMainViewImpl.class);

    private static final String illegalFileNamePattern = "[<>:&/\\|?*&]";

    private FolderNavigatorMenu folderNavigator;

    private Folder rootECMFolder;

    private Folder rootFolder;
    private String rootPath;

    private ResourcesDisplayComponent resourceHandlerLayout;

    private SettingConnectionDrive settingConnectionDrive;
    private MVerticalLayout mainBodyResourceLayout;

    private ResourceService resourceService;
    private ExternalDriveService externalDriveService;
    private ExternalResourceService externalResourceService;

    public FileMainViewImpl() {
        resourceService = ApplicationContextUtil
                .getSpringBean(ResourceService.class);
        externalDriveService = ApplicationContextUtil
                .getSpringBean(ExternalDriveService.class);
        externalResourceService = ApplicationContextUtil
                .getSpringBean(ExternalResourceService.class);

        MHorizontalLayout mainView = new MHorizontalLayout().withWidth("100%");

        HorizontalLayout leftColumn = buildLeftColumn();
        mainView.with(leftColumn).withAlign(leftColumn, Alignment.TOP_LEFT);

        Separator separator = new Separator();
        separator.setHeight("100%");
        separator.setWidthUndefined();
        mainView.with(separator).withAlign(separator, Alignment.TOP_LEFT);

        this.rootFolder = new Folder(rootPath);
        this.rootECMFolder = rootFolder;

        // here for MainBodyResourceLayout class
        MVerticalLayout rightColumn = new MVerticalLayout();
        rightColumn.addComponent(constructHeader());

        mainBodyResourceLayout = new MVerticalLayout().withMargin(new MarginInfo(false, true, false, false));

        resourceHandlerLayout = new ResourcesDisplayComponent(rootFolder);
        mainBodyResourceLayout.addComponent(resourceHandlerLayout);

        rightColumn.addComponent(mainBodyResourceLayout);

        mainView.with(rightColumn).withAlign(rightColumn, Alignment.TOP_LEFT).expand(rightColumn);

        this.with(mainView).withAlign(mainView, Alignment.MIDDLE_CENTER);

    }

    private HorizontalLayout constructHeader() {
        final MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MMarginInfo(true, false, false, false))
                .withWidth("100%");
        final Label searchTitle = new Label(FontAwesome.BRIEFCASE.getHtml() +  " Files", ContentMode.HTML);
        searchTitle.setStyleName("headerName");
        layout.with(searchTitle).expand(searchTitle);
        return layout;
    }

    private HorizontalLayout buildLeftColumn() {
        final MHorizontalLayout menuBarContainerHorizontalLayout = new MHorizontalLayout()
                .withMargin(new MarginInfo(false, true, true, true));

        final MVerticalLayout menuLayout = new MVerticalLayout().withWidth("250px");

        menuBarContainerHorizontalLayout.addComponent(menuLayout);

        MVerticalLayout topControlMenuWrapper = new MVerticalLayout().withSpacing(false).withWidth("250px");

        MHorizontalLayout topControlMenu = new MHorizontalLayout().withWidth("100%");
        topControlMenu.addStyleName("border-box2-no-margin");
        topControlMenu.addStyleName("file-topcontrols");

        topControlMenuWrapper.addComponent(topControlMenu);

        ButtonGroup navButton = new ButtonGroup();
        navButton.addStyleName(UIConstants.THEME_GRAY_LINK);
        topControlMenu.with(navButton).withAlign(navButton, Alignment.MIDDLE_RIGHT);

        Button settingBtn = new Button();
        settingBtn.setIcon(FontAwesome.COG);
        settingBtn.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                mainBodyResourceLayout.removeAllComponents();
                settingConnectionDrive = new SettingConnectionDrive();
                mainBodyResourceLayout.addComponent(settingConnectionDrive);
            }
        });
        settingBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
        navButton.addButton(settingBtn);

        final PopupButton linkBtn = new PopupButton();
        linkBtn.setIcon(FontAwesome.LINK);
        linkBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
        linkBtn.setWidth("65px");

        final MVerticalLayout filterBtnLayout = new MVerticalLayout().withWidth("180px");

        Button uploadDropboxBtn = new Button("Connect Dropbox",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        linkBtn.setPopupVisible(false);
                        AbstractCloudDriveOAuthWindow cloudDriveOAuthWindow = ComponentManagerFactory
                                .getCloudDriveOAuthWindow("Add Dropbox");
                        cloudDriveOAuthWindow
                                .addExternalDriveConnectedListener(new ExternalDriveConnectedListener() {
                                    private static final long serialVersionUID = 1L;

                                    @Override
                                    public void connectedSuccess(
                                            ExternalDriveConnectedEvent event) {
                                        folderNavigator
                                                .expandItem(rootECMFolder);

                                    }
                                });
                        UI.getCurrent().addWindow(cloudDriveOAuthWindow);
                    }
                });
        uploadDropboxBtn.addStyleName("link");
        uploadDropboxBtn.setIcon(FontAwesome.DROPBOX);
        filterBtnLayout.addComponent(uploadDropboxBtn);

        linkBtn.setContent(filterBtnLayout);

        navButton.addButton(linkBtn);

        Label usedVolumeInfo = new Label();
        usedVolumeInfo.addStyleName("volumeUsageInfo");
        BillingPlan currentBillingPlan = AppContext.getBillingAccount()
                .getBillingPlan();
        DriveInfoService driveInfoService = ApplicationContextUtil
                .getSpringBean(DriveInfoService.class);
        String usedStorageTxt = FileUtils.getVolumeDisplay(driveInfoService
                .getUsedStorageVolume(AppContext.getAccountId()))
                + " of " + FileUtils.getVolumeDisplay(currentBillingPlan.getVolume());
        usedVolumeInfo
                .setValue("<div id='left-side'>&nbsp;</div><div id='info-content'>"
                        + usedStorageTxt
                        + "</div><div id='right-side'>&nbsp;</div>");
        usedVolumeInfo.setContentMode(ContentMode.HTML);
        usedVolumeInfo.setWidth("100%");
        topControlMenuWrapper.with(usedVolumeInfo).withAlign(usedVolumeInfo,
                Alignment.TOP_CENTER);

        menuLayout.addComponent(topControlMenuWrapper);

        this.rootPath = String
                .format("%d/Documents", AppContext.getAccountId());

        this.folderNavigator = new FolderNavigatorMenu(rootPath);

        menuLayout.addComponent(this.folderNavigator);

        return menuBarContainerHorizontalLayout;
    }

    private boolean checkValidFolderName(String value) {
        Pattern pattern = Pattern.compile(illegalFileNamePattern);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    private void gotoFileMainViewPage(Folder baseFolder) {
        this.rootFolder = baseFolder;

        mainBodyResourceLayout.removeAllComponents();
        mainBodyResourceLayout.setSpacing(true);
        mainBodyResourceLayout.addComponent(resourceHandlerLayout);

        resourceHandlerLayout.constructBodyItemContainer(rootFolder);
    }

    private void displayResources(String rootPath, String rootFolderName) {
        this.rootFolder = new Folder(rootPath);
        this.rootECMFolder = this.rootFolder;

        this.folderNavigator.removeAllItems();
        this.folderNavigator.addItem(this.rootFolder);
        this.folderNavigator.setItemCaption(this.rootFolder, rootFolderName);
        this.folderNavigator.setItemIcon(this.rootFolder,
                FontAwesome.FOLDER);
        this.folderNavigator.collapseItem(this.rootFolder);

        resourceHandlerLayout.displayComponent(this.rootFolder,
                rootFolderName);

        resourceHandlerLayout
                .addSearchHandlerToBreadCrumb(new SearchHandler<FileSearchCriteria>() {
                    @Override
                    public void onSearch(FileSearchCriteria criteria) {
                        Resource selectedFolder;
                        if (StorageNames.DROPBOX.equals(criteria
                                .getStorageName())) {
                            selectedFolder = externalResourceService
                                    .getCurrentResourceByPath(
                                            criteria.getExternalDrive(),
                                            criteria.getBaseFolder());
                        } else {
                            selectedFolder = FileMainViewImpl.this.resourceService
                                    .getResource(criteria.getBaseFolder());
                        }

                        if (selectedFolder == null) {
                            LOG.error("Can not find folder with path "
                                    + criteria.getBaseFolder() + "--"
                                    + criteria.getRootFolder());
                        } else if (!(selectedFolder instanceof Folder)) {
                            LOG.error("Expect folder but the result is file "
                                    + criteria.getBaseFolder() + "--"
                                    + criteria.getRootFolder());
                        } else {
                            Folder resultFolder = (Folder) selectedFolder;
                            resourceHandlerLayout
                                    .constructBodyItemContainer(resultFolder);

                            FileMainViewImpl.this.rootFolder = resultFolder;
                        }

                    }
                });
    }

    @Override
    public void display() {
        folderNavigator.addSelectFolderListener(new SelectedFolderListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectFolder(SelectFolderEvent event) {
                Folder folder = (Folder) event.getData();
                if (folder != null) {
                    gotoFileMainViewPage(folder);
                }

            }
        });
        displayResources(rootPath, "Documents");
    }

    private class SettingConnectionDrive extends VerticalLayout {
        private static final long serialVersionUID = 1L;

        private final Button connectAccountBtn;
        private final MVerticalLayout bodyLayout;
        private final MVerticalLayout mainLayout;

        public SettingConnectionDrive() {
            mainLayout = new MVerticalLayout().withWidth("100%");

            connectAccountBtn = new Button("Connect account",
                    new ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            AbstractCloudDriveOAuthWindow cloudDriveOAuthWindow = ComponentManagerFactory
                                    .getCloudDriveOAuthWindow("Add Dropbox");
                            cloudDriveOAuthWindow
                                    .addExternalDriveConnectedListener(new ExternalDriveConnectedListener() {
                                        private static final long serialVersionUID = 1L;

                                        @Override
                                        public void connectedSuccess(
                                                ExternalDriveConnectedEvent event) {
                                            FileMainViewImpl.this.folderNavigator
                                                    .collapseItem(rootECMFolder);
                                            FileMainViewImpl.this.folderNavigator
                                                    .expandItem(rootECMFolder);
                                            OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(
                                                    (ExternalDrive) event
                                                            .getData());
                                            bodyLayout.addComponent(layout);
                                            bodyLayout.setComponentAlignment(
                                                    layout,
                                                    Alignment.MIDDLE_LEFT);
                                            bodyLayout.addComponent(new Hr());

                                        }
                                    });
                            UI.getCurrent().addWindow(cloudDriveOAuthWindow);
                        }
                    });
            connectAccountBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
            mainLayout.addComponent(connectAccountBtn);

            bodyLayout = new MVerticalLayout().withSpacing(false).withMargin(false).withWidth("100%");

            mainLayout.addComponent(bodyLayout);
            this.addComponent(mainLayout);

            List<ExternalDrive> lst = externalDriveService
                    .getExternalDrivesOfUser(AppContext.getUsername());

            bodyLayout.addComponent(new Hr());
            for (final ExternalDrive drive : lst) {
                OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(
                        drive);
                bodyLayout.with(layout, new Hr()).withAlign(layout, Alignment.MIDDLE_LEFT);
            }
        }

        private class OneDriveConnectionBodyLayout extends VerticalLayout {
            private static final long serialVersionUID = 1L;
            private boolean isEdit = false;
            private Label foldernameLbl;

            public OneDriveConnectionBodyLayout(final ExternalDrive drive) {
                final MVerticalLayout externalDriveEditLayout = new MVerticalLayout();

                final MHorizontalLayout title = new MHorizontalLayout().withWidth("100%");
                externalDriveEditLayout.addComponent(title);

                CssLayout iconWapper = new CssLayout();
                iconWapper.setWidth("60px");
                final Embedded embed = new Embedded();
                if (drive.getStoragename().equals(StorageNames.DROPBOX))
                    embed.setIcon(MyCollabResource
                            .newResource("icons/48/ecm/dropbox.png"));
                iconWapper.addComponent(embed);
                title.addComponent(iconWapper);
                title.setComponentAlignment(iconWapper, Alignment.MIDDLE_LEFT);

                if (drive.getStoragename().equals(StorageNames.DROPBOX)) {
                    Label lbl = new Label("Dropbox");
                    lbl.addStyleName("h2");
                    lbl.setWidth("100px");
                    title.addComponent(lbl);
                    title.setComponentAlignment(lbl, Alignment.MIDDLE_LEFT);

                    // ----construct title --------------
                    foldernameLbl = new Label(drive.getFoldername());
                    foldernameLbl.addStyleName("h3-dropbox");
                    title.addComponent(foldernameLbl);
                    title.setComponentAlignment(foldernameLbl,
                            Alignment.MIDDLE_LEFT);
                    title.setExpandRatio(foldernameLbl, 1.0f);

                    final PopupButton popupBtn = new PopupButton();
                    popupBtn.setIcon(MyCollabResource
                            .newResource("icons/16/item_settings_big.png"));
                    popupBtn.setStyleName(UIConstants.THEME_BLANK_LINK);

                    final MVerticalLayout popupOptionActionLayout = new MVerticalLayout().withWidth("100px");

                    Button editBtn = new Button(
                            AppContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                            new Button.ClickListener() {
                                private static final long serialVersionUID = 1L;

                                @Override
                                public void buttonClick(ClickEvent event) {
                                    popupBtn.setPopupVisible(false);
                                    if (!isEdit) {
                                        isEdit = true;
                                        externalDriveEditLayout
                                                .addStyleName("driveEditting");
                                        title.removeComponent(popupBtn);
                                        HorizontalLayout layout = editActionHorizontalLayout(
                                                drive, title, foldernameLbl,
                                                externalDriveEditLayout);
                                        title.replaceComponent(foldernameLbl,
                                                layout);
                                        title.setComponentAlignment(layout,
                                                Alignment.MIDDLE_LEFT);
                                        title.setExpandRatio(layout, 1.0f);
                                        title.addComponent(popupBtn);
                                        title.setComponentAlignment(popupBtn,
                                                Alignment.MIDDLE_RIGHT);
                                    }
                                }
                            });
                    editBtn.addStyleName("link");
                    popupOptionActionLayout.addComponent(editBtn);

                    Button deleteBtn = new Button(
                            AppContext.getMessage(GenericI18Enum.BUTTON_DELETE),
                            new Button.ClickListener() {
                                private static final long serialVersionUID = 1L;

                                @Override
                                public void buttonClick(ClickEvent event) {
                                    try {
                                        ConfirmDialogExt.show(
                                                UI.getCurrent(),
                                                AppContext
                                                        .getMessage(
                                                                GenericI18Enum.DIALOG_DELETE_TITLE,
                                                                SiteConfiguration
                                                                        .getSiteName()),
                                                AppContext
                                                        .getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                                                AppContext
                                                        .getMessage(GenericI18Enum.BUTTON_YES),
                                                AppContext
                                                        .getMessage(GenericI18Enum.BUTTON_NO),
                                                new ConfirmDialog.Listener() {
                                                    private static final long serialVersionUID = 1L;

                                                    @Override
                                                    public void onClose(
                                                            final ConfirmDialog dialog) {
                                                        if (dialog
                                                                .isConfirmed()) {
                                                            externalDriveService
                                                                    .removeWithSession(
                                                                            drive.getId(),
                                                                            AppContext
                                                                                    .getUsername(),
                                                                            AppContext
                                                                                    .getAccountId());
                                                            int index = bodyLayout
                                                                    .getComponentIndex(OneDriveConnectionBodyLayout.this);
                                                            bodyLayout
                                                                    .removeComponent(bodyLayout
                                                                            .getComponent(index + 1));
                                                            bodyLayout
                                                                    .removeComponent(OneDriveConnectionBodyLayout.this);
                                                            ExternalFolder res = (ExternalFolder) externalResourceService
                                                                    .getCurrentResourceByPath(
                                                                            drive,
                                                                            "/");
                                                            if (res != null) {
                                                                Container dataSource = FileMainViewImpl.this.folderNavigator
                                                                        .getContainerDataSource();
                                                                final Object[] dataCollectionArray = dataSource
                                                                        .getItemIds()
                                                                        .toArray();
                                                                for (Object id : dataCollectionArray) {
                                                                    Folder folder = (Folder) id;
                                                                    if (folder
                                                                            .getName()
                                                                            .equals(res
                                                                                    .getExternalDrive()
                                                                                    .getFoldername())
                                                                            && folder instanceof ExternalFolder) {
                                                                        dataSource
                                                                                .removeItem(folder);
                                                                    }
                                                                }
                                                                FileMainViewImpl.this.folderNavigator
                                                                        .setContainerDataSource(dataSource);
                                                            }
                                                        }
                                                    }
                                                });
                                    } catch (Exception e) {
                                        throw new MyCollabException(e);
                                    }
                                }
                            });
                    deleteBtn.addStyleName("link");
                    popupOptionActionLayout.addComponent(deleteBtn);
                    popupBtn.setContent(popupOptionActionLayout);
                    title.addComponent(popupBtn);
                    title.setComponentAlignment(popupBtn,
                            Alignment.MIDDLE_RIGHT);

                }
                this.addComponent(externalDriveEditLayout);
            }

            private HorizontalLayout editActionHorizontalLayout(
                    final ExternalDrive drive,
                    final HorizontalLayout parentLayout, final Label lbl,
                    final VerticalLayout externalDriveEditLayout) {
                final MHorizontalLayout layout = new MHorizontalLayout();
                layout.addStyleName("resourceItem");

                Label folderTitleLbl = new Label("Folder title");
                layout.with(folderTitleLbl).withAlign(folderTitleLbl, Alignment.MIDDLE_LEFT);

                final TextField folderNameTextField = new TextField();
                folderNameTextField.setImmediate(true);
                folderNameTextField.setValue(drive.getFoldername());
                layout.addComponent(folderNameTextField);

                Button saveBtn = new Button(
                        AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                        new ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                String folderName = folderNameTextField
                                        .getValue().trim();
                                try {
                                    if (folderName.length() > 0) {
                                        boolean checkingError = checkValidFolderName(folderName);
                                        if (checkingError) {
                                            NotificationUtil
                                                    .showErrorNotification("Please enter valid folder name except any follow characters : <>:&/\\|?*&");
                                            return;
                                        }
                                        ExternalFolder res = (ExternalFolder) externalResourceService
                                                .getCurrentResourceByPath(
                                                        drive, "/");

                                        Container dataSource = FileMainViewImpl.this.folderNavigator
                                                .getContainerDataSource();
                                        final Object[] dataCollectionArray = dataSource
                                                .getItemIds().toArray();
                                        for (int i = 0; i < dataCollectionArray.length; i++) {
                                            Folder folder = (Folder) FileMainViewImpl.this.folderNavigator
                                                    .getContainerDataSource()
                                                    .getItemIds().toArray()[i];
                                            if (folder.getName().equals(
                                                    res.getExternalDrive()
                                                            .getFoldername())
                                                    && folder instanceof ExternalFolder) {
                                                FileMainViewImpl.this.folderNavigator
                                                        .collapseItem(rootECMFolder);
                                                FileMainViewImpl.this.folderNavigator
                                                        .expandItem(rootECMFolder);
                                                break;
                                            }
                                        }
                                        drive.setFoldername(folderName);
                                        externalDriveService.updateWithSession(
                                                drive,
                                                AppContext.getUsername());

                                        foldernameLbl = new Label(folderName);
                                        foldernameLbl.addStyleName("h3");

                                        turnBackMainLayout(parentLayout,
                                                foldernameLbl, layout,
                                                externalDriveEditLayout);
                                    }
                                } catch (Exception e) {
                                    throw new MyCollabException(e);
                                }
                            }
                        });
                saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
                saveBtn.setIcon(FontAwesome.SAVE);

                Button cancelBtn = new Button(
                        AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                        new ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                turnBackMainLayout(parentLayout, lbl, layout,
                                        externalDriveEditLayout);
                            }
                        });
                cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
                layout.with(saveBtn, cancelBtn);
                return layout;
            }

            private void turnBackMainLayout(
                    final HorizontalLayout parentLayout, Label lbl,
                    HorizontalLayout newComponent,
                    VerticalLayout externalDriveEditLayout) {
                this.isEdit = false;
                parentLayout.replaceComponent(newComponent, lbl);
                parentLayout.setComponentAlignment(lbl, Alignment.MIDDLE_LEFT);
                parentLayout.setExpandRatio(lbl, 1.0f);
                externalDriveEditLayout.removeStyleName("driveEditting");
            }
        }
    }
}
