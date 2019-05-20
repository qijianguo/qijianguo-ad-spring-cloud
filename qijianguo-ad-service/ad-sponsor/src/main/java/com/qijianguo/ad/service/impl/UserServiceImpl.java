package com.qijianguo.ad.service.impl;

import com.qijianguo.ad.constant.Contants;
import com.qijianguo.ad.dao.AdUserRepository;
import com.qijianguo.ad.entity.AdUser;
import com.qijianguo.ad.exception.AdException;
import com.qijianguo.ad.service.IUserService;
import com.qijianguo.ad.util.CommonUtils;
import com.qijianguo.ad.vo.CreateUserRequest;
import com.qijianguo.ad.vo.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if (!request.validate()) {
            throw new AdException(Contants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = adUserRepository.findByUsername(request.getName());

        if (oldUser != null) {
            throw new AdException(Contants.ErrorMsg.USERNAME_SAVE_ERROR);
        }

        String token = CommonUtils.md5(request.getName());

        AdUser adUser = adUserRepository.save(new AdUser(request.getName(), token));

        CreateUserResponse response = new CreateUserResponse(
                adUser.getId(),
                adUser.getUsername(),
                token,
                adUser.getCreateTime(),
                adUser.getUpdateTime()
        );

        return response;
    }
}
