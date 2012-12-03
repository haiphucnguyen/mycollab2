package com.esofthead.mycollab.module.file_old.ui;

import com.esofthead.mycollab.module.file_old.ui.component.AggregateSearchFilePanel;
import com.esofthead.mycollab.module.file_old.ui.component.BookmarkPanel;
import com.esofthead.mycollab.module.file_old.ui.component.StorageInfoPanel;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


public class FileDashboard extends Panel {
	private static final long serialVersionUID = 1L;
	
	private BookmarkPanel bookmark;
	
	private StorageInfoPanel storageInfo;
	
	private AggregateSearchFilePanel searchFile;
	
    public FileDashboard() {
    	HorizontalLayout hLayout = new HorizontalLayout();
    	
    	VerticalLayout leftLayout = new VerticalLayout();
    	leftLayout.setWidth("300px");
    	hLayout.addComponent(leftLayout);
    	
    	bookmark = new BookmarkPanel();
    	bookmark.setWidth("100%");
    	leftLayout.addComponent(bookmark);
    	
    	storageInfo = new StorageInfoPanel();
    	leftLayout.addComponent(storageInfo);
    	
    	VerticalLayout rightPanel = new VerticalLayout();
    	
    	searchFile = new AggregateSearchFilePanel();
    	rightPanel.addComponent(searchFile);
    	
    	hLayout.addComponent(rightPanel);
    	
    	this.addComponent(hLayout);
    }
}
