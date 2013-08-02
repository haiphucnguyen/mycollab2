package com.esofthead.mycollab.module.crm.view;

import java.util.Set;

public interface RelatedListHandler<T> {

    void createNewRelatedItem(String itemId);
    
    void selectAssociateItems(Set<T> items);
}
