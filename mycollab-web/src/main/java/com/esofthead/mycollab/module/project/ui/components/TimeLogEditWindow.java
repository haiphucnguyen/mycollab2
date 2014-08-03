package com.esofthead.mycollab.module.project.ui.components;

import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.3
 *
 */
public abstract class TimeLogEditWindow<V extends ValuedBean> extends Window {
	private static final long serialVersionUID = 1L;

	protected DefaultPagedBeanTable<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> tableItem;
	protected ItemTimeLoggingService itemTimeLoggingService;
	protected V bean;

	private HorizontalLayout content;
	protected Button btnAdd;

	private AddTimeInvest investTimeLayout;
	private UpdateTimeRemain updateTimeRemainLayout;

	protected TimeLogEditWindow(final V bean) {
		this.bean = bean;
		this.center();
		content = new HorizontalLayout();
		this.setContent(content);
		content.setMargin(true);
		content.setSpacing(true);

		this.itemTimeLoggingService = ApplicationContextUtil
				.getSpringBean(ItemTimeLoggingService.class);

		this.initUI();
	}

	private void initUI() {
		this.investTimeLayout = this.new AddTimeInvest();
		content.addComponent(this.investTimeLayout);
		content.setExpandRatio(this.investTimeLayout, 1);
		this.updateTimeRemainLayout = this.new UpdateTimeRemain();
		content.addComponent(this.updateTimeRemainLayout);
		content.setExpandRatio(this.updateTimeRemainLayout, 1);
		this.setWidth("800px");
		this.loadTimeValue();
	}

	public void loadTimeValue() {
		investTimeLayout.loadTimeInvestItem();
		updateTimeRemainLayout.setUpdateTimeValue();
	}

	protected double getInvestValue() {
		return Double.parseDouble(this.investTimeLayout.numberField.getValue()
				.toString());
	}

	protected Boolean isBillableHours() {
		return this.investTimeLayout.isBillableField.getValue();
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
		private static final long serialVersionUID = 1L;

		private Label lbTimeTotal;

		private NumbericTextField numberField;

		private CheckBox isBillableField;

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

			this.isBillableField = new CheckBox("Is Billable", true);
			addLayout.addComponent(this.isBillableField);
			addLayout.setComponentAlignment(this.isBillableField,
					Alignment.MIDDLE_LEFT);

			TimeLogEditWindow.this.btnAdd = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_ADD_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							double d = 0;
							try {
								d = Double
										.parseDouble(AddTimeInvest.this.numberField
												.getValue().toString());
							} catch (NumberFormatException e) {
								NotificationUtil
										.showWarningNotification("You must enter a positive number value");
							}
							if (d > 0) {
								TimeLogEditWindow.this.saveTimeInvest();
								AddTimeInvest.this.loadTimeInvestItem();
								AddTimeInvest.this.numberField.setValue("0.0");
							}
						}

					});

			TimeLogEditWindow.this.btnAdd.setEnabled(TimeLogEditWindow.this
					.isEnableAdd());
			TimeLogEditWindow.this.btnAdd
					.setStyleName(UIConstants.THEME_GREEN_LINK);
			TimeLogEditWindow.this.btnAdd.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			addLayout.addComponent(TimeLogEditWindow.this.btnAdd);
			addLayout.setComponentAlignment(TimeLogEditWindow.this.btnAdd,
					Alignment.MIDDLE_LEFT);

			final Label lbIntructAdd = new Label(
					"Add only time invested by you.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);

			TimeLogEditWindow.this.tableItem = new DefaultPagedBeanTable<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
					ApplicationContextUtil
							.getSpringBean(ItemTimeLoggingService.class),
					SimpleItemTimeLogging.class, Arrays
							.asList(new TableViewField(
									TimeTrackingI18nEnum.LOG_USER,
									"logUserFullName",
									UIConstants.TABLE_X_LABEL_WIDTH),
									new TableViewField(
											TimeTrackingI18nEnum.LOG_FOR_DATE,
											"createdtime",
											UIConstants.TABLE_DATE_TIME_WIDTH),
									new TableViewField(
											TimeTrackingI18nEnum.LOG_VALUE,
											"logvalue",
											UIConstants.TABLE_S_LABEL_WIDTH),
									new TableViewField(null, "id",
											UIConstants.TABLE_CONTROL_WIDTH)));

			TimeLogEditWindow.this.tableItem.addGeneratedColumn(
					"logUserFullName", new Table.ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging timeLoggingItem = TimeLogEditWindow.this.tableItem
									.getBeanByIndex(itemId);

							return new ProjectUserLink(timeLoggingItem
									.getLoguser(), timeLoggingItem
									.getLogUserAvatarId(), timeLoggingItem
									.getLogUserFullName());

						}
					});

			TimeLogEditWindow.this.tableItem.addGeneratedColumn("createdtime",
					new ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging monitorItem = TimeLogEditWindow.this.tableItem
									.getBeanByIndex(itemId);
							final Label l = new Label();
							l.setValue(AppContext.formatDateTime(monitorItem
									.getCreatedtime()));
							return l;
						}
					});

			TimeLogEditWindow.this.tableItem.addGeneratedColumn("logvalue",
					new ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging itemTimeLogging = TimeLogEditWindow.this.tableItem
									.getBeanByIndex(itemId);
							final Label l = new Label();
							l.setValue(itemTimeLogging.getLogvalue() + "");
							return l;
						}
					});

			TimeLogEditWindow.this.tableItem.addGeneratedColumn("id",
					new ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								final Table source, final Object itemId,
								final Object columnId) {
							final SimpleItemTimeLogging itemTimeLogging = TimeLogEditWindow.this.tableItem
									.getBeanByIndex(itemId);
							final Button deleteBtn = new Button(null,
									new Button.ClickListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void buttonClick(
												final ClickEvent event) {
											TimeLogEditWindow.this.itemTimeLoggingService.removeWithSession(
													itemTimeLogging.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											AddTimeInvest.this
													.loadTimeInvestItem();
										}
									});
							deleteBtn.setStyleName("link");
							deleteBtn.setIcon(MyCollabResource
									.newResource("icons/16/delete.png"));
							itemTimeLogging.setExtraData(deleteBtn);

							final ProjectMemberService memberService = ApplicationContextUtil
									.getSpringBean(ProjectMemberService.class);
							final SimpleProjectMember member = memberService
									.findMemberByUsername(AppContext
											.getUsername(),
											CurrentProjectVariables
													.getProjectId(), AppContext
													.getAccountId());

							if (member != null) {
								deleteBtn.setEnabled(member.getIsadmin());
							}
							return deleteBtn;
						}
					});

			TimeLogEditWindow.this.tableItem.setWidth("100%");
			this.addComponent(TimeLogEditWindow.this.tableItem);
		}

		private void loadTimeInvestItem() {
			final ItemTimeLoggingSearchCriteria searchCriteria = TimeLogEditWindow.this
					.getItemSearchCriteria();
			TimeLogEditWindow.this.tableItem.setSearchCriteria(searchCriteria);
			this.setTotalTimeValue();
		}

		private double getTotalInvest() {
			double total = 0;
			final ItemTimeLoggingSearchCriteria searchCriteria = TimeLogEditWindow.this
					.getItemSearchCriteria();
			final List<SimpleItemTimeLogging> listTime = itemTimeLoggingService
					.findPagableListByCriteria(new SearchRequest<ItemTimeLoggingSearchCriteria>(
							searchCriteria, 0, Integer.MAX_VALUE));
			for (final SimpleItemTimeLogging simpleItemTimeLogging : listTime) {
				total += simpleItemTimeLogging.getLogvalue();
			}
			return total;
		}

		public void setTotalTimeValue() {
			if (this.getTotalInvest() > 0) {
				this.lbTimeTotal.setValue(this.getTotalInvest() + "");
			}
		}
	}

	private class UpdateTimeRemain extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		public NumbericTextField numberField;

		private final Label lbUpdateTime;

		public UpdateTimeRemain() {

			this.setSpacing(true);
			this.setMargin(new MarginInfo(false, false, false, true));
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

			TimeLogEditWindow.this.btnAdd = new Button("Update",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {

							try {
								double d = 0;
								try {
									d = Double
											.parseDouble(UpdateTimeRemain.this.numberField
													.getValue().toString());
								} catch (Exception e) {
									NotificationUtil
											.showWarningNotification("You must enter a positive number value");
								}
								if (d > 0) {
									TimeLogEditWindow.this.updateTimeRemain();
									UpdateTimeRemain.this.lbUpdateTime
											.setValue(UpdateTimeRemain.this.numberField
													.getValue());
									UpdateTimeRemain.this.numberField
											.setValue("0.0");
								}
							} catch (final Exception e) {
								UpdateTimeRemain.this.numberField
										.setValue("0.0");
							}
						}

					});

			TimeLogEditWindow.this.btnAdd.setEnabled(TimeLogEditWindow.this
					.isEnableAdd());
			TimeLogEditWindow.this.btnAdd
					.setStyleName(UIConstants.THEME_GREEN_LINK);
			addLayout.addComponent(TimeLogEditWindow.this.btnAdd);
			addLayout.setComponentAlignment(TimeLogEditWindow.this.btnAdd,
					Alignment.MIDDLE_LEFT);

			final Label lbIntructAdd = new Label("Update remaining estimate.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);
		}

		public void setUpdateTimeValue() {
			if (TimeLogEditWindow.this.getEstimateRemainTime() > -1) {
				this.lbUpdateTime.setValue(TimeLogEditWindow.this
						.getEstimateRemainTime() + "");
			}
		}
	}

	private class NumbericTextField extends TextField {
		private static final long serialVersionUID = 1L;

		@Override
		protected void setValue(final String newValue,
				final boolean repaintIsNotNeeded) {
			try {
				final String d = Double.parseDouble((String) newValue) + "";
				super.setValue(d, repaintIsNotNeeded);
			} catch (final Exception e) {
				super.setValue("0.0", repaintIsNotNeeded);
			}
		}
	}
}
