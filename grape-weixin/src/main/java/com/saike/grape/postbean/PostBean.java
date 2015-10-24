package com.saike.grape.postbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
/**
 * spring容器启动的时候,初始化每一个bean前,后都会执行该类的方法
 * @author Liubao
 * @2015年6月17日
 *
 */
public class PostBean implements BeanPostProcessor {
    private static int count = 0;
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        count++;
        System.out.println("PostBean初始化前执行的方法内容!"+count);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println(beanName);
        System.out.println(bean.getClass().getName());
        if (beanName.equals(beanName)) {
            System.out.println("PostBean初始化后执行的方法内容"+count);
        }
        return bean;
    }

}
