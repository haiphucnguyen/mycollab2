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
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.*;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.events.FileEvent;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.resources.LazyStreamSource;
import com.esofthead.mycollab.vaadin.resources.OnDemandFileDownloader;
import com.esofthead.mycollab.vaadin.resources.StreamDownloadResourceUtil;
import com.esofthead.mycollab.vaadin.resources.file.FileAssetsUtil;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.web.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.UserLink;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.esofthead.vaadin.floatingcomponent.FloatingComponent;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.MultiFileUploadExt;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ResourcesDisplayComponent extends MVerticalLayout {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ResourcesDisplayComponent.class);

    private ResourceService resourceService;
    private ExternalResourceService externalResourceService;
    private ExternalDriveService externalDriveService;

    private FileBreadcrumb fileBreadCrumb;
    private ResourcesContainer resourcesContainer;

    private Folder baseFolder;
    private String rootFolderName;
    private String rootPath;

    public ResourcesDisplayComponent(final Folder rootFolder) {
        this.baseFolder = rootFolder;
        this.rootPath = rootFolder.getPath();
        externalResourceService = ApplicationContextUtil.getSpringBean(ExternalResourceService.class);
        externalDriveService = ApplicationContextUtil.getSpringBean(ExternalDriveService.class);
        resourceService = ApplicationContextUtil.getSpringBean(ResourceService.class);

        this.withMargin(new MarginInfo(true, false, true, false));
        fileBreadCrumb = new FileBreadcrumb(rootPath);
        fileBreadCrumb.addSearchHandler(new SearchHandler<FileSearchCriteria>() {
            @Override
            public void onSearch(FileSearchCriteria criteria) {
                Folder selectedFolder = (Folder) resourceService.getResource(criteria.getBaseFolder());
                constructBodyItemContainer(selectedFolder);
                rootPath = selectedFolder.getPath();
            }
        });
        ELabel headerLbl = ELabel.h2(ProjectAssetsManager.getAsset(ProjectTypeConstants.FILE).getHtml() + " Files");

        // Construct controllGroupBtn
        Button createBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CREATE), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                AddNewFolderWindow addnewFolderWindow = new AddNewFolderWindow();
                UI.getCurrent().addWindow(addnewFolderWindow);
            }
        });
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.addStyleName(UIConstants.BUTTON_ACTION);
        createBtn.setWidth("90px");
        createBtn.setDescription("Create new folder");
        createBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS));

        Button uploadBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_UPLOAD), new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                MultiUploadContentWindow multiUploadWindow = new MultiUploadContentWindow();
                UI.getCurrent().addWindow(multiUploadWindow);
            }
        });
        uploadBtn.setWidth("90px");
        uploadBtn.setIcon(FontAwesome.UPLOAD);
        uploadBtn.addStyleName(UIConstants.BUTTON_ACTION);
        uploadBtn.setDescription("Upload");
        uploadBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.PUBLIC_DOCUMENT_ACCESS));

        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, new MHorizontalLayout(createBtn, uploadBtn)).expand(headerLbl);
        resourcesContainer = new ResourcesContainer();
        this.with(headerLayout, fileBreadCrumb, resourcesContainer);
        displayComponent(rootFolder, "Documents");
    }

    /**
     * this method show Component when start loading
     *
     * @param baseFolder
     */
    public void displayComponent(Folder baseFolder, String rootFolderName) {
        this.baseFolder = baseFolder;
        this.rootPath = baseFolder.getPath();
        this.rootFolderName = rootFolderName;
        this.fileBreadCrumb.initBreadcrumb();
        this.resourcesContainer.constructBody(this.baseFolder);
    }

    private void deleteResourceAction(final Collection<Resource> deletedResources) {
        ConfirmDialogExt.show(UI.getCurrent(), AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, AppContext.getSiteName()),
                AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                new ConfirmDialog.Listener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClose(final ConfirmDialog dialog) {
                        if (dialog.isConfirmed()) {
                            for (Resource res : deletedResources) {
                                if (res.isExternalResource()) {
                                    externalResourceService.deleteResource(
                                            ((ExternalFolder) res).getExternalDrive(), res.getPath());
                                } else {
                                    if (res instanceof Folder) {
                                        EventBusFactory.getInstance().post(new FileEvent.ResourceRemovedEvent
                                                (ResourcesDisplayComponent.this, res));
                                    }
                                    resourceService.removeResource(res.getPath(), AppContext.getUsername(),
                                            AppContext.getAccountId());
                                }
                            }

                            resourcesContainer.constructBody(baseFolder);
                            NotificationUtil.showNotification("Congrats", "Deleted content successfully.");
                        }
                    }
                });
    }

    public void constructBodyItemContainer(Folder folder) {
        this.baseFolder = folder;
        fileBreadCrumb.gotoFolder(folder);
        resourcesContainer.constructBody(folder);
    }

    public Collection<Resource> getSelectedResources() {
        return resourcesContainer.getSelectedResourceCollection();
    }

    private class ResourcesContainer extends MHorizontalLayout {

        private Resource selectedResource;
        private MVerticalLayout bodyContainer;
        private MVerticalLayout selectedResourceControlLayout;
        private List<Resource> resources;

        public ResourcesContainer() {
            withSpacing(true).withWidth("100%");
        }

        private void constructBody(Folder currentFolder) {
            this.removeAllComponents();

            bodyContainer = new MVerticalLayout().withSpacing(false).withMargin(false).withStyleName("border-top");
            selectedResourceControlLayout = new MVerticalLayout().withMargin(false).withWidth("400px");

            FloatingComponent floatingComponent = FloatingComponent.floatThis(selectedResourceControlLayout);
            floatingComponent.setContainerId("main-body");
            with(bodyContainer, selectedResourceControlLayout).expand(bodyContainer);
            if (currentFolder instanceof ExternalFolder) {
                resources = externalResourceService.getResources(((ExternalFolder) currentFolder).getExternalDrive(),
                        currentFolder.getPath());
            } else {
                resources = resourceService.getResources(currentFolder.getPath());
            }

            if (currentFolder.getPath().equals(rootPath)) {
                List<ExternalDrive> externalDrives = externalDriveService.getExternalDrivesOfUser(AppContext.getUsername());
                if (CollectionUtils.isNotEmpty(externalDrives)) {
                    for (ExternalDrive drive : externalDrives) {
                        if (StorageNames.DROPBOX.equals(drive.getStoragename())) {
                            try {
                                Resource res = externalResourceService.getCurrentResourceByPath(drive, "/");
                                res.setName(drive.getFoldername());
                                resources.add(0, res);
                            } catch (Exception e) {
                                LOG.error("Error while query renameResource", e);
                            }
                        } else {
                            throw new MyCollabException("Do not support any external drive different than Dropbox");
                        }
                    }
                }
            }

            if (CollectionUtils.isNotEmpty(resources)) {
                for (Resource res : resources) {
                    ComponentContainer resContainer = buildResourceRowComp(res);
                    if (resContainer != null) {
                        bodyContainer.addComponent(buildResourceRowComp(res));
                    }
                }
            }
        }

        private void displaySelectedResourceControls() {
            if (selectedResource != null) {
                selectedResourceControlLayout.removeAllComponents();
                selectedResourceControlLayout.addStyleName(UIConstants.BOX);
                ELabel resourceHeaderLbl = ELabel.h3(selectedResource.getName()).withStyleName(UIConstants.TEXT_ELLIPSIS);
                MHorizontalLayout headerLayout = new MHorizontalLayout(resourceHeaderLbl).withMargin(new MarginInfo
                        (false, true, false, true)).withStyleName("panel-header").withFullWidth().alignAll(Alignment.MIDDLE_LEFT);
                selectedResourceControlLayout.with(headerLayout);

                Button renameBtn = new Button("Rename", new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        UI.getCurrent().addWindow(new RenameResourceWindow(selectedResource));
                    }
                });
                renameBtn.addStyleName(UIConstants.BUTTON_LINK);
                renameBtn.setIcon(FontAwesome.EDIT);

                Button downloadBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_DOWNLOAD));

                LazyStreamSource streamSource = new LazyStreamSource() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected StreamSource buildStreamSource() {
                        List<Resource> lstRes = new ArrayList<>();
                        lstRes.add(selectedResource);
                        return StreamDownloadResourceUtil.getStreamSourceSupportExtDrive(lstRes);
                    }

                    @Override
                    public String getFilename() {
                        return selectedResource.getName();
                    }
                };

                OnDemandFileDownloader downloaderExt = new OnDemandFileDownloader(streamSource);
                downloaderExt.extend(downloadBtn);

                downloadBtn.addStyleName(UIConstants.BUTTON_LINK);
                downloadBtn.setIcon(FontAwesome.DOWNLOAD);

                Button moveBtn = new Button("Move to", new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        UI.getCurrent().addWindow(new MoveResourceWindow(selectedResource));
                    }
                });
                moveBtn.addStyleName(UIConstants.BUTTON_LINK);
                moveBtn.setIcon(FontAwesome.ARROWS);

                Button deleteBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_DELETE), new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        deleteResourceAction(Collections.singletonList(selectedResource));
                    }
                });
                deleteBtn.addStyleName(UIConstants.BUTTON_LINK);
                deleteBtn.setIcon(FontAwesome.TRASH_O);
                selectedResourceControlLayout.with(new MVerticalLayout(renameBtn, downloadBtn, moveBtn, deleteBtn));
            } else {
                selectedResourceControlLayout.removeAllComponents();
                selectedResourceControlLayout.removeStyleName(UIConstants.BOX);
            }
        }

        private HorizontalLayout buildResourceRowComp(final Resource resource) {
            if (resource.getName().startsWith(".")) {
                return null;
            }
            final MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%").withStyleName
                    (UIConstants.HOVER_EFFECT_NOT_BOX, "border-bottom");
            layout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                    selectedResource = resource;
                    displaySelectedResourceControls();
                    for (int i = 0; i < bodyContainer.getComponentCount(); i++) {
                        bodyContainer.getComponent(i).removeStyleName("selected");
                    }
                    layout.addStyleName("selected");
                }
            });

            CssLayout resIconWrapper = new CssLayout();
            Component resourceIcon = null;
            if (resource instanceof Folder) {
                resourceIcon = (resource instanceof ExternalFolder) ? ELabel.fontIcon(FontAwesome.DROPBOX) : ELabel.fontIcon(FontAwesome.FOLDER);
                resourceIcon.addStyleName("icon-38px");
            } else if (resource instanceof Content) {
                Content content = (Content) resource;
                if (StringUtils.isNotBlank(content.getThumbnail())) {
                    resourceIcon = new Embedded(null, new ExternalResource(StorageFactory.getInstance().getResourcePath(content.getThumbnail())));
                    resourceIcon.setWidth("38px");
                    resourceIcon.setHeight("38px");
                } else {
                    if (content instanceof ExternalContent) {
                        final byte[] thumbnailBytes = ((ExternalContent) content).getThumbnailBytes();
                        if (thumbnailBytes != null) {
                            resourceIcon = new Embedded(null, new StreamResource(new StreamSource() {
                                @Override
                                public InputStream getStream() {
                                    return new ByteArrayInputStream(thumbnailBytes);
                                }
                            }, String.format("thumbnail%s.%s", content.getPath(), "png")));
                            resourceIcon.setWidth("38px");
                            resourceIcon.setHeight("38px");
                        }
                    }
                }
            } else {
                throw new MyCollabException("Do not support resource file " + resource.getClass());
            }
            if (resourceIcon == null) {
                resourceIcon = ELabel.fontIcon(FileAssetsUtil.getFileIconResource(resource.getName()));
                resourceIcon.addStyleName("icon-38px");
            }
            resIconWrapper.addComponent(resourceIcon);

            layout.addComponent(resIconWrapper);
            layout.setComponentAlignment(resIconWrapper, Alignment.MIDDLE_LEFT);

            MVerticalLayout informationLayout = new MVerticalLayout().withSpacing(false).withMargin(false);

            Button resourceLinkBtn = new Button(resource.getName(), new ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    if (resource instanceof Folder) {
                        baseFolder = (Folder) resource;
                        resourcesContainer.constructBody((Folder) resource);
                        fileBreadCrumb.gotoFolder((Folder) resource);
                    } else {
                        FileDownloadWindow fileDownloadWindow = new FileDownloadWindow((Content) resource);
                        UI.getCurrent().addWindow(fileDownloadWindow);
                    }
                }
            });
            resourceLinkBtn.addStyleName(UIConstants.BUTTON_LINK);
            informationLayout.addComponent(resourceLinkBtn);

            MHorizontalLayout moreInfoAboutResLayout = new MHorizontalLayout();

            // If renameResource is dropbox renameResource then we can not
            // define the created date so we do not need to display\
            if (resource.getCreated() != null) {
                ELabel createdTimeLbl = new ELabel(FontAwesome.CLOCK_O.getHtml() + " " + AppContext.formatPrettyTime
                        (resource.getCreated().getTime()), ContentMode.HTML)
                        .withDescription(AppContext.formatDateTime(resource.getCreated().getTime()))
                        .withStyleName(UIConstants.LABEL_META_INFO);
                moreInfoAboutResLayout.addComponent(createdTimeLbl);
            }

            if (resource instanceof Content) {
                ELabel lbl = new ELabel(FontAwesome.ARCHIVE.getHtml() + " " + FileUtils.getVolumeDisplay(resource
                        .getSize()), ContentMode.HTML)
                        .withStyleName(UIConstants.LABEL_META_INFO);
                moreInfoAboutResLayout.addComponent(lbl);
            }
            informationLayout.addComponent(moreInfoAboutResLayout);

            // If renameResource is dropbox renameResource then we can not
            // define the
            // created user so we do not need to display, then we assume the
            // current user is created user
            if (StringUtils.isEmpty(resource.getCreatedUser())) {
                UserLink usernameLbl = new UserLink(AppContext.getUsername(), AppContext.getUserAvatarId(),
                        AppContext.getUser().getDisplayName());
                usernameLbl.addStyleName(UIConstants.LABEL_META_INFO);
                moreInfoAboutResLayout.addComponent(usernameLbl);
            } else {
                UserService userService = ApplicationContextUtil.getSpringBean(UserService.class);
                SimpleUser user = userService.findUserByUserNameInAccount(resource.getCreatedUser(), AppContext.getAccountId());
                if (user != null) {
                    UserLink userLink = new UserLink(user.getUsername(), user.getAvatarid(), user.getDisplayName());
                    userLink.addStyleName(UIConstants.LABEL_META_INFO);
                    moreInfoAboutResLayout.addComponent(userLink);
                } else {
                    Label usernameLbl = new Label(resource.getCreatedBy());
                    usernameLbl.addStyleName(UIConstants.LABEL_META_INFO);
                    moreInfoAboutResLayout.addComponent(usernameLbl);
                }
            }

            layout.with(informationLayout).withAlign(informationLayout, Alignment.MIDDLE_LEFT).expand(informationLayout);

            return layout;
        }

        Collection<Resource> getSelectedResourceCollection() {
            if (CollectionUtils.isNotEmpty(resources)) {
                return Collections2.filter(resources, new Predicate<Resource>() {
                    @Override
                    public boolean apply(Resource input) {
                        return input.isSelected();
                    }
                });
            } else {
                return new ArrayList<>();
            }

        }
    }

    private class RenameResourceWindow extends Window {
        private static final long serialVersionUID = 1L;
        private Resource renameResource;

        public RenameResourceWindow(Resource resource) {
            super("Rename folder/file");
            this.center();
            this.setResizable(false);
            this.setModal(true);
            this.setWidth("400px");
            this.renameResource = resource;
            this.constructBody();
        }

        private void constructBody() {
            VerticalLayout contentLayout = new VerticalLayout();
            GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
            final TextField folderName = new TextField();
            folderName.setValue(renameResource.getName());
            layoutHelper.addComponent(folderName, "Folder/File Name", 0, 0);
            contentLayout.addComponent(layoutHelper.getLayout());

            final MHorizontalLayout controlButtons = new MHorizontalLayout().withMargin(true);
            final Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    String oldPath = renameResource.getPath();
                    String parentOldPath = oldPath.substring(0, oldPath.lastIndexOf("/") + 1);

                    String newNameValue = folderName.getValue();
                    String newPath = parentOldPath + newNameValue;

                    if (renameResource.isExternalResource()) {
                        externalResourceService.rename(((ExternalFolder) renameResource).getExternalDrive(), oldPath, newPath);
                    } else {
                        resourceService.rename(oldPath, newPath, AppContext.getUsername());
                    }
                    resourcesContainer.constructBody(baseFolder);

                    close();
                }
            });
            saveBtn.setIcon(FontAwesome.SAVE);
            saveBtn.addStyleName(UIConstants.BUTTON_ACTION);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    RenameResourceWindow.this.close();
                }
            });
            cancelBtn.addStyleName(UIConstants.BUTTON_OPTION);
            controlButtons.with(saveBtn, cancelBtn).alignAll(Alignment.MIDDLE_CENTER);
            contentLayout.addComponent(controlButtons);
            contentLayout.setComponentAlignment(controlButtons, Alignment.MIDDLE_CENTER);

            this.setContent(contentLayout);
        }
    }

    private class AddNewFolderWindow extends Window {
        private static final long serialVersionUID = 1L;

        private TextField folderName;
        private TextArea descriptionArea;

        public AddNewFolderWindow() {
            this.setModal(true);
            this.setResizable(false);
            this.setWidth("500px");
            this.setCaption("New Folder");
            this.center();

            MVerticalLayout contentLayout = new MVerticalLayout().withSpacing(false).withMargin(new MarginInfo(false, false, true, false));
            this.setContent(contentLayout);

            GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
            this.folderName = new TextField();
            layoutHelper.addComponent(folderName, "Folder Name", 0, 0);
            contentLayout.addComponent(layoutHelper.getLayout());
            MHorizontalLayout controlsLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false));
            Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    String folderVal = folderName.getValue();

                    if (StringUtils.isNotBlank(folderVal)) {
                        FileUtils.assertValidFolderName(folderVal);
                        String baseFolderPath = baseFolder.getPath();

                        if (baseFolder instanceof ExternalFolder) {
                            String path = baseFolder.getPath() + "/" + folderVal;
                            externalResourceService.createNewFolder(((ExternalFolder) baseFolder).getExternalDrive(), path);
                        } else {
                            resourceService.createNewFolder(baseFolderPath, folderVal, AppContext.getUsername());
                        }
                        resourcesContainer.constructBody(baseFolder);
                        close();
                    }
                }
            });
            saveBtn.addStyleName(UIConstants.BUTTON_ACTION);
            saveBtn.setIcon(FontAwesome.SAVE);
            controlsLayout.addComponent(saveBtn);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    close();
                }
            });
            cancelBtn.addStyleName(UIConstants.BUTTON_OPTION);
            controlsLayout.addComponent(cancelBtn);
            controlsLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);

            contentLayout.with(controlsLayout).withAlign(controlsLayout, Alignment.MIDDLE_CENTER);
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
            this.setModal(true);
            center();

            VerticalLayout contentLayout = new VerticalLayout();
            contentLayout.setMargin(new MarginInfo(false, false, true, false));
            this.setContent(contentLayout);
            final AttachmentPanel attachmentPanel = new AttachmentPanel();

            this.layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);

            multiFileUploadExt = new MultiFileUploadExt(attachmentPanel);
            multiFileUploadExt.addComponent(attachmentPanel);
            multiFileUploadExt.setWidth("100%");

            this.layoutHelper.addComponent(multiFileUploadExt, "File", 0, 0);
            contentLayout.addComponent(this.layoutHelper.getLayout());

            MHorizontalLayout controlsLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false));

            final Button uploadBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_UPLOAD), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    List<File> attachments = attachmentPanel.files();
                    if (CollectionUtils.isNotEmpty(attachments)) {
                        for (File attachment : attachments) {
                            try {
                                if (!FileUtils.isValidFileName(attachment.getName())) {
                                    NotificationUtil.showWarningNotification("Please upload valid file-name except any follow characters : <>:&/\\|?*&");
                                    return;
                                }
                                Content content = new Content(String.format("%s/%s", baseFolder.getPath(), attachment.getName()));
                                content.setSize(attachment.length());
                                FileInputStream fileInputStream = new FileInputStream(attachment);

                                if (baseFolder instanceof ExternalFolder) {
                                    externalResourceService.saveContent(((ExternalFolder) baseFolder)
                                            .getExternalDrive(), content, fileInputStream);
                                } else
                                    resourceService.saveContent(content, AppContext.getUsername(),
                                            fileInputStream, AppContext.getAccountId());
                            } catch (IOException e) {
                                throw new MyCollabException(e);
                            }
                        }
                        resourcesContainer.constructBody(baseFolder);
                        MultiUploadContentWindow.this.close();
                        NotificationUtil.showNotification("Congrats", "Upload successfully.");
                    } else {
                        NotificationUtil.showWarningNotification("It seems you did not attach file yet!");
                    }
                }
            });
            uploadBtn.addStyleName(UIConstants.BUTTON_ACTION);
            uploadBtn.setIcon(FontAwesome.UPLOAD);
            controlsLayout.addComponent(uploadBtn);

            Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(final ClickEvent event) {
                    MultiUploadContentWindow.this.close();
                }
            });
            cancelBtn.addStyleName(UIConstants.BUTTON_OPTION);
            controlsLayout.addComponent(cancelBtn);
            controlsLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_RIGHT);

            contentLayout.addComponent(controlsLayout);
            contentLayout.setComponentAlignment(controlsLayout, Alignment.MIDDLE_CENTER);
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
                NotificationUtil.showNotification("Congrats", "Moved asset(s) successfully.");
            } else {
                NotificationUtil
                        .showWarningNotification("Moving assets is finished, some items can't move to destination. Please " +
                                "check duplicated file-name and try again.");
            }
        }

        @Override
        protected void displayFiles() {
            this.folderTree.removeAllItems();
            this.baseFolder = new Folder(rootPath);
            this.folderTree.addItem(new Object[]{ResourcesDisplayComponent.this.rootFolderName, ""}, this.baseFolder);
            this.folderTree.setItemCaption(this.baseFolder, ResourcesDisplayComponent.this.rootFolderName);
            this.folderTree.setCollapsed(this.baseFolder, false);
        }
    }

}
