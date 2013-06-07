package com.esofthead.mycollab.module.project.view.file;

import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Window;

@ViewComponent
public class FileManagerViewImpl extends AbstractView implements
		FileManagerView {
	private static final long serialVersionUID = 1L;

	private Tree tree;
	private Table resourceTable;
	private String projectPath;

	private Folder baseFolder;

	private ResourceService resourceService;

	public FileManagerViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);

		HorizontalLayout menuBar = new HorizontalLayout();

		Button addFolderBtn = new Button("Add Folder",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						baseFolder = (Folder) tree.getValue();
						FileManagerViewImpl.this.getWindow().addWindow(
								new AddNewFolderWindow());
					}
				});

		menuBar.addComponent(addFolderBtn);

		Button uploadBtn = new Button("Upload", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
		menuBar.addComponent(uploadBtn);

		Button deleteBtn = new Button("Delete", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});

		menuBar.addComponent(deleteBtn);

		this.addComponent(menuBar);

		tree = new Tree();
		tree.setMultiSelect(false);
		tree.setImmediate(true);
		tree.setItemCaptionMode(Tree.ITEM_CAPTION_MODE_EXPLICIT);

		this.addComponent(tree);

		tree.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(ExpandEvent event) {
				Folder expandFolder = (Folder) event.getItemId();
				List<Folder> subFolders = resourceService
						.getSubFolders(expandFolder.getPath());
				if (subFolders != null) {
					for (Folder subFolder : subFolders) {
						expandFolder.addChild(subFolder);
						tree.addItem(subFolder);
						tree.setItemCaption(subFolder, subFolder.getName());
						tree.setParent(subFolder, expandFolder);
					}
				}
			}
		});

		tree.addListener(new Tree.CollapseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeCollapse(CollapseEvent event) {
				Folder collapseFolder = (Folder) event.getItemId();
				List<Folder> childs = collapseFolder.getChilds();
				for (Folder subFolder : childs) {
					tree.removeItem(subFolder);
				}

				childs.clear();
			}
		});

		tree.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				baseFolder = (Folder) event.getItemId();
				displayResourcesInTable(baseFolder);
			}
		});

		resourceTable = new Table();
		resourceTable.addGeneratedColumn("path", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final Container container = source.getContainerDataSource();
				final BeanItem<Resource> item = (BeanItem<Resource>) container
						.getItem(itemId);
				if (item != null) {
					Resource resource = item.getBean();
					return new Label(resource.getPath());
				} else {
					return new Label("Undefined resource");
				}
			}
		});

		this.addComponent(resourceTable);
	}

	private void displayResourcesInTable(Folder baseFolder) {
		// Folder testFolder = new Folder();
		// testFolder.setPath("/a/b/d");
		//
		// Content content = new Content();
		// content.setPath("/a/b/e");
		//
		// List<Resource> resources = Arrays.asList(testFolder, content);
		// resourceTable.setContainerDataSource(new BeanItemContainer(
		// Resource.class, resources));
	}

	@Override
	public void displayProjectFiles() {
		tree.removeAllItems();
		int projectId = CurrentProjectVariables.getProjectId();
		projectPath = String.format("%d/project/%d", AppContext.getAccountId(),
				projectId);

		baseFolder = new Folder();
		baseFolder.setPath(projectPath);
		tree.addItem(baseFolder);
		tree.setItemCaption(baseFolder, CurrentProjectVariables.getProject()
				.getName());

		tree.expandItem(baseFolder);
	}

	private class AddNewFolderWindow extends Window {
		private static final long serialVersionUID = 1L;

		private TextField folderName;

		public AddNewFolderWindow() {
			this.setWidth("500px");
			this.setModal(true);
			this.setCaption("New Folder");
			center();

			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.addComponent(new Label("Enter folder name: "));

			folderName = new TextField();
			layout.addComponent(folderName);

			this.addComponent(layout);

			HorizontalLayout controlsLayout = new HorizontalLayout();

			Button saveBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {

							String folderVal = (String) folderName.getValue();
							if (folderVal != null
									&& !folderVal.trim().equals("")) {
								// TODO: check folder name with valid characters
								String baseFolderPath = (baseFolder == null) ? projectPath
										: baseFolder.getPath();
								Folder newFolder = resourceService
										.createNewFolder(baseFolderPath,
												folderVal,
												AppContext.getUsername());
								baseFolder.addChild(newFolder);
								tree.addItem(newFolder);
								tree.setItemCaption(newFolder,
										newFolder.getName());
								tree.setParent(newFolder, baseFolder);
								if (!tree.isExpanded(baseFolder)) {
									tree.expandItem(baseFolder);
								}
								AddNewFolderWindow.this.close();

							}
						}
					});
			controlsLayout.addComponent(saveBtn);

			Button cancelBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							AddNewFolderWindow.this.close();

						}
					});
			controlsLayout.addComponent(cancelBtn);

			this.addComponent(controlsLayout);
		}
	}
}
