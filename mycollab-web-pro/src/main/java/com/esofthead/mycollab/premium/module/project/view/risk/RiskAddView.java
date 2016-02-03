package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface RiskAddView extends IFormAddView<Risk> {
    HasEditFormHandlers<Risk> getEditFormHandlers();

}
