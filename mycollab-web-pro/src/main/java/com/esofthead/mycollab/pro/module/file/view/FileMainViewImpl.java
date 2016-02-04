package com.esofthead.mycollab.pro.module.file.view;

import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.FileMainView;
import com.esofthead.mycollab.module.file.view.components.ResourcesDisplayComponent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.web.ui.OptionPopupContent;
import com.esofthead.mycollab.vaadin.web.ui.Separator;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

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

    private ResourcesDisplayComponent resourceHandlerLayout;

    private MVerticalLayout mainBodyResourceLayout;

    private ResourceService resourceService;
    private ExternalResourceService externalResourceService;

    public FileMainViewImpl() {
        resourceService = ApplicationContextUtil.getSpringBean(ResourceService.class);
        externalResourceService = ApplicationContextUtil.getSpringBean(ExternalResourceService.class);

        String rootPath = String.format("%d/Documents", AppContext.getAccountId());
        rootFolder = new Folder(rootPath);
    }

    private HorizontalLayout constructHeader() {
        MHorizontalLayout layout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false))
                .withWidth("100%");
        Label searchTitle = new Label(FontAwesome.BRIEFCASE.getHtml() + " Files", ContentMode.HTML);
        searchTitle.setStyleName(ValoTheme.LABEL_H2);
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
        navButton.addStyleName(UIConstants.BUTTON_OPTION);
        topControlMenu.with(navButton).withAlign(navButton, Alignment.MIDDLE_RIGHT);

        Button settingBtn = new Button();
        settingBtn.setIcon(FontAwesome.COG);
        settingBtn.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                CloudDriveSettingWindow cloudDriveSettingWindow = new CloudDriveSettingWindow();
                UI.getCurrent().addWindow(cloudDriveSettingWindow);
            }
        });
        settingBtn.addStyleName(UIConstants.BUTTON_OPTION);
        navButton.addButton(settingBtn);

        final PopupButton linkBtn = new PopupButton();
        linkBtn.setIcon(FontAwesome.LINK);
        linkBtn.addStyleName(UIConstants.BUTTON_OPTION);
        linkBtn.setWidth("65px");

        final OptionPopupContent filterBtnLayout = new OptionPopupContent();

        Button connectDropboxBtn = new Button("Connect Dropbox", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                linkBtn.setPopupVisible(false);
                OauthWindowFactory oauthWindowFactory = ViewManager.getCacheComponent(OauthWindowFactory.class);
                Window dropboxWindow = oauthWindowFactory.newDropBoxAuthWindow();
                UI.getCurrent().addWindow(dropboxWindow);
            }
        });

        connectDropboxBtn.addStyleName(UIConstants.BUTTON_LINK);
        connectDropboxBtn.setIcon(FontAwesome.DROPBOX);
        filterBtnLayout.addOption(connectDropboxBtn);

        linkBtn.setContent(filterBtnLayout);
        navButton.addButton(linkBtn);

        BillingPlan currentBillingPlan = AppContext.getBillingAccount().getBillingPlan();
        DriveInfoService driveInfoService = ApplicationContextUtil.getSpringBean(DriveInfoService.class);
        String usedStorageTxt = FileUtils.getVolumeDisplay(driveInfoService.getUsedStorageVolume(AppContext.getAccountId()))
                + " of " + FileUtils.getVolumeDisplay(currentBillingPlan.getVolume());
        Label usedVolumeInfo = new Label("<div>" + usedStorageTxt + "</div>", ContentMode.HTML);
        usedVolumeInfo.addStyleName("volumeUsageInfo");
        topControlMenuWrapper.with(usedVolumeInfo).withAlign(usedVolumeInfo, Alignment.TOP_CENTER);

        menuLayout.addComponent(topControlMenuWrapper);

        this.folderNavigator = new FolderNavigatorMenu(rootFolder.getPath());
        menuLayout.addComponent(this.folderNavigator);
        return menuBarContainerHorizontalLayout;
    }

    private void browseFolder(Folder baseFolder) {
        mainBodyResourceLayout.removeAllComponents();
        mainBodyResourceLayout.addComponent(resourceHandlerLayout);
        resourceHandlerLayout.constructBodyItemContainer(baseFolder);
    }

    private void displayResources(String rootFolderName) {
        folderNavigator.addSelectFolderListener(new FolderNavigatorMenu.SelectedFolderListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void selectFolder(FolderNavigatorMenu.SelectFolderEvent event) {
                Folder folder = event.getFolder();
                if (folder != null) {
                    browseFolder(folder);
                }
            }
        });

        resourceHandlerLayout.displayComponent(rootFolder, rootFolderName);

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

    private void initComponents() {
        this.removeAllComponents();
        MHorizontalLayout mainView = new MHorizontalLayout().withWidth("100%");

        HorizontalLayout leftColumn = buildLeftColumn();
        mainView.with(leftColumn).withAlign(leftColumn, Alignment.TOP_LEFT);

        Separator separator = new Separator();
        separator.setHeight("100%");
        separator.setWidthUndefined();
        mainView.with(separator).withAlign(separator, Alignment.TOP_LEFT);


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

    @Override
    public void display() {
        initComponents();
        displayResources("Documents");
    }
}
