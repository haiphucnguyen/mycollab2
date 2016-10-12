package com.mycollab.vaadin.touchkit.client.ui;

import com.mycollab.vaadin.touchkit.BackButton;
import com.mycollab.vaadin.touchkit.client.VBackButton;
import com.mycollab.vaadin.touchkit.client.shared.BackButtonState;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(BackButton.class)
public class BackButtonConnector extends AbstractComponentConnector {
	private static final long serialVersionUID = 2308164455076084154L;

	@Override
	protected Widget createWidget() {
		return GWT.create(VBackButton.class);
	}

	@Override
	public boolean delegateCaptionHandling() {
		return false;
	}

	@Override
	public VBackButton getWidget() {
		return (VBackButton) super.getWidget();
	}

	@Override
	public BackButtonState getState() {
		return (BackButtonState) super.getState();
	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);

		String caption = getState().caption;
		getWidget().setText(caption);
		getWidget().setEnabled(getState().enabled);

		getWidget().setIcon(getIcon());

		String description = getState().description;
		getWidget().setDescription(description);
	}

	@Override
	protected void init() {
		super.init();
		getWidget().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				navigateBack();
			}

		});
	}
	
	private native void navigateBack() /*-{
		$wnd.history.back();
	}-*/;
}
