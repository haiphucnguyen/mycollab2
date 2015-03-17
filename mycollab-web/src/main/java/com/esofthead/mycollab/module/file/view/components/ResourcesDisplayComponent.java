/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.file.view.components;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.*;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.resources.LazyStreamSource;
import com.esofthead.mycollab.vaadin.resources.OnDemandFileDownloader;
import com.esofthead.mycollab.vaadin.resources.StreamDownloadResourceUtil;
import com.esofthead.mycollab.vaadin.ui.*;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.util.ReflectTools;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.MultiFileUploadExt;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;
import org.vaadin.peter.buttongroup.ButtonGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ResourcesDisplayComponent extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ResourcesDisplayComponent.class);

    private static final String illegalFileNamePattern = "[.<>:&/\\|?*&%()+-]";

    private ResourceService resourceService;
    private ExternalResourceService externalResourceService;
    private ExternalDriveService externalDriveService;

    private FileBreadcrumb fileBreadCrumb;
    private ResourcesContainer resourcesContainer;
    private ResourcePagingNavigator pagingResourceWapper;

    private Folder baseFolder;
    private String rootFolderName;
    private String rootPath;

    public ResourcesDisplayComponent(final Folder rootFolder) {
        this.setSpacing(true);
        this.baseFolder = rootFolder;
        this.rootPath = rootFolder.getPath();
        externalResourceService = ApplicationContextUtil
                .getSpringBean(ExternalResourceService.class);
        externalDriveService = ApplicationContextUtil
                .getSpringBean(ExternalDriveService.class);
        resourceService = ApplicationContextUtil
                .getSpringBean(ResourceService.class);

        VerticalLayout mainBodyLayout = new VerticalLayout();
        mainBodyLayout.setSpacing(true);
        mainBodyLayout.addStyleName("box-no-border-left");

        // file breadcrum ---------------------
        HorizontalLayout breadcrumbContainer = new HorizontalLayout();
        breadcrumbContainer.setMargin(false);
        fileBreadCrumb = new FileBreadcrumb(rootPath);
        breadcrumbContainer.addComponent(fileBreadCrumb);
        mainBodyLayout.addComponent(breadcrumbContainer);

        // Construct controllGroupBtn
        MHorizontalLayout groupBtns = new MHorizontalLayout();

        final Button selectAllBtn = new Button();
        selectAllBtn.addStyleName(UIConstants.THEME_BROWN_LINK);
        selectAllBtn.setIcon(FontAwesome.SQUARE_O);
        selectAllBtn.setData(false);
        selectAllBtn.setImmediate(true);
        selectAllBtn.setDescription("Select all");

        selectAllBtn.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                if (!(Boolean) selectAllBtn.getData()) {
                    selectAllBtn.setIcon(FontAwesome.CHECK_SQUARE_O);
                    selectAllBtn.setData(true);
                    resourcesContainer.setAllValues(true);
                } else {
                    selectAllBtn.setData(false);
                    selectAllBtn.setIcon(FontAwesome.SQUARE_O);
                    resourcesContainer.setAllValues(false);
                }
            }
        });
        groupBtns.with(selectAllBtn).withAlign(selectAllBtn,
                Alignment.MIDDLE_LEFT);

        Button goUpBtn = new Button("Up");
        goUpBtn.setIcon(FontAwesome.ARROW_UP);

        goUpBtn.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Folder parentFolder;
                if (baseFolder instanceof ExternalFolder) {
                    if (baseFolder.getPath().equals("/")) {
                        parentFolder = baseFolder;
                    } else {
                        parentFolder = externalResourceService
                                .getParentResourceFolder(
                                        ((ExternalFolder) baseFolder)
                                                .getExternalDrive(), baseFolder
                                                .getPath());
                    }
                } else if (!baseFolder.getPath().equals(rootPath)) {
                    parentFolder = resourceService.getParentFolder(baseFolder
                            .getPath());
                } else {
                    parentFolder = baseFolder;
                }

                resourcesContainer.constructBody(parentFolder);
                baseFolder = parentFolder;
                fileBreadCrumb.gotoFolder(baseFolder);
            }
        });
        goUpBtn.setDescription("Back to parent folder");
        goUpBtn.setStyleName(UIConstants.THEME_BROWN_LINK);
        goUpBtn.setDescription("Go up");

        groupBtns.with(goUpBtn)
                .withAlign(goUpBtn, Alignment.MIDDLE_LEFT);

        ButtonGroup navButton = new ButtonGroup();
        navButton.addStyleName(UIConstants.THEME_BROWN_LINK);
        Button createBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_CREATE),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        AddNewFolderWindow addnewFolderWindow = new AddNewFolderWindow();
                        UI.getCurrent().addWindow(addnewFolderWindow);
                    }
                });
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.addStyleName(UIConstants.THEME_BROWN_LINK);
        createBtn.setDescription("Create new folder");
        createBtn.setEnabled(AppContext
                .canWrite(RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS));
        navButton.addButton(createBtn);

        Button uploadBtn = new Button("Upload", new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                MultiUploadContentWindow multiUploadWindow = new MultiUploadContentWindow();
                UI.getCurrent().addWindow(multiUploadWindow);
            }
        });
        uploadBtn.setIcon(FontAwesome.UPLOAD);
        uploadBtn.addStyleName(UIConstants.THEME_BROWN_LINK);
        uploadBtn.setDescription("Upload");

        uploadBtn.setEnabled(AppContext
                .canWrite(RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS));
        navButton.addButton(uploadBtn);

        Button downloadBtn = new Button("Download");

        LazyStreamSource streamSource = new LazyStreamSource() {
            private static final long serialVersionUID = 1L;

            @Override
            protected StreamSource buildStreamSource() {
                Collection<Resource> selectedResources = getSelectedResources();
                return StreamDownloadResourceUtil
                        .getStreamSourceSupportExtDrive(selectedResources);
            }

            @Override
            public String getFilename() {
                Collection<Resource> selectedResources = getSelectedResources();
                return StreamDownloadResourceUtil
                        .getDownloadFileName(selectedResources);
            }
        };
        OnDemandFileDownloader downloaderExt = new OnDemandFileDownloader(
                streamSource);
        downloaderExt.extend(downloadBtn);

        downloadBtn.setIcon(FontAwesome.DOWNLOAD);
        downloadBtn.addStyleName(UIConstants.THEME_BROWN_LINK);
        downloadBtn.setDescription("Download");
        downloadBtn.setEnabled(AppContext
                .canRead(RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS));
        navButton.addButton(downloadBtn);

        Button moveToBtn = new Button("Move", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                Collection<Resource> selectedResources = getSelectedResources();
                if (CollectionUtils.isNotEmpty(selectedResources)) {
                    MoveResourceWindow moveResourceWindow = new MoveResourceWindow(
                            selectedResources);
                    UI.getCurrent().addWindow(moveResourceWindow);
                } else {
                    NotificationUtil
                            .showWarningNotification("Please select at least one item to move");
                }
            }
        });
        moveToBtn.setIcon(FontAwesome.ARROWS);
        moveToBtn.addStyleName(UIConstants.THEME_BROWN_LINK);
        moveToBtn.setEnabled(AppContext
                .canWrite(RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS));
        moveToBtn.setDescription("Move to");
        navButton.addButton(moveToBtn);

        Button deleteBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_DELETE),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        Collection<Resource> selectedResources = getSelectedResources();
                        if (CollectionUtils.isEmpty(selectedResources)) {
                            NotificationUtil
                                    .showWarningNotification("Please select at least one item to delete");
                        } else {
                            deleteResourceAction();
                        }
                    }
                });
        deleteBtn.setIcon(FontAwesome.TRASH_O);
        deleteBtn.addStyleName(UIConstants.THEME_RED_LINK);
        deleteBtn.setDescription("Delete resource");
        deleteBtn.setEnabled(AppContext
                .canAccess(RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS));

        navButton.addButton(deleteBtn);
        groupBtns.addComponent(navButton);

        mainBodyLayout.addComponent(groupBtns);

        resourcesContainer = new ResourcesContainer();

        mainBodyLayout.addComponent(resourcesContainer);
        this.addComponent(mainBodyLayout);
    }

    /**
     * this method show Component when start loading
     *
     * @param baseFolder
     */
    public void displayComponent(Folder baseFolder,
                                 String rootFolderName) {
        this.baseFolder = baseFolder;
        this.rootPath = baseFolder.getPath();
        this.rootFolderName = rootFolderName;
        this.fileBreadCrumb.initBreadcrumb();
        this.resourcesContainer.constructBody(this.baseFolder);
    }

    private void deleteResourceAction() {
        ConfirmDialogExt.show(UI.getCurrent(), AppContext.getMessage(
                        GenericI18Enum.DIALOG_DELETE_TITLE,
                        SiteConfiguration.getSiteName()), AppContext
                        .getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                AppContext.getMessage(GenericI18Enum.BUTTON_YES), AppContext
                        .getMessage(GenericI18Enum.BUTTON_NO),
                new ConfirmDialog.Listener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClose(final ConfirmDialog dialog) {
                        Collection<Resource> selectedResources = getSelectedResources();
                        if (CollectionUtils.isNotEmpty(selectedResources)) {
                            if (dialog.isConfirmed()) {
                                for (Resource res : selectedResources) {
                                    if (res.isExternalResource()) {
                                        externalResourceService.deleteResource(
                                                ((ExternalFolder) res)
                                                        .getExternalDrive(),
                                                res.getPath());
                                    } else {
                                        if (res instanceof Folder) {
                                            fireEvent(new ResourceRemovedEvent(
                                                    ResourcesDisplayComponent.this,
                                                    res));
                                        }
                                        resourceService.removeResource(
                                                res.getPath(),
                                                AppContext.getUsername(),
                                                AppContext.getAccountId());
                                    }
                                }

                                resourcesContainer.constructBody(baseFolder);

                                NotificationUtil
                                        .showNotification("Delete content successfully.");
                            }
                        }
                    }
                });
    }

    public void constructBodyItemContainer(Folder folder) {
        this.baseFolder = folder;
        fileBreadCrumb.gotoFolder(folder);
        resourcesContainer.constructBody(folder);
    }

    public void addSearchHandlerToBreadCrumb(final SearchHandler<FileSearchCriteria> handler) {
        fileBreadCrumb.addSearchHandler(handler);
    }

    public Collection<Resource> getSelectedResources() {
        return resourcesContainer.getSelectedResourceCollection();
    }

    private class ResourcesContainer extends MVerticalLayout {
        private static final long serialVersionUID = 1L;

        private final List<CheckBox> checkboxes;
        private List<Resource> resources;

        public ResourcesContainer() {
            withMargin(false).withWidth("100%");
            checkboxes = new ArrayList<>();
        }

        private void setAllValues(boolean value) {
            for (CheckBox checkbox : checkboxes) {
                checkbox.setValue(value);
            }
        }

        private void constructBody(Folder currentFolder) {
            this.removeAllComponents();
            this.addComponent(new Hr());

            if (currentFolder instanceof ExternalFolder) {
                resources = externalResourceService.getResources(
                        ((ExternalFolder) currentFolder).getExternalDrive(),
                        currentFolder.getPath());
            } else {
                resources = resourceService.getResources(currentFolder
                        .getPath());
            }

            if (currentFolder.getPath().equals(rootPath)) {
                List<ExternalDrive> externalDrives = externalDriveService
                        .getExternalDrivesOfUser(AppContext.getUsername());
                if (CollectionUtils.isNotEmpty(externalDrives)) {
                    for (ExternalDrive drive : externalDrives) {
                        if (StorageNames.DROPBOX.equals(drive.getStoragename())) {
                            try {
                                Resource res = externalResourceService
                                        .getCurrentResourceByPath(drive, "/");
                                res.setName(drive.getFoldername());
                                resources.add(0, res);
                            } catch (Exception e) {
                                LOG.error("Error while query renameResource", e);
                            }
                        } else {
                            throw new MyCollabException(
                                    "Do not support any external drive different than Dropbox");
                        }
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(resources)) {
                if (resources.size() <= ResourcePagingNavigator.pageItemNum) {
                    for (Resource res : resources) {
                        ComponentContainer resContainer = buildResourceRowComp(res);
                        if (resContainer != null) {
                            this.addComponent(buildResourceRowComp(res));
                            this.addComponent(new Hr());
                        }
                    }
                } else if (resources.size() > ResourcePagingNavigator.pageItemNum) {
                    for (int i = 0; i < ResourcePagingNavigator.pageItemNum; i++) {
                        Resource res = resources.get(i);
                        ComponentContainer resContainer = buildResourceRowComp(res);
                        if (resContainer != null) {
                            this.addComponent(buildResourceRowComp(res));
                            this.addComponent(new Hr());
                        }
                    }
                    pagingResourceWapper = new ResourcePagingNavigator(resources);
                    pagingResourceWapper.setWidth("100%");
                    this.addComponent(pagingResourceWapper);
                    this.setComponentAlignment(pagingResourceWapper,
                            Alignment.MIDDLE_CENTER);
                }
            }
        }

        private HorizontalLayout buildResourceRowComp(final Resource res) {
            if (res.getName().startsWith(".")) {
                return null;
            }
            final MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%").withHeight("44px");
            layout.addStyleName("resourceItem");

            final CheckBox checkbox = new CheckBox();
            checkbox.setWidth("25px");
            checkboxes.add(checkbox);

            checkbox.addValueChangeListener(new ValueChangeListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void valueChange(ValueChangeEvent event) {
                    res.setSelected(checkbox.getValue());
                }
            });
            layout.with(checkbox).withAlign(checkbox, Alignment.MIDDLE_LEFT);

            CssLayout resIconWrapper = new CssLayout();
            final FontIconLabel resourceIcon;
            if (res instanceof Folder)
                resourceIcon = (res instanceof ExternalFolder) ? new FontIconLabel(FontAwesome.DROPBOX) : new
                        FontIconLabel(FontAwesome.FOLDER);
            else {
                resourceIcon = new FontIconLabel(FontAwesome.FILE);
            }
            resourceIcon.addStyleName("icon-38px");
            resIconWrapper.addComponent(resourceIcon);

            layout.addComponent(resIconWrapper);
            layout.setComponentAlignment(resIconWrapper, Alignment.MIDDLE_LEFT);

            MVerticalLayout informationLayout = new MVerticalLayout().withMargin(false);

            Button resourceLinkBtn = new Button(res.getName(),
                    new ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            if (res instanceof Folder) {
                                baseFolder = (Folder) res;
                                resourcesContainer.constructBody((Folder) res);
                                fileBreadCrumb.gotoFolder((Folder) res);
                            } else {
                                FileDownloadWindow fileDownloadWindow = new FileDownloadWindow((Content) res);
                                UI.getCurrent().addWindow(fileDownloadWindow);
                            }
                        }
                    });
            resourceLinkBtn.addStyleName("link");
            resourceLinkBtn.addStyleName("h3");
            informationLayout.addComponent(resourceLinkBtn);

            MHorizontalLayout moreInfoAboutResLayout = new MHorizontalLayout();

            // If renameResource is dropbox renameResource then we can not
            // define the
            // created user so we do not need to display, then we assume the
            // current user is created user
            if (StringUtils.isEmpty(res.getCreatedUser())) {
                UserLink usernameLbl = new UserLink(AppContext.getUsername(), AppContext.getUserAvatarId(),
                        AppContext.getSession().getDisplayName(), false);
                usernameLbl.addStyleName("grayLabel");
                moreInfoAboutResLayout.addComponent(usernameLbl);
            } else {
                UserService userService = ApplicationContextUtil.getSpringBean(UserService.class);
                SimpleUser user = userService.findUserByUserNameInAccount(res.getCreatedUser(), AppContext.getAccountId());
                if (user != null) {
                    UserLink userLink = new UserLink(user.getUsername(), user.getAvatarid(), user.getDisplayName(),
                            false);
                    userLink.addStyleName("grayLabel");
                    moreInfoAboutResLayout.addComponent(userLink);
                } else {
                    Label usernameLbl = new Label(res.getCreatedBy());
                    usernameLbl.addStyleName("grayLabel");
                    moreInfoAboutResLayout.addComponent(usernameLbl);
                }
            }
            moreInfoAboutResLayout.addComponent(new Separator());

            // If renameResource is dropbox renameResource then we can not
            // define the
            // created date so we do not need to display\
            if (res.getCreated() != null) {
                Label createdTimeLbl = new Label((AppContext.formatPrettyTime(res
                        .getCreated().getTime())));
                createdTimeLbl.setDescription(AppContext.formatDateTime(res.getCreated().getTime()));
                createdTimeLbl.addStyleName("grayLabel");
                moreInfoAboutResLayout.addComponent(createdTimeLbl);
            } else {
                Label createdTimeLbl = new Label("Undefined");
                createdTimeLbl.addStyleName("grayLabel");
                moreInfoAboutResLayout.addComponent(createdTimeLbl);
            }

            if (res instanceof Content) {
                moreInfoAboutResLayout.addComponent(new Separator());
                Label lbl = new Label(ResourceUtils.getVolumeDisplay(res.getSize()));
                lbl.addStyleName("grayLabel");
                moreInfoAboutResLayout.addComponent(lbl);
            }
            informationLayout.addComponent(moreInfoAboutResLayout);

            layout.with(informationLayout).withAlign(informationLayout, Alignment.MIDDLE_LEFT).expand(informationLayout);

            final PopupButton resourceSettingPopupBtn = new PopupButton();

            final MVerticalLayout filterBtnLayout = new MVerticalLayout();

            final Button renameBtn = new Button("Rename",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            resourceSettingPopupBtn.setPopupVisible(false);
                            final RenameResourceWindow renameWindow = new RenameResourceWindow(
                                    res);
                            UI.getCurrent().addWindow(renameWindow);
                        }
                    });
            renameBtn.addStyleName("link");
            filterBtnLayout.addComponent(renameBtn);

            final Button downloadBtn = new Button("Download");

            LazyStreamSource streamSource = new LazyStreamSource() {
                private static final long serialVersionUID = 1L;

                @Override
                protected StreamSource buildStreamSource() {
                    List<Resource> lstRes = new ArrayList<>();
                    lstRes.add(res);
                    return StreamDownloadResourceUtil
                            .getStreamSourceSupportExtDrive(lstRes);
                }

                @Override
                public String getFilename() {
                    return res.getName();
                }
            };

            OnDemandFileDownloader downloaderExt = new OnDemandFileDownloader(
                    streamSource);
            downloaderExt.extend(downloadBtn);

            downloadBtn.addStyleName("link");
            filterBtnLayout.addComponent(downloadBtn);

            final Button moveBtn = new Button("Move to",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            MoveResourceWindow moveResourceWindow = new MoveResourceWindow(res);
                            UI.getCurrent().addWindow(moveResourceWindow);
                        }
                    });
            moveBtn.addStyleName("link");
            filterBtnLayout.addComponent(moveBtn);

            final Button deleteBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_DELETE),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {

                            ResourcesDisplayComponent.this
                                    .deleteResourceAction();
                        }
                    });
            deleteBtn.addStyleName("link");
            filterBtnLayout.addComponent(deleteBtn);

            filterBtnLayout.setWidth("100px");
            resourceSettingPopupBtn.setIcon(FontAwesome.COG);
            resourceSettingPopupBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
            resourceSettingPopupBtn.setContent(filterBtnLayout);

            layout.addComponent(resourceSettingPopupBtn);
            layout.setComponentAlignment(resourceSettingPopupBtn,
                    Alignment.MIDDLE_RIGHT);
            return layout;
        }

        Collection<Resource> getSelectedResourceCollection() {
            if (CollectionUtils.isNotEmpty(resources)) {
                Collection<Resource> selectedResources = Collections2.filter(
                        resources, new Predicate<Resource>() {
                            @Override
                            public boolean apply(Resource input) {
                                return (input.isSelected() == true);
                            }
                        });
                return selectedResources;
            } else {
                return new ArrayList<>();
            }

        }
    }

    private class RenameResourceWindow extends Window {
        private static final long serialVersionUID = 1L;
        private final Resource renameResource;

        public RenameResourceWindow(final Resource resource) {
            super("Rename folder/file");
            this.center();
            this.setResizable(false);
            this.setWidth("400px");
            this.renameResource = resource;
            this.constructBody();
        }

        private void constructBody() {
            final MVerticalLayout layout = new MVerticalLayout()
                    .withMargin(new MarginInfo(false, true, true, true));

            final MHorizontalLayout topRename = new MHorizontalLayout().withMargin(true);

            final Label label = new Label("Enter new name: ");

            final TextField newName = new TextField();
            newName.setWidth("150px");
            topRename.with(label, newName).alignAll(Alignment.MIDDLE_CENTER);

            layout.with(topRename)
                    .withAlign(topRename, Alignment.MIDDLE_CENTER);

            final MHorizontalLayout controlButtons = new MHorizontalLayout();
            final Button saveBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                    new ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            final String oldPath = renameResource.getPath();
                            String parentOldPath = oldPath.substring(0,
                                    oldPath.lastIndexOf("/") + 1);

                            String newNameValue = newName.getValue();
                            String newPath = parentOldPath + newNameValue;

                            if (renameResource.isExternalResource()) {
                                externalResourceService.rename(
                                        ((ExternalFolder) renameResource)
                                                .getExternalDrive(), oldPath,
                                        newPath);
                            } else {
                                resourceService.rename(oldPath, newPath,
                                        AppContext.getUsername());
                            }
                            resourcesContainer.constructBody(baseFolder);

                            RenameResourceWindow.this.close();
                        }
                    });
            saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

            final Button cancelBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                    new ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            RenameResourceWindow.this.close();
                        }
                    });
            cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
            controlButtons.with(saveBtn, cancelBtn).alignAll(
                    Alignment.MIDDLE_CENTER);
            layout.with(controlButtons).withAlign(controlButtons,
                    Alignment.MIDDLE_CENTER);

            this.setContent(layout);
        }
    }

    private class AddNewFolderWindow extends Window {
        private static final long serialVersionUID = 1L;

        private final TextField folderName;

        public AddNewFolderWindow() {
            this.setModal(true);
            this.setCaption("New Folder");
            this.setResizable(false);
            this.center();

            VerticalLayout contentLayout = new VerticalLayout();
            contentLayout.setMargin(true);
            this.setContent(contentLayout);

            final MHorizontalLayout fileLayout = new MHorizontalLayout();
            fileLayout.setSizeUndefined();
            final Label captionLbl = new Label("Enter folder name: ");
            fileLayout.with(captionLbl).withAlign(captionLbl, Alignment.MIDDLE_LEFT);

            this.folderName = new TextField();
            fileLayout.addComponent(this.folderName);
            fileLayout.setComponentAlignment(this.folderName,
                    Alignment.MIDDLE_LEFT);
            fileLayout.setExpandRatio(this.folderName, 1.0f);

            contentLayout.addComponent(fileLayout);
            contentLayout.setComponentAlignment(fileLayout,
                    Alignment.MIDDLE_CENTER);

            final MHorizontalLayout controlsLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false));

            final Button saveBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {

                            final String folderVal = AddNewFolderWindow.this.folderName
                                    .getValue();

                            if (StringUtils.isNotBlank(folderVal)) {
                                Pattern pattern = Pattern
                                        .compile(illegalFileNamePattern);
                                Matcher matcher = pattern.matcher(folderVal);
                                if (matcher.find()) {
                                    NotificationUtil
                                            .showWarningNotification("Please enter valid folder name except any follow characters : <>:&/\\|?*&");
                                    return;
                                }

                                String baseFolderPath = baseFolder.getPath();

                                if (baseFolder instanceof ExternalFolder) {
                                    String path = baseFolder.getPath() + "/"
                                            + folderVal;
                                    externalResourceService.createFolder(
                                            ((ExternalFolder) baseFolder)
                                                    .getExternalDrive(), path);
                                } else {
                                    resourceService.createNewFolder(
                                            baseFolderPath, folderVal,
                                            AppContext.getUsername());
                                }
                                resourcesContainer.constructBody(baseFolder);
                                AddNewFolderWindow.this.close();
                            }
                        }
                    });
            saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
            saveBtn.setIcon(FontAwesome.SAVE);
            controlsLayout.addComponent(saveBtn);

            final Button cancelBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            AddNewFolderWindow.this.close();
                        }
                    });
            cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
            controlsLayout.addComponent(cancelBtn);
            controlsLayout.setComponentAlignment(cancelBtn,
                    Alignment.MIDDLE_RIGHT);

            contentLayout.addComponent(controlsLayout);
            contentLayout.setComponentAlignment(controlsLayout,
                    Alignment.MIDDLE_CENTER);
        }
    }

    private class MultiUploadContentWindow extends Window {
        private static final long serialVersionUID = 1L;

        private final GridFormLayoutHelper layoutHelper;
        private final MultiFileUploadExt multiFileUploadExt;

        public MultiUploadContentWindow() {
            super("Upload");
            this.setWidth("500px");
            this.setResizable(false);

            VerticalLayout contentLayout = new VerticalLayout();
            contentLayout.setMargin(new MarginInfo(false, false, true, false));
            this.setContent(contentLayout);
            this.setModal(true);
            final AttachmentPanel attachments = new AttachmentPanel();

            this.layoutHelper = new GridFormLayoutHelper(1, 2, "100%", "167px",
                    Alignment.TOP_LEFT);

            multiFileUploadExt = new MultiFileUploadExt(attachments);
            multiFileUploadExt.addComponent(attachments);
            multiFileUploadExt.setWidth("100%");

            this.layoutHelper.addComponent(multiFileUploadExt, "File", 0, 0);

            this.layoutHelper.getLayout().setWidth("100%");
            this.layoutHelper.getLayout().setMargin(false);
            this.layoutHelper.getLayout().addStyleName("colored-gridlayout");
            contentLayout.addComponent(this.layoutHelper.getLayout());

            final MHorizontalLayout controlsLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false));

            final Button uploadBtn = new Button("Upload",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            List<File> lstFileAttachments = attachments
                                    .getListFile();
                            if (lstFileAttachments != null
                                    && lstFileAttachments.size() > 0) {
                                for (File file : lstFileAttachments) {
                                    try {
                                        if (StringUtils.isNotBlank(file
                                                .getName())) {
                                            Pattern pattern = Pattern
                                                    .compile(illegalFileNamePattern);
                                            Matcher matcher = pattern
                                                    .matcher(file.getName());
                                            if (matcher.find()) {
                                                NotificationUtil
                                                        .showWarningNotification("Please upload valid file-name except any follow characters : <>:&/\\|?*&");
                                                return;
                                            }
                                        }
                                        final Content content = new Content(
                                                baseFolder.getPath() + "/"
                                                        + file.getName());
                                        content.setSize(file.length());
                                        FileInputStream fileInputStream = new FileInputStream(
                                                file);

                                        if (baseFolder instanceof ExternalFolder) {
                                            externalResourceService
                                                    .saveContent(
                                                            ((ExternalFolder) baseFolder)
                                                                    .getExternalDrive(),
                                                            content,
                                                            fileInputStream);
                                        } else
                                            resourceService.saveContent(
                                                    content,
                                                    AppContext.getUsername(),
                                                    fileInputStream,
                                                    AppContext.getAccountId());
                                    } catch (IOException e) {
                                        throw new MyCollabException(e);
                                    }
                                }
                                resourcesContainer.constructBody(baseFolder);
                                MultiUploadContentWindow.this.close();
                                NotificationUtil.showNotification("Upload successfully.");
                            } else {
                                NotificationUtil
                                        .showNotification("It seems you did not attach file yet!");
                            }
                        }
                    });
            uploadBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
            controlsLayout.addComponent(uploadBtn);

            final Button cancelBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            MultiUploadContentWindow.this.close();
                        }
                    });
            cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
            controlsLayout.addComponent(cancelBtn);
            controlsLayout.setComponentAlignment(cancelBtn,
                    Alignment.MIDDLE_RIGHT);

            contentLayout.addComponent(controlsLayout);
            contentLayout.setComponentAlignment(controlsLayout,
                    Alignment.MIDDLE_CENTER);
        }

    }

    private class ResourcePagingNavigator extends CssLayout {
        private static final long serialVersionUID = 1L;
        private final int totalItem;
        public static final int pageItemNum = 15;
        private int currentPage;
        private final CssLayout controlBarWrapper;
        private final HorizontalLayout pageManagement;
        private final int totalPage;
        private final List<Resource> lstResource;
        private Button currentBtn;
        private final HorizontalLayout controlBar;

        public ResourcePagingNavigator(List<Resource> lstResource) {
            this.totalItem = lstResource.size();
            this.currentPage = 1;
            this.totalPage = (totalItem / pageItemNum) + 1;
            this.lstResource = lstResource;

            // defined layout here ---------------------------
            this.controlBarWrapper = new CssLayout();
            this.controlBarWrapper.setStyleName("listControl");
            this.controlBarWrapper.setWidth("100%");

            controlBar = new HorizontalLayout();
            controlBar.setWidth("100%");
            this.controlBarWrapper.addComponent(controlBar);

            this.pageManagement = new HorizontalLayout();
            createPageControls();
        }

        private void createPageControls() {
            this.pageManagement.removeAllComponents();
            if (this.currentPage > 1) {
                final Button firstLink = new ButtonLink("1",
                        new ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(final ClickEvent event) {
                                pageChange(1);
                            }
                        }, false);
                firstLink.addStyleName("buttonPaging");
                this.pageManagement.addComponent(firstLink);
            }
            if (this.currentPage >= 5) {
                final Label ss1 = new Label("...");
                ss1.addStyleName("buttonPaging");
                this.pageManagement.addComponent(ss1);
            }
            if (this.currentPage > 3) {
                final Button previous2 = new ButtonLink(""
                        + (this.currentPage - 2), new ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        pageChange(currentPage - 2);
                    }
                }, false);
                previous2.addStyleName("buttonPaging");
                this.pageManagement.addComponent(previous2);
            }
            if (this.currentPage > 2) {
                final Button previous1 = new ButtonLink(""
                        + (this.currentPage - 1), new ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        pageChange(currentPage - 1);
                    }
                }, false);
                previous1.addStyleName("buttonPaging");
                this.pageManagement.addComponent(previous1);
            }
            // Here add current ButtonLink
            currentBtn = new ButtonLink("" + this.currentPage,
                    new ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            // pageChange(currentPage);
                        }
                    }, false);
            currentBtn.addStyleName("buttonPaging");
            currentBtn.addStyleName("buttonPagingcurrent");

            this.pageManagement.addComponent(currentBtn);
            final int range = this.totalPage - this.currentPage;
            if (range >= 1) {
                final Button next1 = new ButtonLink(
                        "" + (this.currentPage + 1), new ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        pageChange(currentPage + 1);
                    }
                }, false);
                next1.addStyleName("buttonPaging");
                this.pageManagement.addComponent(next1);
            }
            if (range >= 2) {
                final Button next2 = new ButtonLink(
                        "" + (this.currentPage + 2), new ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        pageChange(currentPage + 2);
                    }
                }, false);
                next2.addStyleName("buttonPaging");
                this.pageManagement.addComponent(next2);
            }
            if (range >= 4) {
                final Label ss2 = new Label("...");
                ss2.addStyleName("buttonPaging");
                this.pageManagement.addComponent(ss2);
            }
            if (range >= 3) {
                final Button last = new ButtonLink("" + this.totalPage,
                        new ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(final ClickEvent event) {
                                pageChange(totalPage);
                            }
                        }, false);
                last.addStyleName("buttonPaging");
                this.pageManagement.addComponent(last);
            }

            this.pageManagement.setWidth(null);
            this.pageManagement.setSpacing(true);
            controlBar.addComponent(this.pageManagement);
            controlBar.setComponentAlignment(this.pageManagement,
                    Alignment.MIDDLE_RIGHT);
            this.addComponent(controlBarWrapper);
        }

        public void pageChange(int currentPage) {
            this.currentPage = currentPage;
            resourcesContainer.removeAllComponents();
            int index = currentPage - 1;
            int start = (index == 0) ? index : index * pageItemNum;
            int end = ((start + pageItemNum) > lstResource.size()) ? lstResource
                    .size() : start + pageItemNum;

            for (int i = start; i < end; i++) {
                Resource res = lstResource.get(i);
                ComponentContainer resContainer = resourcesContainer
                        .buildResourceRowComp(res);
                if (resContainer != null) {
                    resourcesContainer.addComponent(resContainer);
                    resourcesContainer.addComponent(new Hr());
                }
            }
            createPageControls();
            resourcesContainer.addComponent(this);
        }

        public int getCurrentPage() {
            return currentPage;
        }
    }

    private class MoveResourceWindow extends AbstractResourceMovingWindow {
        private static final long serialVersionUID = 1L;

        public MoveResourceWindow(Resource resource) {
            super(resource);
        }

        public MoveResourceWindow(Collection<Resource> lstResource) {
            super(lstResource);
        }

        @Override
        public void displayAfterMoveSuccess(Folder folder, boolean checking) {
            fileBreadCrumb.gotoFolder(folder);
            resourcesContainer.constructBody(folder);
            if (!checking) {
                NotificationUtil
                        .showNotification("Moved asset(s) successfully.");
            } else {
                NotificationUtil
                        .showNotification("Moving assets is finished, some items can't move to destination. Please " +
                                "check duplicated file-name and try again.");
            }
        }

        @Override
        protected void displayFiles() {
            this.folderTree.removeAllItems();

            this.baseFolder = new Folder(rootPath);
            this.folderTree.addItem(new Object[]{
                            ResourcesDisplayComponent.this.rootFolderName, ""},
                    this.baseFolder);
            this.folderTree.setItemCaption(this.baseFolder,
                    ResourcesDisplayComponent.this.rootFolderName);

            this.folderTree.setCollapsed(this.baseFolder, false);
        }
    }

    public void addResourceRemovedListener(ResourceRemovedListener listener) {
        this.addListener(ResourceRemovedEvent.VIEW_IDENTIFIER,
                ResourceRemovedEvent.class, listener,
                ResourceRemovedListener.viewInitMethod);
    }

    public static interface ResourceRemovedListener extends EventListener,
            Serializable {
        public static final Method viewInitMethod = ReflectTools.findMethod(
                ResourceRemovedListener.class, "removedResource",
                ResourceRemovedEvent.class);

        void removedResource(ResourceRemovedEvent event);
    }

    public static class ResourceRemovedEvent extends ApplicationEvent {
        private static final long serialVersionUID = 1L;

        public static final String VIEW_IDENTIFIER = "resourceRemoved";

        public ResourceRemovedEvent(Object source, Resource data) {
            super(source, data);
        }
    }

}
