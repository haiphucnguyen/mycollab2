package com.esofthead.mycollab.vaadin.ui;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.esofthead.mycollab.core.MyCollabException;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class VerticalTabsheet extends CustomComponent {
	private static final long serialVersionUID = 1L;

	private VerticalLayout tabNavigator;
	private CssLayout tabContainer;

	private Map<Button, Tab> compMap = new HashMap<Button, Tab>();

	private Button selectedButton = null;
	private Tab selectedComp = null;

	private final String TABSHEET_STYLENAME = "vertical-tabsheet";
	private final String TAB_STYLENAME = "tab";
	private final String TAB_SELECTED_STYLENAME = "tab-selected";

	public VerticalTabsheet() {
		HorizontalLayout contentLayout = new HorizontalLayout();
		tabNavigator = new VerticalLayout();
		tabContainer = new CssLayout();
		tabContainer.setWidth("100%");

		contentLayout.addComponent(tabNavigator);
		contentLayout.addComponent(tabContainer);
		contentLayout.setExpandRatio(tabContainer, 1.0f);
		this.setCompositionRoot(contentLayout);
		this.setStyleName(TABSHEET_STYLENAME);
	}

	public void addTab(Component component, String caption, Resource resource) {
		final Button button = new Button(caption);
		button.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (selectedButton == button)
					return;

				clearTabSelection();
				selectedButton = button;
				selectedButton.addStyleName(TAB_SELECTED_STYLENAME);
				selectedComp = compMap.get(selectedButton);
				fireTabChangeEvent(new SelectedTabChangeEvent(
						VerticalTabsheet.this));
			}
		});
		button.setStyleName(TAB_STYLENAME);
		button.setWidth("100%");

		button.setIcon(resource);
		tabNavigator.addComponent(button);

		tabContainer.removeAllComponents();
		tabContainer.addComponent(component);

		TabImpl tabImpl = new TabImpl(caption, component);
		compMap.put(button, tabImpl);
	}

	private void fireTabChangeEvent(SelectedTabChangeEvent event) {
		this.fireEvent(event);
	}

	private static final Method SELECTED_TAB_CHANGE_METHOD;
	static {
		try {
			SELECTED_TAB_CHANGE_METHOD = SelectedTabChangeListener.class
					.getDeclaredMethod("selectedTabChange",
							new Class[] { SelectedTabChangeEvent.class });
		} catch (final java.lang.NoSuchMethodException e) {
			// This should never happen
			throw new java.lang.RuntimeException(
					"Internal error finding methods in TabSheet");
		}
	}

	public void addSelectedTabChangeListener(
			final TabSheet.SelectedTabChangeListener listener) {

		this.addListener(SelectedTabChangeEvent.class, listener,
				SELECTED_TAB_CHANGE_METHOD);
	}

	public Component selectTab(String viewName) {
		Collection<Button> tabs = compMap.keySet();
		for (Button btn : tabs) {
			Tab tab = compMap.get(btn);
			if (tab.getCaption().equals(viewName)) {
				selectedButton = btn;
				clearTabSelection();
				selectedButton.addStyleName(TAB_SELECTED_STYLENAME);
				selectedComp = tab;
				tabContainer.removeAllComponents();
				tabContainer.addComponent(tab.getComponent());
				return tab.getComponent();
			}
		}
		return null;
	}

	public Tab getSelectedTab() {
		return selectedComp;
	}

	public static class TabImpl implements Tab {

		private String caption;
		private Component component;

		public TabImpl(String caption, Component component) {
			this.caption = caption;
			this.component = component;
		}

		@Override
		public boolean isVisible() {
			throw new MyCollabException("Do not support");
		}

		@Override
		public void setVisible(boolean visible) {
			throw new MyCollabException("Do not support");
		}

		@Override
		public boolean isClosable() {
			throw new MyCollabException("Do not support");
		}

		@Override
		public void setClosable(boolean closable) {
			throw new MyCollabException("Do not support");
		}

		@Override
		public boolean isEnabled() {
			throw new MyCollabException("Do not support");
		}

		@Override
		public void setEnabled(boolean enabled) {
			throw new MyCollabException("Do not support");
		}

		@Override
		public void setCaption(String caption) {
			this.caption = caption;

		}

		@Override
		public String getCaption() {
			return caption;
		}

		@Override
		public Resource getIcon() {
			throw new MyCollabException("Do not support");
		}

		@Override
		public void setIcon(Resource icon) {
			throw new MyCollabException("Do not support");

		}

		@Override
		public String getDescription() {
			throw new MyCollabException("Do not support");
		}

		@Override
		public void setDescription(String description) {
			throw new MyCollabException("Do not support");
		}

		@Override
		public void setComponentError(ErrorMessage componentError) {
			throw new MyCollabException("Do not support");

		}

		@Override
		public ErrorMessage getComponentError() {
			throw new MyCollabException("Do not support");
		}

		@Override
		public Component getComponent() {
			return component;
		}

		@Override
		public void setStyleName(String styleName) {
			component.setStyleName(styleName);

		}

		@Override
		public String getStyleName() {
			return component.getStyleName();
		}

	}

	@Override
	public void setWidth(float width, Unit unit) {
		super.setWidth(width, unit);

		if (getCompositionRoot() != null)
			getCompositionRoot().setWidth(width, unit);
	}

	public void setNavigatorWidth(String width) {
		tabNavigator.setWidth(width);
		Iterator<Component> i = tabNavigator.iterator();
		while (i.hasNext()) {
			Component childComponent = i.next();
			childComponent.setWidth(width);
		}
	}

	public void setNavigatorStyleName(String styleName) {
		tabNavigator.setStyleName(styleName);
	}

	public void addNavigatorStyleName(String styleName) {
		tabNavigator.addStyleName(styleName);
	}

	public void setContainerWidth(String width) {
		tabContainer.setWidth(width);
	}

	public void setContainerStyleName(String styleName) {
		tabContainer.setStyleName(styleName);
	}

	public void addContainerStyleName(String styleName) {
		tabContainer.addStyleName(styleName);
	}

	private void clearTabSelection() {
		Collection<Button> tabs = compMap.keySet();
		for (Button btn : tabs) {
			btn.removeStyleName(TAB_SELECTED_STYLENAME);
		}
	}

}
