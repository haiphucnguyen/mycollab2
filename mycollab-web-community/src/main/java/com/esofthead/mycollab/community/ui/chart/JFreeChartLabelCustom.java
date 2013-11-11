package com.esofthead.mycollab.community.ui.chart;

import java.text.AttributedString;

import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.data.general.PieDataset;

/**
 * 
 * @author haiphucnguyen
 */
public class JFreeChartLabelCustom implements PieSectionLabelGenerator {

	@Override
	public String generateSectionLabel(PieDataset dataset, Comparable key) {
		String result = null;
		if (dataset != null) {
			int value = dataset.getValue(key).intValue();
			if (value == 0) {
				return null;
			}

			result = key.toString() + " (" + dataset.getValue(key).intValue()
					+ ")";
		}
		return result;
	}

	@Override
	public AttributedString generateAttributedSectionLabel(PieDataset dataset,
			Comparable key) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
