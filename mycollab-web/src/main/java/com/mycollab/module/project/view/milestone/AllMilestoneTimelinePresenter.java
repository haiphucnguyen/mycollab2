package com.mycollab.module.project.view.milestone;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringComponent
@PrototypeScope
public class AllMilestoneTimelinePresenter implements Serializable {

    private AllMilestoneTimelineWidget view;

    @Autowired
    private MilestoneService milestoneService;

    @Autowired
    private ProjectService projectService;

    void init(AllMilestoneTimelineWidget view) {
        this.view = view;
    }

    List<SimpleMilestone> getMilestones() {
        MilestoneSearchCriteria searchCriteria = new MilestoneSearchCriteria();
        List<Integer> prjKeys = projectService.getProjectKeysUserInvolved(UserUIContext.getUsername(), AppUI.getAccountId());
        if (!prjKeys.isEmpty()) {
            searchCriteria.setProjectIds(new SetSearchField<>(prjKeys));
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField(Milestone.Field.enddate.name(), "ASC")));
            MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
            return (List<SimpleMilestone>) milestoneService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        } else
            return new ArrayList<>();

    }

}
