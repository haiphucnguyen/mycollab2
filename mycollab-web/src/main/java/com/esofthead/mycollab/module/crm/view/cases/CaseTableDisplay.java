package com.esofthead.mycollab.module.crm.view.cases;

import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public class CaseTableDisplay extends
		PagedBeanTable2<CaseService, CaseSearchCriteria, SimpleCase> {

	public CaseTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public CaseTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		this(null, requiredColumn, displayColumns);

	}

	public CaseTableDisplay(String viewId, TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(AppContext.getSpringBean(CaseService.class), SimpleCase.class,
				viewId, requiredColumn, displayColumns);

		this.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						SimpleCase cases = CaseTableDisplay.this
								.getBeanByIndex(itemId);
						CaseTableDisplay.this.fireSelectItemEvent(cases);

					}
				});
				SimpleCase cases = CaseTableDisplay.this.getBeanByIndex(itemId);
				cases.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("subject", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleCase cases = CaseTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(cases.getSubject(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										CaseTableDisplay.this, cases, "subject"));
							}
						});

				if ("Closed".equals(cases.getStatus())
						|| "Rejected".equals(cases.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				}
				return b;
			}
		});

		this.addGeneratedColumn("accountName", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleCase cases = CaseTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(cases.getAccountName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										CaseTableDisplay.this, cases,
										"accountName"));
							}
						});
				return b;
			}
		});

		this.addGeneratedColumn("assignUserFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleCase cases = CaseTableDisplay.this
								.getBeanByIndex(itemId);
						UserLink b = new UserLink(cases.getAssignuser(), cases
								.getAssignUserAvatarId(), cases
								.getAssignUserFullName());
						return b;

					}
				});

		this.addGeneratedColumn("createdtime", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleCase cases = CaseTableDisplay.this
						.getBeanByIndex(itemId);
				Label l = new Label();

				l.setValue(AppContext.formatDateTime(cases.getCreatedtime()));
				return l;
			}
		});

		this.setWidth("100%");
	}
}
