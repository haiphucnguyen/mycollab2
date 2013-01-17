/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
public class BugHistoryList extends Depot {

    private HistoryLogComponent historyLogComponent;

    public BugHistoryList(int bugId) {
        super("History", new VerticalLayout());

        VerticalLayout contentContainer = (VerticalLayout) this.content;
        historyLogComponent = new HistoryLogComponent(ModuleNameConstants.PRJ, ProjectContants.BUG, bugId);
        contentContainer.addComponent(historyLogComponent);
        
        historyLogComponent.generateFieldDisplayHandler("description", "Description");
        historyLogComponent.generateFieldDisplayHandler("summary", "Summary");
        historyLogComponent.generateFieldDisplayHandler("detail", "Detail");
    }
}
