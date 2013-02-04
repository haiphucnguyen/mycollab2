/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.module.crm.view.RelatedListHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.vaadin.ui.VerticalLayout;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author haiphucnguyen
 */
public abstract class RelatedListComp<T, S extends SearchCriteria> extends Depot implements IRelatedListHandlers<T> {

    protected Set<RelatedListHandler<T>> handlers;
    protected IPagedBeanTable<S, T> tableItem;

    public RelatedListComp(String title) {
        super(title, new VerticalLayout());
        this.setWidth("900px");
    }

    protected void fireNewRelatedItem(String itemId) {
        if (handlers != null) {
            for (RelatedListHandler handler : handlers) {
                handler.createNewRelatedItem(itemId);
            }
        }
    }

    protected void fireSelectedRelatedItems(Set selectedItems) {
        if (handlers != null) {
            for (RelatedListHandler handler : handlers) {
                handler.selectAssociateItems(selectedItems);
            }
        }
    }

    public void setSearchCriteria(S criteria) {
        tableItem.setSearchCriteria(criteria);
    }

    @Override
    public void addRelatedListHandler(RelatedListHandler<T> handler) {
        if (handlers == null) {
            handlers = new HashSet<RelatedListHandler<T>>();
        }

        handlers.add(handler);
    }

    public void setSelectedItems(Set selectedItems) {
        throw new MyCollabException("Must be override by support class");
    }
}
