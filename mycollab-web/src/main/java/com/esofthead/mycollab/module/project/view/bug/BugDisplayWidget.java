package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public abstract class BugDisplayWidget extends Depot {

	private static final long serialVersionUID = 1L;
	public static int MAX_ITEM_DISPLAY = 5;

	protected BugSearchCriteria searchCriteria;
	private BeanList<BugService, BugSearchCriteria, SimpleBug> dataList;
	private Button moreBtn;

	public BugDisplayWidget(
			final String title,
			final Class<? extends RowDisplayHandler<SimpleBug>> rowDisplayHandler) {
		super(title, new VerticalLayout());

		dataList = new BeanList<BugService, BugSearchCriteria, SimpleBug>(
				AppContext.getSpringBean(BugService.class), rowDisplayHandler);
		bodyContent.addComponent(dataList);
		bodyContent.setStyleName(UIConstants.BUG_LIST);
		
	}

	protected abstract BugSearchParameter constructMoreDisplayFilter();

	public void setSearchCriteria(final BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		final SearchRequest<BugSearchCriteria> searchRequest = new SearchRequest<BugSearchCriteria>(
				searchCriteria, 0, BugDisplayWidget.MAX_ITEM_DISPLAY);
		final int displayItemsCount = dataList.setSearchRequest(searchRequest);
		if (displayItemsCount == BugDisplayWidget.MAX_ITEM_DISPLAY) {
			moreBtn = new Button("More ...", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					EventBus.getInstance().fireEvent(
							new BugEvent.GotoList(BugDisplayWidget.this,
									new BugScreenData.Search(
											constructMoreDisplayFilter())));
				}
			});
			moreBtn.setStyleName(UIConstants.THEME_LINK);
			final VerticalLayout widgetFooter = new VerticalLayout();
			widgetFooter.addStyleName("widget-footer");
			widgetFooter.setWidth("100%");
			widgetFooter.addComponent(moreBtn);
			widgetFooter.setComponentAlignment(moreBtn, Alignment.TOP_RIGHT);
			bodyContent.addComponent(widgetFooter);
		}
	}
}
