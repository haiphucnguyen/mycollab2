package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.crm.domain.TypeRelationship;

public interface TypeRelationshipService extends
		ICrudService<Integer, TypeRelationship> {

	void deleteRelationShip(TypeRelationship typeRelationship);
}
