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
		if (currentPage > 1){
			final Button firstLink = new ButtonLink("1", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					pageChange(1);
				}
			});
			firstLink.addStyleName("buttonPaging");
			controlsLayout.addComponent(firstLink);
		}
		if (currentPage >= 5){
			Label ss1 = new Label("...");
			ss1.addStyleName("buttonPaging");
			controlsLayout.addComponent(ss1);
		}
		if(currentPage > 3){
			final Button previous2 = new ButtonLink("" + (currentPage - 2), new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					pageChange(currentPage - 2);
				}
			});
			previous2.addStyleName("buttonPaging");
			controlsLayout.addComponent(previous2);
		}
		if(currentPage > 2){
			final Button previous1 = new ButtonLink("" + (currentPage - 1), new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					pageChange(currentPage - 1);
				}
			});
			previous1.addStyleName("buttonPaging");
			controlsLayout.addComponent(previous1);
		}
		
		final Button current = new ButtonLink("" + currentPage, new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				pageChange(currentPage);
			}
		});
		current.addStyleName("buttonPagingcurrent");
		
		controlsLayout.addComponent(current);
		int range = totalPage - currentPage;
		if (range >= 1){
			final Button next1 = new ButtonLink("" + (currentPage + 1), new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					pageChange(currentPage + 1);
				}
			});
			next1.addStyleName("buttonPaging");
			controlsLayout.addComponent(next1);
		}
		if (range >= 2){
			final Button next2 = new ButtonLink("" + (currentPage + 2), new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					pageChange(currentPage + 2);
				}
			});
			next2.addStyleName("buttonPaging");
			controlsLayout.addComponent(next2);
		}
		if (range >= 4){
			Label ss2 = new Label("...");
			ss2.addStyleName("buttonPaging");
			controlsLayout.addComponent(ss2);
		}
		if (range >= 3){
			Button last = new ButtonLink("" + totalPage, new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					pageChange(AbstractBeanPagedList.this.totalPage);
				}
			});
			last.addStyleName("buttonPaging");
			controlsLayout.addComponent(last);
		}
		this.addComponent(bottomLayoutWrapper);
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
