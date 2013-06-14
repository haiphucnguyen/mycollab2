package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public abstract class DefaultGenericSearchPanel<S extends SearchCriteria>
		extends GenericSearchPanel<S> {
	private static final long serialVersionUID = 1L;

	public DefaultGenericSearchPanel() {
		moveToBasicSearchLayout();
	}

	abstract protected BasicSearchLayout<S> createBasicSearchLayout();

	abstract protected AdvancedSearchLayout<S> createAdvancedSearchLayout();

	protected void moveToBasicSearchLayout() {
		BasicSearchLayout<S> layout = createBasicSearchLayout();
		setCompositionRoot(layout);
	}

	protected void moveToAdvancedSearchLayout() {
		AdvancedSearchLayout<S> layout = createAdvancedSearchLayout();
		setCompositionRoot(layout);
	}
}
