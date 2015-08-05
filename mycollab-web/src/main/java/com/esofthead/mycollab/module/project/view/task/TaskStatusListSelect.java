package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.ListSelect;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class TaskStatusListSelect extends ListSelect {
    public TaskStatusListSelect() {
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
        this.setNullSelectionAllowed(false);
        this.setMultiSelect(true);
        this.setRows(4);
    }

    @Override
    public void attach() {
        OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);
        List<OptionVal> options = optionValService.findOptionVals(ProjectTypeConstants.TASK, CurrentProjectVariables
                .getProjectId(), AppContext.getAccountId());
        for (OptionVal option : options) {
            this.addItem(option);
            this.setItemCaption(option, AppContext.getMessage(OptionI18nEnum.StatusI18nEnum.class, option.getTypeval()));
        }
        super.attach();
    }
}
