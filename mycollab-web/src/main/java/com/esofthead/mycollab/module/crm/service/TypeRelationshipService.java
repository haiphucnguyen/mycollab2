package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.ICrudService;
import com.esofthead.mycollab.module.crm.domain.TypeRelationship;

public interface TypeRelationshipService extends
        ICrudService<TypeRelationship, Integer> {

    void deleteRelationShip(TypeRelationship typeRelationship);
}
