/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public abstract class AbstractBeanPagedList <S extends SearchCriteria, T>
        extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    private final Button first, previous, next, last;
    private final Label totalPagesLabel;
    private final TextField currentPageTextField;
    private int defaultNumberSearchItems = 10;
    
    protected final VerticalLayout listContainer;
    protected final Class<? extends DefaultBeanPagedList.RowDisplayHandler<T>> rowDisplayHandler;
    protected int currentPage = 1;
    protected int totalPage = 1;
    protected int totalCount;
    protected SearchRequest<S> searchRequest;

    public AbstractBeanPagedList(
            Class<? extends DefaultBeanPagedList.RowDisplayHandler<T>> rowDisplayHandler, int defaultNumberSearchItems) {
        this.defaultNumberSearchItems = defaultNumberSearchItems;
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
                pageChange(AbstractBeanPagedList.this.currentPage - 1);
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
                pageChange(AbstractBeanPagedList.this.currentPage + 1);
            }
        });
        controlsLayout.addComponent(next);

        last = new ButtonLink(">>", new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                pageChange(AbstractBeanPagedList.this.totalPage);
            }
        });
        controlsLayout.addComponent(last);

        this.addComponent(bottomLayout);
    }

    protected void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        currentPageTextField.setValue(currentPage);
        checkButtonStatus();
    }

    protected void setTotalPage(int totalPage) {
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

    
    public void setSearchCriteria(S searchCriteria) {
        listContainer.removeAllComponents();

        searchRequest = new SearchRequest<S>(searchCriteria, currentPage,
                defaultNumberSearchItems);
        doSearch();
    }

    abstract public void doSearch();

    public static interface RowDisplayHandler<T> {

        Component generateRow(T obj, int rowIndex);
    }
    
}
