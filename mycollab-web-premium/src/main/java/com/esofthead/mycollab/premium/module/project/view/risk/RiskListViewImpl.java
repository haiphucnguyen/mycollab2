package com.esofthead.mycollab.premium.module.project.view.risk;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.LabelLink;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.localization.RiskI18nEnum;
import com.esofthead.mycollab.module.project.service.RiskService;
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
import com.esofthead.mycollab.vaadin.ui.UiUtils;
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
public class RiskListViewImpl extends AbstractPageView implements RiskListView {

	private static final long serialVersionUID = 1L;
	private final RiskSearchPanel riskSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private DefaultPagedBeanTable<RiskService, RiskSearchCriteria, SimpleRisk> tableItem;
	private final VerticalLayout riskListLayout;
	private DefaultMassItemActionHandlersContainer tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();
	private static Logger log = LoggerFactory.getLogger(RiskListViewImpl.class);

	public RiskListViewImpl() {
		this.setMargin(new MarginInfo(false, true, false, true));

		this.riskSearchPanel = new RiskSearchPanel();
		this.addComponent(this.riskSearchPanel);

		this.riskListLayout = new VerticalLayout();
		this.addComponent(this.riskListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new DefaultPagedBeanTable<RiskService, RiskSearchCriteria, SimpleRisk>(
				ApplicationContextUtil.getSpringBean(RiskService.class),
				SimpleRisk.class, RiskListView.VIEW_DEF_ID,
				RiskTableFieldDef.selected, Arrays.asList(
						RiskTableFieldDef.name, RiskTableFieldDef.assignUser,
						RiskTableFieldDef.datedue, RiskTableFieldDef.rating));

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
						final SimpleRisk item = RiskListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						RiskListViewImpl.this.tableItem
								.fireSelectItemEvent(item);

					}
				});

				final SimpleRisk item = RiskListViewImpl.this.tableItem
						.getBeanByIndex(itemId);
				item.setExtraData(cb);
				return cb;
			}
		});

		this.tableItem.addGeneratedColumn("riskname", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleRisk risk = RiskListViewImpl.this.tableItem
						.getBeanByIndex(itemId);
				final LabelLink b = new LabelLink(risk.getRiskname(),
						ProjectLinkBuilder.generateRiskPreviewFullLink(
								risk.getProjectid(), risk.getId()));

				if ("Closed".equals(risk.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (risk.getDatedue() != null
							&& (risk.getDatedue()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
				b.setDescription(generateToolTip(risk));
				return b;

			}
		});

		this.tableItem.addGeneratedColumn("assignedToUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleRisk risk = RiskListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						return new ProjectUserLink(risk.getAssigntouser(), risk
								.getAssignToUserAvatarId(), risk
								.getAssignedToUserFullName(), true, true);

					}
				});

		this.tableItem.addGeneratedColumn("raisedByUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleRisk risk = RiskListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						return new ProjectUserLink(risk.getRaisedbyuser(), risk
								.getRaisedByUserAvatarId(), risk
								.getRaisedByUserFullName(), true, true);

					}
				});

		this.tableItem.addGeneratedColumn("datedue", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final SimpleRisk item = RiskListViewImpl.this.tableItem
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
				final SimpleRisk item = RiskListViewImpl.this.tableItem
						.getBeanByIndex(itemId);
				final RatingStars tinyRs = new RatingStars();
				tinyRs.setValue(item.getLevel());
				tinyRs.setStyleName("tiny");
				tinyRs.setReadOnly(true);
				return tinyRs;
			}
		});

		this.tableItem.setWidth("100%");

		this.riskListLayout.addComponent(this.constructTableActionControls());
		this.riskListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<RiskSearchCriteria> getSearchHandlers() {
		return this.riskSearchPanel;
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
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_DELETE));
		deleteBtn.setEnabled(CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.RISKS));

		this.tableActionControls = new DefaultMassItemActionHandlersContainer();

		if (CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.RISKS)) {
			tableActionControls.addActionItem(
					MassItemActionHandler.DELETE_ACTION,
					MyCollabResource.newResource("icons/16/action/delete.png"),
					"delete", "Delete");
		}

		tableActionControls.addActionItem(MassItemActionHandler.MAIL_ACTION,
				MyCollabResource.newResource("icons/16/action/mail.png"),
				"mail", "Mail");
		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_PDF_ACTION,
				MyCollabResource.newResource("icons/16/action/pdf.png"),
				"export", "export.pdf", "Export pdf");
		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_EXCEL_ACTION,
				MyCollabResource.newResource("icons/16/action/excel.png"),
				"export", "export.xlsx", "Export excel");
		tableActionControls.addDownloadActionItem(
				MassItemActionHandler.EXPORT_CSV_ACTION,
				MyCollabResource.newResource("icons/16/action/csv.png"),
				"export", "export.csv", "Export csv");

		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.RISKS)) {
			tableActionControls.addActionItem(
					MassItemActionHandler.MASS_UPDATE_ACTION, MyCollabResource
							.newResource("icons/16/action/massupdate.png"),
					"update", "Update");
		}

		this.tableActionControls.setVisible(false);
		this.tableActionControls.setWidth(Sizeable.SIZE_UNDEFINED, Unit.PIXELS);
		this.tableActionControls.addStyleName(UIConstants.THEME_SMALL_PADDING);

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
						new RiskListCustomizeWindow(RiskListView.VIEW_DEF_ID,
								tableItem));

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
	public HasSelectableItemHandlers<SimpleRisk> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<RiskSearchCriteria, SimpleRisk> getPagedBeanTable() {
		return this.tableItem;
	}

	private String generateToolTip(SimpleRisk risk) {
		try {
			Div div = new Div();
			H3 riskName = new H3();
			riskName.appendText(Jsoup.parse(risk.getRiskname()).html());
			div.appendChild(riskName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:11px;");

			Tr trRow5 = new Tr();
			Td trRow5_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringRemoveHtmlTag(risk
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
													(risk.getRaisedbyuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	risk.getRaisedbyuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					risk.getRaisedByUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(risk
																			.getRaisedByUserFullName()))));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Consequence:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											StringUtils
													.getStringFieldValue(risk
															.getConsequence())));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width:150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(risk.getAssigntouser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	risk.getAssigntouser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					risk.getAssignToUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(risk
																			.getAssignedToUserFullName()))));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Probability:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											StringUtils
													.getStringFieldValue(risk
															.getProbalitity())));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Date due:"))
					.appendChild(
							new Td().appendText(AppContext.formatDate(risk
									.getDatedue())));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Rating:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(risk
							.getLevel())));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Status:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(risk
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
							StringUtils.getStringRemoveHtmlTag(risk
									.getResponse()));
			trRow6_value.setAttribute("colspan", "3");

			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Response:")).appendChild(trRow6_value);

			table.appendChild(trRow5);
			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow6);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error("Error while generate tooltip for Risk", e);
			return "";
		}
	}

	private HorizontalLayout createHeaderRight() {
		final HorizontalLayout layout = new HorizontalLayout();

		final Button createBtn = new Button(
				LocalizationHelper.getMessage(RiskI18nEnum.NEW_RISK_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoAdd(this, null));
					}
				});
		createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		createBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.RISKS));

		UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}
}
