package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MixValueComboBox;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class TaskStatusComboBox extends MixValueComboBox {
    public TaskStatusComboBox() {
        super(OptionI18nEnum.StatusI18nEnum.class);
        OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);
        List<OptionVal> options = optionValService.findOptionVals(ProjectTypeConstants.TASK, CurrentProjectVariables
                .getProjectId(), AppContext.getAccountId());
        for (OptionVal option : options) {
            addEntry(option.getTypeval());
        }
    }
}
