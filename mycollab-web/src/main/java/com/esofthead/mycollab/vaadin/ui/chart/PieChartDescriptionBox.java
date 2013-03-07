package com.esofthead.mycollab.vaadin.ui.chart;

import java.util.List;

import org.jfree.data.general.DefaultPieDataset;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class PieChartDescriptionBox {

	@SuppressWarnings("rawtypes")
	public static ComponentContainer createLegendBox(
			DefaultPieDataset pieDataSet) {
		CustomLayout boxWrapper = new CustomLayout("centerContent");
		CssLayout mainLayout = new CssLayout();
		mainLayout.addStyleName("border-box");
		mainLayout.setSizeUndefined();
		List keys = pieDataSet.getKeys();
		for (int i = 0; i < keys.size(); i++) {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setMargin(false, false, false, true);
			layout.addStyleName("inline-block");
			Comparable key = (Comparable) keys.get(i);
			String color = "<div style = \" width:8px;height:8px;border-radius:5px;background: #"
					+ GenericChartWrapper.CHART_COLOR_STR[i
							% GenericChartWrapper.CHART_COLOR_STR.length]
					+ "\" />";
			Label lblCircle = new Label(color);
			lblCircle.setContentMode(Label.CONTENT_XHTML);

			Button btnLink = new Button(
					key
							+ "("
							+ String.valueOf(pieDataSet.getValue(key)
									.intValue()) + ")",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
						}
					});
			btnLink.addStyleName("link");
			layout.addComponent(lblCircle);
			layout.setComponentAlignment(lblCircle, Alignment.MIDDLE_CENTER);
			layout.addComponent(btnLink);
			layout.setComponentAlignment(btnLink, Alignment.MIDDLE_CENTER);
			layout.setSizeUndefined();
			mainLayout.addComponent(layout);
		}
		boxWrapper.setSizeFull();
		boxWrapper.addComponent(mainLayout, "centerContent");
		return boxWrapper;
	}

}
