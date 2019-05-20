package com.qijianguo.ad.service;

import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.vo.CreateUserRequest;
import com.qijianguo.ad.vo.CreateUserResponse;

public interface IUserService {

    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
