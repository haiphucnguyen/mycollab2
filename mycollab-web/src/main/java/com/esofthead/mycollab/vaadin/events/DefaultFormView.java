package com.esofthead.mycollab.vaadin.events;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public abstract class DefaultFormView<T> extends Form implements
		HasEditFormHandlers<T> {

	private List<EditFormHandler> editFormHandlers;

	public void addFormHandler(EditFormHandler editFormHandler) {
		if (editFormHandlers == null) {
			editFormHandlers = new ArrayList<EditFormHandler>();
		}

		editFormHandlers.add(editFormHandler);
	}

	protected void fireSaveForm(T bean) {
		if (editFormHandlers != null) {
			for (EditFormHandler editFormHandler : editFormHandlers) {
				editFormHandler.onSave(bean);
			}
		}
	}

	protected void fireCancelForm() {
		if (editFormHandlers != null) {
			for (EditFormHandler editFormHandler : editFormHandlers) {
				editFormHandler.onCancel();
			}
		}
	}
}
