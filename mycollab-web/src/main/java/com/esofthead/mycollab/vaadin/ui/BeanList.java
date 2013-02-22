package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
        extends CustomComponent {

    private static Logger log = LoggerFactory.getLogger(BeanList.class);
    private static final long serialVersionUID = 1L;
    private Object parentComponent;
    private SearchService searchService;
    private Class<? extends RowDisplayHandler<T>> rowDisplayHandler;
    private LazyLoadWrapper contentWrapper;
    private VerticalLayout contentLayout;
    private boolean isLazyLoadComponent = false;

    public BeanList(Object parentComponent, SearchService searchService,
            Class<? extends RowDisplayHandler<T>> rowDisplayHandler) {
        this(parentComponent, searchService, rowDisplayHandler, new VerticalLayout(), false);
    }
    
    public BeanList(Object parentComponent, SearchService searchService,
            Class<? extends RowDisplayHandler<T>> rowDisplayHandler, boolean isLazyLoadComponent) {
        this(parentComponent, searchService, rowDisplayHandler, new VerticalLayout(), isLazyLoadComponent);
        
    }

    public BeanList(Object parentComponent, SearchService searchService,
            Class<? extends RowDisplayHandler<T>> rowDisplayHandler, VerticalLayout contentLayout, boolean isLazyLoadComponent) {
        this.parentComponent = parentComponent;
        this.searchService = searchService;
        this.rowDisplayHandler = rowDisplayHandler;
        this.isLazyLoadComponent = isLazyLoadComponent;

        this.contentWrapper = new LazyLoadWrapper(contentLayout);
        this.contentLayout = contentLayout;
        this.setCompositionRoot(contentWrapper);
    }

    public BeanList(SearchService searchService,
            Class<? extends RowDisplayHandler<T>> rowDisplayHandler) {
        this(null, searchService, rowDisplayHandler);
    }

    public void insertItemOnTop(T item) {
        RowDisplayHandler<T> rowHandler = constructRowndisplayHandler();
        Component row = rowHandler.generateRow(item, 0);
        if (row != null && contentLayout != null) {
        	if (isLazyLoadComponent) {
        		contentLayout.addComponent(new LazyLoadWrapper(row), 0);
        	} else {
        		contentLayout.addComponent(row, 0);
        	}
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

    
    public int setSearchCriteria(S searchCriteria) {
        SearchRequest<S> searchRequest = new SearchRequest<S>(searchCriteria,
                0, Integer.MAX_VALUE);
        return setSearchRequest(searchRequest);
    }

    public int setSearchRequest(SearchRequest<S> searchRequest) {
        List<T> currentListData = searchService.findPagableListByCriteria(searchRequest);
        loadItems(currentListData);
        return currentListData.size();
    }

    public void loadItems(List<T> currentListData) {
        contentLayout.removeAllComponents();

        int i = 0;
        try {
            for (T item : currentListData) {
                RowDisplayHandler<T> rowHandler = constructRowndisplayHandler();
                
                Component row = rowHandler.generateRow(item, i);
                if (row != null) {
                	if (isLazyLoadComponent) {
                		contentLayout.addComponent(new LazyLoadWrapper(row));
                	} else {
                		contentLayout.addComponent(row);
                	}
                }

                i++;
            }
        } catch (Exception e) {
            log.error("Error while generate column display", e);
        }
    }
    
    public boolean isEmpty() {
    	return (contentLayout != null) && (contentLayout.getComponentCount() > 0);
    }

    public static interface RowDisplayHandler<T> {

        Component generateRow(T obj, int rowIndex);
    }
}
