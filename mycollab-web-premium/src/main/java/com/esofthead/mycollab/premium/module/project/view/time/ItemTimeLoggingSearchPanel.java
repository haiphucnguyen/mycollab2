package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Collection;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.localization.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.view.user.ProjectMemberListSelect;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.resource.ui.DateRangeField;
import com.esofthead.mycollab.vaadin.resource.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.resource.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.resource.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class ItemTimeLoggingSearchPanel extends
		GenericSearchPanel<ItemTimeLoggingSearchCriteria> {

	private static final long serialVersionUID = 1L;
	protected ItemTimeLoggingSearchCriteria searchCriteria;

	@Override
	public void attach() {
		super.attach();
		this.createAdvancedSearchLayout();
	}

	private void createAdvancedSearchLayout() {
		final TimeLoggingAdvancedSearchLayout layout = new TimeLoggingAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(true);

		final Image titleIcon = new Image(null, MyCollabResource
				.newResource("icons/24/project/time.png"));
		layout.addComponent(titleIcon);

		final Label searchtitle = new Label(
				LocalizationHelper
						.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE));
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);
		layout.setExpandRatio(searchtitle, 1.0f);
		return layout;
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class TimeLoggingAdvancedSearchLayout extends AdvancedSearchLayout {

		private DateRangeField dateRangeField;

		private ProjectMemberListSelect userField;

		@SuppressWarnings("unchecked")
		public TimeLoggingAdvancedSearchLayout() {
			super(ItemTimeLoggingSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return ItemTimeLoggingSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(2, 2,
					"300px", "100px");

			String nameFieldWidth = "300px";

			gridLayout.getLayout().setWidth("100%");
			gridLayout.getLayout().setSpacing(true);

			this.dateRangeField = (DateRangeField) gridLayout.addComponent(
					new DateRangeField(), null, 0, 0);
			this.dateRangeField.setDateFormat(AppContext.getDateFormat());

			this.userField = (ProjectMemberListSelect) gridLayout.addComponent(
					new ProjectMemberListSelect(), "User", 1, 0);
			this.userField.setWidth(nameFieldWidth);

			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			final HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);

			final Button searchBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_SEARCH_LABEL),
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							TimeLoggingAdvancedSearchLayout.this
									.callSearchAction();
						}
					});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

			final Button clearBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_CLEAR_LABEL),
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							TimeLoggingAdvancedSearchLayout.this.userField
									.setValue(null);
							TimeLoggingAdvancedSearchLayout.this.dateRangeField
									.setDefaultValue();
						}
					});
			clearBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			buttonControls.addComponent(clearBtn);

			return buttonControls;
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
