package com.esofthead.mycollab.module.project.view.risk;

import java.util.Arrays;
import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.PopupButtonControl;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class RiskListViewImpl extends AbstractView implements RiskListView {

	private static final long serialVersionUID = 1L;
	private final RiskSearchPanel riskSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private PagedBeanTable2<RiskService, RiskSearchCriteria, SimpleRisk> tableItem;
	private final VerticalLayout riskListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public RiskListViewImpl() {
		this.setMargin(false, true, true, true);

		this.riskSearchPanel = new RiskSearchPanel();
		this.addComponent(this.riskSearchPanel);

		this.riskListLayout = new VerticalLayout();
		this.addComponent(this.riskListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new PagedBeanTable2<RiskService, RiskSearchCriteria, SimpleRisk>(
				ApplicationContextUtil.getSpringBean(RiskService.class), SimpleRisk.class,
				RiskListView.VIEW_DEF_ID, RiskTableFieldDef.selected,
				Arrays.asList(RiskTableFieldDef.name,
						RiskTableFieldDef.assignUser,
						RiskTableFieldDef.datedue, RiskTableFieldDef.rating));

		this.tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
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
				final ButtonLink b = new ButtonLink(risk.getRiskname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new RiskEvent.GotoRead(this, risk
												.getId()));
							}
						});

				if ("Closed".equals(risk.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (risk.getDatedue() != null
							&& (risk.getDatedue()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
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
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_DELETE));
		deleteBtn.setEnabled(CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.RISKS));

		this.tableActionControls = new PopupButtonControl(
				PopupActionHandler.DELETE_ACTION, deleteBtn);
		this.tableActionControls.addOptionItem(PopupActionHandler.MAIL_ACTION,
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_MAIL));
		this.tableActionControls
				.addOptionItem(PopupActionHandler.EXPORT_CSV_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));
		this.tableActionControls
				.addOptionItem(PopupActionHandler.EXPORT_PDF_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));
		this.tableActionControls.addOptionItem(
				PopupActionHandler.EXPORT_EXCEL_ACTION, LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));
		this.tableActionControls
				.addOptionItem(
						PopupActionHandler.MASS_UPDATE_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_MASSUPDATE),
						CurrentProjectVariables
								.canWrite(ProjectRolePermissionCollections.RISKS));

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		layout.setExpandRatio(selectedItemsNumberLabel, 1.0f);

		Button customizeViewBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getWindow().addWindow(
						new RiskListCustomizeWindow(RiskListView.VIEW_DEF_ID,
								tableItem));

			}
		});
		customizeViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/customize.png"));
		customizeViewBtn.setDescription("Layout Options");
		customizeViewBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
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
	public HasPopupActionHandlers getPopupActionHandlers() {
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
}
