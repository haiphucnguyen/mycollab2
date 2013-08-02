package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class ProjectPreviewFormControlsGenerator<T> {

	private final AdvancedPreviewBeanForm<T> previewForm;
	private Button backBtn;
	private Button editBtn;
	private Button deleteBtn;
	private Button cloneBtn;
	private Button previousItem;
	private Button nextItemBtn;
	private Button historyBtn;
	private Button printBtn;
	private Button assignBtn;
	private boolean haveAssignButton;

	public ProjectPreviewFormControlsGenerator(
			final AdvancedPreviewBeanForm<T> editForm) {
		this.previewForm = editForm;
	}

	public HorizontalLayout createButtonControls(final String permissionItem) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");

		backBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final T item = ((BeanItem<T>) previewForm.getItemDataSource())
						.getBean();
				previewForm.fireCancelForm(item);
			}
		});
		backBtn.setIcon(MyCollabResource.newResource("icons/16/back.png"));
		backBtn.setDescription("Back to list");
		backBtn.setStyleName("link");
		layout.addComponent(backBtn);
		layout.setComponentAlignment(backBtn, Alignment.MIDDLE_LEFT);

		final HorizontalLayout editButtons = new HorizontalLayout();
		editButtons.setSpacing(true);
		editButtons.addStyleName("edit-btn");

		if (haveAssignButton) {
			assignBtn = new Button(GenericForm.ASSIGN_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							@SuppressWarnings("unchecked")
							final T item = ((BeanItem<T>) previewForm
									.getItemDataSource()).getBean();
							previewForm.fireAssignForm(item);
						}
					});
			assignBtn.setIcon(MyCollabResource
					.newResource("icons/16/assign.png"));
			assignBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			editButtons.addComponent(assignBtn);
			editButtons.setComponentAlignment(assignBtn,
					Alignment.MIDDLE_CENTER);
		}

		editBtn = new Button(GenericForm.EDIT_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						@SuppressWarnings("unchecked")
						final T item = ((BeanItem<T>) previewForm
								.getItemDataSource()).getBean();
						previewForm.fireEditForm(item);
					}
				});
		editBtn.setIcon(MyCollabResource.newResource("icons/16/edit_white.png"));
		editBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		editButtons.addComponent(editBtn);
		editButtons.setComponentAlignment(editBtn, Alignment.MIDDLE_CENTER);

		deleteBtn = new Button(GenericForm.DELETE_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						@SuppressWarnings("unchecked")
						final T item = ((BeanItem<T>) previewForm
								.getItemDataSource()).getBean();
						previewForm.fireDeleteForm(item);
					}
				});
		deleteBtn.setIcon(MyCollabResource.newResource("icons/16/delete2.png"));
		deleteBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		editButtons.addComponent(deleteBtn);
		editButtons.setComponentAlignment(deleteBtn, Alignment.MIDDLE_CENTER);

		cloneBtn = new Button(GenericForm.CLONE_ACTION,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						@SuppressWarnings("unchecked")
						final T item = ((BeanItem<T>) previewForm
								.getItemDataSource()).getBean();
						previewForm.fireCloneForm(item);
					}
				});
		cloneBtn.setIcon(MyCollabResource.newResource("icons/16/clone.png"));
		cloneBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		editButtons.addComponent(cloneBtn);
		editButtons.setComponentAlignment(cloneBtn, Alignment.MIDDLE_CENTER);

		layout.addComponent(editButtons);
		layout.setComponentAlignment(editButtons, Alignment.MIDDLE_CENTER);
		layout.setExpandRatio(editButtons, 1.0f);

		previousItem = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final T item = ((BeanItem<T>) previewForm.getItemDataSource())
						.getBean();
				previewForm.fireGotoPrevious(item);
			}
		});

		previousItem.setIcon(MyCollabResource
				.newResource("icons/16/previous.png"));
		previousItem.setStyleName("link");
		previousItem.setDescription("Show previous item");
		layout.addComponent(previousItem);
		layout.setComponentAlignment(previousItem, Alignment.MIDDLE_RIGHT);

		nextItemBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final T item = ((BeanItem<T>) previewForm.getItemDataSource())
						.getBean();
				previewForm.fireGotoNextItem(item);
			}
		});

		nextItemBtn.setIcon(MyCollabResource.newResource("icons/16/next.png"));
		nextItemBtn.setStyleName("link");
		nextItemBtn.setDescription("Show next item");
		layout.addComponent(nextItemBtn);
		layout.setComponentAlignment(nextItemBtn, Alignment.MIDDLE_RIGHT);

		historyBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				previewForm.showHistory();
			}
		});
		historyBtn
				.setIcon(MyCollabResource.newResource("icons/16/history.png"));
		historyBtn.setStyleName("link");
		historyBtn.setDescription("Show history log");
		layout.addComponent(historyBtn);
		layout.setComponentAlignment(historyBtn, Alignment.MIDDLE_RIGHT);

		printBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				previewForm.doPrint();
			}
		});

		printBtn.setIcon(MyCollabResource.newResource("icons/16/print.png"));
		printBtn.setStyleName("link");
		printBtn.setDescription("Print this page");
		layout.addComponent(printBtn);
		layout.setComponentAlignment(printBtn, Alignment.MIDDLE_RIGHT);

		if (permissionItem != null) {
			final boolean canRead = CurrentProjectVariables
					.canRead(permissionItem);
			final boolean canWrite = CurrentProjectVariables
					.canWrite(permissionItem);
			final boolean canAccess = CurrentProjectVariables
					.canAccess(permissionItem);

			if (haveAssignButton) {
				assignBtn.setEnabled(canWrite);
			}

			backBtn.setEnabled(canRead);
			editBtn.setEnabled(canWrite);
			cloneBtn.setEnabled(canWrite);
			deleteBtn.setEnabled(canAccess);
			printBtn.setEnabled(canRead);
			historyBtn.setEnabled(canRead);
		}
		return layout;
	}

	public HorizontalLayout createButtonControls(final String permissionItem,
			final boolean haveAssignButton) {
		this.haveAssignButton = haveAssignButton;
		return createButtonControls(permissionItem);
	}

}
