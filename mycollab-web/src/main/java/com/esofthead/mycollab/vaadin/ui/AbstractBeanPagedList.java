/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class AbstractBeanPagedList<S extends SearchCriteria, T>
		extends VerticalLayout {

	public static interface RowDisplayHandler<T> {

		Component generateRow(T obj, int rowIndex);
	}

	private static final long serialVersionUID = 1L;
	private final Button first, previous1, previous2, next1, next2, last , current;
	private final Label ss1, ss2;

	private int defaultNumberSearchItems = 10;
	protected final VerticalLayout listContainer;
	protected final Class<? extends DefaultBeanPagedList.RowDisplayHandler<T>> rowDisplayHandler;
	protected int currentPage = 1;
	protected int totalPage = 1;
	protected int totalCount;

	protected SearchRequest<S> searchRequest;

	public AbstractBeanPagedList(
			final Class<? extends DefaultBeanPagedList.RowDisplayHandler<T>> rowDisplayHandler,
			final int defaultNumberSearchItems) {
		this.defaultNumberSearchItems = defaultNumberSearchItems;
		this.rowDisplayHandler = rowDisplayHandler;
		listContainer = new VerticalLayout();
		this.addComponent(listContainer);

		final CssLayout bottomLayoutWrapper = new CssLayout();
		bottomLayoutWrapper.setWidth("100%");
		bottomLayoutWrapper.addStyleName("listControl");

		final HorizontalLayout bottomLayout = new HorizontalLayout();
		bottomLayout.setWidth("100%");
		bottomLayoutWrapper.addComponent(bottomLayout);

		final HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setSizeUndefined();
		bottomLayout.addComponent(controlsLayout);
		bottomLayout.setComponentAlignment(controlsLayout,
				Alignment.MIDDLE_RIGHT);
		//add for controlsLayout 
		ss1 = new Label("...");
		ss2 = new Label("...");

		current = new ButtonLink("" + currentPage, new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageChange(currentPage);
			}
		});

		first = new ButtonLink("1", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(1);
			}
		});

		previous1 = new ButtonLink("" + (currentPage - 1), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage - 1);
			}
		});
		previous2 = new ButtonLink("" + (currentPage - 2), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage - 2);
			}
		});
		next1 = new ButtonLink("" + (currentPage + 1), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage + 1);
			}
		});
		next2 = new ButtonLink("" + (currentPage + 2), new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(currentPage + 2);
			}
		});
		last = new ButtonLink("" + totalPage, new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				pageChange(AbstractBeanPagedList.this.totalPage);
			}
		});
		first.addStyleName("pagedtable-first");
		previous1.addStyleName("pagedtable-previous");
		previous2.addStyleName("pagedtable-previous");
		next1.addStyleName("pagedtable-next");
		next2.addStyleName("pagedtable-next");
		last.addStyleName("pagedtable-last");

		first.addStyleName("pagedtable-button");
		previous1.addStyleName("pagedtable-button");
		previous2.addStyleName("pagedtable-button");
		next1.addStyleName("pagedtable-button");
		next2.addStyleName("pagedtable-button");
		last.addStyleName("pagedtable-button");

		addStylePagingButton();
		current.removeStyleName("buttonPaging");
		current.addStyleName("buttonPagingcurrent");
		
		handleAddComponent(controlsLayout, currentPage);

		this.addComponent(bottomLayoutWrapper);
	}
	private void handleAddComponent(HorizontalLayout page, int currentPage) {
		page.removeAllComponents();
		int first = 1, pre1 = currentPage - 1, pre2 = currentPage - 2;
		if (first < currentPage)
			page.addComponent(this.first);
		if (currentPage >= 5)
			page.addComponent(ss1);
		if (first < pre2)
			page.addComponent(previous2);
		if (first < pre1)
			page.addComponent(previous1);
		page.addComponent(current);
		int range = totalPage - currentPage;
		if (range >= 1)
			page.addComponent(this.next1);
		if (range >= 2)
			page.addComponent(this.next2);
		if (range >= 4)
			page.addComponent(ss2);
		if (range >= 3)
			page.addComponent(last);
	}
	
	private void addStylePagingButton() {
		if (first != null)
			first.addStyleName("buttonPaging");
		if (last != null)
			last.addStyleName("buttonPaging");
		if (ss1 != null)
			ss1.addStyleName("buttonPaging");
		if (ss2 != null)
			ss2.addStyleName("buttonPaging");
		if (next1 != null)
			next1.addStyleName("buttonPaging");
		if (next2 != null)
			next2.addStyleName("buttonPaging");
		if (previous2 != null)
			previous2.addStyleName("buttonPaging");
		if (previous1 != null)
			previous1.addStyleName("buttonPaging");
		if (current != null)
			current.addStyleName("buttonPaging");
	}
	
	abstract public void doSearch();

	private void pageChange(final int currentPage) {
		if (searchRequest != null) {
			this.currentPage = currentPage;
			searchRequest.setCurrentPage(currentPage);
			doSearch();
		}
	}

	protected void setCurrentPage(final int currentPage) {
		this.currentPage = currentPage;
	}

	public void setSearchCriteria(final S searchCriteria) {
		listContainer.removeAllComponents();

		searchRequest = new SearchRequest<S>(searchCriteria, currentPage,
				defaultNumberSearchItems);
		doSearch();
	}

	protected void setTotalPage(final int totalPage) {
		this.totalPage = totalPage;
	}

}
