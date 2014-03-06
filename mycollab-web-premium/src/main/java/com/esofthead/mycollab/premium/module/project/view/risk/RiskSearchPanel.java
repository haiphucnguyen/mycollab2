package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.MyCollabSession;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class RiskSearchPanel extends GenericSearchPanel<RiskSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private final SimpleProject project;
	protected RiskSearchCriteria searchCriteria;

	public RiskSearchPanel() {
		this.project = (SimpleProject) MyCollabSession.getVariable("project");
	}

	@Override
	public void attach() {
		super.attach();
		this.createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new RiskBasicSearchLayout());
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
					RiskBasicSearchLayout.this.callSearchAction();
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
					RiskBasicSearchLayout.this.nameField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			RiskSearchPanel.this.searchCriteria = new RiskSearchCriteria();
			RiskSearchPanel.this.searchCriteria
					.setProjectId(new NumberSearchField(SearchField.AND,
							RiskSearchPanel.this.project.getId()));
			RiskSearchPanel.this.searchCriteria
					.setRiskname(new StringSearchField(this.nameField
							.getValue().toString().trim()));

			if (this.myItemCheckbox.getValue()) {
				RiskSearchPanel.this.searchCriteria
						.setAssignToUser(new StringSearchField(SearchField.AND,
								AppContext.getUsername()));
			} else {
				RiskSearchPanel.this.searchCriteria.setAssignToUser(null);
			}
			return RiskSearchPanel.this.searchCriteria;
		}
	}

}
