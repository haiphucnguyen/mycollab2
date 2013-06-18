package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;

public class ProgressBar extends CustomLayout {
	private static final long serialVersionUID = 1L;
	private final ProgressIndicator progressIndicator;
	private final Label progressStatusLabel;

	public ProgressBar(final int total, final int remaining) {
		super("progressBar");
		this.progressIndicator = new ProgressIndicator(new Float(
				(float) (total - remaining) / total));
		this.progressIndicator.setPollingInterval(1000000000);
		this.progressIndicator.setWidth("100%");
		if (total > 0) {
			this.progressStatusLabel = new Label(String.format("%.2f",
					((float) (total - remaining) / total) * 100) + "%");
		} else {
			this.progressStatusLabel = new Label("100%");
		}
		this.progressStatusLabel.setWidth("100%");
		this.addComponent(this.progressIndicator, "progressbar-container");
		this.addComponent(this.progressStatusLabel, "progressbar-status");
	}
}
