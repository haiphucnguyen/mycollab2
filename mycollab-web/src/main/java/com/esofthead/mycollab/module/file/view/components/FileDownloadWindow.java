package com.esofthead.mycollab.module.file.view.components;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.file.StreamDownloadResourceFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class FileDownloadWindow extends Window {
	private static final long serialVersionUID = 1L;
	private final Content content;

	public FileDownloadWindow(final Content content) {
		super(content.getName());
		this.setWidth("400px");
		this.center();

		this.content = content;
		this.constructBody();
	}

	private void constructBody() {
		final VerticalLayout layout = new VerticalLayout();
		final Embedded iconEmbed = new Embedded();
		iconEmbed.setSource(MyCollabResource
				.newResource("icons/page_white.png"));
		UiUtils.addComponent(layout, iconEmbed, Alignment.MIDDLE_CENTER);

		final GridFormLayoutHelper info = new GridFormLayoutHelper(1, 4,
				"100%", "80px", Alignment.MIDDLE_LEFT);
		info.getLayout().setWidth("100%");
		info.getLayout().setMargin(false);
		info.getLayout().setSpacing(false);

		if (this.content.getDescription() != null) {
			final Label desvalue = new Label(this.content.getDescription());
			info.addComponent(desvalue, "Description", 0, 0);
		}
		final Label author = new Label(this.content.getCreatedBy());
		info.addComponent(author, "Created by", 0, 1);

		final Label size = new Label(this.content.getSize() + "KB");
		info.addComponent(size, "Size", 0, 2);

		final Label dateCreate = new Label(AppContext.formatDate(this.content
				.getCreated().getTime()));
		info.addComponent(dateCreate, "Date created", 0, 3);

		layout.addComponent(info.getLayout());

		final HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.setSpacing(true);
		final Button download = new Button("Download", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final com.vaadin.terminal.Resource downloadResource = StreamDownloadResourceFactory
						.getStreamResource(FileDownloadWindow.this.content
								.getPath());
				AppContext.getApplication().getMainWindow()
						.open(downloadResource, "_blank");
			}
		});
		download.addStyleName(UIConstants.THEME_BLUE_LINK);
		UiUtils.addComponent(buttonControls, download, Alignment.MIDDLE_CENTER);

		final Button cancle = new Button("Cancel", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				FileDownloadWindow.this.close();
			}
		});
		cancle.addStyleName(UIConstants.THEME_BLUE_LINK);

		UiUtils.addComponent(buttonControls, cancle, Alignment.MIDDLE_CENTER);
		UiUtils.addComponent(layout, buttonControls, Alignment.MIDDLE_CENTER);
		this.addComponent(layout);
	}
}
