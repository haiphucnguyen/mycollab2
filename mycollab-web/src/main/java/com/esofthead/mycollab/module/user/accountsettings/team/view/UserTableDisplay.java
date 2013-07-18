/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.List;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author haiphucnguyen
 */
public class UserTableDisplay extends
		PagedBeanTable2<UserService, UserSearchCriteria, SimpleUser> {
	private static final long serialVersionUID = 1L;

	public UserTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(AppContext.getSpringBean(UserService.class), SimpleUser.class,
				requiredColumn, displayColumns);

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
						SimpleUser user = UserTableDisplay.this
								.getBeanByIndex(itemId);
						UserTableDisplay.this.fireSelectItemEvent(user);

					}
				});

				SimpleUser user = UserTableDisplay.this.getBeanByIndex(itemId);
				user.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("username", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleUser user = UserTableDisplay.this
						.getBeanByIndex(itemId);
				UserLink b = new UserLink(user.getUsername(), user
						.getAvatarid(), user.getDisplayName());
				b.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						fireTableEvent(new TableClickEvent(
								UserTableDisplay.this, user, "username"));
					}
				});
				return b;

			}
		});

		this.addGeneratedColumn("displayName", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleUser user = UserTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(user.getDisplayName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										UserTableDisplay.this, user,
										"displayName"));
							}
						});
				return b;

			}
		});

		this.addGeneratedColumn("email", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				SimpleUser user = UserTableDisplay.this.getBeanByIndex(itemId);
				return new EmailLink(user.getEmail());
			}
		});

		this.addGeneratedColumn("lastAccessedTime",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleUser user = UserTableDisplay.this
								.getBeanByIndex(itemId);
						Label dateLbl = new Label(DateTimeUtils
								.getStringDateFromNow(user
										.getLastaccessedtime()));
						return dateLbl;
					}
				});
	}
}
