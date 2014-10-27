package com.esofthead.mycollab.module.project.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.FollowingTicketSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FollowingTicketSearchPanel extends
		DefaultGenericSearchPanel<FollowingTicketSearchCriteria> {

	private static final long serialVersionUID = 1L;

	private FollowingTicketBasicSearchLayout basicSearchLayout;

	private FollowingTicketSearchCriteria searchCriteria;

	private List<SimpleProject> projects;

	@Override
	@SuppressWarnings("unchecked")
	protected SearchLayout<FollowingTicketSearchCriteria> createBasicSearchLayout() {
		basicSearchLayout = new FollowingTicketBasicSearchLayout();
		return basicSearchLayout;
	}

	@Override
	protected SearchLayout<FollowingTicketSearchCriteria> createAdvancedSearchLayout() {
		return null;
	}

	public void doSearch() {
		basicSearchLayout.callSearchAction();
	}

	@SuppressWarnings("rawtypes")
	private class FollowingTicketBasicSearchLayout extends BasicSearchLayout {

		private static final long serialVersionUID = 1L;

		private UserInvolvedProjectsListSelect projectField;
		private TextField summaryField;

		@SuppressWarnings("unchecked")
		public FollowingTicketBasicSearchLayout() {
			super(FollowingTicketSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return new HorizontalLayout();
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.setMargin(true);

			final GridLayout selectionLayout = new GridLayout(3, 2);
			selectionLayout.setSpacing(true);
			selectionLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
			selectionLayout.setMargin(true);
			basicSearchBody.addComponent(selectionLayout);

			VerticalLayout summaryLbWrapper = new VerticalLayout();
			summaryLbWrapper.setWidth("70px");
			Label summaryLb = new Label("Summary:");
			summaryLb.setWidthUndefined();
			UiUtils.addComponent(summaryLbWrapper, summaryLb,
					Alignment.TOP_RIGHT);
			selectionLayout.addComponent(summaryLbWrapper, 0, 0);

			this.summaryField = new TextField();
			this.summaryField.setWidth("100%");
			selectionLayout.addComponent(this.summaryField, 1, 0);

			VerticalLayout projectLbWrapper = new VerticalLayout();
			projectLbWrapper.setWidth("70px");
			Label projectLb = new Label("Project:");
			projectLb.setWidthUndefined();
			UiUtils.addComponent(projectLbWrapper, projectLb,
					Alignment.TOP_RIGHT);
			selectionLayout.addComponent(projectLbWrapper, 0, 1);

			this.projectField = new UserInvolvedProjectsListSelect();
			this.projectField.setWidth("300px");
			this.projectField.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
			this.projectField.setNullSelectionAllowed(false);
			this.projectField.setMultiSelect(true);
			this.projectField.setRows(4);
			selectionLayout.addComponent(this.projectField, 1, 1);

			final Button queryBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_SUBMIT),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							FollowingTicketSearchPanel.this.doSearch();
						}
					});
			queryBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

			VerticalLayout queryBtnWrapper = new VerticalLayout();
			queryBtnWrapper.setWidth("100px");
			UiUtils.addComponent(queryBtnWrapper, queryBtn, Alignment.TOP_RIGHT);
			selectionLayout.addComponent(queryBtnWrapper, 2, 0);

			return basicSearchBody;
		}

		@Override
		@SuppressWarnings("unchecked")
		protected SearchCriteria fillupSearchCriteria() {
			searchCriteria = new FollowingTicketSearchCriteria();
			searchCriteria.setUser(new StringSearchField(AppContext
					.getUsername()));

			String summary = summaryField.getValue().toString().trim();
			searchCriteria.setSummary(new StringSearchField(StringUtils
					.isEmpty(summary) ? "" : summary));

			Collection<Integer> selectedProjects = (Collection<Integer>) projectField
					.getValue();
			if (CollectionUtils.isNotEmpty(selectedProjects)) {
				searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
						SearchField.AND, selectedProjects));
			} else {
				List<Integer> keys = new ArrayList<Integer>();
				for (SimpleProject project : projects) {
					keys.add(project.getId());
				}
				searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
						SearchField.AND, keys));
			}

			return searchCriteria;
		}
	}

	private class UserInvolvedProjectsListSelect extends ListSelect {
		private static final long serialVersionUID = 1L;

		public UserInvolvedProjectsListSelect() {
			FollowingTicketSearchPanel.this.projects = ApplicationContextUtil
					.getSpringBean(ProjectService.class)
					.getProjectsUserInvolved(AppContext.getUsername(),
							AppContext.getAccountId());

			for (SimpleProject project : projects) {
				this.addItem(project.getId());
				this.setItemCaption(project.getId(), project.getName());
			}
		}
	}
}
