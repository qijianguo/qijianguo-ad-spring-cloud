package com.qijianguo.ad.proxytest.cglib;

import com.qijianguo.ad.proxytest.jdkproxy.UserService;
import com.qijianguo.ad.proxytest.jdkproxy.UserServiceImple;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("------ before ------");

        System.out.println(method.getName());
        Object o1 = methodProxy.invoke(o, objects);
        System.out.println("------ after ----");

        return o1;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImple.class);
        enhancer.setCallback(cglibProxy);
        UserService userService = (UserService) enhancer.create();
        userService.getName("hhhh");

    }
}
