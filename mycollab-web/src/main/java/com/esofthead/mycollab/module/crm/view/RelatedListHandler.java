package com.esofthead.mycollab.module.crm.view;

import java.util.List;

public interface RelatedListHandler<T> {

    void createNewRelatedItem(String itemId);
    
    void associateSelectItem(List<T> items);
}
