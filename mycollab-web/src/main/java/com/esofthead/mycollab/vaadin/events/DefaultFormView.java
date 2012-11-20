package com.esofthead.mycollab.vaadin.events;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;

public abstract class DefaultFormView extends AbstractView {

	private List<FormHandler> formHandlers;

	public void addFormHandler(FormHandler formHandler) {
		if (formHandlers == null) {
			formHandlers = new ArrayList<FormHandler>();
		}

		formHandlers.add(formHandler);
	}

	protected void fireSaveEvent(FormEvent event) {
		if (formHandlers != null) {
			for (FormHandler formHandler : formHandlers) {
				formHandler.onSave(event);
			}
		}
	}

	protected void fireCancelEvent(FormEvent event) {
		if (formHandlers != null) {
			for (FormHandler formHandler : formHandlers) {
				formHandler.onCancel(event);
			}
		}
	}
}
