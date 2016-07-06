package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.TableViewField;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;
import com.mycollab.vaadin.web.ui.table.CustomizedTableWindow;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RiskListCustomizeWindow extends CustomizedTableWindow {
    private static final long serialVersionUID = 1L;

    public RiskListCustomizeWindow(AbstractPagedBeanTable table) {
        super(ProjectTypeConstants.RISK, table);
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
