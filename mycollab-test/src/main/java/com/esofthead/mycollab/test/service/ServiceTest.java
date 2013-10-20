/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.test.service;

import org.springframework.test.context.ContextConfiguration;

/**
 * 
 * @author haiphucnguyen
 */
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/common-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/form-context.xml",
		"classpath:META-INF/spring-test/ecm-context-test.xml",
		"classpath:META-INF/spring/migration-context.xml",
		"classpath:META-INF/spring/project-context.xml",
		"classpath:META-INF/spring/tracker-context.xml",
		"classpath:META-INF/spring/user-context.xml",
		"classpath:META-INF/spring-test/datasource-context-test.xml",
		"classpath:META-INF/spring-test/service-context-test.xml" })
public class ServiceTest {

}
