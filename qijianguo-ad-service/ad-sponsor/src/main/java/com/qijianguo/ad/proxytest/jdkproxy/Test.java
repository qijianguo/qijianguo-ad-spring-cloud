package com.qijianguo.ad.proxytest.jdkproxy;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        UserService userService = new UserServiceImple();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), myInvocationHandler);
        userServiceProxy.getName("Angus");
    }
}
