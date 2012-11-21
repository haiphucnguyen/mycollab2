package com.esofthead.mycollab.vaadin.events;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public abstract class DefaultFormView extends Form implements
		HasEditFormHandlers {

	private List<EditFormHandler> editFormHandlers;

	public void addFormHandler(EditFormHandler editFormHandler) {
		if (editFormHandlers == null) {
			editFormHandlers = new ArrayList<EditFormHandler>();
		}

		editFormHandlers.add(editFormHandler);
	}

	protected void fireSaveEvent(FormEvent.Save event) {
		if (editFormHandlers != null) {
			for (EditFormHandler editFormHandler : editFormHandlers) {
				editFormHandler.onSave(event);
			}
		}
	}

	protected void fireCancelEvent(FormEvent.Cancel event) {
		if (editFormHandlers != null) {
			for (EditFormHandler editFormHandler : editFormHandlers) {
				editFormHandler.onCancel(event);
			}
		}
	}
}
