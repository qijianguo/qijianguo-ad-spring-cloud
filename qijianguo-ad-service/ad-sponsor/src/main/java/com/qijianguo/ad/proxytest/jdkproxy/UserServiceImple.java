package com.qijianguo.ad.proxytest.jdkproxy;

public class UserServiceImple implements UserService{


    @Override
    public String getName(String name) {
        System.out.println("返回 name = " + name);
        return name;
    }
}
