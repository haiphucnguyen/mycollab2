package com.mycollab.premium.mobile.module.project.view.task;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.vaadin.ui.OptionValListSelect;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
// TODO
public class TaskStatusListSelect extends OptionValListSelect {

    public TaskStatusListSelect() {
        super(StatusI18nEnum.class);
//        OptionValService optionValService = AppContextUtil.getSpringBean(OptionValService.class);
//        List<OptionVal> options = optionValService.findOptionVals(ProjectTypeConstants.TASK, CurrentProjectVariables.getProjectId(),
//                AppUI.getAccountId());
//        options.forEach(this::addEntry);
    }
//
//    @Override
//    public void setPropertyDataSource(Property newDataSource) {
//        Object value = newDataSource.getValue();
//        if (value == null) {
//            newDataSource.setValue(StatusI18nEnum.Open.name());
//        }
//        super.setPropertyDataSource(newDataSource);
//    }
}
