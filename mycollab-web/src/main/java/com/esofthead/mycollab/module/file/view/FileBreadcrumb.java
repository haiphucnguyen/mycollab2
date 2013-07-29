package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.CommonUIFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.lexaden.breadcrumb.Breadcrumb;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;

@ViewComponent
public class FileBreadcrumb extends Breadcrumb implements View {

	private static final long serialVersionUID = 1L;
	private static LabelStringGenerator menuLinkGenerator = new BreadcrumbLabelStringGenerator();
	private int index;

	public FileBreadcrumb() {
		this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setUseDefaultClickBehaviour(false);

		index = 1;
		// home Btn ----------------
		this.addLink(new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO : go to MyDocuments folder
				// EventBus.getInstance().fireEvent(
				// new ShellEvent.GotoProjectModule(this, null));
			}
		}));
		this.setHeight(25, Sizeable.UNITS_PIXELS);
		initBreadcrumb();
	}

	public void initBreadcrumb() {
		Button documentBtnLink = generateBreadcrumbLink("My Documents",
				new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// go to MyDocument folder
					}
				});
		documentBtnLink.addStyleName("link");
		this.addLink(documentBtnLink);
		this.setLinkEnabled(true, 1);
	}

	public void addLinkFolder(Folder folder) {
		index++;
		this.select(index);
		this.addLink(new Button(folder.getName(), new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO : go to folder
			}
		}));
		this.setLinkEnabled(true, index + 1);
		FileLinkBuilder.addLink(folder.getName());
	}

	public void removeLinkFolder(Folder folder) {
	}

	public static class FileLinkBuilder {
		public static String URL_PREFIX_PARAM = "?url=";

		public static String DEFAULT_PREFIX_PARAM = "#";

		public static String rootLink = DEFAULT_PREFIX_PARAM + "file/";

		public static String currentLink = rootLink;

		public static void addLink(String linkName) {
			currentLink += UrlEncodeDecoder.encode(linkName);
			AppContext.addFragment(
					rootLink + UrlEncodeDecoder.encode(linkName), "");
		}
	}

	@Override
	public ComponentContainer getWidget() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private static class BreadcrumbLabelStringGenerator implements
			LabelStringGenerator {

		@Override
		public String handleText(String value) {
			if (value.length() > 35) {
				return value.substring(0, 35) + "...";
			}
			return value;
		}

	}

	private static Button generateBreadcrumbLink(String linkname,
			Button.ClickListener listener) {
		return CommonUIFactory.createButtonTooltip(
				menuLinkGenerator.handleText(linkname), linkname, listener);
	}
}
