package com.esofthead.mycollab.module.file.view;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
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
public class FileBreadcrumb extends Breadcrumb implements View,
		HasSearchHandlers<FileSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private static LabelStringGenerator menuLinkGenerator = new BreadcrumbLabelStringGenerator();
	private List<SearchHandler<FileSearchCriteria>> handers;
	private Folder currentBreadCrumbFolder;

	public FileBreadcrumb() {
		this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setUseDefaultClickBehaviour(false);

		initBreadcrumb();
	}

	public void initBreadcrumb() {
		// home Btn ----------------
		this.addLink(new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FileSearchCriteria criteria = new FileSearchCriteria();
				criteria.setBaseFolder(AppContext.getAccountId().toString()
						+ "/Documents");
				criteria.setRootFolder(AppContext.getAccountId().toString()
						+ "/Documents");
				notifySelectHandler(criteria);
			}
		}));
		this.setHeight(25, Sizeable.UNITS_PIXELS);

		this.select(0);
		Button documentBtnLink = generateBreadcrumbLink("My Documents",
				new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						FileSearchCriteria criteria = new FileSearchCriteria();
						criteria.setBaseFolder(AppContext.getAccountId()
								.toString() + "/Documents");
						criteria.setRootFolder(AppContext.getAccountId()
								.toString() + "/Documents");
						notifySelectHandler(criteria);
					}
				});
		documentBtnLink.addStyleName("link");
		this.addLink(documentBtnLink);
		this.setLinkEnabled(true, 1);
	}

	public void gotoFolder(final Folder folder) {
		initBreadcrumb();
		currentBreadCrumbFolder = folder;
		final String[] path = folder.getPath().split("/");
		final StringBuffer curPath = new StringBuffer("");
		boolean isNeedAdd3dot = (path.length > 6) ? true : false;
		int holder = 0;
		for (int i = 0; i < path.length; i++) {
			String pathName = path[i];
			if (i == 0)
				curPath.append(pathName);
			else
				curPath.append("/").append(pathName);

			if (!pathName.equals(AppContext.getAccountId().toString())) {
				final Button btn = new Button();
				if (!pathName.equals("Documents")) {
					if (pathName.length() > 25) {
						btn.setCaption(pathName.substring(0, 20) + "...");
					} else {
						btn.setCaption(pathName);
					}
					btn.setDescription(pathName);
				}
				final String currentResourcePath = curPath.toString();
				btn.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						FileSearchCriteria criteria = new FileSearchCriteria();
						criteria.setBaseFolder(currentResourcePath);
						criteria.setRootFolder(AppContext.getAccountId()
								.toString() + "/Documents");
						notifySelectHandler(criteria);
					}
				});
				if (i > 1) {
					if (path.length <= 6) {
						this.select(i - 1);
						this.addLink(btn);
						this.setLinkEnabled(true, i);
					} else if (i == path.length - 1 || i == path.length - 2) {
						this.select(holder - 1);
						this.addLink(btn);
						this.setLinkEnabled(true, holder);
						holder++;
					} else {
						if (i > 3 && i < path.length - 2 && isNeedAdd3dot) {
							this.select(i - 1);
							this.addLink(new Button("..."));
							this.setLinkEnabled(true, i);
							isNeedAdd3dot = false;
							holder = i + 1;
						} else if (i <= 3) {
							this.select(i - 1);
							this.addLink(btn);
							this.setLinkEnabled(true, i);
						}
					}
				}
			}
		}
	}

	private static Button generateBreadcrumbLink(String linkname,
			Button.ClickListener listener) {
		return CommonUIFactory.createButtonTooltip(
				menuLinkGenerator.handleText(linkname), linkname, listener);
	}

	@Override
	public void addSearchHandler(final SearchHandler<FileSearchCriteria> handler) {
		if (this.handers == null) {
			this.handers = new ArrayList<SearchHandler<FileSearchCriteria>>();
		}
		this.handers.add(handler);
	}

	public void notifySelectHandler(final FileSearchCriteria criteria) {
		if (this.handers != null) {
			for (final SearchHandler<FileSearchCriteria> handler : this.handers) {
				handler.onSearch(criteria);
			}
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

	public Folder getCurrentBreadCrumbFolder() {
		return currentBreadCrumbFolder;
	}

	public void setCurrentBreadCrumbFolder(Folder currentBreamCrumbFolder) {
		this.currentBreadCrumbFolder = currentBreamCrumbFolder;
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

}
