package com.esofthead.mycollab.vaadin.ui.chart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.general.DefaultPieDataset;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("rawtypes")
public class PieChartDescriptionBox {

	private static Map<String, Comparable> mapKeyStatus = new HashMap<String, Comparable>();

	public static ComponentContainer createLegendBox(
			final PieChartWrapper pieChartHost,
			final DefaultPieDataset pieDataSet) {
		final CustomLayout boxWrapper = new CustomLayout("legendBox");
		final CssLayout mainLayout = new CssLayout();

		mainLayout.setSizeUndefined();
		final List keys = pieDataSet.getKeys();

		for (int i = 0; i < keys.size(); i++) {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.setMargin(false, false, false, true);
			layout.addStyleName("inline-block");
			final Comparable key = (Comparable) keys.get(i);

			final String color = "<div style = \" width:8px;height:8px;border-radius:5px;background: #"
					+ GenericChartWrapper.CHART_COLOR_STR[i
							% GenericChartWrapper.CHART_COLOR_STR.length]
					+ "\" />";
			final Label lblCircle = new Label(color);
			lblCircle.setContentMode(Label.CONTENT_XHTML);

			final Button btnLink = new Button(
					key
							+ "("
							+ String.valueOf(pieDataSet.getValue(key)
									.intValue()) + ")",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							final String caption = event.getButton()
									.getCaption();
							if (PieChartDescriptionBox.mapKeyStatus
									.containsKey(caption)) {
								final String keyStatus = PieChartDescriptionBox.mapKeyStatus
										.get(caption) + "";
								pieChartHost.onClickedDescription(keyStatus);
							}
						}
					});
			PieChartDescriptionBox.mapKeyStatus.put(btnLink.getCaption(), key);
			btnLink.addStyleName("link");
			layout.addComponent(lblCircle);
			layout.setComponentAlignment(lblCircle, Alignment.MIDDLE_CENTER);
			layout.addComponent(btnLink);
			layout.setComponentAlignment(btnLink, Alignment.MIDDLE_CENTER);
			layout.setSizeUndefined();
			mainLayout.addComponent(layout);
		}
		boxWrapper.setWidth("100%");
		boxWrapper.addComponent(mainLayout, "legendBoxContent");
		return boxWrapper;
	}
}
