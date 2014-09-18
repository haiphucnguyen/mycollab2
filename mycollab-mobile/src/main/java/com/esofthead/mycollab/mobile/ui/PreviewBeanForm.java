package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;

public interface PreviewBeanForm<B> extends HasPreviewFormHandlers<B> {

	public B getBean();

	public void setBean(B bean);

	public void fireAssignForm(B bean);

	public void fireEditForm(B bean);

	public void showHistory();

	public void fireCancelForm(B bean);

	public void fireDeleteForm(B bean);

	public void fireCloneForm(B bean);

	public void fireExtraAction(String action, B bean);
}
