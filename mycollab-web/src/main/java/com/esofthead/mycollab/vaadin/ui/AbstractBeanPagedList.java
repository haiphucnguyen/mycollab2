/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class AbstractBeanPagedList<S extends SearchCriteria, T>
		extends VerticalLayout implements HasPagableHandlers {

	public static interface RowDisplayHandler<T> {

		Component generateRow(T obj, int rowIndex);
	}

	private static final long serialVersionUID = 1L;

	private int defaultNumberSearchItems = 10;
	protected final VerticalLayout listContainer;
	protected final RowDisplayHandler<T> rowDisplayHandler;
	protected int currentPage = 1;
	protected int totalPage = 1;
	protected int totalCount;
	protected List<T> currentListData;
	protected CssLayout controlBarWrapper;
	protected HorizontalLayout pageManagement;

	private Set<PagableHandler> pagableHandlers;

	protected SearchRequest<S> searchRequest;

	public AbstractBeanPagedList(final RowDisplayHandler<T> rowDisplayHandler,
			final int defaultNumberSearchItems) {
		this.defaultNumberSearchItems = defaultNumberSearchItems;
		this.rowDisplayHandler = rowDisplayHandler;
		listContainer = new VerticalLayout();
		this.addComponent(listContainer);
	}

	@Override
	public void addPagableHandler(final PagableHandler handler) {
		if (pagableHandlers == null) {
			pagableHandlers = new HashSet<PagableHandler>();
		}
		pagableHandlers.add(handler);
	}

	protected CssLayout createPageControls() {
		this.controlBarWrapper = new CssLayout();
		this.controlBarWrapper.setStyleName("listControl");
		this.controlBarWrapper.setWidth("100%");

		final HorizontalLayout controlBar = new HorizontalLayout();
		controlBar.setWidth("100%");
		this.controlBarWrapper.addComponent(controlBar);

		this.pageManagement = new HorizontalLayout();

		// defined layout here ---------------------------

		if (this.currentPage > 1) {
			final Button firstLink = new ButtonLink("1", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					AbstractBeanPagedList.this.pageChange(1);
				}
			});
			firstLink.addStyleName("buttonPaging");
			this.pageManagement.addComponent(firstLink);
		}
		if (this.currentPage >= 5) {
			final Label ss1 = new Label("...");
			ss1.addStyleName("buttonPaging");
			this.pageManagement.addComponent(ss1);
		}
		if (this.currentPage > 3) {
			final Button previous2 = new ButtonLink(
					"" + (this.currentPage - 2), new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractBeanPagedList.this
									.pageChange(AbstractBeanPagedList.this.currentPage - 2);
						}
					});
			previous2.addStyleName("buttonPaging");
			this.pageManagement.addComponent(previous2);
		}
		if (this.currentPage > 2) {
			final Button previous1 = new ButtonLink(
					"" + (this.currentPage - 1), new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractBeanPagedList.this
									.pageChange(AbstractBeanPagedList.this.currentPage - 1);
						}
					});
			previous1.addStyleName("buttonPaging");
			this.pageManagement.addComponent(previous1);
		}
		// Here add current ButtonLink
		final Button current = new ButtonLink("" + this.currentPage,
				new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						AbstractBeanPagedList.this
								.pageChange(AbstractBeanPagedList.this.currentPage);
					}
				});
		current.addStyleName("buttonPaging");
		current.addStyleName("buttonPagingcurrent");

		this.pageManagement.addComponent(current);
		final int range = this.totalPage - this.currentPage;
		if (range >= 1) {
			final Button next1 = new ButtonLink("" + (this.currentPage + 1),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractBeanPagedList.this
									.pageChange(AbstractBeanPagedList.this.currentPage + 1);
						}
					});
			next1.addStyleName("buttonPaging");
			this.pageManagement.addComponent(next1);
		}
		if (range >= 2) {
			final Button next2 = new ButtonLink("" + (this.currentPage + 2),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractBeanPagedList.this
									.pageChange(AbstractBeanPagedList.this.currentPage + 2);
						}
					});
			next2.addStyleName("buttonPaging");
			this.pageManagement.addComponent(next2);
		}
		if (range >= 4) {
			final Label ss2 = new Label("...");
			ss2.addStyleName("buttonPaging");
			this.pageManagement.addComponent(ss2);
		}
		if (range >= 3) {
			final Button last = new ButtonLink("" + this.totalPage,
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AbstractBeanPagedList.this
									.pageChange(AbstractBeanPagedList.this.totalPage);
						}
					});
			last.addStyleName("buttonPaging");
			this.pageManagement.addComponent(last);
		}

		this.pageManagement.setWidth(null);
		this.pageManagement.setSpacing(true);
		controlBar.addComponent(this.pageManagement);
		controlBar.setComponentAlignment(this.pageManagement,
				Alignment.MIDDLE_RIGHT);

		return this.controlBarWrapper;
	}

	abstract protected int queryTotalCount();

	abstract protected List<T> queryCurrentData();

	protected void doSearch() {
		totalCount = queryTotalCount();
		totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		if (totalPage > 1) {
			if (this.controlBarWrapper != null) {
				this.removeComponent(this.controlBarWrapper);
			}
			this.addComponent(this.createPageControls());
		} else {
			if (getComponentCount() == 2) {
				removeComponent(getComponent(1));
			}
		}

		currentListData = queryCurrentData();

		if (getComponentCount() > 0) {
			final Component comp = getComponent(0);
			if (comp instanceof LazyLoadWrapper) {
				removeComponent(comp);
			}
		}

		final CssLayout content = new CssLayout();
		content.setStyleName("beanlist-content");
		content.setWidth("100%");
		final LazyLoadWrapper wrapperComp = new LazyLoadWrapper(content);
		this.addComponent(wrapperComp, 0);

		int i = 0;
		for (final T item : currentListData) {
			final Component row = rowDisplayHandler.generateRow(item, i);
			content.addComponent(row);
			i++;
		}
	}

	private void pageChange(final int currentPage) {
		if (searchRequest != null) {
			this.currentPage = currentPage;
			searchRequest.setCurrentPage(currentPage);
			doSearch();

			if (pagableHandlers != null) {
				for (final PagableHandler handler : pagableHandlers) {
					handler.move(currentPage);
				}
			}
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

	public RowDisplayHandler<T> getRowDisplayHandler() {
		return rowDisplayHandler;
	}
}
