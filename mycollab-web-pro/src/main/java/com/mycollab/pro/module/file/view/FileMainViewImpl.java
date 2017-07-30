package com.mycollab.pro.module.file.view;

import com.mycollab.core.utils.FileUtils;
import com.mycollab.module.ecm.domain.Folder;
import com.mycollab.module.ecm.service.DriveInfoService;
import com.mycollab.module.file.view.FileMainView;
import com.mycollab.module.file.view.ResourcesDisplayComponent;
import com.mycollab.module.user.domain.BillingPlan;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class FileMainViewImpl extends AbstractLazyPageView implements FileMainView {
    private static final long serialVersionUID = 1L;

    private Folder rootFolder;

    private FolderNavigatorMenu folderNavigator;
    private ResourcesDisplayComponent resourceHandlerLayout;
    private MVerticalLayout mainBodyResourceLayout;

    public FileMainViewImpl() {
        String rootPath = String.format("%d/Documents", AppUI.getAccountId());
        rootFolder = new Folder(rootPath);
    }

    @Override
    protected void displayView() {
        MHorizontalLayout mainView = new MHorizontalLayout().withFullWidth();

        HorizontalLayout leftColumn = buildLeftColumn();
        mainView.with(leftColumn).withAlign(leftColumn, Alignment.TOP_LEFT);

        // here for MainBodyResourceLayout class
        MVerticalLayout rightColumn = new MVerticalLayout();
        mainBodyResourceLayout = new MVerticalLayout().withMargin(new MarginInfo(false, true, false, false));
        resourceHandlerLayout = new ResourcesDisplayComponent(rootFolder);
        mainBodyResourceLayout.addComponent(resourceHandlerLayout);
        rightColumn.addComponent(mainBodyResourceLayout);

        mainView.with(rightColumn).withAlign(rightColumn, Alignment.TOP_LEFT).expand(rightColumn);

        this.with(mainView).withAlign(mainView, Alignment.MIDDLE_CENTER);
        displayResources("Documents");
    }

    private HorizontalLayout buildLeftColumn() {
        MHorizontalLayout menuBarContainerHorizontalLayout = new MHorizontalLayout()
                .withMargin(new MarginInfo(false, true, true, true));

        MVerticalLayout menuLayout = new MVerticalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withWidth("250px");

        menuBarContainerHorizontalLayout.addComponent(menuLayout);

        MVerticalLayout topControlMenuWrapper = new MVerticalLayout().withSpacing(false).withWidth("250px");

        MHorizontalLayout topControlMenu = new MHorizontalLayout().withFullWidth();
        topControlMenu.addStyleName(WebThemes.PANEL_HEADER);

        topControlMenuWrapper.addComponent(topControlMenu);

        MHorizontalLayout navButton = new MHorizontalLayout();
        topControlMenu.with(navButton).withAlign(navButton, Alignment.MIDDLE_RIGHT);

        MButton settingBtn = new MButton("", clickEvent -> {
            CloudDriveSettingWindow cloudDriveSettingWindow = new CloudDriveSettingWindow();
            UI.getCurrent().addWindow(cloudDriveSettingWindow);
        }).withIcon(FontAwesome.COG).withStyleName(WebThemes.BUTTON_ICON_ONLY);

        navButton.with(settingBtn);

        final PopupButton linkBtn = new PopupButton();
        linkBtn.setIcon(FontAwesome.LINK);
        linkBtn.addStyleName(WebThemes.BUTTON_ICON_ONLY);

        final OptionPopupContent filterBtnLayout = new OptionPopupContent();

        MButton connectDropboxBtn = new MButton("Connect Dropbox", clickEvent -> {
            linkBtn.setPopupVisible(false);
            OauthWindowFactory oauthWindowFactory = ViewManager.getCacheComponent(OauthWindowFactory.class);
            Window dropboxWindow = oauthWindowFactory.newDropBoxAuthWindow();
            UI.getCurrent().addWindow(dropboxWindow);
        }).withIcon(FontAwesome.DROPBOX);

        filterBtnLayout.addOption(connectDropboxBtn);

        linkBtn.setContent(filterBtnLayout);
        navButton.with(linkBtn);

        BillingPlan currentBillingPlan = AppUI.getBillingAccount().getBillingPlan();
        DriveInfoService driveInfoService = AppContextUtil.getSpringBean(DriveInfoService.class);
        String usedStorageTxt = FileUtils.getVolumeDisplay(driveInfoService.getUsedStorageVolume(AppUI.getAccountId()))
                + " of " + FileUtils.getVolumeDisplay(currentBillingPlan.getVolume());
        ELabel usedVolumeInfo = ELabel.html("<div>" + usedStorageTxt + "</div>").withStyleName("volumeUsageInfo");
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
}
