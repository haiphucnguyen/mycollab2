/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class BugTableDisplay extends PagedBeanTable2<BugService, BugSearchCriteria, SimpleBug> {
	private static final long serialVersionUID = 1L;

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
						return new ProjectUserLink(bug.getAssignuser(), bug.getAssignuserFullName());

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
                
                if (StringUtil.isNotNullOrEmpty(bug.getPriority())) {
                	ThemeResource iconPriority = new ThemeResource(BugPriorityComboBox.PRIORITY_MAJOR_IMG);
                    
                    if (BugPriorityComboBox.PRIORITY_BLOCKER.equals(bug.getPriority())) {
                    	iconPriority = new ThemeResource(BugPriorityComboBox.PRIORITY_BLOCKER_IMG);
                    } else if (BugPriorityComboBox.PRIORITY_CRITICAL.equals(bug.getPriority())) {
                    	iconPriority = new ThemeResource(BugPriorityComboBox.PRIORITY_CRITICAL_IMG);
                    } else if (BugPriorityComboBox.PRIORITY_MAJOR.equals(bug.getPriority())) {
                    	iconPriority = new ThemeResource(BugPriorityComboBox.PRIORITY_MAJOR_IMG);
                    } else if (BugPriorityComboBox.PRIORITY_MINOR.equals(bug.getPriority())) {
                    	iconPriority = new ThemeResource(BugPriorityComboBox.PRIORITY_MINOR_IMG);
                    } else if (BugPriorityComboBox.PRIORITY_TRIVIAL.equals(bug.getPriority())) {
                    	iconPriority = new ThemeResource(BugPriorityComboBox.PRIORITY_TRIVIAL_IMG);
                    }
                    
                    b.setIcon(iconPriority);
                }
                
                b.addStyleName("medium-text");
                if (BugStatusConstants.CLOSE.equals(bug.getStatus()) || BugStatusConstants.WONFIX.equals(bug.getStatus())) {
                	b.addStyleName(UIConstants.LINK_COMPLETED);
                } else if (bug.getDuedate() != null && (bug.getDuedate().before(new GregorianCalendar().getTime()))) {
                    b.addStyleName(UIConstants.LINK_OVERDUE);
                }
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
        this.setColumnWidth("resolution", UIConstants.TABLE_M_LABEL_WIDTH);
        this.setColumnWidth("duedate", UIConstants.TABLE_DATE_WIDTH);

        this.setWidth("100%");
    }
}
