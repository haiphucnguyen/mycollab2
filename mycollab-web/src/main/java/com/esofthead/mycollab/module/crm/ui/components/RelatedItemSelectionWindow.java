/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.vaadin.ui.Window;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author haiphucnguyen
 */
public abstract class RelatedItemSelectionWindow<S extends SearchCriteria> extends Window {
    
    private static final String selectedFieldName = "selected";
    protected RelatedListComp<S> relatedListComp;
    protected IPagedBeanTable<S, ?> tableItem;
    protected Set selectedItems = new HashSet();
    
    public RelatedItemSelectionWindow(String title, RelatedListComp<S> relatedList) {
        super(title);
        
        this.relatedListComp = relatedList;
        initUI();
        
        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return TableClickEvent.class;
            }
            
            @Override
            public void handle(TableClickEvent event) {
                try {
                    Object rowItem = event.getData();
                    Boolean selectedVal = (Boolean) PropertyUtils.getProperty(rowItem, selectedFieldName);
                    if (selectedVal == true) {
                        selectedItems.remove(rowItem);
                        PropertyUtils.setProperty(rowItem, selectedFieldName, false);
                    } else {
                        selectedItems.add(rowItem);
                        PropertyUtils.setProperty(rowItem, selectedFieldName, true);
                    }
                } catch (Exception ex) {
                    throw new MyCollabException(ex);
                }
            }
        });
    }
    
    @Override
    protected void close() {
        super.close();
        
        if (!selectedItems.isEmpty()) {
            relatedListComp.fireSelectedItems(selectedItems);
        }
    }
    
    protected abstract void initUI();
    
    public void setSearchCriteria(S criteria) {
        tableItem.setSearchCriteria(criteria);
    }
}
