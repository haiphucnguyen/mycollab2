package com.esofthead.mycollab.module.file.view;

import java.util.List;

import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class FileMainViewImpl extends AbstractView implements FileMainView {
	private static final long serialVersionUID = 1L;
	private Tree treeMenu;
	private FilterPanel filterPanel;
	private HorizontalLayout controllGroupBtn;
	private final ResourceService resourceService;
	private Folder baseFolder;
	private String rootPath;
	private String rootFolderName;
	private IteamResourceContainerLayout itemResourceContainerLayout;

	public FileMainViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);
		this.setSpacing(true);
		this.setMargin(false);

		HorizontalLayout mainView = new HorizontalLayout();
		mainView.setSpacing(true);
		mainView.setMargin(true);
		mainView.setWidth("1130px");

		final HorizontalLayout menuBarContainerHorizontalLayout = new HorizontalLayout();
		menuBarContainerHorizontalLayout.setMargin(true);

		final VerticalLayout menuLayout = new VerticalLayout();
		menuBarContainerHorizontalLayout.addComponent(menuLayout);

		this.treeMenu = new Tree();
		this.treeMenu.setMultiSelect(false);
		this.treeMenu.setSelectable(true);
		this.treeMenu.setImmediate(true);

		menuLayout.addComponent(this.treeMenu);

		this.treeMenu.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(final ExpandEvent event) {
				final Folder expandFolder = (Folder) event.getItemId();
				final List<Folder> subFolders = resourceService
						.getSubFolders(expandFolder.getPath());

				treeMenu.setItemIcon(expandFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_open.png"));

				if (subFolders != null) {
					for (final Folder subFolder : subFolders) {
						expandFolder.addChild(subFolder);
						treeMenu.addItem(subFolder);

						treeMenu.setItemIcon(subFolder, MyCollabResource
								.newResource("icons/16/ecm/folder_close.png"));
						treeMenu.setItemCaption(subFolder, subFolder.getName());
						treeMenu.setParent(subFolder, expandFolder);
					}
				}
			}
		});

		this.treeMenu.addListener(new Tree.CollapseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeCollapse(final CollapseEvent event) {
				final Folder collapseFolder = (Folder) event.getItemId();
				treeMenu.setItemIcon(collapseFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_close.png"));
				final List<Folder> childs = collapseFolder.getChilds();
				for (final Folder subFolder : childs) {
					treeMenu.removeItem(subFolder);
				}

				childs.clear();
			}
		});

		this.treeMenu.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(final ItemClickEvent event) {

			}
		});

		HorizontalLayout shareActionLayout = new HorizontalLayout();
		final Embedded shareIcon = new Embedded();
		shareIcon.setSource(MyCollabResource
				.newResource("icons/24/shareICon.png"));
		shareActionLayout.addComponent(shareIcon);
		shareActionLayout.setComponentAlignment(shareIcon,
				Alignment.MIDDLE_CENTER);
		Button shareBtnLink = new Button("Shared with me");
		shareBtnLink.addStyleName("link");
		shareActionLayout.addComponent(shareBtnLink);
		shareActionLayout.setComponentAlignment(shareBtnLink,
				Alignment.MIDDLE_CENTER);
		menuLayout.addComponent(shareActionLayout);

		mainView.addComponent(menuBarContainerHorizontalLayout);

		Separator separator = new Separator();
		separator.setHeight("500px");
		mainView.addComponent(separator);

		VerticalLayout mainBodyLayout = new VerticalLayout();

		filterPanel = new FilterPanel();
		mainBodyLayout.addComponent(filterPanel);

		// Construct controllGroupBtn
		controllGroupBtn = new HorizontalLayout();
		controllGroupBtn.setMargin(true, false, false, true);
		controllGroupBtn.setSpacing(true);

		Button selectAllBtn = new Button();
		selectAllBtn.setIcon(MyCollabResource
				.newResource("icons/16/checkbox_empty.png"));
		PopupButtonControl tableActionControls = new PopupButtonControl(
				"selectAll", selectAllBtn);
		tableActionControls.setWidth("70px");
		UiUtils.addComponent(controllGroupBtn, tableActionControls,
				Alignment.MIDDLE_LEFT);

		Button downloadBtn = new Button("Download");
		downloadBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, downloadBtn,
				Alignment.MIDDLE_LEFT);

		Button downloadAsBtn = new Button("Download as");
		downloadAsBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, downloadAsBtn,
				Alignment.MIDDLE_LEFT);

		Button moveToBtn = new Button("Move to");
		moveToBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, moveToBtn, Alignment.MIDDLE_LEFT);

		Button copyBtn = new Button("Copy");
		copyBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, copyBtn, Alignment.MIDDLE_LEFT);

		Button deleteBtn = new Button("Delete");
		deleteBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, deleteBtn, Alignment.MIDDLE_LEFT);

		mainBodyLayout.addComponent(controllGroupBtn);

		String rootPath = String.format("%d/.crm", AppContext.getAccountId());
		this.baseFolder = new Folder();
		this.baseFolder.setPath(rootPath);

		itemResourceContainerLayout = new IteamResourceContainerLayout(
				FileMainViewImpl.this.baseFolder, resourceService);
		itemResourceContainerLayout.setWidth("100%");

		mainBodyLayout.addComponent(itemResourceContainerLayout);

		mainView.addComponent(mainBodyLayout);

		mainView.setExpandRatio(mainBodyLayout, 1.0f);

		this.addComponent(mainView);
		this.setComponentAlignment(mainView, Alignment.MIDDLE_CENTER);

		displayResources(rootPath, "My Documents");
	}

	class FilterPanel extends GenericSearchPanel<FileSearchCriteria> {
		private static final long serialVersionUID = 1L;
		protected FileSearchCriteria searchCriteria;
		private HorizontalLayout basicSearchBody;

		public HorizontalLayout getBasicSearchBody() {
			return basicSearchBody;
		}

		public FilterPanel() {
		}

		@Override
		public void attach() {
			super.attach();
			this.createBasicSearchLayout();
		}

		private void createBasicSearchLayout() {

			this.setCompositionRoot(new FileBasicSearchLayout());
		}

		private HorizontalLayout createSearchTopPanel() {
			return new HorizontalLayout();
		}

		@SuppressWarnings("rawtypes")
		class FileBasicSearchLayout extends BasicSearchLayout {

			@SuppressWarnings("unchecked")
			public FileBasicSearchLayout() {
				super(FilterPanel.this);
			}

			private static final long serialVersionUID = 1L;
			private TextField nameField;
			private CheckBox myItemCheckbox;

			@Override
			public ComponentContainer constructHeader() {
				return FilterPanel.this.createSearchTopPanel();
			}

			@Override
			public ComponentContainer constructBody() {
				basicSearchBody = new HorizontalLayout();
				basicSearchBody.setSpacing(false);

				Label filterLabel = new Label("Filter: ");
				filterLabel.addStyleName("h3");
				UiUtils.addComponent(basicSearchBody, filterLabel,
						Alignment.MIDDLE_CENTER);

				this.nameField = this.createSeachSupportTextField(
						new TextField(), "NameFieldOfBasicSearch");

				this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
				UiUtils.addComponent(basicSearchBody, this.nameField,
						Alignment.MIDDLE_CENTER);

				final Button searchBtn = new Button();
				searchBtn.setStyleName("search-icon-button");
				searchBtn.setIcon(MyCollabResource
						.newResource("icons/16/search_white.png"));
				searchBtn.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
					}
				});
				UiUtils.addComponent(basicSearchBody, searchBtn,
						Alignment.MIDDLE_LEFT);

				this.myItemCheckbox = new CheckBox("My Items");
				this.myItemCheckbox.setWidth("75px");
				UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
						Alignment.MIDDLE_CENTER);

				final Separator separator = new Separator();
				UiUtils.addComponent(basicSearchBody, separator,
						Alignment.MIDDLE_LEFT);

				final Button cancelBtn = new Button(
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.BUTTON_CLEAR));
				cancelBtn.setStyleName(UIConstants.THEME_LINK);
				cancelBtn.addStyleName("cancel-button");
				cancelBtn.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						FileBasicSearchLayout.this.nameField.setValue("");
					}
				});
				UiUtils.addComponent(basicSearchBody, cancelBtn,
						Alignment.MIDDLE_CENTER);
				return basicSearchBody;
			}

			@Override
			protected SearchCriteria fillupSearchCriteria() {
				return null;
			}
		}
	}

	private class IteamResourceContainerLayout extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		public IteamResourceContainerLayout(Folder folder,
				ResourceService resourceService) {
			VerticalLayout layout = new VerticalLayout();
			List<Resource> lstResource = resourceService.getResources(folder
					.getPath());
			this.addComponent(new Hr());
			for (Resource res : lstResource) {
				layout.addComponent(constructOneIteamResourceLayout(res));
				layout.addComponent(new Hr());
			}
			this.addComponent(layout);
		}

		private HorizontalLayout constructOneIteamResourceLayout(Resource res) {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.addStyleName("resourceItem");
			layout.setWidth("100%");
			layout.setSpacing(true);

			final CheckBox checkbox = new CheckBox();
			checkbox.setWidth("30px");
			checkbox.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			layout.addComponent(checkbox);
			layout.setComponentAlignment(checkbox, Alignment.MIDDLE_LEFT);

			layout.addListener(new LayoutClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void layoutClick(LayoutClickEvent event) {
					if ((Boolean) checkbox.getValue())
						checkbox.setValue(false);
					else
						checkbox.setValue(true);
				}
			});

			CssLayout resIconWapper = new CssLayout();
			final Embedded resourceIcon = new Embedded();
			resourceIcon.setSource(MyCollabResource
					.newResource("icons/48/Folder-icon.png"));
			resIconWapper.setWidth("70px");
			resIconWapper.addComponent(resourceIcon);

			layout.addComponent(resIconWapper);
			layout.setComponentAlignment(resIconWapper, Alignment.MIDDLE_LEFT);

			VerticalLayout informationLayout = new VerticalLayout();
			Label resourceNameLabel = new Label(res.getName());
			resourceNameLabel.addStyleName("h3");
			informationLayout.addComponent(resourceNameLabel);

			HorizontalLayout moreInfoAboutResLayout = new HorizontalLayout();
			moreInfoAboutResLayout.addComponent(new Label(res.getCreatedBy()));
			moreInfoAboutResLayout.addComponent(new Separator());
			moreInfoAboutResLayout.addComponent(new Label(AppContext
					.formatDate(res.getCreated().getTime())));
			informationLayout.addComponent(moreInfoAboutResLayout);

			layout.addComponent(informationLayout);
			layout.setComponentAlignment(informationLayout,
					Alignment.MIDDLE_LEFT);
			layout.setExpandRatio(informationLayout, 1.0f);

			CssLayout shareIconWapper = new CssLayout();
			final Embedded shareIcon = new Embedded();
			shareIcon.setSource(MyCollabResource
					.newResource("icons/24/shareICon.png"));
			shareIconWapper.addComponent(shareIcon);
			shareIconWapper.addComponent(new Label("Share"));
			shareIconWapper.setWidth("100px");

			layout.addComponent(shareIconWapper);
			layout.setComponentAlignment(shareIconWapper,
					Alignment.MIDDLE_RIGHT);

			final PopupButton resourceSettingPopupBtn = new PopupButton();
			final VerticalLayout filterBtnLayout = new VerticalLayout();

			final Button renameBtn = new Button("Rename",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
						}
					});
			renameBtn.addStyleName("link");
			filterBtnLayout.addComponent(renameBtn);
			filterBtnLayout.setMargin(true);
			filterBtnLayout.setSpacing(true);
			filterBtnLayout.setWidth("100px");
			resourceSettingPopupBtn.setIcon(MyCollabResource
					.newResource("icons/16/item_settings.png"));
			resourceSettingPopupBtn.setStyleName("link");
			resourceSettingPopupBtn.addComponent(filterBtnLayout);

			layout.addComponent(resourceSettingPopupBtn);
			layout.setComponentAlignment(resourceSettingPopupBtn,
					Alignment.MIDDLE_RIGHT);
			return layout;
		}
	}

	private void displayResourcesInListLayout(final Folder folder) {
		// if
		// (!itemResourceContainerLayout.getFolder().equals(folder.getPath())) {
		// itemResourceContainerLayout.removeAllComponents();
		// itemResourceContainerLayout = new IteamResourceContainerLayout(
		// folder, resourceService);
		// }
		this.baseFolder = folder;
	}

	// when click on IteamResouce , we call this function
	private void displayResourcesInTable(final String foldername) {
		List<Folder> childs = resourceService.getSubFolders(this.baseFolder
				.getPath());
		if (childs == null) {
			childs = this.resourceService.getSubFolders(this.baseFolder
					.getPath());

			for (final Folder subFolder : childs) {
				this.baseFolder.addChild(subFolder);
				this.treeMenu.addItem(new Object[] {
						subFolder.getName(),
						AppContext.formatDateTime(subFolder.getCreated()
								.getTime()) });
				this.treeMenu.setItemCaption(subFolder, subFolder.getName());
				this.treeMenu.setParent(subFolder, this.baseFolder);
				if (foldername.equals(subFolder.getName())) {
					treeMenu.collapseItem(subFolder);
					this.displayResourcesInListLayout(subFolder);
				} else {
					this.treeMenu.setItemIcon(subFolder, MyCollabResource
							.newResource("icons/16/ecm/folder_close.png"));
				}
			}
		} else {
			for (final Folder subFolder : childs) {
				if (foldername.equals(subFolder.getName())) {
					this.treeMenu.collapseItem(subFolder);
					this.treeMenu.setValue(subFolder);
					this.displayResourcesInListLayout(subFolder);
				}
			}
		}
	}

	public void displayResources(String rootPath, String rootFolderName) {
		this.treeMenu.removeAllItems();
		this.rootPath = rootPath;
		this.rootFolderName = rootFolderName;

		this.baseFolder = new Folder();
		this.baseFolder.setPath(this.rootPath);
		this.treeMenu.addItem(this.baseFolder);
		this.treeMenu.setItemCaption(this.baseFolder, rootFolderName);
		this.treeMenu.setItemIcon(this.baseFolder,
				MyCollabResource.newResource("icons/16/ecm/folder_close.png"));

		this.treeMenu.collapseItem(this.baseFolder);
		this.displayResourcesInListLayout(this.baseFolder);
	}

	@Override
	public void display() {
	}
}
