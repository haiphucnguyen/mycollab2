package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.core.MyCollabThread;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class EventSearchPanel extends GenericSearchPanel<EventSearchCriteria> {

	private static final long serialVersionUID = 1L;
	protected EventSearchCriteria searchCriteria;

	@Override
	public void attach() {
		super.attach();
		this.createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		final EventBasicSearchLayout layout = new EventBasicSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Embedded iconComp = new Embedded();
		iconComp.setSource(MyCollabResource
				.newResource("icons/22/crm/event.png"));
		layout.addComponent(iconComp);
		final Label searchtitle = new Label("Events");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);
		layout.setExpandRatio(searchtitle, 1.0f);

		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CALL)
				|| AppContext.canWrite(RolePermissionCollections.CRM_MEETING));
		controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlsBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		controlsBtn.setCaption("Create Task");
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					@Override
					public void splitButtonClick(
							final SplitButton.SplitButtonClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.TaskAdd(this, null));
					}
				});

		final VerticalLayout btnControlsLayout = new VerticalLayout();
		btnControlsLayout.setWidth("150px");
		controlsBtn.addComponent(btnControlsLayout);

		final Button createMeetingBtn = new Button("Create Meeting",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final Button.ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.MeetingAdd(this, null));
					}
				});
		createMeetingBtn.setStyleName("link");
		btnControlsLayout.addComponent(createMeetingBtn);
		createMeetingBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_MEETING));
		final Button createCallBtn = new Button("Create Call",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final Button.ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.CallAdd(this, null));
					}
				});
		createCallBtn.setStyleName("link");
		createCallBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CALL));
		btnControlsLayout.addComponent(createCallBtn);
		UiUtils.addComponent(layout, controlsBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class EventBasicSearchLayout extends BasicSearchLayout {

		private TextField nameField;
		private CheckBox myItemCheckbox;

		@SuppressWarnings("unchecked")
		public EventBasicSearchLayout() {
			super(EventSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return EventSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			// basicSearchBody.setSpacing(true);
			// basicSearchBody.addComponent(new Label("Subject"));

			this.nameField = new TextField();
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));

			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final Button.ClickEvent event) {
					EventBasicSearchLayout.this.callSearchAction();
				}
			});
			UiUtils.addComponent(basicSearchBody, searchBtn,
					Alignment.MIDDLE_LEFT);

			this.myItemCheckbox = new CheckBox("My Items");
			this.myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button cancelBtn = new Button("Clear");
			cancelBtn.setStyleName(UIConstants.THEME_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.setWidth("55px");
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final Button.ClickEvent event) {
					EventBasicSearchLayout.this.nameField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			EventSearchPanel.this.searchCriteria = new EventSearchCriteria();
			EventSearchPanel.this.searchCriteria
					.setSaccountid(new NumberSearchField(SearchField.AND,
							AppContext.getAccountId()));
			return EventSearchPanel.this.searchCriteria;
		}
	}
}
