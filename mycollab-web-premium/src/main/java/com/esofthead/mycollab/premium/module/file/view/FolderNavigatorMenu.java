package com.esofthead.mycollab.premium.module.file.view;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.data.Container;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Tree;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class FolderNavigatorMenu extends Tree {
	private static final long serialVersionUID = 1L;

	public FolderNavigatorMenu(final String rootPath) {

		final ExternalDriveService externalDriveService = ApplicationContextUtil
				.getSpringBean(ExternalDriveService.class);

		final ResourceService resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);

		final ExternalResourceService externalResourceService = ApplicationContextUtil
				.getSpringBean(ExternalResourceService.class);
		
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
						List<ExternalDrive> externalDrives = externalDriveService
								.getExternalDrivesOfUser(AppContext
										.getUsername());
						for (ExternalDrive externalDrive : externalDrives) {
							ExternalFolder externalMapFolder = new ExternalFolder();
							externalMapFolder.setStorageName(externalDrive
									.getStoragename());
							externalMapFolder.setExternalDrive(externalDrive);
							externalMapFolder.setPath("/");
							externalMapFolder.setName(externalDrive
									.getFoldername());
							expandFolder.addChild(externalMapFolder);
							FolderNavigatorMenu.this.addItem(externalMapFolder);

							FolderNavigatorMenu.this.setItemIcon(
									externalMapFolder,
									MyCollabResource
											.newResource("icons/16/ecm/dropbox.png"));
							FolderNavigatorMenu.this.setItemCaption(
									externalMapFolder,
									externalMapFolder.getName());
							FolderNavigatorMenu.this.setParent(
									externalMapFolder, expandFolder);
						}
					}
					if (expandFolder instanceof ExternalFolder) {
						List<ExternalFolder> subFolders = externalResourceService
								.getSubFolders(((ExternalFolder) expandFolder)
										.getExternalDrive(), expandFolder
										.getPath());
						for (final Folder subFolder : subFolders) {
							expandFolder.addChild(subFolder);
							FolderNavigatorMenu.this.addItem(subFolder);

							FolderNavigatorMenu.this.setItemIcon(
									subFolder,
									MyCollabResource
											.newResource("icons/16/ecm/dropbox_subfolder.png"));
							FolderNavigatorMenu.this.setItemCaption(subFolder,
									subFolder.getName());
							FolderNavigatorMenu.this.setParent(subFolder,
									expandFolder);
						}
					} else {
						final List<Folder> subFolders = resourceService
								.getSubFolders(expandFolder.getPath());

						FolderNavigatorMenu.this.setItemIcon(
								expandFolder,
								MyCollabResource
										.newResource("icons/16/ecm/folder_open.png"));

						if (subFolders != null) {
							for (final Folder subFolder : subFolders) {
								expandFolder.addChild(subFolder);
								FolderNavigatorMenu.this.addItem(subFolder);

								FolderNavigatorMenu.this
										.setItemIcon(
												subFolder,
												MyCollabResource
														.newResource("icons/16/ecm/folder_close.png"));
								FolderNavigatorMenu.this.setItemCaption(
										subFolder, subFolder.getName());
								FolderNavigatorMenu.this.setParent(subFolder,
										expandFolder);
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
				final Folder collapseFolder = (Folder) event.getItemId();
				Container dataSource = FolderNavigatorMenu.this
						.getContainerDataSource();
				final Object[] dataCollectionArray = dataSource.getItemIds()
						.toArray();
				for (Object id : dataCollectionArray) {
					Folder folder = (Folder) id;
					if (folder.getPath().contains(collapseFolder.getPath())
							&& !folder.getPath().equals(
									collapseFolder.getPath())
							|| collapseFolder.getPath().equals(rootPath)) {
						if (!folder.getPath().equals(rootPath)) {
							if (collapseFolder instanceof ExternalFolder
									&& folder instanceof ExternalFolder) {
								if (((ExternalFolder) folder)
										.getExternalDrive()
										.getAccesstoken()
										.equals(((ExternalFolder) folder)
												.getExternalDrive()
												.getAccesstoken()))
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
				final Folder item = (Folder) event.getItemId();
//				gotoFileMainViewPage(item);
			}
		});
	}
}
