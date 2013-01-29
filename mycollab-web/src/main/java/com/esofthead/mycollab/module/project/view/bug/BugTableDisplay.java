/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class BugTableDisplay extends PagedBeanTable2<BugService, BugSearchCriteria, SimpleBug> {

    public BugTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(BugService.class),
                SimpleBug.class, visibleColumns, columnHeaders);

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
                        SimpleBug bug = BugTableDisplay.this
                                .getBeanByIndex(itemId);
                        BugTableDisplay.this.fireSelectItemEvent(bug);

                    }
                });

                SimpleBug account = BugTableDisplay.this.getBeanByIndex(itemId);
                account.setExtraData(cb);
                return cb;
            }
        });

        this.addGeneratedColumn("assignuserFullName", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleBug bug = BugTableDisplay.this.getBeanByIndex(itemId);
                UserLink b = new UserLink(bug.getAssignuser(), bug.getAssignuserFullName());
                return b;

            }
        });

        this.addGeneratedColumn("summary", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleBug bug = BugTableDisplay.this.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(bug.getSummary(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                fireTableEvent(new TableClickEvent(BugTableDisplay.this, bug, "summary"));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });

        this.addGeneratedColumn("duedate", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleBug bug = BugTableDisplay.this.getBeanByIndex(itemId);
                return new Label(AppContext.formatDate(bug.getDuedate()));

            }
        });

        this.setColumnExpandRatio("summary", 1);
        this.setColumnWidth("assignuserFullName",
                UIConstants.TABLE_X_LABEL_WIDTH);
        this.setColumnWidth("severity", UIConstants.TABLE_S_LABEL_WIDTH);
        this.setColumnWidth("resolution", UIConstants.TABLE_S_LABEL_WIDTH);
        this.setColumnWidth("duedate", UIConstants.TABLE_DATE_WIDTH);

        this.setWidth("100%");
    }
}
