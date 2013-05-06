package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
		layoutSearchPane = new GridLayout(3, 3);
        layoutSearchPane.setSpacing(true);

        addTextFieldSearch();

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

               BugSimpleSearchPanel.this.notifySearchHandler(searchCriteria);
            }
        });
        layoutSearchPane.addComponent(searchBtn, 2, 0);
        layoutSearchPane.setComponentAlignment(searchBtn, Alignment.MIDDLE_CENTER);
        this.setCompositionRoot(layoutSearchPane);
	}

	private void addTextFieldSearch() {
		textValueField = new TextField();
		layoutSearchPane.addComponent(textValueField, 0, 0);
		layoutSearchPane.setComponentAlignment(textValueField,
				Alignment.MIDDLE_CENTER);
	}
}
