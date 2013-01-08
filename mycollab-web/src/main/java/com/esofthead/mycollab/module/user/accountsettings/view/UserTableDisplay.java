/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class UserTableDisplay extends PagedBeanTable2<UserService, UserSearchCriteria, SimpleUser> {

    public UserTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(UserService.class), SimpleUser.class, visibleColumns, columnHeaders);

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
                final SimpleUser user = UserTableDisplay.this.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(user.getUsername(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                fireTableEvent(new TableClickEvent(UserTableDisplay.this, user, "username"));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });
        
        this.setColumnExpandRatio("username", 1);
    }
}
