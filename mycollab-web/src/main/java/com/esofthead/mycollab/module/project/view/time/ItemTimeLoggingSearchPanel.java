package com.esofthead.mycollab.module.project.view.time;

import java.util.Collection;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.localization.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.view.user.ProjectMemberListSelect;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.ui.DateRangeField;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Reindeer;

public class ItemTimeLoggingSearchPanel extends
		GenericSearchPanel<ItemTimeLoggingSearchCriteria> {

	private static final long serialVersionUID = 1L;
	protected ItemTimeLoggingSearchCriteria searchCriteria;

	@Override
	public void attach() {
		super.attach();
		createAdvancedSearchLayout();
	}

	private void createAdvancedSearchLayout() {
		BugAdvancedSearchLayout layout = new BugAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label(
				LocalizationHelper
						.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE));
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		return layout;
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class BugAdvancedSearchLayout extends AdvancedSearchLayout {

		private DateRangeField dateRangeField;

		private ProjectMemberListSelect userField;

		@SuppressWarnings("unchecked")
		public BugAdvancedSearchLayout() {
			super(ItemTimeLoggingSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(2, 2,
					"300px", "100px");

			int dateFieldWidth = 140;
			String nameFieldWidth = "300px";

			if (ScreenSize.hasSupport1024Pixels()) {
				gridLayout = new GridFormLayoutHelper(2, 2, "250px", "100px");
				dateFieldWidth = 120;
				nameFieldWidth = "200px";
			}

			gridLayout.getLayout().setWidth("100%");

			dateRangeField = (DateRangeField) gridLayout.addComponent(
					new DateRangeField(new HorizontalLayout(), dateFieldWidth),
					null, 0, 0);
			dateRangeField.setDateFormat(AppContext.getDateFormat());

			userField = (ProjectMemberListSelect) gridLayout.addComponent(
					new ProjectMemberListSelect(), "User", 1, 0);
			userField.setWidth(nameFieldWidth);

			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);

			Button searchBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_SEARCH_LABEL),
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							BugAdvancedSearchLayout.this.callSearchAction();
						}
					});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

			Button clearBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_CLEAR_LABEL),
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							userField.setValue(null);
							dateRangeField.setDefaultValue();
						}
					});
			clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(clearBtn);

			return buttonControls;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			searchCriteria = new ItemTimeLoggingSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(
					CurrentProjectVariables.getProjectId()));

			searchCriteria.setRangeDate(dateRangeField
					.getRangeSearchValue());

			Collection<String> types = (Collection<String>) userField
					.getValue();

			if (types != null && types.size() > 0) {
				searchCriteria.setLogUsers(new SetSearchField(
						SearchField.AND, types));
			}

			return searchCriteria;
		}
	}
}
