package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface StandupAddView extends IFormAddView<StandupReportWithBLOBs> {
    HasEditFormHandlers<StandupReportWithBLOBs> getEditFormHandlers();

}
