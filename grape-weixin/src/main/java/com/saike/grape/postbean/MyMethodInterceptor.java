package com.saike.grape.postbean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕增强前");
        Object value = invocation.proceed();
        System.out.println("环绕增强后");
        return value;
    }

}
