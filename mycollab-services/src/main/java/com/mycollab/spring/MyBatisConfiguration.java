package com.mycollab.spring;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.persistence.VelocityDriverDeclare;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
@Configuration
@Profile("production")
@Import(DataSourceConfiguration.class)
@DependsOn("dbMigration")
@MapperScan(basePackages = {"com.mycollab.**.dao"})
public class MyBatisConfiguration {
    @Autowired
    private DataSourceConfiguration dbConfig;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dbConfig.dataSource());
        sqlSessionFactory.setTypeAliasesPackage("com.mycollab.common.domain.criteria;" +
                "com.mycollab.module.crm.domain.criteria;" +
                "com.mycollab.module.ecm.domain.criteria;" +
                "com.mycollab.module.file.domain.criteria;" +
                "com.mycollab.module.project.domain.criteria;" +
                "com.mycollab.module.tracker.domain.criteria;" +
                "com.mycollab.module.user.domain.criteria;" +
                "com.mycollab.ondemand.module.billing.domain.criteria;" +
                "com.mycollab.ondemand.module.support.domain.criteria");
        sqlSessionFactory.setTypeAliasesSuperType(SearchCriteria.class);
        sqlSessionFactory.setTypeAliases(new Class[]{VelocityDriverDeclare.class});
        sqlSessionFactory.setTypeHandlersPackage("com.mycollab.mybatis.plugin.ext");
        sqlSessionFactory.setMapperLocations(buildBatchMapperResources(
                "classpath:sqlMap/**/*Mapper*.xml"));

        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlMapClient() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    private Resource[] buildMapperResources(String resourcePath) throws IOException {
        try {
            ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
            return patternResolver.getResources(resourcePath);
        } catch (FileNotFoundException e) {
            return new Resource[0];
        }
    }

    private Resource[] buildBatchMapperResources(String... resourcesPath) throws IOException {
        ArrayList<Resource> resources = new ArrayList<>();
        for (String resourcePath : resourcesPath) {
            CollectionUtils.addAll(resources, buildMapperResources(resourcePath));
        }
        return resources.toArray(new Resource[0]);
    }
}
