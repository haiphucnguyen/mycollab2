package com.esofthead.mycollab.premium.module.project.view.problem;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.LabelLink;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.CheckBoxDecor;
import com.esofthead.mycollab.vaadin.ui.DefaultMassItemActionHandlersContainer;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class ProblemListViewImpl extends AbstractPageView implements
		ProblemListView {

	private static final long serialVersionUID = 1L;
	private final ProblemSearchPanel problemSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private DefaultPagedBeanTable<ProblemService, ProblemSearchCriteria, SimpleProblem> tableItem;
	private final VerticalLayout problemListLayout;
	private DefaultMassItemActionHandlersContainer tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();
	private static Logger log = LoggerFactory
			.getLogger(ProblemListViewImpl.class);

	public ProblemListViewImpl() {
		this.setMargin(new MarginInfo(false, true, false, true));

		this.problemSearchPanel = new ProblemSearchPanel();
		addComponent(this.problemSearchPanel);

		this.problemListLayout = new VerticalLayout();
		addComponent(this.problemListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new DefaultPagedBeanTable<ProblemService, ProblemSearchCriteria, SimpleProblem>(
				ApplicationContextUtil.getSpringBean(ProblemService.class),
				SimpleProblem.class, ProblemListView.VIEW_DEF_ID,
				ProblemTableFieldDef.selected, Arrays.asList(
						ProblemTableFieldDef.name,
						ProblemTableFieldDef.assignUser,
						ProblemTableFieldDef.datedue,
						ProblemTableFieldDef.rating));

		this.tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final CheckBoxDecor cb = new CheckBoxDecor("", false);
				cb.setImmediate(true);
				cb.addValueChangeListener(new ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						final SimpleProblem account = ProblemListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						ProblemListViewImpl.this.tableItem
								.fireSelectItemEvent(account);

					}
				});

				final SimpleProblem account = ProblemListViewImpl.this.tableItem
						.getBeanByIndex(itemId);
				account.setExtraData(cb);
				return cb;
			}
		});

		this.tableItem.addGeneratedColumn("issuename", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleProblem problem = ProblemListViewImpl.this.tableItem
						.getBeanByIndex(itemId);
				final LabelLink b = new LabelLink(problem.getIssuename(),
						ProjectLinkBuilder.generateProblemPreviewFullLink(
								problem.getProjectid(), problem.getId()));

				if ("Closed".equals(problem.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (problem.getDatedue() != null
							&& (problem.getDatedue()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
				b.setDescription(generateToolTip(problem));
				return b;

			}
		});

		this.tableItem.addGeneratedColumn("assignedUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleProblem problem = ProblemListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						return new ProjectUserLink(problem.getAssigntouser(),
								problem.getAssignUserAvatarId(), problem
										.getAssignedUserFullName(), true, true);

					}
				});

		this.tableItem.addGeneratedColumn("raisedByUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleProblem problem = ProblemListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						return new ProjectUserLink(problem.getAssigntouser(),
								problem.getRaisedByUserAvatarId(), problem
										.getRaisedByUserFullName(), true, true);

					}
				});

		this.tableItem.addGeneratedColumn("datedue", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleProblem item = ProblemListViewImpl.this.tableItem
						.getBeanByIndex(itemId);
				final Label l = new Label();
				l.setValue(AppContext.formatDate(item.getDatedue()));
				return l;
			}
		});

		this.tableItem.addGeneratedColumn("level", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleProblem item = ProblemListViewImpl.this.tableItem
						.getBeanByIndex(itemId);
				final RatingStars tinyRs = new RatingStars();
				tinyRs.setValue(item.getLevel());
				tinyRs.setStyleName("tiny");
				tinyRs.setReadOnly(true);
				return tinyRs;
			}
		});

		this.tableItem.setWidth("100%");

		this.problemListLayout
				.addComponent(this.constructTableActionControls());
		this.problemListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<ProblemSearchCriteria> getSearchHandlers() {
		return this.problemSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		this.selectOptionButton.setWidth(Sizeable.SIZE_UNDEFINED, Unit.PIXELS);
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_DELETE_LABEL));
		deleteBtn.setEnabled(CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.PROBLEMS));

		this.tableActionControls = new DefaultMassItemActionHandlersContainer();

		if (CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.PROBLEMS)) {
			tableActionControls.addActionItem(
					MassItemActionHandler.DELETE_ACTION,
					MyCollabResource.newResource("icons/16/action/delete.png"),
					"delete",
					AppContext.getMessage(GenericI18Enum.BUTTON_DELETE_LABEL));
		}

		tableActionControls.addActionItem(MassItemActionHandler.MAIL_ACTION,
				MyCollabResource.newResource("icons/16/action/mail.png"),
				"mail", AppContext.getMessage(GenericI18Enum.BUTTON_MAIL));

		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_PDF_ACTION,
				MyCollabResource.newResource("icons/16/action/pdf.png"),
				"export", "export.pdf",
				AppContext.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));

		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_EXCEL_ACTION,
				MyCollabResource.newResource("icons/16/action/excel.png"),
				"export", "export.xlsx",
				AppContext.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));

		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_CSV_ACTION,
				MyCollabResource.newResource("icons/16/action/csv.png"),
				"export", "export.csv",
				AppContext.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));

		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PROBLEMS)) {
			tableActionControls.addActionItem(
					MassItemActionHandler.MASS_UPDATE_ACTION, MyCollabResource
							.newResource("icons/16/action/massupdate.png"),
					"update", AppContext
							.getMessage(GenericI18Enum.MASS_UPDATE_TOOLTIP));
		}

		this.tableActionControls.setVisible(false);
		this.tableActionControls.setWidth(Sizeable.SIZE_UNDEFINED, Unit.PIXELS);

		layout.addComponent(this.tableActionControls);
		this.selectedItemsNumberLabel.setWidth("100%");
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		layout.setExpandRatio(selectedItemsNumberLabel, 1.0f);

		Button customizeViewBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent().addWindow(
						new ProblemListCustomizeWindow(
								ProblemListView.VIEW_DEF_ID, tableItem));

			}
		});
		customizeViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/customize.png"));
		customizeViewBtn.setDescription("Layout Options");
		customizeViewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		customizeViewBtn.addStyleName(UIConstants.THEME_SMALL_PADDING);
		layout.addComponent(customizeViewBtn);
		layout.setComponentAlignment(customizeViewBtn, Alignment.MIDDLE_RIGHT);

		return layoutWrapper;
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue("Selected: "
				+ numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public HasMassItemActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleProblem> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<ProblemSearchCriteria, SimpleProblem> getPagedBeanTable() {
		return this.tableItem;
	}

	private String generateToolTip(SimpleProblem problem) {
		try {
			Div div = new Div();
			H3 problemName = new H3();
			problemName.appendText(problem.getIssuename());
			div.appendChild(problemName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:11px;");

			Tr trRow5 = new Tr();
			Td trRow5_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.trimHtmlTags(problem
									.getDescription()));
			trRow5_value.setAttribute("colspan", "3");

			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow5_value);

			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Raised by:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(problem.getRaisedbyuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	problem.getRaisedbyuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					problem.getRaisedByUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(problem
																			.getRaisedByUserFullName()))));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Impact:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											StringUtils
													.getStringFieldValue(problem
															.getImpact())));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(problem.getRaisedbyuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	problem.getRaisedbyuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					problem.getRaisedByUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(problem
																			.getRaisedByUserFullName()))));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Priority:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											StringUtils
													.getStringFieldValue(problem
															.getPriority())));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Date due:")).appendChild(
					new Td().appendText(AppContext.formatDate(problem
							.getDatedue())));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Rating:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(problem
							.getLevel())));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Status:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(problem
							.getStatus())));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Related to:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(""));

			Tr trRow6 = new Tr();
			Td trRow6_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.trimHtmlTags(problem
									.getResolution()));
			trRow6_value.setAttribute("colspan", "3");

			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Resolution:")).appendChild(
					trRow6_value);

			table.appendChild(trRow5);
			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow6);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error("Error while generate tooltip for Problem", e);
			return "";
		}
	}
}
