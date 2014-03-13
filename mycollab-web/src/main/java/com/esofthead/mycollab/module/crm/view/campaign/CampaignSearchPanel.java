/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.db.query.Param;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserListSelect;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.DynamicQueryParamLayout;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 * 
 * @author MyCollab Ltd
 * @since 1.0
 * 
 */
public class CampaignSearchPanel extends
		DefaultGenericSearchPanel<CampaignSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private static Param[] paramFields = new Param[] {
			CampaignSearchCriteria.p_campaignName,
			CampaignSearchCriteria.p_startDate,
			CampaignSearchCriteria.p_endDate,
			CampaignSearchCriteria.p_createdtime,
			CampaignSearchCriteria.p_lastUpdatedTime,
			CampaignSearchCriteria.p_types, CampaignSearchCriteria.p_statuses,
			CampaignSearchCriteria.p_assignee };

	protected CampaignSearchCriteria searchCriteria;

	public CampaignSearchPanel() {
		this.searchCriteria = new CampaignSearchCriteria();
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(true);

		final Image titleIcon = new Image(null,
				MyCollabResource.newResource("icons/22/crm/campaign.png"));
		layout.addComponent(titleIcon);
		layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

		final Label searchtitle = new Label("Campaigns");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setExpandRatio(searchtitle, 1.0f);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		final Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoAdd(this, null));

					}
				});
		createAccountBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		createAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@Override
	protected BasicSearchLayout<CampaignSearchCriteria> createBasicSearchLayout() {
		return new CampaignBasicSearchLayout();
	}

	@Override
	protected SearchLayout<CampaignSearchCriteria> createAdvancedSearchLayout() {
		return new CampaignAdvancedSearchLayout();
	}

	private class CampaignBasicSearchLayout extends
			BasicSearchLayout<CampaignSearchCriteria> {
		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		public CampaignBasicSearchLayout() {
			super(CampaignSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return CampaignSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.setMargin(true);
			this.nameField = this.createSeachSupportTextField(new TextField(),
					"NameFieldOfBasicSearch");

			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button("Search");
			searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search.png"));
			searchBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					CampaignBasicSearchLayout.this.callSearchAction();
				}
			});
			UiUtils.addComponent(basicSearchBody, searchBtn, Alignment.MIDDLE_LEFT);

			this.myItemCheckbox = new CheckBox(
					LocalizationHelper
							.getMessage(GenericI18Enum.SEARCH_MYITEMS_CHECKBOX));
			this.myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Separator separator1 = new Separator();

			UiUtils.addComponent(basicSearchBody, separator1, Alignment.MIDDLE_LEFT);

			final Button cancelBtn = new Button(
					LocalizationHelper.getMessage(GenericI18Enum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					CampaignBasicSearchLayout.this.nameField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn, Alignment.MIDDLE_CENTER);


			final Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							CampaignSearchPanel.this
									.moveToAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}

		@Override
		protected CampaignSearchCriteria fillupSearchCriteria() {
			CampaignSearchPanel.this.searchCriteria = new CampaignSearchCriteria();
			CampaignSearchPanel.this.searchCriteria
					.setSaccountid(new NumberSearchField(SearchField.AND,
							AppContext.getAccountId()));

			if (StringUtils.isNotNullOrEmpty(this.nameField.getValue()
					.toString().trim())) {
				CampaignSearchPanel.this.searchCriteria
						.setCampaignName(new StringSearchField(SearchField.AND,
								(String) this.nameField.getValue()));
			}

			if (this.myItemCheckbox.getValue()) {
				CampaignSearchPanel.this.searchCriteria
						.setAssignUsers(new SetSearchField<String>(
								SearchField.AND, new String[] { AppContext
										.getUsername() }));
			} else {
				CampaignSearchPanel.this.searchCriteria.setAssignUsers(null);
			}
			return CampaignSearchPanel.this.searchCriteria;
		}
	}

	private class CampaignAdvancedSearchLayout extends
			DynamicQueryParamLayout<CampaignSearchCriteria> {
		private static final long serialVersionUID = 1L;

		public CampaignAdvancedSearchLayout() {
			super(CampaignSearchPanel.this, CrmTypeConstants.CAMPAIGN);
		}

		@Override
		public ComponentContainer constructHeader() {
			return CampaignSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public Param[] getParamFields() {
			return paramFields;
		}

		@Override
		protected Class<CampaignSearchCriteria> getType() {
			return CampaignSearchCriteria.class;
		}

		protected Component buildSelectionComp(String fieldId) {
			if ("campaign-assignuser".equals(fieldId)) {
				return new ActiveUserListSelect();
			}
			return null;
		}
	}
}
