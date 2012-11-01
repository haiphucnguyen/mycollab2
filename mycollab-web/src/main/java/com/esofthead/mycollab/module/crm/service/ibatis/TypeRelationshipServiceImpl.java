package com.esofthead.mycollab.module.crm.service.ibatis;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.TypeRelationshipMapper;
import com.esofthead.mycollab.module.crm.domain.TypeRelationship;
import com.esofthead.mycollab.module.crm.domain.TypeRelationshipExample;
import com.esofthead.mycollab.module.crm.service.TypeRelationshipService;

public class TypeRelationshipServiceImpl extends
		DefaultCrudService<Integer, TypeRelationship> implements
		TypeRelationshipService {
	public static final int CONTACT_LEAD = 1;

	public static final int CONTACT_OPPORTUNITY = 2;

	public static final int CONTACT_QUOTE = 3;

	public static final int LEAD_CAMPAIGN = 4;

	public static final int QUOTE_CONTRACT = 5;

	public static final int PRODUCT_CONTRACT = 6;

	public static final int CONTRACT_CONTACT = 7;

	public void deleteRelationShip(TypeRelationship typeRelationship) {
		TypeRelationshipExample ex = new TypeRelationshipExample();
		ex.createCriteria().andType1idEqualTo(typeRelationship.getType1id())
				.andType2idEqualTo(typeRelationship.getType2id())
				.andTypeEqualTo(typeRelationship.getType());
		((TypeRelationshipMapper) daoObj).deleteByExample(ex);
	}
}
