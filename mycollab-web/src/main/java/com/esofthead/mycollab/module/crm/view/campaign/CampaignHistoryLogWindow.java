/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class CampaignHistoryLogWindow extends HistoryLogWindow {
    public CampaignHistoryLogWindow(String module, String type, int typeid) {
        super(module, type, typeid);
        this.generateFieldDisplayHandler("campaignname", "Name");
        this.generateFieldDisplayHandler("startdate", "Start Date", HistoryLogWindow.DATE_FIELD);
        this.generateFieldDisplayHandler("enddate", "End Date", HistoryLogWindow.DATE_FIELD);
        this.generateFieldDisplayHandler("status", "Status");
        this.generateFieldDisplayHandler("type", "Type");
        this.generateFieldDisplayHandler("currencyid", "Currency");
        this.generateFieldDisplayHandler("budget", "Budget");
        this.generateFieldDisplayHandler("expectedcost", "Expected Cost");
        this.generateFieldDisplayHandler("actualcost", "Actual Cost");
        this.generateFieldDisplayHandler("expectedrevenue", "Expected Revenue");
        this.generateFieldDisplayHandler("assignuser", "Assigned to");
        this.generateFieldDisplayHandler("description", "Description");
    }
}
