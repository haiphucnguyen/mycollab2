/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class RoleTableDisplay extends PagedBeanTable2<RoleService, RoleSearchCriteria, Role> {

    public RoleTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(RoleService.class), Role.class, visibleColumns, columnHeaders);

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
                        Role role = RoleTableDisplay.this
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
                final Role user = RoleTableDisplay.this.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(user.getRolename(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                fireTableEvent(new IPagedBeanTable.TableClickEvent(RoleTableDisplay.this, user, "rolename"));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });
        
        this.setColumnExpandRatio("description", 1);
        this.setColumnWidth("rolename", UIConstants.TABLE_X_LABEL_WIDTH);
    }
    
}
