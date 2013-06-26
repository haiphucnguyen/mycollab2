package com.esofthead.mycollab.module.project.view.file;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

public class FileSearchPanel extends GenericSearchPanel<FileSearchCriteria> {
	private static final long serialVersionUID = 1L;
	private final SimpleProject project;
	protected FileSearchCriteria searchCriteria;
	private ComponentContainer menuBar = null;

	public FileSearchPanel(final ComponentContainer menuBar) {
		this.project = (SimpleProject) AppContext.getVariable("project");
		this.menuBar = menuBar;
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
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		final Embedded titleIcon = new Embedded();
		titleIcon.setSource(MyCollabResource
				.newResource("icons/24/project/file.png"));
		layout.addComponent(titleIcon);
		layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

		final Label searchtitle = new Label("Files");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);
		layout.setExpandRatio(searchtitle, 1.0f);

		if (this.menuBar != null) {
			UiUtils.addComponent(layout, this.menuBar, Alignment.MIDDLE_RIGHT);
		}

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class FileBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public FileBasicSearchLayout() {
			super(FileSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructHeader() {
			return FileSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(false);

			this.nameField = this.createSeachSupportTextField(new TextField(),
					"NameFieldOfBasicSearch");

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
					FileBasicSearchLayout.this.callSearchAction();
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
			FileSearchPanel.this.searchCriteria = new FileSearchCriteria();
			FileSearchPanel.this.searchCriteria
					.setProjectId(new NumberSearchField(SearchField.AND,
							FileSearchPanel.this.project.getId()));

			FileSearchPanel.this.searchCriteria
					.setFileName(new StringSearchField(this.nameField
							.getValue().toString().trim()));

			return FileSearchPanel.this.searchCriteria;
		}
	}
}
