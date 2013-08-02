/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.test;

import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author haiphucnguyen
 */
@ContextConfiguration(locations = {
    "classpath:META-INF/spring/common-context.xml",
    "classpath:META-INF/spring/file-context.xml",
    "classpath:META-INF/spring/crm-context.xml",
    "classpath:META-INF/spring/ecm-context.xml",
    "classpath:META-INF/spring/project-context.xml",
    "classpath:META-INF/spring/tracker-context.xml",
    "classpath:META-INF/spring/user-context.xml",
    "classpath:META-INF/spring/rest-context.xml",
    "classpath:META-INF/spring/datasource-context-test.xml"})
public class ServiceTest {
    
}
