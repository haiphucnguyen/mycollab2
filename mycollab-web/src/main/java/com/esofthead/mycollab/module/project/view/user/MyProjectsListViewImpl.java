package com.esofthead.mycollab.module.project.view.user;

import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.components.ProjectSearchPanel;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class MyProjectsListViewImpl extends AbstractView implements
		MyProjectsListView {

	private PagedBeanTable2<ProjectService, ProjectSearchCriteria, SimpleProject> tableItem;
	private ProjectSearchPanel searchPanel;
	private VerticalLayout listLayout;
	private SelectionOptionButton selectOptionButton;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public MyProjectsListViewImpl() {
		this.setSpacing(true);
		this.setMargin(true);

		searchPanel = new ProjectSearchPanel();
		searchPanel
				.addSearchHandler(new SearchHandler<ProjectSearchCriteria>() {

					@Override
					public void onSearch(ProjectSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}
				});

		this.addComponent(searchPanel);

		listLayout = new VerticalLayout();
		listLayout.setSpacing(true);
		this.addComponent(listLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<ProjectService, ProjectSearchCriteria, SimpleProject>(
				AppContext.getSpringBean(ProjectService.class),
				SimpleProject.class,
				new String[] { "selected", "name", "shortname",
						"actualstartdate", "projectstatus" },
				new String[] { "", "Name", "Short Name", "Start Date", "Status" });

		tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						final SimpleProject project = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(project);

					}
				});

				SimpleProject project = tableItem.getBeanByIndex(itemId);
				project.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("name", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleProject project = tableItem.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(project.getName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance()
										.fireEvent(
												new ProjectEvent.GotoMyProject(
														this,
														new PageActionChain(
																new ProjectScreenData.Goto(
																		project.getId()))));
							}
						});
				b.addStyleName("medium-text");
				if ("Closed".equals(project.getProjectstatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if ((project.getPlanenddate() != null && (project
							.getPlanenddate().before(new GregorianCalendar()
							.getTime())))
							|| (project.getActualenddate() != null && (project
									.getActualenddate()
									.before(new GregorianCalendar().getTime())))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
				return b;

			}
		});

		tableItem.addGeneratedColumn("actualstartdate", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleProject project = tableItem.getBeanByIndex(itemId);
				return new Label(AppContext.formatDate(project
						.getActualstartdate()));

			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("name", 1f);
		tableItem.setColumnWidth("shortname", UIConstants.TABLE_S_LABEL_WIDTH);
		tableItem.setColumnWidth("actualstartdate",
				UIConstants.TABLE_DATE_WIDTH);
		tableItem.setColumnWidth("projectstatus",
				UIConstants.TABLE_M_LABEL_WIDTH);

		listLayout.addComponent(constructTableActionControls());
		listLayout.addComponent(tableItem);
	}

	@Override
	public IPagedBeanTable<ProjectSearchCriteria, SimpleProject> getPagedBeanTable() {
		return tableItem;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("export", "Export");

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setVisible(true);
		selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setVisible(false);
		selectOptionButton.setSelectedChecbox(false);
		selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleProject> getSelectableItemHandlers() {
		return tableItem;
	}
}
