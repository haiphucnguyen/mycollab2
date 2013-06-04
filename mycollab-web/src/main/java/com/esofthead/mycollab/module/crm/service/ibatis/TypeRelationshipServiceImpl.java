package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.TypeRelationshipMapper;
import com.esofthead.mycollab.module.crm.domain.TypeRelationship;
import com.esofthead.mycollab.module.crm.domain.TypeRelationshipExample;
import com.esofthead.mycollab.module.crm.service.TypeRelationshipService;

@Service
@Transactional
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

	@Autowired
	private TypeRelationshipMapper typeRelationshipMapper;

	@Override
	public ICrudGenericDAO<Integer, TypeRelationship> getCrudMapper() {
		return typeRelationshipMapper;
	}

	public void deleteRelationShip(TypeRelationship typeRelationship) {
		TypeRelationshipExample ex = new TypeRelationshipExample();
		ex.createCriteria().andType1idEqualTo(typeRelationship.getType1id())
				.andType2idEqualTo(typeRelationship.getType2id())
				.andTypeEqualTo(typeRelationship.getType());
		typeRelationshipMapper.deleteByExample(ex);
	}
}
