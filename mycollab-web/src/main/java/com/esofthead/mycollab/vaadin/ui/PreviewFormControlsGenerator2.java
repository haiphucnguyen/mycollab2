package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class PreviewFormControlsGenerator2 {

	public static <T> HorizontalLayout createButtonControls(
			AdvancedPreviewBeanForm<? extends ValuedBean> previewForm) {
		return createFormOptionalControls(previewForm, null);
	}

	public static <T> HorizontalLayout createFormOptionalControls(
			final AdvancedPreviewBeanForm<T> previewForm, String permissionItem) {
		HorizontalLayout layout = new HorizontalLayout();

		Button previousItem, nextItemBtn, historyBtn, printBtn;

		layout.setSpacing(false);

		previousItem = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				T item = ((BeanItem<T>) previewForm.getItemDataSource())
						.getBean();
				previewForm.fireGotoPrevious(item);
			}
		});

		previousItem.setIcon(new ThemeResource("icons/16/previous.png"));
		previousItem.setStyleName("link");
		previousItem.setDescription("Show previous item");
		layout.addComponent(previousItem);
		layout.setComponentAlignment(previousItem, Alignment.MIDDLE_RIGHT);

		nextItemBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				T item = ((BeanItem<T>) previewForm.getItemDataSource())
						.getBean();
				previewForm.fireGotoNextItem(item);
			}
		});

		nextItemBtn.setIcon(new ThemeResource("icons/16/next.png"));
		nextItemBtn.setStyleName("link");
		nextItemBtn.setDescription("Show next item");
		layout.addComponent(nextItemBtn);
		layout.setComponentAlignment(nextItemBtn, Alignment.MIDDLE_RIGHT);

		historyBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				previewForm.showHistory();
			}
		});
		historyBtn.setIcon(new ThemeResource("icons/16/history.png"));
		historyBtn.setStyleName("link");
		historyBtn.setDescription("Show history log");
		layout.addComponent(historyBtn);
		layout.setComponentAlignment(historyBtn, Alignment.MIDDLE_RIGHT);

		printBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				previewForm.doPrint();
			}
		});

		printBtn.setIcon(new ThemeResource("icons/16/print.png"));
		printBtn.setStyleName("link");
		printBtn.setDescription("Print this page");
		layout.addComponent(printBtn);
		layout.setComponentAlignment(printBtn, Alignment.MIDDLE_RIGHT);

		if (permissionItem != null) {
			boolean canRead = AppContext.canRead(permissionItem);

			printBtn.setEnabled(canRead);
			historyBtn.setEnabled(canRead);
		}
		return layout;
	}

	public static <T> HorizontalLayout createFormControls(
			final AdvancedPreviewBeanForm<T> previewForm, String permissionItem) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.setWidth("100%");

		HorizontalLayout editButtons = new HorizontalLayout();
		editButtons.setSpacing(true);

		Button editBtn, deleteBtn, cloneBtn;
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
		cloneBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		editButtons.addComponent(cloneBtn);
		editButtons.setComponentAlignment(cloneBtn, Alignment.MIDDLE_CENTER);

		layout.addComponent(editButtons);
		layout.setComponentAlignment(editButtons, Alignment.MIDDLE_CENTER);
		layout.setExpandRatio(editButtons, 1.0f);

		if (permissionItem != null) {
			boolean canWrite = AppContext.canWrite(permissionItem);
			boolean canAccess = AppContext.canAccess(permissionItem);

			editBtn.setEnabled(canWrite);
			cloneBtn.setEnabled(canWrite);
			deleteBtn.setEnabled(canAccess);
		}

		return layout;
	}
}
