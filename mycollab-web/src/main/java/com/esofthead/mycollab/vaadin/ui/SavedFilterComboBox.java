package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.domain.criteria.SaveSearchResultCriteria;
import com.esofthead.mycollab.common.service.SaveSearchResultService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class SavedFilterComboBox extends ComboBox {
    private BeanContainer<String, SaveSearchResultWithBLOBs> beanItem;

    public SavedFilterComboBox(String type) {
        SaveSearchResultCriteria searchCriteria = new SaveSearchResultCriteria();
        searchCriteria.setType(new StringSearchField(type));
        searchCriteria.setCreateUser(new StringSearchField(AppContext.getUsername()));
        searchCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));

        SaveSearchResultService saveSearchResultService = ApplicationContextUtil.getSpringBean(SaveSearchResultService.class);
        List<SaveSearchResultWithBLOBs> result = saveSearchResultService.findPagableListByCriteria(new SearchRequest<>(
                searchCriteria, 0, Integer.MAX_VALUE));
        beanItem = new BeanContainer<>(SaveSearchResultWithBLOBs.class);
        beanItem.setBeanIdProperty("id");

        for (SaveSearchResultWithBLOBs searchResult : result) {
            beanItem.addBean(searchResult);
        }
        this.setContainerDataSource(beanItem);
        this.setItemCaptionPropertyId("queryname");
    }
}
