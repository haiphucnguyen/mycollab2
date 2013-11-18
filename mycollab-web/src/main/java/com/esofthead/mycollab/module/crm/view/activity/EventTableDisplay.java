/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.GregorianCalendar;
import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class EventTableDisplay extends
		DefaultPagedBeanTable<EventService, EventSearchCriteria, SimpleEvent> {
	private static final long serialVersionUID = 1L;

	public EventTableDisplay(List<TableViewField> displayColumns) {
		this(null, displayColumns);
	}

	public EventTableDisplay(TableViewField requireColumn,
			List<TableViewField> displayColumns) {
		super(ApplicationContextUtil.getSpringBean(EventService.class), SimpleEvent.class,
				requireColumn, displayColumns);

		this.addGeneratedColumn("selected", new Table.ColumnGenerator() {
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
						SimpleEvent simpleEvent = EventTableDisplay.this
								.getBeanByIndex(itemId);
						EventTableDisplay.this.fireSelectItemEvent(simpleEvent);

					}
				});

				SimpleEvent simpleEvent = EventTableDisplay.this
						.getBeanByIndex(itemId);
				simpleEvent.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("startDate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {

				final SimpleEvent event = EventTableDisplay.this
						.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(AppContext.formatDateTime(event.getStartDate()));
				return l;
			}
		});

		this.addGeneratedColumn("endDate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleEvent event = EventTableDisplay.this
						.getBeanByIndex(itemId);
				Label l = new Label();
				l.setValue(AppContext.formatDateTime(event.getEndDate()));
				return l;
			}
		});

		this.addGeneratedColumn("subject", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleEvent simpleEvent = EventTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(simpleEvent.getSubject(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										EventTableDisplay.this, simpleEvent,
										"subject"));
							}
						});

				if ("Held".equals(simpleEvent.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (simpleEvent.getEndDate() != null
							&& (simpleEvent.getEndDate()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
				return b;

			}
		});
	}
}
