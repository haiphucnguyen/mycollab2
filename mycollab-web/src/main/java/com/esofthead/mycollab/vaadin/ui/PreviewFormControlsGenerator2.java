package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class PreviewFormControlsGenerator2<T> {

	private AdvancedPreviewBeanForm<T> previewForm;

	private Button previousItem;
	private Button nextItemBtn;
	private Button historyBtn;
	private Button printBtn;

	public PreviewFormControlsGenerator2(AdvancedPreviewBeanForm<T> editForm) {
		this.previewForm = editForm;
	}

	public HorizontalLayout createButtonControls() {
		return createButtonControls(null);
	}

	public HorizontalLayout createButtonControls(String permissionItem) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

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
}
