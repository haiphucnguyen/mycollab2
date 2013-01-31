package com.esofthead.mycollab.usertracking;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.esofthead.mycollab.usertracking.dao.IpCountryMapper;
import com.esofthead.mycollab.usertracking.domain.IpCountry;

public class Ip2CountryCode {
	private static final String MYBATIS_CONFIG = "usertracking.mybatis.xml";

	public static final String getCountryCode(String ipAddress)
			throws Exception {
		long ipInDecimal = IpToInteger.toDecimalNumber(ipAddress);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sessionFactory = builder.build(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(MYBATIS_CONFIG));
		SqlSession session = null;
		IpCountry item = null;
		try {
			session = sessionFactory.openSession();
			IpCountryMapper mapper = session.getMapper(IpCountryMapper.class);
			item = mapper.selectByIp(ipInDecimal);
		}
		finally {
			if (null != session)
				session.close();
		}
		
		if (null != item) {
			return item.getCountryCode();
		}
		
		return "";
	}
}
