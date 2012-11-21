package com.esofthead.mycollab.vaadin.events;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public abstract class DefaultFormView extends Form implements
		HasFormHandlers {

	private List<FormHandler> formHandlers;

	public void addFormHandler(FormHandler formHandler) {
		if (formHandlers == null) {
			formHandlers = new ArrayList<FormHandler>();
		}

		formHandlers.add(formHandler);
	}

	protected void fireSaveEvent(FormEvent.Save event) {
		if (formHandlers != null) {
			for (FormHandler formHandler : formHandlers) {
				formHandler.onSave(event);
			}
		}
	}

	protected void fireCancelEvent(FormEvent.Cancel event) {
		if (formHandlers != null) {
			for (FormHandler formHandler : formHandlers) {
				formHandler.onCancel(event);
			}
		}
	}
}
