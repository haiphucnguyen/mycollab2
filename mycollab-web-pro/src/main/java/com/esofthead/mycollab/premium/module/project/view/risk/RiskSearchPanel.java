package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.ui.components.ProjectViewHeader;
import com.vaadin.server.FontAwesome;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import com.esofthead.mycollab.common.MyCollabSession;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.db.query.Param;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.DynamicQueryParamLayout;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class RiskSearchPanel extends
		DefaultGenericSearchPanel<RiskSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private final SimpleProject project;

	private static Param[] paramFields = new Param[] {
			RiskSearchCriteria.p_assignee, RiskSearchCriteria.p_raisedUser,
			RiskSearchCriteria.p_status, RiskSearchCriteria.p_probalitity,
			RiskSearchCriteria.p_consequence, RiskSearchCriteria.p_duedate,
			RiskSearchCriteria.p_raiseddate, RiskSearchCriteria.p_createdtime,
			RiskSearchCriteria.p_lastupdatedtime };

	protected RiskSearchCriteria searchCriteria;

	public RiskSearchPanel() {
		this.project = (SimpleProject) MyCollabSession.getVariable("project");
	}

	private HorizontalLayout createSearchTopPanel() {
		Label headerText = new ProjectViewHeader(ProjectTypeConstants.RISK,
				AppContext.getMessage(RiskI18nEnum.VIEW_LIST_TITLE));
		headerText.setStyleName(UIConstants.HEADER_TEXT);

		final Button createBtn = new Button(
				AppContext.getMessage(RiskI18nEnum.BUTTON_NEW_RISK),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBusFactory.getInstance().post(
								new RiskEvent.GotoAdd(this, null));
					}
				});
		createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		createBtn.setIcon(FontAwesome.PLUS);
		createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.RISKS));

		MHorizontalLayout header = new MHorizontalLayout()
				.withStyleName(UIConstants.HEADER_VIEW).withWidth("100%")
				.withSpacing(true)
				.withMargin(new MarginInfo(true, false, true, false));

		header.with(headerText, createBtn)
				.withAlign(headerText, Alignment.MIDDLE_LEFT)
				.withAlign(createBtn, Alignment.MIDDLE_RIGHT)
				.expand(headerText);

		return header;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SearchLayout<RiskSearchCriteria> createBasicSearchLayout() {
		return new RiskBasicSearchLayout();
	}

	@Override
	protected SearchLayout<RiskSearchCriteria> createAdvancedSearchLayout() {
		return new RiskAdvancedSearchLayout();
	}

	@SuppressWarnings("rawtypes")
	private class RiskBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public RiskBasicSearchLayout() {
			super(RiskSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructBody() {
			final MHorizontalLayout basicSearchBody = new MHorizontalLayout()
					.withSpacing(true).withMargin(true);

			this.nameField = this.createSeachSupportTextField(new TextField(),
					"NameFieldOfBasicSearch");
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			basicSearchBody.with(nameField).withAlign(nameField,
					Alignment.MIDDLE_CENTER);

			this.myItemCheckbox = new CheckBox(
					AppContext
							.getMessage(GenericI18Enum.SEARCH_MYITEMS_CHECKBOX));
			this.myItemCheckbox.setWidth("75px");
			basicSearchBody.with(myItemCheckbox).withAlign(myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
			searchBtn.setIcon(FontAwesome.SEARCH);
			searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			searchBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					RiskBasicSearchLayout.this.callSearchAction();
				}
			});
			basicSearchBody.with(searchBtn).withAlign(searchBtn,
					Alignment.MIDDLE_LEFT);

			final Button cancelBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					RiskBasicSearchLayout.this.nameField.setValue("");
				}
			});
			basicSearchBody.with(cancelBtn).withAlign(cancelBtn,
					Alignment.MIDDLE_CENTER);

			final Button advancedSearchBtn = new Button(
					AppContext
							.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							RiskSearchPanel.this.moveToAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			basicSearchBody.with(advancedSearchBtn).withAlign(
					advancedSearchBtn, Alignment.MIDDLE_CENTER);

			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			searchCriteria = new RiskSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
					project.getId()));
			searchCriteria.setRiskname(new StringSearchField(this.nameField.getValue().trim()));

			if (this.myItemCheckbox.getValue()) {
				searchCriteria.setAssignToUser(new StringSearchField(
						SearchField.AND, AppContext.getUsername()));
			} else {
				searchCriteria.setAssignToUser(null);
			}
			return searchCriteria;
		}

		@Override
		public ComponentContainer constructHeader() {
			return RiskSearchPanel.this.createSearchTopPanel();
		}
	}

	private class RiskAdvancedSearchLayout extends
			DynamicQueryParamLayout<RiskSearchCriteria> {
		private static final long serialVersionUID = 1L;

		public RiskAdvancedSearchLayout() {
			super(RiskSearchPanel.this, ProjectTypeConstants.RISK);
		}

		@Override
		public ComponentContainer constructHeader() {
			return RiskSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public Param[] getParamFields() {
			return paramFields;
		}

		@Override
		protected Class<RiskSearchCriteria> getType() {
			return RiskSearchCriteria.class;
		}

		@Override
		protected Component buildSelectionComp(String fieldId) {
			if ("risk-assignuser".equals(fieldId)) {
				return new ProjectMemberListSelect();
			} else if ("risk-raiseduser".equals(fieldId)) {
				return new ProjectMemberListSelect();
			}
			return null;
		}

		@Override
		protected RiskSearchCriteria fillupSearchCriteria() {
			searchCriteria = super.fillupSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
					project.getId()));
			return searchCriteria;
		}

	}
}
