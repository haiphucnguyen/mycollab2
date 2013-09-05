/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.List;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class RoleTableDisplay extends
		PagedBeanTable2<RoleService, RoleSearchCriteria, SimpleRole> {
	private static final long serialVersionUID = 1L;

	public RoleTableDisplay(TableViewField requiredColumn,
			List<TableViewField> displayColumns) {
		super(AppContext.getSpringBean(RoleService.class), SimpleRole.class,
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
						SimpleRole role = RoleTableDisplay.this
								.getBeanByIndex(itemId);
						RoleTableDisplay.this.fireSelectItemEvent(role);

					}
				});

				Role role = RoleTableDisplay.this.getBeanByIndex(itemId);
				role.setExtraData(cb);
				return cb;
			}
		});

		this.addGeneratedColumn("rolename", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleRole role = RoleTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(role.getRolename(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								fireTableEvent(new TableClickEvent(
										RoleTableDisplay.this, role, "rolename"));
							}
						});
				return b;

			}
		});
	}

}
