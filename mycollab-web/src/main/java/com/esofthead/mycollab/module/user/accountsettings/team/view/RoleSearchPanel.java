/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author haiphucnguyen
 */
public class RoleSearchPanel extends GenericSearchPanel<RoleSearchCriteria> {
	private static final long serialVersionUID = 1L;
	private RoleSearchCriteria searchCriteria;

    public RoleSearchPanel() {
        this.setCompositionRoot(new RoleBasicSearchLayout());
        if (ScreenSize.hasSupport1024Pixels()) {
			this.setWidth("750px");
		}
    }

    private HorizontalLayout createSearchTopPanel() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);

        Label searchtitle = new Label("Search Roles");
        searchtitle.setStyleName(Reindeer.LABEL_H2);
        layout.addComponent(searchtitle);

        Button createBtn = new Button("Create",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new RoleEvent.GotoAdd(this, null));
                    }
                });
        createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

        UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    private class RoleBasicSearchLayout extends GenericSearchPanel.BasicSearchLayout {

        private static final long serialVersionUID = 1L;
        private TextField nameField;

        @Override
        public ComponentContainer constructHeader() {
            return createSearchTopPanel();
        }

        @Override
        public ComponentContainer constructBody() {
            HorizontalLayout basicSearchBody = new HorizontalLayout();
            basicSearchBody.setSpacing(true);
            basicSearchBody.addComponent(new Label("Name"));
            nameField = new TextField();
            nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            UiUtils.addComponent(basicSearchBody, nameField,
                    Alignment.MIDDLE_CENTER);

            Button searchBtn = new Button("Search",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            searchCriteria = new RoleSearchCriteria();
                            searchCriteria.setsAccountId(new NumberSearchField(AppContext.getAccountId()));
                            if (StringUtil.isNotNullOrEmpty((String) nameField
        							.getValue())) {
        						searchCriteria.setRoleName(new StringSearchField(
        								SearchField.AND, (String) nameField.getValue()));
        					}
                            RoleSearchPanel.this
                                    .notifySearchHandler(searchCriteria);
                        }
                    });
            searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
            basicSearchBody.addComponent(searchBtn);

            Button clearBtn = new Button("Clear",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            nameField.setValue("");
                        }
                    });
            clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
            basicSearchBody.addComponent(clearBtn);
            return basicSearchBody;
        }
    }
}
