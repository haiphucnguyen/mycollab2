package com.esofthead.mycollab.module.project.view.file;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class FileSearchResultViewImpl extends AbstractView implements
		FileSearchResultView {
	private static final long serialVersionUID = 1L;

	private final Label searchHeader;
	private final VerticalLayout bodyLayout;
	private final ResourceService resourceService;

	public FileSearchResultViewImpl() {
		this.resourceService = AppContext.getSpringBean(ResourceService.class);

		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName(UIConstants.THEME_COMP_HEADER);

		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		headerLayout.setSpacing(true);

		headerWrapper.addComponent(headerLayout);
		this.addComponent(headerWrapper);

		final Embedded headerIcon = new Embedded();
		headerIcon.setSource(MyCollabResource
				.newResource("icons/16/search.png"));
		headerLayout.addComponent(headerIcon);
		headerLayout.setComponentAlignment(headerIcon, Alignment.MIDDLE_LEFT);

		this.searchHeader = new Label();
		headerLayout.addComponent(this.searchHeader);
		headerLayout.setComponentAlignment(this.searchHeader,
				Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(this.searchHeader, 1.0f);

		final Button backButton = new Button("Back to dashboard",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ProjectContentEvent.GotoDashboard(
										FileSearchResultViewImpl.this));

					}
				});
		backButton.addStyleName(UIConstants.THEME_BLUE_LINK);
		headerLayout.addComponent(backButton);

		this.bodyLayout = new VerticalLayout();
		this.bodyLayout.addStyleName(UIConstants.THEME_COMP_BODY);
		this.addComponent(this.bodyLayout);
		this.setMargin(true);
	}

	@Override
	public void displaySearchResult(final String basePath, final String name) {
		this.bodyLayout.removeAllComponents();

		final String header = "Search result of '%s'";
		this.searchHeader.setValue(String.format(header, name));

		final List<Resource> resourceList = this.resourceService
				.searchResourcesByName(name);
		for (final Resource resource : resourceList) {
			this.bodyLayout.addComponent(new Label(resource.getPath()));
		}
	}

}
