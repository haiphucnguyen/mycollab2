package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class PreviewFormControlsGenerator<T> {

	private AdvancedPreviewBeanForm<T> previewForm;
	private Button backBtn;
	private Button editBtn;
	private Button deleteBtn;
	private Button cloneBtn;

	// private Button previousItem;
	// private Button nextItemBtn;
	// private Button historyBtn;
	// private Button printBtn;

	public PreviewFormControlsGenerator(AdvancedPreviewBeanForm<T> editForm) {
		this.previewForm = editForm;
	}

	public HorizontalLayout createButtonControls() {
		return createButtonControls(null);
	}

	public HorizontalLayout createButtonControls(String permissionItem) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setStyleName("addNewControl");
		layout.setWidth("100%");

		backBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				T item = ((BeanItem<T>) previewForm.getItemDataSource())
						.getBean();
				previewForm.fireCancelForm(item);
			}
		});
		backBtn.setIcon(new ThemeResource("icons/16/back.png"));
		backBtn.setDescription("Back to list");
		backBtn.setStyleName("link");
		layout.addComponent(backBtn);
		layout.setComponentAlignment(backBtn, Alignment.MIDDLE_LEFT);

		HorizontalLayout editButtons = new HorizontalLayout();
		editButtons.setSpacing(true);

		editBtn = new Button(GenericForm.EDIT_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) previewForm.getItemDataSource())
								.getBean();
						previewForm.fireEditForm(item);
					}
				});
		editBtn.setIcon(new ThemeResource("icons/16/edit.png"));
		editBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		editButtons.addComponent(editBtn);
		editButtons.setComponentAlignment(editBtn, Alignment.MIDDLE_CENTER);

		deleteBtn = new Button(GenericForm.DELETE_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) previewForm.getItemDataSource())
								.getBean();
						previewForm.fireDeleteForm(item);
					}
				});
		deleteBtn.setIcon(new ThemeResource("icons/16/delete2.png"));
		deleteBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		editButtons.addComponent(deleteBtn);
		editButtons.setComponentAlignment(deleteBtn, Alignment.MIDDLE_CENTER);

		cloneBtn = new Button(GenericForm.CLONE_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						@SuppressWarnings("unchecked")
						T item = ((BeanItem<T>) previewForm.getItemDataSource())
								.getBean();
						previewForm.fireCloneForm(item);
					}
				});
		cloneBtn.setIcon(new ThemeResource("icons/16/clone.png"));
		cloneBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		editButtons.addComponent(cloneBtn);
		editButtons.setComponentAlignment(cloneBtn, Alignment.MIDDLE_CENTER);

		layout.addComponent(editButtons);
		layout.setComponentAlignment(editButtons, Alignment.MIDDLE_CENTER);
		layout.setExpandRatio(editButtons, 1.0f);

		// previousItem = new Button(null, new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// T item = ((BeanItem<T>) previewForm.getItemDataSource())
		// .getBean();
		// previewForm.fireGotoPrevious(item);
		// }
		// });

		// previousItem.setIcon(new ThemeResource("icons/16/previous.png"));
		// previousItem.setStyleName("link");
		// previousItem.setDescription("Show previous item");
		// layout.addComponent(previousItem);
		// layout.setComponentAlignment(previousItem, Alignment.MIDDLE_RIGHT);
		//
		// nextItemBtn = new Button(null, new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// T item = ((BeanItem<T>) previewForm.getItemDataSource())
		// .getBean();
		// previewForm.fireGotoNextItem(item);
		// }
		// });

		// nextItemBtn.setIcon(new ThemeResource("icons/16/next.png"));
		// nextItemBtn.setStyleName("link");
		// nextItemBtn.setDescription("Show next item");
		// layout.addComponent(nextItemBtn);
		// layout.setComponentAlignment(nextItemBtn, Alignment.MIDDLE_RIGHT);
		//
		//
		// historyBtn = new Button(null, new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// previewForm.showHistory();
		// }
		// });
		// historyBtn.setIcon(new ThemeResource("icons/16/history.png"));
		// historyBtn.setStyleName("link");
		// historyBtn.setDescription("Show history log");
		// layout.addComponent(historyBtn);
		// layout.setComponentAlignment(historyBtn, Alignment.MIDDLE_RIGHT);
		//
		// printBtn = new Button(null, new Button.ClickListener() {
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// previewForm.doPrint();
		// }
		// });

		// printBtn.setIcon(new ThemeResource("icons/16/print.png"));
		// printBtn.setStyleName("link");
		// printBtn.setDescription("Print this page");
		// layout.addComponent(printBtn);
		// layout.setComponentAlignment(printBtn, Alignment.MIDDLE_RIGHT);

		if (permissionItem != null) {
			boolean canRead = AppContext.canRead(permissionItem);
			boolean canWrite = AppContext.canWrite(permissionItem);
			boolean canAccess = AppContext.canAccess(permissionItem);

			backBtn.setEnabled(canRead);
			editBtn.setEnabled(canWrite);
			cloneBtn.setEnabled(canWrite);
			deleteBtn.setEnabled(canAccess);
			// printBtn.setEnabled(canRead);
			// historyBtn.setEnabled(canRead);
		}
		return layout;
	}
}
