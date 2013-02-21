package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.overlay.TextOverlay;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ProgressIndicator;

public class ProgressPercentageIndicator extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	
	private ProgressIndicator progress;
	private TextOverlay to;

	public ProgressPercentageIndicator(double val) {
		this.setWidth("100%");
		progress = new ProgressIndicator(new Float(val));
		progress.setPollingInterval(1000 * 60 * 60 * 24);
		progress.setWidth("100%");
		this.addComponent(progress);
	}
	
	public void setValue(double val) {
		progress.setValue(val);
	}
	
	@Override
	public void attach() {
		if (this.getApplication() != null) {
			to = new TextOverlay(this, progress.getValue() + "%");
			to.setComponentAnchor(Alignment.MIDDLE_CENTER);
			to.setOverlayAnchor(Alignment.MIDDLE_CENTER);
			to.setXOffset(0);
			to.setYOffset(0);
			this.addComponent(to);
		}
	}
}
