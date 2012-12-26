package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class BugReadViewImpl extends AbstractView implements BugReadView {
	private static final long serialVersionUID = 1L;

	private SimpleBug bug;
	
	public BugReadViewImpl() {
		super();
	}

	@Override
	public void previewItem(SimpleBug item) {
		bug = item;
		this.addComponent(new Label("Preview bug"));
	}

	@Override
	public SimpleBug getItem() {
		return bug;
	}

    @Override
    public void doPrint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
