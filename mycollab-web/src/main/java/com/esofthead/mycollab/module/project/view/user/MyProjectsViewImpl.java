package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.components.ProjectSearchPanel;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class MyProjectsViewImpl extends AbstractView implements MyProjectsView {

    private PagedBeanTable2<ProjectService, ProjectSearchCriteria, SimpleProject> tableItem;
    private ProjectSearchPanel searchPanel;
    private VerticalLayout listLayout;

    public MyProjectsViewImpl() {
        this.setSpacing(true);
        this.setMargin(true);

        searchPanel = new ProjectSearchPanel();
        this.addComponent(searchPanel);

        listLayout = new VerticalLayout();
        listLayout.setSpacing(true);
        this.addComponent(listLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new PagedBeanTable2<ProjectService, ProjectSearchCriteria, SimpleProject>(
                AppContext.getSpringBean(ProjectService.class),
                SimpleProject.class, new String[]{"name", "accountName",
                    "projecttype", "projectstatus"}, new String[]{
                    "Name", "Account", "Type", "Status"});

        tableItem.addGeneratedColumn("name", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleProject project = tableItem.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(project.getName(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new ProjectEvent.GotoMyProject(this,
                                        project));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });

        tableItem.setWidth("100%");

        tableItem.setColumnExpandRatio("name", 1f);
        tableItem
                .setColumnWidth("accountName", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem
                .setColumnWidth("projecttype", UIConstants.TABLE_M_LABEL_WIDTH);
        tableItem.setColumnWidth("projectstatus",
                UIConstants.TABLE_M_LABEL_WIDTH);

        listLayout.addComponent(tableItem);
    }

    @Override
    public IPagedBeanTable<ProjectService, ProjectSearchCriteria, SimpleProject> getPagedBeanTable() {
        return tableItem;
    }
}
