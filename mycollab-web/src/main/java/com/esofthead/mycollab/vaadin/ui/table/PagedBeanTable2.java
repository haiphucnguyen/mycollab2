package com.esofthead.mycollab.vaadin.ui.table;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class PagedBeanTable2<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends AbstractPagedBeanTable<S, T> {

	private static final long serialVersionUID = 1L;

	private final String[] visibleColumns;
	private final String[] columnHeaders;

	private Button first, previous1, previous2, next1, next2, last, current;
	private Label ss1, ss2;

	private final SearchService searchService;
	private List<T> currentListData;
	private final Class<T> type;
	private LazyLoadWrapper tableLazyLoadContainer;

	private Object sortColumnId;
	private HorizontalLayout pageManagement;
	private boolean isAscending = true;

	public PagedBeanTable2(final SearchService searchService,
			final Class<T> type, final String[] visibleColumns,
			final String[] columnHeaders) {
		this.searchService = searchService;
		this.visibleColumns = visibleColumns;
		this.columnHeaders = columnHeaders;
		this.type = type;

		setStyleName("list-view");
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

	private CssLayout createControls() {

		final CssLayout controlBarWrapper = new CssLayout();
		controlBarWrapper.setStyleName("listControl");
		controlBarWrapper.setWidth("100%");

		final HorizontalLayout controlBar = new HorizontalLayout();
		controlBar.setWidth("100%");
		controlBarWrapper.addComponent(controlBar);

		pageManagement = new HorizontalLayout();

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

		handleAddComponent(pageManagement);

		pageManagement.setWidth(null);
		pageManagement.setSpacing(true);
		controlBar.addComponent(pageManagement);
		controlBar
				.setComponentAlignment(pageManagement, Alignment.MIDDLE_RIGHT);

		return controlBarWrapper;
	}

	private void handelBackEndAddComponent(HorizontalLayout page, int i) {
		if (totalPage == i) {
		} else if (totalPage == i + 1) {
			page.addComponent(last);
		} else if (totalPage == i + 2) {
			page.addComponent(next1);
			page.addComponent(last);
		} else if (totalPage == i + 3) {
			page.addComponent(next1);
			page.addComponent(next2);
			page.addComponent(last);
		} else if (totalPage >= i + 4) {
			page.addComponent(next1);
			page.addComponent(next2);
			page.addComponent(ss2);
			page.addComponent(last);
		}
	}

	private void handleAddComponent(HorizontalLayout page) {
		page.removeAllComponents();
		if (currentPage == 1) {
			page.addComponent(current);
			handelBackEndAddComponent(page, currentPage);
		} else if (currentPage == 2) {
			page.addComponent(first);
			page.addComponent(current);
			handelBackEndAddComponent(page, currentPage);
		} else if (currentPage == 3) {
			page.addComponent(first);
			page.addComponent(previous1);
			page.addComponent(current);
			handelBackEndAddComponent(page, currentPage);
		} else if (currentPage == 4) {
			page.addComponent(first);
			page.addComponent(previous2);
			page.addComponent(previous1);
			page.addComponent(current);
			handelBackEndAddComponent(page, currentPage);
		} else if (currentPage == 5) {
			page.addComponent(first);
			page.addComponent(ss1);
			page.addComponent(previous2);
			page.addComponent(previous1);
			page.addComponent(current);
			handelBackEndAddComponent(page, currentPage);
		}
	}

	@Override
	protected void doSearch() {
		totalCount = searchService.getTotalCount(searchRequest
				.getSearchCriteria());
		totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
		if (searchRequest.getCurrentPage() > totalPage) {
			searchRequest.setCurrentPage(totalPage);
		}

		if (totalPage > 1) {
			// Define button layout
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

			previous1 = new ButtonLink("" + (currentPage - 1),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							pageChange(currentPage - 1);
						}
					});
			previous2 = new ButtonLink("" + (currentPage - 2),
					new ClickListener() {
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
					pageChange(PagedBeanTable2.this.totalPage);
				}
			});

			addStylePagingButton();
			current.removeStyleName("buttonPaging");

			if (getComponentCount() == 0 || getComponentCount() == 1) {
				this.addComponent(createControls());
			} else {
				handleAddComponent(pageManagement);
			}

		} else {
			if (getComponentCount() == 2) {
				removeComponent(getComponent(1));
			}
		}

		currentListData = searchService
				.findPagableListByCriteria(searchRequest);
		currentViewCount = currentListData.size();

		tableItem = new Table();
		tableItem.setWidth("100%");
		final CustomComponent tableWrap = new CustomComponent(tableItem);
		tableLazyLoadContainer = new LazyLoadWrapper(tableWrap);
		tableItem.addStyleName("striped");
		tableItem.setSortDisabled(true);

		// set column generator
		for (final Object propertyId : columnGenerators.keySet()) {
			tableItem.addGeneratedColumn(propertyId,
					columnGenerators.get(propertyId));
		}

		// set column width
		for (final Object propertyId : columnWidths.keySet()) {
			tableItem.setColumnWidth(propertyId, columnWidths.get(propertyId));
		}

		if (columnExpandId != null && !columnExpandId.equals("")) {
			tableItem.setColumnExpandRatio(columnExpandId, 1.0f);
		}

		if (sortColumnId != null && !sortColumnId.equals("")) {
			tableItem.setColumnIcon(
					sortColumnId,
					isAscending ? MyCollabResource
							.newResource("icons/16/arrow_down.png")
							: MyCollabResource
									.newResource("icons/16/arrow_up.png"));
		}

		tableItem.addListener(new Table.HeaderClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void headerClick(final Table.HeaderClickEvent event) {
				final String propertyId = (String) event.getPropertyId();

				if (propertyId.equals("selected")) {
					return;
				}

				if (searchRequest != null) {
					sortColumnId = propertyId;

					final S searchCriteria = searchRequest.getSearchCriteria();
					if (searchCriteria.getOrderByField() == null) {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
						isAscending = false;
					} else if (propertyId.equals(searchCriteria
							.getOrderByField())) {
						isAscending = !isAscending;
						searchCriteria
								.setSortDirection(searchCriteria
										.getSortDirection().equals(
												SearchCriteria.ASC) ? SearchCriteria.DESC
										: SearchCriteria.ASC);
					} else {
						searchCriteria.setOrderByField(propertyId);
						searchCriteria.setSortDirection(SearchCriteria.DESC);
						isAscending = false;
					}

					PagedBeanTable2.this.setSearchCriteria(searchCriteria);
				}
			}
		});

		final BeanItemContainer<T> container = new BeanItemContainer<T>(type,
				currentListData);
		tableItem.setPageLength(0);
		tableItem.setContainerDataSource(container);
		tableItem.setVisibleColumns(visibleColumns);
		tableItem.setColumnHeaders(columnHeaders);
		tableItem.setWidth("100%");

		if (getComponentCount() > 0) {
			final Component component0 = getComponent(0);
			if (component0 instanceof LazyLoadWrapper) {
				replaceComponent(component0, tableLazyLoadContainer);
			} else {
				this.addComponent(tableLazyLoadContainer, 0);
			}
		} else {
			this.addComponent(tableLazyLoadContainer, 0);
		}

	}

}
