package com.lw.activitidemo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(Class clazz){
        Object bean = applicationContext.getBean(clazz);
        return bean;
    }

    public static Object getBean(String name,Class clazz){
        Object bean = applicationContext.getBean(name, clazz);
        return bean;
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
}
