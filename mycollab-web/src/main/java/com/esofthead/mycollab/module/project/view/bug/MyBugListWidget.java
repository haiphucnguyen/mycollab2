package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.LabelHTMLDisplayWidget;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class MyBugListWidget extends BugDisplayWidget {
    private static final long serialVersionUID = 1L;

    public MyBugListWidget() {
        super(LocalizationHelper.getMessage(BugI18nEnum.MY_BUGS_WIDGET_TITLE),
                MyBugRowDisplayHandler.class);
    }

    public static class MyBugRowDisplayHandler implements
            BeanList.RowDisplayHandler<SimpleBug> {

        @Override
        public Component generateRow(final SimpleBug bug, int rowIndex) {
            GridLayout layout = new GridLayout(2, 4);
            layout.setWidth("100%");
            layout.setSpacing(false);
            layout.addComponent(
                    new Embedded(null, MyCollabResource
                            .newResource("icons/16/project/bug.png")), 0, 0, 0,
                    1);

            ButtonLink defectLink = new ButtonLink("["
                    + CurrentProjectVariables.getProject().getShortname() + "-"
                    + bug.getBugkey() + "]: " + bug.getSummary(),
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            EventBus.getInstance().fireEvent(
                                    new BugEvent.GotoRead(this, bug.getId()));
                        }
                    });
            defectLink.setWidth("100%");

            if (bug.isOverdue()) {
                defectLink.addStyleName(UIConstants.LINK_OVERDUE);
            }
            layout.addComponent(defectLink);
            layout.setColumnExpandRatio(1, 1.0f);

            LabelHTMLDisplayWidget descInfo = new LabelHTMLDisplayWidget(
                    bug.getDescription());
            descInfo.setWidth("100%");
            layout.addComponent(descInfo);

            Label dateInfo = new Label("Last updated on "
                    + AppContext.formatDateTime(bug.getLastupdatedtime()));
            dateInfo.setStyleName(UIConstants.WIDGET_ROW_METADATA);
            layout.addComponent(dateInfo, 1, 2);

            HorizontalLayout hLayoutAssigneeInfo = new HorizontalLayout();
            hLayoutAssigneeInfo.setSpacing(true);
            Label assignee = new Label("Assignee: ");
            assignee.setStyleName(UIConstants.WIDGET_ROW_METADATA);
            hLayoutAssigneeInfo.addComponent(assignee);
            hLayoutAssigneeInfo.setComponentAlignment(assignee,
                    Alignment.MIDDLE_CENTER);

            ProjectUserLink userLink = new ProjectUserLink(bug.getAssignuser(),
                    bug.getAssignUserAvatarId(), bug.getAssignuserFullName(),
                    false, true);
            hLayoutAssigneeInfo.addComponent(userLink);
            hLayoutAssigneeInfo.setComponentAlignment(userLink,
                    Alignment.MIDDLE_CENTER);
            layout.addComponent(hLayoutAssigneeInfo, 1, 3);

            CssLayout rowLayout = new CssLayout();
            rowLayout.addComponent(layout);
            rowLayout.setStyleName(UIConstants.WIDGET_ROW);
            if ((rowIndex + 1) % 2 != 0) {
                rowLayout.addStyleName("odd");
            }
            rowLayout.setWidth("100%");
            return rowLayout;
        }
    }

    @Override
    protected BugSearchParameter constructMoreDisplayFilter() {
        return new BugSearchParameter("My Bugs", searchCriteria);
    }
}
