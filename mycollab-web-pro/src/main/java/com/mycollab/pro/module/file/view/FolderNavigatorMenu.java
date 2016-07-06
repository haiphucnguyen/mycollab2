package com.mycollab.pro.module.file.view;

import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.ecm.domain.ExternalDrive;
import com.mycollab.module.ecm.domain.ExternalFolder;
import com.mycollab.module.ecm.domain.Folder;
import com.mycollab.module.ecm.domain.Resource;
import com.mycollab.module.ecm.service.ExternalDriveService;
import com.mycollab.module.ecm.service.ExternalResourceService;
import com.mycollab.module.ecm.service.ResourceService;
import com.mycollab.module.file.events.FileEvent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.google.common.eventbus.Subscribe;
import com.vaadin.data.Container;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Tree;
import com.vaadin.util.ReflectTools;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class FolderNavigatorMenu extends Tree {
    private static final long serialVersionUID = 1L;

    private Folder rootFolder;

    public FolderNavigatorMenu(final String rootPath) {
        final ExternalDriveService externalDriveService = AppContextUtil.getSpringBean(ExternalDriveService.class);
        final ResourceService resourceService = AppContextUtil.getSpringBean(ResourceService.class);
        final ExternalResourceService externalResourceService = AppContextUtil.getSpringBean
                (ExternalResourceService.class);

        this.setMultiSelect(false);
        this.setSelectable(true);
        this.setImmediate(true);

        rootFolder = new Folder(rootPath);

        this.addExpandListener(new Tree.ExpandListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void nodeExpand(final ExpandEvent event) {
                final Folder expandFolder = (Folder) event.getItemId();

                // if expand folder is root, will load external drives also
                if (rootPath.equals(expandFolder.getPath())) {
                    List<ExternalDrive> externalDrives = externalDriveService.getExternalDrivesOfUser(AppContext.getUsername());
                    for (ExternalDrive externalDrive : externalDrives) {
                        ExternalFolder externalFolder = new ExternalFolder("/");
                        externalFolder.setStorageName(externalDrive.getStoragename());
                        externalFolder.setExternalDrive(externalDrive);
                        externalFolder.setName(externalDrive.getFoldername());
                        expandFolder.addChild(externalFolder);

                        // Add external folder to tree
                        addItem(externalFolder);
                        setItemIcon(externalFolder, FontAwesome.DROPBOX);
                        setItemCaption(externalFolder, externalFolder.getName());
                        setParent(externalFolder, expandFolder);
                    }
                }
                if (expandFolder instanceof ExternalFolder) {
                    List<ExternalFolder> subFolders = externalResourceService.getSubFolders(((ExternalFolder) expandFolder)
                            .getExternalDrive(), expandFolder.getPath());
                    for (Folder subFolder : subFolders) {
                        expandFolder.addChild(subFolder);
                        addItem(subFolder);
                        setItemIcon(subFolder, FontAwesome.DROPBOX);
                        setItemCaption(subFolder, subFolder.getName());
                        setParent(subFolder, expandFolder);
                    }
                } else {
                    List<Folder> subFolders = resourceService.getSubFolders(expandFolder.getPath());
                    setItemIcon(expandFolder, FontAwesome.FOLDER_OPEN);

                    if (subFolders != null) {
                        for (Folder subFolder : subFolders) {
                            if (!subFolder.isHiddenFolder()) {
                                expandFolder.addChild(subFolder);
                                addItem(subFolder);
                                setItemIcon(subFolder, FontAwesome.FOLDER);
                                setItemCaption(subFolder, subFolder.getName());
                                setParent(subFolder, expandFolder);
                            }
                        }
                    }
                }
            }
        });

        this.addCollapseListener(new Tree.CollapseListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void nodeCollapse(final CollapseEvent event) {
                Folder collapseFolder = (Folder) event.getItemId();
                Container dataSource = getContainerDataSource();
                Object[] dataCollectionArray = dataSource.getItemIds().toArray();
                if (collapseFolder.getPath().equals(rootPath)) {
                    for (Object id : dataCollectionArray) {
                        Folder folder = (Folder) id;
                        if (!folder.getPath().equals(rootPath)) {
                            dataSource.removeItem(folder);
                        }
                    }
                } else {
                    for (Object id : dataCollectionArray) {
                        Folder folder = (Folder) id;
                        if (folder.getPath().contains(collapseFolder.getPath()) && !folder.getPath().equals
                                (collapseFolder.getPath())) {
                            if (collapseFolder instanceof ExternalFolder) {
                                if (folder instanceof ExternalFolder) {
                                    if (((ExternalFolder) folder).getExternalDrive()
                                            .getAccesstoken().equals(((ExternalFolder) folder).getExternalDrive().getAccesstoken()))
                                        dataSource.removeItem(folder);
                                }
                            } else {
                                dataSource.removeItem(folder);
                            }
                        }

                    }
                }
                setContainerDataSource(dataSource);
            }
        });

        this.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void itemClick(final ItemClickEvent event) {
                Folder item = (Folder) event.getItemId();
                fireEvent(new SelectFolderEvent(FolderNavigatorMenu.this, item));
            }
        });
    }

    private ApplicationEventListener<FileEvent.ExternalDriveConnectedEvent> externalDriveConnectHandler = new
            ApplicationEventListener<FileEvent.ExternalDriveConnectedEvent>() {
                @Override
                @Subscribe
                public void handle(FileEvent.ExternalDriveConnectedEvent event) {
                    FolderNavigatorMenu.this.collapseItem(rootFolder);
                    FolderNavigatorMenu.this.expandItem(rootFolder);
                }
            };

    private ApplicationEventListener<FileEvent.ExternalDriveDeleteEvent> externalDriveDeleteHandler = new
            ApplicationEventListener<FileEvent.ExternalDriveDeleteEvent>() {
                @Override
                @Subscribe
                public void handle(FileEvent.ExternalDriveDeleteEvent event) {
                    FolderNavigatorMenu.this.collapseItem(rootFolder);
                    FolderNavigatorMenu.this.expandItem(rootFolder);
                }
            };

    private ApplicationEventListener<FileEvent.ResourceRemovedEvent> deleteResourceHandler = new
            ApplicationEventListener<FileEvent.ResourceRemovedEvent>() {
                @Override
                @Subscribe
                public void handle(FileEvent.ResourceRemovedEvent event) {
                    Resource resource = (Resource) event.getData();
                    if (resource instanceof Folder) {
                        Folder folder = (Folder) resource;
                        Folder parentFolder = (Folder)FolderNavigatorMenu.this.getParent(folder);
                        FolderNavigatorMenu.this.collapseItem(parentFolder);
                        FolderNavigatorMenu.this.expandItem(parentFolder);
                    }

                }
            };

    @Override
    public void attach() {
        super.attach();
        this.removeAllItems();
        this.addItem(rootFolder);
        this.setItemCaption(rootFolder, "Documents");
        this.setItemIcon(rootFolder, FontAwesome.FOLDER);
        this.collapseItem(rootFolder);
        EventBusFactory.getInstance().register(externalDriveConnectHandler);
        EventBusFactory.getInstance().register(externalDriveDeleteHandler);
        EventBusFactory.getInstance().register(deleteResourceHandler);
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(externalDriveConnectHandler);
        EventBusFactory.getInstance().unregister(externalDriveDeleteHandler);
        EventBusFactory.getInstance().unregister(deleteResourceHandler);
        super.detach();
    }

    public void addSelectFolderListener(SelectedFolderListener listener) {
        this.addListener(SelectFolderEvent.VIEW_IDENTIFIER, SelectFolderEvent.class, listener,
                SelectedFolderListener.viewInitMethod);
    }

    public interface SelectedFolderListener extends EventListener, Serializable {
        Method viewInitMethod = ReflectTools.findMethod(
                SelectedFolderListener.class, "selectFolder", SelectFolderEvent.class);

        void selectFolder(SelectFolderEvent event);
    }

    public static class SelectFolderEvent extends EventObject {
        private static final long serialVersionUID = 1L;

        public static final String VIEW_IDENTIFIER = "selectfolder";

        private Folder folder;

        public SelectFolderEvent(Object source, Folder data) {
            super(source);
            folder = data;
        }

        public Folder getFolder() {
            return folder;
        }
    }
}