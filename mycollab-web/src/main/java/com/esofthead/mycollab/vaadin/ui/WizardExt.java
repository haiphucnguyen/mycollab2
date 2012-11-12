package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;

@SuppressWarnings("serial")
public class WizardExt extends Wizard {

	@Override
	public void finish() {
		if (currentStep.onAdvance()) {
			fireEvent(new WizardCompletedEvent(this));
		}
	}

}
