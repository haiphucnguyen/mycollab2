package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.mvp.MassUpdateCommand;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class MassUpdateWindow<V> extends Window {
	private static final long serialVersionUID = 1L;

	private MassUpdateCommand<V> massUpdatePresenter;

	private VerticalLayout layout;
	private Button updateBtn, closeBtn;

	public MassUpdateWindow(String title,
			MassUpdateCommand<V> massUpdatePresenter) {
		super(title);
		center();

		this.massUpdatePresenter = massUpdatePresenter;
	}

	abstract protected V getItem();

	public VerticalLayout getLayout() {
		layout = new VerticalLayout();

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setMargin(true);
		controlsLayout.setSpacing(true);
		controlsLayout.setStyleName("addNewControl");

		updateBtn = new Button("Update", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				massUpdatePresenter.massUpdate(getItem());
				MassUpdateWindow.this.close();
			}
		});
		updateBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(updateBtn);
		controlsLayout
				.setComponentAlignment(updateBtn, Alignment.MIDDLE_CENTER);

		closeBtn = new Button("Close", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MassUpdateWindow.this.close();
			}
		});
		closeBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(closeBtn);
		controlsLayout.setComponentAlignment(closeBtn, Alignment.MIDDLE_CENTER);

		layout.addComponent(controlsLayout);
		layout.setComponentAlignment(controlsLayout, Alignment.BOTTOM_CENTER);
		return layout;
	}

}
