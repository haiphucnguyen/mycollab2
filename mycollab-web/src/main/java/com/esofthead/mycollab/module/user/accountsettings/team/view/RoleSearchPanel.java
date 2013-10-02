/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.shell.view.ScreenSize;
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
public class RoleSearchPanel extends GenericSearchPanel<RoleSearchCriteria> {
	private static final long serialVersionUID = 1L;
	private RoleSearchCriteria searchCriteria;

	public RoleSearchPanel() {
		this.setCompositionRoot(new RoleBasicSearchLayout());
		if (ScreenSize.hasSupport1024Pixels()) {
			this.setWidth("750px");
		}
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		final Label searchtitle = new Label("Roles");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		final Button createBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new RoleEvent.GotoAdd(this, null));
					}
				});
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.USER_ROLE));

		UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class RoleBasicSearchLayout extends
			GenericSearchPanel.BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public RoleBasicSearchLayout() {
			super(RoleSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;

		@Override
		public ComponentContainer constructHeader() {
			return RoleSearchPanel.this.createSearchTopPanel();
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
					RoleBasicSearchLayout.this.callSearchAction();
				}
			});
			searchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			searchComp.addComponent(searchBtn);
			basicSearchBody.addComponent(searchComp);

			final Button clearBtn = new Button("Clear",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							RoleBasicSearchLayout.this.nameField.setValue("");
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
			RoleSearchPanel.this.searchCriteria = new RoleSearchCriteria();
			if (StringUtil.isNotNullOrEmpty((String) this.nameField.getValue())) {
				RoleSearchPanel.this.searchCriteria
						.setRoleName(new StringSearchField(SearchField.AND,
								(String) this.nameField.getValue()));
			}
			return RoleSearchPanel.this.searchCriteria;
		}
	}
}
