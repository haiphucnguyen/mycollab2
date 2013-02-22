package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class BugDescriptionFieldDisplayWidget extends HorizontalLayout implements LayoutClickListener {
	private LabelStringGenerator menuLinkGenerator = new BugDescriptionLinkLabelStringGenerator();
	private Label lbDes;
	private boolean hasShowLess;
	private String description;
	private String pathIconPlus;
	private String pathIconMinus;
	private static int NUM_CUT = 100;
	
	public BugDescriptionFieldDisplayWidget(String content) {
		description = content;
		ThemeResource iconPlus = new ThemeResource("icons/16/plus.png");
		ThemeResource iconMinus = new ThemeResource("icons/16/minus.png");
		
		String contentLabel = menuLinkGenerator.handleText(content);
		lbDes = new Label(description, Label.CONTENT_XHTML);
		if ( contentLabel != null && contentLabel.length() > NUM_CUT) {
			
			hasShowLess = true;
			pathIconPlus = " <img src=\"VAADIN/themes/mycollab/" + iconPlus.getResourceId() + "\" />";
			pathIconMinus = " <img src=\"VAADIN/themes/mycollab/" + iconMinus.getResourceId() + "\" />";;
			contentLabel += " " + pathIconPlus;
			lbDes.setValue(contentLabel);
			lbDes.addStyleName(UIConstants.LABEL_CLICKABLE);
		}
		lbDes.setDescription(description);
		this.addComponent(lbDes);
		this.addListener(this);
	}
	
	private static class BugDescriptionLinkLabelStringGenerator implements
			LabelStringGenerator {

		@Override
		public String handleText(String value) {
			if (value != null && value.length() > NUM_CUT) {
				return value.substring(0, NUM_CUT) + "...";
			}
			return value;
		}

	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		if (event.getClickedComponent() instanceof Label) {
			if (description != null && description.length() > NUM_CUT) {
				if (hasShowLess) {
					lbDes.setValue(description + " " + pathIconMinus);
				} else {
					lbDes.setValue(menuLinkGenerator.handleText(description) + " " +  pathIconPlus);
				}
				lbDes.setContentMode(Label.CONTENT_XHTML);
				hasShowLess = !hasShowLess;
			}
		}
	}
}
