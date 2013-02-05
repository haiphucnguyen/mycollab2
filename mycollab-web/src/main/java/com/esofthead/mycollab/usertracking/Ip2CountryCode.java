package com.esofthead.mycollab.usertracking;

import com.esofthead.mycollab.usertracking.dao.IpCountryMapper;
import com.esofthead.mycollab.usertracking.domain.IpCountry;
import java.io.ByteArrayInputStream;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Ip2CountryCode {
//	private static final String MYBATIS_CONFIG = "usertracking.mybatis.xml";

    public static final String getCountryCode(String ipAddress)
            throws Exception {
        long ipInDecimal = IpToInteger.toDecimalNumber(ipAddress);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder
                .build(new ByteArrayInputStream(MybatisConfig.loadConfig()
                .getBytes()));
        SqlSession session = null;
        IpCountry item = null;
        Exception e = null;
        try {
            session = sessionFactory.openSession();
            IpCountryMapper mapper = session.getMapper(IpCountryMapper.class);
            item = mapper.selectByIp(ipInDecimal);
        } catch (Exception ex) {
            e = ex;
        } finally {
            if (null != session) {
                session.close();
            }
        }

        if (null != item) {
            return item.getCountryCode();
        }

        if (null != e) {
            throw e;
        }

        return "";
    }
}
