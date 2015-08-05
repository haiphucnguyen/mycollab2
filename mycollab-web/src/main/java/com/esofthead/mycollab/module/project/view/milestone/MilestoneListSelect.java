package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ListSelect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class MilestoneListSelect extends ListSelect {
    private static final Map<String, FontAwesome> statusIconsMap;

    static {
        statusIconsMap = new HashMap<>();
        statusIconsMap.put(OptionI18nEnum.MilestoneStatus.InProgress.name(), FontAwesome.SPINNER);
        statusIconsMap.put(OptionI18nEnum.MilestoneStatus.Future.name(), FontAwesome.CLOCK_O);
        statusIconsMap.put(OptionI18nEnum.MilestoneStatus.Closed.name(), FontAwesome.MINUS);
    }

    public MilestoneListSelect() {
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
        this.setNullSelectionAllowed(false);
        this.setMultiSelect(true);
        this.setRows(4);
    }

    @Override
    public void attach() {
        MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
        MilestoneSearchCriteria criteria = new MilestoneSearchCriteria();
        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        List<SimpleMilestone> milestones = milestoneService.findPagableListByCriteria(new SearchRequest<>(criteria));
        for (SimpleMilestone milestone : milestones) {
            this.addItem(milestone);
            this.setItemCaption(milestone, milestone.getName());
            String status = milestone.getStatus();
            this.setItemIcon(milestone, statusIconsMap.get(status));
        }
        super.attach();
    }
}
