package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class BugSimpleSearchPanel extends
		GenericSearchPanel<BugSearchCriteria> {

	private BugSearchCriteria searchCriteria;
	private TextField textValueField;
	private GridLayout layoutSearchPane;
	
	@Override
	public void attach() {
		super.attach();
		this.setHeight("32px");
		createBasicSearchLayout();
	}
	
	private void createBasicSearchLayout() {
		layoutSearchPane = new GridLayout(5, 3);
        layoutSearchPane.setSpacing(true);

        addTextFieldSearch();
        
        final CheckBox chkIsOpenBug = new CheckBox("Only Open Bug");
        layoutSearchPane.addComponent(chkIsOpenBug, 2, 0);
        layoutSearchPane.setComponentAlignment(chkIsOpenBug, Alignment.MIDDLE_CENTER);
        
        Button searchBtn = new Button("Search");
        searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
        searchBtn.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                searchCriteria = new BugSearchCriteria();
                searchCriteria.setProjectId(new NumberSearchField(
						SearchField.AND, CurrentProjectVariables.getProject().getId()));
				searchCriteria.setSummary(new StringSearchField(textValueField
						.getValue().toString().trim()));
				
				if ((Boolean) chkIsOpenBug.getValue()) {
					searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND,
							new String[] { BugStatusConstants.INPROGRESS,
									BugStatusConstants.OPEN,
									BugStatusConstants.REOPENNED }));
				}

               BugSimpleSearchPanel.this.notifySearchHandler(searchCriteria);
            }
        });
        layoutSearchPane.addComponent(searchBtn, 3, 0);
        layoutSearchPane.setComponentAlignment(searchBtn, Alignment.MIDDLE_CENTER);
        
        Button clearBtn = new Button("Clear");
        clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
        clearBtn.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
               textValueField.setValue("");
            }
        });
        
        layoutSearchPane.addComponent(clearBtn, 4, 0);
        layoutSearchPane.setComponentAlignment(clearBtn, Alignment.MIDDLE_CENTER);
        
        this.setCompositionRoot(layoutSearchPane);
	}

	private void addTextFieldSearch() {
		textValueField = new TextField();
		textValueField.setWidth("300px");
		layoutSearchPane.addComponent(textValueField, 0, 0);
		layoutSearchPane.setComponentAlignment(textValueField,
				Alignment.MIDDLE_CENTER);
	}
}
