package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import org.vaadin.hene.splitbutton.SplitButton;

public class EventSearchPanel extends GenericSearchPanel<EventSearchCriteria> {

    private static final long serialVersionUID = 1L;
    protected EventSearchCriteria searchCriteria;

    @Override
    public void attach() {
        super.attach();
        createBasicSearchLayout();
    }

    private void createBasicSearchLayout() {
        EventBasicSearchLayout layout = new EventBasicSearchLayout();
        this.setCompositionRoot(layout);
    }

    private void createAdvancedSearchLayout() {
        EventAdvancedSearchLayout layout = new EventAdvancedSearchLayout();
        this.setCompositionRoot(layout);
    }

    private HorizontalLayout createSearchTopPanel() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);

        Label searchtitle = new Label("Search Events");
        searchtitle.setStyleName(Reindeer.LABEL_H2);
        layout.addComponent(searchtitle);
        
        final SplitButton controlsBtn = new SplitButton();
        controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
        controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
        controlsBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
        controlsBtn.setCaption("Create Task");
        controlsBtn
                .addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(
                    SplitButton.SplitButtonClickEvent event) {
                EventBus.getInstance().fireEvent(
                        new ActivityEvent.TaskAdd(this, null));
            }
        });
        
        VerticalLayout btnControlsLayout = new VerticalLayout();
        btnControlsLayout.setWidth("150px");
        controlsBtn.addComponent(btnControlsLayout);
        
        Button createMeetingBtn = new Button("Create Meeting",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        controlsBtn.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(new ActivityEvent.MeetingAdd(this, null));
                    }
                });
        createMeetingBtn.setStyleName("link");
        btnControlsLayout.addComponent(createMeetingBtn);
        
        Button createCallBtn = new Button("Create Call",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        controlsBtn.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(new ActivityEvent.CallAdd(this, null));
                    }
                });
        createCallBtn.setStyleName("link");
        btnControlsLayout.addComponent(createCallBtn);

        UiUtils.addComponent(layout, controlsBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    private class EventBasicSearchLayout extends BasicSearchLayout {

        private TextField nameField;
        private CheckBox myItemCheckbox;

        public EventBasicSearchLayout() {
            super();
        }

        @Override
        public ComponentContainer constructHeader() {
            return createSearchTopPanel();
        }

        @Override
        public ComponentContainer constructBody() {
            HorizontalLayout basicSearchBody = new HorizontalLayout();
            basicSearchBody.setSpacing(true);
            basicSearchBody.addComponent(new Label("Subject"));
            nameField = new TextField();
            nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            UiUtils.addComponent(basicSearchBody, nameField,
                    Alignment.MIDDLE_CENTER);
            myItemCheckbox = new CheckBox("My Items");
            myItemCheckbox.setWidth("75px");
            UiUtils.addComponent(basicSearchBody, myItemCheckbox,
                    Alignment.MIDDLE_CENTER);
            Button searchBtn = new Button("Search");
            searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
            searchBtn.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    searchCriteria = new EventSearchCriteria();
                    searchCriteria.setSaccountid(new NumberSearchField(
                            SearchField.AND, AppContext.getAccountId()));

                    EventSearchPanel.this.notifySearchHandler(searchCriteria);
                }
            });

            basicSearchBody.addComponent(searchBtn);

            Button cancelBtn = new Button("Clear");
            cancelBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
            cancelBtn.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    nameField.setValue("");
                }
            });
            basicSearchBody.addComponent(cancelBtn);

//            Button advancedSearchBtn = new Button("Advanced Search",
//                    new Button.ClickListener() {
//                        @Override
//                        public void buttonClick(Button.ClickEvent event) {
//                            EventSearchPanel.this
//                                    .createAdvancedSearchLayout();
//                        }
//                    });
//            advancedSearchBtn.setStyleName("link");
//            UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
//                    Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }
    }

    private class EventAdvancedSearchLayout extends AdvancedSearchLayout {

        public EventAdvancedSearchLayout() {
            super();
        }

        @Override
        public ComponentContainer constructHeader() {
            return createSearchTopPanel();
        }

        @Override
        public ComponentContainer constructBody() {
            GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 3);

            return gridLayout.getLayout();
        }

        @Override
        public ComponentContainer constructFooter() {
            HorizontalLayout buttonControls = new HorizontalLayout();
            buttonControls.setSpacing(true);

            Button searchBtn = new Button("Search", new Button.ClickListener() {
                @SuppressWarnings({"unchecked", "rawtypes"})
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    searchCriteria = new EventSearchCriteria();
                    searchCriteria.setSaccountid(new NumberSearchField(
                            SearchField.AND, AppContext.getAccountId()));


                    EventSearchPanel.this.notifySearchHandler(searchCriteria);

                }
            });

            buttonControls.addComponent(searchBtn);
            searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

            Button clearBtn = new Button("Clear", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                }
            });
            clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
            buttonControls.addComponent(clearBtn);

            Button basicSearchBtn = new Button("Basic Search",
                    new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            EventSearchPanel.this.createBasicSearchLayout();

                        }
                    });
            basicSearchBtn.setStyleName("link");
            UiUtils.addComponent(buttonControls, basicSearchBtn,
                    Alignment.MIDDLE_CENTER);
            return buttonControls;
        }
    }
}
