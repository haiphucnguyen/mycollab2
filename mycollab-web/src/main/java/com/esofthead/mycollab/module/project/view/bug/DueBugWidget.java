package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.BeanList.RowDisplayHandler;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
                BugRowDisplayHandler.class);
        dataList.setSearchCriteria(searchCriteria);
        this.addComponent(dataList);
    }

    public class BugRowDisplayHandler implements RowDisplayHandler<SimpleBug> {

        @Override
        public Component generateRow(final SimpleBug obj, int rowIndex) {
            GridLayout layout = new GridLayout(2, 2);
            layout.setSpacing(true);
            layout.addComponent(new Embedded(null, new ThemeResource(
                    "icons/22/bug.png")), 0, 0, 0, 1);

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
            layout.addComponent(new Label(obj.getDescription()));
            return layout;
        }
    }
}
