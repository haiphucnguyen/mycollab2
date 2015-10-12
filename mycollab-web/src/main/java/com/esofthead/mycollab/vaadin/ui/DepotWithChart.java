package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public abstract class DepotWithChart extends Depot {
    private Button toogleViewBtn;
    private boolean isPlainMode = true;

    public DepotWithChart() {
        super("", new MVerticalLayout());
        toogleViewBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                isPlainMode = !isPlainMode;
                if (isPlainMode) {
                    toogleViewBtn.setIcon(FontAwesome.BAR_CHART_O);
                    toogleViewBtn.setDescription("Chart mode");
                    displayPlainMode();
                } else {
                    toogleViewBtn.setIcon(FontAwesome.LIST);
                    toogleViewBtn.setDescription("Simple mode");
                    displayChartMode();
                }
            }
        });
        toogleViewBtn.setStyleName(UIConstants.BUTTON_ICON_ONLY);
        toogleViewBtn.setIcon(FontAwesome.BAR_CHART_O);
        toogleViewBtn.setDescription("Simple mode");
        addHeaderElement(toogleViewBtn);
        setContentBorder(true);
        this.setMargin(new MarginInfo(false, false, true, false));
    }

    abstract protected void displayPlainMode();

    abstract protected void displayChartMode();
}
