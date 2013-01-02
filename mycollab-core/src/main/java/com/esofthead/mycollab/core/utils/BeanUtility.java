package com.esofthead.mycollab.core.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class BeanUtility {

    public static String printBeanObj(Object bean) {
        StringBuffer str = new StringBuffer("Bean class "
                + bean.getClass().getName()).append("\n");
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(),
                    Object.class);

            for (PropertyDescriptor propertyDescriptor : beanInfo
                    .getPropertyDescriptors()) {
                str.append("    Property: ")
                        .append(propertyDescriptor.getName())
                        .append(". Value: ")
                        .append(BeanUtils.getProperty(bean,
                        propertyDescriptor.getName())).append("\n");
            }
            return str.toString();
        } catch (Exception e) {
            return "Exception while try to print bean value";
        }
    }

    public static void main(String[] args) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        TestBean bean = new TestBean(1, "aaa");
        System.out.println(BeanUtility.printBeanObj(bean));
    }

    public static class TestBean {

        private String name;
        private int beanId;

        public TestBean(int beanId, String name) {
            this.beanId = beanId;
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getBeanId() {
            return beanId;
        }
    }
}
