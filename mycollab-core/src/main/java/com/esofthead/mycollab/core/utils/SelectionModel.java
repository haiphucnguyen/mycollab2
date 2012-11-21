package com.esofthead.mycollab.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SelectionModel<T> implements Iterable<T> {
	List<T> selectedItems = new ArrayList<T>();

	public List<T> getSelectedItems() {
		return selectedItems;
	}

	public int size() {
		return selectedItems.size();
	}

	public void addSelections(Collection<T> collection) {
		for (T item : collection) {
			if (!isSelected(item)) {
				addSelection(item);
			}
		}
	}

	public void removeAll() {
		selectedItems.removeAll(selectedItems);
	}

	public void addSelection(T item) {
		selectedItems.add(item);
	}

	public void removeSelection(T item) {
		selectedItems.remove(item);
	}

	public boolean isSelected(T item) {
		return selectedItems.contains(item);
	}

	@Override
	public Iterator<T> iterator() {
		return selectedItems.iterator();
	}
}
