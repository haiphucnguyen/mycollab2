package com.esofthead.mycollab.pro.module.project.view.bug;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.view.bug.IBugAssigneeChartWidget;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.pro.ui.chart.DataSeriesItemExt;
import com.esofthead.mycollab.pro.ui.chart.PieChartWrapper;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.charts.model.DataSeries;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class BugAssigneeChartWidget extends PieChartWrapper<BugSearchCriteria> implements IBugAssigneeChartWidget {
    @Override
    protected List<GroupItem> loadGroupItems() {
        BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
        return bugService.getAssignedDefectsSummary(searchCriteria);
    }

    @Override
    protected DataSeries buildChartSeries() {
        final DataSeries series = new DataSeries("Assignee");
        if (!groupItems.isEmpty()) {
            for (GroupItem item : groupItems) {
                String assignUser = (item.getGroupid() != null) ? item.getGroupid() : "";
                String assignUserFullName = item.getGroupid() == null ? AppContext.getMessage(BugI18nEnum.OPT_UNDEFINED_USER) :
                        item.getGroupname();
                if (assignUserFullName == null || "".equals(assignUserFullName.trim())) {
                    assignUserFullName = StringUtils.extractNameFromEmail(assignUser);
                }
                series.add(new DataSeriesItemExt(assignUser, assignUserFullName, item.getValue()));
            }
        }
        return series;
    }

    @Override
    protected void clickLegendItem(String key) {
        BugSearchCriteria cloneSearchCriteria = BeanUtility.deepClone(searchCriteria);
        cloneSearchCriteria.setAssignuser(StringSearchField.and(key));
        EventBusFactory.getInstance().post(new BugEvent.SearchRequest(this, cloneSearchCriteria));
    }
}
