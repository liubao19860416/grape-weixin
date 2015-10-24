package com.saike.grape.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext appCtx;

    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHelper.appCtx = applicationContext;
    }

    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }

    public static Object getBean(Class clazz) {
        return appCtx.getBean(clazz);
    }

    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return appCtx;
    }

    public static <T> T getBean2(String beanName) {
        checkApplicationContext();
        return (T) appCtx.getBean(beanName);
    }

    public static <T> T getBean2(Class<?> clazz) {
        checkApplicationContext();
        return (T) appCtx.getBean(clazz);
    }

    public static void cleanApplicationContext() {
        appCtx = null;
    }

    private static void checkApplicationContext() {
        if (appCtx == null) {
            throw new IllegalStateException(
                    "ApplicationContext未注入,请在spring配置文件中中注入该bean");
        }
    }

}
