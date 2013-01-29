package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public abstract class MultiSelectComp extends CustomField {

    protected TextField componentsDisplay;
    protected MultipleItemsPopupSelection componentPopupSelection;
    protected List dataList;
    protected List selectedItemsList = new ArrayList();

    public MultiSelectComp() {
        this.setWidth("100%");
        GridLayout content = new GridLayout(2, 1);
        content.setSpacing(false);

        componentsDisplay = new TextField();
        componentsDisplay.setNullRepresentation("");
        componentsDisplay.setReadOnly(true);
        componentsDisplay.setWidth("210px");
        componentsDisplay.addStyleName("noBorderRight");

        componentPopupSelection = new MultipleItemsPopupSelection();
        componentPopupSelection.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                initData();
                setSelectedComponentsDisplay();
            }
        });

        content.addComponent(componentsDisplay, 0, 0);
        content.setComponentAlignment(componentsDisplay, Alignment.MIDDLE_CENTER);

        componentPopupSelection.addStyleName("nonPopupIndicator");
        componentPopupSelection.addStyleName(UIConstants.SELECT_BG);
        content.addComponent(componentPopupSelection, 1, 0);
        content.setComponentAlignment(componentPopupSelection, Alignment.MIDDLE_CENTER);

        this.setCompositionRoot(content);
    }
    
    protected void addItemToComponent(Component comp) {
    	componentPopupSelection.addItemComponent(comp);
    }
    
    public List getDataList() {
    	return dataList;
    }

    public void setSelectedItems(List items) {
        this.selectedItemsList = items;
        componentsDisplay.setReadOnly(false);
        componentsDisplay.setValue(getDisplaySelectedItemsString());
        componentsDisplay.setReadOnly(true);
    }

    public List getSelectedItems() {
        return selectedItemsList;
    }

    abstract protected String getDisplaySelectedItemsString();
    
    abstract protected void setSelectedComponentsDisplay();

    abstract protected void initData();

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}
