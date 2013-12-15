package com.esofthead.mycollab.vaadin.ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandlers;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.vaadin.server.Resource;
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

	public void addActionItem(String id, Resource resource, String groupId) {
		ButtonGroup group = groupMap.get(groupId);
		if (group == null) {
			group = new ButtonGroup();
			groupMap.put(groupId, group);
			this.addComponent(group);
		}

		Button optionBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
		optionBtn.setIcon(resource);
		group.addButton(optionBtn);

	}

	@Override
	public void addMassItemActionHandler(MassItemActionHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<MassItemActionHandler>();
		}
		handlers.add(handler);

	}
}
