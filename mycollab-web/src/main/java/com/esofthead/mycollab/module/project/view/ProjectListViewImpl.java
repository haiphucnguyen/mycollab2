package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandler;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.DefaultMassItemActionHandlerContainer;
import com.esofthead.mycollab.vaadin.web.ui.CheckBoxDecor;
import com.esofthead.mycollab.vaadin.web.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
@ViewComponent
public class ProjectListViewImpl extends AbstractPageView implements ProjectListView {
    private ProjectSearchPanel projectSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private DefaultPagedBeanTable<ProjectService, ProjectSearchCriteria, SimpleProject> tableItem;
    private VerticalLayout bodyLayout;
    private DefaultMassItemActionHandlerContainer tableActionControls;
    private Label selectedItemsNumberLabel = new Label();

    public ProjectListViewImpl() {
        withMargin(true);
        projectSearchPanel = new ProjectSearchPanel();
        with(projectSearchPanel);
        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new DefaultPagedBeanTable<>(
                ApplicationContextUtil.getSpringBean(ProjectService.class),
                SimpleProject.class, VIEW_DEF_ID,
                ProjectTableFieldDef.selected(), Arrays.asList(ProjectTableFieldDef.projectname()));

        tableItem.addGeneratedColumn("selected", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                final SimpleProject item = tableItem.getBeanByIndex(itemId);
                final CheckBoxDecor cb = new CheckBoxDecor("", item.isSelected());
                cb.setImmediate(true);
                cb.addValueChangeListener(new Property.ValueChangeListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        tableItem.fireSelectItemEvent(item);
                    }
                });
                item.setExtraData(cb);
                return cb;
            }
        });
        tableItem.setWidth("100%");

        bodyLayout.addComponent(constructTableActionControls());
        bodyLayout.addComponent(tableItem);
    }

    private ComponentContainer constructTableActionControls() {
        MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%");
        layout.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);

        selectOptionButton = new SelectionOptionButton(tableItem);
        selectOptionButton.setWidthUndefined();
        layout.addComponent(selectOptionButton);

        tableActionControls = new DefaultMassItemActionHandlerContainer();

        tableActionControls.addDownloadPdfActionItem();
        tableActionControls.addDownloadExcelActionItem();
        tableActionControls.addDownloadCsvActionItem();

        tableActionControls.setVisible(false);
        tableActionControls.setWidthUndefined();

        layout.addComponent(tableActionControls);
        selectedItemsNumberLabel.setWidth("100%");
        layout.with(selectedItemsNumberLabel).withAlign(selectedItemsNumberLabel, Alignment.MIDDLE_CENTER).expand(selectedItemsNumberLabel);

        Button customizeViewBtn = new Button("", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().addWindow(new ProjectListCustomizeWindow(VIEW_DEF_ID, tableItem));

            }
        });
        customizeViewBtn.setIcon(FontAwesome.ADJUST);
        customizeViewBtn.setDescription("Layout Options");
        customizeViewBtn.addStyleName(UIConstants.BUTTON_ACTION);
        layout.with(customizeViewBtn).withAlign(customizeViewBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    @Override
    public void enableActionControls(int numOfSelectedItems) {
        tableActionControls.setVisible(true);
        selectedItemsNumberLabel.setValue(AppContext.getMessage(GenericI18Enum.TABLE_SELECTED_ITEM_TITLE, numOfSelectedItems));
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setVisible(false);
        selectOptionButton.setSelectedCheckbox(false);
        selectedItemsNumberLabel.setValue("");
    }

    @Override
    public HasSearchHandlers<ProjectSearchCriteria> getSearchHandlers() {
        return projectSearchPanel;
    }

    @Override
    public HasSelectionOptionHandlers getOptionSelectionHandlers() {
        return selectOptionButton;
    }

    @Override
    public HasMassItemActionHandler getPopupActionHandlers() {
        return tableActionControls;
    }

    @Override
    public HasSelectableItemHandlers<SimpleProject> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<ProjectSearchCriteria, SimpleProject> getPagedBeanTable() {
        return tableItem;
    }
}
