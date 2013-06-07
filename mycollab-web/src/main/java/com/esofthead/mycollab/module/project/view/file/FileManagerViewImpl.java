package com.esofthead.mycollab.module.project.view.file;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ExpandEvent;

@ViewComponent
public class FileManagerViewImpl extends AbstractView implements
		FileManagerView {
	private static final long serialVersionUID = 1L;

	private Tree tree;
	private Table resourceTable;
	private String projectPath;

	private ResourceService resourceService;

	public FileManagerViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);

		HorizontalLayout menuBar = new HorizontalLayout();
		this.addComponent(menuBar);

		tree = new Tree();
		tree.setItemCaptionMode(Tree.ITEM_CAPTION_MODE_EXPLICIT);

		HierarchicalContainer container = new HierarchicalContainer();
		Folder rootFolder = new Folder();
		rootFolder.setPath("/a/b");

		Folder docFolder = new Folder();
		docFolder.setPath("/a/b/c");

		container.addItem(rootFolder);
		tree.setItemCaption(rootFolder, "b");

		container.addItem(docFolder);
		tree.setItemCaption(docFolder, "c");

		container.setParent(rootFolder, docFolder);

		tree.setContainerDataSource(container);

		this.addComponent(tree);

		tree.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(ExpandEvent event) {
				System.out.println("expand event: " + event.getItemId());
			}
		});

		tree.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				Folder baseFolder = (Folder) event.getItemId();
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
		Folder testFolder = new Folder();
		testFolder.setPath("/a/b/d");

		Content content = new Content();
		content.setPath("/a/b/e");

		List<Resource> resources = Arrays.asList(testFolder, content);
		resourceTable.setContainerDataSource(new BeanItemContainer(
				Resource.class, resources));
	}

	@Override
	public void displayProjectFiles() {
		int projectId = CurrentProjectVariables.getProjectId();
		projectPath = String.format("/%d/project/%d",
				AppContext.getAccountId(), projectId);
		List<Resource> resources = resourceService.getResources(projectPath);
	}
}
