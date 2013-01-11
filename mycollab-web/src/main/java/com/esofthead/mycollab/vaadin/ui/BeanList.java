package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;

public class BeanList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
        extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private Object parentComponent;
    private SearchService searchService;
    private Class<? extends RowDisplayHandler<T>> rowDisplayHandler;

    public BeanList(Object parentComponent, SearchService searchService,
            Class<? extends RowDisplayHandler<T>> rowDisplayHandler) {
        this.parentComponent = parentComponent;
        this.searchService = searchService;
        this.rowDisplayHandler = rowDisplayHandler;
    }

    public BeanList(SearchService searchService,
            Class<? extends RowDisplayHandler<T>> rowDisplayHandler) {
        this(null, searchService, rowDisplayHandler);
    }

    public void insertItemOnTop(T item) {
        RowDisplayHandler<T> rowHandler = constructRowndisplayHandler();
        Component row = rowHandler.generateRow(item, 0);
        if (row != null) {
            this.addComponent(row, 0);
        }
    }

    private RowDisplayHandler<T> constructRowndisplayHandler() {
        RowDisplayHandler<T> rowHandler = null;

        try {

            if (rowDisplayHandler.getEnclosingClass() != null && !Modifier.isStatic(rowDisplayHandler.getModifiers())) {

                Constructor constructor = rowDisplayHandler.getDeclaredConstructor(rowDisplayHandler.getEnclosingClass());
                rowHandler = (RowDisplayHandler<T>) constructor.newInstance(parentComponent);
            } else {
                rowHandler = rowDisplayHandler.newInstance();
            }
            return rowHandler;
        } catch (Exception e) {
            throw new MyCollabException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public int setSearchCriteria(S searchCriteria) {
        SearchRequest<S> searchRequest = new SearchRequest<S>(searchCriteria,
                0, Integer.MAX_VALUE);
        return setSearchRequest(searchRequest);
    }

    public int setSearchRequest(SearchRequest<S> searchRequest) {
        this.removeAllComponents();
        List<T> currentListData = searchService
                .findPagableListByCriteria(searchRequest);
        int i = 0;
        try {
            for (T item : currentListData) {

                RowDisplayHandler<T> rowHandler = constructRowndisplayHandler();
                Component row = rowHandler.generateRow(item, i);
                if (row != null) {
                    this.addComponent(row);
                }

                i++;
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        }

        return currentListData.size();
    }

    public interface RowDisplayHandler<T> {

        Component generateRow(T obj, int rowIndex);
    }
}
