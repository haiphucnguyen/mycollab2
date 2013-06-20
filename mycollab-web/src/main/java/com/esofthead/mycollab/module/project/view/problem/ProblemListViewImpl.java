package com.esofthead.mycollab.module.project.view.problem;

import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.PopupButtonControl;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
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

@ViewComponent
public class ProblemListViewImpl extends AbstractView implements
		ProblemListView {

	private static final long serialVersionUID = 1L;
	private final ProblemSearchPanel problemSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private PagedBeanTable2<ProblemService, ProblemSearchCriteria, SimpleProblem> tableItem;
	private final VerticalLayout problemListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public ProblemListViewImpl() {
		this.setSpacing(true);
		this.setMargin(false, true, true, true);

		problemSearchPanel = new ProblemSearchPanel();
		this.addComponent(problemSearchPanel);

		problemListLayout = new VerticalLayout();
		problemListLayout.setSpacing(true);
		this.addComponent(problemListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<ProblemService, ProblemSearchCriteria, SimpleProblem>(
				AppContext.getSpringBean(ProblemService.class),
				SimpleProblem.class, new String[] { "selected", "issuename",
						"assignedUserFullName", "datedue", "level" },
				new String[] { "", "Name", "Assigned to", "Due Date", "Level" });

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
						SimpleProblem account = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(account);

					}
				});

				SimpleProblem account = tableItem.getBeanByIndex(itemId);
				account.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("issuename", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleProblem problem = tableItem.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(problem.getIssuename(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new ProblemEvent.GotoRead(this, problem
												.getId()));
							}
						});

				if ("Closed".equals(problem.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (problem.getDatedue() != null
							&& (problem.getDatedue()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
				return b;

			}
		});

		tableItem.addGeneratedColumn("assignedUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleProblem problem = tableItem
								.getBeanByIndex(itemId);
						return new ProjectUserLink(problem.getAssigntouser(),
								problem.getAssignUserAvatarId(), problem
										.getAssignedUserFullName(), true, true);

					}
				});

		tableItem.addGeneratedColumn("datedue", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleProblem item = tableItem.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(AppContext.formatDate(item.getDatedue()));
				return l;
			}
		});

		tableItem.addGeneratedColumn("level", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleProblem item = tableItem.getBeanByIndex(itemId);
				RatingStars tinyRs = new RatingStars();
				tinyRs.setValue(item.getLevel());
				tinyRs.setStyleName("tiny");
				tinyRs.setReadOnly(true);
				return tinyRs;
			}
		});

		tableItem.setColumnExpandRatio("issuename", 1);
		tableItem.setColumnWidth("assignedUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("level", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("datedue", UIConstants.TABLE_DATE_WIDTH);

		tableItem.setWidth("100%");

		problemListLayout.addComponent(constructTableActionControls());
		problemListLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<ProblemSearchCriteria> getSearchHandlers() {
		return problemSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		Button deleteBtn = new Button("Delete");
		deleteBtn.setEnabled(CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.PROBLEMS));

		tableActionControls = new PopupButtonControl("delete", deleteBtn);
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");
		tableActionControls.addOptionItem("massUpdate", "Mass update");
		tableActionControls.setVisible(false);

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
	public HasSelectableItemHandlers<SimpleProblem> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<ProblemSearchCriteria, SimpleProblem> getPagedBeanTable() {
		return tableItem;
	}
}
