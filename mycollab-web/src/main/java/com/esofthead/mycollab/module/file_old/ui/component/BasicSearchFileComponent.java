package com.esofthead.mycollab.module.file_old.ui.component;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class BasicSearchFileComponent extends Panel implements ISearchFileComponent {
	private static final long serialVersionUID = 1L;

	private TextField titleSearch;
	private Button searchBtn;
	
	public BasicSearchFileComponent() {
		super();
		
		HorizontalLayout hLayout = new HorizontalLayout();
		this.addComponent(hLayout);
		
		titleSearch = new TextField();
		titleSearch.addListener(new FieldEvents.TextChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				System.out.println("Event: " + event.getText());
				
			}
		});
		
		hLayout.addComponent(titleSearch);
		
		searchBtn = new Button("Search");
		searchBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		hLayout.addComponent(searchBtn);
		
	}
}
