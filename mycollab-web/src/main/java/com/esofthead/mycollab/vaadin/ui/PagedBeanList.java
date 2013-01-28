package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PagedBeanList<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
        extends VerticalLayout implements HasPagableHandlers {

    private static final long serialVersionUID = 1L;
    private SearchService searchService;
    private SearchRequest<S> searchRequest;
    private int currentPage = 1;
    private int totalPage = 1;
    private int totalCount;
    private int currentViewCount;
    private ComboBox itemsPerPageSelect;
    private Button first, previous, next, last;
    private Label totalPagesLabel;
    private TextField currentPageTextField;
    private List<T> currentListData;
    private Set<PagableHandler> pagableHandlers;
    private RowDisplayHandler<T> rowDisplayHandler;
    
    public PagedBeanList(SearchService searchService,
            RowDisplayHandler<T> rowDisplayHandler) {
        this.searchService = searchService;
        this.rowDisplayHandler = rowDisplayHandler;

        
        this.addComponent(createPageControls());
    }

    public Layout createPageControls() {
        Label itemsPerPageLabel = new Label("Items per page:");
        itemsPerPageSelect = new ComboBox();

        itemsPerPageSelect.addItem("5");
        itemsPerPageSelect.addItem("10");
        itemsPerPageSelect.addItem("25");
        itemsPerPageSelect.addItem("50");
        itemsPerPageSelect.addItem("100");
        itemsPerPageSelect.addItem("600");
        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth("50px");
        itemsPerPageSelect.addListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                Integer numberOfItems = Integer
                        .parseInt((String) itemsPerPageSelect.getValue());
                displayItemChange(numberOfItems);
            }
        });

        Label pageLabel = new Label("Page:&nbsp;", Label.CONTENT_XHTML);
        currentPageTextField = new TextField();
        currentPageTextField.setValue(String.valueOf(currentPage));
        currentPageTextField.addValidator(new IntegerValidator(null));
        Label separatorLabel = new Label("&nbsp;/&nbsp;", Label.CONTENT_XHTML);
        totalPagesLabel = new Label(String.valueOf(totalPage),
                Label.CONTENT_XHTML);
        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        currentPageTextField.addListener(new ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            @Override
            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
            }
        });
        pageLabel.setWidth(null);
        currentPageTextField.setWidth("20px");
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.setStyleName("listControl");
        HorizontalLayout pageSize = new HorizontalLayout();
        HorizontalLayout pageManagement = new HorizontalLayout();
        first = new ButtonLink("<<", new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                pageChange(1);
            }
        });
        previous = new ButtonLink("<", new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                pageChange(PagedBeanList.this.currentPage - 1);
            }
        });
        next = new ButtonLink(">", new ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            @Override
            public void buttonClick(ClickEvent event) {
                pageChange(PagedBeanList.this.currentPage + 1);
            }
        });
        last = new ButtonLink(">>", new ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            @Override
            public void buttonClick(ClickEvent event) {
                pageChange(PagedBeanList.this.totalPage);
            }
        });

        itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
        itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
        pageLabel.addStyleName("pagedtable-pagecaption");
        currentPageTextField.addStyleName("pagedtable-pagefield");
        separatorLabel.addStyleName("pagedtable-separator");
        totalPagesLabel.addStyleName("pagedtable-total");
        first.addStyleName("pagedtable-first");
        previous.addStyleName("pagedtable-previous");
        next.addStyleName("pagedtable-next");
        last.addStyleName("pagedtable-last");

        itemsPerPageLabel.addStyleName("pagedtable-label");
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName("pagedtable-label");
        currentPageTextField.addStyleName("pagedtable-label");
        separatorLabel.addStyleName("pagedtable-label");
        totalPagesLabel.addStyleName("pagedtable-label");
        first.addStyleName("pagedtable-button");
        previous.addStyleName("pagedtable-button");
        next.addStyleName("pagedtable-button");
        last.addStyleName("pagedtable-button");

        pageSize.addComponent(itemsPerPageLabel);
        pageSize.addComponent(itemsPerPageSelect);
        pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(itemsPerPageSelect,
                Alignment.MIDDLE_LEFT);
        pageSize.setSpacing(true);
        pageManagement.addComponent(first);
        pageManagement.addComponent(previous);
        pageManagement.addComponent(pageLabel);
        pageManagement.addComponent(currentPageTextField);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.addComponent(next);
        pageManagement.addComponent(last);
        pageManagement.setComponentAlignment(first, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(previous, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(currentPageTextField,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(separatorLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel,
                Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(next, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(last, Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);
        controlBar.addComponent(pageSize);
        controlBar.addComponent(pageManagement);
        controlBar.setComponentAlignment(pageManagement,
                Alignment.MIDDLE_CENTER);
        controlBar.setWidth("100%");
        controlBar.setExpandRatio(pageSize, 1);

        itemsPerPageSelect.select("25");
        return controlBar;
    }

    private void pageChange(int currentPage) {
        if (searchRequest != null) {
            this.currentPage = currentPage;
            searchRequest.setCurrentPage(currentPage);
            doSearch();

            if (pagableHandlers != null) {
                for (PagableHandler handler : pagableHandlers) {
                    handler.move(currentPage);
                }
            }
        }
    }

    private void displayItemChange(int numOfItems) {
        if (searchRequest != null) {
            searchRequest.setNumberOfItems(numOfItems);
            doSearch();
        }
    }

    public void setSearchCriteria(S searchCriteria) {
        searchRequest = new SearchRequest<S>(searchCriteria, currentPage,
                SearchRequest.DEFAULT_NUMBER_SEARCH_ITEMS);
        doSearch();
    }

    @SuppressWarnings("unchecked")
    private void doSearch() {
        totalCount = searchService.getTotalCount(searchRequest
                .getSearchCriteria());
        int totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
        if (searchRequest.getCurrentPage() > totalPage) {
            searchRequest.setCurrentPage(totalPage);
        }

        currentListData = searchService
                .findPagableListByCriteria(searchRequest);
        currentViewCount = currentListData.size();

        this.setCurrentPage(currentPage);
        this.setTotalPage(totalPage);

        Component comp = this.getComponent(0);
        if (comp instanceof LazyLoadWrapper) {
          this.removeComponent(comp);  
        } 
        
        VerticalLayout content = new VerticalLayout();
        LazyLoadWrapper wrapperComp = new LazyLoadWrapper(content);
        this.addComponent(wrapperComp, 0);

        int i = 0;
        for (T item : currentListData) {
            Component row = rowDisplayHandler.generateRow(item, i);
            content.addComponent(row);
            i++;
        }
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

    @Override
    public void addPagableHandler(PagableHandler handler) {
        if (pagableHandlers == null) {
            pagableHandlers = new HashSet<PagableHandler>();
        }
        pagableHandlers.add(handler);
    }

    public interface RowDisplayHandler<T> {

        Component generateRow(T obj, int rowIndex);
    }
}
