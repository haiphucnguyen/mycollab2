package com.esofthead.mycollab.module.project.view.bug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleRelatedBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugRelatedSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.RelatedBugService;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class BugRelatedField extends CustomField {
    
    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(BugRelatedField.class);
    private TextField itemField;
    private Embedded browseBtn;
    private Embedded clearBtn;
    private Button btnRelate;
    private BugRelationComboBox comboRelation;
    private PagedBeanTable2<RelatedBugService, BugRelatedSearchCriteria, SimpleRelatedBug> tableItem;
    private RelatedBugService relatedBugService;
    
    protected SimpleBug bean;
    protected SimpleBug relatedBean;
    
    public BugRelatedField(SimpleBug bean) {
    	this.bean = bean;
    	
    	relatedBugService = AppContext.getSpringBean(RelatedBugService.class);
    	
    	VerticalLayout mainLayout = new VerticalLayout();
    	mainLayout.setWidth("100%");
    	mainLayout.setMargin(true);
    	mainLayout.setSpacing(true);
    	
        HorizontalLayout layoutAdd = new HorizontalLayout();
        layoutAdd.setSpacing(true);
        
        Label lbBug = new Label("Bug:");
        layoutAdd.addComponent(lbBug);
        layoutAdd.setComponentAlignment(lbBug, Alignment.MIDDLE_LEFT);
        
        itemField = new TextField();
        itemField.setWidth("300px");
        itemField.setNullRepresentation("");
        itemField.setReadOnly(true);
        itemField.setEnabled(true);
        layoutAdd.addComponent(itemField);
        layoutAdd.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);
        
        browseBtn = new Embedded(null, new ThemeResource(
                "icons/16/browseItem.png"));
        browseBtn.addListener(new MouseEvents.ClickListener() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
            	callItemSelectionWindow();
            }
        });
        
        layoutAdd.addComponent(browseBtn);
        layoutAdd.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
        
        clearBtn = new Embedded(null, new ThemeResource(
                "icons/16/clearItem.png"));
        clearBtn.addListener(new MouseEvents.ClickListener() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public void click(ClickEvent event) {
            	itemField.setReadOnly(false);
            	itemField.setValue("");
            	itemField.setReadOnly(true);
            }
        });
        
        layoutAdd.addComponent(clearBtn);
        layoutAdd.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);
        
        Label lbIs = new Label("is");
        layoutAdd.addComponent(lbIs);
        layoutAdd.setComponentAlignment(lbIs, Alignment.MIDDLE_LEFT);
        
        comboRelation = new BugRelationComboBox();
        comboRelation.setWidth("200px");
        layoutAdd.addComponent(comboRelation);
        layoutAdd.setComponentAlignment(comboRelation, Alignment.MIDDLE_LEFT);
        
        btnRelate = new Button("Relate");
        layoutAdd.addComponent(btnRelate);
        layoutAdd.setComponentAlignment(btnRelate, Alignment.MIDDLE_LEFT);
        
        
        Label lbInstruction = new Label("<strong>Relate to an existing ticket</strong>", Label.CONTENT_XHTML);
        mainLayout.addComponent(lbInstruction);
        
        mainLayout.addComponent(layoutAdd);
        
        tableItem = new PagedBeanTable2<RelatedBugService, BugRelatedSearchCriteria, SimpleRelatedBug>(
				AppContext.getSpringBean(RelatedBugService.class),
				SimpleRelatedBug.class,
				new String[] { "bugName", "relatedid", "relatetype", "comment" }, new String[] {
						"Bug Name", "Related", "Related Type", "Comment" });
        
        mainLayout.addComponent(tableItem);
        
        setCriteria(); 
        
        
        
        this.setCompositionRoot(mainLayout);
    }
    
    private void setCriteria() {
    	BugRelatedSearchCriteria searchCriteria = new BugRelatedSearchCriteria();
    	searchCriteria.setBugId(new NumberSearchField(bean.getId()));
    	tableItem.setSearchCriteria(searchCriteria);
    }
    
	private void callItemSelectionWindow() {
		BugSelectionWindow bugSeletionWindow = new BugSelectionWindow(this);
		 getWindow().addWindow(bugSeletionWindow);
		 bugSeletionWindow.show();
	}

	public void fireValueChange(SimpleBug data) {
		relatedBean = data;
		setItemFieldValue(data.getSummary());
	}
    
    @Override
    public Class<?> getType() {
        return (new String[2]).getClass();
    }
    
    protected void setItemFieldValue(String value) {
    	itemField.setReadOnly(false);
    	itemField.setValue(value);
    	itemField.setReadOnly(true);
    }
    
}
