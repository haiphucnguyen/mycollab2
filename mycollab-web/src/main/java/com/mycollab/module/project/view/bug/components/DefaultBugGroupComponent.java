package com.mycollab.module.project.view.bug.components;

import com.mycollab.module.project.ui.components.IGroupComponent;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class DefaultBugGroupComponent extends MVerticalLayout implements IGroupComponent {
    private Label headerLbl;
    private CssLayout wrapBody;

    protected String titleValue;
    protected int numElements = 0;

    DefaultBugGroupComponent(String titleValue) {
        this.titleValue = titleValue;
        initComponent();
    }

    private void initComponent() {
        this.setMargin(new MarginInfo(true, false, true, false));
        wrapBody = new CssLayout();
        wrapBody.setWidth("100%");
        wrapBody.setStyleName(UIConstants.BORDER_LIST);
        headerLbl = ELabel.h3("");
        this.addComponent(headerLbl);
        this.addComponent(wrapBody);
        updateHeader();
    }

    private void updateHeader() {
        headerLbl.setValue(String.format("%s (%d)", titleValue, numElements));
    }

    void insertBug(SimpleBug bug) {
        wrapBody.addComponent(new BugRowComponent(bug));
        numElements++;
        updateHeader();
    }
}
