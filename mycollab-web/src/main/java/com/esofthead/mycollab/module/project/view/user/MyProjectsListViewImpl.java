package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.components.ProjectSearchPanel;
import com.esofthead.mycollab.module.project.view.ProjectPageAction;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class MyProjectsListViewImpl extends AbstractView implements MyProjectsListView {

    private PagedBeanTable2<ProjectService, ProjectSearchCriteria, SimpleProject> tableItem;
    private ProjectSearchPanel searchPanel;
    private VerticalLayout listLayout;

    public MyProjectsListViewImpl() {
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
                SimpleProject.class, new String[]{"name", "shortname",
                    "actualstartdate", "projectstatus"}, new String[]{
                    "Name", "Short Name", "Start Date", "Status"});

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
                                        new PageActionChain(new ProjectPageAction(new ScreenData<Integer>(project.getId())))));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });
        
        tableItem.addGeneratedColumn("actualstartdate", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleProject project = tableItem.getBeanByIndex(itemId);
                return new Label(AppContext.formatDate(project.getActualstartdate()));

            }
        });

        tableItem.setWidth("100%");

        tableItem.setColumnExpandRatio("name", 1f);
        tableItem
                .setColumnWidth("shortname", UIConstants.TABLE_S_LABEL_WIDTH);
        tableItem
                .setColumnWidth("actualstartdate", UIConstants.TABLE_DATE_WIDTH);
        tableItem.setColumnWidth("projectstatus",
                UIConstants.TABLE_M_LABEL_WIDTH);

        listLayout.addComponent(tableItem);
    }

    @Override
    public IPagedBeanTable<ProjectSearchCriteria, SimpleProject> getPagedBeanTable() {
        return tableItem;
    }
}
