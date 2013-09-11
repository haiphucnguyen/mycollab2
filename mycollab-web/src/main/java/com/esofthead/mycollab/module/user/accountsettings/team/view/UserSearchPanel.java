/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 * 
 * @author haiphucnguyen
 */
public class UserSearchPanel extends GenericSearchPanel<UserSearchCriteria> {
	private static final long serialVersionUID = 1L;
	private UserSearchCriteria searchCriteria;

	public UserSearchPanel() {
		this.setCompositionRoot(new UserBasicSearchLayout());
		if (ScreenSize.hasSupport1024Pixels()) {
			this.setWidth("750px");
		}
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		final Label searchtitle = new Label("Users");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		final Button createBtn = new Button("Invite",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new UserEvent.GotoAdd(this, null));
					}
				});
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.USER_USER));

		UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class UserBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public UserBasicSearchLayout() {
			super(UserSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;

		@Override
		public ComponentContainer constructHeader() {
			return UserSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.addComponent(new Label("Name"));
			basicSearchBody.setSpacing(true);

			final HorizontalLayout searchComp = new HorizontalLayout();
			searchComp.addStyleName("search-comp");
			this.nameField = new TextField();
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			searchComp.addComponent(this.nameField);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));
			searchBtn.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final Button.ClickEvent event) {
					UserBasicSearchLayout.this.callSearchAction();
				}
			});
			searchComp.addComponent(searchBtn);
			basicSearchBody.addComponent(searchComp);

			final Button clearBtn = new Button("Clear",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							UserBasicSearchLayout.this.nameField.setValue("");
						}
					});
			clearBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			clearBtn.addStyleName("cancel-button");
			basicSearchBody.addComponent(clearBtn);
			basicSearchBody.setComponentAlignment(clearBtn,
					Alignment.MIDDLE_LEFT);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			UserSearchPanel.this.searchCriteria = new UserSearchCriteria();
			UserSearchPanel.this.searchCriteria
					.setSaccountid(new NumberSearchField(AppContext
							.getAccountId()));

			if (StringUtil.isNotNullOrEmpty((String) this.nameField.getValue())) {
				UserSearchPanel.this.searchCriteria
						.setDisplayName(new StringSearchField(SearchField.AND,
								(String) this.nameField.getValue()));
			}
			return UserSearchPanel.this.searchCriteria;
		}
	}
}
