/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author haiphucnguyen
 */
public class DueBugWidget extends BugDisplayWidget {
	private static final long serialVersionUID = 1L;
	private static LabelStringGenerator menuLinkGenerator = new RecentBugLinkLabelStringGenerator();
	private static boolean hasShowMore;
	private static int CUT_STRING = 50;

	public DueBugWidget() {
		super("Due Bugs", DueBugRowDisplayHandler.class);
	}

	public static class DueBugRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug obj, int rowIndex) {
			GridLayout layout = new GridLayout(2, 3);
			layout.setWidth("100%");
			layout.setSpacing(false);
			layout.addComponent(new Embedded(null, new ThemeResource(
					"icons/22/project/bug.png")), 0, 0, 0, 1);

			ButtonLink defectLink = new ButtonLink(obj.getSummary(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(this, obj.getId()));
						}
					});
			defectLink.setWidth("100%");
			layout.addComponent(defectLink);
			layout.setColumnExpandRatio(1, 1.0f);

			try {
				HorizontalLayout descriptionLayout = new HorizontalLayout();
				descriptionLayout.setWidth("100%");
				String cutDes = menuLinkGenerator.handleText(obj
						.getDescription());
				final Label descInfo = new Label(cutDes);
				descInfo.setDescription(obj.getDescription());
				descriptionLayout.addComponent(descInfo);
				if (cutDes.length() > CUT_STRING) {
					hasShowMore = true;
					descInfo.setWidth("352px");
					final Button btnPlusLess = new Button();
					btnPlusLess.setStyleName(UIConstants.PLUS_BG);
					btnPlusLess.addListener(new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							if (hasShowMore) {
								descInfo.setValue(obj.getDescription());
								btnPlusLess.setStyleName(UIConstants.MINUS_BG);
							} else {
								descInfo.setValue(menuLinkGenerator
										.handleText(obj.getDescription()));
								btnPlusLess.setStyleName(UIConstants.PLUS_BG);
							}
							hasShowMore = !hasShowMore;
						}
					});

					descriptionLayout.addComponent(btnPlusLess);
				}

				layout.addComponent(descriptionLayout, 1, 1);
			} catch (Exception e) {
			}

			Label dateInfo = new Label("due on "
					+ AppContext.formatDate(obj.getDuedate()) + ". Assignee: "
					+ obj.getAssignuserFullName());
			dateInfo.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			layout.addComponent(dateInfo, 1, 2);

			CssLayout rowLayout = new CssLayout();
			rowLayout.addComponent(layout);
			rowLayout.setStyleName(UIConstants.WIDGET_ROW);
			rowLayout.setWidth("100%");
			return rowLayout;
		}
	}

	private static class RecentBugLinkLabelStringGenerator implements
			LabelStringGenerator {

		@Override
		public String handleText(String value) {
			if (value.length() > CUT_STRING) {
				return value.substring(0, CUT_STRING) + "...";
			}
			return value;
		}

	}
}
