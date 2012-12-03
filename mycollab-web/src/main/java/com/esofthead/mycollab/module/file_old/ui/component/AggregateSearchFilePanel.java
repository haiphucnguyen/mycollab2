package com.esofthead.mycollab.module.file_old.ui.component;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class AggregateSearchFilePanel extends Panel {
	private static final long serialVersionUID = 1L;

	private ISearchFileComponent searchFileComponent;
	
	private BasicSearchFileComponent basicSearchComponent;
	
	private AdvancedSearchFileComponent advancedSearchComponent;
	
	private Layout vLayout;
	
	private Button toogleSearchCommand;
	
	public AggregateSearchFilePanel() {
		super();
		
		vLayout = new VerticalLayout();
		this.addComponent(vLayout);
		
		basicSearchComponent = new BasicSearchFileComponent();
		advancedSearchComponent = new AdvancedSearchFileComponent();
		
		searchFileComponent = basicSearchComponent;
		vLayout.addComponent(searchFileComponent);
		
		
		toogleSearchCommand = new Button("Advanced Search", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				AggregateSearchFilePanel.this.vLayout.removeComponent(searchFileComponent);
				
				if (searchFileComponent == basicSearchComponent) {
					toogleSearchCommand.setCaption("Basic Search");
					searchFileComponent = advancedSearchComponent;
				} else {
					toogleSearchCommand.setCaption("Advanced Search");
					searchFileComponent = basicSearchComponent;
				}
				
				AggregateSearchFilePanel.this.vLayout.addComponent(searchFileComponent);
			}
		});
		
		vLayout.addComponent(toogleSearchCommand);
	}
}
