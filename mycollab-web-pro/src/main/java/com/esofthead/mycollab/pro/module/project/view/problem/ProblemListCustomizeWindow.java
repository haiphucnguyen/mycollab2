package com.esofthead.mycollab.pro.module.project.view.problem;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.premium.module.project.view.problem.ProblemTableFieldDef;
import com.esofthead.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.web.ui.table.CustomizedTableWindow;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProblemListCustomizeWindow extends CustomizedTableWindow {
    private static final long serialVersionUID = 1L;

    public ProblemListCustomizeWindow(String viewId, AbstractPagedBeanTable table) {
        super(viewId, table);
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(ProblemTableFieldDef.assignUser(),
                ProblemTableFieldDef.datedue(), ProblemTableFieldDef.description(),
                ProblemTableFieldDef.impact(), ProblemTableFieldDef.name(),
                ProblemTableFieldDef.priority(), ProblemTableFieldDef.raisedby(),
                ProblemTableFieldDef.rating(), ProblemTableFieldDef.status());
    }

}
