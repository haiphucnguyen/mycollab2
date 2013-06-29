package com.esofthead.mycollab.module.project.view.file;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@ViewComponent
public class FileSearchResultViewImpl extends AbstractView implements
		FileSearchResultView {
	private static final long serialVersionUID = 1L;

	private Label searchHeader;
	private VerticalLayout bodyLayout;
	private ResourceService resourceService;

	public FileSearchResultViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);

		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		this.addComponent(headerLayout);

		searchHeader = new Label();
		headerLayout.addComponent(searchHeader);
		headerLayout.setExpandRatio(searchHeader, 1.0f);

		Button backButton = new Button("Back to dashboard",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ProjectContentEvent.GotoDashboard(
										FileSearchResultViewImpl.this));

					}
				});
		headerLayout.addComponent(backButton);

		bodyLayout = new VerticalLayout();
		this.addComponent(bodyLayout);
	}

	@Override
	public void displaySearchResult(String basePath, String name) {
		bodyLayout.removeAllComponents();

		String header = "Search result of '%s'";
		searchHeader.setValue(String.format(header, name));

		List<Resource> resourceList = resourceService
				.searchResourcesByName(name);
		for (Resource resource : resourceList) {
			bodyLayout.addComponent(new Label(resource.getPath()));
		}
	}

}
