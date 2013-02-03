/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectMemberSearchPanel extends GenericSearchPanel<ProjectMemberSearchCriteria> {
    private static final long serialVersionUID = 1L;
    private SimpleProject project;
    protected ProjectMemberSearchCriteria searchCriteria;

    public ProjectMemberSearchPanel() {
        this.project = (SimpleProject) AppContext.getVariable("project");
    }

    @Override
    public void attach() {
        super.attach();
        createBasicSearchLayout();
    }

    private void createBasicSearchLayout() {

        this.setCompositionRoot(new ProjectMemberSearchPanel.ProjectMemberBasicSearchLayout());
    }

    private HorizontalLayout createSearchTopPanel() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);

        Label searchtitle = new Label("Search ProjectMembers");
        searchtitle.setStyleName(Reindeer.LABEL_H2);
        layout.addComponent(searchtitle);

        Button createBtn = new Button("Create",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new ProjectMemberEvent.GotoAdd(this, null));
                    }
                });
        createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

        UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    private class ProjectMemberBasicSearchLayout extends BasicSearchLayout {

        private static final long serialVersionUID = 1L;
        private TextField nameField;
        private CheckBox myItemCheckbox;

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
            myItemCheckbox = new CheckBox("My Items");
            UiUtils.addComponent(basicSearchBody, myItemCheckbox,
                    Alignment.MIDDLE_CENTER);

            Button searchBtn = new Button("Search",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            searchCriteria = new ProjectMemberSearchCriteria();
                            searchCriteria.setProjectId(new NumberSearchField(
                                    SearchField.AND, project.getId()));
                            
                            ProjectMemberSearchPanel.this
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
