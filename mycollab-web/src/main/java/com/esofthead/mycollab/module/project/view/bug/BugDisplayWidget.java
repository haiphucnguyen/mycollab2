package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class BugDisplayWidget extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	public static int MAX_ITEM_DISPLAY = 10;
	private BugSearchCriteria searchCriteria;
	private final BeanList<BugService, BugSearchCriteria, SimpleBug> dataList;

	public BugDisplayWidget(String title) {
		Label titleLbl = new Label(title);
		titleLbl.setStyleName("widget-title");
		this.addComponent(titleLbl);
		dataList = new BeanList<BugService, BugSearchCriteria, SimpleBug>(
				AppContext.getSpringBean(BugService.class),
				BugRowDisplayHandler.class);
		this.addComponent(dataList);

		Button moreBtn = new Button("More ...", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new BugEvent.GotoList(BugDisplayWidget.this,
								new ScreenData.Search<BugSearchCriteria>(
										searchCriteria)));
			}
		});
		moreBtn.setStyleName(UIConstants.THEME_LINK);
		this.addComponent(moreBtn);
		this.setComponentAlignment(moreBtn, Alignment.TOP_RIGHT);
		this.setStyleName("bug-list");
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		SearchRequest<BugSearchCriteria> searchRequest = new SearchRequest<BugSearchCriteria>(
				searchCriteria, 0, MAX_ITEM_DISPLAY);
		dataList.setSearchRequest(searchRequest);

	}

	public static class BugRowDisplayHandler implements
			RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug obj, int rowIndex) {
			GridLayout layout = new GridLayout(2, 2);
			layout.setWidth("100%");
			layout.setSpacing(true);
			layout.addComponent(new Embedded(null, new ThemeResource(
					"icons/22/project/bug.png")), 0, 0, 0, 1);

			ButtonLink defectLink = new ButtonLink(obj.getSummary(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(this, obj.getId()));
						}
					});
			layout.addComponent(defectLink);
			layout.setColumnExpandRatio(1, 1.0f);
			layout.addComponent(new Label(obj.getDescription()), 1, 1, 1, 1);
			CssLayout rowLayout = new CssLayout();
			rowLayout.addComponent(layout);
			rowLayout.setStyleName("widget-row");
			rowLayout.setWidth("100%");
			return rowLayout;
		}
	}
}
