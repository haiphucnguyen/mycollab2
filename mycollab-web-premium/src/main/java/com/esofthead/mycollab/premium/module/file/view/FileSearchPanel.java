package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class FileSearchPanel extends GenericSearchPanel<FileSearchCriteria> {
	private static final long serialVersionUID = 1L;
	
	private String rootPath;
	private HorizontalLayout basicSearchBody;

	public HorizontalLayout getBasicSearchBody() {
		return basicSearchBody;
	}

	public FileSearchPanel(String rootPath) {
		this.rootPath = rootPath;
		this.setCompositionRoot(new FileBasicSearchLayout());
	}

	@SuppressWarnings("rawtypes")
	class FileBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public FileBasicSearchLayout() {
			super(FileSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructHeader() {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.setWidth("100%");
			layout.setSpacing(true);
			layout.setMargin(true);

			final Image titleIcon = new Image(
					null,
					MyCollabResource
							.newResource("icons/24/ecm/document_preview.png"));
			layout.addComponent(titleIcon);
			layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

			final Label searchtitle = new Label("Files");
			searchtitle.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(searchtitle);
			layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);
			layout.setExpandRatio(searchtitle, 1.0f);
			return layout;
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.setMargin(true);
			UiUtils.addComponent(basicSearchBody, new Label("Name:"),
					Alignment.MIDDLE_LEFT);
			this.addStyleName("file-list-view");
			this.nameField = new TextField();
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);

			this.myItemCheckbox = new CheckBox(
					AppContext
							.getMessage(GenericI18Enum.SEARCH_MYITEMS_CHECKBOX));
			UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button(
					AppContext
							.getMessage(GenericI18Enum.BUTTON_SEARCH_LABEL));
			searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search.png"));

			searchBtn.addClickListener(new Button.ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					ResourceService resourceService = ApplicationContextUtil.getSpringBean(ResourceService.class);
//					resourceHandlerLayout.setCurrentBaseFolder(baseFolder);
//					List<Resource> resources = resourceService
//							.searchResourcesByName(rootPath,
//									nameField.getValue().toString().trim());
//					if (CollectionUtils.isNotEmpty(resources)) {
//						resourceHandlerLayout
//								.constructBodyItemContainerSearchActionResult(
//										resources, nameField.getValue()
//												.toString().trim());
//						resourceHandlerLayout.initBreadCrumb();
//						resourceHandlerLayout
//								.setCurrentBaseFolder((Folder) FileMainViewImpl.this.resourceService
//										.getResource(rootPath));
//					} else {
//						resourceHandlerLayout
//								.constructBodyItemContainerSearchActionResult(
//										resources, nameField.getValue()
//												.toString().trim());
//					}
				}
			});
			UiUtils.addComponent(basicSearchBody, searchBtn,
					Alignment.MIDDLE_LEFT);

			final Button cancelBtn = new Button(
					AppContext
							.getMessage(GenericI18Enum.BUTTON_CLEAR_LABEL));

			cancelBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					FileBasicSearchLayout.this.nameField.setValue("");
				}
			});
			cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
			basicSearchBody.addComponent(cancelBtn);

			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			return null;
		}
	}

}
