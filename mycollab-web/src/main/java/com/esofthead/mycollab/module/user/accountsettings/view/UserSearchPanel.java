/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.events.UserEvent;
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
public class UserSearchPanel extends GenericSearchPanel<UserSearchCriteria> {
    private UserSearchCriteria searchCriteria;
    
    public UserSearchPanel() {
        this.setCompositionRoot(new UserBasicSearchLayout());
    }
    
    private HorizontalLayout createSearchTopPanel() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);

        Label searchtitle = new Label("Search Users");
        searchtitle.setStyleName(Reindeer.LABEL_H2);
        layout.addComponent(searchtitle);

        Button createBtn = new Button("Create",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new UserEvent.GotoAdd(this, null));
                    }
                });
        createBtn.setStyleName("link");
        createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

        UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }
    
    private class UserBasicSearchLayout extends BasicSearchLayout {

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
                            searchCriteria = new UserSearchCriteria();
                            searchCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));

                            UserSearchPanel.this
                                    .notifySearchHandler(searchCriteria);
                        }
                    });
            searchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            basicSearchBody.addComponent(searchBtn);

            Button clearBtn = new Button("Clear",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            nameField.setValue("");
                        }
                    });
            clearBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            basicSearchBody.addComponent(clearBtn);
            return basicSearchBody;
        }
    }
}
