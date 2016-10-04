package com.mycollab.premium.mobile.module.project.view.task;

import com.mycollab.common.domain.OptionVal;
import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.common.service.OptionValService;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.ui.OptionValComboBox;
import com.vaadin.data.Property;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class TaskStatusComboBox extends OptionValComboBox {

    public TaskStatusComboBox() {
        super(StatusI18nEnum.class);
        OptionValService optionValService = AppContextUtil.getSpringBean(OptionValService.class);
        List<OptionVal> options = optionValService.findOptionVals(ProjectTypeConstants.TASK, CurrentProjectVariables.getProjectId(),
                MyCollabUI.getAccountId());
        for (OptionVal option : options) {
            addEntry(option);
        }
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        Object value = newDataSource.getValue();
        if (value == null) {
            newDataSource.setValue(StatusI18nEnum.Open.name());
        }
        super.setPropertyDataSource(newDataSource);
    }
}
