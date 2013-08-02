/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import java.util.HashSet;
import java.util.Set;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.module.crm.view.IRelatedListHandlers;
import com.esofthead.mycollab.module.crm.view.RelatedListHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class RelatedListComp<T, S extends SearchCriteria> extends
		Depot implements IRelatedListHandlers<T> {
	
	private static final long serialVersionUID = 1L;
	
	protected Set<RelatedListHandler<T>> handlers;
	protected IPagedBeanTable<S, T> tableItem;

	public RelatedListComp(final String title) {
		super(title, new VerticalLayout());
		this.setWidth("100%");
	}

	@Override
	public void addRelatedListHandler(final RelatedListHandler<T> handler) {
		if (handlers == null) {
			handlers = new HashSet<RelatedListHandler<T>>();
		}

		handlers.add(handler);
	}

	protected void fireNewRelatedItem(final String itemId) {
		if (handlers != null) {
			for (final RelatedListHandler handler : handlers) {
				handler.createNewRelatedItem(itemId);
			}
		}
	}

	protected void fireSelectedRelatedItems(final Set selectedItems) {
		if (handlers != null) {
			for (final RelatedListHandler handler : handlers) {
				handler.selectAssociateItems(selectedItems);
			}
		}
	}

	public void setSearchCriteria(final S criteria) {
		tableItem.setSearchCriteria(criteria);
	}

	public void setSelectedItems(final Set<T> selectedItems) {
		throw new MyCollabException("Must be override by support class");
	}
}
