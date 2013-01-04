/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;

/**
 *
 * @author haiphucnguyen
 */
public class AccountHistoryLogWindow extends HistoryLogWindow {
    public AccountHistoryLogWindow(String module, String type, int typeid) {
        super(module, type, typeid);
        
        this.generateFieldDisplayHandler("accountname", "Account Name");
    }
}
