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
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
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

	protected CompTimeLogSheet(V bean) {
		this.bean = bean;
		this.setMargin(true);
		this.setSpacing(true);

		itemTimeLoggingService = AppContext
				.getSpringBean(ItemTimeLoggingService.class);

		initUI();
	}

	private void initUI() {

		investTimeLayout = this.new AddTimeInvest();
		this.addComponent(investTimeLayout);
		updateTimeRemainLayout = this.new UpdateTimeRemain();
		this.addComponent(updateTimeRemainLayout);
	}

	protected double getInvestValue() {
		return Double.parseDouble(investTimeLayout.numberField.getValue()
				.toString());
	}

	protected abstract void saveTimeInvest();

	protected abstract void updateTimeRemain();

	protected abstract ItemTimeLoggingSearchCriteria getItemSearchCriteria();

	protected abstract double getEstimateRemainTime();
	
	protected abstract boolean isEnableAdd();

	protected double getUpdateRemainTime() {
		return Double.parseDouble(updateTimeRemainLayout.numberField.getValue()
				.toString());
	}

	private class AddTimeInvest extends VerticalLayout {

		public Label lbTimeTotal;

		public NumbericTextField numberField;

		public AddTimeInvest() {

			this.setSpacing(true);
			this.setWidth("420px");

			VerticalLayout totalLayout = new VerticalLayout();
			totalLayout.setMargin(false, false, true, false);
			totalLayout.addStyleName("boxTotal");
			totalLayout.setWidth("95%");
			this.addComponent(totalLayout);
			
			Label lbTimeInstructTotal = new Label("Total Time Invested");
			totalLayout.addComponent(lbTimeInstructTotal);
			lbTimeTotal = new Label("_");
			lbTimeTotal.addStyleName("numberTotal");
			totalLayout.addComponent(lbTimeTotal);

			HorizontalLayout addLayout = new HorizontalLayout();
			addLayout.setSpacing(true);
			addLayout.setSizeUndefined();
			this.addComponent(addLayout);

			numberField = new NumbericTextField();
			numberField.setWidth("80px");
			addLayout.addComponent(numberField);
			addLayout.setComponentAlignment(numberField, Alignment.MIDDLE_LEFT);

			btnAdd = new Button("Add", new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						double d = Double.parseDouble(numberField.getValue().toString());
						if (d > 0) {
							saveTimeInvest();
							loadTimeInvestItem();
							numberField.setValue(0.0);
						}
					} catch (Exception e) {
						numberField.setValue(0.0);
					}
				}

			});

			btnAdd.setEnabled(isEnableAdd());
			btnAdd.setStyleName(UIConstants.THEME_BLUE_LINK);
			btnAdd.setIcon(new ThemeResource("icons/16/addRecord.png"));
			addLayout.addComponent(btnAdd);
			addLayout.setComponentAlignment(btnAdd, Alignment.MIDDLE_LEFT);

			Label lbIntructAdd = new Label("Add only time invested by you.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);

			tableItem = new PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
					AppContext.getSpringBean(ItemTimeLoggingService.class),
					SimpleItemTimeLogging.class,
					new String[] { "logUserFullName", "createdtime",
							"logvalue", "id" }, new String[] { "User",
							"Created Time", "Time", "" });

			tableItem.addGeneratedColumn("logUserFullName",
					new Table.ColumnGenerator() {
						private static final long serialVersionUID = 1L;

						@Override
						public com.vaadin.ui.Component generateCell(
								Table source, final Object itemId,
								Object columnId) {
							final SimpleItemTimeLogging monitorItem = tableItem
									.getBeanByIndex(itemId);

							UserService userService = AppContext
									.getSpringBean(UserService.class);
							SimpleUser user = userService
									.findUserByUserName(monitorItem
											.getLoguser());

							return new ProjectUserLink(user.getUsername(), user
									.getDisplayName(), true, true);

						}
					});

			tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public com.vaadin.ui.Component generateCell(Table source,
						Object itemId, Object columnId) {
					final SimpleItemTimeLogging monitorItem = tableItem
							.getBeanByIndex(itemId);
					Label l = new Label();
					l.setValue(AppContext.formatDateTime(monitorItem
							.getCreatedtime()));
					return l;
				}
			});

			tableItem.addGeneratedColumn("logvalue", new ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public com.vaadin.ui.Component generateCell(Table source,
						Object itemId, Object columnId) {
					final SimpleItemTimeLogging itemTimeLogging = tableItem
							.getBeanByIndex(itemId);
					Label l = new Label();
					l.setValue(itemTimeLogging.getLogvalue());
					return l;
				}
			});

			tableItem.addGeneratedColumn("id", new ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public com.vaadin.ui.Component generateCell(Table source,
						Object itemId, Object columnId) {
					final SimpleItemTimeLogging itemTimeLogging = tableItem
							.getBeanByIndex(itemId);
					Button deleteBtn = new Button(null,
							new Button.ClickListener() {
								@Override
								public void buttonClick(ClickEvent event) {
									ConfirmDialog
											.show(AppContext.getApplication()
													.getMainWindow(),
													"Please Confirm:",
													"Are you sure to delete this invest?",
													"Yes",
													"No",
													new ConfirmDialog.Listener() {
														private static final long serialVersionUID = 1L;

														@Override
														public void onClose(
																ConfirmDialog dialog) {
															if (dialog
																	.isConfirmed()) {
																itemTimeLoggingService
																		.removeWithSession(
																				itemTimeLogging
																						.getId(),
																				AppContext
																						.getUsername());
																loadTimeInvestItem();
															}
														}
													});
								}
							});
					deleteBtn.setStyleName("link");
					deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
					itemTimeLogging.setExtraData(deleteBtn);

					ProjectMemberService memberService = AppContext
							.getSpringBean(ProjectMemberService.class);
					SimpleProjectMember member = memberService
							.findMemberByUsername(AppContext.getUsername(),
									CurrentProjectVariables.getProjectId());

					if (member != null) {
						deleteBtn.setEnabled(member.getIsadmin());
					}
					return deleteBtn;
				}
			});

			tableItem.setWidth("100%");

			tableItem.setColumnExpandRatio("logUserFullName", 1.0f);
			tableItem.setColumnWidth("monitorDate",
					UIConstants.TABLE_DATE_TIME_WIDTH);
			tableItem.setColumnWidth("logvalue",
					UIConstants.TABLE_S_LABEL_WIDTH);

			this.addComponent(tableItem);
			loadTimeInvestItem();
			setTotalTimeValue();
		}

		private void loadTimeInvestItem() {
			ItemTimeLoggingSearchCriteria searchCriteria = getItemSearchCriteria();
			tableItem.setSearchCriteria(searchCriteria);
			setTotalTimeValue();
		}

		private double getTotalInvest() {
			double total = 0;
			ItemTimeLoggingSearchCriteria searchCriteria = getItemSearchCriteria();
			List<SimpleItemTimeLogging> listTime = itemTimeLoggingService
					.findPagableListByCriteria(new SearchRequest<ItemTimeLoggingSearchCriteria>(
							searchCriteria, 0, Integer.MAX_VALUE));
			for (SimpleItemTimeLogging simpleItemTimeLogging : listTime) {
				total += simpleItemTimeLogging.getLogvalue();
			}
			return total;
		}

		public void setTotalTimeValue() {
			if (getTotalInvest() > 0) {
				lbTimeTotal.setValue(getTotalInvest());
			}
		}
	}

	private class UpdateTimeRemain extends VerticalLayout {

		public NumbericTextField numberField;

		private Label lbUpdateTime;

		public UpdateTimeRemain() {

			this.setSpacing(true);
			this.setMargin(false,false, false, true);

			VerticalLayout updateLayout = new VerticalLayout();
			updateLayout.setMargin(false, false, true, false);
			updateLayout.addStyleName("boxTotal");
			updateLayout.setWidth("300px");
			this.addComponent(updateLayout);
			
			Label lbTimeInstructTotal = new Label("Work Hours Remaining");
			updateLayout.addComponent(lbTimeInstructTotal);
			lbUpdateTime = new Label("_");
			lbUpdateTime.addStyleName("numberTotal");
			updateLayout.addComponent(lbUpdateTime);

			HorizontalLayout addLayout = new HorizontalLayout();
			addLayout.setSpacing(true);
			addLayout.setSizeUndefined();
			this.addComponent(addLayout);

			numberField = new NumbericTextField();
			numberField.setWidth("80px");
			addLayout.addComponent(numberField);
			addLayout.setComponentAlignment(numberField, Alignment.MIDDLE_LEFT);

			btnAdd = new Button("Update", new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					
					try {
						double d = Double.parseDouble(numberField.getValue().toString());
						if (d > 0) {
							updateTimeRemain();
							lbUpdateTime.setValue(numberField.getValue());
							numberField.setValue(0.0);
						}
					} catch (Exception e) {
						numberField.setValue(0.0);
					}
				}

			});
			
			btnAdd.setEnabled(isEnableAdd());
			btnAdd.setStyleName(UIConstants.THEME_BLUE_LINK);
			addLayout.addComponent(btnAdd);
			addLayout.setComponentAlignment(btnAdd, Alignment.MIDDLE_LEFT);

			Label lbIntructAdd = new Label("Update remaining estimate.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);
			setUpdateTimeValue();
		}

		public void setUpdateTimeValue() {
			if (getEstimateRemainTime() > -1) {
				lbUpdateTime.setValue(getEstimateRemainTime());
			}
		}
	}

	private class NumbericTextField extends TextField {
		private static final long serialVersionUID = 1L;

		@Override
		protected void setValue(Object newValue, boolean repaintIsNotNeeded) {
			try {
				double d = Double.parseDouble((String) newValue);
				super.setValue(d, repaintIsNotNeeded);
			} catch (Exception e) {
				super.setValue(0.0, repaintIsNotNeeded);
			}
		}
	}

}
