package com.esofthead.mycollab.vaadin.ui;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.vaadin.StreamResourceFactory;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandlers;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class DefaultMassItemActionHandlersContainer extends HorizontalLayout
		implements HasMassItemActionHandlers {

	private static final long serialVersionUID = 1L;

	private Set<MassItemActionHandler> handlers;

	private Map<String, ButtonGroup> groupMap = new HashMap<>();

	public DefaultMassItemActionHandlersContainer() {
		super();
		this.setSpacing(true);
	}

	public void addActionItem(final String id, Resource resource, String groupId) {
		ButtonGroup group = groupMap.get(groupId);

		if (group == null) {
			group = new ButtonGroup();
			groupMap.put(groupId, group);
			this.addComponent(group);
		}
		group.addStyleName(UIConstants.THEME_GRAY_LINK);

		Button optionBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				changeOption(id);
			}
		});
		optionBtn.setIcon(resource);
		optionBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		group.addButton(optionBtn);
	}

	public void addDownloadActionItem(final String id, Resource resource,
			String groupId) {
		ButtonGroup group = groupMap.get(groupId);

		if (group == null) {
			group = new ButtonGroup();
			groupMap.put(groupId, group);
			this.addComponent(group);
		}
		group.addStyleName(UIConstants.THEME_GRAY_LINK);

		Button optionBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				changeOption(id);
			}
		});

		FileDownloader fileDownler = new FileDownloader(new StreamResource(
				new LazyStreamSource(id), "test.pdf"));
		fileDownler.extend(optionBtn);
		optionBtn.setIcon(resource);
		optionBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		group.addButton(optionBtn);
	}

	private void changeOption(String id) {
		if (handlers != null) {
			for (MassItemActionHandler handler : handlers) {
				handler.onSelect(id);
			}
		}
	}

	private class LazyStreamSource implements StreamSource {
		private static final long serialVersionUID = 1L;
		private String id;

		public LazyStreamSource(String id) {
			this.id = id;
		}

		@Override
		public InputStream getStream() {
			return buildStreamResource(id);
		}
	}

	protected InputStream buildStreamResource(String id) {
		throw new MyCollabException("It must be implemented by sub class");
	}

	@Override
	public void addMassItemActionHandler(MassItemActionHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<MassItemActionHandler>();
		}
		handlers.add(handler);

	}
}
