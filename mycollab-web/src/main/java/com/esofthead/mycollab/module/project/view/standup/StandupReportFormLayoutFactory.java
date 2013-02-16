package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class StandupReportFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	
	private LazyLoadWrapper whatTodayField;
	
	private LazyLoadWrapper whatYesterdayField;
	
	private LazyLoadWrapper whatProblemField;
	
    private String title;
    
    public StandupReportFormLayoutFactory(String title) {
        this.title = title;
    }
    
    @Override
    public Layout getLayout() {
        AddViewLayout reportAddLayout = new AddViewLayout(title, new ThemeResource("icons/48/project/standup.png"));
        
        reportAddLayout.addTopControls(createTopPanel());
        
        VerticalLayout layout = new VerticalLayout();
        
        Label whatYesterdayLbl = new Label("What I did in the last day/week");
        whatYesterdayLbl.setStyleName("h2");
        layout.addComponent(whatYesterdayLbl);
        whatYesterdayField = new LazyLoadWrapper();
        layout.addComponent(whatYesterdayField);
        
        Label whatTodayLbl = new Label("What I will do today/week");
        whatTodayLbl.setStyleName("h2");
        layout.addComponent(whatTodayLbl);
        whatTodayField = new LazyLoadWrapper();
        layout.addComponent(whatTodayField);
        
        Label roadblockLbl = new Label("Do you have roadblocks? If you have questions or you need help, please write your questions or needs here");
        roadblockLbl.setStyleName("h2");
        layout.addComponent(roadblockLbl);
        whatProblemField = new LazyLoadWrapper();
        layout.addComponent(whatProblemField);
        
        reportAddLayout.addBottomControls(createBottomPanel());
        
        reportAddLayout.addBody(layout);
        
        return reportAddLayout;
    }
    
    @Override
    public void attachField(Object propertyId, Field field) {
        if (propertyId.equals("whatlastday")) {
        	whatYesterdayField.setLazyLoadComponent(field);
        } else if (propertyId.equals("whattoday")) {
        	whatTodayField.setLazyLoadComponent(field);
        } else if (propertyId.equals("whatproblem")) {
        	whatProblemField.setLazyLoadComponent(field);
        }
    }
    
    protected abstract Layout createTopPanel();
    
    protected abstract Layout createBottomPanel();
}
