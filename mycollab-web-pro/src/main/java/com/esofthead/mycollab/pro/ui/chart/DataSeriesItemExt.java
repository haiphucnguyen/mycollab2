package com.esofthead.mycollab.pro.ui.chart;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.vaadin.addon.charts.model.DataSeriesItem;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public class DataSeriesItemExt extends DataSeriesItem {
    private String key;

    public DataSeriesItemExt(String key, String displayName, Number value) {
        super(StringUtils.trim(displayName, 20, true), value);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
