package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class BugDisplayWidget extends Depot {

	private static final long serialVersionUID = 1L;
	public static int MAX_ITEM_DISPLAY = 10;
	private BugSearchCriteria searchCriteria;
	private final BeanList<BugService, BugSearchCriteria, SimpleBug> dataList;

	public BugDisplayWidget(String title,
			Class<? extends RowDisplayHandler<SimpleBug>> rowDisplayHandler) {
		super(title, new VerticalLayout());

		dataList = new BeanList<BugService, BugSearchCriteria, SimpleBug>(
				AppContext.getSpringBean(BugService.class), rowDisplayHandler);
		bodyContent.addComponent(dataList);

		Button moreBtn = new Button("More ...", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("SEACR CRITERA: " + BeanUtility.printBeanObj(searchCriteria));
				EventBus.getInstance().fireEvent(
						new BugEvent.GotoList(BugDisplayWidget.this,
								new ScreenData.Search<BugSearchCriteria>(
										searchCriteria)));
			}
		});
		moreBtn.setStyleName(UIConstants.THEME_LINK);
		bodyContent.addComponent(moreBtn);
		((VerticalLayout) bodyContent).setComponentAlignment(moreBtn,
				Alignment.TOP_RIGHT);
		bodyContent.setStyleName(UIConstants.BUG_LIST);
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		SearchRequest<BugSearchCriteria> searchRequest = new SearchRequest<BugSearchCriteria>(
				searchCriteria, 0, MAX_ITEM_DISPLAY);
		dataList.setSearchRequest(searchRequest);

	}
}
