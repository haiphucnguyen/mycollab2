package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
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
import com.esofthead.mycollab.vaadin.ui.OptionPopupContent;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MMarginInfo;
import org.vaadin.maddon.layouts.MVerticalLayout;
import org.vaadin.peter.buttongroup.ButtonGroup;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class FileMainViewImpl extends AbstractPageView implements FileMainView {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(FileMainViewImpl.class);

    private FolderNavigatorMenu folderNavigator;

    private Folder rootFolder;
    private String rootPath;

    private ResourcesDisplayComponent resourceHandlerLayout;

    private SettingConnectionDrive settingConnectionDrive;
    private MVerticalLayout mainBodyResourceLayout;

    private ResourceService resourceService;
    private ExternalResourceService externalResourceService;

    public FileMainViewImpl() {
        resourceService = ApplicationContextUtil.getSpringBean(ResourceService.class);
        externalResourceService = ApplicationContextUtil.getSpringBean(ExternalResourceService.class);

        MHorizontalLayout mainView = new MHorizontalLayout().withWidth("100%");

        HorizontalLayout leftColumn = buildLeftColumn();
        mainView.with(leftColumn).withAlign(leftColumn, Alignment.TOP_LEFT);

        Separator separator = new Separator();
        separator.setHeight("100%");
        separator.setWidthUndefined();
        mainView.with(separator).withAlign(separator, Alignment.TOP_LEFT);

        this.rootFolder = new Folder(rootPath);

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
        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MMarginInfo(true, false, false, false))
                .withWidth("100%");
        Label searchTitle = new Label(FontAwesome.BRIEFCASE.getHtml() + " Files", ContentMode.HTML);
        searchTitle.setStyleName("headerName");
        layout.with(searchTitle).expand(searchTitle);
        return layout;
    }

    private HorizontalLayout buildLeftColumn() {
        MHorizontalLayout menuBarContainerHorizontalLayout = new MHorizontalLayout()
                .withMargin(new MarginInfo(false, true, true, true));

        MVerticalLayout menuLayout = new MVerticalLayout().withWidth("250px");

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

        final OptionPopupContent filterBtnLayout = new OptionPopupContent().withWidth("180px");

        Button connectDropboxBtn = new Button("Connect Dropbox", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                linkBtn.setPopupVisible(false);
                OauthWindowFactory oauthWindowFactory = ApplicationContextUtil.getSpringBean(OauthWindowFactory.class);
                AbstractCloudDriveOAuthWindow dropboxWindow = oauthWindowFactory.newDropBoxAuthWindow();
                UI.getCurrent().addWindow(dropboxWindow);
                dropboxWindow.addExternalDriveConnectedListener(new AbstractCloudDriveOAuthWindow.ExternalDriveConnectedListener() {
                    @Override
                    public void connectedSuccess(AbstractCloudDriveOAuthWindow.ExternalDriveConnectedEvent event) {
                        ExternalDrive data = event.getExternalDrive();
                    }
                });
            }
        });

        connectDropboxBtn.addStyleName(UIConstants.THEME_LINK);
        connectDropboxBtn.setIcon(FontAwesome.DROPBOX);
        filterBtnLayout.addOption(connectDropboxBtn);

        linkBtn.setContent(filterBtnLayout);
        navButton.addButton(linkBtn);

        BillingPlan currentBillingPlan = AppContext.getBillingAccount().getBillingPlan();
        DriveInfoService driveInfoService = ApplicationContextUtil.getSpringBean(DriveInfoService.class);
        String usedStorageTxt = FileUtils.getVolumeDisplay(driveInfoService
                .getUsedStorageVolume(AppContext.getAccountId()))
                + " of " + FileUtils.getVolumeDisplay(currentBillingPlan.getVolume());
        Label usedVolumeInfo = new Label("<div>" + usedStorageTxt + "</div>", ContentMode.HTML);
        usedVolumeInfo.addStyleName("volumeUsageInfo");
        topControlMenuWrapper.with(usedVolumeInfo).withAlign(usedVolumeInfo, Alignment.TOP_CENTER);

        menuLayout.addComponent(topControlMenuWrapper);

        this.rootPath = String.format("%d/Documents", AppContext.getAccountId());
        this.folderNavigator = new FolderNavigatorMenu(rootPath);
        menuLayout.addComponent(this.folderNavigator);
        return menuBarContainerHorizontalLayout;
    }

    private void gotoFileMainViewPage(Folder baseFolder) {
        this.rootFolder = baseFolder;

        mainBodyResourceLayout.removeAllComponents();
        mainBodyResourceLayout.addComponent(resourceHandlerLayout);
        resourceHandlerLayout.constructBodyItemContainer(rootFolder);
    }

    private void displayResources(String rootPath, String rootFolderName) {
        this.rootFolder = new Folder(rootPath);

        this.folderNavigator.removeAllItems();
        this.folderNavigator.addItem(this.rootFolder);
        this.folderNavigator.setItemCaption(this.rootFolder, rootFolderName);
        this.folderNavigator.setItemIcon(this.rootFolder, FontAwesome.FOLDER);
        this.folderNavigator.collapseItem(this.rootFolder);

        resourceHandlerLayout.displayComponent(this.rootFolder, rootFolderName);

        resourceHandlerLayout.addSearchHandlerToBreadCrumb(new SearchHandler<FileSearchCriteria>() {
            @Override
            public void onSearch(FileSearchCriteria criteria) {
                Resource selectedFolder;
                if (StorageNames.DROPBOX.equals(criteria.getStorageName())) {
                    selectedFolder = externalResourceService.getCurrentResourceByPath(
                            criteria.getExternalDrive(), criteria.getBaseFolder());
                } else {
                    selectedFolder = resourceService.getResource(criteria.getBaseFolder());
                }

                if (selectedFolder == null) {
                    LOG.error(String.format("Can not find folder with path %s--%s", criteria.getBaseFolder(),
                            criteria.getRootFolder()));
                } else if (!(selectedFolder instanceof Folder)) {
                    LOG.error(String.format("Expect folder but the result is file %s--%s", criteria.getBaseFolder(),
                            criteria.getRootFolder()));
                } else {
                    Folder resultFolder = (Folder) selectedFolder;
                    resourceHandlerLayout.constructBodyItemContainer(resultFolder);
                    rootFolder = resultFolder;
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
                Folder folder = event.getFolder();
                if (folder != null) {
                    gotoFileMainViewPage(folder);
                }

            }
        });
        displayResources(rootPath, "Documents");
    }
}
