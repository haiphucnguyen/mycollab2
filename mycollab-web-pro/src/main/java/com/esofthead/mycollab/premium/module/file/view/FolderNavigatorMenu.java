package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
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

    public FolderNavigatorMenu(final String rootPath) {
        final ExternalDriveService externalDriveService = ApplicationContextUtil.getSpringBean(ExternalDriveService.class);
        final ResourceService resourceService = ApplicationContextUtil.getSpringBean(ResourceService.class);
        final ExternalResourceService externalResourceService = ApplicationContextUtil.getSpringBean
                (ExternalResourceService.class);

        this.setMultiSelect(false);
        this.setSelectable(true);
        this.setImmediate(true);

        this.addExpandListener(new Tree.ExpandListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void nodeExpand(final ExpandEvent event) {
                Object object = event.getItemId();
                if (object instanceof Folder) {
                    final Folder expandFolder = (Folder) event.getItemId();

                    // if expand folder is root, will load external drives also
                    if (rootPath.equals(expandFolder.getPath())) {
                        List<ExternalDrive> externalDrives = externalDriveService.getExternalDrivesOfUser(AppContext.getUsername());
                        for (ExternalDrive externalDrive : externalDrives) {
                            ExternalFolder externalMapFolder = new ExternalFolder("/");
                            externalMapFolder.setStorageName(externalDrive.getStoragename());
                            externalMapFolder.setExternalDrive(externalDrive);
                            externalMapFolder.setName(externalDrive.getFoldername());
                            expandFolder.addChild(externalMapFolder);
                            addItem(externalMapFolder);

                            setItemIcon(externalMapFolder, FontAwesome.DROPBOX);
                            setItemCaption(externalMapFolder, externalMapFolder.getName());
                            setParent(externalMapFolder, expandFolder);
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
                                if (!subFolder.getName().startsWith(".")) {
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
            }
        });

        this.addCollapseListener(new Tree.CollapseListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void nodeCollapse(final CollapseEvent event) {
                Folder collapseFolder = (Folder) event.getItemId();
                Container dataSource = FolderNavigatorMenu.this.getContainerDataSource();
                Object[] dataCollectionArray = dataSource.getItemIds().toArray();
                for (Object id : dataCollectionArray) {
                    Folder folder = (Folder) id;
                    if (folder.getPath().contains(collapseFolder.getPath()) && !folder.getPath().equals(collapseFolder.getPath())
                            || collapseFolder.getPath().equals(rootPath)) {
                        if (!folder.getPath().equals(rootPath)) {
                            if (collapseFolder instanceof ExternalFolder && folder instanceof ExternalFolder) {
                                if (((ExternalFolder) folder).getExternalDrive()
                                        .getAccesstoken().equals(((ExternalFolder) folder).getExternalDrive().getAccesstoken()))
                                    dataSource.removeItem(folder);
                            } else if (!(collapseFolder instanceof ExternalFolder)) {
                                dataSource.removeItem(folder);
                            }
                        }
                    }
                }
                FolderNavigatorMenu.this.setContainerDataSource(dataSource);
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