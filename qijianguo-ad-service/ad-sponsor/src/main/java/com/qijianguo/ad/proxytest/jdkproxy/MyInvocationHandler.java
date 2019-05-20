package com.qijianguo.ad.proxytest.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler() {
    }

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getName".equals(method.getName())) {
            System.out.println("-----before-----");
            Object result = method.invoke(target, args);
            System.out.println("-----after------");
            return result;
        } else {
            Object result = method.invoke(target, args);
            return result;

        }

    }
}
