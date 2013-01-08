/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class BeanPagedList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
        extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    private int currentPage = 1;
    private int totalPage = 1;
    private int totalCount;
    private SearchRequest<S> searchRequest;
    private final Button first, previous, next, last;
    private final Label totalPagesLabel;
    private final TextField currentPageTextField;
    private final SearchService searchService;
    private final VerticalLayout listContainer;
    private final Class<? extends BeanPagedList.RowDisplayHandler<T>> rowDisplayHandler;

    public BeanPagedList(
            SearchService searchService,
            Class<? extends BeanPagedList.RowDisplayHandler<T>> rowDisplayHandler) {
        this.searchService = searchService;
        this.rowDisplayHandler = rowDisplayHandler;
        listContainer = new VerticalLayout();
        this.addComponent(listContainer);

        HorizontalLayout bottomLayout = new HorizontalLayout();
        bottomLayout.setWidth("100%");

        HorizontalLayout controlsLayout = new HorizontalLayout();
        controlsLayout.setSizeUndefined();
        bottomLayout.addComponent(controlsLayout);
        bottomLayout.setComponentAlignment(controlsLayout,
                Alignment.MIDDLE_RIGHT);

        first = new ButtonLink("<<", new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                pageChange(1);
            }
        });
        controlsLayout.addComponent(first);

        previous = new ButtonLink("<", new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                pageChange(BeanPagedList.this.currentPage - 1);
            }
        });
        controlsLayout.addComponent(previous);

        currentPageTextField = new TextField();
        currentPageTextField.setWidth("20px");
        currentPageTextField.setStyleName("small");
        currentPageTextField.setValue(String.valueOf(currentPage));
        currentPageTextField.addValidator(new IntegerValidator(null));
        currentPageTextField.setImmediate(true);
        controlsLayout.addComponent(currentPageTextField);

        Label separatorLabel = new Label("&nbsp;/&nbsp;", Label.CONTENT_XHTML);
        controlsLayout.addComponent(separatorLabel);

        totalPagesLabel = new Label(String.valueOf(totalPage),
                Label.CONTENT_XHTML);
        controlsLayout.addComponent(totalPagesLabel);

        next = new ButtonLink(">", new Button.ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                pageChange(BeanPagedList.this.currentPage + 1);
            }
        });
        controlsLayout.addComponent(next);

        last = new ButtonLink(">>", new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                pageChange(BeanPagedList.this.totalPage);
            }
        });
        controlsLayout.addComponent(last);

        this.addComponent(bottomLayout);
    }

    private void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        currentPageTextField.setValue(currentPage);
        checkButtonStatus();
    }

    private void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        totalPagesLabel.setValue(String.valueOf(totalPage));
        checkButtonStatus();
    }

    private void checkButtonStatus() {
        if (this.currentPage == 1) {
            this.previous.setEnabled(false);
            this.first.setEnabled(false);
        } else {
            this.previous.setEnabled(true);
            this.first.setEnabled(true);
        }

        if (this.currentPage == totalPage) {
            this.last.setEnabled(false);
            this.next.setEnabled(false);
        } else {
            this.last.setEnabled(true);
            this.next.setEnabled(true);
        }
    }

    private void pageChange(int currentPage) {
        if (searchRequest != null) {
            this.currentPage = currentPage;
            searchRequest.setCurrentPage(currentPage);
            doSearch();
        }
    }

    @SuppressWarnings("unchecked")
    public void setSearchCriteria(S searchCriteria) {
        listContainer.removeAllComponents();

        searchRequest = new SearchRequest<S>(searchCriteria, currentPage,
                SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
        doSearch();
    }

    private void doSearch() {
        totalCount = searchService.getTotalCount(searchRequest
                .getSearchCriteria());
        totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
        if (searchRequest.getCurrentPage() > totalPage) {
            searchRequest.setCurrentPage(totalPage);
        }

        this.setCurrentPage(currentPage);
        this.setTotalPage(totalPage);

        List<T> currentListData = searchService
                .findPagableListByCriteria(searchRequest);
        listContainer.removeAllComponents();
        int i = 0;
        try {
            for (T item : currentListData) {
                BeanPagedList.RowDisplayHandler<T> rowHandler = rowDisplayHandler
                        .newInstance();
                Component row = rowHandler.generateRow(item, i);
                listContainer.addComponent(row);
                i++;
            }
        } catch (Exception e) {
            throw new MyCollabException(e);
        }
    }

    public interface RowDisplayHandler<T> {

        Component generateRow(T obj, int rowIndex);
    }
}
