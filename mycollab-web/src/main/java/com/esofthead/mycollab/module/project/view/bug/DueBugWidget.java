package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class DueBugWidget extends Panel {
	private static final long serialVersionUID = 1L;

	private BeanList<BugService, BugSearchCriteria, SimpleBug> dataList;

	public DueBugWidget() {
		super("Due Bug");
	}

	public void setSearchCriteria(BugSearchCriteria searchCriteria) {
		dataList = new BeanList<BugService, BugSearchCriteria, SimpleBug>(
				AppContext.getSpringBean(BugService.class),
				new BugRowDisplayHandler());
		dataList.setSearchCriteria(searchCriteria);
		this.addComponent(dataList);
	}

	private class BugRowDisplayHandler implements RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(SimpleBug obj, int rowIndex) {
			GridLayout layout = new GridLayout(2,2);
			layout.setSpacing(true);
			layout.addComponent(new Embedded(null, new ThemeResource("icons/22/bug.png")), 0,
					0, 0, 1);
			layout.addComponent(new Label(obj.getSummary()));
			layout.addComponent(new Label(obj.getDescription()));
			return layout;
		}

	}
}
