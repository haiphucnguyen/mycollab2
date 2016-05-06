package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.web.ui.table.CustomizedTableWindow;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RiskListCustomizeWindow extends CustomizedTableWindow {
    private static final long serialVersionUID = 1L;

    public RiskListCustomizeWindow(String viewId, AbstractPagedBeanTable table) {
        super(viewId, table);
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(RiskTableFieldDef.assignUser(),
                RiskTableFieldDef.consequence(), RiskTableFieldDef.duedate(),
                RiskTableFieldDef.description(), RiskTableFieldDef.name(),
                RiskTableFieldDef.probability(), RiskTableFieldDef.raisedBy(),
                RiskTableFieldDef.rating(), RiskTableFieldDef.response(),
                RiskTableFieldDef.status());
    }
}
