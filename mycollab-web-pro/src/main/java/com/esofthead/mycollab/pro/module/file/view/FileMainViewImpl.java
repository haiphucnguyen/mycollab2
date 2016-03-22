package com.esofthead.mycollab.pro.module.file.view;

import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.view.FileMainView;
import com.esofthead.mycollab.module.file.view.components.ResourcesDisplayComponent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
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

    private HorizontalLayout buildLeftColumn() {
        MHorizontalLayout menuBarContainerHorizontalLayout = new MHorizontalLayout()
                .withMargin(new MarginInfo(false, true, true, true));

        MVerticalLayout menuLayout = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withWidth("250px");

        menuBarContainerHorizontalLayout.addComponent(menuLayout);

        MVerticalLayout topControlMenuWrapper = new MVerticalLayout().withSpacing(false).withWidth("250px");

        MHorizontalLayout topControlMenu = new MHorizontalLayout().withWidth("100%");
        topControlMenu.addStyleName("panel-header");

        topControlMenuWrapper.addComponent(topControlMenu);

        MHorizontalLayout navButton = new MHorizontalLayout();
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
        settingBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);
        navButton.with(settingBtn);

        final PopupButton linkBtn = new PopupButton();
        linkBtn.setIcon(FontAwesome.LINK);
        linkBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);

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

        connectDropboxBtn.setIcon(FontAwesome.DROPBOX);
        filterBtnLayout.addOption(connectDropboxBtn);

        linkBtn.setContent(filterBtnLayout);
        navButton.with(linkBtn);

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
