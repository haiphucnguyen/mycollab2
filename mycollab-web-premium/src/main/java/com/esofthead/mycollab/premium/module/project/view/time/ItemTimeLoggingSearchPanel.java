package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Collection;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DateRangeField;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd
 * @since 2.0
 * 
 */
class ItemTimeLoggingSearchPanel extends
		GenericSearchPanel<ItemTimeLoggingSearchCriteria> {

	private static final long serialVersionUID = 1L;
	protected ItemTimeLoggingSearchCriteria searchCriteria;
	private final TimeLoggingAdvancedSearchLayout layout;

	public ItemTimeLoggingSearchPanel() {
		layout = new TimeLoggingAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	public void addClickListener(Button.ClickListener listener) {
		layout.createBtn.addClickListener(listener);
	}

	public void addComponent(Component c, int index) {
		layout.bodyWrap.addComponent(c, index);
	}

	public void removeComponent(Component c) {
		layout.bodyWrap.removeComponent(c);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class TimeLoggingAdvancedSearchLayout extends AdvancedSearchLayout {

		private DateRangeField dateRangeField;

		private ProjectMemberListSelect userField;
		private HorizontalLayout buttonControls;
		private Button createBtn;
		private VerticalLayout bodyWrap;

		@SuppressWarnings("unchecked")
		public TimeLoggingAdvancedSearchLayout() {
			super(ItemTimeLoggingSearchPanel.this);
			this.setStyleName("time-tracking-logging");
		}

		@Override
		public ComponentContainer constructHeader() {
			Image titleIcon = new Image(null,
					MyCollabResource
							.newResource("icons/22/project/time_selected.png"));
			Label headerText = new Label(
					AppContext
							.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE));

			createBtn = new Button(
					AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME));
			createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			createBtn.setIcon(MyCollabResource
					.newResource("icons/16/project/add_time.png"));
			createBtn.setEnabled(!CurrentProjectVariables.isProjectArchived());
			createBtn.addStyleName("v-button-caption-bool");

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

		@Override
		public ComponentContainer constructBody() {
			bodyWrap = new VerticalLayout();

			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 1,
					"350px", "50px");

			String nameFieldWidth = "300px";

			gridLayout.getLayout().setWidth("100%");
			gridLayout.getLayout().setSpacing(true);
			gridLayout.getLayout().setMargin(true);

			this.dateRangeField = (DateRangeField) gridLayout.addComponent(
					new DateRangeField(), null, 0, 0);
			this.dateRangeField.setDateFormat(AppContext.getDateFormat());

			this.userField = (ProjectMemberListSelect) gridLayout.addComponent(
					new ProjectMemberListSelect(), "User", 1, 0);
			this.userField.setWidth(nameFieldWidth);

			buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);

			final Button searchBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH_LABEL),
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							TimeLoggingAdvancedSearchLayout.this
									.callSearchAction();
						}
					});

			UiUtils.addComponent(buttonControls, searchBtn,
					Alignment.MIDDLE_LEFT);
			searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search.png"));

			final Button clearBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR_LABEL),
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							TimeLoggingAdvancedSearchLayout.this.userField
									.setValue(null);
							TimeLoggingAdvancedSearchLayout.this.dateRangeField
									.setDefaultValue();
						}
					});
			clearBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
			UiUtils.addComponent(buttonControls, clearBtn,
					Alignment.MIDDLE_LEFT);
			buttonControls.setExpandRatio(clearBtn, 1.0f);

			gridLayout.addComponent(buttonControls, null, 2, 0);

			bodyWrap.addComponent(gridLayout.getLayout());

			return bodyWrap;
		}

		@Override
		public ComponentContainer constructFooter() {
			CssLayout c = new CssLayout();
			c.setStyleName("empty-compnent");
			return c;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			ItemTimeLoggingSearchPanel.this.searchCriteria = new ItemTimeLoggingSearchCriteria();
			ItemTimeLoggingSearchPanel.this.searchCriteria
					.setProjectId(new NumberSearchField(CurrentProjectVariables
							.getProjectId()));

			ItemTimeLoggingSearchPanel.this.searchCriteria
					.setRangeDate(this.dateRangeField.getRangeSearchValue());

			final Collection<String> types = (Collection<String>) this.userField
					.getValue();

			if (types != null && types.size() > 0) {
				ItemTimeLoggingSearchPanel.this.searchCriteria
						.setLogUsers(new SetSearchField(SearchField.AND, types));
			}

			return ItemTimeLoggingSearchPanel.this.searchCriteria;
		}
	}
}
