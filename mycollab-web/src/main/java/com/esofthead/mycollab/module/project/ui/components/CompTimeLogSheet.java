package com.esofthead.mycollab.module.project.ui.components;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class CompTimeLogSheet<V extends ValuedBean> extends
		HorizontalLayout {

	protected PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> tableItem;
	protected ItemTimeLoggingService itemTimeLoggingService;
	protected V bean;
	protected Button btnAdd;

	private AddTimeInvest investTimeLayout;
	private UpdateTimeRemain updateTimeRemainLayout;

	private static Logger log = LoggerFactory
			.getLogger(CompFollowersSheet.class);

	protected CompTimeLogSheet(final V bean) {
		this.bean = bean;
		this.setMargin(true);
		this.setSpacing(true);

		this.itemTimeLoggingService = AppContext
				.getSpringBean(ItemTimeLoggingService.class);

		this.initUI();
	}

	private void initUI() {

		this.investTimeLayout = this.new AddTimeInvest();
		this.addComponent(this.investTimeLayout);
		this.setExpandRatio(this.investTimeLayout, 1);
		this.updateTimeRemainLayout = this.new UpdateTimeRemain();
		this.addComponent(this.updateTimeRemainLayout);
		this.setExpandRatio(this.updateTimeRemainLayout, 1);
		this.setWidth("100%");
	}

	protected double getInvestValue() {
		return Double.parseDouble(this.investTimeLayout.numberField.getValue()
				.toString());
	}

	protected abstract void saveTimeInvest();

	protected abstract void updateTimeRemain();

	protected abstract ItemTimeLoggingSearchCriteria getItemSearchCriteria();

	protected abstract double getEstimateRemainTime();

	protected abstract boolean isEnableAdd();

	protected double getUpdateRemainTime() {
		return Double.parseDouble(this.updateTimeRemainLayout.numberField
				.getValue().toString());
	}

	private class AddTimeInvest extends VerticalLayout {

		public Label lbTimeTotal;

		public NumbericTextField numberField;

		public AddTimeInvest() {

			this.setSpacing(true);

			final VerticalLayout totalLayout = new VerticalLayout();
			totalLayout.setMargin(true);
			totalLayout.addStyleName("boxTotal");
			totalLayout.setWidth("100%");
			this.addComponent(totalLayout);

			final Label lbTimeInstructTotal = new Label("Total Time Invested");
			totalLayout.addComponent(lbTimeInstructTotal);
			this.lbTimeTotal = new Label("_");
			this.lbTimeTotal.addStyleName("numberTotal");
			totalLayout.addComponent(this.lbTimeTotal);

			final HorizontalLayout addLayout = new HorizontalLayout();
			addLayout.setSpacing(true);
			addLayout.setSizeUndefined();
			this.addComponent(addLayout);

			this.numberField = new NumbericTextField();
			this.numberField.setWidth("80px");
			addLayout.addComponent(this.numberField);
			addLayout.setComponentAlignment(this.numberField,
					Alignment.MIDDLE_LEFT);

			CompTimeLogSheet.this.btnAdd = new Button("Add",
					new Button.ClickListener() {

						@Override
						public void buttonClick(final ClickEvent event) {
							try {
								final double d = Double
										.parseDouble(AddTimeInvest.this.numberField
												.getValue().toString());
								if (d > 0) {
									CompTimeLogSheet.this.saveTimeInvest();
									AddTimeInvest.this.loadTimeInvestItem();
									AddTimeInvest.this.numberField
											.setValue(0.0);
								}
							} catch (final Exception e) {
								AddTimeInvest.this.numberField.setValue(0.0);
							}
						}

					});

			CompTimeLogSheet.this.btnAdd.setEnabled(CompTimeLogSheet.this
					.isEnableAdd());
			CompTimeLogSheet.this.btnAdd
					.setStyleName(UIConstants.THEME_BLUE_LINK);
			CompTimeLogSheet.this.btnAdd.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			addLayout.addComponent(CompTimeLogSheet.this.btnAdd);
			addLayout.setComponentAlignment(CompTimeLogSheet.this.btnAdd,
					Alignment.MIDDLE_LEFT);

			final Label lbIntructAdd = new Label(
					"Add only time invested by you.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);

			CompTimeLogSheet.this.tableItem = new PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
					AppContext.getSpringBean(ItemTimeLoggingService.class),
					SimpleItemTimeLogging.class,
					new String[] { "logUserFullName", "createdtime",
							"logvalue", "id" }, new String[] { "User",
							"Created Time", "Time", "" });

			CompTimeLogSheet.this.tableItem.addGeneratedColumn(
					"logUserFullName", new Table.ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging monitorItem = CompTimeLogSheet.this.tableItem
									.getBeanByIndex(itemId);

							return new ProjectUserLink(
									monitorItem.getLoguser(), monitorItem
											.getLogUserFullName(), true, true);

						}
					});

			CompTimeLogSheet.this.tableItem.addGeneratedColumn("createdtime",
					new ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging monitorItem = CompTimeLogSheet.this.tableItem
									.getBeanByIndex(itemId);
							final Label l = new Label();
							l.setValue(AppContext.formatDateTime(monitorItem
									.getCreatedtime()));
							return l;
						}
					});

			CompTimeLogSheet.this.tableItem.addGeneratedColumn("logvalue",
					new ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging itemTimeLogging = CompTimeLogSheet.this.tableItem
									.getBeanByIndex(itemId);
							final Label l = new Label();
							l.setValue(itemTimeLogging.getLogvalue());
							return l;
						}
					});

			CompTimeLogSheet.this.tableItem.addGeneratedColumn("id",
					new ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging itemTimeLogging = CompTimeLogSheet.this.tableItem
									.getBeanByIndex(itemId);
							final Button deleteBtn = new Button(null,
									new Button.ClickListener() {
										@Override
										public void buttonClick(
												final ClickEvent event) {
											ConfirmDialog
													.show(AppContext
															.getApplication()
															.getMainWindow(),
															"Please Confirm:",
															"Are you sure to delete this invest?",
															"Yes",
															"No",
															new ConfirmDialog.Listener() {
																private static final long serialVersionUID = 1L;

																@Override
																public void onClose(
																		final ConfirmDialog dialog) {
																	if (dialog
																			.isConfirmed()) {
																		CompTimeLogSheet.this.itemTimeLoggingService
																				.removeWithSession(
																						itemTimeLogging
																								.getId(),
																						AppContext
																								.getUsername());
																		AddTimeInvest.this
																				.loadTimeInvestItem();
																	}
																}
															});
										}
									});
							deleteBtn.setStyleName("link");
							deleteBtn.setIcon(MyCollabResource
									.newResource("icons/16/delete.png"));
							itemTimeLogging.setExtraData(deleteBtn);

							final ProjectMemberService memberService = AppContext
									.getSpringBean(ProjectMemberService.class);
							final SimpleProjectMember member = memberService
									.findMemberByUsername(AppContext
											.getUsername(),
											CurrentProjectVariables
													.getProjectId());

							if (member != null) {
								deleteBtn.setEnabled(member.getIsadmin());
							}
							return deleteBtn;
						}
					});

			CompTimeLogSheet.this.tableItem.setWidth("100%");

			CompTimeLogSheet.this.tableItem.setColumnExpandRatio(
					"logUserFullName", 1.0f);
			CompTimeLogSheet.this.tableItem.setColumnWidth("monitorDate",
					UIConstants.TABLE_DATE_TIME_WIDTH);
			CompTimeLogSheet.this.tableItem.setColumnWidth("logvalue",
					UIConstants.TABLE_S_LABEL_WIDTH);

			this.addComponent(CompTimeLogSheet.this.tableItem);
			this.loadTimeInvestItem();
			this.setTotalTimeValue();
		}

		private void loadTimeInvestItem() {
			final ItemTimeLoggingSearchCriteria searchCriteria = CompTimeLogSheet.this
					.getItemSearchCriteria();
			CompTimeLogSheet.this.tableItem.setSearchCriteria(searchCriteria);
			this.setTotalTimeValue();
		}

		private double getTotalInvest() {
			double total = 0;
			final ItemTimeLoggingSearchCriteria searchCriteria = CompTimeLogSheet.this
					.getItemSearchCriteria();
			final List<SimpleItemTimeLogging> listTime = CompTimeLogSheet.this.itemTimeLoggingService
					.findPagableListByCriteria(new SearchRequest<ItemTimeLoggingSearchCriteria>(
							searchCriteria, 0, Integer.MAX_VALUE));
			for (final SimpleItemTimeLogging simpleItemTimeLogging : listTime) {
				total += simpleItemTimeLogging.getLogvalue();
			}
			return total;
		}

		public void setTotalTimeValue() {
			if (this.getTotalInvest() > 0) {
				this.lbTimeTotal.setValue(this.getTotalInvest());
			}
		}
	}

	private class UpdateTimeRemain extends VerticalLayout {

		public NumbericTextField numberField;

		private final Label lbUpdateTime;

		public UpdateTimeRemain() {

			this.setSpacing(true);
			this.setMargin(false, false, false, true);
			this.setWidth("100%");

			final VerticalLayout updateLayout = new VerticalLayout();
			updateLayout.setMargin(true);
			updateLayout.addStyleName("boxTotal");
			updateLayout.setWidth("100%");
			this.addComponent(updateLayout);

			final Label lbTimeInstructTotal = new Label("Work Hours Remaining");
			updateLayout.addComponent(lbTimeInstructTotal);
			this.lbUpdateTime = new Label("_");
			this.lbUpdateTime.addStyleName("numberTotal");
			updateLayout.addComponent(this.lbUpdateTime);

			final HorizontalLayout addLayout = new HorizontalLayout();
			addLayout.setSpacing(true);
			addLayout.setSizeUndefined();
			this.addComponent(addLayout);

			this.numberField = new NumbericTextField();
			this.numberField.setWidth("80px");
			addLayout.addComponent(this.numberField);
			addLayout.setComponentAlignment(this.numberField,
					Alignment.MIDDLE_LEFT);

			CompTimeLogSheet.this.btnAdd = new Button("Update",
					new Button.ClickListener() {

						@Override
						public void buttonClick(final ClickEvent event) {

							try {
								final double d = Double
										.parseDouble(UpdateTimeRemain.this.numberField
												.getValue().toString());
								if (d > 0) {
									CompTimeLogSheet.this.updateTimeRemain();
									UpdateTimeRemain.this.lbUpdateTime
											.setValue(UpdateTimeRemain.this.numberField
													.getValue());
									UpdateTimeRemain.this.numberField
											.setValue(0.0);
								}
							} catch (final Exception e) {
								UpdateTimeRemain.this.numberField.setValue(0.0);
							}
						}

					});

			CompTimeLogSheet.this.btnAdd.setEnabled(CompTimeLogSheet.this
					.isEnableAdd());
			CompTimeLogSheet.this.btnAdd
					.setStyleName(UIConstants.THEME_BLUE_LINK);
			addLayout.addComponent(CompTimeLogSheet.this.btnAdd);
			addLayout.setComponentAlignment(CompTimeLogSheet.this.btnAdd,
					Alignment.MIDDLE_LEFT);

			final Label lbIntructAdd = new Label("Update remaining estimate.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);
			this.setUpdateTimeValue();
		}

		public void setUpdateTimeValue() {
			if (CompTimeLogSheet.this.getEstimateRemainTime() > -1) {
				this.lbUpdateTime.setValue(CompTimeLogSheet.this
						.getEstimateRemainTime());
			}
		}
	}

	private class NumbericTextField extends TextField {
		private static final long serialVersionUID = 1L;

		@Override
		protected void setValue(final Object newValue,
				final boolean repaintIsNotNeeded) {
			try {
				final double d = Double.parseDouble((String) newValue);
				super.setValue(d, repaintIsNotNeeded);
			} catch (final Exception e) {
				super.setValue(0.0, repaintIsNotNeeded);
			}
		}
	}

}
