package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.CustomViewStoreService;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Window;
import org.vaadin.tepi.listbuilder.ListBuilder;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public abstract class CustomizeReportOutputWindow<S extends SearchCriteria, B extends ValuedBean> extends Window {
    private VariableInjector<S> variableInjector;
    private ListBuilder listBuilder;
    private String viewId;

    public CustomizeReportOutputWindow(VariableInjector<S> variableInjector) {
        super("Export");
        this.setModal(true);
        this.setResizable(false);
        this.center();
        this.variableInjector = variableInjector;

        MVerticalLayout contentLayout = new MVerticalLayout();
        setContent(contentLayout);

        listBuilder.setImmediate(true);
        listBuilder.setColumns(0);
        listBuilder.setLeftColumnCaption(AppContext.getMessage(GenericI18Enum.OPT_AVAILABLE_COLUMNS));
        listBuilder.setRightColumnCaption(AppContext.getMessage(GenericI18Enum.OPT_VIEW_COLUMNS));
        listBuilder.setWidth(100, Sizeable.Unit.PERCENTAGE);
        listBuilder.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
        final BeanItemContainer<TableViewField> container = new BeanItemContainer<>(TableViewField.class, this.getAvailableColumns());
        listBuilder.setContainerDataSource(container);
        Iterator<TableViewField> iterator = getAvailableColumns().iterator();
        while (iterator.hasNext()) {
            TableViewField field = iterator.next();
            listBuilder.setItemCaption(field, AppContext.getMessage(field.getDescKey()));
        }
        this.setSelectedViewColumns();
        contentLayout.with(listBuilder).withAlign(listBuilder, Alignment.TOP_CENTER);
    }

    private void setSelectedViewColumns() {
        final Collection<String> viewColumnIds = this.getViewColumns();

        final BeanItemContainer<TableViewField> container = (BeanItemContainer<TableViewField>) listBuilder.getContainerDataSource();
        final Collection<TableViewField> itemIds = container.getItemIds();
        final List<TableViewField> selectedColumns = new ArrayList<>();

        for (String viewColumnId : viewColumnIds) {
            for (final TableViewField viewField : itemIds) {
                if (viewColumnId.equals(viewField.getField())) {
                    selectedColumns.add(viewField);
                }
            }
        }

        listBuilder.setValue(selectedColumns);
    }

    protected Collection<String> getViewColumns() {
        CustomViewStoreService customViewStoreService = AppContextUtil.getSpringBean(CustomViewStoreService.class);
        CustomViewStore viewLayoutDef = customViewStoreService.getViewLayoutDef(AppContext.getAccountId(),
                AppContext.getUsername(), viewId);
        return null;
    }

    abstract protected Collection<TableViewField> getAvailableColumns();

    abstract protected B buildSampleData();
}
