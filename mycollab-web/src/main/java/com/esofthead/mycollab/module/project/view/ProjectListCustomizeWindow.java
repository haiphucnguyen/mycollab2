package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.web.ui.table.CustomizedTableWindow;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class ProjectListCustomizeWindow extends CustomizedTableWindow {
    private static final long serialVersionUID = 1L;

    public ProjectListCustomizeWindow(String viewId, AbstractPagedBeanTable table) {
        super(viewId, table);
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(ProjectTableFieldDef.projectname());
    }
}
