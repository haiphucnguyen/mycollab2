package com.esofthead.mycollab.module.crm.view;

public interface IRelatedListHandlers<T> {

    void addRelatedListHandler(RelatedListHandler<T> handler);
    
    void refresh();
}
