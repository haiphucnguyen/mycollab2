package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.localization.ProblemI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.MyCollabSession;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProblemSearchPanel extends
GenericSearchPanel<ProblemSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private final SimpleProject project;
	protected ProblemSearchCriteria searchCriteria;

	public ProblemSearchPanel() {
		this.project = (SimpleProject) MyCollabSession.getVariable("project");
	}

	@Override
	public void attach() {
		super.attach();
		this.createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new ProblemBasicSearchLayout());
	}



	@SuppressWarnings("rawtypes")
	private class ProblemBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public ProblemBasicSearchLayout() {
			super(ProblemSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(false);
			basicSearchBody.setMargin(true);

			this.nameField = this.createSeachSupportTextField(new TextField(),
					"NameFieldOfBasicSearch");

			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));

			searchBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					ProblemBasicSearchLayout.this.callSearchAction();
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
					LocalizationHelper.getMessage(GenericI18Enum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					ProblemBasicSearchLayout.this.nameField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			ProblemSearchPanel.this.searchCriteria = new ProblemSearchCriteria();
			ProblemSearchPanel.this.searchCriteria
			.setProjectId(new NumberSearchField(SearchField.AND,
					ProblemSearchPanel.this.project.getId()));

			ProblemSearchPanel.this.searchCriteria
			.setProblemname(new StringSearchField(this.nameField
					.getValue().toString().trim()));

			if (this.myItemCheckbox.getValue()) {
				ProblemSearchPanel.this.searchCriteria
				.setAssignToUser(new StringSearchField(SearchField.AND,
						AppContext.getUsername()));
			} else {
				ProblemSearchPanel.this.searchCriteria.setAssignToUser(null);
			}
			return ProblemSearchPanel.this.searchCriteria;
		}

		@Override
		public ComponentContainer constructHeader() {
			Image titleIcon = new Image(null,
					MyCollabResource.newResource("icons/22/project/problem_selected.png"));
			Label headerText = new Label("Problem List");

			final Button createBtn = new Button(
					LocalizationHelper.getMessage(ProblemI18nEnum.NEW_PROBLEM_ACTION),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new ProblemEvent.GotoAdd(this, null));
						}
					});
			createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			createBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			createBtn.setEnabled(CurrentProjectVariables
					.canWrite(ProjectRolePermissionCollections.PROBLEMS));

			HorizontalLayout header = new HorizontalLayout();
			headerText.setStyleName("hdr-text");

			UiUtils.addComponent(header, titleIcon, Alignment.MIDDLE_LEFT);
			UiUtils.addComponent(header, headerText, Alignment.MIDDLE_LEFT);
			UiUtils.addComponent(header, createBtn, Alignment.MIDDLE_RIGHT);
			header.setExpandRatio(headerText, 1.0f);

			header.setStyleName("hdr-view");
			header.setWidth("100%");
			header.setSpacing(true);
			header.setMargin(new MarginInfo(true, false, true, false));
			return header;
		}
	}
}
