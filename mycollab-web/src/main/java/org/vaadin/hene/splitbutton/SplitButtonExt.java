package org.vaadin.hene.splitbutton;

import com.vaadin.ui.Button;

public class SplitButtonExt extends SplitButton{
	
	private static final long serialVersionUID = 1L;
	
	public SplitButtonExt() {
		super();
	}

	public SplitButtonExt(final Button button) {
		super(button, new SplitButton.PopupButton());
	}
}
