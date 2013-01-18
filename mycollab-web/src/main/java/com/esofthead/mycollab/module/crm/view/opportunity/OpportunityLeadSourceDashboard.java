/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.vaadin.ui.JFreeChartWrapper;
import com.vaadin.ui.VerticalLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author haiphucnguyen
 */
public class OpportunityLeadSourceDashboard extends VerticalLayout {
    
    public void setSearchCriteria(OpportunitySearchCriteria criteria) {
        this.removeAllComponents();

        final JFreeChart chart = ChartFactory.createPieChart3D(
            "Pie Chart 3D Demo 1",  // chart title
            createDataset(criteria),                // data
            true,                   // include legend
            true,
            true
        );

        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        
        // OPTIONAL CUSTOMISATION COMPLETED.

        JFreeChartWrapper wrapper = new JFreeChartWrapper(chart);
        wrapper.setHeight("400px");
        wrapper.setWidth("550px");
        wrapper.setGraphHeight(400);
        wrapper.setGraphWidth(550);
        /////////////////////////////////////////////
        this.addComponent(wrapper);
    }
    
    private DefaultPieDataset createDataset(OpportunitySearchCriteria criteria) {
        final DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Java", new Double(43.2));
        result.setValue("Visual Basic", new Double(10.0));
        result.setValue("C/C++", new Double(17.5));
        result.setValue("PHP", new Double(32.5));
        result.setValue("Perl", new Double(1.0));
        return result;
    }
}
